package uf.bird.game

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2

inline fun Batch.use(block: (Batch) -> Unit) {
	this.begin()
	block(this)
	this.end()
}

infix fun Float.v2(y: Float) = Vector2(this, y)