package no.ntnu.fullstack.marketplace.model;

import jakarta.persistence.*;


/**
    The User class represents a user in the marketplace system. It contains fields for the user's id, username, email, password, name, lastname, and age.
    The class includes constructors for creating a User object with just a username and password, or with all fields, as well as getter and setter methods for each field.
    The id field is an auto-generated unique identifier for each user. The username field is a required field that must be unique, and the email field is optional. The password field is a required field that stores the user's password in a secure manner. The name and lastname fields are optional and store the user's first and last name, respectively. The age field is also optional and stores the user's age as an integer.
    This class uses the Jakarta Persistence API annotations to map the class fields to a database table named "USERDATA". The @Id annotation specifies the id field as the primary key, and the @GeneratedValue annotation with the strategy "IDENTITY" generates a unique value for the id field for each new User object. The @Column annotations specify the names and properties of each column in the USERDATA table.
    Example usage:
    User user = new User("jdoe", "jdoe123");
    user.setEmail("jdoe@example.com");
    user.setName("John");
    user.setLastname("Doe");
    user.setAge(35);
    @author [Your Name]
    @version 1.0
 */
@Entity
@Table (name = "USERDATA")
public class User {
    /**
     * The id field represents the user's id in the database. It is automatically generated by the database when a new user is created. It is used to identify a user in the database.
     * The field is annotated with @Id to indicate that it is the primary key for the table.
     * The field is annotated with @GeneratedValue to indicate that the id is automatically generated by the database when a new user is created.
     */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The username field represents the user's username. It is used to log in to the system. It is unique for each user and is used to identify a user in the system.
     * The field is annotated with @Column to indicate that it is a column in the table.
     * The field is annotated with @NotNull to indicate that the field cannot be null.
     */

    @Column (name = "username", nullable = false, unique = true)
    private String username;

    /**
     * The email field represents the user's email. It is used to contact the user.
     * The field is annotated with @Column to indicate that it is a column in the table.
     */
    @Column (name = "email")
    private String email;

    /**
     * The password field represents the user's password. It is used to log in to the system. It is not hashed in this class, but elsewhere in the system.
     * The field is annotated with @Column to indicate that it is a column in the table.
     * The field is annotated with @NotNull to indicate that the field cannot be null.
     */
    @Column (name = "password", nullable = false)
    private String password;

    /**
     * The name field represents the user's first name. It is used to contact the user and to know who it is.
     * The field is annotated with @Column to indicate that it is a column in the table.
     */
    @Column (name = "name")
    private String name;

    /**
     * The lastname field represents the user's lastname. It is used to contact the user and to know who it is.
     * The field is annotated with @Column to indicate that it is a column in the table.
     */
    @Column (name = "lastname")
    private String lastname;

    /**
     * The age field represents the user's age. It is used to contact the user and to know who it is.
     * The field is annotated with @Column to indicate that it is a column in the table.
     */
    @Column (name = "age")
    private Integer age;

    /**
     * User status that defines his status
     */
    @Column (name = "permission")
    private String permission;


    /**
     * The constructor creates a User object with a username and password. The other fields are set to null.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.email = null;
        this.name = null;
        this.lastname = null;
        this.age = null;
        this.permission=null;
    }

    /**
     * The constructor creates a User object with all fields.
     * @param username The username of the user.
     * @param email The email of the user.
     * @param password The password of the user.
     * @param name The name of the user.
     * @param lastname The lastname of the user.
     * @param age The age of the user.
     * @param permission The permission level of the user.
     */
    public User(String username,String email,String password,String name,String lastname,Integer age,String permission){
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.permission = permission;
    }

    /**
     * Default constructor for JPA, which is used to create a User object from the database.
     * The constructor is empty, so one has to use the setter methods to set the fields.
     */
    public User() {

    }

    /**
     * The getId method returns the id of the user.
     * @return The id of the user.
     */
    public Long getId() {
        return id;
    }

    /**
     * The setId method sets the id of the user.
     * @param id The id of the user.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * The getUsername method returns the username of the user.
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * The setUsername method sets the username of the user.
     * @param username The username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * The getPassword method returns the password of the user.
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * The setPassword method sets the password of the user.
     * @param password The password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * The getEmail method returns the email of the user.
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * The setEmail method sets the email of the user.
     * @param email The email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * The getName method returns the name of the user.
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * The setName method sets the name of the user.
     * @param name The name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The getLastname method returns the lastname of the user.
     * @return The lastname of the user.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * The setLastname method sets the lastname of the user.
     * @param lastname The lastname of the user.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * The getAge method returns the age of the user.
     * @return The age of the user.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * The setAge method sets the age of the user.
     * @param age The age of the user.
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * The getPermission method returns the permission level of the user.
     * @return The permission level of the user.
     */
    public String getPermission() {
        return permission;
    }
    /**
     * The setPermission method sets the permission level of the user.
     * @param permission The permission level of the user.
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * The toString method returns a string representation of the user.
     * @return A string representation of the user.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age + '\'' +
                ", permission=" + permission +

                '}';
    }
}
