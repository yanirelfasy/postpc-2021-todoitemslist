package exercise.android.reemh.todo_items

import android.view.View
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class ItemHolder (view: View) : RecyclerView.ViewHolder(view) {
	val itemCheck : CheckBox = view.findViewById(R.id.item_checkbox)
}