import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        List<? extends Event> eventList = Collections.singletonList(new DraftInitialized("content"));
        Mail mail = new Mail(eventList);

        // When
        Event event = mail.sendMail();

        // Then
        Assertions.assertThat(event).isInstanceOf(SentMail.class);
        Assertions.assertThat(((SentMail)event).content).isEqualTo("content");
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

    private class Mail {
        private String content;

        public Mail(List<? extends Event> eventList) {
            eventList.forEach(event -> apply(event));
        }

        private void apply(Event event) {
            if (event instanceof DraftInitialized) {
                content = ((DraftInitialized) event).content;
            }
        }

        public DraftInitialized initializeDraft(String content) {
            return new DraftInitialized(content);
        }

        public Event sendMail() {
            if (content == null){
                return new MailRefused();
            }
            return new SentMail(this.content);
        }
    }

    private class InitializeDraft {
    }

    private class DraftInitialized extends Event {
        public final String content;

        private DraftInitialized(String content) {
            this.content = content;
        }
    }

    private class Event {
    }

    private class SentMail extends Event {
        public final String content;

        private SentMail(String content) {
            this.content = content;
        }
    }

    private class MailRefused  extends Event {
    }
}
