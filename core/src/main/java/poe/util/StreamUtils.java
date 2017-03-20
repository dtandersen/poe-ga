package poe.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamUtils
{
	public static <IN, OUT> List<OUT> mapList(final List<IN> passiveSkills, final Function<IN, OUT> mapper)
	{
		return passiveSkills.stream().map(mapper).collect(Collectors.toList());
	}
}
