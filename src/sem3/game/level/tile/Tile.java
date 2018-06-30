package sem3.game.level.tile;

import sem3.game.graphics.Display;
import sem3.game.graphics.sprite.Sprite;

public abstract class Tile { // Tile class called by level method to render and by entities to check collision

	public Sprite sprite;

	public static Tile tile0 = new SolidTile(Sprite.tile0);
	public static final int COL_TILE0 = 0xffa5a5a6;
	
	public static Tile tile1 = new SolidTile(Sprite.tile1);
	public static final int COL_TILE1 = 0xffa5a5a7;
	
	public static Tile tile2 = new SolidTile(Sprite.tile2);
	public static final int COL_TILE2 = 0xffa5a5a8;
	
	public static Tile tile3 = new SolidTile(Sprite.tile3);
	public static final int COL_TILE3 = 0xffa5a5a9;
	
	public static Tile tile4 = new SolidTile(Sprite.tile4);
	public static final int COL_TILE4 = 0xff828282;
	
	public static Tile tile5 = new SolidTile(Sprite.tile5);
	public static final int COL_TILE5 = 0xff828283;
	
	public static Tile tile6 = new SolidTile(Sprite.tile6);
	public static final int COL_TILE6 = 0xff828284;
	
	public static Tile tile7 = new SolidTile(Sprite.tile7);
	public static final int COL_TILE7 = 0xff828285;
	
	public static Tile tile8 = new SolidTile(Sprite.tile8);
	public static final int COL_TILE8 = 0xff636363;
	
	public static Tile tile9 = new SolidTile(Sprite.tile9);
	public static final int COL_TILE9 = 0xff636364;
	
	public static Tile tile10 = new SolidTile(Sprite.tile10);
	public static final int COL_TILE10 = 0xff636365;
	
	public static Tile tile11 = new SolidTile(Sprite.tile11);
	public static final int COL_TILE11 = 0xff636366;
	
	public static Tile tile12 = new NonSolidTile(Sprite.tile12);
	public static final int COL_TILE12 = 0xffffffff;
	
	public static Tile tile13 = new FatalTile(Sprite.tile13);
	public static final int COL_TILE13 = 0xff000000;
	
	public static Tile tile14 = new NonSolidTile(Sprite.tile14);
	public static final int COL_TILE14 = 0xffa5a5a5;
	
	
	

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public abstract void render(Display display, int x, int y);

	public abstract boolean solid();

	public abstract boolean fatal();
}
