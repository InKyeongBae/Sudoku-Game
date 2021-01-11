package minigame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Timing extends MiniGame {
	private JPanel info_panel, match_panel;
	private JLabel inst, info;
	private JLabel[] target;
	private JButton player;
	private ImageIcon[] icons;
	private String[] image_route = { "images/apeach.jpg", "images/dog.jpg", "images/frog.jpg", "images/green.jpg",
			"images/lion.jpg", "images/mumin.jpg", "images/paper.jpg", "images/puang.jpg", "images/qq.jpg",
			"images/what.jpg", "images/question.png" };
	private boolean ischange = false;
	private int target_idx = 0;
	private int inst_idx;
	public static int index = 0;
	public ResultDialog resultdialog;
	private int level = 1;

	public Timing() {
		resultdialog = new ResultDialog(this);
	}

	@Override
	public void run() {
		setTitle("Timing");
		this.init(); // 화면 구성
		this.start(); // 이벤트, 동작 처리
		this.setBounds(500, 100, 600, 600); // 화면크기
		this.setResizable(false);
		setVisible(true);
	}

	private void changeImage(JLabel target) {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if (ischange) {
					if (index == 10) {
						index = 0;
					}
					target.setIcon(icons[index]);
					index++;
				} else if (!ischange) {
					timer.cancel();
				}
			}
		};
		timer.schedule(task, 0, 500);
	}

	@Override
	public int getResult() {
		if (inst_idx == (index - 1))
			return 1;
		return 0;
	}

	@Override
	protected void init() {
		// image icon으로 변환
		icons = new ImageIcon[11];
		for (int i = 0; i < 11; i++) {
			icons[i] = new ImageIcon(image_route[i]);
			icons[i] = ImageSetSize(icons[i], 180, 180);
		}

		// inst 맞추어야 하는 그림
		inst = new JLabel();
		// 무작위로 이미지 하나 선택
		Random r = new Random();
		inst_idx = r.nextInt(image_route.length - 1);
		String r_image_route = image_route[inst_idx];
		// 사진 삽입
		ImageIcon inst_icon = new ImageIcon(r_image_route);
		inst_icon = ImageSetSize(inst_icon, 180, 180);
		inst.setIcon(inst_icon);

		// info
		info = new JLabel("왼쪽 그림과 똑같이\n" + level + "번 맞추세요.");
		info.setFont(new Font("Serif", Font.BOLD, 20));
		info.setHorizontalAlignment(JLabel.CENTER);

		info_panel = new JPanel();
		info_panel.setLayout(new FlowLayout());
		info_panel.add(inst);
		info_panel.add(info);

		target = new JLabel[3];
		// first level = 1
		target[0] = new JLabel();
		target[0].setIcon(icons[10]);
		// second: level = 2
		target[1] = new JLabel();
		target[1].setIcon(icons[10]);
		// third: level = 3
		target[2] = new JLabel();
		target[2].setIcon(icons[10]);

		match_panel = new JPanel();
		match_panel.setLayout(new GridLayout(1, 3, 15, 0));
		for (int i = 0; i < level; i++) {
			match_panel.add(target[i]); // 그림 하나만 맞추는 난이도
		}

		// player
		player = new JButton("Click to Start");
		player.setPreferredSize(new Dimension(500, 80));
		player.setBackground(new Color(255, 255, 255));

		setLayout(new FlowLayout(1, 0, 25));
		add(info_panel);
		add(match_panel);
		add(player);
	}

	@Override
	protected void start() {
		ActionListener startlistener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == player) {
					if (!ischange) {
						player.setText("Click to Stop");
						changeImage(target[target_idx]);
						ischange = true;
					} else if (ischange) {
						ischange = false;
						target_idx++;
						int result = getResult();
						if (target_idx >= level && result == 1) {
							resultdialog.setResult(1);
							resultdialog.setVisible(true);
							player.setEnabled(false);
						} else if (result == 0) {
							resultdialog.setResult(0);
							resultdialog.setVisible(true);
							player.setEnabled(false);
						}
					}
				}
			}
		};
		player.addActionListener(startlistener);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public ResultDialog getResultDialog() {
		return this.resultdialog;
	}

}