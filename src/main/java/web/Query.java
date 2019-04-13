package web;

import common.Util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Query {
    public static List<List<String>> getResult(String queryStr) {
        try {
            int port = (int) Util.getConfig("search_server.port");
            String host = (String) Util.getConfig("search_server.host");
            Socket socket = new Socket(host, port);

            PrintWriter serverWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
            serverWriter.println(queryStr);
            serverWriter.flush();

            ObjectInputStream serverReceiver = new ObjectInputStream(socket.getInputStream());
            List<List<String>> result = (List<List<String>>) serverReceiver.readObject();

            serverReceiver.close();
            serverWriter.close();
            socket.close();
            return result;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
