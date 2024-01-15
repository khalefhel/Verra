package fr.raouf.verra.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.CommonDataKinds.Phone
import fr.raouf.verra.data.User

class VerraDatabase(mContext: Context) : SQLiteOpenHelper(
    mContext,
    DB_NAME,
    null,
    DB_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        // creation des tables
        val createTableUser = """
            CREATE TABLE users(
            $USER_ID integer PRIMARY KEY,
            $NAME varchar(50),
            $EMAIL varchar(100),
            $PHONE varchar(10),
            $PASSWORD varchar(20)
            )
        """.trimIndent()
        db?.execSQL(createTableUser)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // suppression des anciennes tables,
        // et creation des nouvelles
        db?.execSQL("DROP TABLE IF EXISTS $USERS_TABLE_NAME")
        onCreate(db)
    }

    fun addUser(user: User): Boolean {
        // inserer un nouveau utilisateur dans la bdd
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME, user.name)
        values.put(EMAIL, user.email)
        values.put(PHONE, user.phone)
        values.put(PASSWORD, user.password)

        val result = db.insert(USERS_TABLE_NAME, null, values).toInt()

        db.close()

        return result != -1
    }
    companion object {
        private val DB_NAME = "verra_db"
        private val DB_VERSION = 1
        private val USERS_TABLE_NAME = "users"
        private val USER_ID = "id"
        private val NAME = "id"
        private val EMAIL = "id"
        private val PHONE = "id"
        private val PASSWORD = "id"

    }
}