import java.util.UUID;

class MailRefused extends Event {
    MailRefused(UUID aggregateId) {
        super(aggregateId);
    }
}
