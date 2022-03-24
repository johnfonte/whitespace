package whitespace.model;

public class CityCost
{
	private final Character city;
	private final Integer cost;

	public CityCost(Character city, Integer cost)
	{
		this.city = city;
		this.cost = cost;
	}

	public Character getCity()
	{
		return this.city;
	}

	public int getCost()
	{
		return this.cost;
	}
}
