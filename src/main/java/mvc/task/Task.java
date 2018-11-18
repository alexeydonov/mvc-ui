package mvc.task;

import lombok.val;
import mvc.notifications.Notification;
import mvc.notifications.NotificationDispatcher;

import java.util.Optional;

import static java.util.Collections.singletonMap;
import static java.util.Collections.unmodifiableMap;

/**
 * @author Alexey Donov
 */
public abstract class Task implements Runnable {
    public static final String NOTIFICATION_TASK_DID_START = "mvc.TaskDidStart";
    public static final String NOTIFICATION_TASK_DID_PROGRESS = "mvc.TaskDidProgress";
    public static final String NOTIFICATION_TASK_DID_FAIL = "mvc.TaskDidFail";
    public static final String NOTIFICATION_TASK_DID_FINISH = "mvc.TaskDidFinish";
    
    public static final String NOTIFICATION_TASK_EXCEPTION_KEY = "mvc.Task.Exception";
    public static final String NOTIFICATION_TASK_PROGRESS_KEY = "mvc.Task.Progress";

    public TaskDelegate delegate;

    private double progress;

    public double getProgress() {
        return progress;
    }

    protected void setProgress(double progress) {
        this.progress = progress;

        Optional.ofNullable(delegate).ifPresent(d -> d.taskDidProgress(this));
        val payload = unmodifiableMap(singletonMap(NOTIFICATION_TASK_PROGRESS_KEY, progress));
        NotificationDispatcher.shared().dispatch(new Notification(NOTIFICATION_TASK_DID_PROGRESS, payload, this));
    }

    @Override
    public void run() {
        progress = 0;

        Optional.ofNullable(delegate).ifPresent(d -> d.taskDidStart(this));
        NotificationDispatcher.shared().dispatch(new Notification(NOTIFICATION_TASK_DID_START, null, this));

        try {
            execute();
        } catch (Exception e) {
            Optional.ofNullable(delegate).ifPresent(d -> d.taskDidFail(this, e));
            val payload = unmodifiableMap(singletonMap(NOTIFICATION_TASK_EXCEPTION_KEY, e));
            NotificationDispatcher.shared().dispatch(new Notification(NOTIFICATION_TASK_DID_FAIL, payload, this));
        }

        Optional.ofNullable(delegate).ifPresent(d -> d.taskDidFinish(this));
        NotificationDispatcher.shared().dispatch(new Notification(NOTIFICATION_TASK_DID_FINISH, null, this));
    }

    public abstract void execute() throws Exception;
}
