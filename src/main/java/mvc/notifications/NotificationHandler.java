package mvc.notifications;

/**
 * @author Alexey Donov
 */
@FunctionalInterface
public interface NotificationHandler {
    void handleNotification(Notification notification);
}
