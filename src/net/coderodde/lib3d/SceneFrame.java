package net.coderodde.lib3d;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * This class implements the frame containing a view.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6
 */
public class SceneFrame extends JFrame {

    /**
     * The actual view component.
     */
    private final SceneView view;

    /**
     * Constructs a frame containing the view.
     * 
     * @param width  the width of the frame in pixels.
     * @param height the height of the frame in pixels.
     */
    SceneFrame(int width, int height) {
        super("3D Cube");
        add(view = new SceneView(width, height));
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Dimension screenDimension = 
                Toolkit.getDefaultToolkit().getScreenSize();

        // Center out the frame.
        setLocation((screenDimension.width - getWidth()) / 2,
                    (screenDimension.height - getHeight()) / 2);

        setVisible(true);
    }

    /**
     * Returns the scene view.
     * 
     * @return the scene view.
     */
    public SceneView getSceneView() {
        return view;
    }
}