package eeh.default_.sequential;

import eeh.EntityService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
class DefaultSequentialEntityService implements EntityService<DefaultSequential, Long> {

    private final DefaultSequentialRepo repository;
    private final DefaultSequential2Repo repository2;

    @Override
    public DefaultSequential createNotPersisted() {
        return new DefaultSequential();
    }

    @Override
    public DefaultSequential2 persistEntityOfAnotherTypeWithId(Long id) {
        for (var i = 1; i <= id; i++) {
            var entity = repository2.save(new DefaultSequential2());
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        throw new IllegalStateException("Failed to obtain entity with ID=" + id);
    }
}
