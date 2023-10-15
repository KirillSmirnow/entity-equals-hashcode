package eeh.stackoverflow.sequential;

import eeh.EntityService;
import eeh.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
class StackOverflowSequentialEntityService implements EntityService<StackOverflowSequential, Long> {

    private final StackOverflowSequentialRepo repository;
    private final StackOverflowSequential2Repo repository2;

    @Override
    public StackOverflowSequential createNotPersisted() {
        return new StackOverflowSequential();
    }

    @Override
    public Identifiable<Long> persistEntityOfAnotherTypeWithId(Long id) {
        for (var i = 1; i <= id; i++) {
            var entity = repository2.save(new StackOverflowSequential2());
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        throw new IllegalStateException("Failed to obtain entity with ID=" + id);
    }
}
