package edu.radyuk.foodblog.controller.command.impl.blogger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Picture parts loader.
 */
public final class PicturePartsLoader {
    private static final Logger logger = LogManager.getLogger();

    private PicturePartsLoader() {
    }

    /**
     * Load picture parts list.
     *
     * @param request       the request
     * @param parameterName the parameter name
     * @return the list
     */
    public static List<Part> loadPictureParts(HttpServletRequest request, String parameterName) {
        List<Part> pictureParts;
        try {
            pictureParts = request.getParts().stream()
                    .filter(part -> part.getName().equals(parameterName) && part.getSize() > 0)
                    .collect(Collectors.toList());
        } catch (IOException | ServletException e) {
            logger.log(Level.ERROR, e);
            return List.of();
        }
        return pictureParts;
    }

}
