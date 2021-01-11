package controller;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;

import minigame.MiniGame;
import minigame.OddEven;
import minigame.RockPaperScissors;
import minigame.Timing;
import minigame.ResultDialog;
import model.GameData;
import view.SelectNum;
import view.SudokuMap;

public class GameController {
	ArrayList<MiniGame> gameList = new ArrayList<MiniGame>();
	
	int gameNum;

	private GameData data;
	private SudokuMap gameMap;
	private SelectNum numChoiceMap;
	private MiniGame targetGame;

	private int buttonPosition_x;
	private int buttonPosition_y;

	private OptionButtonListener optionListener = new OptionButtonListener();
	private SudokuButtonListener sudokuListener = new SudokuButtonListener();
	private NumChoiceListener numChoiceListener = new NumChoiceListener();
	private HintButtonListener hintListener = new HintButtonListener();
	private MiniGameListener miniGameListener = new MiniGameListener();
	
	public GameController(GameData data) {
		// this.data = GameData.getinstance();
		gameList.add(new Timing());
		gameList.add(new OddEven());
		gameList.add(new RockPaperScissors());

		this.data = data;
		this.gameMap = new SudokuMap(data.getMap(), data.getLife(), data.getHint());
		this.numChoiceMap = new SelectNum();
		gameMap.refresh.addActionListener(optionListener);
		numChoiceMap.hintButton.addActionListener(hintListener);

		Random r = new Random();
		gameNum = r.nextInt(3);
		targetGame = gameList.get(gameNum);
		targetGame.getResultDialog().okButton.addActionListener(miniGameListener);

		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				gameMap.getSudokuButton()[i][j].addActionListener(sudokuListener);
			}
		}
		gameMap.printMap();
		for (int i = 0; i < 9; i++) {
			numChoiceMap.getNumButton()[i].addActionListener(numChoiceListener);
		}

	}

	// 스도쿠 판을 새로고침 하는 버튼에 추가될 리스너 클래스
	private class OptionButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("New Game")) {
				data.initData();
				gameMap.exitGameMap();
				new GameController(data);
			}
		}
	}

	// 스도쿠 버튼에 추가될 리스너 클래스
	private class SudokuButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (gameMap.getSudokuButton()[i][j] == e.getSource()) {
						if (data.getHint() == 0)
							numChoiceMap.setVisibleHintButton(false);
						setButtonPosition(i, j);
						numChoiceMap.showSelectMap();
					}
				}
			}
		}
	}

	// 숫자 선택지 버튼에 추가될 리스너 클래스
	private class NumChoiceListener implements ActionListener {
		private boolean isCorrect = false;

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 9; i++) {
				if (numChoiceMap.getNumButton()[i] == e.getSource()) {
					this.isCorrect = data.setMap(getButtonPosition_x(), getButtonPosition_y(), (i + 1));
					if (isCorrect) {
						gameMap.exitGameMap();
						GameController temp1 = new GameController(data);
						// gameMap.getSudokuButton()[getButtonPosition_x()][getButtonPosition_y()].setText(""
						// + (i + 1));

						if (data.checkComplete()) {
							temp1.gameMap.setIntro("You Success :D");
						}
						numChoiceMap.exitSelectMap();
					} else {
						data.minusLife();
						numChoiceMap.exitSelectMap();
						gameMap.exitGameMap();
						GameController temp2 = new GameController(data);
						if (data.getLife() == 0) {
							temp2.gameMap.intro.setFont(temp2.gameMap.intro.getFont().deriveFont(40.0f));
						    temp2.gameMap.intro.setBounds(340, 30, 400, 40);
							temp2.gameMap.intro.setForeground(Color.red);
							temp2.gameMap.setIntro("You Fail :(");
						}
						else {
							temp2.gameMap.setIntro("You input wrong number :(");
						}
					}
				}
			}
		}
	}

	// 힌트버튼에 추가될 리스너 클래스
	private class HintButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (numChoiceMap.hintButton == e.getSource()) {
				data.minusHint();
				targetGame.run();
			}
		}
	}

	// 미니게임에 추가될 리스너 클래스
	private class MiniGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (targetGame.getResultDialog().okButton == e.getSource()) {
				targetGame.getResultDialog().dispose();
				targetGame.dispose();
				
				if (targetGame.getResult() == 1) {
					data.setMap(getButtonPosition_x(), getButtonPosition_y(),
							data.getSolution()[getButtonPosition_x()][getButtonPosition_y()]);

					// gameMap.getSudokuButton()[getButtonPosition_x()][getButtonPosition_y()].setText(""
					// + (i + 1));

					if (data.checkComplete()) {
						gameMap.setIntro("You Success :D");
					}
					numChoiceMap.exitSelectMap();
					gameMap.exitGameMap();
					new GameController(data);
				} 
				else {
					numChoiceMap.exitSelectMap();
					gameMap.exitGameMap();
					new GameController(data);
				}

			}
		}
	}


	private void setButtonPosition(int x, int y) {
		this.buttonPosition_x = x;
		this.buttonPosition_y = y;
	}

	private int getButtonPosition_x() {
		return this.buttonPosition_x;
	}

	private int getButtonPosition_y() {
		return this.buttonPosition_y;
	}
}
