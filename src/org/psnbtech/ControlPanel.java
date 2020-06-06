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

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JPanel;


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
	private Tetris tetris;

	private static Clip clip;

	
	public ControlPanel(Tetris tetris) {
		this.tetris = tetris;

		setPreferredSize(new Dimension(200, BoardPanel.PANEL_HEIGHT));
		setBackground(Color.BLACK);
		setLayout(null);
		
		JButton bgmON = new JButton("BGM ON");
		JButton bgmOFF = new JButton("BGM OFF");
        
		bgmON.setForeground(new Color(128, 192, 128));
		bgmON.setBackground(Color.DARK_GRAY);
		bgmON.setFocusPainted(false);
		Dimension size = bgmON.getPreferredSize();
		bgmON.setBounds(10, 80,size.width , size.height);//bgmON.setLocation(500,100);
		
		
		bgmON.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getSource().equals(bgmON)) {
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
        bgmOFF.setBounds(100, 80,sizeOFF.width , sizeOFF.height);//bgmON.setLocation(500,100);
		
		//myButton1.setPreferredSize(new Dimension(100, 100));
		
        bgmOFF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("a");
				if(e.getSource().equals(bgmOFF)) {
					clip.stop();
					
					tetris.requestFocus();
				}
			}
		});
        add(bgmOFF);
        
		try {
			String path = System.getProperty("user.dir");
			System.out.println(path);
			AudioInputStream audio = AudioSystem.getAudioInputStream(
					new File(path + "\\bgm\\1bgm.wav"));
			
			
			//C:\\Users\\kim\\Desktop\\otwojob\\kimasdjoooo\\Tetris\\src\\org\\psnbtech\\1bgm.wav
			
			
			clip = AudioSystem.getClip();
			clip.open(audio);
			//clip.start();
			//clip.loop(Clip.LOOP_CONTINUOUSLY);
		}

		catch (UnsupportedAudioFileException uae) {
			System.out.println(uae);
		}
		catch (IOException ioe) {
			System.out.println(ioe);
		}
		catch (LineUnavailableException lua) {
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
		g.setFont(LARGE_FONT);
		g.drawString("Stats", SMALL_INSET, offset = STATS_INSET);
		g.setFont(SMALL_FONT);
		g.drawString("Level: " + tetris.getLevel(), LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("Score: " + tetris.getScore(), LARGE_INSET, offset += TEXT_STRIDE);

		
		g.setFont(LARGE_FONT);
		g.drawString("Controls", SMALL_INSET, offset = CONTROLS_INSET);
		g.setFont(SMALL_FONT);
		g.drawString("A - Move Left", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("D - Move Right", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("Q - Rotate Anticlockwise", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("E - Rotate Clockwise", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("S - Drop", LARGE_INSET, offset += TEXT_STRIDE);
		g.drawString("P - Pause Game", LARGE_INSET, offset += TEXT_STRIDE);
		 	*/
		g.setFont(LARGE_FONT);
		g.drawString("[BackGround Music]", SMALL_INSET, 70);
		

		
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
