package exercise.android.reemh.todo_items

import android.content.Context
import android.os.Build
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import java.util.*

class TodoItemsHolderImpl(val context : Context) : TodoItemsHolder {

	private val _todoList = ArrayList<TodoItem>();


	override fun getCurrentItems(): List<TodoItem> {
		val sortedList = _todoList.sortedWith(compareBy<TodoItem> { it.isDone }.thenByDescending { it.creationTime })
		return ArrayList(sortedList);
	}

	@RequiresApi(Build.VERSION_CODES.O)
	override fun addNewInProgressItem(description: String) {
		_todoList += TodoItem(description)
	}

	override fun markItemDone(item: TodoItem) {
	 	_todoList.find { it.itemID == item.itemID }?.changeStatus(true)
	}
	override fun markItemInProgress(item: TodoItem) {
		_todoList.find { it.itemID == item.itemID }?.changeStatus(false)
	}
	override fun deleteItem(item: TodoItem) {
		_todoList.removeAll { it.itemID == item.itemID }
	}

	fun getByID(itemID: String?): TodoItem?{
		if (itemID == null) return null
		for (item in _todoList) {
			if (item.itemID == itemID) {
				return item
			}
		}
		return null
	}

	fun editItem(itemID : String){
		// TODO: Add editing func
	}
}