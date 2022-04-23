package com.dkumar.dailynotes.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dkumar.dailynotes.models.Note

@Dao
interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note:Note)

    @Update
    suspend fun update(note:Note)

    @Delete
    suspend fun delete(note:Note)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes():LiveData<List<Note>>
}