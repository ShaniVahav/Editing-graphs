package api;

public class pair {
    protected NodeData n;
    protected double distanceFromsrc;

    public pair(NodeData n, double distanceFromsrc) {
        this.n = n;
        this.distanceFromsrc = distanceFromsrc;
    }
}
