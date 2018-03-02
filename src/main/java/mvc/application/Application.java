package mvc.application;

import mvc.notifications.Notification;
import mvc.notifications.NotificationDispatcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * @author Alexey Donov
 */
public final class Application implements Runnable {
    public static final String NOTIFICATION_APPLICATION_WILL_START = "mvc.ApplicationWillStart";
    public static final String NOTIFICATION_APPLICATION_DID_START = "mvc.ApplicationDidStart";
    public static final String NOTIFICATION_APPLICATION_WILL_FINISH = "mvc.ApplicationWillFinish";
    public static final String NOTIFICATION_APPLICATION_DID_FINISH = "mvc.ApplicationDidFinish";

    public ApplicationDelegate delegate;

    public final Collection<String> arguments;

    public Application(String[] arguments) {
        this(Arrays.asList(arguments));
    }

    public Application(Collection<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public void run() {
        Optional.ofNullable(delegate).ifPresent(d -> d.applicationWillStart(this));
        NotificationDispatcher.instance().dispatch(new Notification(NOTIFICATION_APPLICATION_WILL_START));
        // TODO: Some setup
        Optional.ofNullable(delegate).ifPresent(d -> d.applicationDidStart(this));
        NotificationDispatcher.instance().dispatch(new Notification(NOTIFICATION_APPLICATION_DID_START));

        try {
            Thread.currentThread().join();
        } catch (InterruptedException ignore) {
            // No op
        }

        Optional.ofNullable(delegate).ifPresent(d -> d.applicationWillFinish(this));
        NotificationDispatcher.instance().dispatch(new Notification(NOTIFICATION_APPLICATION_WILL_FINISH));
        // TODO: Finalization
        Optional.ofNullable(delegate).ifPresent(d -> d.applicationDidFinish(this));
        NotificationDispatcher.instance().dispatch(new Notification(NOTIFICATION_APPLICATION_DID_FINISH));
    }

    public void exit() {
        exit(0);
    }

    public void exit(int code) {
        System.exit(code);
    }
}
