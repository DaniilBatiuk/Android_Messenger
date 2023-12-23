package com.example.lab3
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class DB(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "UserDB"
        private const val TABLE = "secure_users"
        private const val ID = "id"
        private const val USERNAME = "username"
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createSecureUsersTable = "CREATE TABLE $TABLE($ID INTEGER PRIMARY KEY, $USERNAME TEXT, $EMAIL TEXT, $PASSWORD TEXT)"
        db?.execSQL(createSecureUsersTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE")
        onCreate(db)
    }

    fun addSecureUser(username: String, email: String, password: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(USERNAME, username)
            put(EMAIL, email)
            put(PASSWORD, password)
        }

        db.insert(TABLE, null, values)
        db.close()
    }


    fun emailExists(email: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.query(TABLE, arrayOf(ID),
            "$EMAIL = ?", arrayOf(email), null, null, null)
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    @SuppressLint("Range")
    fun validate(username: String, enteredPassword: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.query(TABLE, arrayOf(PASSWORD),
            "$USERNAME = ?", arrayOf(username), null, null, null)

        if (cursor.moveToFirst()) {
            val storedSecurePassword = cursor.getString(cursor.getColumnIndex(PASSWORD))
            cursor.close()
            return enteredPassword == storedSecurePassword
        }
        cursor.close()
        return false
    }
}