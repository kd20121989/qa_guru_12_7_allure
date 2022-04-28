package com.gmail.kd2012.allure;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ParametrisedTest {

    @Test
    @DisplayName("My favourite test")
    public void testAnnotated() {
        Allure.parameter("Region", "Moskovskaya oblast");
        Allure.parameter("City", "Moscow");

    }

}
