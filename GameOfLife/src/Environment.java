/*Sedak Puri
 * Project 1
 * 1/18/2018
 * PuriSedakProject1.zip
 * Game of Life: Game of life is a game based on the survival of cells based on the number of its neighbors.
 */

import java.util.Scanner;
import edu.princeton.cs.introcs.StdDraw;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Environment {
	private int rows;
	private int columns;
	private Cell[][] cell;
	private Scanner file;

	public Environment(String filename) {
		file = null;

		//Importing the file
		try {
			file = new Scanner(new FileInputStream(filename));
		} catch (FileNotFoundException e) {
			System.out.println("Could not find file. Exiting system.");
			System.exit(0);
		}

		//Assigning values to rows and columns
		rows = file.nextInt();
		columns = file.nextInt();
		cell = new Cell[columns][rows];

		while(file.hasNext()) {
			//Initialization of Cell array of booleans
			for(int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					cell[i][j] = new Cell(file.nextInt() == 1);
				}
			}
		}
	}

	//Put Conditions of next generation in here
	public void update() {
		Cell[][] nextGeneration = new Cell[rows][columns];

		//Making a new array of Cell type that is an array of false booleans
		for(int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				nextGeneration[i][j] = new Cell(false);
			}
		}

		//Invoking of the neighborCalculations method to determine/change survival status
		for(int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				nextGeneration[i][j].setOccupied(neighborCalculations(i , j));
			}
		}

		//Making original cell point to the new generation cell array
		cell = nextGeneration;	
	}

	//Put increment counter in here to determine the amount of alive neighbors
	public boolean neighborCalculations(int xVal, int yVal) {
		int neighbors = 0;
		int x = xVal; //rows
		int y = yVal; //columns

		//Top Left
		if ((x - 1 >= 0) && (y - 1 >= 0) && (cell[x - 1][y - 1].isOccupied())) {
			neighbors++;
		}

		//Top
		if ((x - 1 >= 0) && (y < cell[0].length) && (cell[x - 1][y].isOccupied())) {
			neighbors++;
		}

		//Top Right
		if ((x - 1 >= 0) && (y + 1 < cell[0].length) && (cell[x - 1][y + 1].isOccupied())) {
			neighbors++;
		}

		//Left
		if ((x >= 0) && (y - 1 >= 0) && (cell[x][y - 1].isOccupied())) {
			neighbors++;
		}

		//Right
		if ((x < cell.length) && (y + 1 < cell[0].length) && (cell[x][y + 1].isOccupied())) {
			neighbors++;
		}

		//Bottom Left	  
		if ((x + 1 < cell.length) && (y - 1 >= 0) && (cell[x + 1][y - 1].isOccupied())) {
			neighbors++;
		}

		//Bottom
		if ((x + 1 < cell.length) && (y < cell[0].length) && (cell[x + 1][y].isOccupied())) {
			neighbors++;
		}

		//Bottom Right
		if ((x + 1 < cell.length) && (y + 1 < cell[0].length) && (cell[x + 1][y + 1].isOccupied())) {
			neighbors++;
		}

		//Survival
		switch(neighbors) {
		case 0:
		case 1:
			return false;
		case 2:
			return cell[x][y].isOccupied();
		case 3:
			return true;
		default:
			return false;
		}
	}

	//Displays the boxes of the new generation
	public void displayGrid() {
		//Grids
		for(int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				if (cell[i][j].isOccupied() == true) {
					showRectangle(j*(1.0/columns) + (0.5)/columns, i*(1.0/rows) + (0.5)/rows);
				}
			}
		}
	}

	//Method to display a red square for the "alive" cells
	public void showRectangle(double x, double y) {
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledRectangle(x, 1 - y, (0.5)*(1.0/columns), (0.5)*(1.0/rows));
	}

	//Method to run the simulation
	public void runSimulation(){
		StdDraw.enableDoubleBuffering();
		displayGrid();

		//Animation Loop
		while(true){
			StdDraw.clear();
			update();
			displayGrid();
			StdDraw.show();
			StdDraw.pause(75);
		}
	}
}