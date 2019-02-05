import java.net.*;
import java.io.*;


public class ClientChat extends Thread{

  BufferedReader resVersConsoleInput;
  BufferedReader consoleVersResInput;
  PrintStream resVersConsoleOutput;
  PrintStream consoleVersResOutput;
  Socket socket;
  boolean loop = true;

  public boolean authentification(){
    return true;
  }

  public ClientChat(Socket socket){

    this.socket = socket;
    if (initStreams()) {
      this.start();

      try{
        String message="";
        while(loop){
          message = consoleVersResInput.readLine();
          consoleVersResOutput.println(message);
        }
      }catch( IOException e ) {
        System.out.println( "Probleme de lecture" );
      }
    }
    else{
      System.out.println( "Probleme d'initialisation des streams" );
    }
  }

  public static void main(String argv[]) throws Exception{
    try{
      int port = 1234;
      String ip = "127.0.0.1";
      Socket socket;
      socket = new Socket(ip, port);

      new ClientChat(socket);

    }catch( IOException e ) {
        System.out.println( "Probleme de connexion" );
    }
  }

  public boolean initStreams(){
    boolean result= false;
    try{
      resVersConsoleInput = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
      resVersConsoleOutput = new PrintStream(System.out);

      consoleVersResInput =  new BufferedReader( new InputStreamReader(System.in));
      consoleVersResOutput = new PrintStream( socket.getOutputStream() );
      result = true;
    }catch( IOException e ){
      System.out.println( "Probleme d'initialisation des streams" );
    }
    return result;
  }

  public void run(){
    try{
      while(loop){
        String message="";
        message = resVersConsoleInput.readLine();
        resVersConsoleOutput.println(message);
      }
    }catch( IOException e ) {
        System.out.println( "Probleme de lecture" );
    }
  }

}
