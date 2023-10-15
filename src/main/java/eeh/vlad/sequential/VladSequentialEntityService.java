package eeh.vlad.sequential;

import eeh.EntityService;
import eeh.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
class VladSequentialEntityService implements EntityService<VladSequential, Long> {

    private final VladSequentialRepo repository;
    private final VladSequential2Repo repository2;

    @Override
    public VladSequential createNotPersisted() {
        return new VladSequential();
    }

    @Override
    public Identifiable<Long> persistEntityOfAnotherTypeWithId(Long id) {
        for (var i = 1; i <= id; i++) {
            var entity = repository2.save(new VladSequential2());
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        throw new IllegalStateException("Failed to obtain entity with ID=" + id);
    }
}
