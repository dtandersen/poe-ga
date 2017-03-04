package poe.repository;

import poe.entity.ImmutableCharacter;

public interface CharacterView
{
	void start();

	void update(ImmutableCharacter character);

	void stop();
}
