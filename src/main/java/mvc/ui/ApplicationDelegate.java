package mvc.ui;

/**
 * @author Alexey Donov
 */
public interface ApplicationDelegate {
    void applicationWillStart(Application application);
    void applicationDidStart(Application application);
    void applicationWillFinish(Application application);
    void applicationDidFinish(Application application);
}
