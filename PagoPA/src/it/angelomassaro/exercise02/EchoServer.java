package it.angelomassaro.exercise02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EchoServer {
	
	static List<Socket> activeSockets = new ArrayList<Socket>();
	static List<MultiServer> activeMultiServer = new ArrayList<MultiServer>();
	
    public static void main(String[] args) throws IOException{

        ServerSocket serverSocket = new ServerSocket(10000);
        while (true) {
            try {
            	Socket s = serverSocket.accept();
            	MultiServer multiServer = new MultiServer(s);
                Thread t = new Thread(multiServer);
                t.start();
                activeSockets.add(s);
            	activeMultiServer.add(multiServer);
                for (MultiServer multi : activeMultiServer) {
                	multi.setActiveSockets(activeSockets);
				}
            } catch(IOException e) {
                System.out.println("Accept Failed:");
                System.exit(-1);
            }
        }
    }
}
