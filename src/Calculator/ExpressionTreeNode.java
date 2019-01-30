package Calculator;

class ExpressionTreeNode {

    private CalculatorExpression value;
    private ExpressionTreeNode left;
    private ExpressionTreeNode right;

    public ExpressionTreeNode(CalculatorExpression str){
        this.value = str;
    }

    public void insertLeft(ExpressionTreeNode node){
        if(left == null) left = node;
        else left.insertLeft(node);
    }

    public void insertRight(ExpressionTreeNode node){
        if(right == null) right = node;
        else right.insertRight(node);
    }

    public float getResult(){
        if(value.isOperator()){
            return value.doOperation(left.getResult(), right.getResult());
        }
        else return value.getNumberValue();
    }



}
