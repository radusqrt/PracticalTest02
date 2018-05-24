package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerCommunicationThread extends Thread {
    private Socket socket;
    private TextView raspunsTextView;

    public ServerCommunicationThread(Socket socket, TextView raspunsTextView) {
        this.socket = socket;
        this.raspunsTextView = raspunsTextView;
    }

    @Override
    public void run() {
        try {
            BufferedReader requestReader = Utilities.getReader(socket);

            Log.d("HEYA", "Got the reader");
            String request = requestReader.readLine();
            Log.d("HEYA", request);

            final String[] words = request.split(",");
            if ("add".equals(words[0])) {
                raspunsTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        int ans = Integer.valueOf(words[1]) + Integer.valueOf(words[2]);
                        raspunsTextView.setText(String.valueOf(ans));
                    }
                });
            } else if ("mul".equals(words[0])) {
                   raspunsTextView.post(new Runnable() {
                       @Override
                       public void run() {
                           int ans = Integer.valueOf(words[1]) * Integer.valueOf(words[2]);
                           raspunsTextView.setText(String.valueOf(ans));
                       }
                   });
            } else {
                raspunsTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        raspunsTextView.setText("Operatie nedefinita");
                    }
                });
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
