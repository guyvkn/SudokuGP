
public class SubsetSumEvolution extends Evolution {

	public SubsetSumEvolution(int[] values, int sum, int popSize, int maxGenerations,
			double mutationProb, double crossoverProb) {
		super(new Population(popSize,
					new SubsetSumIndividual(values, sum),
					new TournamentSelection(mutationProb, crossoverProb)),
				maxGenerations);
	}

}
