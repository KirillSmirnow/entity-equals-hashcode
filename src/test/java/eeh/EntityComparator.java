package eeh;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EntityComparator {

    public static void assertEqual(Object entity1, Object entity2) {
        assertThat(entity1).isEqualTo(entity2);
        assertThat(entity1).hasSameHashCodeAs(entity2);
        assertThat(new HashSet<>(List.of(entity1, entity2))).hasSize(1);
    }

    public static void assertNotEqual(Object entity1, Object entity2) {
        assertThat(entity1).isNotSameAs(entity2);
        assertThat(entity1).isNotEqualTo(entity2);
        assertThat(new HashSet<>(List.of(entity1, entity2))).hasSize(2);
    }
}
