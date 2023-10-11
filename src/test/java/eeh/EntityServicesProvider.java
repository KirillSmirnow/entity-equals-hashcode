package eeh;

import org.junit.platform.commons.support.ReflectionSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Component
@SuppressWarnings("unchecked")
public class EntityServicesProvider {

    private static List<Class<?>> ENTITY_SERVICE_TYPES;

    private final Map<Class<?>, EntityService<?, ?>> entityServicesByType;

    public EntityServicesProvider(ApplicationContext context) {
        entityServicesByType = getEntityServiceTypes().stream().collect(
                toMap(identity(), type -> (EntityService<?, ?>) context.getBean(type))
        );
    }

    public static List<Class<?>> getEntityServiceTypes() {
        if (ENTITY_SERVICE_TYPES == null) {
            ENTITY_SERVICE_TYPES = ReflectionSupport.findAllClassesInPackage(
                    RootConfiguration.class.getPackageName(),
                    type -> type != EntityService.class && EntityService.class.isAssignableFrom(type),
                    name -> true
            );
        }
        return ENTITY_SERVICE_TYPES;
    }

    public <ID> EntityService<?, ID> provide(Class<EntityService<?, ID>> type) {
        return (EntityService<?, ID>) entityServicesByType.get(type);
    }
}
