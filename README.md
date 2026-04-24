# 🚀 Furniro Upload Service

A robust microservice built with **Spring Boot 3** and **Cloudinary** for efficient image management. This service handles file uploads, persistence in MySQL, and seamless integration with Cloudinary's CDN.

---

## ✨ Features

- 📸 **Image Upload**: Securely upload images to Cloudinary with automatic folder organization.
- 🗑️ **Image Deletion**: Synchronized deletion from both Cloudinary and the local database.
- 🗄️ **Persistence**: Tracks file metadata (public IDs, URLs, uploader info) in a MySQL database.
- 🔐 **Validation**: Ensures only valid image files are processed.
- 🛠️ **RESTful API**: Simple endpoints for integration with frontend or other services.
- 📖 **OpenAPI/Swagger**: Built-in documentation for easy API testing.

---

## 🛠️ Tech Stack

- **Framework**: Spring Boot 3
- **Language**: Java 17
- **Storage**: Cloudinary (Cloud-based image management)
- **Database**: MySQL (Metadata persistence)
- **Cache/Session**: Redis (Session management)
- **Monitoring**: Spring Boot DevTools
- **Documentation**: Springdoc OpenAPI (Swagger)

---

## ⚙️ Setup & Installation

### 1. Prerequisites
- **Java 17** or higher
- **Maven**
- **MySQL Instance**
- **Cloudinary Account** (Free tier works)

### 2. Environment Configuration
Create a `.env` file in the root directory (or use your preferred method) with the following variables:

```properties
# Server Configuration
SERVER_PATH=/api/v1/furniro/upload-service

# Database Configuration
DATABASE_URL=jdbc:mysql://localhost:3307/furniro_db
DATABASE_USERNAME=your_username
DATABASE_PASSWORD=your_password

# Cloudinary Configuration
CLOUDINARY_NAME=your_cloud_name
CLOUDINARY_KEY=your_api_key
CLOUDINARY_SECRET=your_api_secret
```

### 3. Run the Application
Using the Maven wrapper:

```bash
./mvnw clean spring-boot:run
```

---

## 📡 API Reference

### Upload Image
- **Endpoint**: `POST /api/v1/furniro/upload-service/upload/`
- **Content-Type**: `multipart/form-data`
- **Body**:
  - `file`: The image file to upload.
  - `uploadedBy`: String (e.g., User ID or username).

### Delete Image
- **Endpoint**: `DELETE /api/v1/furniro/upload-service/upload/{id}`
- **Path Variable**: `id` - The numeric ID of the file record in the database.

### API Documentation
Once running, you can access the Swagger UI at:
- `http://localhost:8080/swagger-ui.html`

---

## 📂 Project Structure

```text
src/main/java/com/furniro/UploadService/
├── controller/     # REST Controllers
├── service/        # Business Logic & Cloudinary Integration
├── database/       # JPA Entities & Repositories
├── dto/            # Data Transfer Objects (Request/Response models)
├── exception/      # Global Exception Handling
└── utils/          # Constants and Error Codes
```

---

## ❤️ Contribution
Feel free to fork this repository and submit pull requests. For major changes, please open an issue first to discuss what you would like to change.

---
*Distributed under the MIT License. See `LICENSE` for more information.*
# UploadService
# UploadService
