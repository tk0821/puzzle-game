package tk0821.puzzlegame.objects;

import tk0821.puzzlegame.game.GameScreen;

public abstract class Piece {

	protected Block[] blocks;
	protected int direction;

	private int[][] searched;

	public abstract void rotate();

	public abstract void setXY(int x, int y);

	abstract void checkGameOver();

	abstract void direction1();

	abstract void direction2();

	abstract void direction3();

	abstract void direction4();

	public Piece() {

	}

	public void draw() {
		for (int i = 0; i < blocks.length; i++) {
			blocks[i].draw(1);
		}
	}

	public boolean checkPut(int x, int y) {
		if (x < 0 || x >= Board.BOARD_SIZE || y < 0 || y >= Board.BOARD_SIZE) {
			return false;
		}

		if (GameScreen.board.getBlock(x, y).hasColor()) {
			return false;
		}

		for (int i = 0; i < blocks.length; i++) {
			if (blocks[i].getX() < Board.BOARD_X / 2 || blocks[i].getY() < Board.BOARD_Y / 2) {
				return false;
			}

			int curX = (int) Math.floor((blocks[i].getX() - Board.BOARD_X + Board.BOARD_X / 2) / Block.SIZE);
			int curY = (int) Math.floor((blocks[i].getY() - Board.BOARD_Y + Board.BOARD_Y / 2) / Block.SIZE);

			if (curX >= Board.BOARD_SIZE || curX < 0 || curY >= Board.BOARD_SIZE || curY < 0) {
				return false;
			}

			if (GameScreen.board.getBlock(curX, curY).hasColor()) {
				return false;
			}
		}
		return true;
	}

	public void put(int x, int y) {
		for (int i = 0; i < blocks.length; i++) {
			int curX = (int) Math.floor((blocks[i].getX() - Board.BOARD_X + Board.BOARD_X / 2) / Block.SIZE);
			int curY = (int) Math.floor((blocks[i].getY() - Board.BOARD_Y + Board.BOARD_Y / 2) / Block.SIZE);
			GameScreen.board.setBlockColor(curX, curY, blocks[i].getColor());
		}
		checkClear();
		checkGameOver();
	}

	private void checkClear() {
		int score = 0;
		int ratio = 1;
		for (int i = 0; i < blocks.length; i++) {
			searched = new int[Board.BOARD_SIZE][Board.BOARD_SIZE];

			int curX = (int) Math.floor((blocks[i].getX() - Board.BOARD_X + Board.BOARD_X / 2) / Block.SIZE);
			int curY = (int) Math.floor((blocks[i].getY() - Board.BOARD_Y + Board.BOARD_Y / 2) / Block.SIZE);

			int count = countLinked(curX, curY, blocks[i].getColor());
			if (count >= 5) {
				if (ratio == 1) {
					score = clear();
					ratio++;
				} else {
					score = score * ratio + clear() * ratio;
				}
			}
		}
		GameScreen.score += score;
	}

	private int clear() {
		int score = 0;
		for (int y = Board.BOARD_SIZE - 1; y >= 0; y--) {
			for (int x = 0; x < Board.BOARD_SIZE; x++) {
				if (searched[y][x] == 2) {
					GameScreen.board.setBlockColor(x, y, Block.NONE);
					searched[y][x] = 1;
					score++;
				}
			}
		}
		return score * 10 * (score - 5 + 1);
	}

	private int countLinked(int x, int y, int color) {
		int count = 0;
		if (x < 0 || x >= Board.BOARD_SIZE || y < 0 || y >= Board.BOARD_SIZE) {
			return count;
		}
		if (searched[y][x] != 0) {
			return count;
		}
		searched[y][x] = 1;
		if (GameScreen.board.getBlock(x, y).getColor() == color) {
			searched[y][x] = 2;
			count = 1;
			count += countLinked(x + 1, y, color);
			count += countLinked(x - 1, y, color);
			count += countLinked(x, y + 1, color);
			count += countLinked(x, y - 1, color);
		}
		return count;
	}
}
