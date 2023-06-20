# Dijktra-s-Algorithm
implement Dijktra's Shortest Path algoritm to to calculate the distance between 2 nodes.





Given the graph below, implement Dijktra's Shortest Path algoritm to to calculate the distance between 2 nodes.
Your java program must be able to take 2 arguments from the command line arguments, and return the distance.
Make a zip of your project and provide the compiled class files as well as source files.

I'll be running the program as follows:
java DijkstraShortestPath.class A D

Create the output in the following format:

Shortest path between A and D:
Shortest path: A-B-D
Distance: 5

If there is no path, the program should output the following:

There is no path between A and D.

A P
B Z
C S
D X
E G
F Y
G K
H M
I T
L R

----

Graph:
       A---3---B
      /       / \
     4       2   5
    /       /     \
   C---5---D---1---E
  / \     / \     / \
 6   2   3   4   2   3
  |   |   |   |   |   |
  F-4-G-2-H   I   J-2-K
   \ /     \ /     \ /
    5       3       4
    |       |       |
    L---2---M---6---N
   / \     / \     / \
  4   3   2   5   2   1
  |   |   |   |   |   |
  O   P   Q-1-R   S-3-T
   \ /     \ /     \ /
    3       4       2
    |       |       |
    U---3---V---5---W
     \     / \     /
      2   4   1   3
       \ /     \ /
        X---2---Y
         \     /
          4	  3
           \ /
		        Z
