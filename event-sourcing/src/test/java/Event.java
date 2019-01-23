import java.io.Serializable;
import java.util.UUID;

class Event implements Serializable {
    public final UUID aggregateId;

    Event(UUID aggregateId) {
        this.aggregateId = aggregateId;
    }
}
