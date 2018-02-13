package mvc.ui.window;

import javax.swing.*;

/**
 * @author Alexey Donov
 */
public abstract class Window extends JFrame {
    public Window() {
        super();

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
