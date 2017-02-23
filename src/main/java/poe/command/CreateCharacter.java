package poe.command;

import java.util.Collection;
import java.util.HashMap;
import poe.command.CreateCharacter.CreateCharacterRequest;
import poe.command.CreateCharacter.CreateCharacterResult;
import poe.entity.Stat;
import poe.entity.StatValue;

public class CreateCharacter extends BaseCommand<CreateCharacterRequest, CreateCharacterResult>
{
	@Override
	void execute()
	{
		final int level = 1;
		final ImmutableCharacterProxy character = new ImmutableCharacterProxy();

		final CharacterClass characterClass = request.getCharacterClass();
		character.stat(Stat.STRENGTH, characterClass.getStrength());
		character.stat(Stat.DEXTERITY, characterClass.getDexterity());
		character.stat(Stat.INTELLIGENCE, characterClass.getIntelligence());
		character.stat(Stat.LIFE, 38 + level * 12 + character.stat(Stat.STRENGTH) / 2);
		character.stat(Stat.MANA, (40 - 6) + level * 6 + character.stat(Stat.INTELLIGENCE) / 2);
		final int dexdiv5 = dexDiv5(character);
		character.stat(Stat.EVASION_RATING, 53 + level * 3 + dexdiv5);
		character.stat(Stat.ACCURACY, character.stat(Stat.DEXTERITY) * 2);

		result.setCharacter(character);
	}

	private int dexDiv5(final ImmutableCharacterProxy character)
	{
		final float dex = character.stat(Stat.DEXTERITY);
		final float gg = dex % 5;
		if (gg == 0) { return (int)(dex / 5); }

		final int g = (int)(dex - gg);
		final int f = g / 5;
		return f;
	}

	private final class ImmutableCharacterProxy implements ImmutableCharacter
	{
		private final HashMap<Stat, StatValue> stats;

		public ImmutableCharacterProxy()
		{
			stats = new HashMap<>();
		}

		public float stat(final Stat dexterity)
		{
			return stats.get(dexterity).getValue();
		}

		@Override
		public Collection<StatValue> getStats()
		{
			return stats.values();
		}

		public void stat(final Stat stat, final float value)
		{
			stats.put(stat, new StatValue(stat, value));
		}
	}

	public static interface CreateCharacterRequest
	{
		CharacterClass getCharacterClass();
	}

	public static interface CreateCharacterResult
	{
		void setCharacter(ImmutableCharacter immutableCharacter);
	}
}
