package com.example.simplebankmanager.paybills


class CodeMap {

    fun create(defaultMap: Map<String, Triple<String, String, Double>>): Map<String, CodeInfo> {
        val codeMap = mutableMapOf<String, CodeInfo>()

        for (item in defaultMap.entries) {
            codeMap[
                item.key
            ] =
                CodeInfo(
                    item.value.first,
                    item.value.second,
                    item.value.third
                )
        }

        return codeMap.toMap()
    }


}

fun String.isRight(defaultCodeMap: Map<String, CodeInfo>): Boolean {
    for (code in defaultCodeMap.keys) {
        if (code == this) return true
    }
    return false
}



