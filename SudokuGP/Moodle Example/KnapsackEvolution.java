
public class KnapsackEvolution extends Evolution {

	public KnapsackEvolution(int[] values, int[] weights, int maxWeight, int popSize, int maxGenerations,
			double mutationProb, double crossoverProb) {
		super(new Population(popSize,
				new KnapsackIndividual(values, weights, maxWeight),
				new TournamentSelection(mutationProb, crossoverProb)),
			maxGenerations);
	}

}
