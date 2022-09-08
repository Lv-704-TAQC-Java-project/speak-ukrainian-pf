package com.ita.edu.speakua.api.clients;

import com.ita.edu.speakua.utils.PropertyProvider;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public abstract class BaseClient {
    public final String baseUrl;
    public final ContentType contentType;
    protected static final PropertyProvider property =  new PropertyProvider();

    public BaseClient() {
        this.baseUrl = property.getAPIBaseUrl();
        this.contentType = ContentType.JSON;
    }
    public BaseClient(ContentType contentType, String url) {
        this.baseUrl = url;
        this.contentType = contentType;
    }

    public RequestSpecification prepareRequest() {
        return given()
                .baseUri(baseUrl)
                .contentType(contentType)
                .accept(contentType);
    }

}
