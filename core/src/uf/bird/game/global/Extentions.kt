package uf.bird.game.global

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch

inline fun Batch.use(block: (Batch) -> Unit) {
	this.begin()
	block(this)
	this.end()
}

val isTouched
	get() = Gdx.input.justTouched()