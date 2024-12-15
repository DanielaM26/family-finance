# Define Pydantic models for request bodies
from pydantic import BaseModel


class RegisterRequest(BaseModel):
    firstName: str
    lastName: str
    email: str
    password: str
    age: int
    role: str

class LoginRequest(BaseModel):
    email: str
    password: str

class FamilyMemberRequest(BaseModel):
    firstName: str
    lastName: str
    age: int
    role: str

class ExpenseRequest(BaseModel):
    description: str
    price: float
    quantity: int
    familyMemberId: int