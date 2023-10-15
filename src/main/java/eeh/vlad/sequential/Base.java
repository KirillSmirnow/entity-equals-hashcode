package eeh.vlad.sequential;

import eeh.Identifiable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

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
        if (!(o instanceof Base other)) return false;
        return getId() != null && getId().equals(other.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
