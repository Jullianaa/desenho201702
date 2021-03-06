package com.schoolapp.desenho.schoolapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.schoolapp.desenho.schoolapp.databaseHelper.DataBaseHelper;
import com.schoolapp.desenho.schoolapp.databaseHelper.GenericDBDAO;
import com.schoolapp.desenho.schoolapp.models.SchoolClass;
import com.schoolapp.desenho.schoolapp.models.Event;
import com.schoolapp.desenho.schoolapp.models.AbstractFactory;
import com.schoolapp.desenho.schoolapp.models.SchoolClassFactory;

import java.util.ArrayList;
import java.util.Date;

public class SchoolClassDAO extends GenericDBDAO{
    private static final String WHERE_ID_EQUALS = DataBaseHelper.SCHOOLCLASS_ID_COLUMN + " =?";

    public SchoolClassDAO(Context context) {
        super(context);
    }

    public ArrayList <SchoolClass> getSchoolClasses (Integer disciplineClassId) {
        ArrayList <SchoolClass> schoolClasses = new ArrayList<>();

        String sql = "SELECT * FROM " + DataBaseHelper.SCHOOLCLASS_TABLE +
                " WHERE " + DataBaseHelper.SCHOOLCLASS_DISCIPLINECLASSID_COLUMN + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] {disciplineClassId+""});

        while(cursor.moveToNext()){
            SchoolClass.Builder builder = new SchoolClass.Builder();
            SchoolClass schoolClass = builder.setEventId(disciplineClassId)
                    .setDateEvent(new Date(cursor.getLong(2)))
                    .setStartTime(new Date(cursor.getLong(3)))
                    .setEndTime(new Date(cursor.getLong(4)))
                    .setLocalEvent(cursor.getString(5))
                    .setDisciplineClassId(cursor.getInt(6))
                    .setAbsentClass(cursor.getInt(1))
                    .createSchoolClass();

            schoolClasses.add(schoolClass);
        }
        cursor.close();
        return schoolClasses;
    }

    public SchoolClass getSchoolClass(Integer schoolClassId){
        SchoolClass schoolClass = null;

        String sql = "SELECT * FROM " + DataBaseHelper.SCHOOLCLASS_TABLE +
                " WHERE " + DataBaseHelper.SCHOOLCLASS_ID_COLUMN + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { schoolClassId + "" });
        if(cursor.moveToNext()) {
          SchoolClass.Builder builder = new SchoolClass.Builder();
          schoolClass = builder.setEventId(schoolClassId)
                  .setDateEvent(new Date(cursor.getLong(2)))
                  .setStartTime(new Date(cursor.getLong(3)))
                  .setEndTime(new Date(cursor.getLong(4)))
                  .setLocalEvent(cursor.getString(5))
                  .setDisciplineClassId(cursor.getInt(6))
                  .setAbsentClass(cursor.getInt(1))
                  .createSchoolClass();
        }
        cursor.close();
        return schoolClass;
    }

    public long saveSchoolClass(SchoolClass schoolClass){
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.SCHOOLCLASS_DISCIPLINECLASSID_COLUMN, schoolClass.getDisciplineClassId());
        values.put(DataBaseHelper.SCHOOLCLASS_ABSENTCLASS_COLUMN, schoolClass.getAbsentClass());
        values.put(DataBaseHelper.SCHOOLCLASS_DATEEVENT_COLUMN, schoolClass.getDateEvent().toString());
        values.put(DataBaseHelper.SCHOOLCLASS_ENDTIME_COLUMN, schoolClass.getEndTime().toString());
        values.put(DataBaseHelper.SCHOOLCLASS_LOCALEVENT_COLUMN, schoolClass.getLocalEvent());
        values.put(DataBaseHelper.SCHOOLCLASS_STARTTIME_COLUMN, schoolClass.getStartTime().toString());

        return database.insert(DataBaseHelper.SCHOOLCLASS_TABLE, null, values);
    }

    public long updateSchoolClass(SchoolClass schoolClass){
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.SCHOOLCLASS_DISCIPLINECLASSID_COLUMN, schoolClass.getDisciplineClassId());
        values.put(DataBaseHelper.SCHOOLCLASS_ABSENTCLASS_COLUMN, schoolClass.getAbsentClass());
        values.put(DataBaseHelper.SCHOOLCLASS_DATEEVENT_COLUMN, schoolClass.getDateEvent().toString());
        values.put(DataBaseHelper.SCHOOLCLASS_ENDTIME_COLUMN, schoolClass.getEndTime().toString());
        values.put(DataBaseHelper.SCHOOLCLASS_LOCALEVENT_COLUMN, schoolClass.getLocalEvent());
        values.put(DataBaseHelper.SCHOOLCLASS_STARTTIME_COLUMN, schoolClass.getStartTime().toString());

        long result = database.update(DataBaseHelper.SCHOOLCLASS_TABLE, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(schoolClass.getEventId())});
        Log.d("Update Result:", "=" + result);
        return result;
    }

    public int deleteSchoolClass(SchoolClass schoolClass) {
        return database.delete(DataBaseHelper.SCHOOLCLASS_TABLE, WHERE_ID_EQUALS,
                new String[] { schoolClass.getEventId() + "" });
    }

}
