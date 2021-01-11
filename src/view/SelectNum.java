package view;

import javax.swing.*;
import java.awt.*;

public class SelectNum extends JFrame {

   // 패널변수
   private Container selectMap;
   private   JPanel numMap;
   // 버튼변수
   private JButton[] numButton;
   // 힌트버튼
   public JButton hintButton;

   public SelectNum() {
      numButton = new JButton[10];
      hintButton = new JButton("Play the mini-game and get answer");
      
      initSelectMap();
   }

   public void initSelectMap() {
      this.setTitle("SelectNum");

      // 여기서 this는 frame하나 말하는거
      this.setSize(450, 200);
      // 패널객체 생성
      selectMap = getContentPane();

      selectMap.setLayout(null);

      numMap = new JPanel();
      numMap.setLayout(new GridLayout(1, 9,1,1));
      numMap.setBounds(15, 30, 400, 60);
      selectMap.add(numMap);

      for (int i = 0; i < 9; i++) {
         // JButton(버튼위에 뜨는 값)
         numButton[i] = new JButton(i + 1 + "");
         numButton[i].setBackground(Color.white);
         numButton[i].setForeground(Color.darkGray);
         numButton[i].setFont(numButton[i].getFont().deriveFont(16.0f));
         numButton[i].setFocusable(false);
         numMap.add(numButton[i]);
      }
      
      hintButton.setBounds(17, 100, 395, 40);
      hintButton.setBackground(Color.orange);
      hintButton.setForeground(Color.black);
      hintButton.setFont(hintButton.getFont().deriveFont(16.0f));
      hintButton.setFocusable(false);
      selectMap.add(hintButton);
   }

   public JButton[] getNumButton() {
      return this.numButton;
   }
   
   public void showSelectMap() {
      this.setVisible(true);
   }
   
   public void setVisibleHintButton(boolean flag) {
      hintButton.setVisible(flag);
   }
   
   public void exitSelectMap() {
      this.dispose();
   }
}