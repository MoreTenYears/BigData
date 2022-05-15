package com.test.work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String content = br.readLine();
        Socket client = new Socket("localhost", 8088);

        new Thread(new Send(client, content)).start();
        new Thread(new Receive(client)).start();

    }
}
