package SodukoGame;

public interface Variable {
	BoardIndividual mutate();
	BoardIndividual crossover(BoardIndividual other);

}
