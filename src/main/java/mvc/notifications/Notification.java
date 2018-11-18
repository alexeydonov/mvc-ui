package mvc.notifications;

import lombok.Data;
import lombok.NonNull;

import java.util.Map;

/**
 * @author Alexey Donov
 */
@Data
public final class Notification {
    private String name;
    private Map<String, ?> payload;
    private Object sender;

    public Notification(@NonNull String name) {
        this(name, null, null);
    }
    
    public Notification(@NonNull String name, Map<String, ?> payload, Object sender) {
        this.name = name;
        this.payload = payload;
        this.sender = sender;
    }
}
