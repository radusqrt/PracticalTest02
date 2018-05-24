package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerThread extends Thread {
    private ServerSocket serverSocket;
    private boolean isRunning = false;
    private TextView raspunsTextView;

    ServerThread(String port, TextView raspunsTextView) {
        this.raspunsTextView = raspunsTextView;

        try {
            Log.d("HEYA", String.valueOf(port));
            serverSocket = new ServerSocket(Integer.valueOf(port));
            isRunning = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
//                Log.d(Constants.TAG, "[SERVER] Incomming communication " + socket.getInetAddress() + ":" + socket.getLocalPort());
            } catch (SocketException socketException) {
//                Log.e(Constants.TAG, "An exception has occurred: " + socketException.getMessage());
            } catch (IOException ioException) {
//                Log.e(Constants.TAG, "An exception has occurred: " + ioException.getMessage());
            }
            if (socket != null) {
                Log.d("HEYA", "Server socket not null");
                ServerCommunicationThread serverCommunicationThread = new ServerCommunicationThread(socket, raspunsTextView);
                serverCommunicationThread.start();
            }
        }
    }
}
