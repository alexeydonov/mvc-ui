package mvc.task;

import java.util.Optional;

/**
 * @author Alexey Donov
 */
public abstract class Task implements Runnable {
    public TaskDelegate delegate;

    private double progress;

    public double getProgress() {
        return progress;
    }

    protected void setProgress(double progress) {
        this.progress = progress;

        Optional.ofNullable(delegate).ifPresent(d -> d.taskDidProgress(this));
    }

    @Override
    public void run() {
        progress = 0;

        Optional.ofNullable(delegate).ifPresent(d -> d.taskDidStart(this));

        try {
            execute();
        } catch (Exception e) {
            Optional.ofNullable(delegate).ifPresent(d -> d.taskDidFail(this, e));
        }

        Optional.ofNullable(delegate).ifPresent(d -> d.taskDidFinish(this));
    }

    public abstract void execute() throws Exception;
}
