package poe.ehp;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import poe.ehp.EhpCalculator.EhpCalculatorBuilder;

public class EhpCalculatorTest
{
	@Test
	public void eachResist25()
	{
		final EhpCalculator ehp = new EhpCalculatorBuilder()
				.withLife(1000)
				.withPhysicalResist(25)
				.withLightningResist(25)
				.withFireResist(25)
				.withColdResist(25)
				.withChaosResist(25)
				.build();

		assertThat(ehp.getLargestPhysicalHit(), equalTo(1333.3334f));
		assertThat(ehp.getLargestLightningHit(), equalTo(1333.3334f));
		assertThat(ehp.getLargestFireHit(), equalTo(1333.3334f));
		assertThat(ehp.getLargestColdHit(), equalTo(1333.3334f));
		assertThat(ehp.getLargestChaosHit(), equalTo(1333.3334f));
	}

	@Test
	public void resistElemental()
	{
		final EhpCalculator ehp = new EhpCalculatorBuilder()
				.withLife(2000)
				.withPhysicalResist(20)
				.withLightningResist(20)
				.withFireResist(20)
				.withColdResist(20)
				.withChaosResist(20)
				.withElementalResist(30)
				.build();

		assertThat(ehp.getLargestPhysicalHit(), equalTo(2500f));
		assertThat(ehp.getLargestLightningHit(), equalTo(4000f));
		assertThat(ehp.getLargestFireHit(), equalTo(4000f));
		assertThat(ehp.getLargestColdHit(), equalTo(4000f));
		assertThat(ehp.getLargestChaosHit(), equalTo(2500f));
	}

	@Test
	public void cappedResists()
	{
		final EhpCalculator ehp = new EhpCalculatorBuilder()
				.withLife(1000)
				.withPhysicalResist(100)
				.withLightningResist(100)
				.withFireResist(100)
				.withColdResist(100)
				.withChaosResist(100)
				.withElementalResist(100)
				.build();

		assertThat(ehp.getLargestPhysicalHit(), equalTo(9999.998f));
		assertThat(ehp.getLargestLightningHit(), equalTo(4000f));
		assertThat(ehp.getLargestFireHit(), equalTo(4000f));
		assertThat(ehp.getLargestColdHit(), equalTo(4000f));
		assertThat(ehp.getLargestChaosHit(), equalTo(4000f));
	}

	@Test
	public void negativeResists()
	{
		final EhpCalculator ehp = new EhpCalculatorBuilder()
				.withLife(1000)
				.withPhysicalResist(-10)
				.withLightningResist(-10)
				.withFireResist(-10)
				.withColdResist(-10)
				.withChaosResist(-10)
				.withElementalResist(-10)
				.build();

		assertThat(ehp.getLargestPhysicalHit(), equalTo(909.0909f));
		assertThat(ehp.getLargestLightningHit(), equalTo(833.3333f));
		assertThat(ehp.getLargestFireHit(), equalTo(833.3333f));
		assertThat(ehp.getLargestColdHit(), equalTo(833.3333f));
		assertThat(ehp.getLargestChaosHit(), equalTo(909.0909f));
	}

	@Test
	public void energyShield()
	{
		final EhpCalculator ehp = new EhpCalculatorBuilder()
				.withLife(500)
				.withEnergyShield(500)
				.withPhysicalResist(25)
				.withLightningResist(25)
				.withFireResist(25)
				.withColdResist(25)
				.withChaosResist(25)
				.withElementalResist(25)
				.build();

		assertThat(ehp.getLargestPhysicalHit(), equalTo(1333.3334f));
		assertThat(ehp.getLargestLightningHit(), equalTo(2000f));
		assertThat(ehp.getLargestFireHit(), equalTo(2000f));
		assertThat(ehp.getLargestColdHit(), equalTo(2000f));
		assertThat(ehp.getLargestChaosHit(), equalTo(666.6667f));
	}
}
