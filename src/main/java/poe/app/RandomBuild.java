package poe.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import poe.command.CommandFactory;
import poe.command.CreateCharacter;
import poe.command.CreateCharacter.CreateCharacterRequest;
import poe.command.CreateCharacter.CreateCharacterResult;
import poe.entity.CharacterClass;
import poe.entity.ImmutableCharacter;
import poe.entity.PassiveSkill;
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
		final List<PassiveSkill> passives = passiveSkillRepository.all();
		Collections.shuffle(passives);
		final List<Integer> selectedIds = new ArrayList<>();
		final UndirectedGraph<String, DefaultEdge> stringGraph = new SimpleGraph<>(DefaultEdge.class);
		for (int i = 0; i < 60; i++)
		{
			selectedIds.add(passives.get(i).getId());
			// stringGraph
		}

		final CreateCharacter command = commandFactory.createCharacter();
		command.setRequest(new CreateCharacterRequest() {
			@Override
			public List<Integer> getPassiveSkillIds()
			{
				return selectedIds;
			}

			@Override
			public CharacterClass getCharacterClass()
			{
				return CharacterClass.MARAUDER;
			}
		});
		final CreateCharacterResultImplementation result = new CreateCharacterResultImplementation();
		command.setResult(result);
		command.execute();

		System.out.println(result.getUrl());
	}

	public static void main(final String[] args) throws Exception
	{
		SpringApplication.run(RandomBuild.class, args);
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
