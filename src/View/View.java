package View;

import Controllers.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View extends JFrame implements PropertyChangeListener {
    // Props and attributes.
    public JTextField som;
    public JButton knopText;
    public JPanel panel;
    public JPanel knopPanel;

    private Controller controller;

    // Het uiterlijk van de rekenmachine.
    public static final String[][] calculator = {
            {"1", "2", "3", "+"},
            {"4", "5", "6", "-"},
            {"7", "8", "9", "*"},
            {"0", ".", "/", "C", "="}
    };

    // constructor
    public View() {
        //Maken van de JPanel Object.
        panel = new JPanel();
        this.add(panel);

        som = new JTextField(20);
        som.setBounds(500, 100, 120, 120);
        som.setEditable(false);
        panel.add(som);

        // Extra JPanel voor de knoppen.
        knopPanel = new JPanel(new GridLayout(4, 5));
        panel.add(knopPanel);

        // Printen Jbuttons met text symbolen.
        for (int i = 0; i < calculator.length; i++) {
            for (int j = 0; j < 5; j++) {
                // Maken JButton objecten en vul met waarde.
                if (j == calculator[i].length) {
                    knopPanel.add(new JLabel());
                } else {
                    // Maak nieuw JButton object en stop text in de Jbuttons.
                    knopText = new JButton(calculator[i][j]);

                    // Voegen Jbuttons aan JPanel.
                    knopPanel.add(knopText);

                    // Voeg ActionListener aan knop objecten.
                    knopText.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Get text uit button.
                            knopText = (JButton) e.getSource();

                            // Stuur text naar controller method.
                            controller.addClickText(knopText.getText());
                        }
                    });
                }
            }
        }
    }

    //setter method set controller object
    public void setController(Controller controller) {
        this.controller = controller;
        //abonneer View aan PropertyChangeListener (Hij wil de post in zijn brievenbus krijgen)
        controller.addPropertyChangeListener(this);
    }

    // Method die de String in de Jtextfield zet
    public void setTextView(String totaal) {
        som.setText(totaal);
    }

    // Dit is de brievenbus hier komt de post binnen dat gestuurd is door de Controller
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Kijken welke post je hebt gekregen een result of een totaal som
        if (evt.getPropertyName().equals("result")) {
            //geef resultaat aan de setTextView method
            setTextView((String) evt.getNewValue());
        }
        if (evt.getPropertyName().equals("totaal")) {
            setTextView((String) evt.getNewValue());
        }
    }
}
