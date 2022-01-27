package by.epamtc.ivangavrilovich.shop.tags;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class Pagination extends TagSupport {
    private final static Logger logger = LogManager.getLogger();
    private String path;
    private int currentPage;
    private int numberOfPages;

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();

            //START OF PAGINATION
            out.write("<div class=\"container\">");
            out.write("<ul class=\"pagination\"");
            if (currentPage != 1) {
                out.write("<li class=\"page-item\"><a class=\"page-link\" " +
                        "href=\"" +
                        pageContext.getRequest().getServletContext().getContextPath() + path + (currentPage - 1) +
                        "\" aria-label=\"Previous\" style=\"color: black;\"><span aria-hidden=\"true\"><</span></a></li>");
            }
            for (int i = 1; i <= numberOfPages; i++) {
                out.write("<li class=\"page-item\"><a class=\"page-link\" " +
                        "href=\"" +
                        pageContext.getRequest().getServletContext().getContextPath() + path + i +
                        "\"");
                if (i == currentPage) {
                    out.write("style=\"color: white; background-color: #208f8f;\"");
                } else {
                    out.write("style=\"color: black;\"");
                }
                out.write(">" +
                        i +
                        "</a></li>");
            }
            if (currentPage != numberOfPages) {
                out.write("<li class=\"page-item\"><a class=\"page-link\" " +
                        "href=\"" +
                        pageContext.getRequest().getServletContext().getContextPath() + path + (currentPage + 1) +
                        "\" aria-label=\"Previous\" style=\"color: black;\"><span aria-hidden=\"true\">></span></a></li>");
            }
            out.write("</ul>");
            out.write("</div>");
            //END OF PAGINATION
        } catch (IOException e) {
            logger.error("Error while processing custom Pagination tag", e);
            throw new JspException("Error while processing custom Pagination tag", e);
        }
        return SKIP_BODY;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
