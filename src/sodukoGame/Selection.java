package sodukoGame;

public interface Selection {

	Individual reproduce(Individual[] pop);
	Individual reproduce(Individual[] pop, Individual p1);
	double getMutationProb();
	double getCrossoverProb();
	double getGoodPopulationPercent();

}
