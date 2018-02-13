package mvc.ui;

import mvc.notifications.Notification;
import mvc.notifications.NotificationDispatcher;

import java.util.Optional;

/**
 * @author Alexey Donov
 */
public final class Application implements Runnable {
    public static final String NOTIFICATION_APPLICATION_WILL_START = "com.alexeydonov.ui.ApplicationWillStart";
    public static final String NOTIFICATION_APPLICATION_DID_START = "com.alexeydonov.ui.ApplicationDidStart";
    public static final String NOTIFICATION_APPLICATION_WILL_FINISH = "com.alexeydonov.ui.ApplicationWillFinish";
    public static final String NOTIFICATION_APPLICATION_DID_FINISH = "com.alexeydonov.ui.ApplicationDidFinish";

    private static Application instance;

    public static Application instance() {
        if (instance == null) {
            instance = new Application();
        }

        return instance;
    }

    private ApplicationDelegate delegate;

    private Application() {
        // No op
    }

    @Override
    public void run() {
        Optional.ofNullable(delegate).ifPresent(d -> d.applicationWillStart(this));
        NotificationDispatcher.instance().dispatch(new Notification(NOTIFICATION_APPLICATION_WILL_START));
        // Some setup
        Optional.ofNullable(delegate).ifPresent(d -> d.applicationDidStart(this));
        NotificationDispatcher.instance().dispatch(new Notification(NOTIFICATION_APPLICATION_DID_START));

        try {
            Thread.currentThread().join();
        } catch (InterruptedException ignore) {
            // No op
        }

        Optional.ofNullable(delegate).ifPresent(d -> d.applicationWillFinish(this));
        NotificationDispatcher.instance().dispatch(new Notification(NOTIFICATION_APPLICATION_WILL_FINISH));
        // Finalization
        Optional.ofNullable(delegate).ifPresent(d -> d.applicationDidFinish(this));
        NotificationDispatcher.instance().dispatch(new Notification(NOTIFICATION_APPLICATION_DID_FINISH));
    }
}
