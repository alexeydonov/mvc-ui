package mvc.notifications;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.util.Collections.emptyList;

/**
 * @author Alexey Donov
 */
public final class NotificationDispatcher {
    private static NotificationDispatcher instance;

    public static NotificationDispatcher instance() {
        if (instance == null) {
            instance = new NotificationDispatcher();
        }

        return instance;
    }

    private ConcurrentMap<String, Collection<NotificationHandler>> handlers = new ConcurrentHashMap<>();

    private NotificationDispatcher() {
        // No op
    }

    private Collection<NotificationHandler> handlersForName(String name) {
        if (!handlers.containsKey(name)) {
            handlers.put(name, emptyList());
        }

        return handlers.get(name);
    }

    public void dispatch(Notification notification) {
        handlersForName(notification.getName()).forEach(handler -> handler.handleNotification(notification));
    }

    public void addHandler(String name, NotificationHandler handler) {
        handlersForName(name).add(handler);
    }

    public void removeHandler(String name, NotificationHandler handler) {
        handlersForName(name).remove(handler);
    }
}
