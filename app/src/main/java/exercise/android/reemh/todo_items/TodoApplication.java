package exercise.android.reemh.todo_items;

import android.app.Application;

public class TodoApplication extends Application {
    private TodoItemsHolderImpl database;

    public TodoItemsHolderImpl getDatabase() {
        return database;
    }

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
