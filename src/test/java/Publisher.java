import java.util.ArrayList;
import java.util.List;

class Publisher {
    private List<Event> events = new ArrayList<>();
    private List<Handler> handlers = new ArrayList<>();

    public void publish(Event event) {
        events.add(event);
        handlers.forEach(handler -> handler.handle(event));
    }

    public List<Event> getEvents() {
        return events;
    }

    public void subscribe(Handler handler) {
        handlers.add(handler);
    }
}
