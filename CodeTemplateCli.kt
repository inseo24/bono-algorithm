import java.io.File
import java.time.LocalDate

fun main() {
    println("어떤 플랫폼인가요?")
    println("1. LeetCode")
    println("2. 백준 (BOJ)")
    print("선택: ")

    val platform = when (readLine()?.toIntOrNull()) {
        1 -> "LeetCode"
        2 -> "BOJ"
        else -> {
            println("잘못된 입력입니다.")
            return
        }
    }

    println("코드를 입력하세요 (끝내려면 빈 줄 또는 Ctrl+Z 입력):")
    val codeLines = mutableListOf<String>()
    while (true) {
        val line = readlnOrNull() ?: break
        if (line.isBlank() || line == "\u001a") {
            break
        }
        codeLines.add(line)
    }
    if (codeLines.isEmpty()) {
        println("작업 취소")
        return
    }
    val code = codeLines.joinToString("\n")

    print("문제 번호 (optional): ")
    val problemNumber = readlnOrNull().orEmpty()

    print("문제 설명 (optional): ")
    val problemDescription = readlnOrNull().orEmpty()

    val currentDate = LocalDate.now()
    val fileName = "${currentDate}_${problemNumber}.kt"

    val folder = File(platform)
    if (!folder.exists()) {
        folder.mkdir()
    }

    val file = File(folder, fileName)
    file.writeText(code)

    val branchName = "${currentDate}_${platform}_${problemNumber}_${problemDescription}"
    val gitCheckoutCommand = arrayOf("git", "checkout", "-b", branchName)
    val checkoutProcess = ProcessBuilder(*gitCheckoutCommand).inheritIO().start()
    checkoutProcess.waitFor()

    val addCommand = arrayOf("git", "add", "$platform/$fileName")
    val addProcess = ProcessBuilder(*addCommand).inheritIO().start()
    addProcess.waitFor()

    val commitMessage = "Add solution for $platform problem $problemNumber"
    val commitCommand = arrayOf("git", "commit", "-m", commitMessage)
    val commitProcess = ProcessBuilder(*commitCommand).inheritIO().start()
    commitProcess.waitFor()

    val pushCommand = arrayOf("git", "push", "-u", "origin", branchName)
    val pushProcess = ProcessBuilder(*pushCommand).inheritIO().start()
    pushProcess.waitFor()

    println("작업 완료")
}