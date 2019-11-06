package main.com.aaa.web.linuxIo;

import ch.ethz.ssh2.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/10/21
 */
@Slf4j
public class test {
    public static void main(String[] args) {
    String str=   execute("root","Haoxia123!","121.40.164.144",
                "tail -f -n 2 /opt/logs/wmlmicro-wechat-service-v1/spring.log.2019-10-21.0");
        System.out.println(str);
    }
    public static String execute(String userName, String password, String ipAddr, String cmd) {
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
                    result = processStdout(session.getStdout());
                    //如果为得到标准输出为空，说明脚本执行出错了
                    if (StringUtils.isBlank(result)) {
                        log.info("得到标准输出为空,链接conn:" + conn + ",执行的命令：" + cmd);
                        result = processStdout(session.getStderr());
                    } else {
                        log.info("执行命令成功,链接conn:" + conn + ",执行的命令：" + cmd);
                    }

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


    private static String processStdout(InputStream in) throws Exception {
        InputStream stdout = new StreamGobbler(in);
        StringBuilder buffer = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                buffer.append(line).append("\n");

            }

        } catch (IOException e) {
            log.error("解析脚本出错：" + e.getMessage());
        }

        return buffer.toString();
    }
    private static String processSinout(InputStream in) throws Exception {
        InputStream stdout = new StreamGobbler(in);
        StringBuilder buffer = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                buffer.append(line).append("\n");

            }

        } catch (IOException e) {
            log.error("解析脚本出错：" + e.getMessage());
        }

        return buffer.toString();
    }
}
