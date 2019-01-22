import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class EventStore {
    Path path = Paths.get("c:\\toto.txt");

    public void add(Event event) throws IOException {
        try (OutputStream out = Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND, StandardOpenOption.WRITE)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(event);
            objectOutputStream.close();
        }
    }

    public List<Event> getAllEvents() throws IOException, ClassNotFoundException {
        List<Event> result = new ArrayList<>();
        try (InputStream in = Files.newInputStream(path)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            try {
                while (true) {
                    Object o = objectInputStream.readObject();
                    result.add((Event) o);
                }
            }catch (EOFException e){}
            objectInputStream.close();
        }
        return result;
    }
}
