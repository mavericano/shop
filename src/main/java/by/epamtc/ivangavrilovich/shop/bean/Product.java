package by.epamtc.ivangavrilovich.shop.bean;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    private int userId;
    private String name;
    private float price;
    private int stock;

    public Product() {
    }

    public Product(int userId, String name, float price, int stock) {
        this.userId = userId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;
        Product that = (Product) o;

        return (this.userId == that.userId) &&
                (this.name.equals(that.name)) &&
                (this.price == that.price) &&
                (this.stock == that.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, price, stock);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "id=" + userId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

    public int getUserId() {
        return userId;
    }

//    public void setUserId(int userId) {
//        this.userId = userId;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
