package whitespace.runner;

import whitespace.service.GraphOperationsService;

/**
 PROBLEM ONE: SPEEDY LOOP
 <p>
 The local commuter railroad services a number of towns in Iansville. Because of
 monetary concerns, all of the tracks are 'one-way.' That is, a route from Irving to
 Invercargill does not imply the existence of a route from Invercargill to Irving. In fact,
 even if both of these routes do happen to exist, they are distinct and are not
 necessarily the same distance!
 <p>
 The purpose of this problem is to help the transportation company provide its
 customers with information about the routes. In particular, you will compute the
 distance along a certain route, the number of different routes between two towns,
 and the shortest route between two towns.
 <p>
 Input: A directed graph where a node represents a town and an edge represents a
 route between two towns. The weighting of the edge represents the distance
 between the two towns. A given route will never appear more than once, and for a
 given route, the starting and ending town will not be the same town.
 <p>
 Output: For test input 1 through 5, if no such route exists, output 'NO SUCH ROUTE'.
 Otherwise, follow the route as given; do not make any extra stops! For example, the
 first problem means to start at city A, then travel directly to city B (a distance of 5),
 then directly to city C (a distance of 4).
 <p>
 1. The distance of the route A-B-C.
 <p>
 2. The distance of the route A-D.
 <p>
 3. The distance of the route A-D-C.
 <p>
 4. The distance of the route A-E-B-C-D.
 <p>
 5. The distance of the route A-E-D.
 <p>
 6. The number of trips starting at C and ending at C with a maximum of 3 stops.
 In the sample data below, there are two such trips: C-D-C (2 stops). and
 C-E-B-C (3 stops).
 <p>
 7. The number of trips starting at A and ending at C with exactly 4 stops. In the
 sample data below, there are three such trips: A to C (via B,C,D); A to C (via
 D,C,D); and A to C (via D,E,B).
 <p>
 8. The length of the shortest route (in terms of distance to travel) from A to C.
 <p>
 9. The length of the shortest route (in terms of distance to travel) from B to B.
 <p>
 10. The number of different routes from C to C with a distance of less than 30. In
 the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC,
 CEBCEBC, CEBCEBCEBC.
 <p>
 Test Input:
 <p>
 For the test input, the towns are named using the first few letters of the
 alphabet from A to D. A route between two towns (A to B) with a distance of 5
 is represented as AB5.
 <p>
 Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
 <p>
 Test Output:
 <p>
 Output #1: 9
 <p>
 Output #2: 5
 <p>
 Output #3: 13
 <p>
 Output #4: 22
 <p>
 Output #5: NO SUCH ROUTE
 <p>
 Output #6: 2
 <p>
 Output #7: 3
 <p>
 Output #8: 9
 <p>
 Output #9: 9
 <p>
 Output #10: 7
 */

public class SpeedyLoopRunner
{
	public static void main(String[] args)
	{
		if (args == null || args.length != 1) {
			return;
		}
		String filename = args[0];

		GraphOperationsService graphOperationsService = new GraphOperationsService(filename);

		generateTestOutput(graphOperationsService);
	}

	public static void generateTestOutput(GraphOperationsService graphOperationsService)
	{
		graphOperationsService.getDistanceForPath('A', 'B', 'C');
		graphOperationsService.getDistanceForPath('A', 'D');
		graphOperationsService.getDistanceForPath('A', 'D', 'C');
		graphOperationsService.getDistanceForPath('A', 'E', 'B', 'C', 'D');
		graphOperationsService.getDistanceForPath('A', 'E', 'D');
		graphOperationsService.numRoutesLessThanEqualStops('C', 'C', 3);
		graphOperationsService.numRoutesEqualStops('A', 'C', 4);
		graphOperationsService.shortestRouteDistance('A', 'C');
		graphOperationsService.shortestRouteDistance('B', 'B');
		graphOperationsService.numRoutesLessThanDistance('C', 'C', 30);
	}

}
