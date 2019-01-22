import java.util.UUID;

class Event {
    public final UUID aggregateId;

    Event(UUID aggregateId) {
        this.aggregateId = aggregateId;
    }
}
