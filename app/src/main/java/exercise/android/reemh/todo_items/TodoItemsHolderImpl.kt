package exercise.android.reemh.todo_items

import android.os.Build
import androidx.annotation.RequiresApi
import exercise.android.reemh.todo_items.TodoItemsHolder
import exercise.android.reemh.todo_items.TodoItem
import java.time.LocalDateTime

class TodoItemsHolderImpl : TodoItemsHolder {

	private val _todoList = mutableListOf<TodoItem>();

	override fun getCurrentItems(): List<TodoItem> {
		return _todoList;
	}

	@RequiresApi(Build.VERSION_CODES.O)
	override fun addNewInProgressItem(description: String) {
		val itemID = if(_todoList.size == 0){ 0 } else{ _todoList.last().itemID + 1 }
		_todoList += TodoItem(description, itemID)
	}

	override fun markItemDone(item: TodoItem) {
	 	_todoList.find { it.itemID == item.itemID }?.changeStatus(true)
	}
	override fun markItemInProgress(item: TodoItem) {
		_todoList.find { it.itemID == item.itemID }?.changeStatus(false)
	}
	override fun deleteItem(item: TodoItem) {}
}