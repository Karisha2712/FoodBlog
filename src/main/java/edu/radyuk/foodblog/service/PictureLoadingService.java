package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.impl.PictureLoadingServiceImpl;

import javax.servlet.http.Part;
import java.util.List;

/**
 * The interface Picture loading service.
 */
public interface PictureLoadingService {
    /**
     * Save picture string.
     *
     * @param id           the id
     * @param category     the category
     * @param pictureParts the picture parts
     * @return the string
     * @throws ServiceException the service exception
     */
    String savePicture(long id,
                       PictureLoadingServiceImpl.PictureCategory category, List<Part> pictureParts) throws ServiceException;
}
