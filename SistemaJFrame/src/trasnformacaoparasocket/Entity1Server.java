package trasnformacaoparasocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Entity1Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Número da porta
            System.out.println("Servidor aguardando conexões...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                // Lógica para processar as requisições do cliente
                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
                // Aqui você pode ler objetos enviados pelo cliente usando inputStream.readObject()
                // Adapte esta parte conforme necessário para o seu projeto

                // Exemplo de como você poderia responder ao cliente
                // clientSocket.getOutputStream().write("Resposta do servidor".getBytes());

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}