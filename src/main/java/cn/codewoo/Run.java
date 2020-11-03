package cn.codewoo;

import cn.codewoo.test.Test1;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

/**
 * @author kehong
 */
public class Run {
    public static void main(String[] args) {
        System.out.println("Running tests!");

        JUnitCore engine = new JUnitCore();
        engine.addListener(new TextListener(System.out)); // required to print reports
        engine.run(Test1.class);
    }
}
