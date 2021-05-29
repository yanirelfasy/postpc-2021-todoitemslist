package exercise.android.reemh.todo_items;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;

public class TodoApplication extends Application implements Serializable {
    private TodoItemsHolderImpl database;

    public TodoItemsHolderImpl getDatabase() {
        return database;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = new TodoItemsHolderImpl(this);
    }

    private static TodoApplication instance = null;

    public static TodoApplication getInstance() {
        return instance;
    }
}
