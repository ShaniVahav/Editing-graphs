# Ex2_OOP
###### Directed weighted graph construction, editing options and GUI


## About the project.
Given a json file, which contains values of vertices and directed and weighted edges, we construct a directed and weighted graph.
There are several editing options on the graph, such as adding / subtracting a vertex or edge, creating a new edge or a new vertex.
In addition to all the edits, various algorithms can be performed on the graph, such as finding the middle vertex,
the shortest path between two vertices, and even the traveling salseman problem (ie: https://en.wikipedia.org/wiki/Travelling_salesman_problem).

## Literature review.

In our project we used several sources, some of which are:
1. https://www.youtube.com/watch?v=EFg3u_E6eHU
2. https://www.geeksforgeeks.org/traveling-salesman-problem-tsp-implementation/
3. https://docs.oracle.com/javase/tutorial/uiswing/components/frame.html

## GUI
In the graphical interface we created a menu with 4 buttons:

First button - "Save / Load" - clicking on it leads to a screen where two buttons appear, of loading or saving Json file only! After pressing one of the buttons, you must access the terminal.

Second button - "Edit" - clicking on it leads to a screen where two buttons, node and Edge, appear, clicking on each of them will lead you to different actions of adding / missing the same item.

Third button - "Algorithms" - clicking on it leads to a screen with a number of different algorithms that can be executed on the graph, those that require data entry it will be executed through the terminal.

Fourth button - "Drawing of the graph" - as its name implies, the drawing of the current graph, after all the edits made on it (if made).

## Class diagram.
    
![image](https://user-images.githubusercontent.com/92265738/145578473-964f71ab-aa2c-4fcf-b09e-4b9ad3ea7a04.png)

## Analyzing the performance of the algorithms:

#### isConnected:
1,000 Vertices 10,000 Edges: 255 ms  
10,000 Vertices 100,000 Edges: 679 ms  
100,000 Vertices 2,000,000 Edges: 9 sec 720 ms  
1,000,000 Vertices 10,000,000 Edges: could not create connected graph, 19 sec 25 ms.  

#### center:
1,000 Vertices 10,000 Edges:1 sec 593 ms  
10,000 Vertices 100,000 Edges: 4 min 20 sec  
100,000 Vertices 1,000,000 Edges: timeout  
1,000,000 Vertices 10,000,000 Edges: timeout  

#### tsp: 
1000 Vertices 10,000 Edges: - 504ms   
10000 Vertices 100,000 Edges - 2 sec 38 ms   
100000 Vertices 1,000,000 Edges- 44 sec 695msc  
1000000 Vertices 10,000,000-timeout

#### shortestPath & shortestPathDist:
1000 Vertices 10,000 Edges- 255 ms  
10000 Vertices-100,000 Edges 679 ms  
100000- Vertices1,000,000 Edges - 9 sec 164   
1,000,000 Vertices 10,000,000 Edges: timeout  

#### copy:
1000 Vertices 10,000 Edges - 235 ms   
10000 Vertices-100,000 Edges - 747 ms  
100000- Vertices1,000,000 Edges 10 sec , 391 ms  






