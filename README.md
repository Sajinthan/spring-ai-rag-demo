# Spring AI RAG Demo

## 📌 Introduction
This project demonstrates how to build a **Retrieval-Augmented Generation (RAG) system** using **Spring AI and Ollama**. The system ingests documents, retrieves relevant information based on user queries, and generates AI-powered responses using the **Ollama LLM**.

## 🛠 Features
- **Document Ingestion**: Store documents in an in-memory store.
- **Information Retrieval**: Fetch relevant documents based on queries.
- **AI-Powered Responses**: Use **Ollama** to generate answers with relevant context.
- **Spring Boot API**: RESTful endpoints for interaction.

## 🚀 Getting Started

### **1. Prerequisites**
Ensure you have the following installed:
- Java 21+
- Maven
- Docker (optional, for running Ollama in a container)
- Ollama (for running the AI model)

### **2. Clone the Repository**
```sh
git clone https://github.com/Sajinthan/spring-ai-rag-demo.git
cd spring-ai-rag-demo
```

### **3. Start Ollama**
Ensure Ollama is running before starting the application:
```sh
ollama run llama3.1
```
> Replace with another model if needed.

### **4. Build and Run the Application**
```sh
mvn spring-boot:run
```

The application should now be running at `http://localhost:8080`.

## 🖥 API Endpoints

### **1. Ingest a Document**
```sh
POST /api/rag/ingest
Content-Type: application/json
{
  "content": "Spring AI is a powerful framework for integrating AI into Spring Boot applications."
}
```

### **2. Retrieve Relevant Documents**
```sh
POST /api/rag/retrieve
Content-Type: application/json
{
  "query": "Spring AI"
}
```

### **3. Generate AI Response**
```sh
POST /api/rag/chat
Content-Type: application/json
{
  "query": "What is Spring AI?"
}
```

## 🎯 Next Steps
- Implement **vector search** for better retrieval.
- Store documents in a **database** instead of memory.
- Support additional AI models like **OpenAI**.
- Add **authentication and security**.

## 💡 Contributing
Contributions are welcome! Feel free to open an issue or submit a PR.

## 📬 Contact
For any queries, reach out to **[@Sajinthan](https://github.com/Sajinthan)**.

---

Happy coding! 🚀

