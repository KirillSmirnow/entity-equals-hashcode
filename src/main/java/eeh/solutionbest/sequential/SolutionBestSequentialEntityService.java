package eeh.solutionbest.sequential;

import eeh.EntityService;
import eeh.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
class SolutionBestSequentialEntityService implements EntityService<SolutionBestSequential, Long> {

    private final SolutionBestSequentialRepo repository;
    private final SolutionBestSequential2Repo repository2;

    @Override
    public SolutionBestSequential createNotPersisted() {
        return new SolutionBestSequential();
    }

    @Override
    public Identifiable<Long> persistEntityOfAnotherTypeWithId(Long id) {
        for (var i = 1; i <= id; i++) {
            var entity = repository2.save(new SolutionBestSequential2());
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        throw new IllegalStateException("Failed to obtain entity with ID=" + id);
    }
}
