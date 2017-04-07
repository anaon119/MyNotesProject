package com.example.nuelle.mynotes;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;

import java.io.Serializable;
import java.util.Date;

import static android.icu.util.TimeZone.*;

/**
 * Created by Nuelle on 05.04.2017.
 */

public class Note implements Serializable{
    private long nDataTime;
    private String nTitle;
    private String nContent;

    public Note(long dataTime, String title, String content) {
        nContent = content;
        nDataTime = dataTime;
        nTitle = title;
    }
    public void setContent(String content){
        nContent = content;
    }
    public void setTitle(String title){
        nTitle = title;
    }
    public void setDateTime(long dateTime){
        nDataTime = dateTime;
    }

    public long getDataTime(){
        return nDataTime;
    }



    public String getDateTimeAsString(Context context) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"
                , context.getResources().getConfiguration().locale);
        formatter.setTimeZone(getDefault());
        return formatter.format(new Date(nDataTime));
    }
    public String getTitle(){
        return nTitle;
    }
    public String getContent(){
        return nContent;
    }

}
