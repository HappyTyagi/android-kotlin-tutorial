package skilllotto.com.finvest.DataBase

import android.content.Context

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by Vishwajeet Verma on 10-11-2017.
 */

class DBManager private constructor(private val mDatabaseHelper: SQLiteOpenHelper) {
    private val mOpenCounter = AtomicInteger()
    private var mDatabase: SQLiteDatabase? = null
    @Synchronized
    fun openDatabase(): SQLiteDatabase? {

        /*if (mOpenCounter.incrementAndGet() == 1) {
            // Opening new database
            mDatabase = mDatabaseHelper.getWritableDatabase();
        }*/
        if (mDatabase != null && mDatabase!!.isOpen)
            mDatabase!!.close()
        mDatabase = mDatabaseHelper.writableDatabase
        Log.d("Database open counter: ", "" + mOpenCounter.get())
        return mDatabase
    }

    @Synchronized
    fun closeDatabase() {
        /*  if (mOpenCounter.decrementAndGet() == 0) {
            // Closing database
            mDatabase.close();

        }*/
        if (mDatabase != null && mDatabase!!.isOpen)
            mDatabase!!.close()
        Log.d("Database open counter: ", "" + mOpenCounter.get())
    }

    companion object {
        private var instance: DBManager? = null

        @Synchronized
        fun initializeInstance(helper: SQLiteOpenHelper) {
            if (instance == null) {
                instance = DBManager(helper)
            }
        }

        @Synchronized
        fun getInstance(): DBManager {
            if (instance == null) {
                throw IllegalStateException(DBManager::class.java.simpleName + " is not initialized, call initializeInstance(..) method first.")
            }

            return instance as DBManager
        }

        @Synchronized
        fun getInstance(context: Context): DBManager {
            if (instance == null) {
                instance = DBManager(DBHelper(context))
            }
            return instance as DBManager
        }
    }

}
