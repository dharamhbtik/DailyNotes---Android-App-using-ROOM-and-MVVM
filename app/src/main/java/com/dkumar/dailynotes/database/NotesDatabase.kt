package com.dkumar.dailynotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dkumar.dailynotes.models.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase:RoomDatabase() {
    abstract fun getNotesDao():NotesDAO

    companion object{
        @Volatile
        private var INSTANCE:NotesDatabase?=null
        fun getDatabase(mContext:Context):NotesDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(mContext.applicationContext,
                NotesDatabase::class.java,
                "Notes_Database")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE= instance
                instance
            }
        }
    }
}