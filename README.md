# 🚀 Furniro Upload Service

A robust microservice built with **Spring Boot 3** and **Cloudinary** for efficient image management. This service handles file uploads, metadata persistence in MySQL, and seamless integration with Cloudinary's CDN. It also supports event-driven operations via Kafka.

---

## ✨ Features

- 📸 **Image Upload**: Securely upload images to Cloudinary with automatic metadata tracking.
- 🔄 **Image Update**: Replace existing images while keeping the same database record ID.
- 🗑️ **Image Deletion**: Event-driven deletion from both Cloudinary and the local database.
- ✅ **Activation**: Event-driven activation of uploaded images.
- 🗄️ **Persistence**: Tracks file metadata (public IDs, URLs, uploader info) in a MySQL database.
- 📡 **Kafka Integration**: Asynchronous processing for image activation and deletion.
- 📖 **OpenAPI/Swagger**: Built-in documentation for easy API testing.

---

## 🛠️ Tech Stack

- **Framework**: Spring Boot 3.4.1
- **Language**: Java 17
- **Storage**: Cloudinary (Cloud-based image management)
- **Database**: MySQL (Metadata persistence)
- **Message Broker**: Apache Kafka
- **Documentation**: Springdoc OpenAPI (Swagger)

---

## ⚙️ Setup & Installation

### 1. Prerequisites
- **Java 17** or higher
- **Maven**
- **MySQL Instance**
- **Kafka Cluster**
- **Cloudinary Account**

### 2. Environment Configuration
The service expects the following environment variables (can be set in a `.env` file or system environment):

```properties
# Server Configuration
SERVER_PORT=8084

# Database Configuration
DATABASE_URL=jdbc:mysql://localhost:3307/furniro_db
DATABASE_USERNAME=your_username
DATABASE_PASSWORD=your_password

# Kafka Configuration
KAFKA_BOOTSTRAP_SERVERS=localhost:9092
KAFKA_CONSUMER_GROUP_ID=upload

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

### 1. Upload Image
- **Endpoint**: `POST /file/`
- **Content-Type**: `multipart/form-data`
- **Body**:
  - `file`: The image file to upload.
  - `uploadedBy`: String (e.g., User ID or username).

### 2. Update Image
- **Endpoint**: `PUT /file/`
- **Content-Type**: `multipart/form-data`
- **Body**:
  - `file`: The new image file.
  - `oldFileId`: Integer (ID of the record to update).
  - `uploadedBy`: String.

---

## 🎡 Kafka Events

### Consumers
The service listens to the following Kafka topics:

| Topic | Description | Payload Example |
| :--- | :--- | :--- |
| `upload.active` | Activates an image record in the database. | `{"fileID": 123}` |
| `upload.delete` | Deletes an image from Cloudinary and the database. | `{"fileID": 123}` |

---

## 📂 Project Structure

```text
src/main/java/com/furniro/UploadService/
├── config/         # Configuration classes (Kafka, Cloudinary, etc.)
├── controller/     # REST Controllers
├── database/       # JPA Entities & Repositories
├── dto/            # Data Transfer Objects (Request/Response models)
├── exception/      # Global Exception Handling
├── service/        # Business Logic
│   └── kafka/      # Kafka Producers & Consumers
└── utils/          # Constants and Error Codes
```

---

## 📖 API Documentation
Once running, you can access the Swagger UI at:
- `http://localhost:8084/swagger-ui/index.html` (Note: Port depends on `SERVER_PORT`)

---

## ❤️ Contribution
Feel free to fork this repository and submit pull requests. For major changes, please open an issue first to discuss what you would like to change.
