package net.coderodde.lib3d;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * This class implements a vertex that can be thought of as a vector.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6
 */
public class Vertex implements Iterable<Vertex> {

    public double x;
    public double y;
    public double z;

    /**
     * The list of neighbor vertices.
     */
    private final List<Vertex> neighborVertexList;

    /**
     * The list of colors.
     */
    private final List<Color> neighborColorList;

    /**
     * Constructs a new vertex.
     * 
     * @param x the initial <tt>x</tt>-coordinate.
     * @param y the initial <tt>y</tt>-coordinate.
     * @param z the initial <tt>z</tt>-coordinate.
     */
    public Vertex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.neighborVertexList = new ArrayList<>();
        this.neighborColorList = new ArrayList<>();
    }

    /**
     * Constructs a new vertex with the same coordinates as <code>other</code>.
     * 
     * @param other the other vertex.
     */
    public Vertex(Vertex other) {
        this(other.x, other.y, other.z);
    }

    /**
     * Constructs a new vertex located at origo.
     */
    public Vertex() {
        this(0.0, 0.0, 0.0);
    }

    /**
     * Returns the view of colors of this vertex.
     * 
     * @return the color view.
     */
    public List<Color> getColorList() {
        return Collections.<Color>unmodifiableList(neighborColorList);
    }

    /**
     * Returns the view of neighbor vertices of this vertex.
     * 
     * @return the neighbor view.
     */
    public List<Vertex> getNeighborList() {
        return Collections.<Vertex>unmodifiableList(neighborVertexList);
    }

    /**
     * Adds a neighbor vertex to this vertex.
     * 
     * @param neighbor the neighbor to add.
     * @param color    the color of the edge from this vertex to 
     *                 <code>neighbor</code>.
     */
    public void addNeighbor(Vertex neighbor, Color color) {
        neighborVertexList.add(neighbor);
        neighborColorList.add(color);
    }

    /**
     * Returns an iterator over neighbor vertices of this vertex.
     * 
     * @return an iterator.
     */
    @Override
    public Iterator<Vertex> iterator() {
        return this.neighborVertexList.iterator();
    }

    /**
     * Returns a string representation of this vertex.
     * 
     * @return a string.
     */
    @Override
    public String toString() {
        return "[Vertex (" + x + ", " + y + ", " + z + ")]";
    }
}