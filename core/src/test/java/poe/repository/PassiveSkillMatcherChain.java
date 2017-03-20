package poe.repository;

import static org.hamcrest.Matchers.equalTo;
import java.util.Arrays;
import org.hamcrest.Matcher;
import poe.entity.PassiveSkill;
import poe.matcher.MatcherChain;

public class PassiveSkillMatcherChain extends MatcherChain<PassiveSkill>
{
	public PassiveSkillMatcherChain withName(final String name)
	{
		addChain(equalTo(name), passiveSkill -> passiveSkill.getName());
		return this;
	}

	public Matcher<PassiveSkill> withNodes(final Integer... ids)
	{
		addChain(equalTo(Arrays.asList(ids)), passiveSkill -> passiveSkill.getOutputs());
		return this;
	}
}
