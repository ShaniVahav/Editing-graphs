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


    private void BFS(Graph currentGraph, int v, Boolean[] visited) {
        Queue<Integer> list = new LinkedList<>();
        visited[v] = true;
        list.add(v);
        while (!list.isEmpty()) {
            int x = list.poll();
            Iterator<Integer> it = currentGraph.getEdges().get(x).keySet().iterator();
            while (it.hasNext()) {
                int dest = it.next();
                if (visited[dest] == false) {
                    visited[dest] = true;
                    list.add(dest);
                }
            }

        }

    }

    @Override
    public boolean isConnected() {
        Boolean[] visited = new Boolean[graph.nodeSizeIncludeDelete];
        for(int i = 0 ; i <visited.length; i ++){
            visited[i] = true;
        }
        int index = 0 ;
        Iterator<Integer> it = this.graph.getNodes().keySet().iterator(); {
                                                           // Initialize the entire array that no vertex is yet visited
             index = it.next();
            visited[index] = false;
        }
        Boolean[] visited2 = visited.clone();


        BFS(graph, index, visited);  // DFS traversal starting from first node.

        for (int i = 0; i < graph.nodeSize(); i++) {  // If DFS traversal doesn't visit all vertices, return false
            if (!visited[i])
                return false;
        }
        // Create a reversed graph
        Graph opositeGraph = new Graph(graph.getNodes(), graph.getOpositeEdges(), graph.getEdges());
        BFS(opositeGraph, index, visited2);

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


    public double[] dijkstra(int src) {
        int size = this.graph.nodeSizeIncludeDelete;
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
        int size = this.graph.nodeSizeIncludeDelete;
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
        pair current = new pair(this.graph.getNode(src), distance[src]);  ////////// change withput cheaking
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
        LinkedList<NodeData> pathList = new LinkedList<>();
        pathList.add(this.graph.getNode(dest));
        int preNode = previues[dest];
        while (preNode != -1) {
            pathList.add(0, this.graph.getNode(preNode));
            preNode = previues[preNode];
        }
        return pathList;
    }

  public NodeData center() {
        if( !this.isConnected())
            return null;
        HashMap<Integer, NodeData> h = this.graph.getNodes();
        ArrayList<pair> finalList = new ArrayList(this.graph.nodeSize());
        Iterator<Integer> iterator = h.keySet().iterator();
        while (iterator.hasNext()) {
            int current_dex = iterator.next();
            double arr[] = dijkstra(current_dex);
            double max_distance = -Double.MAX_VALUE;
            for (int i = 0; i < arr.length; i++) {
                double currentDis = arr[i];
                if (currentDis > max_distance && currentDis !=Double.MAX_VALUE) {
                    max_distance = currentDis;
                }
            }
            finalList.add(new pair(this.graph.getNode(current_dex), max_distance));
        }
        double minValue = Double.MAX_VALUE;
        NodeData ans = null;
        for (pair value : finalList) {
            if (value.distanceFromsrc < minValue) {
                minValue = value.distanceFromsrc;     ////// distanceFromsrc = maximum distance from value.node= the maximum
                ans = value.n;//// distance of this node
            }
        }
        return ans;
    }




    public int findMinIndexAllGraph(double[] doubleArr, boolean boolArr[]) {
        double lowestValue = Double.MAX_VALUE;
        int lowestIndexAllGraph = 0;
        for (int k = 0; k < doubleArr.length; k++) {
            this.graph.getNodes().get(k);
            int currentTag = this.graph.getNodes().get(k).getTag();
            if (doubleArr[k] != 0 && doubleArr[k] < lowestValue && currentTag > -1 && boolArr[currentTag] == false) {
                lowestValue = doubleArr[k];
                lowestIndexAllGraph = k;
            }
        }
        return lowestIndexAllGraph;
    }

    public NodeData randomFromArray(ArrayList<NodeData> allCitits) {
        int rnd = (int) (Math.random() * allCitits.size());
        NodeData choosen = allCitits.get(rnd);
        return choosen;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        int times = 0;
        double sumAns = Double.MAX_VALUE;
        ArrayList<NodeData> allCitits = new ArrayList(cities.size());
        ArrayList<NodeData> ansList = new ArrayList(cities.size());
        Iterator<NodeData> it = cities.iterator();
        int counter = 0;
        while (it.hasNext()) {
            NodeData current = it.next();
            current.setTag(counter);
            counter++;
            allCitits.add(current);
        }
        while (times < 10) {
            ArrayList<NodeData> cList = new ArrayList(cities.size());
            double sum = 0;
            boolean[] boolArray = new boolean[cities.size()];
            NodeData choosen = randomFromArray(allCitits);
            int choosenTagIndex = choosen.getTag();
            double[] arrDistance = this.dijkstra(choosen.getKey());
            boolArray[choosenTagIndex] = true;
            cList.add(choosen);
            for (int i = 0; i < boolArray.length - 1; i++) {   ////////  create the list
                int lowestIdDistAllGraph = findMinIndexAllGraph(arrDistance, boolArray);
                NodeData NodeAddToAns = this.graph.getNodes().get(lowestIdDistAllGraph);
                int lowestIndexAllCitis = NodeAddToAns.getTag();
                boolArray[lowestIndexAllCitis] = true;
                cList.add(NodeAddToAns);
                sum += arrDistance[lowestIdDistAllGraph];
                if (i < boolArray.length - 2)
                    arrDistance = this.dijkstra(lowestIdDistAllGraph);
            }
            if (sumAns > sum) {
                sumAns = sum;
                ansList = cList;
            }
            times++;
        }
        return ansList;
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
        double x = 0, y = 0;
        double z = 0;
        int key = 0;
        int src = 0, dest = 0;
        double w = 0;
        Graph a = new Graph();
        File input = new File(file);
        ////
        //
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
            return false;
        }

    }
////////////////////////////////////////// no use minWhile
    public double dijkstraSingleNode(int src) {
        double maximum = 0;
        int size = this.graph.nodeSize();
        int[] previues = new int[size];
        boolean[] arr = new boolean[size];
        double[] distance = new double[size];
        for (int i = 0; i < size; i++) {
            distance[i] = Double.MAX_VALUE;
            previues[i] = -1;
        }
        PriorityQueue<pair> priorityQueue = new PriorityQueue<pair>(size, (o1, o2) -> {
            double distance1 = o1.distanceFromsrc;
            double distance2 = o2.distanceFromsrc;
            return (int) (distance1 - distance2);
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
                        if (distance[keyDestId] > maximum && distance[keyDestId] != Double.MAX_VALUE) {
                            maximum = distance[keyDestId];
                        }
                    }

                }
            }
        }
        return  maximum;
    }
}






