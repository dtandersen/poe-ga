package poe.app.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import poe.repository.CharacterView;
import poe.repository.EvolutionStatus;

public class MultiCharacterView implements CharacterView
{
	private final List<CharacterView> views = new ArrayList<>();

	public MultiCharacterView(final CharacterView... views)
	{
		Arrays.asList(views).stream().forEach(view -> this.views.add(view));
	}

	@Override
	public void start()
	{
		forEachView(view -> view.start());
	}

	@Override
	public void update(final EvolutionStatus evolutionStatus)
	{
		forEachView(view -> view.update(evolutionStatus));
	}

	@Override
	public void stop()
	{
		forEachView(view -> view.stop());
	}

	private void forEachView(final Consumer<? super CharacterView> action)
	{
		views.stream().forEach(action);
	}
}
