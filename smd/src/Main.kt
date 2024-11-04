package pl.edu.mimuw

import pl.edu.mimuw.smd.generateSMD
import java.io.File

fun main(args: Array<String>) {

    // You have to pass name of the file you want to output test markdown content
    require(args.isNotEmpty())

    val simpleMarkDownContent = generateSMD {

        h1 { +"Lorem ipsum" }

        h2 { +"dolor sit amet" }

        p {
            +"Lorem ipsum dolor sit amet, consectetur adipiscing elit,"
            +br()
            +"sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        }

        p {
            +"Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
        }

        normalList {
            -textBlock { +"Duis aute irure" }
            -textBlock { +"dolor in reprehenderit" }
        }

        numberList {
            -textBlock { +"in voluptate velit esse" }
            -textBlock { +"cillum dolore eu fugiat nulla pariatur." }
        }

        line()

        p {
            +"Excepteur "
            +italics("sint")
            +" occaecat "
            +bold("cupidatat")
            +" non "
            +boldItalics("proident")
            +", "
            +inlineCode("sunt in culpa")
        }

        code {
            +"qui officia deserunt"
            +"mollit anim id est laborum."
        }
    }

    File(args[0]).writeText(simpleMarkDownContent.content)

}
