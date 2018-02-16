/*Sedak Puri
 * Project 1
 * 1/18/2018
 * PuriSedakProject1.zip
 * Game of Life: Game of life is a game based on the survival of cells based on the number of its neighbors.
 */

public class EnvironmentDriver {
	public static void main(String[] args) {
		String filename = "GameOfLife2.txt";
		Environment e = new Environment(filename);
		e.runSimulation();
	}
}


