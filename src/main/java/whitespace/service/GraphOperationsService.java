package whitespace.service;

import whitespace.model.CityCost;
import whitespace.model.Comparators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class GraphOperationsService
{
	private final Map<Character, Map<Character, Integer>> cityGraph;
	private final List<Character> path = new ArrayList<>();
	private final Set<List<Character>> paths = new HashSet<>();
	private final Map<Integer, Integer> outputResults = new HashMap<>();

	private Comparator<Integer> usingComparator;
	private boolean isDistanceUsedForCost;
	private int operationNumber = 0;
	private int routeLength = 0;

	public GraphOperationsService(Map<Character, Map<Character, Integer>> inputGraph)
	{
		this.cityGraph = (inputGraph == null) ? new HashMap<>() : inputGraph;
	}

	public GraphOperationsService(String filename, boolean isTestFile)
	{
		this( initializeGraphFromFile(filename, isTestFile) );
	}

	public GraphOperationsService(String filename)
	{
		this(filename, false);
	}

	public static Map<Character, Map<Character, Integer>> initializeGraphFromFile(String filename, boolean isTestFile)
	{
		List<String> values = FileProcessorService.getFileValues(filename, isTestFile);
		Map<Character, Map<Character, Integer>> inputGraph = new HashMap<>();
		for (String value : values) {
			List<String> potentialCities = Arrays.stream(value.split(",")).map(String::trim).collect(Collectors.toList());

			for (String potentialCity : potentialCities)
			{
				if (potentialCity.length() != 3) {
					continue;
				}
				int cost;
				try {
					Character costChar = potentialCity.charAt(2);
					cost = Integer.parseInt(String.valueOf(costChar));
				} catch (Exception e) {
					System.out.println("Exception formatting cost as integer");
					continue;
				}
				Character c1 = potentialCity.charAt(0);
				Character c2 = potentialCity.charAt(1);
				Map<Character, Integer> connections = inputGraph.getOrDefault(c1, new HashMap<>());
				connections.put(c2, cost);
				inputGraph.put(c1, connections);
			}
		}
		return inputGraph;
	}

	private void preCalculationSetup(Comparator<Integer> comparator, boolean isDistanceUsedForCost)
	{
		this.preCalculationSetup();
		this.usingComparator = comparator;
		this.isDistanceUsedForCost = isDistanceUsedForCost;
	}

	private void preCalculationSetup()
	{
		this.path.clear();
		this.paths.clear();
		this.operationNumber++;
	}

	public Set<List<Character>> getPaths()
	{
		return this.paths;
	}

	public Map<Integer, Integer> getOutputResults()
	{
		return this.outputResults;
	}

	public int getPathsSize()
	{
		return this.paths.size();
	}

	public int getRouteLength()
	{
		return this.routeLength;
	}

	public void storeResult(int value)
	{
		this.outputResults.put(this.operationNumber, value);
		System.out.println("Output #" + this.operationNumber + ": " + ((value > 0) ? value : "NO SUCH ROUTE"));
	}

	public void getDistanceForPath(Character... nodes)
	{
		preCalculationSetup();
		this.routeLength = calculateDistanceGraphTraversal(nodes);
		storeResult(getRouteLength());
	}

	public void shortestRouteDistance(Character start, Character end)
	{
		preCalculationSetup();
		this.routeLength = calculateDistanceBFS(start, end);
		storeResult(getRouteLength());
	}

	public void numRoutesLessThanEqualStops(Character start, Character end, int maxStops)
	{
		preCalculationSetup(Comparators.lessThanEqualComparator, false);
		calculateRoutesDFS(start, end, 0, maxStops);
		storeResult(getPathsSize());
	}

	public void numRoutesEqualStops(Character start, Character end, int exactStops)
	{
		preCalculationSetup(Comparators.equalComparator, false);
		calculateRoutesDFS(start, end, 0, exactStops);
		storeResult(getPathsSize());
	}

	public void numRoutesLessThanDistance(Character start, Character end, int maxCost)
	{
		preCalculationSetup(Comparators.lessThanComparator, true);
		calculateRoutesDFS(start, end, 0, maxCost);
		storeResult(getPathsSize());
	}

	public void calculateRoutesDFS(Character current, Character target, int cost, int maxCost)
	{
		if (cost > maxCost)
		{
			return;
		}
		this.path.add(current);
		if (current.equals(target) && (this.usingComparator.compare(cost, maxCost) > 0) && path.size() > 1)
		{
			this.paths.add(new ArrayList<>(this.path));
		}
		if (this.cityGraph.get(current) == null)
		{
			return;
		}
		for (Map.Entry<Character, Integer> edge : this.cityGraph.get(current).entrySet())
		{
			int useCost = (this.isDistanceUsedForCost) ? edge.getValue() : 1;
			calculateRoutesDFS(edge.getKey(), target, cost + useCost, maxCost);
		}
		this.path.remove(this.path.size() - 1);
	}

	public int calculateDistanceBFS(Character current, Character target)
	{
		Set<Character> visited = new HashSet<>();
		PriorityQueue<CityCost> pq = new PriorityQueue<>(Comparator.comparingInt(CityCost::getCost));

		if (this.cityGraph.get(current) == null)
		{
			return -1;
		}
		for (Map.Entry<Character, Integer> edge : this.cityGraph.get(current).entrySet())
		{
			pq.add(new CityCost(edge.getKey(), edge.getValue()));
		}
		while (!pq.isEmpty())
		{
			CityCost currentCity = pq.poll();
			if (currentCity.getCity().equals(target))
			{
				return currentCity.getCost();
			}
			visited.add(currentCity.getCity());
			for (Map.Entry<Character, Integer> edge : this.cityGraph.getOrDefault(currentCity.getCity(), new HashMap<>()).entrySet())
			{
				if (!visited.contains(edge.getKey()))
				{
					pq.add(new CityCost(edge.getKey(), edge.getValue() + currentCity.getCost()));
				}
			}
		}
		return -1;
	}

	public int calculateDistanceGraphTraversal(Character... nodes)
	{
		int runningCost = 0;
		for (int i = 0;i < nodes.length - 1;i++)
		{
			Character baseNode = nodes[i];
			Character targetNode = nodes[i + 1];
			Map<Character, Integer> baseNodeEdges = this.cityGraph.get(baseNode);
			if (baseNodeEdges == null)
			{
				return -1;
			}
			Integer cost = baseNodeEdges.get(targetNode);
			if (cost == null)
			{
				return -1;
			}
			runningCost += baseNodeEdges.get(targetNode);
		}
		return runningCost;
	}

}
