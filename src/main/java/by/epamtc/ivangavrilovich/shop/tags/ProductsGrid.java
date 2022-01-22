package by.epamtc.ivangavrilovich.shop.tags;

import by.epamtc.ivangavrilovich.shop.bean.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ProductsGrid extends TagSupport {
    private final static String PATH_PAGINATION = "/pages/controller?command=VIEW_ALL_PRODUCTS&page=";
    //TODO fix
    private final static String PATH_PRODUCT = "/pages/controller?command=VIEW_ALL_PRODUCTS&id=";
    private final static Logger logger = LogManager.getLogger();
    private int currentPage;
    private int numberOfPages;
    private List<Product> products;

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();

            //START OF PAGINATION
            out.write("<ul class=\"pagination\">");
            if (currentPage != 1) {
                out.write("<li class=\"page-item\"><a class=\"page-link\" " +
                        "href=\"" +
                        pageContext.getRequest().getServletContext().getContextPath() + PATH_PAGINATION + (currentPage - 1) +
                        "\" aria-label=\"Previous\"><span aria-hidden=\"true\"><</span></a></li>");
            }
            for (int i = 1; i <= numberOfPages; i++) {
                out.write("<li class=\"page-item\"><a class=\"page-link\" " +
                        "href=\"" +
                        pageContext.getRequest().getServletContext().getContextPath() + PATH_PAGINATION + i +
                        "\">" +
                        i +
                        "</a></li>");
            }
            if (currentPage != numberOfPages) {
                out.write("<li class=\"page-item\"><a class=\"page-link\" " +
                        "href=\"" +
                        pageContext.getRequest().getServletContext().getContextPath() + PATH_PAGINATION + (currentPage + 1) +
                        "\" aria-label=\"Previous\"><span aria-hidden=\"true\">></span></a></li>");
            }
            out.write("</ul>");
            //END OF PAGINATION

            //START OF GRID ITEMS
            out.write("<div class=\"container\" style=\"margin-bottom: 120px;\" >");
                out.write("<div class=\"row product-list dev\">");
                    for (Product product : products) {
                        String link = pageContext.getRequest().getServletContext().getContextPath() + PATH_PRODUCT + product.getProductId();
                        out.write("<div class=\"col-sm-6 col-md-4 product-item\">");
                            out.write("<div class=\"product-container\">");
                                out.write("<div class=\"row\">");
                                    out.write("<div class=\"col-md-12\"><a class=\"product-image\" " +
                                            "href=\"" +
                                            link +
                                            "\"><img " +
                                            "src=\"" +
                                            product.getThumbnail() +
                                            "\"/></a></div>");
                                    out.write("<div class=\"row\">");
                                        out.write("<div class=\"col-8\">");
                                            out.write("<h2><a href=\"" +
                                                    link +
                                                    "\">" +
                                                    product.getName() +
                                                    "</a></h2>");
                                        out.write("</div>");
                                    out.write("</div>");
                                    out.write("<div class=\"row\">");
                                        out.write("<div class=\"col-12\">");
                                            //TODO desc or replace with smth
                                            out.write("<p class=\"product-description\">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ornare sem sed nisl dignissim, facilisis dapibus lacus vulputate. Sed lacinia lacinia magna. </p>");
                                            out.write("<div class=\"row\">");
                                                out.write("<div class=\"col-6\"><button class=\"btn btn-light\" type=\"button\">${requestScope.page}</button></div>");
                                                out.write("<div class=\"col-6\">");
                                                    out.write("<p class=\"product-price\">" +
                                                            product.getPrice() +
                                                            "$" +
                                                            "</p>");
                                                out.write("</div>");
                                            out.write("</div>");
                                        out.write("</div>");
                                    out.write("</div>");
                                out.write("</div>");
                            out.write("</div>");
                        out.write("</div>");
                    }
                out.write("</div>");
            out.write("</div>");
            //END OF GRID ITEMS

        } catch (IOException e) {
            logger.error("Error while processing custom ProductsGrid tag", e);
            throw new JspException("Error while processing custom ProductsGrid tag", e);
        }
        return SKIP_BODY;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
