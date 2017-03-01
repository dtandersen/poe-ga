package poe.repository.jenetics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jenetics.Chromosome;
import org.jenetics.Genotype;
import org.jenetics.Mutator;
import org.jenetics.SinglePointCrossover;
import org.jenetics.engine.Engine;
import org.jenetics.engine.EvolutionResult;
import org.jenetics.util.Factory;
import poe.entity.CharacterClass;
import poe.entity.PassiveSkill;
import poe.entity.PassiveSkillTree;
import poe.entity.PoeCharacter;
import poe.repository.Evolver;

public class JeneticsEvolver implements Evolver
{
	@Override
	public PoeCharacter getBest(
			final List<PassiveSkill> passives,
			final CharacterClass characterClass,
			final PassiveSkillTree pst)
	{
		final int length = 50;
		final List<Integer> ids = passives.stream().map(new Function<PassiveSkill, Integer>() {
			@Override
			public Integer apply(final PassiveSkill t)
			{
				return t.getId();
			}
		}).collect(Collectors.toList());
		final Factory<Genotype<SkillGene>> gtf = Genotype.of(SkillChromosome.seq(ids, length));

		final Engine<SkillGene, Integer> engine = Engine
				.builder(new FitnessFunction(pst, characterClass), gtf)
				.populationSize(50)
				.alterers(new Mutator<>(.75), new SinglePointCrossover<>(.2))
				.build();

		final Genotype<SkillGene> result = engine.stream()
				.limit(50000)
				.peek(new EvolutionStatistics())
				.collect(EvolutionResult.toBestGenotype());

		final Chromosome<SkillGene> chromosome = result.getChromosome(0);

		final PoeCharacter character = new PoeCharacter(characterClass);
		character.addPassiveSkill(pst.findByName(characterClass.getRootPassiveSkillName()));
		final List<PassiveSkill> myPassives = new ArrayList<>();
		for (final SkillGene g : chromosome)
		{
			final PassiveSkill passiveSkill = pst.find(g.getPassiveSkillId());
			myPassives.add(passiveSkill);
		}
		character.sneakyAdd(myPassives);

		return character;
	}
}
