package poe.app;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import poe.command.CommandFactory;
import poe.command.CreateCharacter;
import poe.command.CreateCharacter.CreateCharacterRequest;
import poe.command.CreateCharacter.CreateCharacterResult;
import poe.command.RandomBuild.RandomBuildRequest;
import poe.command.RandomBuild.RandomBuildResult;
import poe.entity.CharacterClass;
import poe.entity.ImmutableCharacter;
import poe.repository.PassiveSkillRepository;

@SpringBootApplication
public class RandomBuild implements CommandLineRunner
{
	@Autowired
	private CommandFactory commandFactory;

	@Autowired
	PassiveSkillRepository passiveSkillRepository;

	@Override
	public void run(final String... args)
	{
		final poe.command.RandomBuild command = commandFactory.randomCharacter();
		command.setRequest(new RandomBuildRequest() {
			@Override
			public int getSize()
			{
				return 60;
			}
		});
		final RandomBuildResultImplementation result1 = new RandomBuildResultImplementation();
		command.setResult(result1);
		command.execute();

		final CreateCharacter command2 = commandFactory.createCharacter();
		command2.setRequest(new CreateCharacterRequest() {
			@Override
			public List<Integer> getPassiveSkillIds()
			{
				return result1.selectedIds;
			}

			@Override
			public CharacterClass getCharacterClass()
			{
				return CharacterClass.MARAUDER;
			}
		});
		final CreateCharacterResultImplementation result = new CreateCharacterResultImplementation();
		command2.setResult(result);
		command2.execute();

		System.out.println(result.getUrl());
	}

	// private PassiveSkill reset(final List<Integer> selectedIds, final Random
	// r, final PassiveSkillTree ps)
	// {
	// final int i = r.nextInt(selectedIds.size());
	// final int id = selectedIds.get(i);
	// return ps.find(id);
	// }

	public static void main(final String[] args) throws Exception
	{
		SpringApplication.run(RandomBuild.class, args);
	}

	private final class RandomBuildResultImplementation implements RandomBuildResult
	{
		protected List<Integer> selectedIds;

		@Override
		public void setCharacter(final ImmutableCharacter build)
		{
			selectedIds = build.getPassiveSkillIds();
		}
	}

	private final class CreateCharacterResultImplementation implements CreateCharacterResult
	{
		private String url;

		@Override
		public void setUrl(final String url)
		{
			this.url = url;
		}

		@Override
		public void setCharacter(final ImmutableCharacter immutableCharacter)
		{
		}

		public String getUrl()
		{
			return url;
		}
	}
}
