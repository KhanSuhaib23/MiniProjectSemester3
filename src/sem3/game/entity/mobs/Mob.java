package sem3.game.entity.mobs;

import sem3.game.entity.Entity;
import sem3.game.graphics.Display;
import sem3.game.graphics.sprite.Sprite;
import sem3.game.level.Level;
import sem3.game.util.Time;

public class Mob extends Entity { // Mobile entities are the mobs

	protected boolean moving = false;
	protected boolean jumping = false;
	protected boolean falling = false;
	protected boolean attacking = false;
	protected boolean jump_check = false;
	protected boolean wallSlide = false;
	protected boolean wallJump = false;
	protected boolean wallJump_check = false;
	protected boolean wallSide = false; // False -> Right, True -> Left
	protected int maxHP;
	public int HP;

	protected Time time;

	protected int sx, sy;
	protected int uy;
	protected int vy;

	public Mob(int x, int y, Sprite[] sprite, Level level) {
		super(x, y, sprite, level);
		time = new Time();
	}

	protected void move(int xMove, int yMove) {
		if (xMove != 0 && yMove != 0) {
			move(xMove, 0);
			move(0, yMove);
			return;
		}

		if (!collision(x + xMove, y + yMove)) {
			if (!spriteCollision()) {
				x += xMove;
				y += yMove;
			} else {
				if (this instanceof Player) {
					doDamage(50);
					if (collision == 1) {
						x -= 50;
					} else if (collision == 2) {
						y -= yMove * 5;
						x -= 50;
					}
				}
			}
		} else if (collision(x, y + yMove)) {
			if (yMove > 0) {
				sy = 0;
				jumping = false;
				jump_check = false;
				wallJump = false;
				wallJump_check = false;
				wallSlide = false;
			} else {
				vy = 0;
			}
		}
		if (!collision(x, y + yMove)) {
			falling = true;
			if (collision(x + 7, y)) {
				wallSide = false;
				wallSlide = true;
				wallJump = false;
				wallJump_check = false;
			} else if (collision(x - 7, y)) {
				wallSide = true;
				wallSlide = true;
				wallJump = false;
				wallJump_check = false;
			} else {
				wallSlide = false;
			}
		}

	}

	public void update() {

	}

	public void render(Display display) {
		display.renderSprite(x, y, currSprite);
		
	}

	protected void jump() {
		if (!jumping) {
			vy = 17;
			jumping = true;
		}
		sy = -vy;
		vy--;
	}

	protected void wallJump() {
		if (!wallJump) {
			vy = 20;
			wallJump = true;
		}
		if (!wallSide) {
			sx = -7;
		} else {
			sx = 7;
		}
		sy = -vy;
		vy--;
	}

	protected void gravity() {
		if (!jumping && !wallSlide) {
			sy += 1;
			if (sy >= 10) {
				sy = 10;
			}
		}
	}

	public void doDamage(int damage) {
		HP -= damage;
	}

	public double healthRatio() {
		double ratio = (double) HP / maxHP;
		return ratio;
	}

	public boolean spriteCollision() {
		boolean xCollision = false;
		boolean yCollision = false;
		for (int i = 0; i < level.mobs.size(); i++) {
			if (Math.abs(level.mobs.get(i).x0 - x1) < Math.abs(level.mobs.get(i).x1 - x0)) {
				if (level.mobs.get(i).x0 - x1 <= 0) {
					collision = 1;
					xCollision = true;
				}
			} else if (Math.abs(x0 - level.mobs.get(i).x1) < Math.abs(x1 - level.mobs.get(i).x0)) {
				if (x0 - level.mobs.get(i).x1 <= 0) {
					xCollision = true;
					collision = 1;
				}
			}
			if (Math.abs(level.mobs.get(i).y0 - y1) < Math.abs(level.mobs.get(i).y1 - y0)) {
				if (level.mobs.get(i).y0 - y1 <= 0) {
					yCollision = true;
					collision = 2;
				}
			} else if (Math.abs(y0 - level.mobs.get(i).y1) < Math.abs(y1 - level.mobs.get(i).y0)) {
				if (y0 - level.mobs.get(i).y1 <= 0) {
					yCollision = true;
					collision = 2;
				}
			}

		}
		return (xCollision && yCollision);
	}

}
