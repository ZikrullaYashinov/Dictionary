package zikrulla.production.dictionary.data.model

import java.io.Serializable

data class Dictionary(
    val key: String,
    val value: String,
    val id: Int? = null,
    var isSelected: Boolean = true,
) : Serializable
