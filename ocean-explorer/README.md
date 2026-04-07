Ocean Explorer API
A robust Spring Boot application designed to control a robotic probe exploring the ocean floor. The project follows TDD (Test Driven Development) principles to ensure movement accuracy, boundary safety, and obstacle avoidance.

Tech Stack
Java: 17 or 22
Framework: Spring Boot 3.x
Testing: TestNG / JUnit
Build Tool: Maven

🛠 Features
Precise Movement: Supports Forward (F), Backward (B), and 90° Left/Right (L/R) turns.
Boundary Validation: Prevents the probe from moving outside the defined grid.
Obstacle Detection: The probe automatically halts if an obstacle is detected in its path.
Path Tracking: Maintains a history of all coordinates visited during the mission.
Case Insensitivity: Gracefully handles both uppercase and lowercase commands/directions.

🏁 Getting Started
1. Prerequisites
    JDK 17 or higher
    Maven 3.6+

2. Run the Application
   Open your terminal in the project root and run:

Bash
mvn spring-boot:run
The server will start on port 9494 (as configured in application.properties).

API Usage
Move Probe
Endpoint: POST /api/explorer/move
Note: For demonstration purposes, a sample obstacle is placed at coordinate (2, 2).

Query Parameters:
| Parameter | Type | Required | Description |
| :--- | :--- | :--- | :--- |
| x | int | Yes | Starting X-coordinate |
| y | int | Yes | Starting Y-coordinate |
| dir | String | Yes | Initial Direction (NORTH, EAST, SOUTH, WEST) |
| commands | String | Yes | Sequence of moves (e.g., "FFRFFL") |

Example Request (Postman)
http://localhost:9494/api/explorer/move?x=0&y=0&dir=NORTH&commands=FFRFF

Example Response
JSON
{
"x": 1,
"y": 2,
"direction": "EAST",
"visitedPath": [
"(0,1)",
"(0,2)",
"(1,2)"
]
}
🧪 Running Tests
To execute the test suite and verify the movement logic:

Bash
mvn test
📂 Project Structure
model: Contains data entities (Probe, Grid, Direction).
service: Contains the core logic for movement and validation (ProbeService).
controller: Handles REST API requests and input validation (ExplorerController).
test: Contains comprehensive TestNG unit tests.