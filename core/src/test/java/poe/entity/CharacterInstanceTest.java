package poe.entity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.hamcrest.Matcher;
import org.hobsoft.hamcrest.compose.ComposeMatchers;
import org.junit.jupiter.api.Test;
import poe.command.MarkdownStream;
import poe.command.MarkdownStream.Row;
import poe.entity.CharacterInstance.PoeCharacterEditor;

public class CharacterInstanceTest
{
	@Test
	public void checkWitchLevel1Attributes()
	{
		final CharacterInstance editor = new PoeCharacterEditor()
				.withCharacterClass(CharacterClass.WITCH)
				.withLevel(1)
				.build();

		assertThat(editor, matches("" +
				"dexterity | intelligence | strength | life | mana | ",
				"14        | 32           | 14       | 57   | 56"));
	}

	@Test
	public void checkMarauderLevel100Attributes()
	{
		final CharacterInstance editor = new PoeCharacterEditor()
				.withCharacterClass(CharacterClass.MARAUDER)
				.withLevel(100)
				.build();

		assertThat(editor, matches("" +
				"dexterity | intelligence | strength | life | mana",
				"14        | 14           | 32       | 1254 | 641"));
	}

	private Matcher<CharacterInstance> matches(final String... markdown)
	{
		final Row row = MarkdownStream.stream(markdown).findFirst().get();
		return ComposeBuilder.of(CharacterInstance.class)
				.withDescription("a character with")
				.withFeature("dexterity", CharacterInstance::getDexterity, row.intValue("dexterity"))
				.withFeature("intelligence", CharacterInstance::getIntelligence, row.intValue("intelligence"))
				.withFeature("strength", CharacterInstance::getStrength, row.intValue("strength"))
				.withFeature("life", CharacterInstance::getLife, row.intValue("life"))
				.withFeature("mana", CharacterInstance::getMana, row.intValue("mana"))
				.build();
	}

	static class ComposeBuilder<T>
	{
		private String description;

		private final List<Matcher<? super T>> features;

		public ComposeBuilder()
		{
			features = new ArrayList<>();
		}

		public ComposeBuilder<T> withDescription(final String description)
		{
			this.description = description;
			return this;
		}

		public <U> ComposeBuilder<T> withFeature(final String featureName, final Function<T, U> featureFunction, final Matcher<U> featureMatcher)
		{
			features.add(ComposeMatchers.hasFeature(featureName, featureFunction, featureMatcher));
			return this;
		}

		public <U> ComposeBuilder<T> withFeature(final String featureName, final Function<T, U> featureFunction, final U featureValue)
		{
			return withFeature(featureName, featureFunction, equalTo(featureValue));
		}

		public Matcher<T> build()
		{
			return ComposeMatchers.compose(description, features);
		}

		public static <T> ComposeBuilder<T> of(final Class<T> clazz)
		{
			return new ComposeBuilder<>();
		}
	}
}
