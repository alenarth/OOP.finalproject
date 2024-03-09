import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagementFrame extends JFrame {
    private String userType;
    private List<String> userList;
    private final String USER_LIST_FILE = "userlist.dat"; // Nome do arquivo da lista de usuários

    public UserManagementFrame(String userType) {
        this.userType = userType;
        setTitle("Gerenciamento de Usuários");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Gerenciamento de Usuários (" + userType + ")");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton addButton = new JButton("Adicionar Usuário");
        JButton editButton = new JButton("Editar Usuário");
        JButton deleteButton = new JButton("Excluir Usuário");

        // Personalize a aparência dos botões.
        addButton.setBackground(Color.GREEN);
        editButton.setBackground(Color.YELLOW);
        deleteButton.setBackground(Color.RED);
        addButton.setForeground(Color.WHITE);
        editButton.setForeground(Color.BLACK);
        deleteButton.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        userList = carregarUserList(); // Carrega a lista de usuários ao iniciar.

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarUsuario();
            }
        });

        add(panel);
    }

    private void adicionarUsuario() {
        String novoUsuario = JOptionPane.showInputDialog(this, "Digite o nome do novo usuário:");
        if (novoUsuario != null && !novoUsuario.trim().isEmpty()) {
            userList.add(novoUsuario);
            salvarUserList();
            JOptionPane.showMessageDialog(this, "Usuário adicionado com sucesso: " + novoUsuario);
        }
    }

    // Função para salvar a lista de usuários em um arquivo.
    private void salvarUserList() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(USER_LIST_FILE))) {
            outputStream.writeObject(userList);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar a lista de usuários.");
        }
    }

    // Função para carregar a lista de usuários de um arquivo, se existir.
    private List<String> carregarUserList() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(USER_LIST_FILE))) {
            return (List<String>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Retorna uma lista vazia se o arquivo não existir ou ocorrer um erro na leitura.
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserManagementFrame userManagementFrame = new UserManagementFrame("admin");
                userManagementFrame.setVisible(true);
            }
        });
    }
}
