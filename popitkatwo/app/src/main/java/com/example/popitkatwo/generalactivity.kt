package com.example.popitkatwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popitkatwo.db.MyDbManager
import java.util.ArrayList

class generalactivity : AppCompatActivity() {

    val myDbManager= MyDbManager(this)
    val adap=MyAdapter(ArrayList(),this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generalactivity)

        init()
    }

    fun Onclic(view: View) {
        val intent = Intent(this, edittaskactivity::class.java)
        startActivity((intent))
    }
    fun init(){
        val rcView:RecyclerView=findViewById(R.id.recyclerView)
        rcView.layoutManager=LinearLayoutManager(this)
        val swapHelper=swapitem()
        swapHelper.attachToRecyclerView(rcView)
        rcView.adapter=adap
    }
    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDB()
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        filladapter()
    }
    fun filladapter(){
        adap.upgradeAdapter(myDbManager.readDbData())
    }

    fun swapitem():ItemTouchHelper{
        return ItemTouchHelper(object:ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adap.removeItem(viewHolder.adapterPosition, myDbManager)
            }
        })
    }

    fun perehod(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity((intent))
    }
}