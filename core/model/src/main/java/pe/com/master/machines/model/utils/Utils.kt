package pe.com.master.machines.model.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object Utils {

    fun fromStringList(value: List<String>): String {
        return Gson().toJson(value)
    }

    fun toStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
}