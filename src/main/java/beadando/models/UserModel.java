package beadando.models;

/*-
 * #%L
 * Beadando
 * %%
 * Copyright (C) 2016 - 2018 Faculty of Informatics, University of Debrecen
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

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
