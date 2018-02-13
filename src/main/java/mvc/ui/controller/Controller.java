package mvc.ui.controller;

import java.util.Optional;

/**
 * @author Alexey Donov
 */
public abstract class Controller {
    public ControllerDelegate delegate;

    public void present() {
        Optional.ofNullable(delegate).ifPresent(d -> d.controllerDidPresent(this));
    }

    public void dismiss() {
        Optional.ofNullable(delegate).ifPresent(d -> d.controllerDidDismiss(this));
    }
}
