package uf.bird.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20

class PlayScreen(private val game: BirdGame): ScreenAdapter() {

	private lateinit var bird: BirdModel
	private lateinit var birdController: BirdController
	private lateinit var obstacles: ObstaclesModel
	private lateinit var obstaclesController: ObstaclesController

	override fun show() {
		bird = BirdModel(BirdGame.screenCenterX / 3, BirdGame.screenCenterY)
		birdController = BirdController(bird)
		obstacles = ObstaclesModel()
		obstaclesController = ObstaclesController(obstacles, bird)

		Gdx.gl.glClearColor(.5f, .5f, .5f, 1f)
	}

	override fun render(deltaTime: Float) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

		birdController.updateBirdPosition(BirdGame.deltaTime)
		obstaclesController.update(BirdGame.deltaTime)
		game.batch.use {
			it.draw(game.background, 0f, 0f, BirdGame.screenWidth, BirdGame.screenHeight)
			it.draw(bird.currentFrame, bird.positionX, bird.positionY, BIRD_WIDTH, BIRD_HEIGHT)
			obstacles.tubesPairs.forEach {
				tubes ->
				it.draw(obstacles.bottomTubeTexture, tubes.tubesX, tubes.bottomTubeY)
				it.draw(obstacles.topTubeTexture, tubes.tubesX, tubes.topTubeY)
			}
			it.draw(obstacles.groundTexture, obstacles.groundStartX, GROUND_START_Y)
			it.draw(obstacles.groundTexture, obstacles.groundStartX2, GROUND_START_Y)
			game.font.draw(it, obstacles.tubesPassed.toString(), 50f, 50f)
		}
		if (bird.isDead) {
			game.setScreen(WastedScreen(game, bird, obstacles));
		}
	}

}