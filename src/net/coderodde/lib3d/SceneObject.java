package net.coderodde.lib3d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * This class models an object in the scene.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6
 */
public class SceneObject implements Iterable<Vertex> {

    /**
     * The list of vertices this object consists of. These are vector pointing
     * from <code>location</code>.
     */
    private final List<Vertex> vertexList;

    /**
     * The location of this object.
     */
    private final Vertex location;

    /**
     * Constructs a new scene object with given location.
     * 
     * @param x the <tt>x</tt>-coordinate of the location.
     * @param y the <tt>y</tt>-coordinate of the location.
     * @param z the <tt>z</tt>-coordinate of the location.
     */
    public SceneObject(double x, double y, double z) {
        this.location = new Vertex(x, y, z);
        this.vertexList = new ArrayList<>();
    }

    /**
     * Constructs a new scene object with given location.
     * 
     * @param vertex the initial location.
     */
    public SceneObject(Vertex vertex) {
        this(vertex.x, vertex.y, vertex.y);
    }

    /**
     * Constructs a new scene object with location at origo.
     */
    public SceneObject() {
        this(0.0, 0.0, 0.0);
    }

    /**
     * Returns the view of vertices belonging to this object.
     * 
     * @return the vertex view.
     */
    public List<Vertex> getVertexList() {
        return Collections.<Vertex>unmodifiableList(vertexList);
    }

    /**
     * Returns the location of this scene object.
     * 
     * @return the location.
     */
    public Vertex getLocation() {
        return new Vertex(location);
    }

    /**
     * Sets the location of this object.
     * 
     * @param  v the new location.
     */
    public void setLocation(Vertex v) {
        this.location.x = v.x;
        this.location.y = v.y;
        this.location.z = v.z;
    }

    /**
     * Adds a vertex to this object.
     * 
     * @param vertex the vertex to add.
     */
    public void add(Vertex vertex) {
        this.vertexList.add(vertex);
    }

    /**
     * Rotates this geometric object relative to the point
     * <code>relative</code>.
     *
     * @param relative the relative point.
     * @param angleAroundX the angle around the <tt>x</tt>-axis.
     * @param angleAroundY the angle around the <tt>y</tt>-axis.
     * @param angleAroundZ the angle around the <tt>z</tt>-axis.
     */
    public void rotate(Vertex relative,
                       double angleAroundX,
                       double angleAroundY,
                       double angleAroundZ) {
        rotateImpl(relative,
                   location,
                   angleAroundX,
                   angleAroundY,
                   angleAroundZ);

        // Rotate the locatio vector of this geometric object.
        final Vertex zero = new Vertex();

        // Rotate the vertex vectors.
        for (final Vertex vertex : vertexList) {
            rotateImpl(zero,
                       vertex,
                       -angleAroundX,
                       -angleAroundY,
                       -angleAroundZ);
        }
    }

    /**
     * Implements the rotation routine.
     *
     * @param relative the relative location.
     * @param target the vertex to rotate.
     * @param angleAroundXAxis the angle around the <tt>x</tt>-axis.
     * @param angleAroundY the angle around the <tt>y</tt>-axis.
     * @param angleAroundZ the angle around the <tt>z</tt>-axis.
     */
    private void rotateImpl(Vertex relative,
                            Vertex target,
                            double angleAroundXAxis,
                            double angleAroundYAxis,
                            double angleAroundZAxis) {
        final Matrix x = Matrix.getXRotationMatrix(angleAroundXAxis);
        final Matrix y = Matrix.getYRotationMatrix(angleAroundYAxis);
        final Matrix z = Matrix.getZRotationMatrix(angleAroundZAxis);

        Vertex tmp = new Vertex(target.x - relative.x,
                                target.y - relative.y,
                                target.z - relative.z);

        tmp = x.product(tmp);
        tmp = y.product(tmp);
        tmp = z.product(tmp);

        target.x = relative.x + tmp.x;
        target.y = relative.y + tmp.y;
        target.z = relative.z + tmp.z;
    }

    @Override
    public Iterator<Vertex> iterator() {
        return this.vertexList.iterator();
    }
}