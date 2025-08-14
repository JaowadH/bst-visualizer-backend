# BST Visualizer Backend

A Spring Boot backend API for storing and visualizing Binary Search Trees (BSTs), including balanced BST generation.  
This service is designed to pair with a frontend visualizer that consumes the tree data and renders it interactively.

---

## Features
- Create BSTs from a list of integers
- Optionally generate a **balanced BST**
- Retrieve stored BSTs
- List all saved trees with metadata
- Health check endpoints for uptime monitoring
- Deployed and hosted on **Render**
- Ready to integrate with a React frontend

---

## Hosting & Deployment
This backend is deployed on **[Render](https://render.com)** as a public web service.

**CI/CD** is managed via **GitHub Actions**:
- On each push to `main`, the pipeline triggers a Render deploy
- The backend is rebuilt and restarted automatically
- `application.yml` manages database and environment configs

**Live API Base URL:**
```
https://bst-visualizer-backend.onrender.com
```

---

## Frontend Integration
The planned frontend (React-based) will:
1. Call `POST /api/trees` to create a new tree
2. Fetch and render trees with `GET /api/trees` and `GET /api/trees/{id}`
3. Display both the standard and balanced BST structures side by side

---

## API Endpoints

### Health Check
```http
GET /actuator/health
```
**Response**
```json
{"status":"UP","groups":["liveness","readiness"]}
```

---

### List Trees
```http
GET /api/trees
```
**Query Parameters**
- `offset` (optional, default `0`)
- `limit` (optional, default `20`)

**Response**
```json
{
  "items": [
    {
      "id": "uuid",
      "numbers": "8,3,10,1,6",
      "createdAt": "2025-08-14T21:42:27.961984Z"
    }
  ],
  "total": 1
}
```

---

### Create a New Tree
```http
POST /api/trees
```
**Request Body**
```json
{
  "numbers": [8,3,10,1,6],
  "balanced": true
}
```

**Response**
```json
{
  "id": "2dad17ae-37c3-4496-8698-741297e2a28e",
  "numbers": [8,3,10,1,6],
  "bst": { "@value": 8, "left": {}, "right": {} },
  "balancedBst": { "@value": 6, "left": {}, "right": {} },
  "createdAt": "2025-08-14T21:42:27.961984Z"
}
```

---

### Get a Specific Tree
```http
GET /api/trees/{id}
```
**Example**
```http
GET /api/trees/2dad17ae-37c3-4496-8698-741297e2a28e
```

**Response**
```json
{
  "id": "2dad17ae-37c3-4496-8698-741297e2a28e",
  "numbers": [8,3,10,1,6],
  "bst": { "@value": 8, "left": {}, "right": {} },
  "balancedBst": { "@value": 6, "left": {}, "right": {} },
  "createdAt": "2025-08-14T21:42:27.961984Z"
}
```

---

## Local Development

### Prerequisites
- Java 17+
- Maven
- PostgreSQL (local or remote)
- Git

### Run Locally
```bash
# Clone the repo
git clone https://github.com/jaowadH/bst-visualizer-backend.git
cd bst-visualizer-backend

# Build the app
mvn clean package

# Run the app
mvn spring-boot:run
```

---

## Environment Variables
The backend reads configuration from `application.yml` and environment variables:
- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`

---

## License
MIT License. See `LICENSE` for details.
