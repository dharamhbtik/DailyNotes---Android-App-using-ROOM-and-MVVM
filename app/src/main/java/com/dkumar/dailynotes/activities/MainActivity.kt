package com.dkumar.dailynotes.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dkumar.dailynotes.R
import com.dkumar.dailynotes.adapters.NoteClickDeleteInterface
import com.dkumar.dailynotes.adapters.NoteClickDetailInterface
import com.dkumar.dailynotes.adapters.NotesAdapter
import com.dkumar.dailynotes.models.Note
import com.dkumar.dailynotes.utilities.Constants
import com.dkumar.dailynotes.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NoteClickDetailInterface, NoteClickDeleteInterface {
    lateinit var viewModel:NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        idRvNotes.layoutManager = LinearLayoutManager(this)
        val notesRVAdapter = NotesAdapter(this,this,this)
        idRvNotes.adapter = notesRVAdapter
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NotesViewModel::class.java]
        viewModel.allNotes.observe(this, Observer{
        it?.let{
            notesRVAdapter.updateList(it)
        }
        })
        idFABAddNote.setOnClickListener {
        val intent = Intent(this,AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this,AddEditNoteActivity::class.java)
        intent.putExtra(Constants.NOTE_TYPE,Constants.NOTE_ACTION_TYPE_EDIT)
        intent.putExtra(Constants.NOTE_ID,note.id)
        intent.putExtra(Constants.NOTE_TITLE,note.noteTitle)
        intent.putExtra(Constants.NOTE_DESCRIPTION,note.noteDescription)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteNotes(note: Note) {
       viewModel.deleteNote(note)
        Toast.makeText(this,"${note.noteTitle} has been deleted",Toast.LENGTH_LONG).show()
    }
}