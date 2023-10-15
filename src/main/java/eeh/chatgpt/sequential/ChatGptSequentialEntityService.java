package eeh.chatgpt.sequential;

import eeh.EntityService;
import eeh.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
class ChatGptSequentialEntityService implements EntityService<ChatGptSequential, Long> {

    private final ChatGptSequentialRepo repository;
    private final ChatGptSequential2Repo repository2;

    @Override
    public ChatGptSequential createNotPersisted() {
        return new ChatGptSequential();
    }

    @Override
    public Identifiable<Long> persistEntityOfAnotherTypeWithId(Long id) {
        for (var i = 1; i <= id; i++) {
            var entity = repository2.save(new ChatGptSequential2());
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        throw new IllegalStateException("Failed to obtain entity with ID=" + id);
    }
}
