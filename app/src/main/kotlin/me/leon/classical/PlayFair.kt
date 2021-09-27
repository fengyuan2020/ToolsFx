package me.leon.classical

import me.leon.encode.base.padding

fun String.playFair(keyword: String): String {
    val alphabeta = "ABCDEFGHIKLMNOPQRSTUVWXYZ".toMutableList()
    val key = keyword.replace(" ", "")

    key.uppercase().toList().distinct().also {
        alphabeta.removeAll(it)
        alphabeta.addAll(0, it)
    }
    return replace(" ", "")
        .uppercase()
        .replace("J", "I")
        .replace("(\\w)\\1".toRegex(), "$1X$1")
        .padding("X", 2)
        .chunked(2)
        .joinToString(" ") {
            val i1 = alphabeta.indexOf(it.first())
            val i2 = alphabeta.indexOf(it.last())
            val tmpChars = mutableListOf<Char>()

            val p1 = i1.point()
            val p2 = i2.point()
            // same row
            if (p1.first == p2.first) {
                tmpChars.add(alphabeta[5 * p1.first + (p2.second + 1) % 5])
                tmpChars.add(alphabeta[5 * p2.first + (p1.second + 1) % 5])
            } else if (p1.second == p2.second) { // same column
                tmpChars.add(alphabeta[5 * ((p1.first + 1) % 5) + p2.second])
                tmpChars.add(alphabeta[5 * ((p2.first + 1) % 5) + p1.second])
            } else {
                tmpChars.add(alphabeta[5 * p1.first + p2.second])
                tmpChars.add(alphabeta[5 * p2.first + p1.second])
            }
            tmpChars.joinToString("")
        }
}

fun String.playFairDecrypt(keyword: String): String {
    val alphabeta = "ABCDEFGHIKLMNOPQRSTUVWXYZ".toMutableList()
    keyword.replace(" ", "").uppercase().toList().distinct().also {
        alphabeta.removeAll(it)
        alphabeta.addAll(0, it)
    }
    return replace("\\s".toRegex(), "")
        .chunked(2)
        .joinToString("") {
            val i1 = alphabeta.indexOf(it.first())
            val i2 = alphabeta.indexOf(it.last())
            val tmpChars = mutableListOf<Char>()

            val p1 = i1.point()
            val p2 = i2.point()
            // same row
            if (p1.first == p2.first) {
                tmpChars.add(alphabeta[5 * p1.first + (p2.second + 4) % 5])
                tmpChars.add(alphabeta[5 * p2.first + (p1.second + 4) % 5])
            } else if (p1.second == p2.second) { // same column
                tmpChars.add(alphabeta[5 * ((p1.first + 4) % 5) + p2.second])
                tmpChars.add(alphabeta[5 * ((p2.first + 4) % 5) + p1.second])
            } else {
                tmpChars.add(alphabeta[5 * p1.first + p2.second])
                tmpChars.add(alphabeta[5 * p2.first + p1.second])
            }
            tmpChars.joinToString("")
        }
        .replace("(\\w)X\\1".toRegex(), "$1$1")
}

private fun Int.point(rowNum: Int = 5) = this / rowNum to this % rowNum