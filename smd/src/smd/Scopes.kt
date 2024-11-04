package pl.edu.mimuw.smd

open class GlobalScope {
    val content = StringBuilder()
}


class TextScope : GlobalScope() {
    operator fun String.unaryPlus() {
        content.append(this)
    }
}

class NormalListScope : GlobalScope() {
    operator fun String.unaryMinus() {
        content.append("- $this\n")
    }
}

class NumberListScope : GlobalScope() {
    private var counter = 1

    operator fun String.unaryMinus() {
        content.append("${counter++}. $this\n")
    }
}

class CodeScope {
    var code = StringBuilder("```\n")

    operator fun String.unaryPlus() {
        code.append("$this\n")
    }

}
