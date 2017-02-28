package poe.command;

import poe.command.EvolveCharacter.EvolveCharacterRequest;
import poe.command.EvolveCharacter.EvolveCharacterResult;
import poe.command.PureImmutableCharacter.ImmutableCharacterBuilder;
import poe.entity.CharacterClass;
import poe.entity.ImmutableCharacter;
import poe.entity.PoeCharacter;
import poe.repository.Evolver;
import poe.repository.PassiveSkillRepository;

public class EvolveCharacter extends BaseCommand<EvolveCharacterRequest, EvolveCharacterResult>
{
	private final Evolver evolver;

	private final PassiveSkillRepository repo;

	public EvolveCharacter(
			final Evolver evolver,
			final PassiveSkillRepository repo)
	{
		this.evolver = evolver;
		this.repo = repo;
	}

	@Override
	public void execute()
	{
		final PoeCharacter character = evolver.getBest(repo.all(), request.getCharacterClass());
		result.setCharacter(ImmutableCharacterBuilder.character().withPassiveSkills(character.getPassiveSkillsWithoutRoot()).build());
	}

	public interface EvolveCharacterRequest
	{
		CharacterClass getCharacterClass();
	}

	public interface EvolveCharacterResult
	{
		void setCharacter(ImmutableCharacter character);
	}
}
