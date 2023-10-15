package eeh.thorben.sequential;

import eeh.EntityService;
import eeh.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
class ThorbenSequentialEntityService implements EntityService<ThorbenSequential, Long> {

    private final ThorbenSequentialRepo repository;
    private final ThorbenSequential2Repo repository2;

    @Override
    public ThorbenSequential createNotPersisted() {
        return new ThorbenSequential();
    }

    @Override
    public Identifiable<Long> persistEntityOfAnotherTypeWithId(Long id) {
        for (var i = 1; i <= id; i++) {
            var entity = repository2.save(new ThorbenSequential2());
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        throw new IllegalStateException("Failed to obtain entity with ID=" + id);
    }
}
