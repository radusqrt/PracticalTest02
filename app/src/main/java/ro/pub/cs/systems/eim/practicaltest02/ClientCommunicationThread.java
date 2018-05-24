package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientCommunicationThread extends Thread {
    private Socket socket;
    private String message;
    private int port;

    public ClientCommunicationThread(String message, int port) {
        this.port = port;
        this.socket = null;
        this.message = message;
    }

    public void run() {
        Log.d("HEYA", "Client port " + this.port);
        try {
            this.socket = new Socket("localhost", this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PrintWriter requestPrintWriter = Utilities.getWriter(socket);
            requestPrintWriter.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
