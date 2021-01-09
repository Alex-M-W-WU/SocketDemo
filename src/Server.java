import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        final int DEFAULT_PORT=9999;
        ServerSocket serverSocket = null;

        //绑定监听端口
        try {
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("启动服务器，监听端口"+DEFAULT_PORT);
            while (true){
                //等待客户端连接
                Socket socket = serverSocket.accept();//accept之后客户端会一直保持阻塞的状态
                System.out.println("客户端["+socket.getPort()+"]已连接");
                //建立完成之后，读取客户端发送过来的数据
                BufferedReader reader = new BufferedReader(//装饰器模式
                        new InputStreamReader(socket.getInputStream())
                );
                //把服务器端的数据写入到客户端
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())
                );
                //读取客户端发送的消息
                String msg = reader.readLine();
                if (msg!=null){
                    System.out.println("客户端["+socket.getPort()+"]: "+msg);
                    //回复客户端发送的消息
                    writer.write("服务端/服务器：" +msg + "\n");
                    writer.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    //关闭服务器端
                    serverSocket.close();
                    System.out.println("关闭服务器端的serversocket");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //writer.close();
        }
    }
}
