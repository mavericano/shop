package by.epamtc.ivangavrilovich.shop.bean;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {
    private int orderId;
    private int userId;
    private String address;
    private double price;
    private String info;
    private int status;
    private int courierId;

    public Order() {
    }

    public Order(int orderId, int userId, String address, int status, int courierId, double price, String info) {
        this.orderId = orderId;
        this.userId = userId;
        this.address = address;
        this.status = status;
        this.courierId = courierId;
        this.price = price;
        this.info = info;
    }

    public Order(int userId, double price, String address, String info) {
        this.userId = userId;
        this.address = address;
        this.price = price;
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && userId == order.userId && Double.compare(order.price, price) == 0 && status == order.status && courierId == order.courierId && Objects.equals(address, order.address) && Objects.equals(info, order.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, address, price, info, status, courierId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", info='" + info + '\'' +
                ", statusId=" + status +
                ", courierId=" + courierId +
                '}';
    }

    public int getOrderId() {
        return orderId;
    }

//    public void setOrderId(int orderId) {
//        this.orderId = orderId;
//    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCourierId() {
        return courierId;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }
}