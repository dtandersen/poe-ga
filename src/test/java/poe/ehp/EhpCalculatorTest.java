package poe.ehp;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import poe.evaluator.EhpCalculator;
import poe.evaluator.EhpCalculator.EhpCalculatorBuilder;

public class EhpCalculatorTest
{
	@Test
	public void eachResist25()
	{
		final EhpCalculator ehp = new EhpCalculatorBuilder()
				.withLife(1000)
				.withPhysicalDamageReduction(25)
				.withLightningResist(25)
				.withFireResist(25)
				.withColdResist(25)
				.withChaosResist(25)
				.build();

		assertThat((double)ehp.getMaxPhysicalHit(), closeTo(1333.3334, .5));
		assertThat((double)ehp.getMaxLightningHit(), closeTo(1333, 1));
		assertThat((double)ehp.getMaxFireHit(), closeTo(1333, 1));
		assertThat((double)ehp.getMaxColdHit(), closeTo(1333, 1));
		assertThat((double)ehp.getMaxChaosHit(), closeTo(1333, 1));
	}

	@Test
	public void resistElemental()
	{
		final EhpCalculator ehp = new EhpCalculatorBuilder()
				.withLife(2000)
				.withPhysicalDamageReduction(20)
				.withLightningResist(20)
				.withFireResist(20)
				.withColdResist(20)
				.withChaosResist(20)
				.withElementalResist(30)
				.build();

		assertThat((double)ehp.getMaxPhysicalHit(), closeTo(2500, 1));
		assertThat((double)ehp.getMaxLightningHit(), closeTo(4000, 1));
		assertThat((double)ehp.getMaxFireHit(), closeTo(4000, 1));
		assertThat((double)ehp.getMaxColdHit(), closeTo(4000, 1));
		assertThat((double)ehp.getMaxChaosHit(), closeTo(2500, 1));
	}

	@Test
	public void cappedResists()
	{
		final EhpCalculator ehp = new EhpCalculatorBuilder()
				.withLife(1000)
				.withPhysicalDamageReduction(100)
				.withLightningResist(100)
				.withFireResist(100)
				.withColdResist(100)
				.withChaosResist(100)
				.withElementalResist(100)
				.build();

		assertThat((double)ehp.getMaxPhysicalHit(), closeTo(9999.998, .5));
		assertThat((double)ehp.getMaxLightningHit(), closeTo(4000f, 1));
		assertThat((double)ehp.getMaxFireHit(), closeTo(4000f, 1));
		assertThat((double)ehp.getMaxColdHit(), closeTo(4000f, 1));
		assertThat((double)ehp.getMaxChaosHit(), closeTo(4000f, 1));
	}

	@Test
	public void negativeResists()
	{
		final EhpCalculator ehp = new EhpCalculatorBuilder()
				.withLife(1000)
				.withPhysicalDamageReduction(-10)
				.withLightningResist(-10)
				.withFireResist(-10)
				.withColdResist(-10)
				.withChaosResist(-10)
				.withElementalResist(-10)
				.build();

		assertThat((double)ehp.getMaxPhysicalHit(), closeTo(909, 1));
		assertThat((double)ehp.getMaxLightningHit(), closeTo(833, 1));
		assertThat((double)ehp.getMaxFireHit(), closeTo(833, 1));
		assertThat((double)ehp.getMaxColdHit(), closeTo(833, 1));
		assertThat((double)ehp.getMaxChaosHit(), closeTo(909, 1));
	}

	@Test
	public void energyShield()
	{
		final EhpCalculator ehp = new EhpCalculatorBuilder()
				.withLife(500)
				.withEnergyShield(500)
				.withPhysicalDamageReduction(25)
				.withLightningResist(25)
				.withFireResist(25)
				.withColdResist(25)
				.withChaosResist(25)
				.withElementalResist(25)
				.build();

		assertThat((double)ehp.getMaxPhysicalHit(), closeTo(1333.3334, .5));
		assertThat((double)ehp.getMaxLightningHit(), closeTo(2000, 1));
		assertThat((double)ehp.getMaxFireHit(), closeTo(2000, 1));
		assertThat((double)ehp.getMaxColdHit(), closeTo(2000, 1));
		assertThat((double)ehp.getMaxChaosHit(), closeTo(666, 1));
	}

	@Test
	public void armor()
	{
		final EhpCalculator ehp = new EhpCalculatorBuilder()
				.withLife(500)
				.withArmour(500)
				.build();

		assertThat((double)ehp.getMaxPhysicalHit(), closeTo(545, 1));
	}

	@Test
	public void mindOverMatter()
	{
		final EhpCalculator ehp = new EhpCalculatorBuilder()
				.withLife(1000)
				.withPhysicalDamageReduction(25)
				.withLightningResist(25)
				.withFireResist(25)
				.withColdResist(25)
				.withChaosResist(25)
				.withMindOverMatter()
				.withMana(1000)
				.build();

		assertThat((double)ehp.getMaxPhysicalHit(), closeTo(1904, 1));
		assertThat((double)ehp.getMaxLightningHit(), closeTo(1333, 1));
		assertThat((double)ehp.getMaxFireHit(), closeTo(1333, 1));
		assertThat((double)ehp.getMaxColdHit(), closeTo(1333, 1));
		assertThat((double)ehp.getMaxChaosHit(), closeTo(1333, 1));
	}

	@Test
	public void mindOverMatterInsufficientMana()
	{
		final EhpCalculator ehp = new EhpCalculatorBuilder()
				.withLife(1000)
				.withPhysicalDamageReduction(25)
				.withLightningResist(25)
				.withFireResist(25)
				.withColdResist(25)
				.withChaosResist(25)
				.withMindOverMatter()
				.withMana(250)
				.build();

		assertThat((double)ehp.getMaxPhysicalHit(), closeTo(1583, 1));
		assertThat((double)ehp.getMaxLightningHit(), closeTo(1333, 1));
		assertThat((double)ehp.getMaxFireHit(), closeTo(1333, 1));
		assertThat((double)ehp.getMaxColdHit(), closeTo(1333, 1));
		assertThat((double)ehp.getMaxChaosHit(), closeTo(1333, 1));
	}
}
