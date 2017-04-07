package com.example.nuelle.mynotes;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {
    private EditText nEtTitle;
    private EditText nEtContent;

    private String noteFileName;
    private Note loadedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        nEtTitle = (EditText) findViewById(R.id.noteTitle);
        nEtContent = (EditText) findViewById(R.id.noteContent);

        noteFileName = getIntent().getStringExtra("NOTE_FILE");
        if(noteFileName != null && !noteFileName.isEmpty()){
            loadedNote = Utilities.getNoteByName(this, noteFileName);

            if(loadedNote != null){
                nEtTitle.setText(loadedNote.getTitle());
                nEtContent.setText(loadedNote.getContent());
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_save:
                    saveNote();

            case R.id.action_delete:
                    deleteNote();
                break;
        }
        return true;
    }


    private  void saveNote(){
        Note note;
        if(nEtContent.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Content fild is empty! Please enter something.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(loadedNote == null) {
            note = new Note(System.currentTimeMillis(), nEtTitle.getText().toString(), nEtContent.getText().toString());
        }else{
            note = new Note(loadedNote.getDataTime(), nEtTitle.getText().toString(), nEtContent.getText().toString());
        }
        if(Utilities.saveNote(this, note)){
            Toast.makeText(this, "Your note is save!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Note did`nt saved! Please make sure you have enough space on your device.", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
    private void deleteNote(){
        if(loadedNote == null){
            finish();
        }else{
            AlertDialog.Builder dialog = new AlertDialog.Builder(this). setTitle("Delete")
                    .setMessage("You are about to delete " + nEtTitle.getText().toString() + ", are you sure?").
                    setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Utilities.deleteNote(getApplicationContext(), loadedNote.getDataTime() + Utilities.FILE_Extension);

                            Toast.makeText(getApplicationContext(), nEtTitle.getText().toString() + " is deleted", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    })
                    .setNegativeButton("No", null).setCancelable(false);

            dialog.show();

        }
    }
}
