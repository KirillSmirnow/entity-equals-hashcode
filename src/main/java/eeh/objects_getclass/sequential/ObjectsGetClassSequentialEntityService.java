package eeh.objects_getclass.sequential;

import eeh.EntityService;
import eeh.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
class ObjectsGetClassSequentialEntityService implements EntityService<ObjectsGetClassSequential, Long> {

    private final ObjectsGetClassSequentialRepo repository;
    private final ObjectsGetClassSequential2Repo repository2;

    @Override
    public ObjectsGetClassSequential createNotPersisted() {
        return new ObjectsGetClassSequential();
    }

    @Override
    public Identifiable<Long> persistEntityOfAnotherTypeWithId(Long id) {
        for (var i = 1; i <= id; i++) {
            var entity = repository2.save(new ObjectsGetClassSequential2());
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        throw new IllegalStateException("Failed to obtain entity with ID=" + id);
    }
}
