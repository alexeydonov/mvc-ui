package mvc.ui.controller;

import mvc.ui.window.Window;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @author Alexey Donov
 */
public abstract class WindowController extends Controller implements WindowListener {
    protected final Window window;

    protected abstract Window createWindow();

    public WindowController() {
        this.window = createWindow();
        this.window.addWindowListener(this);
    }

    @Override
    public void present() {
        this.window.setVisible(true);
    }

    @Override
    public void dismiss() {
        this.window.setVisible(false);
    }

    // WindowListener

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
