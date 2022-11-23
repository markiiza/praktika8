package com.example.popitkatwo.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.widget.SearchView


class MyDbManager(val context: Context) {
    val mydbhelp=MyDbHelper(context)
    var db:SQLiteDatabase?=null

    fun openDb(){
        db=mydbhelp.writableDatabase
    }
    fun insertToDb(title: String,content: String,time: String,data:String){
        val values=ContentValues().apply {
            put(mydb.COLUMN_NAME_TITLE,title)
            put(mydb.COLUMN_NAME_CONTENT,content)
            put(mydb.COLUMN_NAME_TIME,time)
            put(mydb.COLUMN_NAME_DATA,data)
        }
        db?.insert(mydb.TABLE_NAME,null,values)
    }
    fun updateItem(title: String,content: String,time: String,data:String,id: Int){
        val selection=BaseColumns._ID+"=$id"
        val values=ContentValues().apply {
            put(mydb.COLUMN_NAME_TITLE,title)
            put(mydb.COLUMN_NAME_CONTENT,content)
            put(mydb.COLUMN_NAME_TIME,time)
            put(mydb.COLUMN_NAME_DATA,data)

        }
        db?.update(mydb.TABLE_NAME,values,selection,null)
    }


    fun removeItemFromDb(id:String){
        val selecthion=BaseColumns._ID+"=$id"
        db?.delete(mydb.TABLE_NAME,selecthion,null)
    }
    fun readDbData():ArrayList<ListItem>{
        val dataList=ArrayList<ListItem>()

        val cersor=db?.query(mydb.TABLE_NAME,null,null, null,
            null,null,null)
        with(cersor){
            while (cersor?.moveToNext()!!){
                val datatext1=cersor.getString(cersor.getColumnIndexOrThrow(mydb.COLUMN_NAME_TITLE))
                val datatext2=cersor.getString(cersor.getColumnIndexOrThrow(mydb.COLUMN_NAME_CONTENT))
                val datatext3=cersor.getString(cersor.getColumnIndexOrThrow(mydb.COLUMN_NAME_TIME))
                val datatext4=cersor.getString(cersor.getColumnIndexOrThrow(mydb.COLUMN_NAME_DATA))
                val dataid=cersor.getInt(cersor.getColumnIndexOrThrow(BaseColumns._ID))
                val item=ListItem()
                item.id=dataid
                item.title=datatext1
                item.conten=datatext2
                item.time=datatext3
                item.data=datatext4
                dataList.add(item)
            }
        }
        cersor?.close()
        return dataList
    }

    fun closeDB(){
        mydbhelp.close()
    }

}