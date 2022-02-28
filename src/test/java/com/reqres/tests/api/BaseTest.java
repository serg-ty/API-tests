package com.reqres.tests.api;

import com.demo.conf.Config;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

abstract class BaseTest {

    protected final RequestSpecification requestSpec = given()
            .baseUri(Config.getProperty("base.uri"))
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON);
}
