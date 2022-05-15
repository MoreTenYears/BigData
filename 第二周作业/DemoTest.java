package com.test.work;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class DemoTest {

    private static CopyOnWriteArrayList<Channel> all = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8088);
        while (true) {
            Socket client = server.accept();
            Channel channel = new Channel(client);
            all.add(channel);
            new Thread(channel).start();
        }

    }

    static class Channel implements Runnable {

        private DataInputStream in;
        private DataOutputStream out;

        private Socket client;

        private boolean isRunning;

        private String content;

        public Channel(Socket client) {
            this.client = client;
            try {
                out = new DataOutputStream(client.getOutputStream());
                in = new DataInputStream(client.getInputStream());
                isRunning = true;

                this.content = receive();
                this.send("对话开始");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void send(String content) {
            try {
                out.writeUTF(content);
                out.flush();
            } catch (IOException e) {
                close();
            }
        }


        private String receive() {
            try {
                return in.readUTF();
            } catch (IOException e) {
                close();
            }
            return "";
        }

        private void close() {
            this.isRunning = false;
            try {
                in.close();
                out.close();
                all.remove(this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        @Override
        public void run() {
            while (isRunning) {
                String content = receive();
                System.out.println(content);
            }
        }
    }
}
