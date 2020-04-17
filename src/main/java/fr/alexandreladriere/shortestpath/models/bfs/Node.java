package main.java.fr.alexandreladriere.shortestpath.models.bfs;

/**
 * Implement a Node (i.e. a cell in the matrix
 */
public class Node {
    int x, y; // coordinates of the node in the matrix
    Node parent; // Parent of the node

    /**
     * Default constructor
     *
     * @param x      X coordinate
     * @param y      Y coordinate
     * @param parent Parent node
     */
    public Node(int x, int y, Node parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    /**
     * Override the "toString" function
     *
     * @return String representation of the node. e.g.: Node{x=3, y=2, parent=Node{...}}
     */
    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", parent=" + parent +
                '}';
    }

    /**
     * Return a string representation of the node coordinates
     *
     * @return String representation of the node coordinates (e.g.: Node{x=3, y=2})
     */
    public String toStringCoordinates() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}