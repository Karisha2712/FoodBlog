package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.exception.ServiceException;
import edu.radyuk.foodblog.service.impl.PictureLoadingServiceImpl;

import javax.servlet.http.Part;
import java.util.List;

public interface PictureLoadingService {
    String savePicture(long id,
                       PictureLoadingServiceImpl.PictureCategory category, List<Part> pictureParts) throws ServiceException;
}
