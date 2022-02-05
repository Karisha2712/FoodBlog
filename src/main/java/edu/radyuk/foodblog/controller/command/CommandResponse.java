package edu.radyuk.foodblog.controller.command;

/**
 * The type Command response.
 */
public class CommandResponse {
    private String resultPage;
    private RoutingType routingType;

    /**
     * Instantiates a new Command response.
     */
    public CommandResponse() {
    }

    /**
     * Instantiates a new Command response.
     *
     * @param resultPage  the result page
     * @param routingType the routing type
     */
    public CommandResponse(String resultPage, RoutingType routingType) {
        this.resultPage = resultPage;
        this.routingType = routingType;
    }

    /**
     * Gets result page.
     *
     * @return the result page
     */
    public String getResultPage() {
        return resultPage;
    }

    /**
     * Gets routing type.
     *
     * @return the routing type
     */
    public RoutingType getRoutingType() {
        return routingType;
    }

    /**
     * Sets routing type.
     *
     * @param routingType the routing type
     */
    public void setRoutingType(RoutingType routingType) {
        this.routingType = routingType;
    }
}

