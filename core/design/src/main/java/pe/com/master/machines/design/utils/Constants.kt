package pe.com.master.machines.design.utils

object Constants {


    object Regex {
        val ONLY_LETTERS = Regex("^[a-zA-ZáéíóúñÑÁÉÍÓÚ@.,+\\s]*$")
        val ONLY_NUMBERS = Regex("^[0-9.,\\s]*$")
        val MIXTO = Regex("^[a-zA-Z0-9áéíóúñÑÁÉÍÓÚ@_.,+()/\\s]*$")
    }
}