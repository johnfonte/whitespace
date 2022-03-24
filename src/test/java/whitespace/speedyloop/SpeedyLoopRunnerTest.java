package whitespace.speedyloop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import whitespace.runner.SpeedyLoopRunner;
import whitespace.service.GraphOperationsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class SpeedyLoopRunnerTest
{
	@Test public void testOriginalInput()
	{
		GraphOperationsService graphOperationsService = new GraphOperationsService("testSpeedyLoop.txt", true);
		SpeedyLoopRunner.generateTestOutput(graphOperationsService);
		Map<Integer, Integer> testOutputValues = new HashMap<>();
		testOutputValues.put(1, 9);
		testOutputValues.put(2, 5);
		testOutputValues.put(3, 13);
		testOutputValues.put(4, 22);
		testOutputValues.put(5, -1);
		testOutputValues.put(6, 2);
		testOutputValues.put(7, 3);
		testOutputValues.put(8, 9);
		testOutputValues.put(9, 9);
		testOutputValues.put(10, 7);
		for (int i = 1;i <= 10;i++)
		{
			assertEquals(testOutputValues.get(i), graphOperationsService.getOutputResults().get(i));
		}
	}

	@Test public void testEmptyInput()
	{
		GraphOperationsService graphOperationsService = new GraphOperationsService(new HashMap<>());
		SpeedyLoopRunner.generateTestOutput(graphOperationsService);
		Map<Integer, Integer> testOutputValues = new HashMap<>();
		testOutputValues.put(1, -1);
		testOutputValues.put(2, -1);
		testOutputValues.put(3, -1);
		testOutputValues.put(4, -1);
		testOutputValues.put(5, -1);
		testOutputValues.put(6, 0);
		testOutputValues.put(7, 0);
		testOutputValues.put(8, -1);
		testOutputValues.put(9, -1);
		testOutputValues.put(10, 0);
		for (int i = 1;i <= 10;i++)
		{
			assertEquals(testOutputValues.get(i), graphOperationsService.getOutputResults().get(i));
		}
	}

	@Test public void testOriginalInputExactPaths6()
	{
		HashMap<Character, Map<Character, Integer>> cityGraph = getSampleCityGraphMap();
		GraphOperationsService graphOperationsService = new GraphOperationsService(cityGraph);
		graphOperationsService.numRoutesLessThanEqualStops('C', 'C', 3);
		Set<List<Character>> expectedResults = Set.of(List.of('C', 'D', 'C'), List.of('C', 'E', 'B', 'C'));
		Set<List<Character>> pathResults = graphOperationsService.getPaths();
		assertEquals(expectedResults.size(), pathResults.size());
		for (List<Character> path : expectedResults) {
			assertTrue(pathResults.contains(path));
		}
	}

	@Test public void testOriginalInputExactPaths7()
	{
		HashMap<Character, Map<Character, Integer>> cityGraph = getSampleCityGraphMap();
		GraphOperationsService graphOperationsService = new GraphOperationsService(cityGraph);
		graphOperationsService.numRoutesEqualStops('A', 'C', 4);
		Set<List<Character>> expectedResults = Set.of(List.of('A', 'B', 'C', 'D', 'C'), List.of('A', 'D', 'C', 'D', 'C'), List.of('A', 'D', 'E', 'B', 'C'));
		Set<List<Character>> pathResults = graphOperationsService.getPaths();
		assertEquals(expectedResults.size(), pathResults.size());
		for (List<Character> path : expectedResults) {
			assertTrue(pathResults.contains(path));
		}
	}

	@Test public void testOriginalInputExactPaths10()
	{
		HashMap<Character, Map<Character, Integer>> cityGraph = getSampleCityGraphMap();
		GraphOperationsService graphOperationsService = new GraphOperationsService(cityGraph);
		graphOperationsService.numRoutesLessThanDistance('C', 'C', 30);
		Set<List<Character>> expectedResults = Set.of(List.of('C', 'D', 'C'), List.of('C', 'E', 'B', 'C'), List.of('C', 'E', 'B', 'C', 'D', 'C'), List.of('C', 'D', 'C', 'E', 'B', 'C'),
			List.of('C','D','E','B','C'), List.of('C','E','B','C','E','B','C'), List.of('C','E','B','C','E','B','C','E','B','C'));
		Set<List<Character>> pathResults = graphOperationsService.getPaths();
		assertEquals(expectedResults.size(), pathResults.size());
		for (List<Character> path : expectedResults) {
			assertTrue(pathResults.contains(path));
		}
	}

	@Test public void testOriginalInputGraphTraversal()
	{
		GraphOperationsService graphOperationsService = new GraphOperationsService("testSpeedyLoop.txt", true);
		graphOperationsService.getDistanceForPath('A', 'B', 'C', 'D', 'E');
		int routeLength = graphOperationsService.getRouteLength();
		assertEquals(23, routeLength);
	}

	@Test public void testOriginalInputDijkstrasBFS()
	{
		GraphOperationsService graphOperationsService = new GraphOperationsService("testSpeedyLoop.txt", true);
		graphOperationsService.shortestRouteDistance('C', 'B');
		int routeLength = graphOperationsService.getRouteLength();
		assertEquals(5, routeLength);
	}

	@Test public void testOriginalInputDijkstrasBFSNoRoute()
	{
		GraphOperationsService graphOperationsService = new GraphOperationsService("testSpeedyLoop.txt", true);
		graphOperationsService.shortestRouteDistance('E', 'A');
		int routeLength = graphOperationsService.getRouteLength();
		assertEquals(-1, routeLength);
	}

	@Test public void testOriginalInputHeightLimitedDFS()
	{
		GraphOperationsService graphOperationsService = new GraphOperationsService("testSpeedyLoop.txt", true);
		graphOperationsService.numRoutesLessThanDistance('A', 'C', 18);
		Set<List<Character>> expectedResults = Set.of(List.of('A', 'B', 'C'), List.of('A', 'D', 'C'), List.of('A', 'E', 'B', 'C'));
		Set<List<Character>> pathResults = graphOperationsService.getPaths();
		assertEquals(expectedResults.size(), pathResults.size());
		for (List<Character> path : expectedResults) {
			assertTrue(pathResults.contains(path));
		}
	}

	public HashMap<Character, Map<Character, Integer>> getSampleCityGraphMap() {
		HashMap<Character, Map<Character, Integer>> cityGraph = new HashMap<>();
		Map<Character, Integer> aGraph = new HashMap<>();
		cityGraph.put('A', aGraph);
		aGraph.put('B', 5);
		aGraph.put('D', 5);
		aGraph.put('E', 7);

		Map<Character, Integer> bGraph = new HashMap<>();
		cityGraph.put('B', bGraph);
		bGraph.put('C', 4);

		Map<Character, Integer> cGraph = new HashMap<>();
		cityGraph.put('C', cGraph);
		cGraph.put('D', 8);
		cGraph.put('E', 2);

		Map<Character, Integer> dGraph = new HashMap<>();
		cityGraph.put('D', dGraph);
		dGraph.put('C', 8);
		dGraph.put('E', 6);

		Map<Character, Integer> eGraph = new HashMap<>();
		cityGraph.put('E', eGraph);
		eGraph.put('B', 3);

		return cityGraph;
	}
}