import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.Graph;

import java.awt.*;
import javax.swing.*;

public class GUI extends JPanel {

    protected static JFrame frame;
    protected static DirectedWeightedGraphAlgorithms alg;
    protected static DirectedWeightedGraph graph;
    protected static Container panel = new Container();
    protected static Container panelCont = new Container();
    protected static CardLayout c = new CardLayout();


    public GUI(DirectedWeightedGraphAlgorithms algo) {
        alg = algo;
        graph = algo.getGraph();

        frame = new JFrame("Graph System");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initMenu();
        panelCont = frame.getContentPane();
        panelCont.setLayout(c);
        frame.setLayout(c);
        frame.setVisible(true);
    }


    private void initMenu() {
        JMenu fileMenu = new JMenu("Menu");
        JMenuItem item1 = new JMenuItem("Save/Load");
        JMenuItem item2 = new JMenuItem("Edit");
        JMenuItem item3 = new JMenuItem("Algorithms");
        JMenuItem graphPaint = new JMenuItem("Drawing of the graph");

        item1.addActionListener(new Listener.Saveload());
        item2.addActionListener(new Listener.Edit());
        item3.addActionListener(new Listener.Algo());
        graphPaint.addActionListener(new Listener.Paint());

        fileMenu.add(item1);
        fileMenu.add(item2);
        fileMenu.add(item3);
        fileMenu.add(graphPaint);

        JMenuBar menubar = new JMenuBar();
        menubar.add(fileMenu);
        frame.setJMenuBar(menubar);
    }


    protected static void initsaveload() {
        panel = new Container();

        JButton button1 = new JButton("Load a graph");
        button1.setLocation(30, 30);
        button1.setSize(150, 100);
        button1.addActionListener(new Listener.Saveload());

        JButton button2 = new JButton("Save a graph");
        button2.setLocation(210, 30);
        button2.setSize(150, 100);
        button2.addActionListener(new Listener.Saveload());

        panel.add(button1);
        panel.add(button2);

        panelCont.add(panel, "0");
        panelCont.add(panel, "0");
        c.show(panelCont, "0");
    }

    public static void initEdit() {
        panel = new Container();
        JButton button1 = new JButton("Node");
        button1.setLocation(30, 30);
        button1.setSize(150, 100);
        button1.addActionListener(new Listener.Edit());

        JButton button2 = new JButton("Edge");
        button2.setLocation(210, 30);
        button2.setSize(150, 100);
        button2.addActionListener(new Listener.Edit());

        panel.add(button1);
        panel.add(button2);

        panelCont.add(panel, "0");
        c.show(panelCont, "0");
    }

    public static void initNode() {
        panel = new Container();
        JButton button1 = new JButton("Create new node");
        button1.setLocation(30, 30);
        button1.setSize(150, 100);
        button1.addActionListener(new Listener.Nodeinfo());

        JButton button2 = new JButton("Actions on an existing node");
        button2.setLocation(210, 30);
        button2.setSize(200, 100);
        button2.addActionListener(new Listener.Nodeinfo());

        panel.add(button1);
        panel.add(button2);

        panelCont.add(panel, "0");
        c.show(panelCont, "0");
    }

    public static void initExistNode() {
        panel = new Container();
        JButton button1, button2, button3;
        button1 = new JButton("getKey");
        button1.setLocation(30, 30);
        button1.setSize(150, 100);
        button1.addActionListener(new Listener.NodeActions());

        button2 = new JButton("getLocation");
        button2.setLocation(210, 30);
        button2.setSize(150, 100);
        button2.addActionListener(new Listener.NodeActions());

        button3 = new JButton("setLocation");
        button3.setLocation(390, 30);
        button3.setSize(150, 100);
        button3.addActionListener(new Listener.NodeActions());

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        panelCont.add(panel, "0");
        c.show(panelCont, "0");
    }


    public static void initEdge() {
        panel = new Container();
        JButton button1 = new JButton("getSrc");
        button1.setLocation(30, 30);
        button1.setSize(150, 100);
        button1.addActionListener(new Listener.EdgeActions());

        JButton button2 = new JButton("getDest");
        button2.setLocation(210, 30);
        button2.setSize(150, 100);
        button2.addActionListener(new Listener.EdgeActions());

        JButton button3 = new JButton("getWeight");
        button3.setLocation(390, 30);
        button3.setSize(150, 100);
        button3.addActionListener(new Listener.EdgeActions());

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        panelCont.add(panel, "0");
        c.show(panelCont, "0");
    }


    public static void initAlgo() {
        panel = new Container();
        JButton button1, button2, button3, button4, button5;

        button1 = new JButton("isConnected");
        button1.setLocation(30, 30);
        button1.setSize(150, 100);
        button1.addActionListener(new Listener.Algo());

        button2 = new JButton("shortestPathDist");
        button2.setLocation(210, 30);
        button2.setSize(150, 100);
        button2.addActionListener(new Listener.Algo());

        button3 = new JButton("shortestPath");
        button3.setLocation(390, 30);
        button3.setSize(150, 100);
        button3.addActionListener(new Listener.Algo());

        button4 = new JButton("center");
        button4.setLocation(120, 160);
        button4.setSize(150, 100);
        button4.addActionListener(new Listener.Algo());

        button5 = new JButton("tsp");
        button5.setLocation(300, 160);
        button5.setSize(150, 100);
        button5.addActionListener(new Listener.Algo());


        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);

        panelCont.add(panel, "0");
        c.show(panelCont, "0");
    }

}


