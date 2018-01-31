package dnyaneshwar.mekotlin.rxjavamvpwithkotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import dnyaneshwar.mekotlin.rxjavamvpwithkotlin.R
import dnyaneshwar.mekotlin.rxjavamvpwithkotlin.beans.ResponseModel
import kotlinx.android.synthetic.main.inflate_user_list.view.*
import org.jetbrains.anko.toast

/**
 * Created by Dnyaneshwar Dalvi on 30/01/18.
 */
class UserListAdapter(var arrayList: ArrayList<ResponseModel.Datum>, var context: Context) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.inflate_user_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (arrayList.size > 0) {
            holder!!.firstName.text = "First Name :${arrayList.get(position).first_name}"
            holder.lastName.text = "Last Name :${arrayList.get(position).last_name}"
            holder.id.text = "Id ${arrayList.get(position).id}"
            Glide.with(context).load(arrayList.get(position).avatar).into(holder.avatar)

            holder.itemView.setOnClickListener {
                context.toast("Hello from ${arrayList.get(position).first_name}")
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstName = itemView.tvFirstName
        var lastName = itemView.tvLastName
        var id = itemView.tvId
        var avatar = itemView.ivImage
    }
}
