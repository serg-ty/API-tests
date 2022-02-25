package com.reqres.tests.steps;

import com.demo.model.User;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class Steps {

    @Step("Получение списка пользователей на страницу")
    public static List<User> getUsersByPage(RequestSpecification specification, int pageNumber) {
        return specification.
                when()
                    .param("page", pageNumber)
                    .get("/api/users")
                .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .jsonPath().getList("data", User.class);
    }
}
