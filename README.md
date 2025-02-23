This project follows a separated architecture for the frontend and backend:

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
Clone the repository:
sh
Copy
Edit
git clone <repository-url>
cd contactsappfrontend
Open the frontend folder in your preferred IDE (VS Code, WebStorm, etc.).
Install dependencies:
sh
Copy
Edit
npm install
Start the development server:
sh
Copy
Edit
npm run dev
The frontend will be running at http://localhost:3000.
2️⃣ Setup the Database
Create a new PostgreSQL database.
Execute the database scripts:
Navigate to the backend/scripts.sql file.
Run the script in PostgreSQL to create the necessary tables.
Insert sample data if needed (ensure the same usernames exist in both frontend & backend).
3️⃣ Start the Backend
Open the backend folder in IntelliJ IDEA (or your preferred IDE).
Ensure Maven dependencies are loaded.
Run the backend service:
sh
Copy
Edit
mvn spring-boot:run
The backend will be available at http://localhost:8080.
🎯 Summary
Step	Command
Clone Repository	git clone <repo-url>
Start Frontend	npm install → npm run dev (Runs on port 3000)
Setup Database	Create PostgreSQL DB → Run scripts.sql
Start Backend	mvn spring-boot:run (Runs on port 8080)
Now your Contacts App is up and running! 🚀✨

For any issues, feel free to create an issue or reach out. 😊
