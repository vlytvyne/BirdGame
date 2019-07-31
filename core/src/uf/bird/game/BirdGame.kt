package uf.bird.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class BirdGame : ApplicationAdapter() {
	private lateinit var shapeRenderer: ShapeRenderer

	private val screenWidth
		get() = Gdx.graphics.width

	private val screenHeight
		get() = Gdx.graphics.height

	private val deltaTime
		get() = Gdx.graphics.deltaTime

	private val centerX
		get() = (screenWidth / 2).toFloat()

	private val centerY
		get() = (screenHeight / 2).toFloat()

	private val isTouched
		get() = Gdx.input.isTouched

	private var circleX = 0f
	private var circleY = 0f

	override fun create() {
		shapeRenderer = ShapeRenderer()
		circleX = centerX
		circleY = centerY
	}

	override fun render() {
		Gdx.gl.glClearColor(.5f, .5f, .5f, 1f)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

		if (isTouched) {
			circleY += MOVE_SPEED * deltaTime
		} else {
			circleY -= MOVE_SPEED * deltaTime
		}

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
		shapeRenderer.color = Color.GOLD
		shapeRenderer.circle(circleX, circleY, CIRCLE_RADIUS)
		shapeRenderer.end()
	}

	override fun dispose() {
		shapeRenderer.dispose()
	}

	companion object {
		const val CIRCLE_RADIUS = 50f
		const val MOVE_SPEED = 100
	}
}
