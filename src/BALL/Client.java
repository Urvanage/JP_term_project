package BALL;

import java.awt.Font;
import java.awt.Graphics2D;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
	
	private int type;
	private String name;
	private GamePanel gp;
	private Graphics2D g2;
	
	public Client(int deathcount, String name, GamePanel gp,Graphics2D g2) {
		this.type = deathcount;
		this.name = name;
		this.gp= gp;
		this.g2=g2;
	}
	public void runClient() {
        String serverAddress = "localhost"; // 서버 주소 설정
        int serverPort = 12345; // 서버 포트 번호 설정
        String path = System.getProperty("user.dir");

        try {
            Socket socket = new Socket(serverAddress, serverPort);

            // 서버로 데이터를 전송하기 위한 스트림 생성
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // 점수 데이터 전송
            int messageType = type;	
            	outputStream.writeInt(messageType);
            	outputStream.flush();
            	List<Integer> num = new ArrayList<>();
            	List<String> str = new ArrayList<>();
            	String line;
            gp.lb.clear();
            //outputStream.writeInt(0);
            	int cnt = 0;
            	while ((line = (String) inputStream.readObject()) != null) {
                	if (line.equals("EOF")) {
                		break;
               		}
                	if(type < 0) {
                		gp.lb.add(line);
                	}
                	else {
                		String [] split=line.split(" ");
                		num.add(Integer.parseInt(split[0]));
                		str.add(split[1]);
                	}
            	}

            	if(type>=0) {
            		num.add(type);
            		str.add(name);
                	
                	int len = num.size();
                	
                	int[] array = new int[len];
                	for(int i=0;i<len;i++) {
                		array[i] = i;
                	}
                
                	for(int i=0;i<len-1;i++) {
                		for(int j=i+1;j<len;j++) {
                			if(num.get(array[i]) > num.get(array[j])) {
                				int tmp = array[i];
                				array[i] = array[j];
                				array[j] = tmp;
                			}
                		}
                	}
                	
                	if(len > 5) len = 5;
                	
                	try(BufferedWriter writer = new BufferedWriter(new FileWriter(path+"\\"+"Result.txt",false))) {
                		for(int i=0;i<len;i++) {
                			String message = num.get(array[i])+" "+str.get(array[i]);
                    		gp.lb.add(message);
                			writer.write(message);
                			if(i!=len-1) writer.newLine();
                		}
                	}catch (Exception e) {
    				}
            	}
            //outputStream.writeInt(1);


            // 연결 종료
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
