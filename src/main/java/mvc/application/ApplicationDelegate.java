package mvc.application;

/**
 * @author Alexey Donov
 */
public interface ApplicationDelegate {
    default void applicationWillStart(Application application) {
    
    }
    
    default void applicationDidStart(Application application) {
    
    }
    
    default void applicationWillFinish(Application application) {
    
    }
    
    default void applicationDidFinish(Application application) {
    
    }
}
