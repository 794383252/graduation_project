package greendao.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import greendao.db.entity.memorandum;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MEMORANDUM".
*/
public class memorandumDao extends AbstractDao<memorandum, Long> {

    public static final String TABLENAME = "MEMORANDUM";

    /**
     * Properties of entity memorandum.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Content = new Property(1, String.class, "content", false, "CONTENT");
        public final static Property Year = new Property(2, Integer.class, "year", false, "YEAR");
        public final static Property Month = new Property(3, Integer.class, "month", false, "MONTH");
        public final static Property Day = new Property(4, Integer.class, "day", false, "DAY");
        public final static Property Hour = new Property(5, Integer.class, "hour", false, "HOUR");
        public final static Property Minute = new Property(6, Integer.class, "minute", false, "MINUTE");
        public final static Property IsImportant = new Property(7, Boolean.class, "isImportant", false, "IS_IMPORTANT");
    };


    public memorandumDao(DaoConfig config) {
        super(config);
    }
    
    public memorandumDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MEMORANDUM\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"CONTENT\" TEXT," + // 1: content
                "\"YEAR\" INTEGER," + // 2: year
                "\"MONTH\" INTEGER," + // 3: month
                "\"DAY\" INTEGER," + // 4: day
                "\"HOUR\" INTEGER," + // 5: hour
                "\"MINUTE\" INTEGER," + // 6: minute
                "\"IS_IMPORTANT\" INTEGER);"); // 7: isImportant
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MEMORANDUM\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, memorandum entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(2, content);
        }
 
        Integer year = entity.getYear();
        if (year != null) {
            stmt.bindLong(3, year);
        }
 
        Integer month = entity.getMonth();
        if (month != null) {
            stmt.bindLong(4, month);
        }
 
        Integer day = entity.getDay();
        if (day != null) {
            stmt.bindLong(5, day);
        }
 
        Integer hour = entity.getHour();
        if (hour != null) {
            stmt.bindLong(6, hour);
        }
 
        Integer minute = entity.getMinute();
        if (minute != null) {
            stmt.bindLong(7, minute);
        }
 
        Boolean isImportant = entity.getIsImportant();
        if (isImportant != null) {
            stmt.bindLong(8, isImportant ? 1L: 0L);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public memorandum readEntity(Cursor cursor, int offset) {
        memorandum entity = new memorandum( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // content
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // year
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // month
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // day
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // hour
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // minute
            cursor.isNull(offset + 7) ? null : cursor.getShort(offset + 7) != 0 // isImportant
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, memorandum entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setContent(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setYear(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setMonth(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setDay(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setHour(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setMinute(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setIsImportant(cursor.isNull(offset + 7) ? null : cursor.getShort(offset + 7) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(memorandum entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(memorandum entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
