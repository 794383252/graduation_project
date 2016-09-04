package com.example;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class daoexamplegenerator_example {
    public static void main(String[] args) throws Exception {
        //生成数据库的实体类，前一个是版本号，后一个是包名
        Schema schema = new Schema(1, "greendao.db.entity");
        addMemorandum(schema);
        schema.setDefaultJavaPackageDao("greendao.db.dao");
        try {
            new DaoGenerator().generateAll(schema, "F:/project/MyApplication/app/src/main/java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addMemorandum(Schema schema) {
        //创建数据库的表
        Entity entity = schema.addEntity("memorandum");
        //创建主键是long类型,默认为自增长，增长规律为以第一条记录为基准每次自加一
        entity.addIdProperty();
        //对应数据库的列
        entity.addStringProperty("content");
        entity.addIntProperty("year");
        entity.addIntProperty("month");
        entity.addIntProperty("day");
        entity.addIntProperty("hour");
        entity.addIntProperty("minute");
        entity.addBooleanProperty("isImportant");
    }

}
