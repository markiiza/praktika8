package com.example.popitkatwo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.popitkatwo.db.ListItem
import com.example.popitkatwo.db.MyDbManager

class MyAdapter(listMain:ArrayList<ListItem>,contextM: Context):RecyclerView.Adapter<MyAdapter.MyHolder>() {
    var listArray=listMain
    var context=contextM

    class MyHolder(itemView: View,contextV: Context) : RecyclerView.ViewHolder(itemView) {
        var context=contextV
        val atitle=itemView.findViewById<TextView>(R.id.one)
        val acontent=itemView.findViewById<TextView>(R.id.two)
        val atime=itemView.findViewById<TextView>(R.id.three)
        val adata=itemView.findViewById<TextView>(R.id.four)

         fun setdata(item:ListItem){
             atitle.text=item.title
             acontent.text=item.conten
             atime.text=item.time
             adata.text=item.data
            itemView.setOnClickListener(){
                val intent=Intent(context,edittaskactivity::class.java).apply {
                    putExtra(MyIntentConst.I_TITLE_KEY,item.title)
                    putExtra(MyIntentConst.I_CONTENT_KEY,item.conten)
                    putExtra(MyIntentConst.I_TIME_KEY,item.time)
                    putExtra(MyIntentConst.I_DATA_KEY,item.data)
                    putExtra(MyIntentConst.I_ID_KEY,item.id)
                }
                context.startActivity(intent)
            }
         }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater=LayoutInflater.from(parent.context)
        return MyHolder(inflater.inflate(R.layout.potato,parent,false),context)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setdata(listArray.get(position))
    }

    override fun getItemCount(): Int {
        return listArray.size
    }

    fun upgradeAdapter(listItems:List<ListItem>){
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()
    }
    fun removeItem(pos:Int,dbManager: MyDbManager){
        dbManager.removeItemFromDb(listArray[pos].id.toString())
        listArray.removeAt(pos)
        notifyItemRangeChanged(0,listArray.size)
        notifyItemRemoved(pos)

    }
}