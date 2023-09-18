package de.claudioaltamura.java.java21;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class RecordPatternTest {

    @Test
    void instanceOfTest() {
        Object object = new Car("BMW", 120);

        if(object instanceof Car(String make, int speedInKmh)) {
            System.out.printf("Car %s, %d", make, speedInKmh);
            assertThat(true).isTrue();
        } else if (object instanceof String s) {
            fail("not a string");
        }
    }

    @Test
    void switchTest() {
        Object object = new Car("BMW", 120);

        switch (object) {
            case Car(String make, int speedInKmh) ->  { System.out.printf("Car %s, %d%n", make, speedInKmh); assertThat(true).isTrue();  }
            case String s -> fail("not a string");
            default -> System.out.printf("default");
        }
    }


}
