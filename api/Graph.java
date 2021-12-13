package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Graph implements DirectedWeightedGraph {
    protected HashMap<Integer, NodeData> nodes;
    protected HashMap<Integer, HashMap<Integer, EdgeData>> edegs;
    protected HashMap<Integer, HashMap<Integer, EdgeData>> opostieEdges;
    protected int mc;
    protected int numberOfEdgs = 0;
    protected int nodeSizeIncludeDelete = 0 ;

    public Graph() {
        Node.id = 0;
        this.mc = 0;
        this.nodes = new HashMap<Integer, NodeData>();
        this.edegs = new HashMap<Integer, HashMap<Integer, EdgeData>>();
        this.opostieEdges = new HashMap<Integer, HashMap<Integer, EdgeData>>();
        this.numberOfEdgs = 0;

    }

    public Graph(Graph other) {
        this.nodes = new HashMap<Integer, NodeData>();
        this.edegs = new HashMap<Integer, HashMap<Integer, EdgeData>>();
        this.opostieEdges = new HashMap<Integer, HashMap<Integer, EdgeData>>();
        Iterator<NodeData> itNode = other.nodes.values().iterator();
        while (itNode.hasNext()) {
            NodeData current = itNode.next();
            this.addNode(new Node((Node) current));
        }
        Iterator<EdgeData> allit = other.edgeIter();
        while (allit.hasNext()) {
            EdgeData current = allit.next();
            this.connect(current.getSrc(), current.getDest(), current.getWeight());
        }

    }


    public Graph(HashMap<Integer, NodeData> nodes, HashMap<Integer, HashMap<Integer, EdgeData>> edges,
                 HashMap<Integer, HashMap<Integer, EdgeData>> opositeEdges) {
        this.mc = 0;
        this.nodes = nodes;
        this.edegs = opositeEdges;
        this.opostieEdges = edges;
    }

    @Override
    public NodeData getNode(int key) {
        return nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        Edge c = (Edge) edegs.get(src).get(dest);
        return c;
    }

    @Override
    public void addNode(NodeData n) {
        this.nodes.put(n.getKey(), n);
        this.edegs.put(n.getKey(), new HashMap<Integer, EdgeData>());
        this.opostieEdges.put(n.getKey(), new HashMap<Integer, EdgeData>());
        this.nodeSizeIncludeDelete++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        Node nodeIn = (Node) nodes.get(src);
        Node nodeOut = (Node) nodes.get(dest);
        Edge edgeIn = new Edge(nodeIn, nodeOut, w);
        this.edegs.get(src).put(dest, edgeIn);
        this.opostieEdges.get(dest).put(src, edgeIn);
        mc++;
        numberOfEdgs++;
    }

  @Override  // 
    public Iterator<NodeData> nodeIter() {
        return new Iterator<NodeData>() {
            public Iterator<NodeData> iter = Graph.this.nodes.values().iterator();
            public int itMC = getMC();

            @Override
            public boolean hasNext() {
                if (itMC != getMC()) {
                    throw new NoSuchElementException();
                }
                return iter.hasNext();
            }

            @Override
            public NodeData next() {
                if (itMC != getMC()) {
                    throw new NoSuchElementException();
                }
                return iter.next();
            }
        };
    }

    @Override  // 
    public Iterator<EdgeData> edgeIter() {
        int size = this.nodeSize();
        ArrayList<EdgeData> finalList = new ArrayList(size);///////////////*****
        ArrayList<Iterator> iterators = new ArrayList(size);
        Iterator<Integer> idSet = this.nodes.keySet().iterator();
        while (idSet.hasNext()) {        /////////////////
            int current = idSet.next();
            iterators.add(edgeIter(current));
        }
        for (int i = 0; i < iterators.size(); i++) {
            Iterator<EdgeData> current = iterators.get(i);
            while (current.hasNext()) {
                EdgeData currentEdge = current.next();
                finalList.add(currentEdge);
            }
        }
        return new Iterator<EdgeData>() {
            public Iterator<EdgeData> iter = finalList.iterator();
            public int itmc = getMC();
            @Override
            public boolean hasNext() {
                if (itmc != getMC())
                    throw new NoSuchElementException();
                return iter.hasNext();
            }
            @Override
            public EdgeData next() {
                if (itmc != getMC()) {
                    throw new NoSuchElementException();
                }
                return iter.next();
            }
        };
    }
    @Override  //
    public Iterator<EdgeData> edgeIter(int node_id) {
        return new Iterator<EdgeData>() {
          public Iterator<EdgeData> iter = edegs.get(node_id).values().iterator();
           public int fmc = getMC();

            @Override
            public boolean hasNext() {
                if (fmc != getMC()) {
                    throw new NoSuchElementException();
                }
                return iter.hasNext();
            }

            @Override
            public EdgeData next() {
                if (fmc != getMC()) {
                    throw new NoSuchElementException();
                }
                return iter.next();
            }
        };
    }

    @Override
    public NodeData removeNode(int key) {
        NodeData current = this.nodes.remove(key);
        Iterator<Integer> it1 = edegs.get(key).keySet().iterator();
        while (it1.hasNext()) {
            int c = it1.next();
            opostieEdges.get(c).remove(key);
            this.mc++;
            this.numberOfEdgs--;
        }
        this.edegs.remove(key);
        Iterator<Integer> it2 = opostieEdges.get(key).keySet().iterator();
        while (it2.hasNext()) {
            int c = it2.next();
            edegs.get(c).remove(key);
            this.mc++;
            this.numberOfEdgs--;
        }
        opostieEdges.remove(key);
        return current;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        this.edegs.get(src).remove(dest);
        this.opostieEdges.get(dest).remove(src);
        this.mc++;
        this.numberOfEdgs--;
        return null;
    }


    @Override
    public int nodeSize() {
        return this.nodes.size();
    }

    @Override
    public int edgeSize() {
        return numberOfEdgs;
    }

    @Override
    public int getMC() {
        return mc;
    }


    public HashMap<Integer, NodeData> getNodes() {
        return nodes;
    }

    public HashMap<Integer, HashMap<Integer, EdgeData>> getEdges() {
        return edegs;
    }

    public HashMap<Integer, HashMap<Integer, EdgeData>> getOpositeEdges() {
        return opostieEdges;
    }
}



