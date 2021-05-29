package exercise.android.reemh.todo_items;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    private String itemID;
    private TodoItemsHolderImpl holder;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(holder == null){
            holder = TodoApplication.getInstance().getDatabase();
        }
        setContentView(R.layout.edit_item);

        TextView idContent = findViewById(R.id.item_id);
        TextView creationContent = findViewById(R.id.creation_date);
        TextView modifiedContent = findViewById(R.id.modify_date);
        TextView statusContent = findViewById(R.id.status);
        EditText descriptionContent = findViewById(R.id.description_text);

        Intent recIntent = getIntent();
        itemID = recIntent.getStringExtra("item");
        TodoItem itemToEdit  = holder.getByID(itemID);
        idContent.setText(itemToEdit.getItemID());
        creationContent.setText(itemToEdit.getCreationTime().toLocalDate().toString());
        modifiedContent.setText(this.getModifiedString(itemToEdit.getDateModified()));
        if(itemToEdit.isDone()){
            statusContent.setText("Done");
        }
        else{
            statusContent.setText("In Progress");
        }
        descriptionContent.setText(itemToEdit.getDescription());

        Button changeStatusButton = findViewById(R.id.change_status);
        changeStatusButton.setOnClickListener(v -> {
            if(itemToEdit.isDone()){
                holder.markItemInProgress(itemToEdit);
                statusContent.setText("In Progress");
            }
            else{
                holder.markItemDone(itemToEdit);
                statusContent.setText("Done");
            }
            modifiedContent.setText(this.getModifiedString(itemToEdit.getDateModified()));
        });

        EditActivity that = this;
        descriptionContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String newDescription = descriptionContent.getText().toString();
                holder.editItemDescription(itemToEdit.getItemID(), newDescription);
                modifiedContent.setText(that.getModifiedString(itemToEdit.getDateModified()));
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getModifiedString(LocalDateTime lastModified){
        LocalDateTime tempDateTime = LocalDateTime.from( lastModified );
        LocalDateTime now = LocalDateTime.now();
        long days = tempDateTime.until( now, ChronoUnit.DAYS );
        tempDateTime = tempDateTime.plusDays( days );

        long hours = tempDateTime.until( now, ChronoUnit.HOURS );
        tempDateTime = tempDateTime.plusHours( hours );

        long minutes = tempDateTime.until( now, ChronoUnit.MINUTES );
        tempDateTime = tempDateTime.plusMinutes( minutes );

        if(days == 0 && hours == 0 && minutes >= 0){
            return minutes + " minutes ago";
        }
        if(days == 0 && hours > 0){
            return "Today at " + now.toLocalTime().toString();
        }
        return now.toLocalDate().toString() + " at " + now.toLocalTime().toString();

    }


}
