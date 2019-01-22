import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SentMailHandlerShould {
    @Test
    public void createAndStoreMailSentProjectionWhenReceiveMailSent() {
        MailSentHandler mailSentHandler = new MailSentHandler(new MailSentRepository());

        mailSentHandler.handle(new MailSentEvent(1, "hello"));

        Assertions.assertThat(mailSentHandler.getSentMailProjection(1)).isNotNull();
        Assertions.assertThat(mailSentHandler.getSentMailProjection(1).getContent()).isEqualTo("hello");
    }

    @Test
    public void deleteMailSentProjectionWhenDeletedMailEventWithSentMail() {
        // Given
        MailSentRepository repository = new MailSentRepository();
        repository.put(1, new MailSentProjection(1, "hello"));
        MailSentHandler mailSentHandler = new MailSentHandler(repository);

        // When
        mailSentHandler.handle(new MailDeleted(1));

        // Then
        Assertions.assertThat(mailSentHandler.getSentMailProjection(1)).isNull();
    }

    private class MailSentHandler {


        private final MailSentRepository repository;

        public MailSentHandler(MailSentRepository repository) {
            this.repository = repository;
        }

        public void handle(MailSentEvent hello) {
            repository.put(hello.id, new MailSentProjection(hello.id, hello.content));
        }

        public MailSentProjection getSentMailProjection(int i) {
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

    private class MailSentProjection {
        private final String content;
        private final Integer id;

        public MailSentProjection(Integer id, String content) {
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

    private class MailSentRepository {
        Map<Integer, MailSentProjection> repository = new HashMap<>();

        public void put(Integer id, MailSentProjection sentMailProjection) {
            repository.put(id, sentMailProjection);
        }

        public MailSentProjection get(int i) {
            return repository.get(i);
        }

        public void remove(int id) {
            repository.remove(id);
        }
    }
}
