package poe.app;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import poe.command.CommandFactory;
import poe.command.ListSkills;
import poe.command.ListSkills.ListSkillsResult;
import poe.entity.Stat;
import poe.entity.PassiveSkill;

@SpringBootApplication
public class Booter implements CommandLineRunner
{
	@Autowired
	private CommandFactory commandFactory;

	@Override
	public void run(final String... args)
	{
		final ListSkills command = commandFactory.list();
		command.setResult(new ListSkillsResult() {
			@Override
			public void skills(final List<PassiveSkill> skills)
			{
				int count = 0;
				for (final PassiveSkill skill : skills)
				{
					if (skill.hasAttribute(Stat.UNKNOWN))
					{
						System.out.println(skill);
						count++;
					}
				}
				System.out.println("" + count);
			}
		});
		command.execute();
	}

	public static void main(final String[] args) throws Exception
	{
		SpringApplication.run(Booter.class, args);
	}
}
