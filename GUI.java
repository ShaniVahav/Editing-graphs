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
        frame.add(new Welcome());
     //   frame.getContentPane().setBackground(new Color(130, 207, 226, 139));
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

        JButton button2 = new JButton("Save the graph");
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

        JLabel l = new JLabel("^^^");
        JLabel l1 = new JLabel("NOTE: After clicking on");
        JLabel l2 = new JLabel("this button you must");
        JLabel l3 = new JLabel("access the terminal to continue!");

        l.setBounds(90,120,400,50);
        l1.setBounds(30,130,400,50);
        l2.setBounds(30, 150,400, 50);
        l3.setBounds(30,170,400,50);
        panel.add(l);
        panel.add(l1);
        panel.add(l2);
        panel.add(l3);

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

        JButton button4 = new JButton("connect");
        button4.setLocation(570, 30);
        button4.setSize(150, 100);
        button4.addActionListener(new Listener.EdgeActions());

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        JLabel l = new JLabel("NOTE: After clicking a button in this window you"
        + " must access the terminal to continue!");
        l.setBounds(30,150,600,30);
        panel.add(l);

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

        JLabel l = new JLabel("NOTE: After clicking a button in this window " +
                "(excluding isConnected() and center()),");
        JLabel l2 = new JLabel(" you must access the terminal to continue!");
        l.setBounds(30,280,600,30);
        l2.setBounds(135, 297, 600, 30);
        panel.add(l);
        panel.add(l2);

        panelCont.add(panel, "0");
        c.show(panelCont, "0");
    }




    public static class TruePaint extends JPanel{
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.blue);
            int w = getWidth();
            int h = getHeight();
            g.setFont((new Font("Arial", Font.PLAIN, 24)));
            g.drawString("True", w/2, h/2);
        }
    }

    public static class FalsePaint extends JPanel{
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.red);
            int w = getWidth();
            int h = getHeight();
            g.setFont((new Font("Arial", Font.PLAIN, 24)));
            g.drawString("False", w/2, h/2);
        }
    }

    public static class CenterPaint extends JPanel{
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.blue);
            int w = getWidth();
            int h = getHeight();
            g.setFont((new Font("Arial", Font.PLAIN, 24)));
            int c = alg.center().getKey();
            String ans = String.valueOf(c);
            g.drawString(ans, w/2, h/2);
        }
    }

    public static class SaveSuccessPaint extends JPanel{
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.blue);
            int w = getWidth();
            int h = getHeight();
            g.setFont((new Font("Arial", Font.PLAIN, 24)));
            String str = "The graph was saved successfully";
            g.drawString(str, 100, h/2);
        }
    }


    public static class SaveFailPaint extends JPanel{
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.red);
            int w = getWidth();
            int h = getHeight();
            g.setFont((new Font("Arial", Font.PLAIN, 24)));
            String str = "Failed to save graph!";
            g.drawString(str, 170, h/2);
        }
    }

    public static class LoadSuccessPaint extends JPanel{
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.blue);
            int w = getWidth();
            int h = getHeight();
            g.setFont((new Font("Arial", Font.PLAIN, 24)));
            String str = "The graph loaded successfully";
            g.drawString(str, 110, h/2);
        }
    }

    public static class LoadFailPaint extends JPanel{
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.red);
            int w = getWidth();
            int h = getHeight();
            g.setFont((new Font("Arial", Font.PLAIN, 24)));
            String str = "Failed to load the graph!";
            g.drawString(str, 150, h/2);
        }
    }



    public static class GoTerminal extends JPanel{
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.red);
            int w = getWidth();
            int h = getHeight();
            g.setFont((new Font("Arial", Font.BOLD, 24)));
            String ans = "Go to the terminal to continue";
            g.drawString(ans, 110, h/2);
        }
    }

    public static class Welcome extends JPanel{
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(12, 159, 196, 139));
            int w = getWidth();
            int h = getHeight();
            g.setFont((new Font("Arial", Font.BOLD, 50)));
            String a1 = "Welcome to our first GUI!";
            String a2 = "We hope you'll enjoy ^_^";
            g.drawString(a1, w/2-300, (h/2)-100);
            g.setFont((new Font("Bierstadt Display", Font.BOLD, 35)));
            g.drawString(a2, w/2-210, (h/2));
        }
    }

}


