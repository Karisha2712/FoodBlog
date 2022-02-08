package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.connection.ConnectionPool;
import edu.radyuk.foodblog.exception.ServiceException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BloggerInfoServiceImplTest {

    @BeforeAll
    static void createConnections() {
        ConnectionPool.getInstance();
    }

    @AfterAll
    static void closeConnections() {
        ConnectionPool.getInstance().closeConnections();
    }

    @Test
    void ifRetrievePicturePathByUserIdReturnsDefaultPath() throws ServiceException {
        BloggerInfoService service = ServiceProvider.getInstance().getBloggerInfoService();
        long invalidUserId = -10;
        String expected = "default_avatar.png";
        String actual = service.retrievePicturePathByUserId(invalidUserId);
        Assertions.assertEquals(expected, actual);
    }
}
