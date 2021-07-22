package com.dicoding.tugas.core.data.detailData.coreL.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.tugas.core.data.detailData.coreL.entity.TourismEntity

@Database(entities = [TourismEntity::class], version = 1, exportSchema = false)
abstract class TourismDatabase : RoomDatabase() {

    abstract fun tourismDao(): TourismDao

}