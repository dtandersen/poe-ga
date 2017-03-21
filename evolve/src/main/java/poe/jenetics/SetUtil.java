package poe.jenetics;

import java.util.Set;

public class SetUtil
{

	static Integer randomElement(final Set<Integer> set, final int index)
	{
		return set.stream().skip(index).findFirst().get();
	}

}
