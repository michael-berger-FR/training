import java.util.UUID;

class MailSentEvent extends Event{

    public final String content;

    public MailSentEvent(UUID id, String content) {
        super(id);
        this.content = content;
    }
}
