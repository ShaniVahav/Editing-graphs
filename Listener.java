import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import api.Graph;

public class Listener {
        private static JTextField text = new JTextField();


    public static class Saveload extends JPanel implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            if (str.equals("Save/Load")) {
                GUI.initsaveload();
            }

            if (str.equals("Load a graph")) {
                JFrame frame = new JFrame();
                Container panel = new Container();
                frame.setSize(600, 150);

                JLabel lable = new JLabel("Enter the Json file name:");
                lable.setBounds(30, 50, 500, 20);
                panel.add(lable);

                text.setLocation(210, 50);
                text.setSize(150, 30);
                panel.add(text);

                JButton submit = new JButton("Load");
                submit.addActionListener(this);
                submit.setLocation(390, 50);
                submit.setSize(150, 30);
                panel.add(submit);

                frame.add(panel);
                frame.setVisible(true);
            }

            if (str.equals("Load")) {
                    DirectedWeightedGraphAlgorithms graph = new GraphAlgorithm();
                    boolean flag = graph.load(text.getText());
                    GUI.alg = graph;
                    GUI.graph = graph.getGraph();

                JFrame frame = new JFrame();
                Container panel = new Container();
                frame.setSize(600, 150);
                    if (flag){
                        frame.add(new GUI.LoadSuccessPaint());
                    }
                    else{
                        frame.add(new GUI.LoadFailPaint());
                    }
                frame.setVisible(true);
            }


            if (str.equals("Save the graph")) {
                JFrame frame = new JFrame();
                Container panel = new Container();
                frame.setSize(600, 150);

                JLabel lable = new JLabel("Enter a name for the file:");
                lable.setBounds(30, 50, 500, 20);
                panel.add(lable);

                text.setLocation(210, 50);
                text.setSize(150, 30);
                panel.add(text);

                JButton submit = new JButton("Save");
                submit.addActionListener(this);
                submit.setLocation(390, 50);
                submit.setSize(150, 30);
                panel.add(submit);

                JLabel l = new JLabel("NOTE: You will see the graph json file only after you exit the GUI");
                l.setBounds(30,80,600,30);
                panel.add(l);

                frame.add(panel);
                frame.setVisible(true);
            }

