package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class SudokuMap extends JFrame {

   // 패널변수
   private Container gameMap;
   private JPanel sudokuMap;
   // 스도쿠 버튼 변수
   private JButton[][] sudokuButton;
   private int[][] temp;
   // 게임소개 변수
   public JLabel intro;
   // 생명 이미지
   private JLabel[] img;
   private ImageIcon heart;
   //힌트 이미지
   private JLabel[] hintImg;
   private ImageIcon hintIcon;
   // 새로운 스도쿠판 활성 버튼
   public JButton refresh;
   
   private int life;
   private int hint;
   
   public SudokuMap(int[][] temp, int life, int hint) {
      this.temp = temp;
      this.life = life;
      this.hint = hint;
      this.sudokuButton = new JButton[9][9];
      this.intro = new JLabel("Let's Play Sudoku Games!");
      
      this.img = new JLabel[life];
      this.heart = new ImageIcon("images/life.png");

      this.hintImg = new JLabel[hint];
      
      this.hintIcon = new ImageIcon("images/hint.png");
      this.refresh = new JButton("New Game");

      initMap();
   }
   
   private void initMap() {
      this.setTitle("Sudoku");
      // 창을 껐을때 프로그램이 같이 종료되게 하는거
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // 여기서 this는 frame하나 말하는거
      this.setSize(900, 900);
      // 패널객체 생성
      gameMap = getContentPane();
      gameMap.setLayout(null);

      sudokuMap = new JPanel();
      sudokuMap.setLayout(new GridLayout(9, 9, 2, 2));
      sudokuMap.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 2, true)));
      // game맵 위에 스도쿠 맵 절대 위치 지정
      sudokuMap.setBounds(120, 140, 630, 630);
      gameMap.add(sudokuMap);

      intro.setFont(intro.getFont().deriveFont(25.0f));
      intro.setBounds(280, 30, 400, 25);
      gameMap.add(intro);
      
      refresh.setBounds(630, 80, 120, 50);
      refresh.setBackground(Color.darkGray);
      refresh.setFont(refresh.getFont().deriveFont(17.0f));
      refresh.setForeground(Color.orange);
      refresh.setFocusable(false);
      gameMap.add(refresh);

      drawLife();
      drawHint();

      drawSudokuMap();
   }

   ImageIcon ImageSetSize(ImageIcon icon, int width, int heignt) {
      Image ximg = icon.getImage();
      Image yimg = ximg.getScaledInstance(width, heignt, Image.SCALE_SMOOTH);
      ImageIcon xyimg = new ImageIcon(yimg);
      return xyimg;
   }
   

   void drawSudokuMap() {
      if(life == 0) {
         for (int i = 0; i < 9; i++) {
               for (int j = 0; j < 9; j++) {
                  if (temp[i][j] == 0) {
                     sudokuButton[i][j] = new JButton("");
                     sudokuButton[i][j].setOpaque(false);
                     sudokuButton[i][j].setBackground(Color.WHITE);
                     sudokuButton[i][j].setEnabled(false);
                     refresh.setVisible(false);
                     
                  } else {
                     sudokuButton[i][j] = new JButton("" + temp[i][j]);
                     sudokuButton[i][j].setFont(new Font(Font.DIALOG, Font.BOLD, 25));
                     sudokuButton[i][j].setOpaque(false);
                     sudokuButton[i][j].setEnabled(false);

                  }
                  
                  sudokuMap.add(sudokuButton[i][j]); 
               }
            }
      } else {
         for (int i = 0; i < 9; i++) {
               for (int j = 0; j < 9; j++) {
                  if (temp[i][j] == 0) {
                     sudokuButton[i][j] = new JButton("");
                     sudokuButton[i][j].setBackground(Color.WHITE);
                     
                  } else {
                     sudokuButton[i][j] = new JButton("" + temp[i][j]);
                     sudokuButton[i][j].setFont(new Font(Font.DIALOG, Font.BOLD, 25));
                     sudokuButton[i][j].setOpaque(false);
                     sudokuButton[i][j].setEnabled(false);

                  }
                  
                  sudokuMap.add(sudokuButton[i][j]);
                  
               }
            }
      }
      
      
   }
   

   public void printMap() {
      this.setVisible(true);
   }
   

   public void updateMap(int[][] temp, int life) {
      this.temp = temp;
      this.life = life;
      sudokuMap.removeAll();
      drawSudokuMap();
      
   }
   
   public JButton[][] getSudokuButton() {
      return this.sudokuButton;
   }

   public void setIntro(String str) {
      this.intro.setText(str);
   }
   
   public void drawLife() {
      int countLife = life - 1;
      heart = ImageSetSize(heart, 20, 20);

      int heartTerm = 260;
      for (int i = 0; i < life; i++) {
         heartTerm += 55;
         img[i] = new JLabel();
         img[i].setIcon(heart);
         img[i].setBounds(heartTerm, 65, 20, 20);
         gameMap.add(img[i]);
      }

      for(int i = countLife; i>life-1 ; i--) {
         img[i].setVisible(false);
      }
      
      
   }
   public void paint(Graphics g) { 
      // TODO Auto-generated method stub 
      super.paint(g); // 그려지는 곳 1
      g.setColor(Color.DARK_GRAY);
      g.fillRect(127 ,380, 628, 2);
      g.fillRect(127 ,587, 628, 2);
      g.fillRect(337 ,170, 2, 628);
      g.fillRect(544 ,170, 2, 628);
      
   }

   public void drawHint() {
      int countHint = hint - 1;
      hintIcon = ImageSetSize(hintIcon, 40, 40);

      int hintTerm = 250;
      for (int i = 0; i < hint; i++) {
         hintTerm += 55;
         hintImg[i] = new JLabel();
         hintImg[i].setIcon(hintIcon);
         hintImg[i].setBounds(hintTerm, 90, 40, 40);
         gameMap.add(hintImg[i]);
      }

      for(int i = countHint; i>hint-1 ; i--) {
         hintImg[i].setVisible(false);
      }
   }
   public void exitGameMap() {
      this.dispose();
   }

}