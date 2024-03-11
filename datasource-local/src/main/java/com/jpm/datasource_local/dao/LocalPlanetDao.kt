package com.jpm.datasource_local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jpm.datasource_local.entity.LocalPlanet
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalPlanetDao {
    @Insert
    fun insert(localPlanets: List<LocalPlanet>)

    @Query("DELETE FROM LocalPlanet")
    fun clearAll()

    @Query("SELECT * FROM LocalPlanet")
    fun pagingSource(): PagingSource<Int, LocalPlanet>

    @Query("SELECT * FROM LocalPlanet WHERE id = :id")
    fun getPlanet(id:Long): Flow<LocalPlanet>
}