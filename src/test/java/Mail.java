import java.util.List;
import java.util.UUID;

class Mail {
    private String content;
    private UUID id;

    public Mail(List<? extends Event> eventList) {
        eventList.forEach(event -> apply(event));
    }

    private void apply(Event event) {
        if (event instanceof DraftInitialized) {
            content = ((DraftInitialized) event).content;
            id = event.aggregateId;
        }
    }

    public DraftInitialized initializeDraft(String content) {
        return new DraftInitialized(UUID.randomUUID(), content);
    }

    public Event sendMail() {
        if (content == null){
            return new MailRefused(this.id);
        }
        return new MailSentEvent(this.id, this.content);
    }
}
