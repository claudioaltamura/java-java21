package de.claudioaltamura.java.java21;

import org.junit.jupiter.api.Test;

import static java.lang.StringTemplate.STR;
import static org.assertj.core.api.Assertions.assertThat;

class TemplateTest {

    @Test
    void helloWorld() {
        var name = "Java 21";
        String info = STR."Hello World, \{name} !";
        assertThat(info).isEqualTo("Hello World, Java 21 !");
    }

}