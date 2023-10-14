package eeh.lombok.sequential;

import eeh.Identifiable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@MappedSuperclass
@Getter
@EqualsAndHashCode(of = "id")
class Base implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public final String toString() {
        return super.toString();
    }
}
