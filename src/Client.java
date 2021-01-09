import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final String DEFAULT_SERVER_HOST = "127.0.0.1";
        final int DEFAULT_SERVER_PORT= 9999;
        Socket socket = null;

        BufferedWriter writer = null;
        try {
            //创建socket
            socket = new Socket(DEFAULT_SERVER_HOST,DEFAULT_SERVER_PORT);
            //创建IO流
            BufferedReader reader  = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())//客户端的输入流
            );
            writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );
            //等待用户输入信息
            BufferedReader consoleReader = new BufferedReader(
                    new InputStreamReader(System.in)
            );
            String input = consoleReader.readLine();//读取回车之前的信息
            //发送消息给服务器
            writer.write(input + "\n");//因为server端也是读回车之前的信息的 所以加了换行处理
            writer.flush();
            //读取服务器返回的消息
            String msg = reader.readLine();
            System.out.println(msg);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                    System.out.println("关闭客户端的socket");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
