public class SubsetSumMain {
	public static void main(String[] args) {
		int[]  values  = {7,3,5,10,13,17};
		int    sum     = 21;
		int    popSize = 100;
		int    maxGenerations = 20;
		double mutationProb   = 0.2;
		double crossoverProb  = 0.8;

		Evolution evolution =
			new SubsetSumEvolution(
				values, sum, popSize,
				maxGenerations,
				mutationProb, crossoverProb);
		evolution.evolve();

		System.out.println("Result: " +
				evolution.getBest());
	}
}
