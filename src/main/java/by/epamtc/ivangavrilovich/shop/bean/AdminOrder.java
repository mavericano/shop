package by.epamtc.ivangavrilovich.shop.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AdminOrder implements Serializable {
    private Order order;
    private User user;
    private List<CartedProduct> orderedProducts;

    public AdminOrder() {
    }

    public AdminOrder(Order order, User user, List<CartedProduct> orderedProducts) {
        this.order = order;
        this.user = user;
        this.orderedProducts = orderedProducts;
    }

    @Override
    public String toString() {
        return "AdminOrder{" +
                "order=" + order +
                ", user=" + user +
                ", orderedProducts=" + orderedProducts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminOrder that = (AdminOrder) o;
        return Objects.equals(order, that.order) && Objects.equals(user, that.user) && Objects.equals(orderedProducts, that.orderedProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, user, orderedProducts);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<CartedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }
}
