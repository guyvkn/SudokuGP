package sodukoGame;

public class TournamentSelection implements Selection {

	private final double mutationProb;
	private final double crossoverProb;
	private final double goodPopulationPercent;

	public TournamentSelection(double mutationProb, double crossoverProb,double goodPopulationPercent) {
		this.mutationProb  = mutationProb;
		this.crossoverProb = crossoverProb;
		this.goodPopulationPercent=goodPopulationPercent;
	}
	
	public double getMutationProb() {
		return mutationProb;
	}

	public double getCrossoverProb() {
		return crossoverProb;
	}

	public double getGoodPopulationPercent() {
		return goodPopulationPercent;
	}

	public Individual reproduce(Individual[] pop, Individual p1)
	{
		if (Math.random() < crossoverProb) {
			Individual p2 = select(pop);
			p1 = p1.crossover(p2);
		}
		
		if (Math.random() < mutationProb)
		{
			p1 = p1.mutate();
		}
		return p1;
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
		
		return pop[randomIndex((int)((pop.length)*goodPopulationPercent))];
	}

	private int randomIndex(int max) {
		return (int)(Math.random() * max);

	}

}
