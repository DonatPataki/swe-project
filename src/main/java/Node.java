 class Node implements Comparable {
    public Node parent;
    public Point position;
    public double g;
    public double h;

    Node(Node parent, Point position, double g, double h) {
        this.parent = parent;
        this.position = position;
        this.g = g;
        this.h = h;
    }

    @Override
    public int compareTo(Object o) {
        Node otherNode = (Node) o;
        return (int) ((this.g + this.h) - (otherNode.g + otherNode.h));
    }

    @Override
    public boolean equals(Object o) {
        Node otherNode = (Node) o;
        return this.position.getX() == otherNode.position.getX() && this.position.getY() == otherNode.position.getY();
    }
}