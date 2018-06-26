package poe.repository.json;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import com.google.gson.Gson;
import poe.entity.CharacterClass;
import poe.entity.PassiveSkill;
import poe.entity.Stat;
import poe.entity.StatValue;
import poe.entity.UnknownStatValue;
import poe.repository.PassiveSkillRepository;
import poe.repository.PassiveSkillTree;
import poe.repository.json.JsonPassiveSkillRepository.Stuff.Node22;

public class JsonPassiveSkillRepository implements PassiveSkillRepository
{
	@Override
	public List<PassiveSkill> all()
	{
		final InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream("passivetree.json");
		final Gson gson = new Gson();
		final Stuff x = gson.fromJson(new InputStreamReader(in), Stuff.class);
		final List<PassiveSkill> skills = new ArrayList<>();

		for (final Node22 n : x.nodes.values())
		{
			if (n.asc || n.ascendancyName != null)
			{
				continue;
			}
			final PassiveSkill e = new PassiveSkill(n.dn);
			e.setId(n.id);
			e.setOutputs(n.out);

			if (n.isJewelSocket)
			{
				e.setJewels(1);
			}

			if (n.spc.size() > 0)
			{
				e.setClassStartingPoint(CharacterClass.byId(n.spc.get(0)));
			}

			for (final String s : n.sd)
			{
				boolean matched = false;
				for (final Stat at : Stat.values())
				{
					final Matcher matcher = at.matcher(s);
					if (!matcher.find())
					{
						continue;
					}

					StatValue attribute;
					attribute = new StatValue(at, 0);
					try
					{
						final String group = matcher.group(1);
						final float val = Float.parseFloat(group);
						attribute = new StatValue(at, val);
					}
					catch (final IndexOutOfBoundsException e2)
					{
					}
					e.addAttribute(attribute);
					matched = true;
					break;
				}
				if (!matched)
				{
					// System.out.println("failed at " + s);
					e.addAttribute(new UnknownStatValue(s));
				}
			}
			skills.add(e);
		}

		return skills;
	}

	public static final class Stuff
	{
		Map<Integer, Node22> nodes;

		public static final class Node22
		{
			String ascendancyName;

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

			boolean isJewelSocket;

			/**
			 * output nodes
			 */
			List<Integer> out;

			int type;

			boolean asc;

			List<Integer> spc;
		}
	}

	@Override
	public PassiveSkillTree skillTree()
	{
		return new PassiveSkillTree(all());
	}
}
