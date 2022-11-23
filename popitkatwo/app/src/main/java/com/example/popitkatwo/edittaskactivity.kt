package com.example.popitkatwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.popitkatwo.db.MyDbManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList

class edittaskactivity : AppCompatActivity() {
    val myDbManager = MyDbManager(this)
    var id = 0
    var editState = false
    lateinit var editTitle: EditText
    lateinit var editTime: EditText
    lateinit var editData: EditText
    lateinit var knop: FloatingActionButton

    val adap = MyAdapter(ArrayList(), this)
    lateinit var editContent: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edittaskactivity)
        editTitle = findViewById((R.id.editText3))
        editTime = findViewById(R.id.timer)
        editData = findViewById(R.id.dateee)
        editContent = findViewById(R.id.editText4)
        knop = findViewById(R.id.floatingActionButton)
        getmyitems()
    }

    fun butadd(view: View) {

        if(editTitle.text.isNotEmpty()&&editTime.text.isNotEmpty()&&editData.text.isNotEmpty()&&editContent.text.isNotEmpty()){
        if (editState) {
            myDbManager.updateItem(editTitle.text.toString(),
                editContent.text.toString(),
                editTime.text.toString(),
                editData.text.toString(),
                id)

        } else {
            myDbManager.insertToDb(editTitle.text.toString(),
                editContent.text.toString(),
                editTime.text.toString(),
                editData.text.toString())
        }
        finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDB()
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
    }

    fun getmyitems() {
        knop.visibility = View.GONE
        val i = intent
        if (i != null) {
            id = i.getIntExtra(MyIntentConst.I_ID_KEY, 0)
            editTitle.isEnabled=false
            editTime.isEnabled=false
            editData.isEnabled=false
            editContent.isEnabled=false

            knop.visibility = View.VISIBLE
            if (i.getStringExtra(MyIntentConst.I_TITLE_KEY) != null) {
                editState = true
                editTitle.setText(i.getStringExtra(MyIntentConst.I_TITLE_KEY))
                editTime.setText(i.getStringExtra(MyIntentConst.I_TIME_KEY))
                editData.setText(i.getStringExtra(MyIntentConst.I_DATA_KEY))
                editContent.setText(i.getStringExtra(MyIntentConst.I_CONTENT_KEY))
            }
        }
    }

    fun block(view: View) {
        editTitle.isEnabled = true
        editTime.isEnabled = true
        editData.isEnabled = true
        editContent.isEnabled = true
        knop.visibility = View.GONE
    }

    fun perehod(view: View) {
        val intent = Intent(this, generalactivity::class.java)
        startActivity((intent))
    }


}