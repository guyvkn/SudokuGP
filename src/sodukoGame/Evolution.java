package sodukoGame;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Evolution {

	private final Population population;
	private final int maxGenerations;
	private CSV_Writer reportGenerator;

	public Evolution(Population population, int maxGenerations) {
		this.population     = population;
		this.maxGenerations = maxGenerations;
	}

	public Individual getBest() {
		return population.getBest();
	}
	private Individual getWorst() {
		return population.getWorst();
	}

	public void evolve() {
		int gen;
		createReportFile();

		for (gen = 0;  gen < maxGenerations;  ++gen) {
			
			writeGenerationData(gen);
			
			System.out.println("Generation " + gen + ": \n" + getBest());
			System.out.println("\n");
			
			if (getBest().isIdeal())
				break;

			population.nextGeneration();
		}

		if (gen == maxGenerations)
			System.out.println("Best attempt: \n" + getBest());
		else
			System.out.println("Solution: \n" + getBest());
		
	}
	
	public void createReportFile(){
		/*
		 * create the .csv file with basic information about the experiment parameters
		 */
		reportGenerator = new CSV_Writer(new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date()) + ".csv");
		reportGenerator.createCsvFile();
		reportGenerator.appendCsvFile(new String[]{"Expirement Parameters:"});
		reportGenerator.appendCsvFile(new String[]{"Orginal Empty Cells:" , Integer.toString(population.countEmptyCellInOrginalSudoku())});
		reportGenerator.appendCsvFile(new String[]{"Population Size:" , Integer.toString(population.getPopulationSize())});
		reportGenerator.appendCsvFile(new String[]{"Max Generations:" , Integer.toString(maxGenerations)});
		reportGenerator.appendCsvFile(new String[]{"Crossover Probability:", Double.toString(population.getCrossoverProb())});
		reportGenerator.appendCsvFile(new String[]{"Mutation Probability:",  Double.toString(population.getMutationProb())});
		reportGenerator.appendCsvFile(new String[]{"Percent of good indviduals from population:",  Double.toString(population.getGoodPopulationPercent())});
		reportGenerator.appendCsvFile(new String[]{""}); // just add a line separator

		
		// write the function and terminal sets which were used in the experiment
		reportGenerator.appendCsvFile(new String[]{"Primitive Set:"});
		String[] primitiveArr = new String[Individual.operators.size()];
		primitiveArr = Individual.operators.toArray(primitiveArr);
		reportGenerator.appendCsvFile(primitiveArr);
		reportGenerator.appendCsvFile(new String[]{"Terminal Set:"});	
		String[] terminalArr = new String[Individual.functions.size()];
		terminalArr = Individual.functions.toArray(terminalArr);
		reportGenerator.appendCsvFile(terminalArr);
		
		reportGenerator.appendCsvFile(new String[]{""}); // just add a line separator
		reportGenerator.appendCsvFile(new String[]{""}); // just add a line separator
		reportGenerator.appendCsvFile(new String[]{"Generation","Worst Individual Fitness","Best Individual Fitness","Average Fitness", "Best Individual Tree - Prefix","Best Individual Tree - Infix"});
	}
	public void writeGenerationData(int gen){
		/*
		 * write the current generation data
		 * generation number, worst individual name and fitness, best individual and fitness, average fitness, best individual tree representation as a flat string
		 */
		reportGenerator.appendCsvFile(new String[]{
				Integer.toString(gen),
				Double.toString(getWorst().getFitness()),
				Double.toString(getBest().getFitness()),
				Double.toString(population.getAvgPopulationFitness()),
				getBest().treeAsPrefixExpression(),
				getBest().treeAsInfixExpression()
				});
	}

	
}