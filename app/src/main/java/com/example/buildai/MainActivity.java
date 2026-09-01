package com.example.buildai;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText userInputBox;
    private Button sendBtn;
    private TextView consoleLogText;
    private ScrollView chatScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        userInputBox = findViewById(R.id.userInputBox);
        sendBtn = findViewById(R.id.sendBtn);
        consoleLogText = findViewById(R.id.consoleLogText);
        chatScrollView = findViewById(R.id.chatScrollView);

        appendConsoleLog("[Agent] AI Manager initialized.");
        appendConsoleLog("[Agent] Connected to Termux & GitHub repository.");

        if (sendBtn != null) {
            sendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userInputBox != null) {
                        String msg = userInputBox.getText().toString().trim();
                        if (!msg.isEmpty()) {
                            appendConsoleLog("[User] " + msg);
                            appendConsoleLog("[Agent] Command received: Processing '" + msg + "'...");
                            userInputBox.setText("");
                            Toast.makeText(MainActivity.this, "Command Sent to Agent!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }

    private void appendConsoleLog(String text) {
        if (consoleLogText != null) {
            String current = consoleLogText.getText().toString();
            consoleLogText.setText(current + "\n" + text);
            if (chatScrollView != null) {
                chatScrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        chatScrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        }
    }
}
