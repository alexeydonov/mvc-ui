package mvc.notifications;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.util.Collections.emptySet;

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
    public void dispatch(Notification notification) {
        if (notification == null) {
            throw new IllegalArgumentException("notification must not be null");
        }

        if (notification.getName() == null) {
            throw new IllegalArgumentException("Notification's name must not be null");
        }

        handlersForName(notification.getName()).forEach(handler -> handler.handleNotification(notification));
    }

    /**
     * Register a handler for given notification name
     *
     * @param name Notification name
     * @param handler Notification handler object
     * @throws IllegalArgumentException if name or handler is null
     */
    public void addHandler(String name, NotificationHandler handler) {
        if (name == null) {
            throw new IllegalArgumentException("name must not be null");
        }

        if (handler == null) {
            throw new IllegalArgumentException("handler must not be null");
        }

        handlersForName(name).add(handler);
    }

    /**
     * Remove a handler for given notification name
     *
     * @param name Notification name
     * @param handler Notification handler object
     * @throws IllegalArgumentException if name or handler is null
     */
    public void removeHandler(String name, NotificationHandler handler) {
        if (name == null) {
            throw new IllegalArgumentException("name must not be null");
        }

        if (handler == null) {
            throw new IllegalArgumentException("handler must not be null");
        }

        handlersForName(name).remove(handler);
    }
}
