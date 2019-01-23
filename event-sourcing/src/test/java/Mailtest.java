import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Mailtest {

    @Test
    public void mailShouldSendEventDraftInitializedWhenReceiveInitializeDraft() {
        Mail mail = new Mail(new ArrayList<Event>());
        DraftInitialized event = mail.initializeDraft("content");
        Assertions.assertThat(event).isNotNull();
        Assertions.assertThat(event.content).isEqualTo("content");
    }

    @Test
    public void mailWithInitializedDraftShouldSendSentMailWhenReceiveSendMail() {
        // Given
        UUID aggregateId = UUID.randomUUID();
        List<? extends Event> eventList = Collections.singletonList(new DraftInitialized(aggregateId, "content"));
        Mail mail = new Mail(eventList);

        // When
        Event event = mail.sendMail();

        // Then
        Assertions.assertThat(event).isInstanceOf(MailSentEvent.class);
        Assertions.assertThat(((MailSentEvent)event).content).isEqualTo("content");
        Assertions.assertThat(event.aggregateId).isEqualTo(aggregateId);
    }

    @Test
    public void shouldSendMailRefusedIfMailWasNotInitialized() {
        // Given
        List<? extends Event> eventList = new ArrayList<>();
        Mail mail = new Mail(eventList);

        // When
        Event event = mail.sendMail();

        // Then
        Assertions.assertThat(event).isInstanceOf(MailRefused.class);
    }

}
