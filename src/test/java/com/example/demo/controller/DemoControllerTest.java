package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Skeleton template for a controller test using MockMvc.
 *
 * You can use annotations from JUnit 5 such as @ParameterizedTest, @ValueSauce,
 * @CsvSource and @MethodSource for your test data.
 *
 * Example usage of mockMvc for a GET request
 * mockMvc.perform(get("/path-to-your-endpoint").param("your-query-param", param-value))
 *                 .andExpect(status().whateverStatusCodeYouExpect())
 *                 .andExpect(content().string("string-you-expect-in-response")).
 *                 .andExpect(jsonPath("$.jsonField").value("json-value-you-expect"));
 */
@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @ParameterizedTest(name = "Input: \"{0}\" → Expected: \"{1}\"")
    @CsvSource({
            "eloquent, loquen",
            "country, ountr",
            "person, erso",
            "xyz, y",
            "'123%qwerty+', '23%qwerty'"
    })
    void testValidStrings(String input, String expected) throws Exception {
        mockMvc.perform(get("/remove").param("original", input))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    void testExactlyTwo() throws Exception {
        mockMvc.perform(get("/remove").param("original", "ab"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @ParameterizedTest(name = "Invalid input: \"{0}\"")
    @ValueSource(strings = {"", "a"})
    void testInvalidStrings(String input) throws Exception {
        mockMvc.perform(get("/remove").param("original", input))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testMissingParameter() throws Exception {
        mockMvc.perform(get("/remove"))
                .andExpect(status().isBadRequest());
    }


}
