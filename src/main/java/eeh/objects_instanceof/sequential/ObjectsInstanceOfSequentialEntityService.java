package eeh.objects_instanceof.sequential;

import eeh.EntityService;
import eeh.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
class ObjectsInstanceOfSequentialEntityService implements EntityService<ObjectsInstanceOfSequential, Long> {

    private final ObjectsInstanceOfSequentialRepo repository;
    private final ObjectsInstanceOfSequential2Repo repository2;

    @Override
    public ObjectsInstanceOfSequential createNotPersisted() {
        return new ObjectsInstanceOfSequential();
    }

    @Override
    public Identifiable<Long> persistEntityOfAnotherTypeWithId(Long id) {
        for (var i = 1; i <= id; i++) {
            var entity = repository2.save(new ObjectsInstanceOfSequential2());
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        throw new IllegalStateException("Failed to obtain entity with ID=" + id);
    }
}
