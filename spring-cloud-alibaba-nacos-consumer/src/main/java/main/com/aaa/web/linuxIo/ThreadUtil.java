package main.com.aaa.web.linuxIo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/10/23
 */
class ThreadUtil implements Runnable {
    // 设置读取的字符编码
    private String character = "GB2312";
    private List<String> list;
    private InputStream inputStream;

    public ThreadUtil(InputStream inputStream, List<String> list) {
        this.inputStream = inputStream;
        this.list = list;
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.setDaemon(true);//将其设置为守护线程
        thread.start();
    }
    @Override
    public void run() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream, character));
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line != null) {
                    list.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //释放资源
                inputStream.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void test(String  str) {
        CommandUtil util = new CommandUtil();
        util.executeCommand(str);
        printList(util.getStdoutList());
        System.out.println("--------------------");
        printList(util.getErroroutList());
    }


    public static void printList(List<String> list){
        for (String string : list) {
            System.out.println(string);
        }
    }
}
