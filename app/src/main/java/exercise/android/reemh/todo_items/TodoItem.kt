package exercise.android.reemh.todo_items

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*


data class TodoItem(val description: String) : Serializable {
	val itemID: String = UUID.randomUUID().toString();
	var isDone = false
	@RequiresApi(Build.VERSION_CODES.O)
	val creationTime =  LocalDateTime.now();

	fun changeStatus(newStatus: Boolean):Unit{
		isDone = newStatus
	}
}