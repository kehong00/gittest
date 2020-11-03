package cn.codewoo.test;

import cn.codewoo.utils.CustomWatcher;
import cn.codewoo.utils.ZooKeeperUtils;
import org.apache.zookeeper.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Test1 {
//    @Test
    public void create_test() throws IOException, InterruptedException, KeeperException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("42.194.140.230", 3000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                      countDownLatch.countDown();
                }
            }
        });
        countDownLatch.await();

        zooKeeper.create("/hadoop1", "data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }


    /**
     * 创建节点
     */
    @Test
    public void create_utils_test() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper connection = ZooKeeperUtils.getConnection("42.194.140.230", 3000, new CustomWatcher(countDownLatch));
        try {
            countDownLatch.await();
            connection.create("/test1", "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            System.out.println("创建失败!");
        }
    }

    /**
     * 获取节点内容
     */
    @Test
    public void get_test(){
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper connection = ZooKeeperUtils.getConnection("42.194.140.230", 3000, new CustomWatcher(countDownLatch));
        try {
            countDownLatch.await();
            byte[] data = connection.getData("/test1", false, null);
            System.out.println(new String(data));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            System.out.println("获取节点内容失败");
        }
    }


    /**
     * 修改节点信息
     */
    @Test
    public void set_test(){
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper connection = ZooKeeperUtils.getConnection("42.194.140.230", 3000, new CustomWatcher(countDownLatch));
        try {
            countDownLatch.await();
            connection.setData("/test1","change".getBytes(),-1);
            System.out.println("更新成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            System.out.println("更新失败");
        }
    }

    /**
     * 删除节点
     */
    @Test
    public void delete_test(){
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper connection = ZooKeeperUtils.getConnection("42.194.140.230", 3000, new CustomWatcher(countDownLatch));
        try {
            countDownLatch.await();
            connection.delete("/test1",-1);
            System.out.println("删除节点成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            System.out.println("删除节点失败");
        }
    }
}
