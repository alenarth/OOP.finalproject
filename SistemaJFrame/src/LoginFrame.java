import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;

public class LoginFrame extends JFrame {
    private HashMap<String, String> userCredentials;
    private final String CREDENTIALS_FILE = "credentials.dat"; // Nome do arquivo de credenciais

    public LoginFrame() {
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Usuário:");
        JLabel passwordLabel = new JLabel("Senha:");
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        loginButton.setBackground(Color.BLUE);
        loginButton.setForeground(Color.WHITE);

        // Inicialize o HashMap com pares de nome de usuário e senha do código.
        userCredentials = new HashMap<>();
        userCredentials.put("admin", "admin123");
        userCredentials.put("operador", "operador123");

        // Tente carregar as credenciais do arquivo, se existirem.
        HashMap<String, String> savedCredentials = carregarCredenciais();
        if (savedCredentials != null) {
            userCredentials.putAll(savedCredentials);
        }

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido como " + username + ".");
                    openUserManagement(username);
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciais inválidas. Tente novamente.");
                }
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        add(panel);
    }

    private void openUserManagement(String userType) {
        // Salva as credenciais antes de abrir a tela de gerenciamento de usuários.
        salvarCredenciais();

        if (userType.equals("admin")) {
            UserManagementFrame userManagementFrame = new UserManagementFrame("admin");
            userManagementFrame.setVisible(true);
        } else if (userType.equals("operador")) {
            UserManagementFrame userManagementFrame = new UserManagementFrame("operador");
            userManagementFrame.setVisible(true);
        }
        this.dispose();
    }

    // Função para salvar as credenciais em um arquivo.
    private void salvarCredenciais() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(CREDENTIALS_FILE))) {
            outputStream.writeObject(userCredentials);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar as credenciais.");
        }
    }

    // Função para carregar as credenciais de um arquivo, se existirem.
    private HashMap<String, String> carregarCredenciais() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(CREDENTIALS_FILE))) {
            return (HashMap<String, String>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Retorna null se o arquivo não existir ou ocorrer um erro na leitura.
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
    }
}
