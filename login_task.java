
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class User {
    private String username;
    private String password;
    private String role;

    User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}

class WelcomePage extends Frame {

    WelcomePage(User user) {
        super("Welcome");

        setLayout(new GridLayout(2, 1, 10, 10));

        Label welcomeLabel = new Label(
            "Hello, " + user.getUsername(), Label.CENTER
        );

        Label roleLabel = new Label(
            "Role: " + user.getRole(), Label.CENTER
        );

        welcomeLabel.setFont(
            new Font("SansSerif", Font.BOLD, 20)
        );

        add(welcomeLabel);
        add(roleLabel);

        setSize(350, 200);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}

public class LoginFormTask extends Frame {

    private ArrayList<User> users = new ArrayList<>();
    private int failedAttempts = 0;

    public LoginFormTask() {
        super("Login");
        loadUsers();

        setLayout(new GridBagLayout());
        Panel content = new Panel(new BorderLayout(10, 15));

        Label title = new Label("Welcome back", Label.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        content.add(title, BorderLayout.NORTH);

        Panel form = new Panel(new GridLayout(3, 2, 8, 8));

        form.add(new Label("Username:", Label.RIGHT));
        TextField username = new TextField(18);
        form.add(username);

        form.add(new Label("Password:", Label.RIGHT));
        TextField password = new TextField(18);
        password.setEchoChar('*');
        form.add(password);

        form.add(new Label("Role:", Label.RIGHT));
        Choice role = new Choice();
        role.add("Student");
        role.add("TA");
        role.add("Professor");
        form.add(role);

        content.add(form, BorderLayout.CENTER);

        Panel buttons = new Panel(
            new FlowLayout(FlowLayout.CENTER, 15, 0)
        );

        Button cancel = new Button("Cancel");
        Button login = new Button("Login");

        buttons.add(cancel);
        buttons.add(login);
        content.add(buttons, BorderLayout.SOUTH);

        add(content);

        // Login Logic
        login.addActionListener(e -> {

            String enteredUsername = username.getText().trim();
            String enteredPassword = password.getText();
            String selectedRole = role.getSelectedItem();

            // Check empty fields
            if (enteredUsername.isEmpty() ||
                enteredPassword.isEmpty()) {

                showMessage("Please fill all fields!");
                return;
            }

            // Find user
            User user = findUser(enteredUsername);

            if (user == null) {
                showMessage("User not found!");
                return;
            }

            // Check password
            if (!user.getPassword().equals(enteredPassword)) {

                failedAttempts++;

                if (failedAttempts >= 3) {
                    showMessage(
                        "Too many failed attempts! Login disabled."
                    );
                    login.setEnabled(false);
                } else {
                    showMessage(
                        "Wrong password! Attempts left: "
                        + (3 - failedAttempts)
                    );
                }

                return;
            }

            // Check role
            if (!user.getRole().equals(selectedRole)) {
                showMessage("Incorrect role!");
                return;
            }

            // Login successful
            failedAttempts = 0;
            showMessage("Login successful!");

            new WelcomePage(user).setVisible(true);
            dispose();
        });

        // Cancel Button
        cancel.addActionListener(e -> {
            dispose();
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setSize(420, 340);
        setLocationRelativeTo(null);
    }

    // Hardcoded Users
    private void loadUsers() {
        users.add(new User("mohamed", "1234", "Student"));
        users.add(new User("ahmed", "5678", "TA"));
        users.add(new User("ali", "9999", "Professor"));
    }

    // Search for user
    private User findUser(String name) {
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }

    // Show message dialog
    private void showMessage(String message) {
        Dialog dialog = new Dialog(this, "Message", true);
        dialog.setLayout(new BorderLayout(10, 10));

        Label label = new Label(message, Label.CENTER);
        dialog.add(label, BorderLayout.CENTER);

        Button ok = new Button("OK");
        ok.addActionListener(e -> dialog.dispose());

        Panel panel = new Panel();
        panel.add(ok);
        dialog.add(panel, BorderLayout.SOUTH);

        dialog.setSize(400, 150);
        dialog.setLocationRelativeTo(this);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.dispose();
            }
        });

        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFormTask().setVisible(true);
    }
}
