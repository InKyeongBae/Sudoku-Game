package minigame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public abstract class MiniGame extends JFrame{
	public MiniGame() {
	}
	public abstract void run();
	protected abstract void init();
	protected abstract void start();
	public abstract int getResult();
	public abstract ResultDialog getResultDialog();
	
	protected ImageIcon ImageSetSize(ImageIcon icon, int width, int heignt) {
		Image ximg = icon.getImage();
		Image yimg = ximg.getScaledInstance(width, heignt, Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg);
		return xyimg;
	}
}

