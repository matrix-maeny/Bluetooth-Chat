package com.matrix_maeny.bluetoothchat;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ChatDataBase extends SQLiteOpenHelper {
    public ChatDataBase(@Nullable Context context) {
        super(context, "Chat.db", null, 1);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL("Create Table Chat(name TEXT primary key, chats TEXT)");
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if exists Chat");
    }


    public boolean insertData(String name, String chats) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("chats", chats);

        long result = 0;
        try {
            result = db.insert("Chat", null, cv);
        } catch (Exception ignored) {}

        return result != -1;
    }

    public boolean updateData(String name, String chats) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("chats", chats);

        long result = db.update("Chat", cv, "name=?", new String[]{name});

        return result != -1;
    }


    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("Select * from Chat", null);
    }

    public boolean deleteSpecific(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete("Chat","name=?",new String[]{name});

        return result != -1;
    }


}
