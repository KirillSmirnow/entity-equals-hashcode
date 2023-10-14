package eeh.objects_instanceof.sequential;

import eeh.Identifiable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.util.Objects;

@MappedSuperclass
@Getter
class Base implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public final String toString() {
        return super.toString();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Base base)) return false;
        return Objects.equals(id, base.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}
