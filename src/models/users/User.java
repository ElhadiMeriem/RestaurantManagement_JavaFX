package models.users;

public abstract class User {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User() {
    }

    public User(String username, String firstName, String lastName, String email, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password= password;
        this.email = email;
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password ;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String customer_Address) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }
}
