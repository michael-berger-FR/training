class MailSentHandler implements Handler{


    private final MailSentRepository repository;

    public MailSentHandler(MailSentRepository repository) {
        this.repository = repository;
    }

    public void handle(MailSentEvent hello) {
        repository.put(new MailSentProjection(hello.aggregateId, hello.content));
    }

    public void handle(MailDeleted mailDeleted) {
        repository.remove(mailDeleted.id);
    }

    @Override
    public void handle(Event event) {
        if (event instanceof MailSentEvent)
            handle((MailSentEvent)event);
        if (event instanceof MailDeleted)
            handle((MailDeleted)event);
    }
}
