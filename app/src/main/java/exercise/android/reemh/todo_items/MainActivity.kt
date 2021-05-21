package exercise.android.reemh.todo_items

import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
	@JvmField
    var holder: TodoItemsHolder? = null
	@RequiresApi(Build.VERSION_CODES.O)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		if (holder == null) {
			holder = TodoItemsHolderImpl()
		}

		val adapter = ItemAdapter()
		adapter.setItems(holder!!.currentItems);
		adapter.onItemCheckChange = {todoItem: TodoItem ->
			if(!todoItem.isDone){
				holder!!.markItemDone(todoItem)
			}
			else{
				holder!!.markItemInProgress(todoItem)
			}
			adapter.setItems(holder!!.currentItems)
			adapter.notifyDataSetChanged()
		}
		val itemsRecycler: RecyclerView = findViewById(R.id.recyclerTodoItemsList)
		val addTaskBtn : FloatingActionButton = findViewById(R.id.buttonCreateTodoItem);
		val inputText : EditText = findViewById(R.id.editTextInsertTask);

		itemsRecycler.adapter = adapter
		itemsRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
		addTaskBtn.setOnClickListener {
			if(inputText.text.isNotEmpty()){
				holder!!.addNewInProgressItem(inputText.text.toString())
				inputText.setText("");
				adapter.setItems(holder!!.currentItems)
				adapter.notifyDataSetChanged()
			}
		}
		// TODO: implement the specs as defined below
		//    (find all UI components, hook them up, connect everything you need)
	}

}

/*

SPECS:

- the "TodoItems" list is shown in the screen
  * every operation that creates/edits/deletes a TodoItem should immediately be shown in the UI
  * add a functionality to remove a TodoItem. either by a button, long-click or any other UX as you want
- when a screen rotation happens (user flips the screen):
  * the UI should still show the same list of TodoItems
  * the edit-text should store the same user-input (don't erase input upon screen change)

Remarks:
- you should use the `holder` field of the activity
- you will need to create a class extending from RecyclerView.Adapter and use it in this activity
- notice that you have the "row_todo_item.xml" file and you can use it in the adapter
- you should add tests to make sure your activity works as expected. take a look at file `MainActivityTest.java`



(optional, for advanced students:
- save the TodoItems list to file, so the list will still be in the same state even when app is killed and re-launched
)

*/
