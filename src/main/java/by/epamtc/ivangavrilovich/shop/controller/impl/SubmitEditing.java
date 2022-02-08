package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.exceptions.InvalidInputsException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.interfaces.ProductService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

public class SubmitEditing implements Command {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductService service = ServiceProvider.getInstance().getProductServiceImpl();
        if (!ServletFileUpload.isMultipartContent(request)) {
            logger.error("Form should be multipart/form-data");
            response.sendRedirect(request.getContextPath() + "/pages/serverException.jsp");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String maker = request.getParameter("maker");
            String body = request.getParameter("body");
            String fret = request.getParameter("fret");
            String scale = request.getParameter("scale");
            String fretAmount = request.getParameter("fretAmount");
            String picks = request.getParameter("picks");
            boolean beltButton = request.getParameterValues("beltButton") != null;
            String price = request.getParameter("price");
            String thumbnail = request.getParameter("oldThumbnail");
            Part filePart = request.getPart("file");

            if (!"".equals(filePart.getSubmittedFileName())) {
                //TODO fix

                File uploads = new File(
                        System.getenv("USERPROFILE") +
                        "\\" +
                        request.getServletContext().getInitParameter("uploadLocation")
                );
                System.out.println(uploads.mkdirs());
                File file = File.createTempFile("thumbnail-", ".jpg", uploads);
                thumbnail = file.getName();
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }

            try {
                service.updateProduct(id, thumbnail, name, maker, body, fret, scale, fretAmount, picks, beltButton, price);
                response.sendRedirect(request.getContextPath() + "/pages/controller?command=VIEW_SINGLE_PRODUCT&id=" + id);
            } catch (InvalidInputsException e) {
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher("/pages/controller?command=VIEW_SINGLE_PRODUCT&id=" + id).forward(request, response);
            } catch (ServiceException e) {
                logger.error("Error while updating product", e);
                response.sendRedirect(request.getContextPath() + "/pages/serverException.jsp");
            }
        }
    }

    @Override
    public List<Integer> getAppropriateRoles() {
        return Arrays.asList(3);
    }
}
