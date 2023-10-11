package eeh;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EntityEqualsAndHashCodeTest {

    @Autowired
    private EntityServicesProvider entityServicesProvider;

    private static List<Class<?>> provideEntityServiceTypes() {
        return EntityServicesProvider.getEntityServiceTypes();
    }

    @ParameterizedTest
    @MethodSource("provideEntityServiceTypes")
    public <ID> void identicalEntitiesFetchedInDifferentTransactionsMustBeEqual(
            Class<EntityService<?, ID>> entityServiceType
    ) {
        var entityService = entityServicesProvider.provide(entityServiceType);

        var entity1 = entityService.getAny();
        var entity2 = entityService.get(entity1.getId());

        assertThat(entity1.getId()).isEqualTo(entity2.getId());
        assertThat(entity1).hasSameClassAs(entity2);

        EntityComparator.assertEqual(entity1, entity2);
    }

    @ParameterizedTest
    @MethodSource("provideEntityServiceTypes")
    public <ID> void identicalEntitiesFetchedAsNotProxyAndProxyMustBeEqual(
            Class<EntityService<?, ID>> entityServiceType
    ) {
        var entityService = entityServicesProvider.provide(entityServiceType);

        var entity1 = entityService.getAny();
        var entity2 = entityService.getProxy(entity1.getId());

        assertThat(entity1.getId()).isEqualTo(entity2.getId());
        assertThat(entity1).doesNotHaveSameClassAs(entity2);

        EntityComparator.assertEqual(entity1, entity2);
    }

    @ParameterizedTest
    @MethodSource("provideEntityServiceTypes")
    public <ID> void differentEntitiesMustBeNotEqual(
            Class<EntityService<?, ID>> entityServiceType
    ) {
        var entityService = entityServicesProvider.provide(entityServiceType);

        var entities = entityService.getAll();
        var entity1 = entities.get(0);
        var entity2 = entities.get(1);

        assertThat(entity1.getId()).isNotEqualTo(entity2.getId());
        assertThat(entity1).hasSameClassAs(entity2);

        EntityComparator.assertNotEqual(entity1, entity2);
    }

    @ParameterizedTest
    @MethodSource("provideEntityServiceTypes")
    public <ID> void notPersistedEntitiesMustBeNotEqual(
            Class<EntityService<?, ID>> entityServiceType
    ) {
        var entityService = entityServicesProvider.provide(entityServiceType);

        var entity1 = entityService.createNotPersisted();
        var entity2 = entityService.createNotPersisted();

        if (entity1.getId() != null) {
            assertThat(entity1.getId()).isNotEqualTo(entity2.getId());
        }
        assertThat(entity1).hasSameClassAs(entity2);

        EntityComparator.assertNotEqual(entity1, entity2);
    }

    @ParameterizedTest
    @MethodSource("provideEntityServiceTypes")
    public <ID> void entitiesOfDifferentTypesWithSameIdMustBeNotEqual(
            Class<EntityService<?, ID>> entityServiceType
    ) {
        var entityService = entityServicesProvider.provide(entityServiceType);

        var entity1 = entityService.getAny();
        var entity2 = entityService.persistEntityOfAnotherTypeWithId(entity1.getId());

        assertThat(entity1.getId()).isEqualTo(entity2.getId());
        assertThat(entity1).doesNotHaveSameClassAs(entity2);

        EntityComparator.assertNotEqual(entity1, entity2);
    }
}
