import api.EdgeData;
import api.Location;
import api.Node;
import api.NodeData;
import api.Graph;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Listener {


    public static class Saveload extends JPanel implements ActionListener {
        Scanner input = new Scanner(System.in);

        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            if (str.equals("Save/Load")) {
                GUI.initsaveload();
            }

            if (str.equals("Load a graph")) {
                System.out.println("Insert json file path:");
                GUI.alg.load(input.next());
                System.out.println("The file was loaded successfully");
            }

            if (str.equals("Save a graph")) {
                System.out.println("Insert file name:");
                GUI.alg.save(input.next());
                System.out.println("The file was saved successfully");
            }
        }

    }


    public static class Edit extends JPanel implements ActionListener {
        Scanner input = new Scanner(System.in);

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
                System.out.println("Enter the node location in which you want its index, in frame '(x, y, z)':");
                String location = input.next();

                for (int i = 0; i < GUI.graph.nodeSize(); i++) {
                    if (GUI.graph.getNode(i).getLocation().toString().equals(location))
                        System.out.println("The key of this node is: " + GUI.graph.getNode(i).getKey());
                    break;
                }
            }

            if (str.equals("getLocation")) {
                System.out.println("Enter the node key in which you want its location:");
                int i = input.nextInt();
                System.out.println("The location of this node is: " + GUI.graph.getNode(i).getLocation().toString());
            }

            if (str.equals(("setLocation"))) {
                System.out.println("Enter the node key in which you want to set its location:");
                int i = input.nextInt();
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
                System.out.println("Enter the destination node of the edge:");
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
                System.out.println("Enter the source node and the destination node of the edge," +
                        "  in the following order: src -> ENTER -> dest:");
                int src = input.nextInt();
                int dest = input.nextInt();

                EdgeData edge = GUI.graph.getEdge(src, dest);
                System.out.println("The edge weight is: " + edge.getWeight());
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
                if (flag) {
                    System.out.println("The graph is connected!");
                } else {
                    System.out.println("The graph is NOT connected!");
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

                frame.setSize(500, 500);
                frame.setVisible(true);
                frame.add(g);
            }
        }
    }
}






