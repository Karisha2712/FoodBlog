package edu.radyuk.foodblog.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static edu.radyuk.foodblog.controller.command.RequestParameter.PICTURE_PATH;

public class PictureServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final String PICTURE_PROPERTIES_FILE = "property/picture.properties";
    private static final String PICTURE_BASE_DIRECTORY_PROPERTY = "picture.base_directory";
    private String baseDirectory;

    @Override
    public void init() throws ServletException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PICTURE_PROPERTIES_FILE)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            baseDirectory = properties.getProperty(PICTURE_BASE_DIRECTORY_PROPERTY);
        } catch (IOException e) {
            logger.log(Level.ERROR, e);
        } catch (NullPointerException e) {
            logger.log(Level.ERROR, "File {} does not exist", PICTURE_PROPERTIES_FILE, e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String picturePath = req.getParameter(PICTURE_PATH);
        if (picturePath == null) {
            logger.log(Level.ERROR, "There is no such parameter {} in request", PICTURE_PATH);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String absolutePath = baseDirectory + picturePath;
        try (InputStream inputStream = new FileInputStream(absolutePath)) {
            ServletOutputStream outputStream = resp.getOutputStream();
            inputStream.transferTo(outputStream);
        } catch (IOException e) {
            logger.log(Level.ERROR, e);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

