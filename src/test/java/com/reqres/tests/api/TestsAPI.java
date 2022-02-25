package com.reqres.tests.api;

import com.demo.model.User;
import com.reqres.tests.steps.Steps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestsAPI extends BaseTest {

    @Test
    @DisplayName("Проверка почты пользователя на первой странице ответа")
    void checkUserMailOnPage1() {

        List<User> users = Steps.getUsersByPage(requestSpec, 1);

        String userMail = users.stream()
                .filter(u -> u.getFirstName().equals("George") && u.getLastName().equals("Bluth"))
                .findAny().orElseThrow().getEmail();

        Assertions.assertEquals("george.bluth@reqres.in", userMail);
    }

    @Test
    @DisplayName("Проверка почты пользователя на второй странице ответа")
    void checkUserMailOnPage2() {

        List<User> users = Steps.getUsersByPage(requestSpec, 2);

        String userMail = users.stream()
                .filter(u -> u.getFirstName().equals("Michael") && u.getLastName().equals("Lawson"))
                .findAny().orElseThrow().getEmail();

        Assertions.assertEquals("michael.lawson@reqres.in", userMail);
    }
}
