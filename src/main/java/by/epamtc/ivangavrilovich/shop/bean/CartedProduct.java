package by.epamtc.ivangavrilovich.shop.bean;

import java.io.Serializable;
import java.util.Objects;

public class CartedProduct implements Serializable {
    private Product product;
    private int quantity;

    public CartedProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public CartedProduct() {
    }

    @Override
    public String toString() {
        return "CartedProduct{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartedProduct that = (CartedProduct) o;
        return quantity == that.quantity && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
