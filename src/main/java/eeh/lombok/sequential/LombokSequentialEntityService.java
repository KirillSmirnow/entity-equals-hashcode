package eeh.lombok.sequential;

import eeh.EntityService;
import eeh.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
class LombokSequentialEntityService implements EntityService<LombokSequential, Long> {

    private final LombokSequentialRepo repository;
    private final LombokSequential2Repo repository2;

    @Override
    public LombokSequential createNotPersisted() {
        return new LombokSequential();
    }

    @Override
    public Identifiable<Long> persistEntityOfAnotherTypeWithId(Long id) {
        for (var i = 1; i <= id; i++) {
            var entity = repository2.save(new LombokSequential2());
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        throw new IllegalStateException("Failed to obtain entity with ID=" + id);
    }
}
