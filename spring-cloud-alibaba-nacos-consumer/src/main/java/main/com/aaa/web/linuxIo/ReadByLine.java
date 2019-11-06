package main.com.aaa.web.linuxIo;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/10/21
 */
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SCPInputStream;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadByLine {

    public static void contentToTxt(String filePath, String content) {
        try {
            File f = new File(filePath);
            BufferedWriter output = new BufferedWriter(new FileWriter(f,true));
            output.write(content + "\n");
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
//        txt2StringList( new File("d:/error.log"));
//        test2();
//        SCPInputStream getStream=    getStream("spring.log.2019-10-21.0","/opt/logs/wmlmicro-wechat-service-v1");
//        System.out.println(readStream(getStream));
        main2();
    }

    /**
     *  获取服务器上相应文件的流
     * @param remoteFile 文件名
     * @param remoteTargetDirectory 文件路径
     * @return
     * @throws IOException
     */
    public static SCPInputStream getStream(String remoteFile, String remoteTargetDirectory) throws IOException {
        Connection connection = new Connection("121.40.164.144",22);
        connection.connect();
        boolean isAuthenticated = connection.authenticateWithPassword("root","Haoxia123!");
        if(!isAuthenticated){
            System.out.println("连接建立失败");
            return null;
        }
        SCPClient scpClient = connection.createSCPClient();
        return scpClient.get(remoteTargetDirectory + "/" + remoteFile);
    }
    /**
     * 读取 InputStream 到 String字符串中
     */
    public static String readStream(SCPInputStream in) {
        try {
            //<1>创建字节数组输出流，用来输出读取到的内容
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //<2>创建缓存大小
            byte[] buffer = new byte[1024]; // 1KB
            //每次读取到内容的长度
            int len = -1;
            //<3>开始读取输入流中的内容
            while ((len = in.read(buffer)) != -1) { //当等于-1说明没有数据可以读取了
                baos.write(buffer, 0, len);   //把读取到的内容写到输出流中
            }
            //<4> 把字节数组转换为字符串
            String content = baos.toString();
            //<5>关闭输入流和输出流
            in.close();
            baos.close();
            //<6>返回字符串结果
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return  e.getMessage();
        }


    }
    /**
     * 读取 InputStream 到 String字符串中 2
     */
    public static void main2() {
        try {
            InputStream is = getStream("spring.log.2019-10-21.0","/opt/logs/wmlmicro-wechat-service-v1");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is));
            String str = null;
            while (true) {
                str = reader.readLine();
                if(str!=null){
                    System.out.println(str);
                }
                else{
                    break;
                }
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void  test2(){
        //文件路径
        String filePath="d:/error.log";
        File file=new File(filePath);
        BufferedReader reader = null;
        String tempString = null;
        int line =1;
        try {
            // System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
            while ((tempString = reader.readLine()) != null) {
                System.out.println("Line"+ line + ":" +tempString);
                line ++ ;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
    public static void test3() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("d:/error.log", "rw");
        System.out.println(raf.length());
        raf.seek(raf.length()-10);
        String line=null;
        while((line=raf.readLine())!=null){
            System.out.println(line);
        }
        raf.close();

    }
}
