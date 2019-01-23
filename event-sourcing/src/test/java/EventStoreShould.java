import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class EventStoreShould {
    @Test
    public void returnAllEvents() throws IOException, ClassNotFoundException {
        // Given
        EventStore eventStore = new EventStore();
        UUID id = UUID.randomUUID();
        eventStore.add(new DraftInitialized(id, "msg1"));
        eventStore.add(new MailSentEvent(id, "msg1"));

        // When
        List<Event> eventList = eventStore.getAllEvents();

        // Then
        assertThat(eventList.size()).isEqualTo(2);
    }
}