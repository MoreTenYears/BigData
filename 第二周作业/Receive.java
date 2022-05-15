package com.test.work;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable{

    private Socket client;

    private boolean isRunning;

    private DataInputStream in;

    public Receive(Socket client) {
        this.client =client;
        this.isRunning=true;
        try {
            in = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            try {
                in.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void run() {
        while (isRunning){
            String content = receive();
            System.out.println("内容:"+content);
        }
    }

    private String receive(){
        String content = null;
        try {
            content = in.readUTF();
        } catch (IOException e) {
            this.isRunning=false;
            try {
                in.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return content;
    }
}
