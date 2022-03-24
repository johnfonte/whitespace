package whitespace.model;

import java.util.Comparator;
import java.util.Objects;

public class Comparators
{
	public static final Comparator<Integer> equalComparator = (left, right)->(Objects.equals(left, right)) ? 1 : 0;
	public static final Comparator<Integer> lessThanEqualComparator = (left, right)->(left <= right) ? 1 : 0;
	public static final Comparator<Integer> lessThanComparator = (left, right)->(left < right) ? 1 : 0;
}
