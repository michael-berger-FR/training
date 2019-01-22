import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.UUID;

public class SentMailHandlerShould {
    @Test
    public void createAndStoreMailSentProjectionWhenReceiveMailSent() {
        MailSentRepository repository = new MailSentRepository();
        MailSentHandler mailSentHandler = new MailSentHandler(repository);
        UUID id = UUID.randomUUID();

        mailSentHandler.handle(new MailSentEvent(id, "hello"));

        Assertions.assertThat(repository.get(id)).isNotNull();
        Assertions.assertThat(repository.get(id).getContent()).isEqualTo("hello");
    }

    @Test
    public void deleteMailSentProjectionWhenDeletedMailEventWithSentMail() {
        // Given
        MailSentRepository repository = new MailSentRepository();
        UUID id = UUID.randomUUID();
        repository.put(new MailSentProjection(id, "hello"));
        MailSentHandler mailSentHandler = new MailSentHandler(repository);

        // When
        mailSentHandler.handle(new MailDeleted(id));

        // Then
        Assertions.assertThat(repository.get(id)).isNull();
    }

}
