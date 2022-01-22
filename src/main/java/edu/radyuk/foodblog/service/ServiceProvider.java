package edu.radyuk.foodblog.service;

import edu.radyuk.foodblog.service.impl.UserServiceImpl;

public class ServiceProvider {
    private final UserService userService = new UserServiceImpl();

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return Holder.instance;
    }

    public UserService getUserService() {
        return userService;
    }

    private static class Holder {
        private static final ServiceProvider instance = new ServiceProvider();
    }
}
