import java.util.UUID;

class DraftInitialized extends Event {
    public final String content;

    DraftInitialized(UUID id, String content) {
        super(id);
        this.content = content;
    }
}
