# 🏆 Firsto - Capture Your First Experiences!

## ✨ About the Project

**Firsto** is a web application where users can document and revisit their **"first-time experiences"**, creating a **personalized timeline** of life's memorable moments. Whether it's **skydiving for the first time**, **learning to play an instrument**, or **traveling solo**, Firsto helps you cherish and reflect on these milestones.

## 🚀 Features

- ✅ **User Authentication** - Secure login and registration using Spring Security
- ✅ **Post Experiences** - Add details of your first-time experiences
- ✅ **Personalized Timeline** - View a chronological list of your milestones
- ✅ **Location Tagging** - Save where your experience happened
- ✅ **Bootstrap UI** - Clean and responsive design

## 🏗️ Tech Stack

- **Backend:** Java, Spring Boot, Spring Security, Spring Data JPA
- **Frontend:** Thymeleaf, Bootstrap
- **Database:** H2 (in-memory)
- **Build Tool:** Maven

## 🎯 Getting Started

### 🔹 Prerequisites

- Java 8+
- Maven

### 🔹 Installation

1. **Clone the repository**
   ```sh
   git clone https://github.com/friskycodeur/firsto.git
   cd firsto
   ```
2. **Run the application**
   ```sh
   mvn spring-boot:run
   ```

3. **Access the app**  
   Open [http://localhost:8080](http://localhost:8080) in your browser.

## 🔑 Default Credentials (for testing)

| Username | Password |
|----------|----------|
| `admin` | `admin123` |

## 🛠️ API Endpoints

| Method   | Endpoint                          | Description                       |
|----------|-----------------------------------|-----------------------------------|
| `GET`    | `/home`                           | Home Page                         |
| `GET`    | `/api/experience`                 | View logged in user's experiences |
| `POST`   | `/api/experience/add`             | Add a new experience              |
| `GET`    | `/api/users/stats`                | View user experience stats        |
| `GET`    | `api/experience/<post_id>`        | View a specific experience        |
| `POST`   | `api/experience/update`           | Update an existing experience     |
| `DELETE` | `api/experience/delete/<post_id>` | Delete an existing experience     |



## 📜 License

This project is licensed under the **MIT License**.

---

Made with ❤️ by [Prateek Maheshwari](https://github.com/friskycodeur) 🚀
