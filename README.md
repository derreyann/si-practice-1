Erasmus students : Giorgi Odishvili - Yann Derré

# Practice 1 - Intelligent Systems

## Context of the program

The aim of the practice was to apply the search algorithms seen in class in a real project, using Java. We&#39;ll be using Graph Search, Depth-first Search, Breadth-first, and finally a heuristic approach

The project has been categorized in packages for easier reading.

## Exercise 1

1. Vacuum Problem

The vacuum cleaner is designed to clean the surrounding dirt until clean, and thus need a search strategy.

The node class contains the attributes of state, parent and action as described in class :

The Strategy4 records the explored states as Nodes. Our implementation uses a stack to add the explored nodes as they go, using push to add a node which state isn&#39;t the explored array.


The reconstruct\_sol implementation seen in class takes in parameters that stack of explored nodes, and return an array of nodes.

However, Strategy4 is limited, and fails if a state has no successors. We can overcome this by using a graph search

Our graph search implementation creates a frontier to store the explorable nodes.

The Nodes get added to the current frontier, using a stack and the pop function, for every available action, we check is the the state is a goal, is explored and if the current state is not in the frontier. If it is, it get pushed out the frontier. If the frontier is empty, we handle the error with an IllegalStateException.

If a solution is found, the node is sent to the reconstruct\_sol.

## Exercise 2

The second exercise is solving a magic square, from a partially given square; meaning the sum of every number each line, column or diagonal is equal to the formula

We create a Board entity class to define the square, as well as MagicSquareProblem defining the State and Action that we&#39;ll be using to solve the problem.

The action attribute contains the coordinates x,y as well as the cell number

The implementation of the class takes care the applicability of a found solution, though the function _isApplicable_, checking if a cell is in bound for a given state, as well as filling the board through _applyTo_, matching a board with a given state as a parameter to an empty cell.

We created a Search Strategy helper, storing the reconstruct\_sol as well as the successor functions shared by the algorithms in that second exercise.

1. Search algorithm
  1. Breadth-First ![](RackMultipart20220321-4-1d3zek0_html_261473c73baf15f2.png)

We first started by the BDS, an approach searching every node level by level until encountering a goal state.

We followed the algorithm made in class. Our implementation uses a queue (FIFO) as frontier, and check if the frontier is empty. We add the last the last element state to the explored ArrayList. We check for all available actions applied to the successor of the node until we find a goal check.

We chose to use a queue to easily get to the last element easily (using poll), and checking the frontier states using stream() for a fast check.

We found that Breadth-First was the most inefficient at solving the magic square

  1. Depth-First

The DFS explores the deepest nodes first in the frontier, then goes back to the deepest unexplored node. Our implementation uses a stack (LIFO) or the frontier, pushing a node, then peeking to get to the latest node added checking for a goal. A Node List will check the availableActions for the node successors, then checks against the frontier to push the new nodes in if not already there.

  1. Tracking nodes:

We had to keep track of the nodes created and expanded during the search. To do so, we utilized two variables, the expanded one, whenever a new node was added to the frontier, and the created one, everytime a successor node was searched


  1. Conclusion

The DFS approach is better suited for this problem. It does not start over the search progress and checks all the possible solutions by going up the expanded nodes, instead of the very first input.

These approach still are very inefficient when faced to bigger problems/more incomplete

1. Heuristic Implementation


We&#39;ve chosen to use the A\* Heuristic algorithm. Our heuristic sums up all of the diagonals/rows/cols which are not equal to board sum. It is consistent to the board size, using the goal formula. We are not using any random variables making it amissible.

Our implementation of A\* follows the algorithm seen in class.

The algorithm uses a priority queue, which uses comparable interface where we define compareTo function which checks the total cost (real cost + heuristic calculated cost), based on that, sorts the elements.

This guarantees us that the best candidate value is always chosen.


1. Improve

With our heuristic approach, if an array {{2, 9, 0}, {0, 0, 0}, {0, 0, 0}};

Here, 9 is in the correct spot, but the sum is calculated as 11.

In the second array, {{2, 0, 1}, {0, 0, 0}, {0, 0, 0}}, 1 is in the wrong place, but the sum is calculated as 3. Because of this reason, the sum will never reach 15. But our code will prioritize this because of its lower sum value.

A way to improve this could be done by checking if such value can ever reach 15 with the highest possible action. If not, the candidate gets repurposed for later use. The cost should be how much it would be to achieve 15 rather than getting the sum of the two values.

This will result in a time improvement by reducing the number of candidates that have to be checked.

Currently, the execution time is rather long. We tried to improve search time with the most efficient (heuristic), but couldn&#39;t find a way.
