import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Entity2ManagementFrame extends JFrame {
    public Entity2ManagementFrame() {
        setTitle("Gerenciamento da Entidade 2");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Gerenciamento da Entidade 2");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton addButton = new JButton("Adicionar Dados");
        JButton editButton = new JButton("Editar Dados");
        JButton deleteButton = new JButton("Excluir Dados");

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

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Entity2ManagementFrame entity2ManagementFrame = new Entity2ManagementFrame();
                entity2ManagementFrame.setVisible(true);
            }
        });
    }
}
