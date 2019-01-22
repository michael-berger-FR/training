import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SentMailHandlerShould {
    @Test
    public void createAndStoreMailSentProjectionWhenReceiveMailSent() {
        MailSentHandler mailSentHandler = new MailSentHandler(new HashMap<>());
        mailSentHandler.handle(new MailSentEvent(1, "hello"));
        Assertions.assertThat(mailSentHandler.getSentMailProjection(1)).isNotNull();
        Assertions.assertThat(mailSentHandler.getSentMailProjection(1).getContent()).isEqualTo("hello");
    }

    @Test
    public void deleteMailSentProjectionWhenDeletedMailEventWithSentMail() {
        // Given
        Map<Integer, SentMailProjection> repository = new HashMap<>();
        repository.put(1, new SentMailProjection(1, "hello"));
        MailSentHandler mailSentHandler = new MailSentHandler(repository);

        // When
        mailSentHandler.handle(new MailDeleted(1));

        // Then
        Assertions.assertThat(mailSentHandler.getSentMailProjection(1)).isNull();
    }

    private class MailSentHandler {
        Map<Integer, SentMailProjection> repository = new HashMap<>();

        public MailSentHandler(Map<Integer, SentMailProjection> repository) {
            this.repository = repository;
        }

        public void handle(MailSentEvent hello) {
            repository.put(hello.id, new SentMailProjection(hello.id, hello.content));
        }

        public SentMailProjection getSentMailProjection(int i) {
            return repository.get(i);
        }

        public void handle(MailDeleted mailDeleted) {
            repository.remove(mailDeleted.id);
        }
    }

    private class MailSentEvent {

        public final String content;
        public final Integer id;

        public MailSentEvent(int id, String content) {
            this.id = id;
            this.content = content;
        }
    }

    private class SentMailProjection {
        private final String content;
        private final Integer id;

        public SentMailProjection(Integer id, String content) {
            this.id = id;
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }

    private class MailDeleted {

        public final int id;

        public MailDeleted(int id) {
            this.id = id;
        }
    }
}
