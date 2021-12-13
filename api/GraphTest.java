package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {


    @Test
    void getNode() {
        Node.id = 0 ;
        Graph a = new Graph();
        NodeData n = new Node(new Location(1, 2, 3), 5);
        a.addNode(n);
        System.out.println(a.getNode(0));
        System.out.println(a.getNode(8));
        assertTrue(a.getNode(0) == n);
    }


    @Test
    void getEdge() {
        Graph a = new Graph();
        NodeData n1 = new Node(new Location(1, 2, 3), 5);
        NodeData n2 = new Node(new Location(4, 6, 9), 5);
        a.addNode(n1);
        a.addNode(n2);
        a.connect(n1.getKey(), n2.getKey(), 3);
        EdgeData c = a.getEdge(n1.getKey(), n2.getKey());
        assertTrue(a.getEdge(n1.getKey(), n2.getKey()).equals(c));
        a = null ;
    }


    @Test
    void addNode() {
        Graph a = new Graph();
        NodeData n1 = new Node(new Location(1, 2, 3), 5);
        NodeData n2 = new Node(new Location(4, 6, 9), 5);
        a.addNode(n1);
        a.addNode(n2);
        assertTrue(a.nodeSize() > 1);
    }


    @Test
    void connect() {
        Graph a = new Graph();
        NodeData n1 = new Node(new Location(1, 2, 3), 5);
        NodeData n2 = new Node(new Location(4, 6, 9), 5);
        a.addNode(n1);
        a.addNode(n2);
        a.connect(n1.getKey(), n2.getKey(), 3);
        assertTrue(a.numberOfEdgs > 0);

    }


    @Test
    void nodeIter() {
        Graph a = new Graph();
        NodeData n1 = new Node(new Location(1, 2, 3), 5);
        NodeData n2 = new Node(new Location(4, 6, 9), 5);
        a.addNode(n1);
        Iterator<NodeData> it = a.nodeIter();
        while (it.hasNext()) {
            NodeData copy = it.next();
            assertTrue(copy.getKey() == n1.getKey());
        }
    }


    @Test
    void edgeIter() {
        Graph a = new Graph();
        Node n1 = new Node(new Location(1, 2, 3), 5);
        Node n2 = new Node(new Location(4, 6, 9), 5);
        Node n3 = new Node(new Location(1, 2, 3), 5);
        Node n4 = new Node(new Location(4, 6, 9), 5);
        a.addNode(n1);
        a.addNode(n2);
        a.addNode(n3);
        a.addNode(n4);
        a.connect(n1.getKey(), n2.getKey(), 3);
        a.connect(n3.getKey(), n4.getKey(), 6);
        Iterator<EdgeData> it = a.edgeIter(n1.getKey());
        assertTrue(a.getEdge(n1.getKey(), n2.getKey()).equals(it.next()));


    }


    @Test
    void testEdgeIter() {
        Graph a = new Graph();
        Node n1 = new Node(new Location(1, 2, 3), 5);
        Node n2 = new Node(new Location(4, 6, 9), 5);
        Node n3 = new Node(new Location(1, 2, 3), 5);
        Node n4 = new Node(new Location(4, 6, 9), 5);
        a.addNode(n1);
        a.addNode(n2);
        a.addNode(n3);
        a.addNode(n4);
        a.connect(n1.getKey(), n2.getKey(), 3);
        a.connect(n3.getKey(), n4.getKey(), 6);
        Iterator<EdgeData> it = a.edgeIter();
        assertTrue(a.getEdge(n1.getKey(), n2.getKey()).equals(it.next()));
        assertTrue(a.getEdge(n3.getKey(), n4.getKey()).equals(it.next()));

    }

    @Test
    void removeNode() {
        Graph a = new Graph();
        Node n2 = new Node(new Location(4, 6, 9), 5);
        Node n3 = new Node(new Location(1, 2, 3), 5);
        Node n4 = new Node(new Location(4, 6, 9), 5);
        a.addNode(n2);
        a.addNode(n3);
        a.addNode(n4);
        a.removeNode(n2.getKey());
        assertTrue(a.nodeSize() == 2);
    }


        @Test
        void removeEdge () {
            Graph a = new Graph();
            Node n1 = new Node(new Location(1, 2, 3), 5);
            Node n2 = new Node(new Location(4, 6, 9), 5);
            Node n3 = new Node(new Location(1, 2, 3), 5);
            Node n4 = new Node(new Location(4, 6, 9), 5);
            a.addNode(n1);
            a.addNode(n2);
            a.addNode(n3);
            a.addNode(n4);
            a.connect(n1.getKey(), n2.getKey(), 3);
            a.connect(n3.getKey(), n4.getKey(), 6);
            a.removeEdge(n1.getKey(), n2.getKey());
            assertTrue(a.numberOfEdgs==1);
        }

        @Test
        void nodeSize () {
            Graph a = new Graph();
            Node n1 = new Node(new Location(1, 2, 3), 5);
            Node n2 = new Node(new Location(4, 6, 9), 5);
            Node n3 = new Node(new Location(1, 2, 3), 5);
            Node n4 = new Node(new Location(4, 6, 9), 5);
            a.addNode(n1);
            a.addNode(n2);
            a.addNode(n3);
            a.addNode(n4);
            assertTrue(a.nodeSize()==4);
        }

        @Test
        void edgeSize () {
            Graph a = new Graph();
            Node n1 = new Node(new Location(1, 2, 3), 5);
            Node n2 = new Node(new Location(4, 6, 9), 5);
            Node n3 = new Node(new Location(1, 2, 3), 5);
            Node n4 = new Node(new Location(4, 6, 9), 5);
            a.addNode(n1);
            a.addNode(n2);
            a.addNode(n3);
            a.addNode(n4);
            a.connect(n1.getKey(), n2.getKey(), 3);
            a.connect(n3.getKey(), n4.getKey(), 6);
            assertTrue(a.edgeSize() ==2);
        }

        @Test
        void getMC () {
            Graph a = new Graph();
            Node n1 = new Node(new Location(1, 2, 3), 5);
            Node n2 = new Node(new Location(4, 6, 9), 5);
            Node n3 = new Node(new Location(1, 2, 3), 5);
            Node n4 = new Node(new Location(4, 6, 9), 5);
            a.addNode(n1);
            a.addNode(n2);
            a.addNode(n3);
            a.addNode(n4);
            a.connect(n1.getKey(), n2.getKey(), 3);
            a.connect(n3.getKey(), n4.getKey(), 6);
            a.removeEdge(n1.getKey(), n2.getKey());
            assertTrue(a.getMC() == 3);
        }
    }


