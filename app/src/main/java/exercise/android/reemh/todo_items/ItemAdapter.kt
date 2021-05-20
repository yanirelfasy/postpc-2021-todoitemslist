package exercise.android.reemh.todo_items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter: RecyclerView.Adapter<ItemHolder>() {
	private val _items: MutableList<TodoItem> = ArrayList()

	fun setItems(items: List<TodoItem>){
		_items.clear()
		_items.addAll(items)
		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
		val context = parent.context
		val view = LayoutInflater.from(context).inflate(R.layout.row_todo_item, parent, false);
		return ItemHolder(view)
	}

	override fun onBindViewHolder(holder: ItemHolder, position: Int) {
		val item = _items[position]
		holder.itemCheck.text = item.description
	}

	override fun getItemCount(): Int {
		return _items.size
	}

}