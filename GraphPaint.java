import java.text.DecimalFormat;
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
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            setBackground(Color.black);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            double xScale = ((double) getWidth() - 2 * padding - labelPadding) / (getMaxScoreX() - getMinScoreX());
            double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScoreY() - getMinScoreY());

            int x, y;
            int x2, y2;
            NodeData node_in, node_out;
            g.setColor(Color.red);
            for(Integer n : edges.keySet()){
                for (EdgeData edge : edges.get(n).values()){
                            if (edge == null) continue;

                         node_in = ((Edge) edge).getNode_in();
                         node_out = ((Edge) edge).getNode_out();
                            if (node_in == null || node_out == null) continue;

                         x = (int) ((getMaxScoreX() - node_in.getLocation().x()) * xScale + padding);
                         y = (int) ((getMaxScoreY() - node_in.getLocation().y()) * yScale + padding);

                         x2 = (int) ((getMaxScoreX() - node_out.getLocation().x()) * xScale + padding);
                         y2 = (int) ((getMaxScoreY() - node_out.getLocation().y()) * yScale + padding);

                     g.drawLine(x,y, x2,y2);
                     g.drawString(">",x2-8, y2+7);
                 }
             }

            double d_x, d_y;
           String str;
           for (Integer node : graph.getNodes().keySet()){
                g.setColor(Color.blue);
                d_x = nodes.get(node).getLocation().x();
                d_y = nodes.get(node).getLocation().y();
                x = (int)((getMaxScoreX() - d_x) * xScale + padding);
                y = (int)((getMaxScoreY() - d_y) * yScale + padding);

                g.fillOval(x, y, 5, 5);
                g.setColor(Color.white);
                str = "(" + df.format(d_x) + ", " + df.format(d_y) + ")";
                g.drawString(str,x,y);
            }

        }

    private static final DecimalFormat df = new DecimalFormat("0.00");

    private double getMaxScoreX() {
        double maxScore = Double.MIN_VALUE;
        for (Integer node : graph.getNodes().keySet()){
            maxScore = Math.max(maxScore, nodes.get(node).getLocation().x());
        }
        return maxScore;
    }

    private double getMinScoreX() {
        double minScore = Double.MAX_VALUE;
        for (Integer node : graph.getNodes().keySet()){
            minScore = Math.min(minScore, nodes.get(node).getLocation().x());
        }
        return minScore;
    }


    private double getMaxScoreY() {
        double maxScore = Double.MIN_VALUE;
        for (Integer node : graph.getNodes().keySet()){
            maxScore = Math.max(maxScore, nodes.get(node).getLocation().y());
        }
        return maxScore;
    }

    private double getMinScoreY() {
        double minScore = Double.MAX_VALUE;
        for (Integer node : graph.getNodes().keySet()){
            minScore = Math.min(minScore, nodes.get(node).getLocation().y());
        }
        return minScore;
    }



}


