package Calculator;



public class Calculator {

    public static float calculate(String str) throws InvalidInputStringException{
        ExpressionTree tree = Parser.parse(str);
        if(tree != null) return tree.getResult();
        else throw new InvalidInputStringException("InvalidInputString");
    }
}
