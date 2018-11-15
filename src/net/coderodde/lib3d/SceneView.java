package net.coderodde.lib3d;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class implements a scene view displaying three-dimensional objects.
 *
 * @author Rodion "rodde" Efremov
 */
public class SceneView extends Canvas implements KeyListener {

    /**
     * The list of scene objects in this view.
     */
    private final List<SceneObject> sceneObjectList;

    /**
     * The rotation source. All objects are rotated with respect to this point.
     */
    private final Vertex rotationSource;

    /**
     * Constructs this scene canvas.
     *
     * @param width the width of this canvas in pixels.
     * @param height the height of this canvas in pixels.
     */
    public SceneView(int width, 
                     int height) {
        setPreferredSize(new Dimension(width, height));
        this.sceneObjectList = new ArrayList<>();
        this.rotationSource = new Vertex(width / 2, height / 2, 0);
        this.addKeyListener(this);
        this.setBackground(Color.BLACK);
    }

    /**
     * Draws this view.
     * 
     * @param g the graphics device handle.
     */
    @Override
    public void update(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.red);

        for (final SceneObject object : sceneObjectList) {
            final Vertex objectOrigin = object.getLocation();
            final List<Vertex> vertexList = object.getVertexList();

            for (int i = 0; i < vertexList.size(); ++i) {
                final Vertex v = vertexList.get(i);
                final List<Color> colorList = v.getColorList();
                final List<Vertex> neighborList = v.getNeighborList();

                for (int j = 0; j < neighborList.size(); ++j) {
                    final Vertex neighbor = neighborList.get(j);

                    g.setColor(colorList.get(j));

                    g.drawLine((int) Math.round(objectOrigin.x + v.x),
                               (int) Math.round(objectOrigin.y + v.y),
                               (int) Math.round(objectOrigin.x + neighbor.x),
                               (int) Math.round(objectOrigin.y + neighbor.y));
                }
            }
        }
    }

    /**
     * Draws this view.
     * 
     * @param g the graphics device handle.
     */
    @Override
    public void paint(Graphics g) {
        update(g);
    }

    /**
     * Adds all the vectors in <code>vectors</code> to this scene view.
     *
     * @param objects the world objects to add to this view.
     */
    public void addWorldObject(SceneObject... objects) {
        sceneObjectList.addAll(Arrays.asList(objects));
    }

    /**
     * Responds to the event of a key being typed.
     * 
     * @param e the key event.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        char ch = e.getKeyChar();
        ch = Character.toUpperCase(ch);
        
       switch (ch) {
            case KeyEvent.VK_A:

                sceneObjectList.stream().forEach((o) -> {
                    o.rotate(rotationSource, 0.0, -0.1, 0.0);
                });
                break;

            case KeyEvent.VK_D:

                sceneObjectList.stream().forEach((o) -> {
                    o.rotate(rotationSource, 0.0, 0.1, 0.0);
                });
                break;

            case KeyEvent.VK_W:

                sceneObjectList.stream().forEach((o) -> {
                    o.rotate(rotationSource, 0.1, 0.0, 0.0);
                });
                break;

            case KeyEvent.VK_S:
                System.out.println("S");
                sceneObjectList.stream().forEach((o) -> {
                    o.rotate(rotationSource, -0.1, -0.0, 0.0);
                });
                break;

            case KeyEvent.VK_Q:

                sceneObjectList.stream().forEach((o) -> {
                    System.out.println("Q");
                    o.rotate(rotationSource, 0.0, 0.0, -0.1);
                });
                break;

            case KeyEvent.VK_E:

                sceneObjectList.stream().forEach((o) -> {
                    o.rotate(rotationSource, 0.0, 0.0, 0.1);
                });
                break;
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
