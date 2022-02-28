package com.reqres.tests.steps;

import com.demo.model.User;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class Steps {

    @Step("Получение списка пользователей на страницу")
    public static List<User> getUsersByPage(RequestSpecification specification, int pageNumber) {
        return given(specification)
                    .queryParam("page", pageNumber)
                .when()
                    .get("/api/users")
                .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .jsonPath().getList("data", User.class);
    }

    public static int getUsersPageCount(RequestSpecification specification) {
        return given(specification)
                .when()
                    .get("/api/users")
                .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .jsonPath().getInt("total_pages");
    }

    @Step("Поиск пользователя на странице")
    public static List<User> findUserByPage(RequestSpecification specification, int pageNumber, Predicate<User> condition) {
        List<User> users = getUsersByPage(specification, pageNumber);

        return users.stream().filter(condition).collect(Collectors.toList());
    }

    @Step("Поиск пользователя по всем страницам")
    public static List<User> findUserByAllPages(RequestSpecification specification, Predicate<User> condition) {
        List<User> users = new ArrayList<>();

        for (int i = 1; i <= getUsersPageCount(specification); i++) {
            users.addAll(Steps.findUserByPage(specification, i, condition));
        }

        return users;
    }
}
