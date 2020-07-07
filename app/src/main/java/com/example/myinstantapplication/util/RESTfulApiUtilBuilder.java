package com.example.myinstantapplication.util;

public class RESTfulApiUtilBuilder {
    private String baseUri;
    private String requestMethod;
    private String parameter;

    public RESTfulApiUtilBuilder setBaseUri(String baseUri) {
        this.baseUri = baseUri;
        return this;
    }

    public RESTfulApiUtilBuilder setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    public RESTfulApiUtilBuilder setParameter(String parameter) {
        this.parameter = parameter;
        return this;
    }

    public RESTfulApiUtil createRESTfulApiUtil() {
        return new RESTfulApiUtil(baseUri, requestMethod, parameter);
    }

}