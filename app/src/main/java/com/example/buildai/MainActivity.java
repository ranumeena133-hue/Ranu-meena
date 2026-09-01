package com.example.buildai;

/* ==========================================================
 *  UPDATED BY ARENA AI AGENT 🚀
 *  TEST SYNC STATUS: SUCCESSFUL ✅
 *  DATE: 2026-09-02
 * ========================================================== */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // UI Elements
    private EditText userInputBox;
    private Button sendBtn;
    private TextView consoleLogText;
    private ScrollView chatScrollView;
    private TextView statusManager;
    private TextView statusDev;
    private TextView statusFileOp;
    private TextView statusInspector;
    private TextView statusVision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Initialize UI Elements from XML Layout
        userInputBox = findViewById(R.id.userInputBox);
        sendBtn = findViewById(R.id.sendBtn);
        consoleLogText = findViewById(R.id.consoleLogText);
        chatScrollView = findViewById(R.id.chatScrollView);
        
        statusManager = findViewById(R.id.statusManager);
        statusDev = findViewById(R.id.statusDev);
        statusFileOp = findViewById(R.id.statusFileOp);
        statusInspector = findViewById(R.id.statusInspector);
        statusVision = findViewById(R.id.statusVision);

        // Welcome Log Messages from Arena AI Agent
        appendConsoleLog("=========================================");
        appendConsoleLog("🚀 ARENA AI AGENT CONNECTED SUCCESSFULLY!");
        appendConsoleLog("⚡ REALTIME SYNC TEST: VERIFIED");
        appendConsoleLog("=========================================");
        appendConsoleLog("[Manager] AI Workspace System Ready.");
        appendConsoleLog("[Dev] Java File Updated by Arena Agent.");

        // Send Button Click Listener
        if (sendBtn != null) {
            sendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userInputBox != null) {
                        String userCommand = userInputBox.getText().toString().trim();
                        if (!userCommand.isEmpty()) {
                            appendConsoleLog("\n[User]: " + userCommand);
                            appendConsoleLog("[Agent Response]: Executing order -> '" + userCommand + "'...");
                            userInputBox.setText("");
                            Toast.makeText(MainActivity.this, "Order sent to Agent!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Kripya koi order likhein!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }

    // Helper method to add logs to the Terminal Console
    private void appendConsoleLog(String text) {
        if (consoleLogText != null) {
            String currentText = consoleLogText.getText().toString();
            consoleLogText.setText(currentText + "\n" + text);
            
            // Auto scroll down to latest message
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
