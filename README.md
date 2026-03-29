#  PharmaCare API

##  Description

PharmaCare API is a Spring Boot RESTful application designed to manage medicines in a pharmacy system.
It provides endpoints to create, retrieve, update, and delete medicine records, ensuring efficient and organized pharmacy inventory management.


##  Technologies Used

* Java
* Spring Boot
* Spring Data JPA
* MySQL
* Maven
* Lombok
* Swagger UI



##  How to Run the Project

1. Clone the repository:

   
   git clone https://github.com/your-username/pharmacare-api.git
   

2. Open the project in IntelliJ IDEA or VS Code

3. Configure the database in `application.yml`:

4. Run the application

5. Access the application at:

   
   http://localhost:8083

##  API Endpoints

| Method | Endpoint            | Description           |
| ------ | ------------------- | --------------------- |
| GET    | /api/medicines      | Get all medicines     |
| GET    | /api/medicines/{id} | Get medicine by ID    |
| POST   | /api/medicines      | Create a new medicine |
| PUT    | /api/medicines/{id} | Update a medicine     |
| DELETE | /api/medicines/{id} | Delete a medicine     |


##  Example Request & Response

###  POST Request

```json
{
  "name": "Paracetamol",
  "price": 500,
  "quantity": 20
}
```

###  Response

```json
{
  "id": 1,
  "name": "Paracetamol",
  "price": 500,
  "quantity": 20
}
```



##  API Testing (Swagger UI)

This project uses Swagger UI for testing and documentation.

Access Swagger UI at:

```
http://localhost:8080/swagger-ui/index.html
```

Use the interface to test all endpoints (GET, POST, PUT, DELETE).


## 📸 Screenshots

###  Swagger UI Home


<img width="1908" height="909" alt="Screenshot 2026-03-28 224348" src="https://github.com/user-attachments/assets/7bc298af-dbd8-4e34-9c4e-59c36ac0e7e8" />

###  Login with google or github

<img width="690" height="427" alt="Screenshot 2026-03-26 085700" src="https://github.com/user-attachments/assets/db06594f-c8db-4ac1-958f-4fe51427dcea" />
<img width="959" height="334" alt="google auth1" src="https://github.com/user-attachments/assets/031656e1-fabc-43dc-8329-d28f0d939830" />



## Author

**Lucie Imanisingizwe**
