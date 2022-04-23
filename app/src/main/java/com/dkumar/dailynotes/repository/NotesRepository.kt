package com.dkumar.dailynotes.repository

import androidx.lifecycle.LiveData
import com.dkumar.dailynotes.database.NotesDAO
import com.dkumar.dailynotes.models.Note

class NotesRepository(private val notesDao:NotesDAO) {
    val allNotes:LiveData<List<Note>> = notesDao.getAllNotes()
    suspend fun insert(note:Note){
        notesDao.insert(note)
    }
    suspend fun delete(note:Note){
        notesDao.delete(note)
    }
    suspend fun update(note:Note){
        notesDao.update(note)
    }
}