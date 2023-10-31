package tk0821.puzzlegame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.ScreenUtils;

import tk0821.puzzlegame.objects.Board;
import tk0821.puzzlegame.objects.InfoBoard;
import tk0821.puzzlegame.objects.Piece;
import tk0821.puzzlegame.objects.PieceL;

public class GameScreen implements Screen {

	final Puzzle game;

	public static ShapeRenderer shapeRenderer; // 図形描画用
	public static OrthographicCamera camera; // 平行投影カメラ
	MyInputProcessor inputProcessor; // 入力ハンドラ

	public static Board board;// ボード
	public static InfoBoard info;//スコア枠とネクスト枠
	static Piece currentPiece;// 持っているピース
	static Piece nextPiece;// NEXTピース

	public static int score;

	// gameover用
	final GlyphLayout gameOverLayout;//text
	public static boolean isGameOver = false;// gameoverフラグ

	public GameScreen(final Puzzle game) {
		this.game = game;

		//カメラ設定
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);

		//テキスト表示用
		game.font.getData().setScale(4);
		gameOverLayout = new GlyphLayout(game.font, "GAME OVER");

		//block描画用
		shapeRenderer = new ShapeRenderer();

		//入力ハンドラ
		inputProcessor = new MyInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);

		// ボード生成
		board = new Board();
		info = new InfoBoard();

		//ピース生成
		currentPiece = new PieceL(-1000, -1000);
		nextPiece = new PieceL(InfoBoard.NEXT_BLOCK_X, InfoBoard.NEXT_BLOCK_Y);
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		if (isGameOver) {
			GlyphLayout scoreLayout = new GlyphLayout(game.font, "SCORE: " + String.valueOf(score));

			//Game over
			game.batch.begin();
			game.font.draw(game.batch, gameOverLayout, (1280 - gameOverLayout.width) / 2,
					(720 - gameOverLayout.height) / 2 + 200);
			game.font.draw(game.batch, scoreLayout, (1280 - scoreLayout.width) / 2,
					(720 - scoreLayout.height) / 2 - 100);
			game.batch.end();
		} else {
			// inGame
			shapeRenderer.begin(ShapeType.Filled);
			info.draw(); // info
			board.draw();// 盤面
			nextPiece.draw(); // nextピース
			shapeRenderer.end();

			//スコア表示
			game.batch.begin();
			game.font.draw(game.batch, "SCORE: " + String.valueOf(score), InfoBoard.INFO_X + 30,
					InfoBoard.INFO_Y + 180);
			game.batch.end();

			// 持っているピース表示
			shapeRenderer.begin(ShapeType.Filled);
			currentPiece.draw();
			shapeRenderer.end();
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		shapeRenderer.dispose();
	}

}
