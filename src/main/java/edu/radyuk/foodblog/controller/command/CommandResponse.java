package edu.radyuk.foodblog.controller.command;

public class CommandResponse {
    private String resultPage;
    private RoutingType routingType;

    public CommandResponse() {
    }

    public CommandResponse(String resultPage, RoutingType routingType) {
        this.resultPage = resultPage;
        this.routingType = routingType;
    }

    public String getResultPage() {
        return resultPage;
    }

    public void setResultPage(String resultPage) {
        this.resultPage = resultPage;
    }

    public RoutingType getRoutingType() {
        return routingType;
    }

    public void setRoutingType(RoutingType routingType) {
        this.routingType = routingType;
    }
}