            if (str.equals("Save")){
                boolean flag = GUI.alg.save(text.getText());

                JFrame frame = new JFrame();
                Container panel = new Container();
                frame.setSize(600, 150);
                if (flag){
                    frame.add(new GUI.SaveSuccessPaint());
                }
                else{
                    frame.add(new GUI.SaveFailPaint());
                }
                frame.setVisible(true);
            }
        }

    }


    public static class Edit extends JPanel implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();

            if (str.equals("Edit")) {
                GUI.initEdit();
            }

            if (str.equals("Node")) {
                GUI.initNode();
            }

            if (str.equals("Edge")) {
                GUI.initEdge();
            }
        }
    }

    public static class Nodeinfo extends JPanel implements ActionListener {
        Scanner input = new Scanner(System.in);

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();

            if (str.equals("Create new node")) {
                System.out.println("Enter the node location, in the following order: x -> ENTER -> y");
                double x = input.nextDouble();
                double y = input.nextDouble();
                Location l = new Location(x, y, 0);
                NodeData node = new Node(l, 0);
                GUI.graph.addNode(node);
                System.out.println("The node created successfully");
            }

            if (str.equals("Actions on an existing node")) {
                GUI.initExistNode();
            }
        }
    }


    public static class NodeActions extends JPanel implements ActionListener {
        Scanner input = new Scanner(System.in);

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();

            if (str.equals("getKey")) {
                JFrame frame = new JFrame();
                Container panel = new Container();
                frame.setSize(900, 150);

                JLabel lable = new JLabel("Enter the node location in which you want its key, in frame '(x, y, z)':");
                lable.setBounds(30, 50, 500, 20);
                panel.add(lable);

                text.setLocation(430, 50);
                text.setSize(150, 30);
                panel.add(text);

                JButton submit = new JButton("Get Key");
                submit.setLocation(600, 50);
                submit.setSize(150, 30);
                submit.addActionListener(this);
                panel.add(submit);

                frame.add(panel);
                frame.setVisible(true);
            }
            if (str.equals("Get Key")){
                String location = text.getText();

                for (NodeData n : ((Graph) GUI.graph).getNodes().values()){
                    if (getLocation().toString().equals(location)) {
                        System.out.println("The key of this node is: " + n.getKey());
                        break;
                    }
                }
            }

            if (str.equals("getLocation")) {
                JFrame frame = new JFrame();
                Container panel = new Container();
                frame.setSize(750, 150);

                JLabel lable = new JLabel("Enter the node key in which you want its location:");
                lable.setBounds(30, 50, 500, 20);
                panel.add(lable);

                text.setLocation(350, 50);
                text.setSize(150, 30);
                panel.add(text);

                JButton submit = new JButton("Get Location");
                submit.setLocation(530, 50);
                submit.setSize(150, 30);
                submit.addActionListener(this);
                panel.add(submit);

                frame.add(panel);
                frame.setVisible(true);
            }

            if (str.equals("Get Location")){
                int key = Integer.parseInt(text.getText());
                System.out.println("The location of this node is: "
                        + GUI.graph.getNode(key).getLocation().toString());
            }

            if (str.equals(("setLocation"))) {
                JFrame frame = new JFrame();
                Container panel = new Container();
                frame.setSize(750, 200);

                JLabel lable = new JLabel("Enter the node key in which you want to set its location, " +
                        "then go to the terminal to continue.");
                lable.setBounds(50, 50, 700, 20);
                panel.add(lable);

                text.setLocation(120, 100);
                text.setSize(150, 30);
                panel.add(text);

                JButton submit = new JButton("Set Location");
                submit.setLocation(300, 100);
                submit.setSize(150, 30);
                submit.addActionListener(this);
                panel.add(submit);

                frame.add(panel);
                frame.setVisible(true);
            }

            if (str.equals("Set Location")){
                int i = Integer.parseInt(text.getText());
                NodeData node = GUI.graph.getNode(i);
                System.out.println("Enter the location, in the following order: x -> ENTER -> y");
                double x = input.nextDouble();
                double y = input.nextDouble();
                Location l = new Location(x, y, 0);
                node.setLocation(l);
                System.out.println("Location updated successfully");
            }
        }
    }


    public static class EdgeActions extends JPanel implements ActionListener {
        Scanner input = new Scanner(System.in);
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();

            if (str.equals("getSrc")) {
                System.out.println("Enter the key destination node of the edge:");
               int dest = input.nextInt();
                HashMap<Integer, EdgeData> h = ((Graph) GUI.graph).getOpositeEdges().get(dest);

                System.out.println("Edges whose destination is node " + dest + " their sources are nodes:");
                StringBuilder src = new StringBuilder();
                for (Integer num : h.keySet()) {
                    src.append(", ").append(num);
                }
                System.out.println(src);
            }


            if (str.equals("getDest")) {
                JFrame frame = new JFrame();
                frame.setSize(600, 150);
                frame.setVisible(true);
                GUI.GoTerminal g = new GUI.GoTerminal();
                frame.add(g);





                System.out.println("Enter the source node of the edge:");
                int src = input.nextInt();
                HashMap<Integer, EdgeData> h = ((Graph) GUI.graph).getEdges().get(src);

                System.out.println("Edges whose source is node " + src + " their destinations are nodes:");
                StringBuilder dest = new StringBuilder();
                for (Integer num : h.keySet()) {
                    dest.append(", ").append(num);
                }
                System.out.println(dest);
            }


            if (str.equals("getWeight")) {
                System.out.println("Enter the key source node and the key destination node of the edge," +
                        "  in the following order: src -> ENTER -> dest:");
                int src = input.nextInt();
                int dest = input.nextInt();

                EdgeData edge = GUI.graph.getEdge(src, dest);
                System.out.println("The edge weight is: " + edge.getWeight());
            }

            if (str.equals("connect")) {
                System.out.println("Enter the key source node and the key destination node of the edge you wanna add," +
                        "  in the following order: src -> ENTER -> dest:");
                int src = input.nextInt();
                int dest = input.nextInt();
                GeoLocation lsrc = GUI.graph.getNode(src).getLocation();
                GeoLocation ldest = GUI.graph.getNode(dest).getLocation();
                double w = lsrc.distance(ldest);
                GUI.graph.connect(src, dest, w);
                System.out.println("The edge was added successfully");
            }
        }
    }

    public static class Algo extends JPanel implements ActionListener {
        Scanner input = new Scanner(System.in);

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            if (str.equals("Algorithms")) {
                GUI.initAlgo();
            }


            if (str.equals("isConnected")) {
                boolean flag = GUI.alg.isConnected();
                JFrame frame = new JFrame("Graph Drawing");
                frame.setSize(150, 100);
                frame.setVisible(true);

                if (flag) {
                    System.out.println("The graph is connected!");
                    GUI.TruePaint ans = new GUI.TruePaint();
                    frame.add(ans);
                } else {
                    System.out.println("The graph is NOT connected!");
                    GUI.FalsePaint ans = new GUI.FalsePaint();
                    frame.add(ans);
                }
            }

            if (str.equals("shortestPathDist")) {
                System.out.println("Enter the source node and the destination node, " +
                        "in the following order: src -> ENTER -> dest:");
                int src = input.nextInt();
                int dest = input.nextInt();

                double ans = GUI.alg.shortestPathDist(src, dest);
                System.out.println("The shortest path distance from " + src + " to " + dest + " is: " + ans);
            }

            if (str.equals("shortestPath")) {
                System.out.println("Enter the source node and the destination node, " +
                        "in the following order: src -> ENTER -> dest:");
                int src = input.nextInt();
                int dest = input.nextInt();

                List<NodeData> ans = GUI.alg.shortestPath(src, dest);
                System.out.println("The shortest path from " + src + " to " + dest + " is:");

                StringBuilder nodes = new StringBuilder();

                for (NodeData an : ans) {
                    nodes.append(" -> ").append(an);
                }

                System.out.println(nodes);
            }

            if (str.equals("center")) {
                JFrame frame = new JFrame("Graph Drawing");
                frame.setSize(150, 100);
                frame.setVisible(true);
                GUI.CenterPaint c = new GUI.CenterPaint();
                frame.add(c);

                NodeData node = GUI.alg.center();
                System.out.println("The center node is: " + node.getKey());
            }

            if (str.equals("tsp")) {
                int i;
                List<NodeData> cities = null;

                System.out.println("Enter indexes of nodes you want to test their TSP, " +
                        "after EACH ENTRY of an index press 'Enter', to finish enter -1:");

                i = input.nextInt();
                while (i != -1){
                    cities.add(GUI.graph.getNode(i));
                    i = input.nextInt();
                }

                List<NodeData> ans = GUI.alg.tsp(cities);

                StringBuilder tspNode = new StringBuilder();
                for (NodeData an : ans) {
                    tspNode.append(" -> ").append(an.getKey());
                }
                System.out.println("The TSP is:");
                System.out.println(tspNode);
            }

        }


    }

    public static class Paint implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();

            if (str.equals("Drawing of the graph")){

                GraphPaint g = new GraphPaint((Graph) GUI.graph);

                JFrame frame = new JFrame("Graph Drawing");

                frame.setSize(600, 600);
                frame.setVisible(true);
                frame.add(g);
            }
        }
    }


    public static void Note(){
        JFrame frame = new JFrame();
        Container c = new Container();
        JLabel l = new JLabel("NOTE: After clicking a button in this window you" +
                " must access the terminal to continue the process!");
        l.setBounds(70,70,30,30);
        frame.setSize(600, 150);
        frame.setVisible(true);
        c.add(l);
        frame.add(c);
    }


}






