package sem3.game.entity.mobs;

import sem3.game.Game;
import sem3.game.entity.projectiles.Projectiles;
import sem3.game.graphics.sprite.Sprite;
import sem3.game.input.Keyboard;
import sem3.game.level.Level;

public class Player extends Mob { // Player takes the keys and moves the sprite accrodingly

	Keyboard keyInput;
	private boolean chargeAttack = false;
	private boolean shot = false;

	public Player(int x, int y, Sprite[] sprite, Level level, Keyboard keyInput) {
		super(x, y, sprite, level);
		this.keyInput = keyInput;
		HP = 1000;
		maxHP = 1000;
	}

	public void update() {
		animCounter++;
		if (animCounter == 100000) animCounter = 0;
		sx = 0;
		if (!falling) {
			sy = 0;
		}
		calculateCollisionBox(0, 0);
		getInput();
		executeInput();
		selectSprite();
		gravity();
		move(sx, sy);
		moving = false;
		
	}

	private void getInput() {
		keyInput.update();
		if (keyInput.left) {
			sx = -5;
			direction = -1;
			moving = true;
		}
		if (keyInput.right) {
			sx = 5;
			direction = 1;
			moving = true;
		}
		if (keyInput.jump) {
			if (!wallSlide) {
				jump_check = true;
			} else {
				wallJump_check = true;
			}
		}
		
		if (keyInput.attack) {
			doDamage(0);
		}
	}

	private void executeInput() {
		if (jump_check) jump();

		if (wallSlide) {
			sy = 3;
		}
		if (wallJump_check) {
			wallJump();
		}

		if (!isCharged() && !keyInput.attack) {
			time.stopTimer();
			chargeAttack = false;
		}

		if (wallSlide) {
			if (wallSide) {
				direction = 1;
			} else {
				direction = -1;
			}
		}

		if (isCharged() && !keyInput.attack) {
			shot = true;
			Projectiles p = new Projectiles(x + 50 * direction, y, Sprite.projectile, level, direction);
			level.addProjectile(p);
			time.stopTimer();
			chargeAttack = false;
		}
	}

	private void selectSprite() {
		if (moving) {
			if (direction == 1) {
				if (animCounter % animationSpeed < (animationSpeed / 3)) {
					if (chargeAttack) {
						currSprite = Sprite.playerSprites[5];

					} else if (shot) {
						shot = false;
						currSprite = Sprite.playerSprites[9];
					} else {
						currSprite = Sprite.playerSprites[1];
					}

				} else if (animCounter % animationSpeed < 2 * (animationSpeed / 3)) {
					if (chargeAttack) {
						currSprite = Sprite.playerSprites[6];
					} else if (shot) {
						shot = false;
						currSprite = Sprite.playerSprites[10];
					} else {
						currSprite = Sprite.playerSprites[2];
					}

				} else {
					if (chargeAttack) {
						currSprite = Sprite.playerSprites[7];
					} else if (shot) {
						shot = false;
						currSprite = Sprite.playerSprites[11];
					} else {
						currSprite = Sprite.playerSprites[3];
					}

				}
			} else if (direction == -1) {

				if (animCounter % animationSpeed < (animationSpeed / 3)) {
					if (chargeAttack) {
						currSprite = Sprite.playerSprites[22];
					} else if (shot) {
						shot = false;
						currSprite = Sprite.playerSprites[26];
					} else {
						currSprite = Sprite.playerSprites[18];
					}

				} else if (animCounter % animationSpeed < 2 * (animationSpeed / 3)) {
					if (chargeAttack) {
						currSprite = Sprite.playerSprites[23];
					} else if (shot) {
						shot = false;
						currSprite = Sprite.playerSprites[27];
					} else {
						currSprite = Sprite.playerSprites[19];
					}

				} else {
					if (chargeAttack) {
						currSprite = Sprite.playerSprites[24];
					} else if (shot) {
						shot = false;
						currSprite = Sprite.playerSprites[28];
					} else {
						currSprite = Sprite.playerSprites[20];
					}

				}

			}

		} else {
			if (direction == 1) {

				if (chargeAttack) {
					currSprite = Sprite.playerSprites[4];
				} else if (shot) {
					shot = false;
					currSprite = Sprite.playerSprites[8];
				} else {
					currSprite = Sprite.playerSprites[0];
				}

			} else if (direction == -1) {

				if (chargeAttack) {
					currSprite = Sprite.playerSprites[21];
				} else if (shot) {
					shot = false;
					currSprite = Sprite.playerSprites[25];
				} else {
					currSprite = Sprite.playerSprites[17];
				}

			}
		}
		if (jumping) {
			if (direction == 1) {
				if (chargeAttack) {
					currSprite = Sprite.playerSprites[12];
				} else if (shot) {
					shot = false;
					currSprite = Sprite.playerSprites[13];
				} else {
					currSprite = Sprite.playerSprites[14];
				}
			} else if (direction == -1) {
				if (chargeAttack) {
					currSprite = Sprite.playerSprites[29];
				} else if (shot) {
					shot = false;
					currSprite = Sprite.playerSprites[30];
				} else {
					currSprite = Sprite.playerSprites[31];
				}
			}
		}
		if (wallSlide) {
			if (direction == 1) {
				if (chargeAttack) {
					currSprite = Sprite.playerSprites[35];
				} else if (shot) {
					currSprite = Sprite.playerSprites[33];
				} else {
					currSprite = Sprite.playerSprites[32];
				}
			} else if (direction == -1) {
				if (chargeAttack) {
					currSprite = Sprite.playerSprites[34];
				} else if (shot) {
					currSprite = Sprite.playerSprites[16];
				} else {
					currSprite = Sprite.playerSprites[15];
				}
			}
		}
	}

	public boolean isCharged() {
		if (keyInput.attack && !chargeAttack) {
			chargeAttack = true;
			time.startTimer();
		}

		if (time.getCurrentTime() > 0.5) {
			return true;
		}
		return false;
	}

}
