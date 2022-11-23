package com.example.popitkatwo.db

import android.provider.BaseColumns

object mydb:BaseColumns {
    const val TABLE_NAME="Task"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_CONTENT = "content"
    const val COLUMN_NAME_TIME = "time"
    const val COLUMN_NAME_DATA = "data"

    const val DATABASE_NAME="MyDb.db"
    const val DATABASE_VERSION=1

    const val CREATE_TABLE="CREATE TABLE IF NOT EXISTS $TABLE_NAME ("+
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_TITLE TEXT,$COLUMN_NAME_CONTENT TEXT,$COLUMN_NAME_TIME TEXT,$COLUMN_NAME_DATA TEXT)"
    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

}