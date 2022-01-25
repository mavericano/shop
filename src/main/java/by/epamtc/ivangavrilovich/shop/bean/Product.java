package by.epamtc.ivangavrilovich.shop.bean;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    private int productId;
    private String thumbnail;
    private String name;
    private double price;
    private int stock;
    private int type;
    private String typeName;
    private int timesOrdered;
    private String maker;
    private String body;
    private String fret;
    private int scale;
    private int fretAmount;
    private String picks;
    private boolean beltButton;

    public Product() {
    }

    public Product(int productId, String thumbnail, String name, double price, int stock, int type, String typeName, int timesOrdered, String maker, String body, String fret, int scale, int fretAmount, String picks, boolean beltButton) {
        this.productId = productId;
        this.thumbnail = thumbnail;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.type = type;
        this.typeName = typeName;
        this.timesOrdered = timesOrdered;
        this.maker = maker;
        this.body = body;
        this.fret = fret;
        this.scale = scale;
        this.fretAmount = fretAmount;
        this.picks = picks;
        this.beltButton = beltButton;
    }

    public Product(String thumbnail, String name, double price, int stock, int type, int timesOrdered, String maker, String body, String fret, int scale, int fretAmount, String picks, boolean beltButton) {
        this.thumbnail = thumbnail;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.type = type;
        this.timesOrdered = timesOrdered;
        this.maker = maker;
        this.body = body;
        this.fret = fret;
        this.scale = scale;
        this.fretAmount = fretAmount;
        this.picks = picks;
        this.beltButton = beltButton;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{productId=" + productId +
                ", thumbnail='" + thumbnail + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                ", timesOrdered=" + timesOrdered +
                ", maker='" + maker + '\'' +
                ", body='" + body + '\'' +
                ", fret='" + fret + '\'' +
                ", scale=" + scale +
                ", fretAmount=" + fretAmount +
                ", picks='" + picks + '\'' +
                ", beltButton=" + beltButton +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && Double.compare(product.price, price) == 0 && stock == product.stock && type == product.type && timesOrdered == product.timesOrdered && scale == product.scale && fretAmount == product.fretAmount && beltButton == product.beltButton && Objects.equals(thumbnail, product.thumbnail) && Objects.equals(name, product.name) && Objects.equals(typeName, product.typeName) && Objects.equals(maker, product.maker) && Objects.equals(body, product.body) && Objects.equals(fret, product.fret) && Objects.equals(picks, product.picks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, thumbnail, name, price, stock, type, typeName, timesOrdered, maker, body, fret, scale, fretAmount, picks, beltButton);
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public int getTimesOrdered() {
        return timesOrdered;
    }

    public void setTimesOrdered(int timesOrdered) {
        this.timesOrdered = timesOrdered;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFret() {
        return fret;
    }

    public void setFret(String fret) {
        this.fret = fret;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getFretAmount() {
        return fretAmount;
    }

    public void setFretAmount(int fretAmount) {
        this.fretAmount = fretAmount;
    }

    public String getPicks() {
        return picks;
    }

    public void setPicks(String picks) {
        this.picks = picks;
    }

    public boolean isBeltButton() {
        return beltButton;
    }

    public void setBeltButton(boolean beltButton) {
        this.beltButton = beltButton;
    }
}
