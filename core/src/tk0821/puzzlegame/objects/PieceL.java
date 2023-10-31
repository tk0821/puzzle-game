package tk0821.puzzlegame.objects;

import com.badlogic.gdx.math.MathUtils;

import tk0821.puzzlegame.game.GameScreen;

public class PieceL extends Piece {

	public PieceL(int x, int y) {
		blocks = new Block[3];
		direction = 1;

		int color1 = MathUtils.random(Block.YELLOW);
		int color2 = MathUtils.random(Block.YELLOW);

		blocks[0] = new Block(x, y, color1);
		blocks[1] = new Block(x + Block.SIZE, y, color1);
		blocks[2] = new Block(x, y + Block.SIZE, color2);
	}

	public void rotate() {
		switch (direction) {
		case 1:
			direction2();
			break;
		case 2:
			direction3();
			break;
		case 3:
			direction4();
			break;
		case 4:
			direction1();
			break;
		}
	}

	public void setXY(int x, int y) {

		blocks[0].setXY(x, y);
		switch (direction) {
		case 1:
			direction1();
			break;
		case 2:
			direction2();
			break;
		case 3:
			direction3();
			break;
		case 4:
			direction4();
			break;

		}
	}

	void direction1() {
		//   2
		//   0 1
		// 
		blocks[1].setXY(blocks[0].getX() + Block.SIZE, blocks[0].getY());
		blocks[2].setXY(blocks[0].getX(), blocks[0].getY() + Block.SIZE);
		direction = 1;
	}

	void direction2() {
		//
		//   0 2
		//   1			
		blocks[1].setXY(blocks[0].getX(), blocks[0].getY() - Block.SIZE);
		blocks[2].setXY(blocks[0].getX() + Block.SIZE, blocks[0].getY());
		direction = 2;
	}

	void direction3() {
		//
		// 1 0
		//   2	
		blocks[1].setXY(blocks[0].getX() - Block.SIZE, blocks[0].getY());
		blocks[2].setXY(blocks[0].getX(), blocks[0].getY() - Block.SIZE);
		direction = 3;
	}

	void direction4() {
		//   1
		// 2 0
		//
		blocks[1].setXY(blocks[0].getX(), blocks[0].getY() + Block.SIZE);
		blocks[2].setXY(blocks[0].getX() - Block.SIZE, blocks[0].getY());
		direction = 4;
	}

	void checkGameOver() {
		for (int y = 0; y < Board.BOARD_SIZE; y++) {
			for (int x = 0; x < Board.BOARD_SIZE; x++) {
				if (GameScreen.board.getBlock(x, y).getColor() == Block.NONE) {
					if (x + 1 < Board.BOARD_SIZE && GameScreen.board.getBlock(x + 1, y).getColor() == Block.NONE) {
						if (y + 1 < Board.BOARD_SIZE
								&& GameScreen.board.getBlock(x, y + 1).getColor() == Block.NONE) {
							return;
						}
						if (y + 1 < Board.BOARD_SIZE
								&& GameScreen.board.getBlock(x + 1, y + 1).getColor() == Block.NONE) {
							return;
						}
						if (y - 1 >= 0 && GameScreen.board.getBlock(x, y - 1).getColor() == Block.NONE) {
							return;
						}
						if (y - 1 >= 0 && GameScreen.board.getBlock(x + 1, y - 1).getColor() == Block.NONE) {
							return;
						}
					}
				}
			}
		}
		GameScreen.isGameOver = true;
	}
}
