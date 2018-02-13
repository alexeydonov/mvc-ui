package mvc.ui.controller;

/**
 * @author Alexey Donov
 */
public interface ControllerDelegate {
    void controllerDidPresent(Controller controller);
    void controllerDidDismiss(Controller controller);
}
