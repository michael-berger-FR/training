import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;

public class PublisherTest {
    @Test
    public void shouldStoreEventsWhenPublishEvent() {
        // Given
        Publisher publisher = new Publisher();
        Event event = new Event(UUID.randomUUID());

        // When
        publisher.publish(event);

        // Then
        Assertions.assertThat(publisher.getEvents()).contains(event);
    }

    @Test
    public void shouldCallHandlersWhenPublishEvent() {
        // Given
        Publisher publisher = new Publisher();
        Event event = new Event(UUID.randomUUID());
        Handler handler = Mockito.mock(Handler.class);
        publisher.subscribe(handler);

        // When
        publisher.publish(event);

        // Then
        Mockito.verify(handler).handle(event);
    }

}
