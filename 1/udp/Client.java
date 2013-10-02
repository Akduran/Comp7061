// UDP echo client
// Note: may block forever if reply is lost
// Exercise: fix this
import java.io.*;
import java.net.*;

class Client {
  public static void main(String[] args) {
    final int BUFSIZE = 256;

    if (args.length != 3) {
      System.exit(1);
    }
  
    try {
      int port = Integer.parseInt(args[0]);
      InetAddress destAddress = InetAddress.getByName(args[1]);
      int destPort = Integer.parseInt(args[2]);
      DatagramSocket s = new DatagramSocket(port);
      BufferedReader stdinReader = new BufferedReader(
                                     new InputStreamReader(System.in));

      String line; 
      while (true) {
        System.out.print("> ");
        if ((line = stdinReader.readLine()) == null)
          break;
        byte[] data = line.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, 
                                                   destAddress, destPort);
        s.send(packet);
        byte[] buffer = new byte[BUFSIZE];
        DatagramPacket inPacket = new DatagramPacket(buffer, BUFSIZE);
        s.receive(inPacket);
        System.out.println(new String(buffer));
      }
      s.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
