package com.test.work;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable {

    private Socket client;

    private String content;

    private boolean isRunning;

    private BufferedReader reader;

    private DataOutputStream out;

    public Send(Socket client, String content) {
        this.client = client;
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.isRunning = true;
        this.content = content;
        try {
            out = new DataOutputStream(client.getOutputStream());
            send(content);
        } catch (IOException e) {
            this.isRunning =false;
            try {
                out.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void send(String content) {
        try {
            out.writeUTF(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (isRunning){
            try {
                reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
