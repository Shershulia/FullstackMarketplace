
# This is the backend for this project

To setup the backend, navigate to the `marketplace` directory and run the following commands:

```
mvn install
mvn spring-boot:run
```

This will install all the necessary dependencies and start the backend server.

Documentation of java classes can be found in the `marketplace/javedoc` directory. To view the documentation, open the `index.html` file in a browser.

# API documentation
this is some api documentation for the backend

## TokenController API

The TokenController API allows clients to generate JWT tokens for authentication and authorization purposes.
Authentication
Generate Token

Generates a JWT token for the user and returns it as a string.

URL: /token

Method: POST

Request Body:

json

{
    "username": "exampleuser",
    "password": "examplepassword"
}

will return json with the token as token and the userid as the sub (subject) field of the token

## UserController API

This class provides REST API endpoints for handling user-related requests such as creating, updating, deleting, and retrieving users.

Base URL: /user

Endpoint: GET /{id}

Description: Retrieve user information by user ID.

Request Parameters:

    id: User ID of the user to retrieve. It is a path variable and a required parameter.
    token: A token from the header to verify user credentials so that only the user can get their own personal data.

Response: User object with public or private information.

Endpoint: GET /pub/{id}

Description: Retrieve public user information by user ID.

Request Parameters:

    id: User ID of the user to retrieve. It is a path variable and a required parameter.

Response: User object with only public information such as username, email address, and first name.

Endpoint: POST /register

Description: Create a new user in the database.

Request Parameters:

    UserRequest: A JSON object that contains the user information such as username, email, password, name, last name, and age. It is a required parameter.

Response: ID of the created user.

Endpoint: POST /update

Description: Update an existing user in the database.

Request Parameters:

    User: A JSON object that contains the new user information such as username, email, password, name, last name, and age. It is a required parameter.
    token: A token from the header to verify user credentials so that only the user can update their own personal data.

Response: ID of the updated user.

Endpoint: DELETE /delete/{id}

Description: Delete an existing user from the database.

Request Parameters:

    User: A JSON object that contains the user information such as ID, username, email, password, name, last name, and age. It is a required parameter.
    token: A token from the header to verify user credentials so that only the user can delete their own personal data.

Response: None. The HTTP status code is set to 204 (No Content) if the user is deleted successfully.




## ItemController API

The ItemController class is a REST controller that handles requests related to Item objects in the database. It provides endpoints for retrieving, creating, updating and deleting items, as well as categories related to items.
Base URL

http://localhost:8080/
Endpoints
1. Get All Items

    URL: /item
    HTTP Method: GET
    Response: Returns a List of all items in the database.

2. Get All Categories

    URL: /item/categories
    HTTP Method: GET
    Response: Returns a List of all categories in the database with no duplicates.

3. Get Available Categories

    URL: /item/creation-categories
    HTTP Method: GET
    Response: Returns a List of all available categories in the database with no duplicates.

4. Add Category

    URL: /item/creation-categories
    HTTP Method: POST
    Request Body: A Map with the new category as a value for the key "category".
    Headers: The JWT token containing the id of the user in the Authorization header.
    Response: Adds the new category to the database.

5. Delete Category

    URL: /item/creation-categories/{categoryDelete}
    HTTP Method: DELETE
    Path Variable: The category that should be deleted.
    Headers: The JWT token containing the id of the user in the Authorization header.
    Response: Deletes the specified category from the database.

6. Modify Category

    URL: /item/creation-categories/{categoryModified}
    HTTP Method: PUT
    Path Variable: The category to be modified in the database.
    Request Body: A Map with the new category as a value for the key "category".
    Headers: The JWT token containing the id of the user in the Authorization header.
    Response: Modifies the specified category in the database.

7. Get Item by ID

    URL: /item/{id}
    HTTP Method: GET
    Path Variable: The id of the item to get.
    Response: Returns the Item object with the specified id.

8. Get Items by User ID

    URL: /user/{id}/items
    HTTP Method: GET
    Path Variable: The id of the user to get items for.
    Response: Returns a List of all items in the database for the specified user.

9. Delete Item by ID

    URL: /item/{id}
    HTTP Method: DELETE
    Path Variable: The id of the item to delete.
    Response: Deletes the specified item from the database.

10. Save or Update Item

    URL: /item/update
    HTTP Method: POST
    Request Body: The Item object with the updated information.
    Headers: The JWT token containing the id of the user in the Authorization header.
    Response: Saves or updates the specified item in the database and returns its id.

11. Create New Item

    URL: /item/new
    HTTP Method: POST
    Request Body: The Item object with the information of the new item.
    Response: Creates a new item in the database and returns its id.




