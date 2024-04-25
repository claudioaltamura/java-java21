package de.claudioaltamura.java.java21;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class SequencedMapTest {

    @Test
    void putFirst() {
        var map = Map.of("1", 1, "2", 2);
        var linkedHashMap = new LinkedHashMap<>(map);

        linkedHashMap.putFirst("0",0);

        assertThat(linkedHashMap.firstEntry().getKey()).isEqualTo("0");
    }

}
