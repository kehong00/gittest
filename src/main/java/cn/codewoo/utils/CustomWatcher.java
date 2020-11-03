package cn.codewoo.utils;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @author kehong
 * 实现Wetcher接口
 */
public class CustomWatcher implements Watcher {
    private CountDownLatch countDownLatch;

    public CustomWatcher(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected){
            countDownLatch.countDown();
        }
    }
}
