package Calculator;


class CalculatorExpression {

    private boolean isNumber;
    private boolean isOperator;
    private boolean isAddSub;
    private boolean isMulDiv;

    private float numberValue;
    private String operator;

    /**
     *
     * @param str represents either a number or an oparator
     */
    public CalculatorExpression(String str){
        switch (str){
            case "+": case "-":
                operator = str;
                isOperator = true;
                isAddSub = true;
                isMulDiv = false;
                isNumber = false;
                break;
            case "*": case "/":
                operator = str;
                isOperator = true;
                isAddSub = false;
                isMulDiv = true;
                isNumber = false;
                break;
        }
        if(operator == null){
            numberValue = Float.parseFloat(str);
            isOperator = false;
            isAddSub = false;
            isMulDiv = false;
            isNumber = true;
        }
    }

    public boolean isNumber(){
        return isNumber;
    }

    public boolean isOperator(){
        return isOperator;
    }

    public boolean isAddSub() {
        return isAddSub;
    }

    public boolean isMulDiv() {
        return isMulDiv;
    }

    public float getNumberValue() {
        return numberValue;
    }

    public float doOperation(float left, float right){
        if(operator.equals("+")){
            return left+right;
        }
        else if(operator.equals("-")){
            return left-right;
        }
        else if(operator.equals("*")){
            return left*right;
        }
        else {// has to be divide
            return left/right;
        }
    }
}
