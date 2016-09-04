package db;

import android.content.Context;

import de.greenrobot.dao.query.QueryBuilder;
import greendao.db.dao.DaoMaster;
import greendao.db.dao.DaoSession;

/**
 * Created by Administrator on 2016/9/3.
 */
public class daoManager {
    //数据库名称
    public static final String DB_NAME = "memorandum.db";
    //多线程访问使用单例模式,volatile关键字用于保证进程每次数据读取都是从内存中读取的
    //但是无法表示程序块的原子性
    private volatile static daoManager manager;
    private static DaoMaster.DevOpenHelper helper;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private Context context;


    public void init(Context context) {
        this.context = context;
    }

    /**
     * 使用单例模式获取操作数据库的对象
     *
     * @return
     */
    public static daoManager getInstance() {
        daoManager instance = null;
        if (instance == null) {
            synchronized (daoManager.class) {
                instance = new daoManager();
                manager = instance;
            }
        }
        return instance;
    }

    /**
     * 判断是否存在数据库，如果没有就创建一个
     *
     * @return
     */
    public DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 完成对数据库的操作，仅仅是一个接口
     *
     * @return
     */
    public DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    /**
     * 打开输出日志的操作，默认是关闭的
     */
    public void setDebug() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    /**
     * 关闭数据库的连接，数据库打开之后必须要关闭
     */
    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }

    private void closeHelper() {
        if (helper != null) {
            helper.close();
            helper = null;
        }
    }

    /**
     * 关闭数据库
     */
    private void closeDaoSession() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }

}
