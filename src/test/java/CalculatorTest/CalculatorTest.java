package CalculatorTest;

import Calculator.CalculatorCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    /**  TDD approach
     * 1 - Write the test case
     * 2 - Make the test compile
     * 3 - Watch the test fail
     * 4 - Do just enough code to make the test pass
     * 5 - Refactor and generalise
     */

    //Code is to calculate the string input equation and result in double
    private CalculatorCode calculateCode;
    private static final double delta = 0.01;

    @Before
    public void setUp(){
        calculateCode = new CalculatorCode();
    }

    @Test
    public void whenInputOfOneStringThenResultOfOneDouble(){
        String equation = "1";
        double output = 1.0;
        Assert.assertEquals(output, calculateCode.calculate(equation), delta);
        Assert.assertEquals(25.0, calculateCode.calculate("25"), delta);
    }

    @Test
    public void whenInputPlusEquationReturnDoubleResult(){
        String equation = "1+1";
        String equation2 = "1+1+1";
        Assert.assertEquals(2.0, calculateCode.calculate(equation),delta);
        Assert.assertEquals(3.0, calculateCode.calculate(equation2),delta);
    }

    @Test
    public void whenInputIsMutipleReturnDoubleResult(){
        String equation = "2*2";
        Assert.assertEquals(4.0, calculateCode.calculate(equation),delta);
        Assert.assertEquals(8.0, calculateCode.calculate("2*2+2*2"),delta);
    }

    @Test
    public void whenInputIsSubstractReturnDoubleResult(){
        String equation = "10-5";
        Assert.assertEquals(5.0, calculateCode.calculate(equation),delta);
        Assert.assertEquals(-35.0, calculateCode.calculate("5-15-5-20"),delta);
    }

    @Test
    public void whenInputIsStringOfAllOperation(){
        String equation = "3+4*5-6";
        Assert.assertEquals(17.0, calculateCode.calculate(equation),delta);
    }

    @Test
    public void whenInputIsCharReturnNaN(){
        String equation = "a";
        Assert.assertEquals(Double.NaN, calculateCode.calculate(equation),delta);
    }
}
