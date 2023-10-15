package eeh.solution0.sequential;

import eeh.EntityService;
import eeh.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
class Solution0SequentialEntityService implements EntityService<Solution0Sequential, Long> {

    private final Solution0SequentialRepo repository;
    private final Solution0Sequential2Repo repository2;

    @Override
    public Solution0Sequential createNotPersisted() {
        return new Solution0Sequential();
    }

    @Override
    public Identifiable<Long> persistEntityOfAnotherTypeWithId(Long id) {
        for (var i = 1; i <= id; i++) {
            var entity = repository2.save(new Solution0Sequential2());
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        throw new IllegalStateException("Failed to obtain entity with ID=" + id);
    }
}
