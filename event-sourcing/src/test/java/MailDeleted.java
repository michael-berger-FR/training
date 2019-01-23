import java.util.UUID;

class MailDeleted extends Event{

    public final UUID id;

    public MailDeleted(UUID id) {
        super(id);
        this.id = id;
    }
}
