package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.connection.ConnectionPool;
import edu.radyuk.foodblog.entity.dto.RecipePostDto;
import edu.radyuk.foodblog.exception.ServiceException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class RecipePostServiceImplTest {

    @BeforeAll
    static void createConnections() {
        ConnectionPool.getInstance();
    }

    @AfterAll
    static void closeConnections() {
        ConnectionPool.getInstance().closeConnections();
    }

    @Test
    void ifRetrieveRecipePostByIdReturnsEmptyOptional() throws ServiceException {
        RecipePostService service = ServiceProvider.getInstance().getRecipePostService();
        long invalidPostId = -10;
        Optional<RecipePostDto> actual = service.retrieveRecipePostById(invalidPostId);
        Assertions.assertTrue(actual.isEmpty());
    }
}
