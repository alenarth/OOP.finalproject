package trasnformacaoparasocket;

import java.util.List;
import java.util.ArrayList;  // Adicione esta linha para importar ArrayList
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserManagementClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12348; // Número da porta do servidor de gerenciamento de usuários

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // Lista de usuários (você pode modificar isso conforme necessário)
            List<String> userList = getUserList();

            outputStream.writeObject(userList);

            // Recebe a resposta do servidor
            String response = (String) inputStream.readObject();
            System.out.println(response);

            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getUserList() {
        // Lógica para obter a lista de usuários do seu aplicativo
        // Este é um exemplo básico; você deve adaptar isso conforme necessário
        List<String> userList = new ArrayList<>();
        userList.add("User1");
        userList.add("User2");
        return userList;
    }
}

