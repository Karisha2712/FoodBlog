package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.service.impl.UserServiceImpl;

public class ServiceFactory {
    private final UserService userService = new UserServiceImpl();

    private ServiceFactory() {
    }

    public UserService getUserService() {
        return userService;
    }

    public static ServiceFactory getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static final ServiceFactory instance = new ServiceFactory();
    }
}
