package edu.radyuk.foodblog.connection;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConnectionPoolTest {

    @Test
    void ifGetInstanceReturnsSingleObject() {
        ConnectionPool pool1 = ConnectionPool.getInstance();
        ConnectionPool pool2 = ConnectionPool.getInstance();
        Assertions.assertEquals(pool1, pool2);
    }

    @AfterAll
    static void closeConnections() {
        ConnectionPool.getInstance().closeConnections();
    }

}
