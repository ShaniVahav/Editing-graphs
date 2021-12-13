package api;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphAlgorithmTest {
GraphAlgorithm a ;

@AfterEach
void clean(){
    a = null ;
}


    @org.junit.jupiter.api.Test
    void init() {
        GraphAlgorithm a = new GraphAlgorithm();
        Graph mygraph = new Graph();
        a.init(mygraph);
        assertNotEquals(a.getGraph(),null);
        a = null ;

        }
    @org.junit.jupiter.api.Test
    void getGraph() {
        GraphAlgorithm a = new GraphAlgorithm();
        Graph mygraph = new Graph();
        a.init(mygraph);
       assertEquals(a.getGraph(),mygraph);
       a =null ;
    }

    @org.junit.jupiter.api.Test
    void copy() {
        Node.id = 0 ;
        GraphAlgorithm b = new GraphAlgorithm();
        Graph mygraph = new Graph();
        mygraph.addNode(new Node(new Location(0, 0, 0), 0));
        mygraph.addNode(new Node(new Location(1, 2, 4), 0));
        mygraph.addNode(new Node(new Location(2, 4, 8), 0));
        mygraph.addNode(new Node(new Location(3, 6, 9), 0));
        mygraph.addNode(new Node(new Location(4, 8, 12), 0));
        mygraph.addNode(new Node(new Location(5, 10, 15), 0));
        mygraph.connect(0, 1, 6);
        mygraph.connect(0, 4, 2);
        mygraph.connect(3, 4, 3);
        mygraph.connect(1, 2, 4);
        mygraph.connect(2, 3, 1);
        mygraph.connect(5, 3, 4);
        mygraph.connect(1, 5, 4);
        ArrayList<EdgeData> try1 = new ArrayList();
        ArrayList<EdgeData> try2 = new ArrayList();
        Graph g = mygraph;
        b.init(g);
        Graph copy = (Graph) b.copy();
        Iterator<EdgeData> t2 = b.getGraph().edgeIter();
        Iterator<EdgeData> t = b.getGraph().edgeIter();
        while (t.hasNext()) {
            try1.add(t.next());
        }
        while (t2.hasNext()) {
            try2.add(t2.next());
        }
        for (int i = 0; i < try1.size(); i++) {
            assertEquals(try1.get(i).getSrc(), try2.get(i).getSrc());
            assertEquals(try1.get(i).getDest(), try2.get(i).getDest());
        }
        b =null;
    }
    @org.junit.jupiter.api.Test
    void isConnected() {

        Node.id = 0 ;
        GraphAlgorithm a = new GraphAlgorithm();
        Graph mygraph = new Graph();
        a.init(mygraph);
        mygraph.addNode(new Node(new Location(0, 0, 0), 0));
        mygraph.addNode(new Node(new Location(1, 2, 4), 0));
        mygraph.addNode(new Node(new Location(2, 4, 8), 0));
        mygraph.addNode(new Node(new Location(3, 6, 9), 0));
        mygraph.addNode(new Node(new Location(4, 8, 12), 0));
        mygraph.connect(1, 2, 6);
        mygraph.connect(2, 3, 6);
        mygraph.connect(4, 0, 6);
        mygraph.connect(0, 3, 6);
        mygraph.connect(3, 4, 6);
        mygraph.connect(3, 1, 6);
        assertEquals(a.isConnected(),true);
        a = null;



    }
    @Test
    void shortestPathDist() {
        Node.id = 0 ;
        Graph mygraph= new Graph();
        GraphAlgorithm a = new GraphAlgorithm();
        a.init(mygraph);
        mygraph.addNode(new Node(new Location(0, 0, 0), 0));
        mygraph.addNode(new Node(new Location(1, 2, 4), 0));
        mygraph.addNode(new Node(new Location(2, 4, 8), 0));
        mygraph.addNode(new Node(new Location(3, 6, 9), 0));
        mygraph.addNode(new Node(new Location(4, 8, 12), 0));
        mygraph.addNode(new Node(new Location(5, 10, 15), 0));
        mygraph.connect(2, 3, 9.5);
        mygraph.connect(0, 2, 1);
        mygraph.connect(0, 3, 10);
        mygraph.connect(0, 1, 8);
        mygraph.connect(1, 3, 1.5);
       assertEquals(a.shortestPathDist(0, 3),9.5);
         a = null ;

        }

    @org.junit.jupiter.api.Test
    void shortestPath() {
        Node.id = 0 ;
        Graph mygraph1 = new Graph();
        GraphAlgorithm b1 = new GraphAlgorithm();
        b1.init(mygraph1);
        mygraph1.addNode(new Node(new Location(0,0,0), 0));
        mygraph1.addNode(new Node(new Location(1,2,4), 0));
        mygraph1.addNode(new Node(new Location(2,4,8), 0));
        mygraph1.addNode(new Node(new Location(3,6,9), 0));
        mygraph1.addNode(new Node(new Location(4,8,12), 0));
        mygraph1.addNode(new Node(new Location(5,10,15), 0));
        mygraph1.connect(2,3,9.5);
        mygraph1.connect(0,2,1);
        mygraph1.connect(0,3,10);
        mygraph1.connect(0,1,8);
        mygraph1.connect(1,3,1.5);
        LinkedList<NodeData> A = (LinkedList<NodeData>) b1.shortestPath(0,3);
        int array[] = new int[3];
        array[0] = 0 ;
        array[1] =1;
        array[2] = 3;
        int i = 0;
        for(int j = 0 ; j < A.size(); j++){
            assertTrue(array[j]== A.get(j).getKey());
        }
        b1 = null ;
    }


    @Test
    void center(){
        Node.id = 0 ;
        GraphAlgorithm a = new GraphAlgorithm();
        Graph mygraph = new Graph();
        a.load("G2.json");
        NodeData A = a.center();
        assertTrue(A.getKey()==0);
        a = null ;
    }

    @org.junit.jupiter.api.Test
    void tsp() {
       GraphAlgorithm a = new GraphAlgorithm();
        a.load("G3.json");
        List random = new LinkedList();
        random.add(a.getGraph().getNode(20));
        random.add(a.getGraph().getNode(40));
        random.add(a.getGraph().getNode(28));
        random.add(a.getGraph().getNode(15));
        List<NodeData> B= a.tsp(random);
        a.getGraph().removeNode(30);
        assertTrue(B.contains(a.getGraph().getNode(20)));
        assertTrue(B.contains(a.getGraph().getNode(40)));
        assertTrue(B.contains(a.getGraph().getNode(28)));
        assertTrue(B.contains(a.getGraph().getNode(15)));
        a = null ;

    }

    @org.junit.jupiter.api.Test
    void save() {
        GraphAlgorithm a = new GraphAlgorithm();
        Graph mygraph = new Graph();
        a.load("G1.json");
        Boolean v =a.save("G4.json");
        assertTrue(v==true);

    }


    @org.junit.jupiter.api.Test
    void load() {
        GraphAlgorithm a = new GraphAlgorithm();
        Graph mygraph = new Graph();
        a.load("G1.json");
        assertTrue(a.getGraph().nodeSize() > 0);


    }

}