package trasnformacaoparasocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class UserManagementServer {
    private static final int PORT = 12348; // Número da porta para o servidor de gerenciamento de usuários

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor de Gerenciamento de Usuários aguardando conexões...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());

                // Lógica de gerenciamento de usuários
                List<String> userList = (List<String>) inputStream.readObject();

                // Aqui você deve implementar a lógica de gerenciamento de usuários adequada
                // Por exemplo, adicionar, editar ou excluir usuários da lista
                // Este é um exemplo básico e não deve ser usado em um ambiente de produção

                // Envia uma resposta para o cliente
                outputStream.writeObject("Operação de gerenciamento de usuários concluída com sucesso.");

                clientSocket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
