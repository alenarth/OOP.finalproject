package trasnformacaoparasocket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Entity2Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12346); // IP e número da porta do servidor da Entidade 2

            // Lógica para enviar dados para o servidor
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            // Aqui você pode enviar objetos para o servidor usando outputStream.writeObject()
            // Adapte esta parte conforme necessário para o seu projeto

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
