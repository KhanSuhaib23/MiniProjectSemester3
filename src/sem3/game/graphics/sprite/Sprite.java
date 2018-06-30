package sem3.game.graphics.sprite; // transparency 0xff303040

public class Sprite { // Used in extracting various sprites from the spritesheet

	private SpriteSheet sheet;
	private int x, y; // Position of the sprite in spritesheet in sprite precision
	public int width, height;
	public int[] pixels;
	
	public static Sprite[] projectile = {new Sprite(SpriteSheet.projectileSprite, 4, 0, 48, 48), 
									   	 new Sprite(SpriteSheet.projectileSprite, 3, 0, 48, 48), 
										 new Sprite(SpriteSheet.projectileSprite, 2, 0, 48, 48), 
										 new Sprite(SpriteSheet.projectileSprite, 1, 0, 48, 48), 
										 new Sprite(SpriteSheet.projectileSprite, 0, 0, 48, 48), 
										 new Sprite(SpriteSheet.projectileSprite, 5, 0, 48, 48), 
										 new Sprite(SpriteSheet.projectileSprite, 6, 0, 48, 48), 
										 new Sprite(SpriteSheet.projectileSprite, 7, 0, 48, 48), 
										 new Sprite(SpriteSheet.projectileSprite, 8, 0, 48, 48), 
										 new Sprite(SpriteSheet.projectileSprite, 9, 0, 48, 48)};
	
	public static Sprite[] playerSprites = {new Sprite(SpriteSheet.playerSprites, 0, 0, 48, 96), 
										    new Sprite(SpriteSheet.playerSprites, 1, 0, 48, 96), 
										    new Sprite(SpriteSheet.playerSprites, 2, 0, 48, 96), 
										    new Sprite(SpriteSheet.playerSprites, 3, 0, 48, 96), 
										    new Sprite(SpriteSheet.playerSprites, 0, 2, 48, 96), 
										    new Sprite(SpriteSheet.playerSprites, 1, 2, 48, 96), 
										    new Sprite(SpriteSheet.playerSprites, 2, 2, 48, 96), 
										    new Sprite(SpriteSheet.playerSprites, 3, 2, 48, 96), 
										    new Sprite(SpriteSheet.playerSprites, 0, 4, 48, 96), 
										    new Sprite(SpriteSheet.playerSprites, 1, 4, 48, 96), 
										    new Sprite(SpriteSheet.playerSprites, 2, 4, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 3, 4, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 0, 6, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 1, 6, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 2, 6, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 0, 8, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 1, 8, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 7, 0, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 6, 0, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 5, 0, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 4, 0, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 7, 2, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 6, 2, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 5, 2, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 4, 2, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 7, 4, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 6, 4, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 5, 4, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 4, 4, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 7, 6, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 6, 6, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 5, 6, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 7, 8, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 6, 8, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 2, 8, 48, 96),
										    new Sprite(SpriteSheet.playerSprites, 5, 8, 48, 96)};
	
	/*
	 * 0 -> right idle 
	 * 1 -> right run 1
	 * 2 -> right run 2
	 * 3 -> right run 3
	 * 4 -> right idle charge
	 * 5 -> right run charge 1
	 * 6 -> right run charge 2
	 * 7 -> right run charge 3
	 * 8 -> right idle swing 
	 * 9 -> right run swing 1
	 * 10 -> right run swing 2
	 * 11 -> right run swing 3
	 * 12 -> right jump charge
	 * 13 -> right jump swing
	 * 14 -> right jump
	 * 15 -> right wall climb
	 * 16 -> right wall swing 
	 * 17 -> left idle
	 * 18 -> left run 1
	 * 19 -> left run 2
	 * 20 -> left run 3
	 * 21 -> left idle charge
	 * 22 -> left run charge 1
	 * 23 -> left run charge 2
	 * 24 -> left run charge 3
	 * 25 -> left idle swing 
	 * 26 -> left run swing 1
	 * 27 -> left run swing 2
	 * 28 -> left run swing 3
	 * 29 -> left jump charge
	 * 30 -> left jump swing
	 * 31 -> left jump
	 * 32 -> left wall climb
	 * 33 -> left wall swing
	 * 34 -> right wall charge
	 * 35 -> left wall charge
	 */
	
	public static Sprite[] enemySprites = {new Sprite(SpriteSheet.enemySprite, 0, 0, 48, 96),
										   new Sprite(SpriteSheet.enemySprite, 1, 0, 48, 96),
										   new Sprite(SpriteSheet.enemySprite, 2, 0, 48, 96),
										   new Sprite(SpriteSheet.enemySprite, 3, 0, 48, 96),
										   new Sprite(SpriteSheet.enemySprite, 4, 0, 48, 96),
										   new Sprite(SpriteSheet.enemySprite, 4, 2, 48, 96),
										   new Sprite(SpriteSheet.enemySprite, 3, 2, 48, 96),
										   new Sprite(SpriteSheet.enemySprite, 2, 2, 48, 96),
										   new Sprite(SpriteSheet.enemySprite, 1, 2, 48, 96),
										   new Sprite(SpriteSheet.enemySprite, 0, 2, 48, 96)};
	
	/*
	 * 0 -> right run 1
	 * 1 -> right run 2
	 * 2 -> right run 3
	 * 3 -> right run 4
	 * 4 -> right else
	 * 5 -> left run 1
	 * 6 -> left run 2
	 * 7 -> left run 3
	 * 8 -> left run 4
	 * 9 -> left else
	 */
	
	public static Sprite tile0 = new Sprite(SpriteSheet.levelTiles, 0, 0, 48, 48);
	public static Sprite tile1 = new Sprite(SpriteSheet.levelTiles, 1, 0, 48, 48);
	public static Sprite tile2 = new Sprite(SpriteSheet.levelTiles, 2, 0, 48, 48);
	public static Sprite tile3 = new Sprite(SpriteSheet.levelTiles, 3, 0, 48, 48);
	public static Sprite tile4 = new Sprite(SpriteSheet.levelTiles, 0, 1, 48, 48);
	public static Sprite tile5 = new Sprite(SpriteSheet.levelTiles, 1, 1, 48, 48);
	public static Sprite tile6 = new Sprite(SpriteSheet.levelTiles, 2, 1, 48, 48);
	public static Sprite tile7 = new Sprite(SpriteSheet.levelTiles, 3, 1, 48, 48);
	public static Sprite tile8 = new Sprite(SpriteSheet.levelTiles, 0, 2, 48, 48);
	public static Sprite tile9 = new Sprite(SpriteSheet.levelTiles, 1, 2, 48, 48);
	public static Sprite tile10 = new Sprite(SpriteSheet.levelTiles, 2, 2, 48, 48);
	public static Sprite tile11 = new Sprite(SpriteSheet.levelTiles, 3, 2, 48, 48);
	public static Sprite tile12 = new Sprite(SpriteSheet.levelTiles, 0, 3, 48, 48);
	public static Sprite tile13 = new Sprite(SpriteSheet.levelTiles, 1, 3, 48, 48);
	public static Sprite tile14 = new Sprite(SpriteSheet.levelTiles, 2, 3, 48, 48);
	
	public Sprite(SpriteSheet sheet, int x, int y, int width, int height) {
		this.sheet = sheet;
		this.x = x * sheet.sizeOfCell;
		this.y = y * sheet.sizeOfCell;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		loadSprite();
	}

	private void loadSprite() {
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				this.pixels[x + y * this.width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.width];
			}
		}
	}

}
