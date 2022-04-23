package com.dkumar.dailynotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dkumar.dailynotes.database.NotesDatabase
import com.dkumar.dailynotes.models.Note
import com.dkumar.dailynotes.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application:Application):AndroidViewModel(application) {
    val allNotes: LiveData<List<Note>>
    private val repository: NotesRepository
    init{
        val dao = NotesDatabase.getDatabase(application).getNotesDao()
        repository= NotesRepository(dao)
        allNotes = repository.allNotes
    }
    fun deleteNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
    fun updateNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }
    fun addNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}