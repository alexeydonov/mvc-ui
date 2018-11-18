package mvc.notifications;

import lombok.NonNull;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Collections.emptySet;

/**
 * @author Alexey Donov
 */
public final class NotificationDispatcher {
    private static NotificationDispatcher instance;

    public static NotificationDispatcher shared() {
        if (instance == null) {
            instance = new NotificationDispatcher();
        }

        return instance;
    }

    private final Map<String, Collection<NotificationHandler>> handlers = new ConcurrentHashMap<>();

    private NotificationDispatcher() {
        // No op
    }

    private Collection<NotificationHandler> handlersForName(@NonNull String name) {
        if (!handlers.containsKey(name)) {
            handlers.put(name, emptySet());
        }

        return handlers.get(name);
    }

    /**
     * Runs all registered notification handlers for the name of given notification
     *
     * @param notification Notification
     * @throws IllegalArgumentException if notification is null
     */
    public void dispatch(@NonNull Notification notification) {
        handlersForName(notification.getName()).forEach(handler -> handler.handleNotification(notification));
    }

    /**
     * Register a handler for given notification name
     *
     * @param name Notification name
     * @param handler Notification handler object
     * @throws IllegalArgumentException if name or handler is null
     */
    public void addHandler(@NonNull String name, @NonNull NotificationHandler handler) {
        handlersForName(name).add(handler);
    }

    /**
     * Remove a handler for given notification name
     *
     * @param name Notification name
     * @param handler Notification handler object
     * @throws IllegalArgumentException if name or handler is null
     */
    public void removeHandler(@NonNull String name, @NonNull NotificationHandler handler) {
        handlersForName(name).remove(handler);
    }
}
