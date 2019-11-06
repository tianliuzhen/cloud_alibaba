package main.com.aaa.web.linuxIo;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/10/22
 */

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;


public class CommandUtil2 {
    // 保存进程的输入流信息
    private List<String> stdoutList = new ArrayList<String>();
    // 保存进程的错误流信息
    private List<String> erroroutList = new ArrayList<String>();

    public  String execute(String userName, String password, String ipAddr, String cmd) {
        String result = "";
        boolean isAuthed = false;
        try {
            if (InetAddress.getByName(ipAddr).isReachable(1500)) {
                Connection conn = new Connection(ipAddr);
                conn.connect();
                isAuthed = conn.authenticateWithPassword(userName, password);
                if (isAuthed) {
                    Session session = conn.openSession();//打开一个会话
                    session.execCommand(cmd);

                    OutputStream outputStream=   session.getStdin();
                    outputStream.write(("\n").getBytes());
                    InputStream inputStream= session.getStdout();
                    ThreadUtil2 stdoutUtil = new ThreadUtil2(session.getStdout(), stdoutList);
                    ThreadUtil2 erroroutUtil = new ThreadUtil2(session.getStderr(), erroroutList);
                    //启动线程读取缓冲区数据
                    stdoutUtil.start();
                    erroroutUtil.start();
                    session.close();
                    conn.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<String> getStdoutList() {
        return stdoutList;
    }

    public List<String> getErroroutList() {
        return erroroutList;
    }

}
