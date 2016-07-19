
public class TournamentSelection implements Selection {

	private final double mutationProb;
	private final double crossoverProb;

	public TournamentSelection(double mutationProb, double crossoverProb) {
		this.mutationProb  = mutationProb;
		this.crossoverProb = crossoverProb;
	}

	@Override
	public Individual reproduce(Individual[] pop) {
		Individual p1 = select(pop);

		if (Math.random() < crossoverProb) {
			Individual p2 = select(pop);
			p1 = p1.crossover(p2);
		}

		if (Math.random() < mutationProb)
			p1 = p1.mutate();

		return p1;
	}

	private Individual select(Individual[] pop) {
		Individual i1 = pop[randomIndex(pop.length)];
		Individual i2 = pop[randomIndex(pop.length)];

		if (i1.compareTo(i2) <= 0)
			return i1;
		else
			return i2;
	}

	private int randomIndex(int max) {
		return (int)(Math.random() * max);
	}

}
