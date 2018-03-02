package mvc.task;

/**
 * @author Alexey Donov
 */
@FunctionalInterface
public interface TaskDelegate {
    default void taskDidStart(Task task) {
        // No op
    }

    default void taskDidFinish(Task task) {
        // No op
    }

    default void taskDidFail(Task task, Exception e) {
        // No op
    }

    void taskDidProgress(Task task);
}
