// Importação das bibliotecas necessárias
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

// Definição da classe principal que estende JFrame
public class Interface_NewChat extends JFrame {

    // Lista para armazenar os usuários do sistema
    private List<Usuario> usuarios = new ArrayList<>();

    // Representa o usuário atualmente logado
    private Usuario usuarioLogado;

    // Componentes da interface gráfica
    private JTextField emailField, senhaField;
    private JButton loginButton, logoutButton, cadastrarButton;
    private JTextArea amigosList;
    private JComboBox<Usuario> amigoComboBox;
    private JButton adicionarAmigoButton, consultarAmigosButton, excluirAmigoButton, enviarMensagemButton;

    // Construtor da classe principal
    public Interface_NewChat() {
        // Configurações da janela principal
        setTitle("Sistema de Amigos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de login
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(4, 2));
        loginPanel.add(new JLabel("E-mail: "));
        emailField = new JTextField(20);
        loginPanel.add(emailField);
        loginPanel.add(new JLabel("Senha: "));
        senhaField = new JPasswordField(20);
        loginPanel.add(senhaField);
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        loginPanel.add(loginButton);
        logoutButton = new JButton("Logout");
        logoutButton.setEnabled(false);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        loginPanel.add(logoutButton);
        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaCadastro();
            }
        });
        loginPanel.add(cadastrarButton);
        add(loginPanel, BorderLayout.NORTH);

        // Área para exibir a lista de amigos
        amigosList = new JTextArea(10, 30);
        amigosList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(amigosList);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de interação com amigos
        JPanel amigoPanel = new JPanel();
        amigoPanel.setLayout(new GridLayout(2, 3));
        amigoPanel.add(new JLabel("Amigo: "));
        amigoComboBox = new JComboBox<>();
        amigoPanel.add(amigoComboBox);
        adicionarAmigoButton = new JButton("Adicionar Amigo");
        adicionarAmigoButton.setEnabled(false);
        adicionarAmigoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarAmigo();
            }
        });
        amigoPanel.add(adicionarAmigoButton);
        consultarAmigosButton = new JButton("Consultar Amigos");
        consultarAmigosButton.setEnabled(false);
        consultarAmigosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarAmigos();
            }
        });
        amigoPanel.add(consultarAmigosButton);
        excluirAmigoButton = new JButton("Excluir Amigo");
        excluirAmigoButton.setEnabled(false);
        excluirAmigoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirAmigo();
            }
        });
        amigoPanel.add(excluirAmigoButton);
        amigoPanel.add(new JLabel("Enviar Mensagem: "));
        enviarMensagemButton = new JButton("Enviar");
        enviarMensagemButton.setEnabled(false);
        enviarMensagemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enviarMensagem();
            }
        });
        amigoPanel.add(enviarMensagemButton);
        add(amigoPanel, BorderLayout.SOUTH);
    }

    // Método para abrir a tela de cadastro
    public void abrirTelaCadastro() {
        CadastroDialog cadastroDialog = new CadastroDialog(this);
        cadastroDialog.setVisible(true);
    }

    // Método para realizar o cadastro de um novo usuário
    public void cadastrar(String nome, String email, String senha) {
        // Verifica se o nome só contém letras
        if (isNomeValido(nome)) {
            // Verifica se a senha tem pelo menos 6 caracteres, compostos por letras maiúsculas ou números
            if (isSenhaValida(senha)) {
                // Verifica se o email está no formato correto
                if (isEmailValid(email)) {
                    // Adiciona um novo usuário à lista de usuários
                    Usuario novoUsuario = new Usuario(nome, email, senha);
                    usuarios.add(novoUsuario);

                    // Exibe uma mensagem de sucesso
                    JOptionPane.showMessageDialog(this, "Cadastro bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "O email inserido não está em um formato válido.", "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "A senha deve ter pelo menos 6 caracteres, compostos por letras maiúsculas ou números.", "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "O nome deve conter apenas letras.", "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para verificar se o nome contém apenas letras
    public boolean isNomeValido(String nome) {
        return nome.matches("^[A-Za-z]+$");
    }

    // Método para verificar se a senha tem pelo menos 6 caracteres, compostos por letras maiúsculas ou números
    public boolean isSenhaValida(String senha) {
        return senha.matches("^[A-Z0-9]{6,}$");
    }

    // Método para verificar se o email está no formato correto
    public boolean isEmailValid(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    // Método para realizar o login do usuário
    public void login() {
        String email = emailField.getText();
        String senha = senhaField.getText();

        // Lógica de validação de login precisa ser implementada

        boolean loginSuccessful = false; // Substitua pela lógica de login real

        if (loginSuccessful) {
            usuarioLogado = findUsuarioByEmail(email);
            loginButton.setEnabled(false);
            logoutButton.setEnabled(true);
            adicionarAmigoButton.setEnabled(true);
            consultarAmigosButton.setEnabled(true);
            excluirAmigoButton.setEnabled(true);
            enviarMensagemButton.setEnabled(true);

            JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Login falhou. Por favor, cadastre-se se você não tiver uma conta.", "Login Falhou", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para realizar o logout do usuário
    public void logout() {
        // Lógica de logout precisa ser implementada
    }

    // Métodos para interação com amigos (adicionar, consultar, excluir, enviar mensagem)
    public void adicionarAmigo() {
        // Lógica para adicionar um amigo precisa ser implementada
    }

    public void consultarAmigos() {
        // Lógica para consultar a lista de amigos precisa ser implementada
    }

    public void excluirAmigo() {
        // Lógica para excluir um amigo precisa ser implementada
    }

    public void enviarMensagem() {
        // Lógica para enviar uma mensagem para um amigo precisa ser implementada
    }

    // Método principal para iniciar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Interface_NewChat sistema = new Interface_NewChat();
                sistema.setVisible(true);
            }
        });
    }

    // Classe interna que representa um usuário
    class Usuario {
        private String nome;
        private String email;
        private String senha;

        // Construtor da classe Usuario
        public Usuario(String nome, String email, String senha) {
            this.nome = nome;
            this.email = email;
            this.senha = senha;
        }

        // Métodos de acesso e outros métodos relevantes podem ser adicionados aqui
    }

    // Método para encontrar um usuário pelo email na lista
    public Usuario findUsuarioByEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.email.equals(email)) {
                return usuario;
            }
        }
        return null;
    }
}
