package de.claudioaltamura.java.java21;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class SequencedMapTest {

    @Test
    void findFirst() {
        var of = Map.of("1", 1, "2", 2);
        var linkedHashMap = new LinkedHashMap<>(of);

        assertThat(linkedHashMap.firstEntry().getKey()).isEqualTo("1");
    }

    @Test
    void putFirst() {
        var of = Map.of("1", 1, "2", 2);
        var linkedHashMap = new LinkedHashMap<>(of);

        linkedHashMap.putFirst("0",0);

        assertThat(linkedHashMap.firstEntry().getKey()).isEqualTo("0");
    }

}
