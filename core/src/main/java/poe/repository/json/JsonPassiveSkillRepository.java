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
import poe.repository.json.JsonPassiveSkillRepository.PassiveSkillTreeJson.PassiveSkillJson;

public class JsonPassiveSkillRepository implements PassiveSkillRepository
{
	private static final String PASSIVE_TREE_RESOURCE = "passivetree.json";

	@Override
	public List<PassiveSkill> all()
	{
		final InputStream in = this.getClass().getClassLoader().getResourceAsStream(PASSIVE_TREE_RESOURCE);
		final Gson gson = new Gson();
		final PassiveSkillTreeJson passiveSkillTree = gson.fromJson(new InputStreamReader(in), PassiveSkillTreeJson.class);
		final List<PassiveSkill> skills = new ArrayList<>();

		for (final PassiveSkillJson passiveSkillJson : passiveSkillTree.nodes.values())
		{
			if (passiveSkillJson.asc || passiveSkillJson.ascendancyName != null)
			{
				continue;
			}

			final PassiveSkill passiveSkill = new PassiveSkill(passiveSkillJson.dn);
			passiveSkill.setId(passiveSkillJson.id);
			passiveSkill.setOutputs(passiveSkillJson.out);

			if (passiveSkillJson.isJewelSocket)
			{
				passiveSkill.setJewels(1);
			}

			if (passiveSkillJson.spc.size() > 0)
			{
				passiveSkill.setClassStartingPoint(CharacterClass.byId(passiveSkillJson.spc.get(0)));
			}

			for (final String statDescription : passiveSkillJson.sd)
			{
				boolean matched = false;
				for (final Stat at : Stat.values())
				{
					final Matcher matcher = at.matcher(statDescription);
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
					passiveSkill.addAttribute(attribute);
					matched = true;
					break;
				}
				if (!matched)
				{
					passiveSkill.addAttribute(new UnknownStatValue(statDescription));
				}
			}
			skills.add(passiveSkill);
		}

		return skills;
	}

	@Override
	public PassiveSkillTree skillTree()
	{
		return new PassiveSkillTree(all());
	}

	public static final class PassiveSkillTreeJson
	{
		Map<Integer, PassiveSkillJson> nodes;

		public static final class PassiveSkillJson
		{
			String ascendancyName;

			/**
			 * skill id
			 */
			int id;

			/**
			 * stat description
			 */
			List<String> sd;

			/**
			 * skill name
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
}
