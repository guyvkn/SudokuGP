import java.io.PrintStream;

/**
 * Code to be put in the submission system.
 *
 * The submission system just compiles it
 * (it is not executed).
 */
public class TestRunner {
	public static boolean runTest(PrintStream out) {
		// Individual - default constructor and abstracts
		Individual ind = new Individual() {
			// Checks that not public in Individual
			@Override
			protected double evaluate() {
				return 0;
			}

			@Override
			public Individual crossover(Individual other) {
				return null;
			}

			@Override
			public Individual mutate() {
				return null;
			}
		};

		// getFitness()
		double  x = ind.getFitness();
		boolean b = ind.isIdeal();
		b = !b;

		// Cloneable
		java.lang.Cloneable indClo = ind;
		ind = ind.clone();
		ind = (Individual) indClo;

		// Comparable
		java.lang.Comparable indComp = ind;
		int a = ind.compareTo(ind);
		ind = (Individual) indComp;

		// Variable
		Variable indVar = ind;
		ind = indVar.mutate();
		ind = indVar.crossover(ind);

		// Selection
		Selection sel = new Selection() {
			@Override
			public Individual reproduce(Individual[] pop) {
				return null;
			}
		};

		// Tournament selection
		sel = new TournamentSelection(x, x);
		sel.reproduce((Individual[])null);

		// Population
		Population pop = new Population(a, ind, sel);
		ind = pop.getBest();
		pop.nextGeneration();

		// Iterable
		/*
		java.lang.Iterable iterable = pop;
		java.util.Iterator it = iterable.iterator();
		it.hasNext();
		*/

		// Evolution
		Evolution evo = new Evolution(pop, a);
		ind = evo.getBest();
		evo.evolve();
		// evo.dump();

		// SubsetSumEvolution
		evo = new SubsetSumEvolution((int[])null, a, a, a, x, x);

		// SubsetSumIndividual
		SubsetSumIndividual ssInd = new SubsetSumIndividual((int[])null, a);
		ind = ssInd;
		ssInd = ssInd.clone();

		ssInd = new SubsetSumIndividual((int[])null, a) {
			// Checks that not public in SubsetSumIndividual
			@Override
			protected double evaluate() {
				return super.evaluate();
			}
		};

		// SubsetSumMain
		// SubsetSumMain.main((String[])null);

		// KnapsackEvolution
		evo = new KnapsackEvolution((int[])null, (int[])null, a, a, a, x, x);

		// KnapSackIndividual
		ssInd = new KnapsackIndividual((int[])null, (int[])null, a) {
			// Checks that not public in KnapsackIndividual
			@Override
			protected double evaluate() {
				return super.evaluate();
			}
		};

		// KnapsackMain
		// KnapsackMain.main((String[])null);

		return false;
	}
}
