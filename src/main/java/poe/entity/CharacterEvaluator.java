package poe.entity;

import poe.entity.SpringCharacterEvaluator.EvaluationResult;

public interface CharacterEvaluator
{
	EvaluationResult evaluate(ExpressionContext rootObject);
}
