package exercise.android.reemh.todo_items

import android.graphics.Paint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import java.time.format.DateTimeFormatter

class ItemAdapter: RecyclerView.Adapter<ItemHolder>() {

	private val _items: MutableList<TodoItem> = ArrayList()
	public var onItemCheckChange: ((TodoItem) -> Unit) ?= null

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

	@RequiresApi(Build.VERSION_CODES.O)
	override fun onBindViewHolder(holder: ItemHolder, position: Int) {
		val item = _items[position]
		holder.itemDescription.text = item.description
		holder.itemDate.text = item.creationTime.format(DateTimeFormatter.ofPattern("d/M/y"))
		holder.itemCheck.setOnClickListener{ view ->
			val callback = onItemCheckChange ?: return@setOnClickListener
			callback(item)
		}
		holder.itemCheck.isChecked = item.isDone
		if(item.isDone){
			holder.itemDescription.setPaintFlags(holder.itemDescription.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
		}
		else{
			holder.itemDescription.setPaintFlags(holder.itemDescription.getPaintFlags() and Paint.STRIKE_THRU_TEXT_FLAG.inv())
		}
	}

	override fun getItemCount(): Int {
		return _items.size
	}

}