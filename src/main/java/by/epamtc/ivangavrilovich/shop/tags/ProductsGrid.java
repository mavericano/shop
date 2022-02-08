package by.epamtc.ivangavrilovich.shop.tags;

import by.epamtc.ivangavrilovich.shop.bean.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class ProductsGrid extends TagSupport {
    private final static String PATH_PRODUCT = "/pages/controller?command=VIEW_SINGLE_PRODUCT&id=";
    private final static String PATH_IMAGES = "/img/";
    //TODO fix, Tomcat9 issue
    private final static Logger logger = LogManager.getLogger();
    private List<Product> products;

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();

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
                                            PATH_IMAGES + product.getThumbnail() +
                                            "\"/></a></div>");
                                out.write("</div>");

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
                                        out.write("<div class=\"row\">");
                                            out.write("<div class=\"col-6\"><a style=\"background: #208F8F; color: white;\" role=\"button\" class=\"btn btn-light action-button\" href=\"" +
                                                    link + "\">" +
                                                    pageContext.getSession().getAttribute("moreButton") +
                                                    "</a></div>");
                                            out.write("<div class=\"col-6\">");
                                                out.write("<p class=\"product-price\">" +
                                                        product.getPrice() +
                                                        "<i class=\"fa fa-dollar\"></i></p>");
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

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
