package by.epamtc.ivangavrilovich.shop.bean;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private int orderId;
    private int userId;
    private List<Product> orderedProducts;
    private String address;
    //private Pair<Integer, String> status;

}
