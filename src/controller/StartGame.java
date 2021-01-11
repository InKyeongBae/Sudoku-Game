package controller;

import model.GameData;

public class StartGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameData test = GameData.getinstance();
		test.initData();
	    new GameController(test);
	}
}
