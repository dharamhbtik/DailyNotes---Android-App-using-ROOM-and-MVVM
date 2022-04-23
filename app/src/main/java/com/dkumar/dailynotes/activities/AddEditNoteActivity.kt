package com.dkumar.dailynotes.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dkumar.dailynotes.R
import com.dkumar.dailynotes.models.Note
import com.dkumar.dailynotes.utilities.Constants
import com.dkumar.dailynotes.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.activity_add_edit_note.*
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {
    lateinit var viewModel: NotesViewModel
    var noteID = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        init()
    }
    private fun init(){
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NotesViewModel::class.java]
        val noteType = intent.getStringExtra(Constants.NOTE_TYPE)
        if ( noteType == Constants.NOTE_ACTION_TYPE_EDIT){
            val noteTitle = intent.getStringExtra(Constants.NOTE_TITLE)
            val noteDesc = intent.getStringExtra(Constants.NOTE_DESCRIPTION)
            noteID = intent.getIntExtra(Constants.NOTE_ID,-1)
            btn_add_update_note.text = "Update Note"
            et_note_title.setText(noteTitle)
            et_note_description.setText(noteDesc)
        } else{
            btn_add_update_note.text = "Add Note"
        }
        btn_add_update_note.setOnClickListener {
            val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
            val currentDate:String = sdf.format(Date())
            val noteTitle = et_note_title.text
            val noteDesc = et_note_description.text
            if ( noteType == Constants.NOTE_ACTION_TYPE_EDIT){
                if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()){
                    val updatedNote = Note(noteTitle.toString(),noteDesc.toString(),currentDate)
                    updatedNote.id   = noteID
                    viewModel.updateNote(updatedNote)
                    Toast.makeText(this,"Note has been updated",Toast.LENGTH_LONG).show()
                }
            } else{
                if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()) {
                    val note = Note(noteTitle.toString(), noteDesc.toString(), currentDate.toString())
                    viewModel.addNote(note)
                    Toast.makeText(this,"Note has been added",Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(this,MainActivity::class.java))
            this.finish()
        }
    }
}