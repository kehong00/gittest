package cn.codewoo.utils;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * zookeeper连接工具类
 * @author kehong
 */
public class ZooKeeperUtils {
    private String url;
    private int timeOut;
    private Watcher watcher;
    public static ZooKeeper getConnection(String url,int timeOut,Watcher watcher){
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper(url, timeOut, watcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zooKeeper;
    }
}
