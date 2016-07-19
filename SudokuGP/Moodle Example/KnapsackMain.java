public class KnapsackMain {
	public static void main(String[] args) {
		int[]  values    = { 4,2,2,1,10};
		int[]  weights   = {12,1,2,1, 14};
		int    maxWeight = 222;
		int    popSize   = 100;
		int    maxGenerations = 10000;
		double mutationProb   = 0.2;
		double crossoverProb  = 0.8;

		Evolution evolution =
			new KnapsackEvolution(
				values, weights, maxWeight,
				popSize, maxGenerations,
				mutationProb, crossoverProb);
		evolution.evolve();

		System.out.println("Result: " +
				evolution.getBest());
	}
}
