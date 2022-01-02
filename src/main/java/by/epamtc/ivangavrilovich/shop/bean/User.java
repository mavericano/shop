package by.epamtc.ivangavrilovich.shop.bean;

//import com.sun.tools.javac.util.Pair;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private int userId;
    private String email;
    private String password;
    private String defaultAddress;
    private int role;
    private String roleName;
    private boolean banned;

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public User() {
    }

    public User(String email, String password, String defaultAddress, int role, boolean banned) {
        this.email = email;
        this.password = password;
        this.defaultAddress = defaultAddress;
        this.role = role;
        this.banned = banned;
    }

    public User(int userId, String email, String password, String defaultAddress, int role, boolean banned) {
        this(email, password, defaultAddress, role, banned);
        this.userId = userId;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "id=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", defaultAddress='" + defaultAddress + '\'' +
                ", role=" + role +
                ", roleName=" + roleName +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;
        User that = (User) o;
        return (this.userId == that.userId) &&
                (this.email.equals(that.email)) &&
                (this.password.equals(that.password)) &&
                (this.defaultAddress.equals(that.defaultAddress)) &&
                (this.role == that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password, defaultAddress, role);
    }

    public int getUserId() {
        return userId;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
