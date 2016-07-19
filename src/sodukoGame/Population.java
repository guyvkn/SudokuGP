package sodukoGame;

import java.util.Arrays;

public class Population {
	
	protected Individual[] individuals;
	private final Selection selection;

	public Population(int popSize, Individual prototype, Selection selection) {
		individuals = new Individual[popSize];
		for (int i = 0;  i < popSize;  ++i)
		{
			individuals[i] = prototype.clone();
			individuals[i].generateFullTree();
			
			//int rnd=HelpFunctions.randomNumber(4, 7);
			//individuals[i].Re_generateFullTree(rnd);
			
		}
		sort();
		
		this.selection=selection;
	}

	public Individual getBest() {
		return individuals[0];
	}
	public Individual getWorst() {
		return individuals[individuals.length-1];
	}

	public void nextGeneration() {
		Individual[] newPop = new Individual[individuals.length];

		for (int index = 0;  index < newPop.length;  ++index)
		{
			//newPop[index] = selection.reproduce(individuals);
			newPop[index]=selection.reproduce(individuals, individuals[index]);
		}

		individuals = newPop;
		sort();
	}

	private void sort() {
		Arrays.sort(individuals);
	}
	
	public Selection getSelection() {
		return selection;
	}
	public double getMutationProb() {
		return selection.getMutationProb();
	}
	public double getCrossoverProb() {
		return selection.getCrossoverProb();
	}
	public double getGoodPopulationPercent()
	{
		return selection.getGoodPopulationPercent();
	}
	public int getPopulationSize()
	{
		return individuals.length;
	}
	public int countEmptyCellInOrginalSudoku()
	{
		return ((BoardIndividual)getBest()).countEmptyCellInOrginalSudoku();
	}

	public double getAvgPopulationFitness() {
		double sum=0;
		for (int i = 0; i < individuals.length; i++) {
			sum+=individuals[i].getFitness();
		}
		return sum/(double)individuals.length;
	}
}
