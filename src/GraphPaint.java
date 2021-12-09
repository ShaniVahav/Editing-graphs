import java.util.*;
import api.*;
import java.awt.*;
import javax.swing.JPanel;
import api.Edge;



public class GraphPaint extends JPanel {

    private static Graph graph;
    private static HashMap<Integer, NodeData> nodes;
    private static HashMap<Integer, HashMap<Integer, EdgeData>> edges;
    private static int padding = 20;
    private static int labelPadding = 12;



    public GraphPaint(Graph g){
        graph = g;
        nodes = graph.getNodes();
        edges = graph.getEdges();
    }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.black);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            double xScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScoreX() - getMinScoreX());
            double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScoreY() - getMinScoreY());

            int x, y;
            g.setColor(Color.red);
            int x2, y2;
            NodeData node_in, node_out;
           for(Integer n : edges.keySet()){
                 for (EdgeData edge : edges.get(n).values()){
                    // edge = edges.get(n).get(e);

                            if (edge == null) continue;

                         node_in = ((Edge) edge).getNode_in();
                         node_out = ((Edge) edge).getNode_out();
                            if (node_in == null || node_out == null) continue;

                         x = (int) ((getMaxScoreX() - node_in.getLocation().x()) * xScale + padding);
                         y = (int) ((getMaxScoreY() - node_in.getLocation().y()) * yScale + padding);

                         x2 = (int) ((getMaxScoreX() - node_out.getLocation().x()) * xScale + padding);
                         y2 = (int) ((getMaxScoreY() - node_out.getLocation().y()) * yScale + padding);

                         g.drawLine(x, y, x2, y2);
                 }
             }

            g.setColor(Color.blue);
            for (int i=0; i<nodes.size(); i++) {
                x = (int) ((getMaxScoreX() - nodes.get(i).getLocation().x()) * xScale + padding);
                y = (int) ((getMaxScoreY() - nodes.get(i).getLocation().y()) * yScale + padding);
                g.fillOval(x, y, 5, 5);
            }

        }

    private double getMaxScoreX() {
        double maxScore = Double.MIN_VALUE;
        for (int i = 0; i < nodes.size(); i++) {
            maxScore = Math.max(maxScore, nodes.get(i).getLocation().x());
        }
        return maxScore;
    }

    private double getMinScoreX() {
        double minScore = Double.MAX_VALUE;
        for (int i = 0; i < nodes.size(); i++){
            minScore = Math.min(minScore, nodes.get(i).getLocation().x());
        }
        return minScore;
    }


    private double getMaxScoreY() {
        double maxScore = Double.MIN_VALUE;
        for (int i = 0; i < nodes.size(); i++) {
            maxScore = Math.max(maxScore, nodes.get(i).getLocation().y());
        }
        return maxScore;
    }

    private double getMinScoreY() {
        double minScore = Double.MAX_VALUE;
        for (int i = 0; i < nodes.size(); i++){
            minScore = Math.min(minScore, nodes.get(i).getLocation().y());
        }
        return minScore;
    }



}


