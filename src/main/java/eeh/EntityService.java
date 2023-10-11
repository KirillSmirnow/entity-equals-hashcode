package eeh;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntityService<E extends Identifiable<ID>, ID> {

    JpaRepository<E, ID> getRepository();

    @EventListener(ApplicationReadyEvent.class)
    default void init() {
        getRepository().save(createNotPersisted());
        getRepository().save(createNotPersisted());
    }

    default E get(ID id) {
        return getRepository().findById(id).orElseThrow();
    }

    default E getProxy(ID id) {
        return getRepository().getReferenceById(id);
    }

    default E getAny() {
        return getRepository().findAll().get(0);
    }

    default List<E> getAll() {
        return getRepository().findAll();
    }

    E createNotPersisted();

    Identifiable<ID> persistEntityOfAnotherTypeWithId(ID id);
}
