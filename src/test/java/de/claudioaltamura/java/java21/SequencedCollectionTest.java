package de.claudioaltamura.java.java21;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SequencedCollection;

import static org.assertj.core.api.Assertions.assertThat;

class SequencedCollectionTest {

    private final List<Integer> tmpList = Arrays.asList(1, 2, 4, 8, 16);
    @Test
    void addFirst() {
        SequencedCollection<Integer> integers = new ArrayList<>(tmpList);

        var newFirstElement = 0;
        integers.addFirst(newFirstElement);

        assertThat(integers.getFirst()).isEqualTo(newFirstElement);
    }

    @Test
    void addLast() {
        SequencedCollection<Integer> integers = new ArrayList<>(tmpList);

        var newLastElement = 32;
        integers.addLast(newLastElement);

        assertThat(integers.getLast()).isEqualTo(newLastElement);
    }

    @Test
    void reversed() {
        SequencedCollection<Integer> integers = new ArrayList<>(tmpList);

        var reversed = integers.reversed();

        assertThat(reversed).containsExactly(16,8,4,2,1);
    }
}
