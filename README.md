# whitespace

## Project Scope
I've placed all three problem statements into the repository.
However, please grade only the Speedy Loop Runner file.

I used IntelliJ IDEA to build this. The project should load well into an IDE for the purpose of running tests.

### General Project Structure

I defined some constants, models, and services to afford some separation of responsibilities.

Specifically I started to implement the Merchants Guide problem, so I could understand some common structure related to file I/O and place that in its own file.

## SpeedyLoop Problem

### Class Structure

I built a `GraphOperationsService` to take care of all algorithmic operations and semantics related to the problem.

The main runner `SpeedyLoopRunner` follows the output steps and runs those same steps for each given input file.

The `FileProcessorService` was extracted to handle file input and return a collection of inputs.
For SpeedyLoop in particular, this does allow safety in bringing back nodes from multiple lines if so desired.

### GraphOperationsService

This service is built with an overloaded constructor. I also initialized several variables as final and re-use them. I did this because storage of paths from one input query to the next isn't yet part of the design.

I built several functions re-usable across different types of queries.
For the direct taps into output functions, they can re-use internal functions and share a similar look and feel.

For testing, I exposed some functions for the paths and output results. All functions are currently public in this service for this reason.

### Algorithms

#### Directed Graph Traversal
Given a list of nodes to walk through, I walk through the nodes and check whether a directed edge exists, then add the distances together.

See `GraphOperationsService.calculateDistanceGraphTraversal`

#### Minimum Cost in a Weighted Graph - Dijkstra's Algorithm

Using a Priority Queue, I can take the directed edge with the lowest distance value starting at the source node.

I also needed a Visited Nodes set to stop the case where a pathway between the two input nodes does not exist (infinite loop).

See `GraphOperationsService.calculateDistanceBFS`

#### Height-Limited Depth First Search

I built a DFS function that did not stop until a certain depth was reached, so that traveling back to the source node once or more was possible.

## Running the Project
First place your test files into the `src/main/resources` folder.

You will receive the 10 scenarios in the file as output.

### Using IntelliJ IDEA

Load the project. Use Gradle to import dependencies for JUnit/Jupiter.

Add a Run Configuration specifying the target test file in command line options.

Click Run and view the console for expected output.

### Using command line

Then run `./src/main/java/whitespace/runner/SpeedyLoopRunner.java filename.extension`

You may need to chmod the files or directory for this.

## Unit Tests
Load the project into your IDE of choice.
Run the entire SpeedyLoopRunnerTest suite.
