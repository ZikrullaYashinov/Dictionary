package zikrulla.production.dictionary.data.model

import java.io.Serializable

data class DictionaryList(
    val list: List<Dictionary>
) : Serializable
