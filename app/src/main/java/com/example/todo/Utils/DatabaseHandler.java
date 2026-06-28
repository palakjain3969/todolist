package com.example.todo.Utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.todo.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 2;   // Increased version
    private static final String NAME = "toDoListDatabase";
    private static final String TODO_TABLE = "todo";

    private static final String ID = "id";
    private static final String TASK = "task";
    private static final String STATUS = "status";
    private static final String CREATED_AT = "created_at";

    // Database query to create table
    private static final String CREATE_TODO_TABLE =
            "CREATE TABLE " + TODO_TABLE + "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TASK + " TEXT, " +
                    STATUS + " INTEGER, " +
                    CREATED_AT + " TEXT" +
                    ")";

    private SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        onCreate(db);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    // Insert Task
    public void insertTask(ToDoModel task) {
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getTask());
        cv.put(STATUS, task.getStatus());
        cv.put(CREATED_AT, task.getCreatedAt());

        db.insert(TODO_TABLE, null, cv);
    }

    // Get All Tasks
    @SuppressLint("Range")
    public List<ToDoModel> getAllTask() {

        List<ToDoModel> taskList = new ArrayList<>();
        Cursor cur = db.query(TODO_TABLE,null, null, null,null,null,ID + " DESC");

        if (cur != null) {
            while (cur.moveToNext()) {

                ToDoModel task = new ToDoModel();

                task.setId(cur.getInt(cur.getColumnIndex(ID)));
                task.setTask(cur.getString(cur.getColumnIndex(TASK)));
                task.setStatus(cur.getInt(cur.getColumnIndex(STATUS)));
                task.setCreatedAt(cur.getString(cur.getColumnIndex(CREATED_AT)));

                taskList.add(task);
            }
            cur.close();
        }

        return taskList;
    }

    // Update Status
    public void updateStatus(int id, int status) {
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);

        db.update(TODO_TABLE, cv, ID + "=?",
                new String[]{String.valueOf(id)});
    }

    // Update Task
    public void updateTask(int id, String task) {
        ContentValues cv = new ContentValues();
        cv.put(TASK, task);

        db.update(TODO_TABLE, cv, ID + "=?",
                new String[]{String.valueOf(id)});
    }

    // Delete Task
    public void deleteTask(int id) {
        db.delete(TODO_TABLE,
                ID + "=?",
                new String[]{String.valueOf(id)});
    }
}