package by.epamtc.ivangavrilovich.shop.DAO.impl;

import by.epamtc.ivangavrilovich.shop.DAO.ConnectionPool;
import by.epamtc.ivangavrilovich.shop.DAO.exceptions.DAOException;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.ProductDAO;
import by.epamtc.ivangavrilovich.shop.bean.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class MySQLProductDAO implements ProductDAO {
    private final static Logger logger = LogManager.getLogger();
    public static final String PRODUCT_ID_COLUMN_NAME = "product_id";
    public static final String THUMBNAIL_COLUMN_NAME = "thumbnail";
    public static final String NAME_COLUMN_NAME = "name";
    public static final String PRICE_COLUMN_NAME = "price";
    public static final String STOCK_COLUMN_NAME = "stock";
    public static final String TYPE_COLUMN_NAME = "type";
    public static final String TYPE_NAME_COLUMN_NAME = "type name";
    public static final String TIMES_ORDER_COLUMN_NAME = "times ordered";
    public static final String MAKER_COLUMN_NAME = "maker";
    public static final String BODY_COLUMN_NAME = "body";
    public static final String FRET_COLUMN_NAME = "fret";
    public static final String SCALE_COLUMN_NAME = "scale";
    public static final String FRET_AMOUNT_COLUMN_NAME = "fret amount";
    public static final String PICKS_COLUMN_NAME = "picks";
    public static final String BELT_BUTTON_COLUMN_NAME = "belt button";

    @Override
    public int numberOfProducts() throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT count(*) FROM products JOIN types ON products.type = types.type_id WHERE del=0";
        int numberOfProducts;
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            if (rs.next()) {
                numberOfProducts = rs.getInt(1);
            } else {
                logger.error("Result set for number of products is empty");
                throw new DAOException("Result set for number of products is empty");
            }
        } catch (SQLException e) {
            logger.error("Error while reading number of products", e);
            throw new DAOException("Error while reading number of products", e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }

        return numberOfProducts;
    }

    @Override
    public List<Product> viewPageProducts(int offset, int recsPerPage) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT * FROM products JOIN types ON products.type = types.type_id WHERE del=0 ORDER BY product_id LIMIT ? OFFSET ?";
        PreparedStatement ps;
        ResultSet rs;
        int id;
        String thumbnail;
        String name;
        double price;
        int stock;
        int type;
        String typeName;
        int timesOrdered;
        String maker;
        String fret;
        String body;
        int scale;
        int fretAmount;
        String picks;
        boolean beltButton;
        List<Product> products = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, recsPerPage);
            ps.setInt(2, offset);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(PRODUCT_ID_COLUMN_NAME);
                thumbnail = rs.getString(THUMBNAIL_COLUMN_NAME);
                name = rs.getString(NAME_COLUMN_NAME);
                price = rs.getFloat(PRICE_COLUMN_NAME);
                stock = rs.getInt(STOCK_COLUMN_NAME);
                type = rs.getInt(TYPE_COLUMN_NAME);
                typeName = rs.getString(TYPE_NAME_COLUMN_NAME);
                timesOrdered = rs.getInt(TIMES_ORDER_COLUMN_NAME);
                maker = rs.getString(MAKER_COLUMN_NAME);
                body = rs.getString(BODY_COLUMN_NAME);
                fret = rs.getString(FRET_COLUMN_NAME);
                scale = rs.getInt(SCALE_COLUMN_NAME);
                fretAmount = rs.getInt(FRET_AMOUNT_COLUMN_NAME);
                picks = rs.getString(PICKS_COLUMN_NAME);
                beltButton = rs.getBoolean(BELT_BUTTON_COLUMN_NAME);
                products.add(new Product(id, thumbnail, name, price, stock, type, typeName, timesOrdered, maker, body, fret, scale, fretAmount, picks, beltButton));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.error(String.format("Error while reading page products for offset %d recsPerPage %d", offset, recsPerPage), e);
            throw new DAOException(String.format("Error while reading page products for offset %d recsPerPage %d", offset, recsPerPage), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }

        return products;
    }

    @Override
    public void addProduct(Product product) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "INSERT INTO products(thumbnail,name,price,stock,type,`times ordered`,maker,body,fret,scale,`fret amount`,picks,`belt button`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, product.getThumbnail());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());
            ps.setInt(5, product.getType());
            ps.setInt(6, product.getTimesOrdered());
            ps.setString(7, product.getMaker());
            ps.setString(8, product.getBody());
            ps.setString(9, product.getFret());
            ps.setInt(10, product.getScale());
            ps.setInt(11, product.getFretAmount());
            ps.setString(12, product.getPicks());
            ps.setBoolean(13, product.isBeltButton());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            logger.error("Error while adding product", e);
            throw new DAOException("Error while adding product", e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }
    }

    @Override
    public List<Product> readProducts() throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT * FROM products JOIN types ON products.type = types.type_id WHERE del=0";
        Statement st;
        ResultSet rs;
        List<Product> products = new ArrayList<>();
        int id;
        String thumbnail;
        String name;
        double price;
        int stock;
        int type;
        String typeName;
        int timesOrdered;
        String maker;
        String fret;
        String body;
        int scale;
        int fretAmount;
        String picks;
        boolean beltButton;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt(PRODUCT_ID_COLUMN_NAME);
                thumbnail = rs.getString(THUMBNAIL_COLUMN_NAME);
                name = rs.getString(NAME_COLUMN_NAME);
                price = rs.getFloat(PRICE_COLUMN_NAME);
                stock = rs.getInt(STOCK_COLUMN_NAME);
                type = rs.getInt(TYPE_COLUMN_NAME);
                typeName = rs.getString(TYPE_NAME_COLUMN_NAME);
                timesOrdered = rs.getInt(TIMES_ORDER_COLUMN_NAME);
                maker = rs.getString(MAKER_COLUMN_NAME);
                body = rs.getString(BODY_COLUMN_NAME);
                fret = rs.getString(FRET_COLUMN_NAME);
                scale = rs.getInt(SCALE_COLUMN_NAME);
                fretAmount = rs.getInt(FRET_AMOUNT_COLUMN_NAME);
                picks = rs.getString(PICKS_COLUMN_NAME);
                beltButton = rs.getBoolean(BELT_BUTTON_COLUMN_NAME);
                products.add(new Product(id, thumbnail, name, price, stock, type, typeName, timesOrdered, maker, body, fret, scale, fretAmount, picks, beltButton));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            logger.error("Error while reading all products", e);
            throw new DAOException("Error while reading all products", e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }
        return products;
    }

    @Override
    public void modifyDelStatus(Product product, boolean newStatus) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "UPDATE products SET del=? WHERE product_id = ?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, newStatus);
            ps.setInt(2, product.getProductId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            logger.error("Error while modifying product del status", e);
            throw new DAOException("Error while modifying product del status", e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }
    }

    private String buildSetExpr(Product product) {
        StringJoiner sj = new StringJoiner(", ");
        sj.add(THUMBNAIL_COLUMN_NAME + "='" + product.getThumbnail() + "'");
        sj.add(NAME_COLUMN_NAME + "='" + product.getName() + "'");
        sj.add(PRICE_COLUMN_NAME + "='" + product.getPrice() + "'");
        sj.add(STOCK_COLUMN_NAME + "='" + product.getStock() + "'");
        sj.add(TYPE_COLUMN_NAME + "='" + product.getType() + "'");
        sj.add("`" + TIMES_ORDER_COLUMN_NAME + "`" + "='" + product.getTimesOrdered() + "'");
        sj.add(MAKER_COLUMN_NAME + "='" + product.getMaker() + "'");
        sj.add(BODY_COLUMN_NAME + "='" + product.getBody() + "'");
        sj.add(FRET_COLUMN_NAME + "='" + product.getFret() + "'");
        sj.add(SCALE_COLUMN_NAME + "='" + product.getScale() + "'");
        sj.add("`" + FRET_AMOUNT_COLUMN_NAME + "`" + "='" + product.getFretAmount() + "'");
        sj.add(PICKS_COLUMN_NAME + "='" + product.getPicks() + "'");
        sj.add("`" + BELT_BUTTON_COLUMN_NAME + "`" + "='" + (product.isBeltButton() ? "1" : "0") + "'");

        return sj.setEmptyValue("").toString();
    }

    private void merge(Product current, Product changed) {
        current.setName(changed.getName());
        current.setPrice(changed.getPrice());
        current.setMaker(changed.getMaker());
        current.setBody(changed.getBody());
        current.setFret(changed.getFret());
        current.setScale(changed.getScale());
        current.setFretAmount(changed.getFretAmount());
        current.setPicks(changed.getPicks());
        current.setBeltButton(changed.isBeltButton());
    }

    //TODO fix to avoid reading
    @Override
    public void updateProduct(Product product) throws DAOException {
        Product currentState = retrieveProductById(product.getProductId());
        merge(currentState, product);
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String setExpr = buildSetExpr(currentState);
        String sql = "UPDATE products SET " +
                setExpr +
                " WHERE product_id = ?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, currentState.getProductId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            logger.error("Error while updating product", e);
            throw new DAOException("Error while updating product", e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }
    }

    @Override
    public List<Product> findPopularProducts(int amount) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT * FROM products JOIN types ON products.type = types.type_id WHERE del=0 ORDER BY `times ordered` DESC LIMIT ?";
        PreparedStatement ps;
        ResultSet rs;
        List<Product> products = new ArrayList<>();
        int id;
        String thumbnail;
        String name;
        double price;
        int stock;
        int type;
        String typeName;
        int timesOrdered;
        String maker;
        String fret;
        String body;
        int scale;
        int fretAmount;
        String picks;
        boolean beltButton;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, amount);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(PRODUCT_ID_COLUMN_NAME);
                thumbnail = rs.getString(THUMBNAIL_COLUMN_NAME);
                name = rs.getString(NAME_COLUMN_NAME);
                price = rs.getFloat(PRICE_COLUMN_NAME);
                stock = rs.getInt(STOCK_COLUMN_NAME);
                type = rs.getInt(TYPE_COLUMN_NAME);
                typeName = rs.getString(TYPE_NAME_COLUMN_NAME);
                timesOrdered = rs.getInt(TIMES_ORDER_COLUMN_NAME);
                maker = rs.getString(MAKER_COLUMN_NAME);
                body = rs.getString(BODY_COLUMN_NAME);
                fret = rs.getString(FRET_COLUMN_NAME);
                scale = rs.getInt(SCALE_COLUMN_NAME);
                fretAmount = rs.getInt(FRET_AMOUNT_COLUMN_NAME);
                picks = rs.getString(PICKS_COLUMN_NAME);
                beltButton = rs.getBoolean(BELT_BUTTON_COLUMN_NAME);
                products.add(new Product(id, thumbnail, name, price, stock, type, typeName, timesOrdered, maker, body, fret, scale, fretAmount, picks, beltButton));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.error(String.format("Error while reading %d popular products", amount), e);
            throw new DAOException(String.format("Error while reading %d popular products", amount), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }
        return products;
    }

    @Override
    public Product retrieveProductById(int id) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT * FROM products JOIN types ON products.type = types.type_id WHERE del=0 and product_id=?";
        PreparedStatement ps;
        ResultSet rs;
        String thumbnail;
        String name;
        double price;
        int stock;
        int type;
        String typeName;
        int timesOrdered;
        String maker;
        String fret;
        String body;
        int scale;
        int fretAmount;
        String picks;
        boolean beltButton;
        Product result = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                thumbnail = rs.getString(THUMBNAIL_COLUMN_NAME);
                name = rs.getString(NAME_COLUMN_NAME);
                price = rs.getFloat(PRICE_COLUMN_NAME);
                stock = rs.getInt(STOCK_COLUMN_NAME);
                type = rs.getInt(TYPE_COLUMN_NAME);
                typeName = rs.getString(TYPE_NAME_COLUMN_NAME);
                timesOrdered = rs.getInt(TIMES_ORDER_COLUMN_NAME);
                maker = rs.getString(MAKER_COLUMN_NAME);
                body = rs.getString(BODY_COLUMN_NAME);
                fret = rs.getString(FRET_COLUMN_NAME);
                scale = rs.getInt(SCALE_COLUMN_NAME);
                fretAmount = rs.getInt(FRET_AMOUNT_COLUMN_NAME);
                picks = rs.getString(PICKS_COLUMN_NAME);
                beltButton = rs.getBoolean(BELT_BUTTON_COLUMN_NAME);
                result = new Product(id, thumbnail, name, price, stock, type, typeName, timesOrdered, maker, body, fret, scale, fretAmount, picks, beltButton);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.error(String.format("Error while retrieving product by id %d", id), e);
            throw new DAOException(String.format("Error while retrieving product by id %d", id), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }

        if (result == null) {
            logger.error(String.format("No product for id %d", id));
            throw new DAOException(String.format("No product for id %d", id));
        } else {
            return result;
        }
    }
}
