package net.coderodde.lib3d;

import java.awt.Color;
import javax.swing.SwingUtilities;

public class Demo {

    /**
     * The entry point into a program.

     * @param args the command line arguments.
     */
    public static void main(final String... args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SceneFrame frame = new SceneFrame(600, 600);
                
                // Let's build a wire cube.
                SceneObject cube = new SceneObject(300, 300, 0);
                
                // The vertices.
                Vertex v000 = new Vertex(-100, -100, -100);
                Vertex v001 = new Vertex(-100, -100, 100);
                Vertex v010 = new Vertex(-100, 100, -100);
                Vertex v011 = new Vertex(-100, 100, 100);
                
                Vertex v100 = new Vertex(100, -100, -100);
                Vertex v101 = new Vertex(100, -100, 100);
                Vertex v110 = new Vertex(100, 100, -100);
                Vertex v111 = new Vertex(100, 100, 100);
                
                Color red = Color.red;
                Color green = Color.green;
                Color yellow = Color.yellow;
                
                // Each vertex must know what other vertices it is linked to.
                v000.addNeighbor(v001, red);
                v000.addNeighbor(v010, red);
                v000.addNeighbor(v100, yellow);
                
                v001.addNeighbor(v101, yellow);
                v001.addNeighbor(v011, red);
                v001.addNeighbor(v000, red);
                
                v010.addNeighbor(v110, yellow);
                v010.addNeighbor(v000, red);
                v010.addNeighbor(v011, red);
                
                v011.addNeighbor(v111, yellow);
                v011.addNeighbor(v001, red);
                v011.addNeighbor(v010, red);
                
                //
                
                v100.addNeighbor(v000, yellow);
                v100.addNeighbor(v110, green);
                v100.addNeighbor(v101, green);
                
                v101.addNeighbor(v001, yellow);
                v101.addNeighbor(v111, green);
                v101.addNeighbor(v100, green);
                
                v110.addNeighbor(v010, yellow);
                v110.addNeighbor(v100, green);
                v110.addNeighbor(v111, green);
                
                v111.addNeighbor(v011, yellow);
                v111.addNeighbor(v101, green);
                v111.addNeighbor(v110, green);
                
                // Load the vertices to the cube.
                cube.add(v000);
                cube.add(v001);
                cube.add(v010);
                cube.add(v011);
                
                cube.add(v100);
                cube.add(v101);
                cube.add(v110);
                cube.add(v111); 
                
                cube.rotate(cube.getLocation(),
                        0.0,
                        Math.PI / 2.0,
                        0.0);
                
                //frame.getSceneView().addKeyListener(() -> {});
                frame.getSceneView().addWorldObject(cube);
            }
        });
    }
}