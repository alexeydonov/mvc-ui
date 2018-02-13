package mvc.notifications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Alexey Donov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Notification {
    private String name;
    private Map<String, Object> payload;

    public Notification(String name) {
        this(name, null);
    }
}
