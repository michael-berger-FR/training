import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Mailtest {

    @Test
    public void mailShouldSendEventDraftInitializedWhenReceiveInitializeDraft() {
        Mail mail = new Mail();
        DraftInitialized event = mail.initializeDraft("content");
        Assertions.assertThat(event).isNotNull();
        Assertions.assertThat(event.content).isEqualTo("content");
    }

    @Test
    public void mailWithInitializedDraftShouldSendSentMailWhenReceiveSendMail() {
        // Given
        Mail mail = new Mail();
        mail.initializeDraft("content");

        // When
        SentMail event = mail.sendMail();

        // Then
        Assertions.assertThat(event).isNotNull();
        Assertions.assertThat(event.content).isEqualTo("content");
    }

    private class Mail {
        private String content;

        public DraftInitialized initializeDraft(String content) {
            this.content = content;
            return new DraftInitialized(content);
        }

        public SentMail sendMail() {
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

    private class SentMail {
        public final String content;

        private SentMail(String content) {
            this.content = content;
        }
    }
}
