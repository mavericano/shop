package by.epamtc.ivangavrilovich.shop.bean;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    private int productId;
    private String thumbnail;
    private String name;
    private float price;
    private int stock;
    private int type;
    private String typeName;

    public Product() {
    }

    public Product(int productId, String thumbnail, String name, float price, int stock, int type, String typeName) {
        this.productId = productId;
        this.thumbnail = thumbnail;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.type = type;
        this.typeName = typeName;
    }

    public Product(String thumbnail, String name, float price, int stock, int type) {
        this.thumbnail = thumbnail;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && Float.compare(product.price, price) == 0 && stock == product.stock && type == product.type && Objects.equals(thumbnail, product.thumbnail) && Objects.equals(name, product.name) && Objects.equals(typeName, product.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, thumbnail, name, price, stock, type, typeName);
    }

    public int getProductId() {
        return productId;
    }

//    public void setProductId(int productId) {
//        this.productId = productId;
//    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
