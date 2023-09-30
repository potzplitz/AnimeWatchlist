package api;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPApi {
	
	public void udpApi(String name) throws IOException {
		
		@SuppressWarnings("resource")
		DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("api.anidb.net");
        int serverPort = 9000;

        // Set up the UDP request data
        byte[] requestData = "PING".getBytes();

        // Send the UDP request
        DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, serverAddress, serverPort);
        socket.send(requestPacket);

        // Receive the UDP response
        byte[] responseData = new byte[1024];
        DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length);
        socket.receive(responsePacket);

        // Parse the UDP response
        String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
        System.out.println(response);
		
	}

}
