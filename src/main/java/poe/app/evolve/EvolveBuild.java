package poe.app.evolve;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jenetics.Chromosome;
import org.jenetics.Genotype;
import org.jenetics.engine.Engine;
import org.jenetics.engine.EvolutionResult;
import org.jenetics.util.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import poe.command.CommandFactory;
import poe.command.PoeComUrlBuilder;
import poe.entity.CharacterClass;
import poe.entity.PassiveSkill;
import poe.entity.PassiveSkillTree;
import poe.entity.PoeCharacter;
import poe.evolve.FitnessFunction;
import poe.evolve.SkillChromosome;
import poe.evolve.SkillGene;
import poe.repository.PassiveSkillRepository;

@SpringBootApplication
@ComponentScan(basePackages = { "poe.app.config" }, excludeFilters = {})
public class EvolveBuild implements CommandLineRunner
{
	@Autowired
	private CommandFactory commandFactory;

	@Autowired
	PassiveSkillRepository passiveSkillRepository;

	@Override
	public void run(final String... args)
	{ // 1.) Define the genotype (factory) suitable
		// for the problem.
		final List<PassiveSkill> all = this.passiveSkillRepository.all();
		final PassiveSkillTree pst = new PassiveSkillTree(all);
		final List<Integer> ids = all.stream().map(new Function<PassiveSkill, Integer>() {
			@Override
			public Integer apply(final PassiveSkill t)
			{
				return t.getId();
			}
		}).collect(Collectors.toList());
		final Factory<Genotype<SkillGene>> gtf = Genotype.of(SkillChromosome.seq(ids, 10));

		// 3.) Create the execution environment.
		final Engine<SkillGene, Integer> engine = Engine
				.builder(new FitnessFunction(pst), gtf)
				// .builder(EvolveBuild::eval, gtf)
				.build();

		// 4.) Start the execution (evolution) and
		// collect the result.
		final Genotype<SkillGene> result = engine.stream()
				.limit(1000)
				.collect(EvolutionResult.toBestGenotype());

		final Chromosome<SkillGene> chromosome = result.getChromosome(0);

		System.out.println(chromosome);

		final PoeCharacter character = new PoeCharacter(CharacterClass.MARAUDER);
		for (final SkillGene g : chromosome)
		{
			final PassiveSkill passiveSkill = pst.find(g.getPassiveSkillId());
			character.addPassiveSkill(passiveSkill);
		}
		final PoeComUrlBuilder b = new PoeComUrlBuilder();
		b.withCharacterClass(CharacterClass.MARAUDER);
		b.withPassiveSkillIds(character.getPassiveSkillIds());
		System.out.println(b.toUrl());
	}

	public static void main(final String[] args) throws Exception
	{
		SpringApplication.run(EvolveBuild.class, args);
	}
}
