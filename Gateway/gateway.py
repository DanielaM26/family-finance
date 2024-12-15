import base64

import requests
from fastapi import FastAPI, Request, HTTPException
from jose import JWTError, jwt

from model import *

# Create FastAPI app instance
app = FastAPI()

# Configuration for the backend services
SECURITY_APP_URL = "http://securityspringapp:8070/api/v1/auth"  # URL for authentication service
FAMILY_FINANCE_APP_URL = "http://familyfinancespringapp:8080/api/v1"  # URL for family finance service
SECRET_KEY = "3AtDHVw88JXE6hPCec83lMj3pAfs7yei9h7ikPj+yaBCEbjjA+DuQm+h927njm9D"  # JWT Secret Key
ALGORITHM = "HS256"  # Algorithm used for JWT validation

# Function to verify JWT tokens
def verify_token(token: str):
    """
    Decodes and validates a JWT token.
    """
    try:
        decoded_secret_key = base64.b64decode(SECRET_KEY)
        payload = jwt.decode(token, decoded_secret_key, algorithms=[ALGORITHM])
        return payload
    except JWTError:
        raise HTTPException(status_code=401, detail="Invalid or expired token")

# Routes for authentication and user registration
@app.post("/auth/register")
def register(request: RegisterRequest):
    """
    Registers a new user and creates a family member for the user.
    """
    # Step 1: Register the user with the authentication service
    user_data = {
        "first_name": request.first_name,
        "last_name": request.last_name,
        "email": request.email,
        "password": request.password,
    }
    auth_response = requests.post(f"{SECURITY_APP_URL}/register", json=user_data)
    if auth_response.status_code != 201:
        raise HTTPException(status_code=auth_response.status_code, detail=auth_response.json())

    # Extract token from the authentication response
    auth_token = auth_response.json().get("token")
    if not auth_token:
        raise HTTPException(status_code=500, detail="Failed to retrieve auth token")

    print("[INFO] Create family member")

    # Step 2: Create a family member with the provided extra fields
    family_member_data = FamilyMemberRequest(
        firstName=request.first_name,
        lastName=request.last_name,
        age=request.age,
        role=request.role
    )
    headers = {"Authorization": f"Bearer {auth_token}"}
    family_response = requests.post(
        f"{FAMILY_FINANCE_APP_URL}/member",
        json=family_member_data.dict(),
        headers=headers
    )
    if family_response.status_code != 201:
        raise HTTPException(status_code=family_response.status_code, detail=family_response.json())

    print("[INFO] Ii gata so create family member")

    return {"message": "User registered and family member created successfully", "user": auth_response.json()}

@app.post("/auth/login")
def login(credentials: LoginRequest):
    """
    Authenticates a user by forwarding the request to the security service.
    """
    response = requests.post(f"{SECURITY_APP_URL}/authenticate", json=credentials.dict())
    if response.status_code != 200:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    return response.json()

# Middleware for token validation
@app.middleware("http")
async def auth_middleware(request: Request, call_next):
    """
    Middleware to validate JWT tokens for protected routes.
    """
    if request.url.path.startswith(("/auth", "/docs", "/redoc", "/openapi.json")):
        return await call_next(request)

    auth_header = request.headers.get("Authorization")
    if not auth_header or not auth_header.startswith("Bearer "):
        raise HTTPException(status_code=401, detail="Missing or invalid Authorization header")

    token = auth_header.split(" ")[1]
    verify_token(token)
    return await call_next(request)

# Proxy routes for Family Finance operations
@app.post("/family/member")
def add_member(request: FamilyMemberRequest, req: Request):
    """
    Forwards a request to add a family member to the Family Finance service.
    """
    token = req.headers.get("Authorization")
    response = requests.post(
        f"{FAMILY_FINANCE_APP_URL}/member", json=request.dict(), headers={"Authorization": token}
    )
    if response.status_code != 201:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    return response.json()

@app.get("/family/members")
def get_all_members(req: Request):
    """
    Fetch all family members.
    """
    token = req.headers.get("Authorization")
    response = requests.get(f"{FAMILY_FINANCE_APP_URL}/members", headers={"Authorization": token})
    if response.status_code != 200:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    return response.json()

@app.post("/family/expense")
def add_expense(request: ExpenseRequest, req: Request):
    """
    Forwards a request to add an expense to the Family Finance service.
    """
    token = req.headers.get("Authorization")
    response = requests.post(
        f"{FAMILY_FINANCE_APP_URL}/expense", json=request.dict(), headers={"Authorization": token}
    )
    if response.status_code != 201:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    return response.json()

@app.get("/family/expenses")
def get_expenses(req: Request):
    """
    Fetch all expenses.
    """
    token = req.headers.get("Authorization")
    response = requests.get(f"{FAMILY_FINANCE_APP_URL}/expenses", headers={"Authorization": token})
    if response.status_code != 200:
        raise HTTPException(status_code=response.status_code, detail=response.json())
    return response.json()
