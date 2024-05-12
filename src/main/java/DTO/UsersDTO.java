package DTO;

public class UsersDTO {

    private String name;
    private String password;
    private String email;
    private int admin;

    // Constructors, getters, and setters

    public UsersDTO() {
    }

    public UsersDTO(String name, String password, String email, int admin) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.admin = admin;
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
