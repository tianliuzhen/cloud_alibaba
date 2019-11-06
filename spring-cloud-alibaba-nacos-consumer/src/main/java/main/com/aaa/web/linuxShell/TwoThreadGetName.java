package main.com.aaa.web.linuxShell;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/10/23
 */

public class TwoThreadGetName extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            printMsg();
        }
    }
    public void printMsg() {
        Thread t = Thread.currentThread();
        String name = t.getName();
        System.out.println("name=" + name);
    }
    public static void main(String[] args) {
        TwoThreadGetName tt = new TwoThreadGetName();
        tt.start();
        for (int i = 0; i < 10; i++) {
            tt.printMsg();
        }
    }
}

