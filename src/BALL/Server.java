package BALL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    

	public static void main(String[] args) {
        String path= System.getProperty("user.dir");
		try( ServerSocket serverSocket = new ServerSocket(12345) ) {
            
            System.out.println("Leaderboard server is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                // 클라이언트와의 통신을 위한 스트림 생성
                ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
                

                // 클라이언트로부터 점수 데이터 수신
                try(BufferedReader reader = new BufferedReader(new FileReader(path+"\\"+"result.txt"))){
                	String line;
                	while((line=reader.readLine())!=null) {
                		outputStream.writeObject(line);
                	}
                	outputStream.writeObject("EOF");
                }catch(IOException e) {
                	
                }


                // 연결 종료
                outputStream.close();
                inputStream.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

