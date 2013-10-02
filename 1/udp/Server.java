// UDP echo server
import java.io.*;
import java.net.*;

class Server {
  public static void main(String[] args) {
    final int BUFSIZE = 256;

    if (args.length != 1) {
      System.exit(1);
    }
  
    try {
      int port = Integer.parseInt(args[0]);
      DatagramSocket s = new DatagramSocket(port);
      byte[] buffer = new byte[BUFSIZE];
      DatagramPacket packet = new DatagramPacket(buffer, BUFSIZE);
      while (true) {
        s.receive(packet);
        String msg = new String(buffer);
        System.out.println(packet.getAddress() + ":" + 
                         packet.getPort()+ ":" + msg);
        s.send(packet);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
