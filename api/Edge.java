package api;

public class Edge implements EdgeData {
    private final Node node_in;
    private final Node node_out;
    private final double weight;  // positive number
    private String info;
    private int tag = 0;



    public Edge(Node node_in, Node node_out, double weight){
        this.node_in = node_in;
        this.node_out = node_out;
        this.weight = weight;
        this.info = "(" + this.node_in.getKey() + ", " + this.node_out.getKey() + ", " + this.weight + ", " + this.tag + ")";
    }

    public Edge(Edge other){
        this.node_in = new Node(other.node_in);
        this.node_out = new Node(other.node_out);
        this.weight = other.weight;
        this.info = other.info+"";
    }

    @Override
    public int getSrc() {
        return node_in.getKey();
    }

    @Override
    public int getDest() {
        return node_out.getKey();
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    public NodeData getNode_in() {
        return this.node_in;
    }

    public NodeData getNode_out() {
        return this.node_out;
    }
}
