package pl.edu.mimuw.dice

import kotlin.random.Random

class Die(val faces: Int) {
    fun roll(): Int = Random.nextInt(1, faces)
}
