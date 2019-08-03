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

	private val screenWidth
		get() = Gdx.graphics.width.toFloat()

	private val screenHeight
		get() = Gdx.graphics.height.toFloat()

	private val deltaTime
		get() = Gdx.graphics.deltaTime

	private val screenCenterX
		get() = (screenWidth / 2).toFloat()

	private val screenCenterY
		get() = (screenHeight / 2).toFloat()

	override fun create() {
		batch = SpriteBatch()
		background = Texture("background.png")
		bird = BirdModel(screenCenterX / 3, screenCenterY, 60f, 40f)
		birdController = BirdController(bird)

		Gdx.gl.glClearColor(.5f, .5f, .5f, 1f)
	}

	override fun render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

		birdController.updateBirdPosition(deltaTime)
		batch.use {
			it.draw(background, 0f, 0f, screenWidth, screenHeight)
			it.draw(bird.currentFrame, bird.positionX, bird.positionY, bird.width, bird.height)
		}
	}

	override fun dispose() {
		batch.dispose()
	}
}
