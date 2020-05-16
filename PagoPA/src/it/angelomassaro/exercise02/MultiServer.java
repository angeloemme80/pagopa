package it.angelomassaro.exercise02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiServer implements Runnable
{
    private Socket client;
    private List<Socket> activeSockets = new ArrayList<Socket>();

	public MultiServer(Socket m){
        this.client = m;
    }

    @Override
    public void run(){
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch(IOException ignored) {
        	System.out.println("ignored");
        }

        while (true) {
            String line;
            try {
                while ((line = in.readLine()) != null ) {
            		for (Socket soc : activeSockets) {
						System.out.println(soc.toString());
						PrintWriter allout = new PrintWriter(soc.getOutputStream(), true);
						allout.println(line);
					}
                }
            } catch (IOException e) {
                System.out.println("Read Failed");
                System.exit(-1);
            }
        }
    }

	public List<Socket> getActiveSockets() {
		return activeSockets;
	}

	public void setActiveSockets(List<Socket> activeSockets) {
		this.activeSockets = activeSockets;
	}
    
    
	
}
