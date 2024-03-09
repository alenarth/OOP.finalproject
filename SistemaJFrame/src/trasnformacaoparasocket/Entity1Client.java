package trasnformacaoparasocket;

import java.io.IOException;
        import java.io.ObjectOutputStream;
        import java.net.Socket;

public class Entity1Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // IP e número da porta do servidor

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

