package exercise.android.reemh.todo_items

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class TodoItemsHolderImpl( val context : Context) : TodoItemsHolder, Serializable {

	private val _todoList = ArrayList<TodoItem>()
	private val sp : SharedPreferences = context.getSharedPreferences("local_db_todo", Context.MODE_PRIVATE)
	private var itemsLiveData : MutableLiveData<List<TodoItem>> = MutableLiveData<List<TodoItem>>()
	val itemsLiveDataPublic : LiveData<List<TodoItem>> = itemsLiveData

	init{
		this.initializeFromSP();
	}

	@RequiresApi(Build.VERSION_CODES.O)
	fun initializeFromSP(){
		val keys: Set<String> = sp.all.keys
		for(key in keys){
			val itemString = sp.getString(key, null);
			if(itemString != null){
				val item : TodoItem ?= TodoItem.stringToItem(itemString)
				_todoList.add(item!!);
			}
		}
		itemsLiveData.value = ArrayList(_todoList)
	}

	override fun getCurrentItems(): List<TodoItem> {
		val sortedList = _todoList.sortedWith(compareBy<TodoItem> { it.isDone }.thenByDescending { it.creationTime })
		return ArrayList(sortedList);
	}

	@RequiresApi(Build.VERSION_CODES.O)
	override fun addNewInProgressItem(description: String) {
		val itemToAdd = TodoItem(description, UUID.randomUUID().toString(), false, LocalDateTime.now());
		_todoList.add(itemToAdd)
		itemsLiveData.value = ArrayList(_todoList)
		val editor : SharedPreferences.Editor = sp.edit();
		editor.putString(itemToAdd.itemID, itemToAdd.serialize());
		editor.apply();
	}

	override fun markItemDone(item: TodoItem) {
		val foundItem = _todoList.find { it.itemID == item.itemID }
		foundItem?.changeStatus(true)
		itemsLiveData.value = ArrayList(_todoList)
		val editor : SharedPreferences.Editor = sp.edit();
		editor.putString(foundItem?.itemID, foundItem?.serialize());
		editor.apply();
	}
	override fun markItemInProgress(item: TodoItem) {
		val foundItem = _todoList.find { it.itemID == item.itemID }
		foundItem?.changeStatus(false)
		itemsLiveData.value = ArrayList(_todoList)
		val editor : SharedPreferences.Editor = sp.edit();
		editor.putString(foundItem?.itemID, foundItem?.serialize());
		editor.apply();
	}
	override fun deleteItem(item: TodoItem) {
		_todoList.removeAll { it.itemID == item.itemID }
		itemsLiveData.value = ArrayList(_todoList)
		val editor : SharedPreferences.Editor = sp.edit();
		editor.remove(item.itemID);
		editor.apply();
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