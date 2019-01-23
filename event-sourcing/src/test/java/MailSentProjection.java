import java.util.UUID;

class MailSentProjection {
    private final String content;
    public final UUID aggregateId;

    public MailSentProjection(UUID aggregateId, String content) {
        this.aggregateId = aggregateId;
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
