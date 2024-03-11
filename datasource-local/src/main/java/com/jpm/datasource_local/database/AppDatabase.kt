package com.jpm.datasource_local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jpm.datasource_local.dao.LocalPlanetDao
import com.jpm.datasource_local.entity.LocalPlanet

@Database(entities = [LocalPlanet::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun localPlanetDao(): LocalPlanetDao
}