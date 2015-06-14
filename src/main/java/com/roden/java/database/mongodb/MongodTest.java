package com.roden.java.database.mongodb;

import java.net.UnknownHostException;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class MongodTest {

    public static void main(String[] args) throws Exception {

        // 连接到Mongodb服务
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        // 连接到你的数据库
        DB db = mongoClient.getDB("test");
        // boolean auth = db.authenticate(myUserName, myPassword);
        // System.out.println("Authentication: " + auth);

        DBCollection users = db.getCollection("User");
        System.out.println("Collection user selected successfully");

        BasicDBObject u = new BasicDBObject("id", 1).
                append("name", "天下第一").
                append("num", 100).
                append("createDate", new Date());         
        users.insert(u);
        System.out.println("user inserted successfully");

        DBCursor cursor = users.find();
        while (cursor.hasNext()) {
            DBObject updateDocument = cursor.next();
            System.out.println(updateDocument);
            // updateDocument.put("likes", "200");
            // users.update(cursor.next(), updateDocument);
        }
        System.out.println(cursor.count());
        System.out.println(cursor.getCursorId());
        System.out.println(JSON.serialize(cursor));

        //DBObject myDoc = users.findOne();
        //users.remove(myDoc);
    }

}
