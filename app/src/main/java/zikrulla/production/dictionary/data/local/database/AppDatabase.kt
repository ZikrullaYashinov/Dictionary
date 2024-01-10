package zikrulla.production.dictionary.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import zikrulla.production.dictionary.data.local.dao.DictionaryDao
import zikrulla.production.dictionary.data.local.dao.FolderDao
import zikrulla.production.dictionary.data.local.entity.DictionaryEntity
import zikrulla.production.dictionary.data.local.entity.FolderEntity

@Database(entities = [FolderEntity::class, DictionaryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun folderDao(): FolderDao
    abstract fun dictionaryDao(): DictionaryDao

}