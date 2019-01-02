package skilllotto.com.finvest.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import skilllotto.com.finvest.R

import java.util.*
import skilllotto.com.finvest.Model.EmiData



class PdcAdapter(private var items: ArrayList<EmiData>): RecyclerView.Adapter<PdcAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }



    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var userDto = items[position]
//        holder?.txtName?.text = userDto.case_date
//        holder?.txtComment?.text = userDto.case_date
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.user_list_pdc, parent, false)

        return ViewHolder(itemView)
    }

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var txtName: TextView? = null
        var txtComment: TextView? = null

//        init {
//            this.txtName = row?.findViewById<TextView>(R.id.txtName)
//            this.txtComment = row?.findViewById<TextView>(R.id.txtComment)
//        }
    }
}