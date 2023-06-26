import javax.swing.*;
import Controllers.Controller;
import View.View;

public class Runner {
    public static void main(String[] args) {

        // Maak View object.
        View frame = new View();

        // Maak Controller object.
        Controller controller = new Controller();

        // Set properties
        frame.setController(controller);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 300);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Calculator");
        frame.setVisible(true);

    }
}
