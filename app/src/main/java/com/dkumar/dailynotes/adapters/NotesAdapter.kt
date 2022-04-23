package com.dkumar.dailynotes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dkumar.dailynotes.R
import com.dkumar.dailynotes.models.Note
import kotlinx.android.synthetic.main.note_rv_item.view.*

class NotesAdapter(
    val mContext: Context,
    val noteClickInterface:NoteClickDetailInterface,
    val noteDeleteInterface:NoteClickDeleteInterface
    ): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    private val allNotes = ArrayList<Note>()
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
            val noteTV = itemView.idTVNoteTitle
            val timeTV = itemView.idTVTimeStamp
            val deleteTV = itemView.idTVDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.noteTV.text = currentNote.noteTitle
        holder.timeTV.text ="Last Updated :  ${currentNote.timeStamp}"
        holder.deleteTV.setOnClickListener {
           noteDeleteInterface.onDeleteNotes(currentNote)
        }
        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(currentNote)
        }
    }

    override fun getItemCount(): Int {
       return allNotes.size
    }
    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}
interface NoteClickDeleteInterface{
fun onDeleteNotes(note: Note)
}
interface NoteClickDetailInterface{
    fun onNoteClick(note:Note)
}