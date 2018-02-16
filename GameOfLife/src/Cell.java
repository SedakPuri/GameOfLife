/*Sedak Puri
 * Project 1
 * 1/18/2018
 * PuriSedakProject1.zip
 * Game of Life: Game of life is a game based on the survival of cells based on the number of its neighbors.
 */

public class Cell {
	private boolean occupied;

	public Cell(boolean occupied) {
		this.occupied = occupied;
	}

	//Accessor for occupied
	public boolean isOccupied() {
		return occupied;
	}

	//Mutator for occupied
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
}
