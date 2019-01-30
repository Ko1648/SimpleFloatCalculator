package Calculator;



class ExpressionTree {

    private CalculatorExpression value;
    private ExpressionTreeNode left;
    private ExpressionTreeNode right;


    public ExpressionTree(CalculatorExpression rootValue){
        this.value = rootValue;
    }

    public void setLeftTree(ExpressionTreeNode node){
        if(this.left == null) this.left = node;
        else this.left.insertLeft(node);
    }

    public void setRightTree(ExpressionTreeNode node){
        if(this.right ==  null) this.right = node;
        else this.right.insertRight(node);
    }

    public float getResult(){
        return value.doOperation(left.getResult(), right.getResult());
    }

}
