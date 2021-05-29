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

	var holder: TodoItemsHolderImpl? = null;

	@RequiresApi(Build.VERSION_CODES.O)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		if(holder == null){
			holder = TodoApplication.getInstance().database
		}

		setContentView(R.layout.activity_main)

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
		adapter.onDeleteClick = {todoItem: TodoItem ->
			holder!!.deleteItem(todoItem)
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
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		outState.putSerializable("holder", holder);
	}

	@RequiresApi(Build.VERSION_CODES.O)
	override fun onRestoreInstanceState(savedInstanceState: Bundle) {
		super.onRestoreInstanceState(savedInstanceState)
		holder = savedInstanceState.getSerializable("holder") as TodoItemsHolderImpl

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
		adapter.onDeleteClick = {todoItem: TodoItem ->
			holder!!.deleteItem(todoItem)
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
	}

}

