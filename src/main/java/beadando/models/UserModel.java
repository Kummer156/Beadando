package beadando.models;

import javax.persistence.*;

/**
 * A model for storing user information.
 */
@Entity
@Table(name = "Users")
public class UserModel {
    /**
     * Users ID.
     */
    @Id
    private int id;
    /**
     * Users name.
     */
    @Column
    private String name;
    /**
     * Users password.
     */
    @Column
    private String password;
    /**
     * Users email.
     */
    @Column
    private String email;
    /**
     * Users address.
     */
    @Column
    private String address;
    /**
     * Users phonenumber.
     */
    @Column
    private String phonenumber;

    /**
     * @return The users ID.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id users id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return The users name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name users name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The users password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password users password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return The users email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email users email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The users address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address users address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return The users phonenumber.
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * @param phonenumber users phonenumber
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * @return The users data as a String.
     */
    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                '}';
    }
}
