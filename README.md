# SQL-AI

An AI-powered service for generating and executing SQL queries based on natural language questions.

## 📋 Overview

SQL-AI is a Spring Boot application that leverages Large Language Models (LLMs) to translate natural language questions into SQL queries. The system uses Ollama with the SQLCoder model to enable intelligent SQL generation.

## ✨ Features

- **Natural Language SQL Generation**: Translate questions into SQL queries
- **Automatic Query Execution**: Execute generated SELECT statements automatically
- **RESTful API**: Simple HTTP endpoints for integration
- **Ollama Integration**: Uses local LLM models for privacy and performance
- **Schema-aware**: Uses database schema for contextual SQL generation

## 🚀 Quick Start

### Prerequisites

- Java 17 or higher
- Gradle 7.x or higher
- Ollama installed and running
- SQLCoder model available in Ollama

### Ollama Setup

1. Install Ollama from [ollama.ai](https://ollama.ai)
2. Download the SQLCoder model:
   ```bash
   ollama pull sqlcoder:7b
   ```
3. Ensure Ollama is running on port 11434

### Starting the Application

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd sql-ai
   ```

2. Run the application:
   ```bash
   ./gradlew bootRun
   ```

3. The application will be available at: `http://localhost:8080/sql-ai`

## 📖 API Documentation

### Base URL
```
http://localhost:8080/sql-ai/chat
```

### Endpoints

#### Generate SQL Query
```http
POST /sql
Content-Type: application/json

{
  "question": "Show me all users with name John"
}
```

**Response:**
```sql
SELECT * FROM users WHERE name = 'John';
```

#### Generate and Execute SQL
```http
POST /
Content-Type: application/json

{
  "question": "How many users are in the database?"
}
```

**Response:**
```json
{
  "sqlQuery": "SELECT COUNT(*) FROM users;",
  "results": [
    {
      "count": 42
    }
  ]
}
```

## 💡 Usage Examples

### Example 1: Simple Query
```bash
curl -X POST http://localhost:8080/sql-ai/chat/sql \
  -H "Content-Type: application/json" \
  -d '{"question": "Find all active users"}'
```

### Example 2: Aggregation
```bash
curl -X POST http://localhost:8080/sql-ai/chat \
  -H "Content-Type: application/json" \
  -d '{"question": "How many orders were placed this month?"}'
```

### Example 3: Join Query
```bash
curl -X POST http://localhost:8080/sql-ai/chat/sql \
  -H "Content-Type: application/json" \
  -d '{"question": "Show users with their latest orders"}'
```

## ⚙️ Configuration

### application.properties
```properties
# Application name
spring.application.name=sql-ai

# Server configuration
server.servlet.context-path=/sql-ai

# Ollama configuration
spring.ai.ollama.model=sqlcoder:7b
spring.ai.ollama.base-url=http://localhost:11434
```

### Customizing Database Schema

1. Edit `src/main/resources/schema.sql` with your database schema
2. Update `src/main/resources/sql-prompt-template.st` if needed

## 🏗️ Project Structure

```
src/main/java/com/schumaker/sql_ai/
├── SqlAiApplication.java          # Main application class
├── model/
│   ├── SqlRequest.java           # Request model
│   └── SqlResponse.java          # Response model
├── service/
│   └── SqlAiService.java         # AI service for SQL generation
└── view/
    └── SqlAiController.java      # REST Controller

src/main/resources/
├── application.properties        # Application configuration
├── schema.sql                   # Database schema
└── sql-prompt-template.st       # AI prompt template
```

## 🛠️ Development

### Local Development

1. Start Ollama: `ollama serve`
2. Run application in development mode: `./gradlew bootRun`
3. Run tests: `./gradlew test`

### Build

```bash
./gradlew build
```

This generates a JAR file in `build/libs/`.

## 🔧 Troubleshooting

### Common Issues

1. **Ollama not reachable**
   - Check if Ollama is running: `ollama list`
   - Verify the URL in application.properties

2. **SQLCoder model not found**
   - Download the model: `ollama pull sqlcoder:7b`
   - Check available models: `ollama list`

3. **Database connection failed**
   - Configure database connection in application.properties
   - Ensure JdbcTemplate is properly configured

## 📝 License

This project is licensed under the [MIT License](LICENSE).

## 🤝 Contributing

Contributions are welcome! Please create a pull request or open an issue for discussions.

## 📞 Support

For questions or issues, please create an issue in this repository.
