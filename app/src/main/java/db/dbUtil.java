package db;

import android.content.Context;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import greendao.db.dao.memorandumDao;
import greendao.db.entity.memorandum;

/**
 * Created by Administrator on 2016/9/3.
 */
public class dbUtil {

    public daoManager manager;

    public dbUtil(Context context) {
        manager = new daoManager();
        manager.init(context);
    }

    /**
     * 完成对数据表insert的插入操作
     *
     * @param memorandum
     * @return
     */
    public boolean insertMemorandum(memorandum memorandum) {
        boolean flag = false;
        flag = (manager.getDaoSession().insert(memorandum)) != -1 ? true : false;
        return flag;
    }

    /**
     * 同时插入多条数据可能会耗时比较长，所以单开一个线程进行插入
     *
     * @param memorandumList
     * @return
     */
    public boolean insertMultMemorandum(final List<memorandum> memorandumList) {
        boolean flag = false;
        try {
            manager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (memorandum memorandum : memorandumList) {
                        manager.getDaoSession().insertOrReplace(memorandum);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对memorandum的某个数据的修改
     *
     * @param memorandum
     * @return
     */
    public boolean updataMemorandum(memorandum memorandum) {
        boolean flag = false;
        try {
            manager.getDaoSession().update(memorandum);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 实现删除操作
     *
     * @param memorandum
     * @return
     */
    public boolean deteleData(memorandum memorandum) {
        boolean flag = false;
        try {
            manager.getDaoSession().delete(memorandum);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除memorandum中的所有数据
     *
     * @return
     */
    public boolean deleteAll() {
        boolean flag = false;
        try {
            manager.getDaoSession().deleteAll(memorandum.class);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 返回所有记录
     *
     * @return
     */
    public List<memorandum> listAll() {
        return manager.getDaoSession().loadAll(memorandum.class);
    }

    /**
     * 根据主键返回单条记录
     *
     * @param id
     * @return
     */
    public memorandum listOneMemorandum(int id) {
        return manager.getDaoSession().load(memorandum.class, id);
    }

    public void query1() {
        List<memorandum> list = manager.getDaoSession().queryRaw(memorandum.class, "where name like ? and _id > ?", new String[]{"%李%", "1001"});
    }

    public void query2() {
        QueryBuilder<memorandum> builder = manager.getDaoSession().queryBuilder(memorandum.class);
        //里面每个语句都是逻辑与操作
        //下面语句等同于select * from where _id > ? and year like ?
        //
        builder.where(memorandumDao.Properties.Id.ge(23), memorandumDao.Properties.Year.like("%李%")).limit(3);
        //里面每个语句都是逻辑或的操作
        //下面语句等同于select * from where _id > ? or name like ?
        builder.whereOr(memorandumDao.Properties.Id.ge(23), memorandumDao.Properties.Year.like("%李%"));
    }

}
