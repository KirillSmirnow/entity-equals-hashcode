package eeh.lombokfinal.sequential;

import eeh.EntityService;
import eeh.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
class LombokFinalSequentialEntityService implements EntityService<LombokFinalSequential, Long> {

    private final LombokFinalSequentialRepo repository;
    private final LombokFinalSequential2Repo repository2;

    @Override
    public LombokFinalSequential createNotPersisted() {
        return new LombokFinalSequential();
    }

    @Override
    public Identifiable<Long> persistEntityOfAnotherTypeWithId(Long id) {
        for (var i = 1; i <= id; i++) {
            var entity = repository2.save(new LombokFinalSequential2());
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        throw new IllegalStateException("Failed to obtain entity with ID=" + id);
    }
}
