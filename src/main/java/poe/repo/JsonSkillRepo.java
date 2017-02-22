package poe.repo;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import com.google.gson.Gson;
import poe.entity.Attribute;
import poe.entity.AttributeType;
import poe.entity.PassiveSkill;
import poe.entity.UnknownAttribute;
import poe.repo.JsonSkillRepo.Stuff.Node22;

public class JsonSkillRepo implements SkillRepo
{
	@Override
	public List<PassiveSkill> all()
	{
		final InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream("passivetree.json");
		final Gson gson = new Gson();
		final Stuff x = gson.fromJson(new InputStreamReader(in), Stuff.class);
		final List<PassiveSkill> skills = new ArrayList<>();

		for (final Node22 n : x.nodes)
		{
			final PassiveSkill e = new PassiveSkill(n.dn);
			for (final String s : n.sd)
			{
				boolean matched = false;
				for (final AttributeType at : AttributeType.values())
				{
					final Matcher matcher = at.matcher(s);
					if (matcher.find())
					{
						final Attribute attribute = new Attribute(at);
						try
						{
							attribute.setValue(Float.parseFloat(matcher.group(1)));
						}
						catch (final IndexOutOfBoundsException e2)
						{
						}
						e.addAttribute(attribute);
						matched = true;
					}
				}
				if (!matched)
				{
					// System.out.println("failed at " + s);
					e.addAttribute(new UnknownAttribute(s));
				}
			}
			skills.add(e);
		}

		return skills;
	}

	public static final class Stuff
	{
		List<Node22> nodes;

		public static final class Node22
		{
			/**
			 * skill id
			 */
			String id;

			/**
			 * description
			 */
			List<String> sd;

			/**
			 * name
			 */
			String dn;

			/**
			 * output nodes
			 */
			List<Integer> out;
		}
	}
}
