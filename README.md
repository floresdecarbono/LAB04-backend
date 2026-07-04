
# 🚀 TaskFlow - Backend API

This is the backend API for the system, developed using **Java 25**, **Spring Boot 4.1.0**, with **Spring Web (WebMVC)**, **Spring Data JPA**, **Spring Validation**, and **Spring Actuator**, and **PostgreSQL** as the database. The application has been structured independently to expose REST endpoints that will be consumed by the frontend team.

---

## 🧱 Technologies & Dependencies

- Java 25  
- Spring Boot 4.1.0  
- Spring Web (WebMVC)  
- Spring Data JPA  
- Spring Validation  
- Spring Actuator  
- PostgreSQL Driver  
- Lombok  
- Maven (Maven Wrapper)

---

## ⚙️ How to Run the Project

### 📦 Build project

```bash
./mvnw clean install
```
### ▶️ Run the application

Windows:
```bash
./mvnw.cmd spring-boot:run
````

Linux / Mac:

```bash
./mvnw spring-boot:run
```

### 🧪 Run tests

```bash
./mvnw test
```

---

## 🛠️ Git Workflow

To ensure organization and code stability, we adopt the **Feature Branches** methodology integrated with **Pull Requests (PRs)**. Follow the workflow below for any new implementation or bug fix.

### 1. Update the Local Base

The main branch (`main`) concentrates the stable version of the project. Before starting any task, switch to it and pull the latest updates:

```bash
git checkout main
git pull origin main
```

---

### 2. Create the Requirement Branch

Each new task must be developed in isolation within its own branch. Use the `feat/` prefix for new features, followed by the requirement name in lowercase and separated by hyphens:

```bash
git checkout -b feat/user-login
```

---

### 3. Semantic Commits (Conventional Commits)

Our commit messages must be clear and standardized so we know exactly what changed in the history. Use the following mandatory prefixes:

* **`feat:`** For the implementation of new requirements or routes.
* **`fix:`** For bug fixes or code corrections.

**Practical Example:**

```bash
git commit -m "feat: add customer registration route"
```

---

### 4. Publish and Open the Pull Request (PR)

Upon completing the development and local testing, push your branch to the remote repository:

```bash
git push origin feat/user-login
```

After pushing, access the GitHub interface to open the **Pull Request**.

> ⚠️ **Attention:** No changes should be merged directly into the `main` branch. The analyst/tech lead will be exclusively responsible for reviewing the code, validating the defined rules, and approving the PR merge.