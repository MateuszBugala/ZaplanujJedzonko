package pl.coderslab.model;

import org.mindrot.jbcrypt.BCrypt;

public class Admins {
    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private int superadmin;
    private boolean enable;

    public Admins(String firstName, String lastName, String email, String password, int superadmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.setPassword(password);
        this.superadmin = superadmin;

    }

    public Admins() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSuperadmin() {
        return superadmin;
    }

    public void setSuperadmin(int superadmin) {
        this.superadmin = superadmin;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Admins [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", superadmin=" + superadmin + ", enable=" + enable+"]";

    }
}
