package it.angelomassaro.exercise02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MultiServer implements Runnable
{
    private Socket client;
    public List<Socket> activeClients = new ArrayList<Socket>();

	public MultiServer(Socket m){
    	//System.out.println(m.toString());
        this.client = m;
    }

    @Override
    public void run(){
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch(IOException ignored) {
        	System.out.println("ignored");
        }

        while (true) {
            String line;
            try {
                while ((line = in.readLine()) != null) {
            		//out.println(line);
            		for (Socket soc : activeClients) {
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
    
    public List<Socket> getActiveClients() {
		return activeClients;
	}

	public void setActiveClients(List<Socket> activeClients) {
		this.activeClients = activeClients;
	}
	
}
