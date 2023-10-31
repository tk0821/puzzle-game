package tk0821.puzzlegame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

	final Puzzle game;
	final GlyphLayout titleLayout;
	final GlyphLayout startLayout;

	OrthographicCamera camera;

	public MainMenuScreen(final Puzzle game) {
		this.game = game;

		game.font.getData().setScale(4);
		titleLayout = new GlyphLayout(game.font, "PUZZLE GAME");
		startLayout = new GlyphLayout(game.font, "click to start !");

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.font.draw(game.batch, titleLayout, (1280 - titleLayout.width) / 2, (720 - titleLayout.height) / 2 + 200);
		game.font.draw(game.batch, startLayout, (1280 - startLayout.width) / 2, (720 - startLayout.height) / 2 - 50);
		game.batch.end();

		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}

	@Override
	public void show() {
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

	}

}
