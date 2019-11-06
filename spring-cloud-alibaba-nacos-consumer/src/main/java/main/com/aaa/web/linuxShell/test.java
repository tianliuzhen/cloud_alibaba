package main.com.aaa.web.linuxShell;

import java.util.List;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/10/23
 */
public class test {
    public static void main(String[] args) {
        CommandUtil2 util = new CommandUtil2();
        util.execute("root","Haoxia123!","121.40.164.144",
                "tail -f -n 50000 /opt/logs/wmlmicro-wechat-service-v1/spring.log.2019-10-21.0");;
        printList(util.getStdoutList());
        System.out.println("--------------------");
        printList(util.getErroroutList());
        System.out.println("over");
    }

    public static void printList(List<String> list){
        for (String string : list) {
            System.out.println(string);
        }
    }
}
