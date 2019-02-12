import java.net.*;
import java.io.*;

class ServerChat {

    public static void main(String argv[]) throws Exception{

        try {

          ServerSocket serversocket = new ServerSocket(1234);

          while (true){

              new ServiceChat(serversocket.accept());
          }
        } catch( IOException e ) {
          System.out.println( "probleme de connexion" );
        }
    }
}
