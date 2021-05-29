package exercise.android.reemh.todo_items

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*


data class TodoItem(var description: String, val itemID: String, var isDone: Boolean, val creationTime: LocalDateTime, var dateModified : LocalDateTime = creationTime) : Serializable {

	fun changeStatus(newStatus: Boolean):Unit{
		this.isDone = newStatus
	}

	fun changeDateModified(newDate: LocalDateTime){
		this.dateModified = newDate
	}

	fun serialize() : String{
		return "$itemID#$description#$isDone#$creationTime#$dateModified"
	}

	companion object{
		@RequiresApi(Build.VERSION_CODES.O)
		fun stringToItem(itemString : String) : TodoItem? {
			try{
				val data: List<String> = itemString.split("#")
				val id = data[0]
				val description = data[1]
				val isDone = data[2].toBoolean()
				val creationTime = data[3]
				val dateModified = data[4]
				return TodoItem(description, id, isDone, LocalDateTime.parse(creationTime), LocalDateTime.parse(dateModified))
			}
			catch( e: Exception){
				return null
			}
		}
	}

}