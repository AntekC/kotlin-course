package pl.edu.mimuw.smd

class SMDBuilder {

    private val content: StringBuilder = StringBuilder()

    fun build(): SMDDocument = SMDDocument(content.toString())

    fun h1(action: TextScope.() -> Unit) {
        val header = TextScope().apply(action).content
        content.append(header)
        content.append("\n${"=".repeat(header.length)}\n\n")
    }

    fun h2(action: TextScope.() -> Unit) {
        val header = TextScope().apply(action).content
        content.append(header)
        content.append("\n${"-".repeat(header.length)}\n\n")
    }

    fun p(action: TextScope.() -> Unit) {
        content.append(TextScope().apply(action).content)
        content.append("\n\n")
    }

    fun normalList(action: NormalListScope.() -> Unit) {
        content.append(NormalListScope().apply(action).content)
        content.append("\n")
    }

    fun numberList(action: NumberListScope.() -> Unit) {
        content.append(NumberListScope().apply(action).content)
        content.append("\n")
    }


    fun line() {
        content.append("---\n\n")
    }

    fun code(action: CodeScope.() -> Unit) {
        content.append(CodeScope().apply(action).code)
        content.append("```\n")
    }

    fun italics(line: String): String =
        "*$line*"

    fun bold(line: String): String =
        "**$line**"

    fun boldItalics(line: String): String =
        "***$line***"

    fun inlineCode(line: String): String =
        "`$line`"

    fun br(): String =
        "  \n"

    fun textBlock(action: TextScope.() -> Unit): String =
        TextScope().apply(action).content.toString()
}

fun generateSMD(action: SMDBuilder.() -> Unit): SMDDocument =
    SMDBuilder().apply(action).build()

