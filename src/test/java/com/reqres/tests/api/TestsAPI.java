package com.reqres.tests.api;

import com.demo.model.User;
import com.reqres.tests.steps.Steps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestsAPI extends BaseTest {

    @Test
    @DisplayName("Проверка почты пользователя по полному совпадению")
    void checkUserMailByCompleteMatch() {

        List<User> users = Steps.findUserByAllPages(requestSpec,
                u -> u.getFirstName().equals("George") && u.getLastName().equals("Bluth"));
        String userMail = null;
        if (!users.isEmpty())
            userMail = users.get(0).getEmail();

        Assertions.assertNotEquals(0, users.size(), "Не нашли пользователя с заданным фильтром");
        Assertions.assertEquals("george.bluth@reqres.in", userMail,
                "Почта пользователя не совпадает с ожидаемым значением");
    }

    @Test
    @DisplayName("Проверка почты пользователя по частичному совпадению")
    void checkUserMailByPartialMatch() {

        List<User> users = Steps.findUserByAllPages(requestSpec,
                u -> u.getFirstName().equals("Michael") && u.getLastName().equals("Lawson"));
        String userMail = null;
        if (!users.isEmpty())
            userMail = users.get(0).getEmail();

        Assertions.assertNotEquals(0, users.size(), "Не нашли пользователя с заданным фильтром");
        Assertions.assertTrue(userMail.contains("michael.lawson@reqres"),
                "Почта пользователя не совпадает с ожидаемым значением");
    }
}
