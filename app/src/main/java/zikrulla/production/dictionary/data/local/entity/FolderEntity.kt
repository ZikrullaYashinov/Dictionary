package zikrulla.production.dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity("folder")
data class FolderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val baseId: Long,
    val isFolderEnd: Boolean,
) : Serializable
