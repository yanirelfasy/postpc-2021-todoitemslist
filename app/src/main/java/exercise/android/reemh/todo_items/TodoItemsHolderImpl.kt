package exercise.android.reemh.todo_items

import android.os.Build
import androidx.annotation.RequiresApi
import exercise.android.reemh.todo_items.TodoItemsHolder
import exercise.android.reemh.todo_items.TodoItem
import java.time.LocalDateTime

class TodoItemsHolderImpl : TodoItemsHolder {

	private val _todoList = mutableListOf<TodoItem>();
	private var _index = 0

	override fun getCurrentItems(): List<TodoItem> {
		val sortedList = _todoList.sortedWith(compareBy<TodoItem> { it.isDone }.thenByDescending { it.creationTime })
		return sortedList;
	}

	@RequiresApi(Build.VERSION_CODES.O)
	override fun addNewInProgressItem(description: String) {
		_todoList += TodoItem(description, _index)
		_index += 1
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
}