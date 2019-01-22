import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class MailSentRepository {
    Map<UUID, MailSentProjection> repository = new HashMap<>();

    public void put(MailSentProjection sentMailProjection) {
        repository.put(sentMailProjection.aggregateId, sentMailProjection);
    }

    public MailSentProjection get(UUID i) {
        return repository.get(i);
    }

    public void remove(UUID id) {
        repository.remove(id);
    }
}
