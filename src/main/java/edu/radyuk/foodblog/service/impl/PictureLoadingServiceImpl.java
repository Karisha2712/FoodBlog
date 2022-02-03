package edu.radyuk.foodblog.service.impl;

import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.PictureLoadingService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class PictureLoadingServiceImpl implements PictureLoadingService {
    private static final Logger logger = LogManager.getLogger();
    private static final String PICTURE_PROPERTIES = "property/picture.properties";
    private static final String AVATAR_PATH_PROPERTY = "picture.avatar";
    private static final String POST_PATH_PROPERTY = "picture.post";
    private static final String BASE_DIRECTORY_PROPERTY = "picture.base_directory";
    private static final String DEFAULT_BASE_DIRECTORY = "D:/blog_pictures/";
    private static final String DEFAULT_AVATAR_PATH_PROPERTY = "avatars/";
    private static final String DEFAULT_POST_PATH_DIRECTORY = "posts/";
    private static final String EXTENSION = ".png";
    private String baseDirectory;
    private String avatarPath;
    private String postPath;

    public PictureLoadingServiceImpl() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            Properties properties = new Properties();
            properties.load(classLoader.getResourceAsStream(PICTURE_PROPERTIES));
            baseDirectory = properties.getProperty(BASE_DIRECTORY_PROPERTY);
            avatarPath = properties.getProperty(AVATAR_PATH_PROPERTY);
            postPath = properties.getProperty(POST_PATH_PROPERTY);
            if (baseDirectory == null || avatarPath == null || postPath == null) {
                baseDirectory = DEFAULT_BASE_DIRECTORY;
                avatarPath = DEFAULT_AVATAR_PATH_PROPERTY;
                postPath = DEFAULT_POST_PATH_DIRECTORY;
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while reading property file {} ", PICTURE_PROPERTIES, e);
            baseDirectory = DEFAULT_BASE_DIRECTORY;
            avatarPath = DEFAULT_AVATAR_PATH_PROPERTY;
            postPath = DEFAULT_POST_PATH_DIRECTORY;
        }
    }

    public enum PictureCategory {
        POST,
        AVATAR
    }

    @Override
    public String savePicture(long id, PictureCategory pictureCategory, List<Part> pictureParts)
            throws ServiceException {
        String path = pictureCategory == PictureCategory.POST ? postPath : avatarPath;
        String relativePicturePath = path + id + EXTENSION;
        String absolutePath = baseDirectory + relativePicturePath;
        try (FileOutputStream fileOutputStream = new FileOutputStream(absolutePath)) {
            for (Part part : pictureParts) {
                part.getInputStream().transferTo(fileOutputStream);
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return relativePicturePath;
    }
}
