package org.psnbtech;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JPanel {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 2181495598854992747L;

	/**
	 * The dimensions of each tile on the next piece preview.
	 */
	private static final int TILE_SIZE = BoardPanel.TILE_SIZE >> 1;

	/**
	 * The width of the shading on each tile on the next piece preview.
	 */
	private static final int SHADE_WIDTH = BoardPanel.SHADE_WIDTH >> 1;

	/**
	 * The number of rows and columns in the preview window. Set to 5 because we can
	 * show any piece with some sort of padding.
	 */
	private static final int TILE_COUNT = 5;

	/**
	 * The center x of the next piece preview box.
	 */
	private static final int SQUARE_CENTER_X = 130;

	/**
	 * The center y of the next piece preview box.
	 */
	private static final int SQUARE_CENTER_Y = 65;

	/**
	 * The size of the next piece preview box.
	 */
	private static final int SQUARE_SIZE = (TILE_SIZE * TILE_COUNT >> 1);

	/**
	 * The number of pixels used on a small insets (generally used for categories).
	 */
	private static final int SMALL_INSET = 20;

	/**
	 * The number of pixels used on a large insets.
	 */
	private static final int LARGE_INSET = 40;

	/**
	 * The y coordinate of the stats category.
	 */
	private static final int STATS_INSET = 175;

	/**
	 * The y coordinate of the controls category.
	 */
	private static final int CONTROLS_INSET = 300;

	/**
	 * The number of pixels to offset between each string.
	 */
	private static final int TEXT_STRIDE = 25;

	/**
	 * The small font.
	 */
	private static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 11);

	/**
	 * The large font.
	 */
	private static final Font LARGE_FONT = new Font("Tahoma", Font.BOLD, 13);

	/**
	 * The color to draw the text and preview box in.
	 */
	private static final Color DRAW_COLOR = new Color(128, 192, 128);

	/**
	 * The Tetris instance.
	 */
	
	private JTextField userName;
	
	public String getUserName() {
		return userName.getText();
	}
	
	public void setUpdateRankUser(ArrayList<String> users) {
		
		remove(rankUser1);
		remove(rankUser2);
		remove(rankUser3);
		remove(rankUser4);
		remove(rankUser5);
		
		rankUser1 = null;
		rankUser2 = null;
		rankUser3 = null;
		rankUser4 = null;
		rankUser5 = null;
		
		System.out.println(users.get(0));
		System.out.println(users.get(1));
		System.out.println(users.get(2));
		System.out.println(users.get(3));
		System.out.println(users.get(4));
		
		//rankUser1.setText(users.get(0));
		//rankUser2.setText(users.get(1));
		//rankUser3.setText(users.get(2));
		//rankUser4.setText(users.get(3));
		//rankUser5.setText(users.get(4));
		
		rankUser1 = new JLabel(users.get(0));
		rankUser2 = new JLabel(users.get(1));
		rankUser3 = new JLabel(users.get(2));
		rankUser4 = new JLabel(users.get(3));
		rankUser5 = new JLabel(users.get(4));
		
		rankUser1.setForeground(new Color(128, 192, 128));
		Dimension sizerankUser1 = rankUser1.getPreferredSize();
		rankUser1.setBounds(20, 310, sizerankUser1.width, sizerankUser1.height);// bgmON.setLocation(500,100);
		add(rankUser1);
		
		rankUser2.setForeground(new Color(128, 192, 128));
		Dimension sizerankUser2 = rankUser2.getPreferredSize();
		rankUser2.setBounds(20, 330, sizerankUser2.width, sizerankUser2.height);// bgmON.setLocation(500,100);
		add(rankUser2);
		
		rankUser3.setForeground(new Color(128, 192, 128));
		Dimension sizerankUser3 = rankUser3.getPreferredSize();
		rankUser3.setBounds(20, 350, sizerankUser3.width, sizerankUser3.height);// bgmON.setLocation(500,100);
		add(rankUser3);
		
		rankUser4.setForeground(new Color(128, 192, 128));
		Dimension sizerankUser4 = rankUser4.getPreferredSize();
		rankUser4.setBounds(20, 370, sizerankUser4.width, sizerankUser4.height);// bgmON.setLocation(500,100);
		add(rankUser4);
		
		rankUser5.setForeground(new Color(128, 192, 128));
		Dimension sizerankUser5 = rankUser5.getPreferredSize();
		rankUser5.setBounds(20, 390, sizerankUser5.width, sizerankUser5.height);// bgmON.setLocation(500,100);
		add(rankUser5);
		
		revalidate();
		repaint();
	}

	private JLabel rankUser1;
	private JLabel rankUser2;
	private JLabel rankUser3;
	private JLabel rankUser4;
	private JLabel rankUser5;
	
	private Tetris tetris;

	private static Clip clip;

	public ControlPanel(Tetris tetris) {
		this.tetris = tetris;

		setPreferredSize(new Dimension(200, BoardPanel.PANEL_HEIGHT));
		setBackground(Color.BLACK);
		setLayout(null);

		JButton bgmON = new JButton("BGM ON");
		JButton bgmOFF = new JButton("BGM OFF");

		JButton gameStart = new JButton("START");
		JButton gameReset = new JButton("RESET");
		
		userName = new JTextField("USER_1", 13);
		
		rankUser1 = new JLabel("1.");
		rankUser2 = new JLabel("2.");
		rankUser3 = new JLabel("3.");
		rankUser4 = new JLabel("4.");
		rankUser5 = new JLabel("5.");
		
		bgmON.setForeground(new Color(128, 192, 128));
		bgmON.setBackground(Color.DARK_GRAY);
		bgmON.setFocusPainted(false);
		Dimension size = bgmON.getPreferredSize();
		bgmON.setBounds(10, 80, size.width, size.height);// bgmON.setLocation(500,100);

		bgmON.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (e.getSource().equals(bgmON)) {
					clip.start();
					clip.loop(Clip.LOOP_CONTINUOUSLY);

					tetris.requestFocus();
				}
			}
		});
		add(bgmON);

		bgmOFF.setForeground(new Color(128, 192, 128));
		bgmOFF.setBackground(Color.DARK_GRAY);
		bgmOFF.setFocusPainted(false);

		Dimension sizeOFF = bgmOFF.getPreferredSize();
		bgmOFF.setBounds(100, 80, sizeOFF.width, sizeOFF.height);// bgmON.setLocation(500,100);

		// myButton1.setPreferredSize(new Dimension(100, 100));

		bgmOFF.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource().equals(bgmOFF)) {
					clip.stop();

					tetris.requestFocus();
				}
			}
		});
		add(bgmOFF);

		gameStart.setForeground(new Color(128, 192, 128));
		gameStart.setBackground(Color.DARK_GRAY);
		gameStart.setFocusPainted(false);
		Dimension sizegameStart = gameStart.getPreferredSize();
		gameStart.setBounds(10, 180, sizegameStart.width, sizegameStart.height);// bgmON.setLocation(500,100);

		gameStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (e.getSource().equals(gameStart)) {

					if(tetris.isGameOver() || tetris.isNewGame()) {
						tetris.allGameReset();
					}
					
					tetris.requestFocus();
				}
			}
		});
		add(gameStart);

		gameReset.setForeground(new Color(128, 192, 128));
		gameReset.setBackground(Color.DARK_GRAY);
		gameReset.setFocusPainted(false);
		Dimension sizegameReset = gameStart.getPreferredSize();
		gameReset.setBounds(84, 180, sizegameReset.width, sizegameReset.height);// bgmON.setLocation(500,100);

		gameReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (e.getSource().equals(gameReset)) {

					if (tetris.isGameOver() || tetris.isNewGame() == false) {
						tetris.allGameReset();

					}
					tetris.requestFocus();
				}
			}
		});
		add(gameReset);

		userName.setCaretColor(new Color(128, 192, 128));
		userName.setForeground(new Color(128, 192, 128));
		userName.setBackground(Color.DARK_GRAY);
		Dimension sizegameuserName = userName.getPreferredSize();
		userName.setBounds(10, 220, sizegameuserName.width, sizegameuserName.height + 2);// bgmON.setLocation(500,100);
		userName.setHorizontalAlignment(JTextField.CENTER);
		add(userName);
		
		
		rankUser1.setForeground(new Color(128, 192, 128));
		Dimension sizerankUser1 = rankUser1.getPreferredSize();
		rankUser1.setBounds(20, 310, sizerankUser1.width, sizerankUser1.height);// bgmON.setLocation(500,100);
		add(rankUser1);
		
		rankUser2.setForeground(new Color(128, 192, 128));
		Dimension sizerankUser2 = rankUser2.getPreferredSize();
		rankUser2.setBounds(20, 330, sizerankUser2.width, sizerankUser2.height);// bgmON.setLocation(500,100);
		add(rankUser2);
		
		rankUser3.setForeground(new Color(128, 192, 128));
		Dimension sizerankUser3 = rankUser3.getPreferredSize();
		rankUser3.setBounds(20, 350, sizerankUser3.width, sizerankUser3.height);// bgmON.setLocation(500,100);
		add(rankUser3);
		
		rankUser4.setForeground(new Color(128, 192, 128));
		Dimension sizerankUser4 = rankUser4.getPreferredSize();
		rankUser4.setBounds(20, 370, sizerankUser4.width, sizerankUser4.height);// bgmON.setLocation(500,100);
		add(rankUser4);
		
		rankUser5.setForeground(new Color(128, 192, 128));
		Dimension sizerankUser5 = rankUser5.getPreferredSize();
		rankUser5.setBounds(20, 390, sizerankUser5.width, sizerankUser5.height);// bgmON.setLocation(500,100);
		add(rankUser5);
		
		try {
			String path = System.getProperty("user.dir");
			System.out.println(path);
			AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path + "\\bgm\\1bgm.wav"));

			clip = AudioSystem.getClip();
			clip.open(audio);
			// clip.start();
			// clip.loop(Clip.LOOP_CONTINUOUSLY);
		}

		catch (UnsupportedAudioFileException uae) {
			System.out.println(uae);
		} catch (IOException ioe) {
			System.out.println(ioe);
		} catch (LineUnavailableException lua) {
			System.out.println(lua);
		}

		tetris.requestFocus();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Set the color for drawing.
		g.setColor(DRAW_COLOR);

		/*
		 * This variable stores the current y coordinate of the string. This way we can
		 * re-order, add, or remove new strings if necessary without needing to change
		 * the other strings.
		 */
		int offset;

		/*

		 * g.setFont(LARGE_FONT); g.drawString("Controls", SMALL_INSET, offset =
		 * CONTROLS_INSET); g.setFont(SMALL_FONT); g.drawString("A - Move Left",
		 * LARGE_INSET, offset += TEXT_STRIDE); g.drawString("D - Move Right",
		 * LARGE_INSET, offset += TEXT_STRIDE); g.drawString("Q - Rotate Anticlockwise",
		 * LARGE_INSET, offset += TEXT_STRIDE); g.drawString("E - Rotate Clockwise",
		 * LARGE_INSET, offset += TEXT_STRIDE); g.drawString("S - Drop", LARGE_INSET,
		 * offset += TEXT_STRIDE); g.drawString("P - Pause Game", LARGE_INSET, offset +=
		 * TEXT_STRIDE);
		 */
		g.setFont(LARGE_FONT);
		g.drawString("[BackGround Music]", SMALL_INSET, 70);

		g.setFont(LARGE_FONT);
		g.drawString("[Game Setting]", SMALL_INSET, 170);

		g.setFont(LARGE_FONT); 
		g.drawString("[Game Rank]", SMALL_INSET, 300);
	}

	/**
	 * Draws a tile onto the preview window.
	 * 
	 * @param type The type of tile to draw.
	 * @param x    The x coordinate of the tile.
	 * @param y    The y coordinate of the tile.
	 * @param g    The graphics object.
	 */
	private void drawTile(TileType type, int x, int y, Graphics g) {
		/*
		 * Fill the entire tile with the base color.
		 */
		g.setColor(type.getBaseColor());
		g.fillRect(x, y, TILE_SIZE, TILE_SIZE);

		/*
		 * Fill the bottom and right edges of the tile with the dark shading color.
		 */
		g.setColor(type.getDarkColor());
		g.fillRect(x, y + TILE_SIZE - SHADE_WIDTH, TILE_SIZE, SHADE_WIDTH);
		g.fillRect(x + TILE_SIZE - SHADE_WIDTH, y, SHADE_WIDTH, TILE_SIZE);

		/*
		 * Fill the top and left edges with the light shading. We draw a single line for
		 * each row or column rather than a rectangle so that we can draw a nice looking
		 * diagonal where the light and dark shading meet.
		 */
		g.setColor(type.getLightColor());
		for (int i = 0; i < SHADE_WIDTH; i++) {
			g.drawLine(x, y + i, x + TILE_SIZE - i - 1, y + i);
			g.drawLine(x + i, y, x + i, y + TILE_SIZE - i - 1);
		}
	}

}
