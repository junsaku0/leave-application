# Leave Application

The Leave Application project is a Java-based application that allows users to manage leave requests and track employee leave balances. It provides RESTful APIs for creating users, updating user leave details, and fetching user lists based on different roles.

## Dependencies

The project uses the following dependencies:

- Spring Boot: A framework for building Java applications.
- Spring Data JPA: A library for easy implementation of JPA-based repositories.
- Spring Web: A library for building web applications with Spring MVC.
- Spock: A testing framework for Groovy and Java.
- Lombok - for getter & setter
## Getting Started

To get started with the Leave Application project, follow these steps:

1. Clone the repository to your local machine.
2. Open the project in your preferred IDE.
3. Build the project using Gradle.
4. Run the application using the provided main class or by executing the generated JAR file.
5. Access the application's RESTful APIs using a tool like Postman or cURL.

## Usage

The Leave Application project provides the following RESTful APIs:

### for USER 
- `POST /api/v1/user`: Create a new user with the provided user details.
- `GET /api/v1/user/admin`: Fetch a list of users with the "ADMIN" role.
- `GET /api/v1/user`: Fetch a list of all users.
- `PUT /api/v1/user/{id}`: Update the leave details of a user with the specified ID.


### for Leave
- `POST /api/v1/leave` :  Create Leave for Users.
- `GET api/v1/leave/{id}` : Fetch Personal Leave.
- `GET api/v1/leave/head/{id}` : Fetch Employee Leaves
- `GET pi/v1/leave/head` :Fetch all leaves
- `PUT /api/v1/leave/{id}` : updates Leave status



Please refer to the source code and API documentation for more details on the request and response formats.

## Contributing

Contributions to the Leave Application project are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

## License

The Leave Application project is licensed under the [MIT License](LICENSE).
