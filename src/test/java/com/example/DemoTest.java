package com.example;

import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoTest {
    private static Demo demo;

    @BeforeAll
    public static void setUp() throws Exception {
        demo = new Demo();
    }

    @Test
    public void test01() {
        String result = demo.run();
        assertThat(result).isEqualTo("ABC");
    }
}