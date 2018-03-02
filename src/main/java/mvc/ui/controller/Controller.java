package mvc.ui.controller;

/**
 * @author Alexey Donov
 */
public abstract class Controller {
    /**
     * Present controller
     *
     */
    public abstract void present();

    /**
     * Dismiss controller
     */
    public abstract void dismiss();
}
