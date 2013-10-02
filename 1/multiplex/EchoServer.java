import java.io.*;
import java.net.*;
import java.nio.*;          // ByteBuffer
import java.nio.channels.*; // Selector, SocketChannel, ...
import java.util.*;
import java.util.regex.*;

class EchoServer {
  private static final int PORT = 8007;
  private static final int BUFSIZE = 1024;

  public static void main(String[] args) throws IOException {
    if (args.length > 1) {
      System.err.println("Usage: java EchoServer [port]");
      return;
    }

    int port = PORT;
    if (args.length == 1 && args[0].matches("[0-9]+"))
      port = Integer.parseInt(args[0]);

    ServerSocketChannel  ssc = ServerSocketChannel.open();
    ssc.configureBlocking(false);
    InetSocketAddress addr = new InetSocketAddress(port);
    ssc.socket().bind(addr);

    Selector selector = Selector.open();
    ssc.register(selector, SelectionKey.OP_ACCEPT);

    ByteBuffer buffer = ByteBuffer.allocate(BUFSIZE);

    while (true) {
      int count = selector.select();
      if (count == 0)
        continue;

      Set keys = selector.selectedKeys();
      Iterator it = keys.iterator();

      while (it.hasNext()) {
        SelectionKey  key = (SelectionKey) it.next();	      
        it.remove();

        if (key.isAcceptable()) {
          ServerSocketChannel ch = (ServerSocketChannel) key.channel();
          SocketChannel sc = ch.accept();
          sc.configureBlocking(false);
          sc.register(selector, SelectionKey.OP_READ);
        }

        if (key.isReadable()) {
          SocketChannel sc = (SocketChannel) key.channel();
          buffer.clear();
          int nbytes = sc.read(buffer);
          if (nbytes == -1)
            sc.close();
          else if (nbytes > 0) {
            buffer.flip();
            while (buffer.hasRemaining())
              sc.write(buffer);
          }
        }
      }
    }
  }
}
