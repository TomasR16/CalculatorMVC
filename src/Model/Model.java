package Model;

//Model class does the calculations
public class Model {
    // props
    private Double calculationValue;

    // Kijken welke operatie moet worden uitgevoerd.
    public void calculate(String first, String second, String operation) {
        // Maak Double van String eerste en tweede symbool.
        Double s0 = Double.parseDouble(first);
        Double s2 = Double.parseDouble(second);

        switch (operation.charAt(0)) {
            case '+':
                this.addNums(s0, s2);
                break;
            case '-':
                this.minusNums(s0, s2);
                break;
            case '*':
                this.timesNums(s0, s2);
                break;
            case '/':
                this.divideNum(s0, s2);
                break;
        }
    }

    // methodes
    public void addNums(double firstNumber, double secondNumber) { calculationValue = firstNumber + secondNumber; }

    public void minusNums(double firstNumber, double secondNumber) {
        calculationValue = firstNumber - secondNumber;
    }

    public void timesNums(double firstNumber, double secondNumber) {
        calculationValue = firstNumber * secondNumber;
    }

    public void divideNum(double firstNumber, double secondNumber) {
        calculationValue = firstNumber / secondNumber;
    }

    // Getter methode.
    public double getCalculationValue() {

        return calculationValue;

    }
}
