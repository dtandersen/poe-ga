package poe.repo;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import com.google.gson.Gson;
import poe.entity.Attribute;
import poe.entity.AttributeDescription;
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
			if (n.asc)
			{
				continue;
			}
			final PassiveSkill e = new PassiveSkill(n.dn);
			e.setId(n.id);
			for (final String s : n.sd)
			{
				boolean matched = false;
				for (final AttributeDescription at : AttributeDescription.values())
				{
					final Matcher matcher = at.matcher(s);
					if (!matcher.find())
					{
						continue;
					}

					final Attribute attribute = new Attribute(at);
					try
					{
						final String group = matcher.group(1);
						final float val = Float.parseFloat(group);
						attribute.setValue(val);
					} catch (final IndexOutOfBoundsException e2)
					{
					}
					e.addAttribute(attribute);
					matched = true;
					break;
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
			int id;

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

			boolean asc;
		}
	}
}
