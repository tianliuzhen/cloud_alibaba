package main.com.aaa.web.linuxIo;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/10/21
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SCPInputStream;
import ch.ethz.ssh2.SCPOutputStream;

public class ScpClientS {

    private static ScpClientS instance;

    private String ip;

    private int port;

    private String name;

    private String password;

    /**
     * 私有化默认构造函数
     * 实例化对象只能通过getInstance
     */
    private ScpClientS(){

    }

    /**
     * 私有化有参构造函数
     * @param ip 服务器ip
     * @param port 服务器端口 22
     * @param name 登录名
     * @param password 登录密码
     */
    private ScpClientS(String ip,int port,String name,String password){
        this.ip = ip ;
        this.port = port;
        this.name = name;
        this.password = password;
    }

    /**
     * download
     * @param remoteFile 服务器上的文件名
     * @param remoteTargetDirectory 服务器上文件的所在路径
     * @param newPath 下载文件的路径
     */
    public void downloadFile(String remoteFile, String remoteTargetDirectory,String newPath){
        Connection connection = new Connection(ip,port);

        try {
            connection.connect();
            boolean isAuthenticated = connection.authenticateWithPassword(name,password);
            if(isAuthenticated){
                SCPClient scpClient = connection.createSCPClient();
                SCPInputStream sis = scpClient.get(remoteTargetDirectory + "/" + remoteFile);
                File f = new File(newPath);
                if(!f.exists()){
                    f.mkdirs();
                }
                File newFile = new File(newPath + remoteFile);
                FileOutputStream fos = new FileOutputStream(newFile);
                byte[] b = new byte[4096];
                int i;
                while ((i = sis.read(b)) != -1){
                    fos.write(b,0, i);
                }
                fos.flush();
                fos.close();
                sis.close();
                connection.close();
                System.out.println("download ok");
            }else{
                System.out.println("连接建立失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  获取服务器上相应文件的流
     * @param remoteFile 文件名
     * @param remoteTargetDirectory 文件路径
     * @return
     * @throws IOException
     */
    public SCPInputStream getStream(String remoteFile, String remoteTargetDirectory) throws IOException {
        Connection connection = new Connection(ip,port);
        connection.connect();
        boolean isAuthenticated = connection.authenticateWithPassword(name,password);
        if(!isAuthenticated){
            System.out.println("连接建立失败");
            return null;
        }
        SCPClient scpClient = connection.createSCPClient();
        return scpClient.get(remoteTargetDirectory + "/" + remoteFile);
    }

    /**
     * 上传文件到服务器
     * @param f 文件对象
     * @param remoteTargetDirectory 上传路径
     * @param mode 默认为null
     */
    public void uploadFile(File f, String remoteTargetDirectory, String mode) {
        Connection connection = new Connection(ip,port);

        try {
            connection.connect();
            boolean isAuthenticated = connection.authenticateWithPassword(name,password);
            if(!isAuthenticated){
                System.out.println("连接建立失败");
                return ;
            }
            SCPClient scpClient = new SCPClient(connection);
            SCPOutputStream os = scpClient.put(f.getName(),f.length(),remoteTargetDirectory,mode);
            byte[] b = new byte[4096];
            FileInputStream fis = new FileInputStream(f);
            int i;
            while ((i = fis.read(b)) != -1) {
                os.write(b, 0, i);
            }
            os.flush();
            fis.close();
            os.close();
            connection.close();
            System.out.println("upload ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 单例模式
     * 懒汉式
     * 线程安全
     * @return
     */
    public static ScpClientS getInstance(){
        if(null == instance){
            synchronized (ScpClientS.class){
                if(null == instance){
                    instance = new ScpClientS();
                }
            }
        }
        return instance;
    }

    public static ScpClientS getInstance(String ip,int port,String name,String password){
        if(null == instance){
            synchronized (ScpClientS.class){
                if(null == instance){
                    instance = new ScpClientS(ip,port,name,password);
                }
            }
        }
        return instance;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public  static  void  main(String[] args){

        File  f1=new File("C:/Users/nijun/Desktop/QQ截图20181013162821副本.jpg");

        ScpClientS.getInstance("192.168.1.10",22, "root","123456").uploadFile(f1, "/home/temp/", null);

    }


}
