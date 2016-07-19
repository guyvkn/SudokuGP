
public class Evolution {

	private final Population population;
	private final int        maxGenerations;

	public Evolution(Population population, int maxGenerations) {
		this.population     = population;
		this.maxGenerations = maxGenerations;
	}

	public Individual getBest() {
		return population.getBest();
	}

	public void evolve() {
		int gen;

		for (gen = 0;  gen < maxGenerations;  ++gen) {
			System.out.println("Generation " + gen + ": " + getBest());

			if (getBest().isIdeal())
				break;

			population.nextGeneration();
		}

		if (gen == maxGenerations)
			System.out.println("Best attempt: " + getBest());
		else
			System.out.println("Solution: " + getBest());
	}

	/*
	public void dump() {
		System.out.println("--------------------");

		for (Object o: population) {
			Individual ind = (Individual) o;
			System.out.println(ind);
		}

		System.out.println("--------------------");
	}
	*/

}
