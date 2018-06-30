package sem3.game.entity.mobs;

import java.util.Random;

import sem3.game.entity.projectiles.Projectiles;
import sem3.game.graphics.Display;
import sem3.game.graphics.sprite.Sprite;
import sem3.game.level.Level;

public class Enemy extends Mob {

	Player player;
	int fireRate = 50;
	int firing = 50;
	private Random random;
	private int movements = 100;
	private int count = 0;
	private int rangeX = 15 * 48;

	public Enemy(int x, int y, Sprite[] sprite, Level level, Player player) {
		super(x, y, sprite, level);
		maxHP = 100;
		HP = 100;
		this.player = player;
		animationSpeed = 28;
		random = new Random();
	}

	public void update() {
		animCounter++;
		if (animCounter == 10000) animCounter = 0;
		calculateCollisionBox(0, 0);
		enemyAI();
		selectSprite();
		direction = -1;
		if (sx != 0 || sy != 0) moving = true;
		else moving = false;
		move(sx, sy);
	}

	private void selectSprite() {
		if (direction == 1) {
			
			if (moving) {
				if (animCounter % animationSpeed < animationSpeed / 4) {
					currSprite = sprite[0];

				} else if (animCounter % animationSpeed < 2 * (animationSpeed / 4)) {
					currSprite = sprite[1];

				} else if (animCounter % animationSpeed < 3 * (animationSpeed / 4)) {
					currSprite = sprite[2];

				} else {
					currSprite = sprite[3];
					
				}
			} else {
				currSprite = sprite[4];
			}

		} else if (direction == -1) {
			if (moving) {
				if (animCounter % animationSpeed < animationSpeed / 4) {
					currSprite = sprite[5];

				} else if (animCounter % animationSpeed < 2 * (animationSpeed / 4)) {
					currSprite = sprite[6];

				} else if (animCounter % animationSpeed < 3 * (animationSpeed / 4)) {
					currSprite = sprite[7];

				} else {
					currSprite = sprite[8];

				}
			} else {
				currSprite = sprite[9];
				
			}
		}

	}

	public void render(Display display) {
		display.renderSprite(x, y, currSprite);
		display.renderUI(x - 10, y - 15, 65, 5, (int) (65 * healthRatio()), false);
	}

	public void enemyAI() {
		sx = 0;
		sy = 0;
		if (player.jump_check && random.nextInt(20) == 0) jump_check = true;
		if (jump_check) jump();

		if (Math.abs(player.getY() - y) >= 0 && Math.abs(player.getY() - y) <= 150) {
			if (Math.abs(player.getX() - x) > rangeX) return;
			
			if (player.getX() > x) {
				direction = 1;
				if (Math.abs(player.getX() - x) <= 400) {
					if (firing != 0) {
						firing--;
					} else {
						Projectiles p = new Projectiles(x + 50 * direction, y, Sprite.projectile, level, direction);
						level.addProjectile(p);
						firing = fireRate;
					}
				} else {
					sx = 3;
				}
			} else if (player.getX() < x) {
				direction = -1;
				if (Math.abs(player.getX() - x) <= 400) {
					if (firing != 0) {
						firing--;
					} else {
						Projectiles p = new Projectiles(x + 50 * direction, y, Sprite.projectile, level, direction);
						level.addProjectile(p);
						firing = fireRate;
					}
				} else {
					sx = -3;
				}
			}
		}
	}

}
