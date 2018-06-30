package sem3.game; //TODO Comment Properly

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import sem3.game.entity.mobs.Enemy;
import sem3.game.entity.mobs.Player;
import sem3.game.graphics.Display;
import sem3.game.graphics.sprite.Sprite;
import sem3.game.input.Keyboard;
import sem3.game.level.Level;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 900; // Defining the width and height of the window
	public static final int HEIGHT = WIDTH / 16 * 9;

	public static final int TILE_SIZE = 48;

	public boolean running = false; // Boolean variable to run the game until it is false

	private long base_update_time; // Variables required to control the numbers of ups and fps
	private long base_render_time;

	private int update_limit = 60;
	private int render_limit = 60;

	private long updates = 1_000_000_000 / update_limit;
	private long frames = 1_000_000_000 / render_limit;

	private boolean update_check = true;
	private boolean render_check = true;

	private final int Y = 575; // Y Coordinate which determines with respect to what the level gets rendered

	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // Making a buffer for the window to display pixels

	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); // Loading and linking the buffer into the pixels

	private Thread thread; // Defining a thread on which we run the window/ game
	private JFrame frame; // Making a JFrame object to set the attributes of the game
	private Random random; // Random object only for testing purposes

	private Level level; // Object of user defined classes
	private Display display;
	private Keyboard keyInput;
	private Player player;
	private Enemy enemy;

	public Game() {
		Dimension size = new Dimension(WIDTH, HEIGHT); // Defining the size of canvas
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		frame = new JFrame(); // Instantiating the objects
		random = new Random();
		display = new Display(WIDTH, HEIGHT, pixels);
		level = new Level("/textures/levels/level.png");
		keyInput = new Keyboard();
		player = new Player(500, 1600, Sprite.playerSprites, level, keyInput); 
		level.addMobs(player);
		addEnemy();
		addKeyListener(keyInput);
	}

	private void addEnemy() {
		enemy = new Enemy(2115, 1536, Sprite.enemySprites, level, player);
		level.addMobs(enemy);
		enemy = new Enemy(2890, 1632, Sprite.enemySprites, level, player);
		level.addMobs(enemy);
		enemy = new Enemy(3565, 1536, Sprite.enemySprites, level, player);
		level.addMobs(enemy);
		enemy = new Enemy(4525, 1632, Sprite.enemySprites, level, player);
		level.addMobs(enemy);
		enemy = new Enemy(6148, 1632, Sprite.enemySprites, level, player);
		level.addMobs(enemy);
		enemy = new Enemy(6727, 1248, Sprite.enemySprites, level, player);
		level.addMobs(enemy);
		enemy = new Enemy(8254, 1440, Sprite.enemySprites, level, player);
		level.addMobs(enemy);
		enemy = new Enemy(9512, 1296, Sprite.enemySprites, level, player);
		level.addMobs(enemy);
		enemy = new Enemy(10945, 1152, Sprite.enemySprites, level, player);
		level.addMobs(enemy);
		
		
	}

	public synchronized void start() { // Starting the thread
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}

	public synchronized void stop() { // Stopping the thread
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void update() { // Update method to handle the logic of the game
		level.update();
	}

	private void render() { // Render method to handle the rendering of the game
		BufferStrategy buffer = getBufferStrategy();
		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = buffer.getDrawGraphics();
		display.clear();
		
		level.render(display, player.getX() - WIDTH / 2, player.getY() - HEIGHT / 2); //// TODO

		display.renderUI(10, 10, 200, 20, (int) (200.0 * player.healthRatio()), true);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		buffer.show();
	}

	public void run() { // Run method which is automatically called with the thread, this runs the game
		while (running) {
			requestFocus();
			if (update_check) {
				base_update_time = System.nanoTime();
				update_check = false;
			}
			if (render_check) {
				base_render_time = System.nanoTime();
				render_check = false;
			}
			long curr_time = System.nanoTime();

			if (curr_time - base_update_time >= updates) {
				update();
				update_check = true;
			}
			if (curr_time - base_render_time >= frames) {
				render();
				render_check = true;
			}

		}
	}

	
	public static void main(String[] args) {
		Game game = new Game(); // Making object of this class
		game.frame.setResizable(false); // Setting properties of the window
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start(); // Starting the game
	}

}
