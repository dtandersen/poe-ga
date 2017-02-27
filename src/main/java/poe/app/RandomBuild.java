package poe.app;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import poe.command.CommandFactory;
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

			@Override
			public CharacterClass getCharacterClass()
			{
				return CharacterClass.SCION;
			}
		});
		final RandomBuildResultImplementation result1 = new RandomBuildResultImplementation();
		command.setResult(result1);
		command.execute();

		System.out.println(result1.getUrl());
	}

	public static void main(final String[] args) throws Exception
	{
		SpringApplication.run(RandomBuild.class, args);
	}

	private final class RandomBuildResultImplementation implements RandomBuildResult
	{
		protected List<Integer> selectedIds;

		private String url;

		@Override
		public void setCharacter(final ImmutableCharacter build)
		{
			selectedIds = build.getPassiveSkillIds();
		}

		public String getUrl()
		{
			return url;
		}

		@Override
		public void setUrl(final String url)
		{
			this.url = url;
		}
	}
}
