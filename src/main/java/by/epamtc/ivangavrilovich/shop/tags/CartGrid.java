package by.epamtc.ivangavrilovich.shop.tags;

import by.epamtc.ivangavrilovich.shop.bean.CartedProduct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class CartGrid extends TagSupport {
    private final static Logger logger = LogManager.getLogger();
    private final static String PATH_PRODUCT = "/pages/controller?command=VIEW_SINGLE_PRODUCT&id=";
    private final static String PATH_DECREASE = "/pages/controller?command=DECREASE_QUANTITY&id=";
    private final static String PATH_INCREASE = "/pages/controller?command=INCREASE_QUANTITY&id=";
    private final static String PATH_REMOVE = "/pages/controller?command=REMOVE_FROM_CART&id=";
    private List<CartedProduct> products;

    @Override
    public int doStartTag() throws JspException {
        String context = pageContext.getRequest().getServletContext().getContextPath();
        try{
            JspWriter out = pageContext.getOut();

            out.write("<div class=\"row\">");
                out.write("<div class=\"col-lg-12 p-5 bg-white rounded shadow-sm mb-5\">");
                    out.write("<div class=\"table-responsive\">");
                        out.write("<table class=\"table\">");
                            out.write("<thead><tr><th scope=\"col\" class=\"border-0 bg-light\"><div class=\"p-2 px-3 text-uppercase\">");
                            out.write((String)pageContext.getSession().getAttribute("cartProductLabel"));
                            out.write("</div></th><th scope=\"col\" class=\"border-0 bg-light\"><div class=\"py-2 text-uppercase\">");
                            out.write((String)pageContext.getSession().getAttribute("cartPriceLabel"));
                            out.write("</div></th><th scope=\"col\" class=\"border-0 bg-light\"><div class=\"py-2 text-uppercase\">");
                            out.write((String)pageContext.getSession().getAttribute("cartQuantityLabel"));
                            out.write("</div></th><th scope=\"col\" class=\"border-0 bg-light\"><div class=\"py-2 text-uppercase\">");
                            out.write((String)pageContext.getSession().getAttribute("cartRemoveLabel"));
                            out.write("</div></th></thead>");
                            out.write("<tbody>");
                                for (CartedProduct product : products) {
                                    out.write("<tr><th scope=\"row\" class=\"border-0\"><div class=\"p-2\"><img src=\"");
                                    out.write(product.getProduct().getThumbnail());
                                    out.write("\" alt width=\"70\" class=\"img-fluid rounded shadow-sm\" /><div class=\"ml-3 d-inline-block align-middle\"><h5 class=\"mb-0\"><a href=\"");
                                    out.write(context + PATH_PRODUCT + product.getProduct().getProductId());
                                    out.write("\" class=\"text-dark d-inline-block align-middle\">");
                                    out.write(product.getProduct().getName());
                                    out.write("</a></h5></div></div></th><td class=\"border-0 align-middle\"><strong>$");
                                    out.write(String.format("%.2f", product.getProduct().getPrice()));
                                    out.write("</strong></td><td class=\"border-0 align-middle\">");
                                    if (product.getQuantity() > 1) {
                                        out.write("<a href=\"");
                                        out.write(context + PATH_DECREASE + product.getProduct().getProductId());
                                        out.write("\" class=\"text-dark\"><i class=\"fa fa-1px fa-minus\"></i></a>");
                                    }
                                    out.write("<strong style=\"margin-right: 2px; margin-left: 2px;\">");
                                    out.write(String.valueOf(product.getQuantity()));
                                    out.write("</strong>");
                                    if (product.getQuantity() < product.getProduct().getStock()) {
                                        out.write("<a href=\"");
                                        out.write(context + PATH_INCREASE + product.getProduct().getProductId());
                                        out.write("\" class=\"text-dark\"><i class=\"fa fa-1px fa-plus\"></i></a>");
                                    }
                                    out.write("</td><td class=\"border-0 align-middle\"><a href=\"");
                                    out.write(context + PATH_REMOVE + product.getProduct().getProductId());
                                    out.write("\" class=\"text-dark\"><i class=\"fa fa-trash\"></i></a></td></tr>");
                                }
                            out.write("</tbody>");
                        out.write("</table>");
                    out.write("</div>");
                out.write("</div>");
            out.write("</div>");
        } catch (IOException e) {
            logger.error("Error while processing custom CartGrid tag", e);
            throw new JspException("Error while processing custom CartGrid tag", e);
        }
        return SKIP_BODY;
    }

    public void setProducts(List<CartedProduct> products) {
        this.products = products;
    }
}

