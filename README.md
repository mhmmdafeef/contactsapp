

Frontend: Built with Next.js

Backend: Developed using Spring Boot

Database: Uses PostgreSQL

Prerequisites

Before running the project, ensure you have the following installed on your system:


Node.js (LTS version) & npm (for the frontend)

PostgreSQL (for the database)

Java 17+ (for the backend)

Maven (for managing dependencies)

🚀 Steps to Run the Project

1️⃣ Start the Frontend

1)Clone the repository:

2)cd contactsappfrontend

3)Open the frontend folder in your preferred IDE (VS Code, WebStorm, etc.).

4)Install dependencies using npm install command

5)execute npm run dev

6)The frontend will be running at http://localhost:3000.

2️⃣ Setup the Database

1)Create a new PostgreSQL database.

2)Execute the database scripts

3)Navigate to the contactsapp\contactsappbackend\src\main\resources\scripts.sql"l file.

4)Run the script in PostgreSQL to create the necessary tables.

5)Insert sample data  

6)Make Sure database properties are added correctly in application.properties of backend application


3️⃣ Start the Backend

1)Open the backend folder in IntelliJ IDEA (or your preferred IDE).

2)Ensure Maven dependencies are loaded        

3)Run the backend service: mvn spring-boot:run

The backend will be available at http://localhost:8080.

Deployment on AWS

Our application architecture features a static website hosted on AWS S3 that interacts seamlessly with a backend API deployed within a Docker container running on an EC2 instance. This EC2 instance is provisioned inside a dedicated Virtual Private Cloud (VPC) that is segmented into public and private subnets. The frontend, served from S3, is publicly accessible and can optionally be accelerated using CloudFront, while the backend API is deployed on an EC2 instance with a security group configured to allow only necessary inbound traffic. The backend container communicates with an Amazon RDS PostgreSQL database hosted in a private subnet, ensuring that the database remains isolated from direct internet access. The VPC setup—with its defined routing tables, network access control lists, and properly configured subnets—ensures secure, efficient communication between the public-facing components and the protected backend and database layers, offering both scalability and robust security for your application.
