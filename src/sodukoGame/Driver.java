package sodukoGame;

public class Driver {
	
	public static void main(String[] args) {
		int popSize = 100;
		int maxGenerations = 50;
		double mutationProb   = 0.2;
		double crossoverProb  = 0.8;
		double goodPopulationPercent=0.4;
		
		int hight=5;
		int [][]board;
		
		// fill the board and the arraylist of the terminal and primitive
		//--------------------------------------------------------------------
		HelpFunctions.fillFunctions(Individual.functions);
		HelpFunctions.Filloperators(Individual.operators);
		
		//board=HelpFunctions.fillTheMatrix1();
		//board=HelpFunctions.fillTheMatrix2();		// very hard soducko
		//board=HelpFunctions.fillTheMatrix3();  	//very difficult and requires the use of advanced techniques
		//board=HelpFunctions.fillTheMatrix4();		//World's hardest sudoku
		
		int rnd=HelpFunctions.randomNumber(40);
		System.out.println("Sudoku :"+rnd);
		board=HelpFunctions.loadSudokuBoardFromFile(rnd);
		
		HelpFunctions.printSudokuBoard(board);
		HelpFunctions.waitFor(5);
		//---------------------------------------------------------------------
		
		
		
		
		Individual prototype= new BoardIndividual(hight, board);
		Selection sl = new TournamentSelection(mutationProb, crossoverProb,goodPopulationPercent);
		Population firstPopulation= new Population(popSize, prototype, sl);
		Evolution evolution= new Evolution(firstPopulation, maxGenerations);
		evolution.evolve();

	}	

}
