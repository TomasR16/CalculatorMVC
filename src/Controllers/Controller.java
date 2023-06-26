package Controllers;

import Model.Model;
import View.View;
import java.beans.PropertyChangeSupport;

public class Controller {
    // properties
    private Model model;
    public String s0, s1, s2;

    PropertyChangeSupport pcs;

    // Constructor method.
    public Controller() {
        // New PropertyChangeSupport object maken van de postbode.
        pcs = new PropertyChangeSupport(this);

        // Maak string leeg.
        s0 = s1 = s2 = "";

        // New model object.
        model = new Model();
    }

    // Methode krijgt argument uit View binnen.
    public void addClickText(String text) {

        // Kijken of waarde uit text een cijfer is.
        if (text.charAt(0) >= '0' && text.charAt(0) <= '9' || text.charAt(0) == '.') {

            if (!s1.equals("")) {
                s2 = s2 + text;
            } else {
                s0 = s0 + text;
            }

            // Updaten View.
            setResult(s0 + s1 + s2);

        } else if (text.charAt(0) == 'C') { // Als text C is maak String leeg.
            s0 = s1 = s2 = "";

            // update view.
            setResult(s0 + s1 + s2);
        } else if (text.charAt(0) == '=') { // Als text = bereken String
            // Checken welk operator er is String s1 zit.
            double te;
            model.calculate(s0, s2, s1);
            te = model.getCalculationValue();

            // Updaten som View.
            setResult(s0 + s1 + s2);
            setTotaal(te);

            // Bewaren van waarde.
            s0 = Double.toString(te);

            // Legen van String zodat je nieuwe som kan maken.
            s1 = s2 = "";
        } else {
            // Als er geen operator was.
            if (s1.equals("") || s2.equals(""))
                s1 = text;
            else {
                double te;

                // Bewaarde de eerste waarde.
                model.calculate(s0, s2, s1);
                te = model.getCalculationValue();

                // Double naar string conversie.
                s0 = Double.toString(te);

                s1 = text;
                s2 = "";
            }
            setResult(s0 + s1 + s2);
        }
    }

    // Opsturen van de post.
    private void setTotaal(double te) {
        // Stuur post door naar elk persoon dat abonnement heeft met naam totaal
        // geef een null waarde mee en de nieuwe waardes.
        pcs.firePropertyChange("totaal", 0, Double.toString(te));
    }

    // Zelfde methode andere post.
    private void setResult(String s) {
        // Iedereen met brievenbus result krijg nieuwe waarde.
        pcs.firePropertyChange("result", "", s);
    }

    // Aangeven dat de VIEW een abonnement heeft op deze PropertyChangeListener.
    public void addPropertyChangeListener(View view) {
        // Toevoegen view object aan PropertyChangeListener.
        pcs.addPropertyChangeListener(view);
    }

}
