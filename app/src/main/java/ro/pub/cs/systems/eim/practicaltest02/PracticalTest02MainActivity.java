package ro.pub.cs.systems.eim.practicaltest02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.Socket;

public class PracticalTest02MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test02_main);

        final EditText operator1EditText = (EditText) findViewById(R.id.operator1EditText);
        final EditText operator2EditText = (EditText) findViewById(R.id.operator2EditText);
        final EditText operationEditText = (EditText) findViewById(R.id.operationEditText);
        final EditText clientPortEditText = (EditText) findViewById(R.id.clientServerPort);
        final EditText serverPortEditText = (EditText) findViewById(R.id.serverPortEditText);
        Button serverStartButton = (Button) findViewById(R.id.serverStartButton);
        Button clientSendButton = (Button) findViewById(R.id.clientSendButton);
        final TextView raspunsTextView = (TextView) findViewById(R.id.raspunsTextView);

        clientSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String operator1 = operator1EditText.getText().toString();
                String operator2 = operator2EditText.getText().toString();
                String operation = operationEditText.getText().toString();
                String message = operation + "," + operator1 + "," + operator2;
                int port = Integer.valueOf(clientPortEditText.getText().toString());

                ClientCommunicationThread clientCommunicationThread = new ClientCommunicationThread(message, port);
                clientCommunicationThread.start();
            }
        });

        serverStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerThread serverThread = new ServerThread(serverPortEditText.getText().toString(), raspunsTextView);
                serverThread.start();
            }
        });
    }
}
