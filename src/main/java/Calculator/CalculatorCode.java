package Calculator;


public class CalculatorCode {

    public double calculate(String equation) {
        if(equation.contains("-")){
            return substractionEquation(equation);
        }
        if(equation.contains("+")){
            return additionalEquation(equation);
        }
        if(equation.contains("*")){
            return multiplyEquations(equation);
        }
        if(equation.trim().matches("[0-9]+")){
            return Double.parseDouble(equation);
        }
        return Double.NaN;
    }

    private double substractionEquation(String equation) {
        String[] elements = equation.trim().split("-");
        double result = calculate(elements[0]);
        for(int i =1 ; i < elements.length; i++){
            result -= calculate(elements[i]);
        }
        return result;
    }

    private double multiplyEquations(String equation) {
        String[] elements = equation.trim().split("\\*");
        double result = 1.0;
        for(String eachElement : elements){
            result *= calculate(eachElement);
        }
        return result;
    }

    private double additionalEquation(String equation) {
        String[] elements = equation.trim().split("\\+");
        double result = 0.0;
        for(String eachElement : elements){
            result += calculate(eachElement);  //Use of the Recursion to verify single digit and convert to Double
        }
        return result;
    }
}
