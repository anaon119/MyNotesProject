package com.example.nuelle.mynotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nuelle on 05.04.2017.
 */

public class NoteAdapter extends ArrayAdapter<Note> {


    public NoteAdapter(Context context, int resource, ArrayList<Note> notes) {
        super(context, resource, notes);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_note, null);
        }
        Note note = getItem(position);

        if(note != null) {
            TextView title = (TextView) convertView.findViewById(R.id.listTitle);
            TextView date = (TextView) convertView.findViewById(R.id.listDate);
            TextView content = (TextView) convertView.findViewById(R.id.listContent);


            title.setText(note.getTitle());
            date.setText(note.getDateTimeAsString(getContext()));

            if (note.getContent().length() > 45) {
                content.setText(note.getContent().substring(0, 45));
            }else{
                content.setText(note.getContent());
            }
        }
        return  convertView;
    }

}
