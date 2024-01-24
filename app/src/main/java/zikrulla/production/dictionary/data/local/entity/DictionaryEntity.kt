package zikrulla.production.dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity("dictionary",
    foreignKeys = [
        ForeignKey(
            entity = FolderEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("baseId"),
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class DictionaryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var baseId: Long,
    val key: String,
    val value: String,
    val keyDescription: String? = null,
    val valueDescription: String? = null,
    val path: String? = null,
)
