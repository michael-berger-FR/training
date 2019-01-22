import org.junit.Test;

public class ApplicationShould {

    @Test
    public void displayUpdateProjectionWhenSendCommand() {
        // Given
        MailSentRepository repository = new MailSentRepository();
        MailSentHandler mailSentHandler = new MailSentHandler(repository);
        Publisher publisher = new Publisher();
        publisher.subscribe(mailSentHandler);



    }
}
