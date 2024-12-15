#FamilyFinance

FamilyFinance is a microservices-based application designed to manage family members and their financial expenses. Built using modern technologies such as Spring Boot, FastAPI, and MariaDB, the project ensures security, scalability, and modularity. It utilizes **JWT (JSON Web Tokens)** for secure user authentication and a central **API Gateway** for efficient routing of client requests.

#Features

- User Authentication and Registration
   - Secure registration and login using JWT tokens.  
   - User credentials are managed through a Spring Boot service.  

- Family Management  
   - Add, retrieve, and manage family members.  
   - Implemented via Spring Boot microservices.  

- Expense Management 
   - Track and manage family expenses.  
   - Associate expenses with specific family members.

- Central API Gateway 
   - Developed with FastAPI to handle request routing and token validation.  
   - Acts as a proxy between clients and backend microservices.

- Database Integration
   - MariaDB for persistent storage.  
   - Separate databases for user authentication and family expenses.

#Technologies Used

- Spring Boot: Backend services for authentication and family finance management.  
- FastAPI: Gateway for routing and handling HTTP requests.  
- MariaDB: Relational database for storing user and expense data.  
- Docker: Containerization for easy deployment.  
- JWT: Secure token-based authentication.  

#Architecture

The system follows a modular microservices architecture:  

1. Security Service (Spring Boot)  
   - Handles user registration, login, and JWT generation.  

2. FamilyFinance Service (Spring Boot)  
   - Manages family members and expenses.  

3. API Gateway (FastAPI)
   - Validates JWT tokens and routes requests to the appropriate backend service.  

4. MariaDB Databases  
   - Separate databases for authentication and expense tracking.  

#API Endpoints

### Authentication Service
| Method | Endpoint             | Description             |
|--------|----------------------|-------------------------|
| POST   | `/auth/register`     | Register a new user     |
| POST   | `/auth/login`        | Authenticate user       |

### FamilyFinance Service
| Method | Endpoint             | Description                       |
|--------|----------------------|-----------------------------------|
| POST   | `/family/member`     | Add a new family member           |
| GET    | `/family/members`    | Get all family members            |
| POST   | `/family/expense`    | Add an expense                    |
| GET    | `/family/expenses`   | Retrieve all expenses             |

