package api;

import java.io.*;
import java.util.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class GraphAlgorithm implements DirectedWeightedGraphAlgorithms {
    private Graph graph;

    @Override
    public void init(DirectedWeightedGraph g) {
        graph = (Graph) g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        Graph copy = new Graph(graph);
        return copy;
    }


    private void DFS(Graph currentGraph, int v, Boolean[] visited) {
        visited[v] = true;  // Mark the current node as visited

        // Recursive for all the vertices dest to this vertex
        for (Integer node : currentGraph.getEdges().get(v).keySet()) {  // iterate on the DEST node
            if (!visited[node])
                DFS(currentGraph, node, visited);
        }
    }


    @Override
    public boolean isConnected() {
        Boolean[] visited = new Boolean[graph.nodeSize()];
        Boolean[] visited2 = new Boolean[graph.nodeSize()];
        for (int i = 0; i < graph.nodeSize(); i++) {  // Initialize the entire array that no vertex is yet visited
            visited[i] = false;
        }
        DFS(graph, 0, visited);  // DFS traversal starting from first node.

        for (int i = 0; i < graph.nodeSize(); i++) {  // If DFS traversal doesn't visit all vertices, return false
            if (!visited[i])
                return false;
        }
        // Create a reversed graph
        Graph opositeGraph = new Graph(graph.getNodes(), graph.getOpositeEdges(), graph.getEdges());

        for (int i = 0; i < graph.nodeSize(); i++) { // Mark all the vertices as not visited
            visited2[i] = false;
        }

        DFS(opositeGraph, 0,visited2 );

        for (int i = 0; i < graph.nodeSize(); i++) {  // If all vertices are not visited in DFS, return false
            if (!visited2[i])
                return false;
        }
        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        double shortestFromAtoALL[] = dijkstra(src);
        return shortestFromAtoALL[dest];
    }


    public double[] dijkstra(int src){
        int size = this.graph.nodeSize();
        int[] previues = new int[size];
        boolean[] arr = new boolean[size];
        double[] distance = new double[size];
        for (int i = 0; i < size; i++) {
            distance[i] = Double.MAX_VALUE;
            previues[i] = -1;
        }
        PriorityQueue<pair> priorityQueue = new PriorityQueue<pair>(size, new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {
                double distance1 = o1.distanceFromsrc;
                double distance2 = o2.distanceFromsrc;
                return (int) (distance1 - distance2);
            }
        });
        distance[src] = 0;
        pair current = new pair(this.graph.getNode(src), distance[src]);
        priorityQueue.add(current);
        while (!priorityQueue.isEmpty()) {
            current = priorityQueue.poll();
            int CheakingindexOfNode = current.n.getKey();
            arr[CheakingindexOfNode] = true;
            Iterator<Integer> it = this.graph.getEdges().get(CheakingindexOfNode).keySet().iterator();
            while (it.hasNext()) {
                int keyDestId = it.next();
                if (arr[keyDestId] == false) {
                    EdgeData connectingEdge = this.graph.getEdge(CheakingindexOfNode, keyDestId);
                    double currentDis = distance[keyDestId];
                    double possibleDis = (distance[CheakingindexOfNode] + connectingEdge.getWeight());
                    if (currentDis > possibleDis) {
                        distance[keyDestId] = possibleDis;   ////////////  very important
                        previues[keyDestId] = CheakingindexOfNode;
                        pair addToQPair = new pair(this.graph.getNode(keyDestId), distance[keyDestId]);
                        priorityQueue.add(addToQPair);
                    }
                }
            }
        }
        return distance;
    }


    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        int size = this.graph.nodeSize();
        int[] previues = new int[size];
        boolean[] arr = new boolean[size];
        double[] distance = new double[size];
        for (int i = 0; i < size; i++) {
            distance[i] = Integer.MAX_VALUE;
            previues[i] = -1;
        }
        PriorityQueue<pair> priorityQueue = new PriorityQueue<pair>(size, new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {
                double distance1 = o1.distanceFromsrc;
                double distance2 = o2.distanceFromsrc;
                return (int) (distance1 - distance2);
            }
        });
        distance[src] = 0;
        pair current = new pair(this.graph.getNode(0), distance[src]);
        priorityQueue.add(current);
        while (!priorityQueue.isEmpty()) {
            current = priorityQueue.poll();
            int CheakingindexOfNode = current.n.getKey();
            arr[CheakingindexOfNode] = true;
            Iterator<Integer> it = this.graph.getEdges().get(CheakingindexOfNode).keySet().iterator();
            while (it.hasNext()) {
                int keyDestId = it.next();
                if (arr[keyDestId] == false) {
                    EdgeData connectingEdge = this.graph.getEdge(CheakingindexOfNode, keyDestId);
                    double currentDis = distance[keyDestId];
                    double possibleDis = (distance[CheakingindexOfNode] + connectingEdge.getWeight());
                    if (currentDis > possibleDis) {
                        distance[keyDestId] = possibleDis;   ////////////  very important
                        previues[keyDestId] = CheakingindexOfNode;
                        pair addToQPair = new pair(this.graph.getNode(keyDestId), distance[keyDestId]);
                        priorityQueue.add(addToQPair);
                    }
                }
            }
        }
        ArrayList<NodeData> pathList = new ArrayList<NodeData>();
        pathList.add(this.graph.getNode(dest));
        int preNode = previues[dest];
        while (preNode != -1) {
            pathList.add(0, this.graph.getNode(preNode));
            preNode = previues[preNode];
        }
        return pathList;
    }

    @Override
    public NodeData center() {
        HashMap<Integer,NodeData> h = this.graph.getNodes();
        ArrayList<pair> finalList = new ArrayList(this.graph.nodeSize());
        Iterator<Integer> iterator = h.keySet().iterator();
        while(iterator.hasNext()) {
            int current_dex = iterator.next();
            double arr[] = dijkstra(current_dex);
            double max_distance = -Double.MAX_VALUE;
            for (int i = 0; i < arr.length; i++) {
                double currentDis = arr[i];
                if(currentDis ==Double.MAX_VALUE ) {
                    System.out.println(Double.MAX_VALUE);
                    return null;
                }
                if (currentDis > max_distance) {
                    max_distance = currentDis;
                }
            }
            finalList.add(new pair(this.graph.getNode(current_dex),max_distance ));
        }
        double minValue = Double.MAX_VALUE;
        NodeData ans =null;
        for(pair value : finalList){
            if(value.distanceFromsrc < minValue) {
                minValue = value.distanceFromsrc;     ////// distanceFromsrc = maximum distance from value.node= the maximum
                ans = value.n;//// distance of this node
            }
        }
        return ans ;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        Iterator<EdgeData> iteratorOfAllEdges = this.graph.edgeIter();
        int numberOfedgs = this.graph.numberOfEdgs;
        JsonArray jsonEdgeArray = new JsonArray(numberOfedgs);
        while (iteratorOfAllEdges.hasNext()) {
            JsonObject obj1 = new JsonObject();
            EdgeData current = iteratorOfAllEdges.next();
            obj1.addProperty("src", (current.getSrc() + ""));
            obj1.addProperty("dest", (current.getDest() + ""));
            obj1.addProperty("w", (current.getWeight() + ""));
            jsonEdgeArray.add(obj1);
        }
        JsonArray jsonNodeArray = new JsonArray(numberOfedgs);
        Iterator<NodeData> nodeIt = this.graph.getNodes().values().iterator();
        while (nodeIt.hasNext()) {
            JsonObject obj2 = new JsonObject();
            NodeData current = nodeIt.next();
            Location lCurrent = (Location) current.getLocation();
            obj2.addProperty("pos", lCurrent.x() + "," + lCurrent.y() + "," + lCurrent.z() + "");
            obj2.addProperty("id", current.getKey());
            jsonNodeArray.add(obj2);
        }
        JsonObject finalJsonObject = new JsonObject();
        finalJsonObject.add("Edges", jsonEdgeArray);
        finalJsonObject.add("Nodes", jsonNodeArray);
        try (FileWriter realfile = new FileWriter(file)) {
            realfile.write(finalJsonObject.toString());
            realfile.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean load(String file) {
        double x = 0, y = 0;double z = 0;
        int key = 0;
        int src = 0, dest = 0;
        double w = 0;
        Graph a = new Graph();
        File input = new File(file);
        ////        "pos": "35.19589389346247,32.10152879327731,0.0",
        //      "id": 0
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject filObject = fileElement.getAsJsonObject();
            //// for Nodes
            JsonArray jsonArrayOfNodes = filObject.get("Nodes").getAsJsonArray();
            for (JsonElement NodeElement : jsonArrayOfNodes) {
                JsonObject toNode = NodeElement.getAsJsonObject();
                key = toNode.get("id").getAsInt();
                String temp = toNode.get("pos").getAsString();
                String[] arrTemp = temp.split(",");
                x = Double.parseDouble(arrTemp[0]);
                y = Double.parseDouble(arrTemp[1]);
                z = Double.parseDouble(arrTemp[2]);
                Location l = new Location(x, y, z);
                a.addNode(new Node(l, key));
            }
            ///// for edges
            JsonArray jsonArrayOfEdeges = filObject.get("Edges").getAsJsonArray();
            for (JsonElement EdgeElement : jsonArrayOfEdeges) {
                JsonObject toEdge = EdgeElement.getAsJsonObject();
                src = toEdge.get("src").getAsInt();
                w = toEdge.get("w").getAsDouble();
                dest = toEdge.get("dest").getAsInt();
                a.connect(src, dest, w);
            }
            this.graph = a;
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false ;
        }

    }


    public static void main(String[] args) {
        GraphAlgorithm g = new GraphAlgorithm();
        g.load("G1.json");

        NodeData node = g.center();
        System.out.println(node.getKey());
    }



}





