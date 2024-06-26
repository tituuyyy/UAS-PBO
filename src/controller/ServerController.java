/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author TITHA
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;

    public ServerController() {
        try {
            serverSocket = new ServerSocket(8000);
            System.out.println("Server is running...");

            clientSocket = serverSocket.accept();
            System.out.println("Client connected...");

            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);

            handleMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleMessages() throws IOException {
        String message;
        while (true) {
            message = reader.readLine();
            System.out.println("Client: " + message);

            String response;
            switch (message) {
                case "1":
                    response = "Tujuan pembuatan chat bot adalah untuk memfasilitasi komunikasi antara manusia dengan mesin secara interaktif.";
                    break;
                case "2":
                    response = "Chat bot adalah program komputer yang dirancang untuk mensimulasikan percakapan dengan manusia, khususnya melalui internet.";
                    break;
                case "3":
                    response = "Terima kasih telah menggunakan layanan chat bot. Sampai jumpa!";
                    closeConnection();
                    return;
                default:
                    response = "Maaf, kode yang Anda masukkan tidak sesuai.";
                    break;
            }

            writer.println(response);
        }
    }

    private void closeConnection() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
