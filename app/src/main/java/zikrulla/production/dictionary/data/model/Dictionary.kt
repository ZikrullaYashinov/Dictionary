package zikrulla.production.dictionary.data.model

import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import java.io.Serializable

data class Dictionary(
    val key: String,
    val value: String,
    var isSelected: Boolean = true,
) : Serializable {
    fun toDictionaryEntity(): DictionaryEntity {
        return DictionaryEntity(0, 0, key, value)
    }
}
