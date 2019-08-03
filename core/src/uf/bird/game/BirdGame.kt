package uf.bird.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class BirdGame : ApplicationAdapter() {

	private lateinit var batch: SpriteBatch
	private lateinit var background: Texture
	private lateinit var bird: BirdModel
	private lateinit var birdController: BirdController
	private lateinit var obstacles: ObstaclesModel
	private lateinit var obstaclesController: ObstaclesController

	override fun create() {
		batch = SpriteBatch()
		background = Texture("background.png")
		bird = BirdModel(screenCenterX / 3, screenCenterY)
		birdController = BirdController(bird)
		obstacles = ObstaclesModel()
		obstaclesController = ObstaclesController(obstacles)

		Gdx.gl.glClearColor(.5f, .5f, .5f, 1f)
	}

	override fun render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

		birdController.updateBirdPosition(deltaTime)
		obstaclesController.update(deltaTime)
		batch.use {
			it.draw(background, 0f, 0f, screenWidth, screenHeight)
			it.draw(bird.currentFrame, bird.positionX, bird.positionY, BIRD_WIDTH, BIRD_HEIGHT)
			obstacles.tubesPairs.forEach {
				tubes ->
					it.draw(obstacles.bottomTubeTexture, tubes.tubesX, tubes.bottomTubeY)
					it.draw(obstacles.topTubeTexture, tubes.tubesX, tubes.topTubeY)
			}
			it.draw(obstacles.groundTexture, obstacles.groundStartX, GROUND_START_Y)
			it.draw(obstacles.groundTexture, obstacles.groundStartX2, GROUND_START_Y)
		}
	}

	override fun dispose() {
		batch.dispose()
	}

	companion object {
		val screenWidth
			get() = Gdx.graphics.width.toFloat()

		val screenHeight
			get() = Gdx.graphics.height.toFloat()

		val deltaTime
			get() = Gdx.graphics.deltaTime

		val screenCenterX
			get() = (screenWidth / 2)

		val screenCenterY
			get() = (screenHeight / 2)
	}
}
