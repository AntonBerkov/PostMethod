import javafx.application.Application;
import javafx.stage.Stage;


import java.io.*;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
       String params = URLEncoder.encode("param1","UTF-8")+ "="+ URLEncoder.encode("value1","UTF-8");
       Socket socket = new Socket("localhost",1456);

        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
        wr.write("POST "+"/post "+"HTTP/1.1"+"\r\n");
        wr.write("Params: " + params+"\r\n");
        wr.write("Content-lenght: "+params.length()+"\r\n");
        wr.write("Host: localhost:1456"+"\r\n");
        wr.write("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"+"\r\n");
        wr.write("Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3"+"\r\n");
        wr.write("Accept-Encoding: gzip, deflate"+ "\r\n");
        wr.write("Connection: keep-alive"+"\r\n");
        wr.write("Date: " + new Date().toString() + "\r\n");
        wr.write("Accept-Ranges: none\n"+"\r\n");
       // wr.write("\r\n");

        wr.flush();

        BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            wr.write(line);
            System.out.println(line);
        }
        wr.close();
       // rd.close();
    }


    public static void main(String[] args){launch(args);}
}