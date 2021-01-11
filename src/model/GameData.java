package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameData {
	private static GameData instance;
	private int[][] solution; // Generated solution.
	private int[][] game; // Generated game with user input.
	private boolean[][] check;
	private int life = 5;
	private int hint = 5;
	
	GameData() {

	}

	public static GameData getinstance() {
		if (instance == null) {
			instance = new GameData();
			return instance;
		} else {
			return instance;
		}
	}

	public int[][] makeSudoku(int[][] game, int index) {
		if (index > 80)
			return game;
		int x = index % 9;
		int y = index / 9;

		List<Integer> solutions = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) {
			solutions.add(i);
		}
		Collections.shuffle(solutions);

		while (solutions.size() > 0) {
			int number = getPossibleNumber(game, x, y, solutions);
			if (number == -1)
				return null;
			game[y][x] = number;
			int[][] copyGame = makeSudoku(game, index + 1);
			if (copyGame != null)
				return copyGame;
			game[y][x] = 0;
		}
		return null;

	}

	private boolean checkPossibleX(int[][] game, int y, int number) {
		for (int x = 0; x < 9; x++) {
			if (game[y][x] == number)
				return false;
		}
		return true;
	}

	private boolean checkPossibleY(int[][] game, int x, int number) {
		for (int y = 0; y < 9; y++) {
			if (game[y][x] == number)
				return false;
		}
		return true;
	}

	private boolean checkPossibleBlock(int[][] game, int x, int y, int number) {
		int x1 = x < 3 ? 0 : x < 6 ? 3 : 6;
		int y1 = y < 3 ? 0 : y < 6 ? 3 : 6;
		for (int yy = y1; yy < y1 + 3; yy++) {
			for (int xx = x1; xx < x1 + 3; xx++) {
				if (game[yy][xx] == number)
					return false;
			}
		}
		return true;
	}

	private int getPossibleNumber(int[][] game, int x, int y, List<Integer> numbers) {
		while (numbers.size() > 0) {
			int number = numbers.remove(0);
			if (checkPossibleX(game, y, number) && checkPossibleY(game, x, number)
					&& checkPossibleBlock(game, x, y, number))
				return number;
		}
		return -1;
	}

	private boolean checkValid(int[][] game) {
		return checkValid(game, 0, new int[] { 0 }); // 값 대신 참조로 정수를 전달
	}

	private boolean checkValid(int[][] game, int index, int[] solutionsNumber) {
		if (index > 80)
			return ++solutionsNumber[0] == 1;

		int x = index / 9;
		int y = index % 9;

		if (game[x][y] == 0) {
			List<Integer> numbers = new ArrayList<Integer>();
			for (int i = 1; i <= 9; i++)
				numbers.add(i);

			while (numbers.size() > 0) {
				int number = getPossibleNumber(game, y, x, numbers);
				if (number == -1)
					break;
				game[x][y] = number;

				if (!checkValid(game, index + 1, solutionsNumber)) {
					game[x][y] = 0;
					return false;
				}
				game[x][y] = 0;
			}
		} else if (!checkValid(game, index + 1, solutionsNumber))
			return false;

		return true;
	}

	@SuppressWarnings("unused")
	private int[][] generateGame(int[][] game) {
		List<Integer> positions = new ArrayList<Integer>();
		for (int i = 0; i < 81; i++)
			positions.add(i);
		Collections.shuffle(positions);
		return generateGame(game, positions);
	}

	private int[][] generateGame(int[][] game, List<Integer> positions) {
		while (positions.size() > 0) {
			int position = positions.remove(0);
			int x = position % 9;
			int y = position / 9;
			int temp = game[y][x];
			game[y][x] = 0;

			if (!checkValid(game))
				game[y][x] = temp;
		}

		return game;
	}

	private int[][] copy(int[][] game) {
		int[][] copy = new int[9][9];
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++)
				copy[y][x] = game[y][x];
		}
		return copy;
	}

	public void initData() {
		solution = new int[9][9];
		solution = makeSudoku(new int[9][9], 0);
		check = new boolean[9][9];
		game = generateGame(copy(solution));

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (game[i][j] != 0) {
					check[i][j] = true;
				} 
				else {
					check[i][j] = false;
				}
			}
		}
	}

	public boolean checkComplete() {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (solution[x][y] != game[x][y])
					return false;
			}
		}
		return true;
	}

	public int[][] getMap() {
		int retMap[][] = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				retMap[i][j] = game[i][j];
			}
		}
		return retMap;
	}

	public void checkInputNum(int input) {

		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++)
				check[x][y] = input == solution[x][y];
		}

	}

	public boolean setMap(int x, int y, int num) {
		checkInputNum(num);
		if (check[x][y]) {
			game[x][y] = num;
			return true;
		}
		return false;
	}
	
	public int[][] getSolution() {
		return solution;
	}

	
	public int getLife() {
		return life;
	}

	public void minusLife() {
		life--;
	}
	public int getHint() {
		return hint;
	}

	public void minusHint() {
		hint--;
	}

}