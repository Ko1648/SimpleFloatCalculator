package Calculator;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser {


    private static Matcher m;
    private static List<String> lst;

    /**
     * parses the given string which should match a arithmetic expression and returns the ExpressionTree of the arithmetic expression
     * @param str the string to be evaluated
     * @return the tree
     */
    public static ExpressionTree parse(String str){

        if(!Pattern.matches("(\\d+(?:\\.\\d+)?)(?:(\\+|\\-|\\*|\\/)(\\d+(?:\\.\\d+)?))*", str)) return null;

        lst = new LinkedList<>();
        Pattern p = Pattern.compile("(\\d+(?:\\.\\d+)?).*");
        m = p.matcher(str);
        if(m.matches()) lst.add(m.group(1)); //add the first number to the list

        //match rest and add it to the list
        p = Pattern.compile("(\\+|\\-|\\*|\\/)(\\d+(?:\\.\\d+)?)");
        m = p.matcher(str.substring(lst.get(0).length()));
        while(m.find()){
            lst.add(m.group(1));
            lst.add(m.group(2));
        }



        ExpressionTree tree;

        if(lst.size() == 1) {
            tree = new ExpressionTree(new CalculatorExpression("+"));
            tree.setLeftTree(new ExpressionTreeNode(new CalculatorExpression(lst.get(0))));
            tree.setRightTree(new ExpressionTreeNode(new CalculatorExpression("0")));
            return tree;
        }

        //calculator is left associative
        int groupSize = lst.size(); //at all odd indices is a operator
        int i=1; //not 0 because group 0 is the whole expression; not 1 because its not an operator
        //determine the operator of the root
        while(i < groupSize && (lst.get(i).equals("*") || lst.get(i).equals("/"))) { //+ or - has to be root otherwise the first * or /; i<groupSize because the last element cant be an operator
            i = i + 2;
        }
        int middle; // divides the string in right and left expression half
        if(i<groupSize) {
            tree = new ExpressionTree(new CalculatorExpression(lst.get(i)));
            middle = i;
        }
        else {
            tree = new ExpressionTree(new CalculatorExpression(lst.get(1))); //this is only choosen if there is no + or - for the root
            middle = 1;
        }

        tree.setLeftTree(parseSubExpr(0, middle-1));
        tree.setRightTree(parseSubExpr(middle+1, groupSize-1));
        return tree;
    }

    /**
     *
     * @param left index of the first number
     * @param right index of the last number
     * @return
     */
    private static ExpressionTreeNode parseSubExpr(int left, int right){
        if(left == right) { //is a number
            System.out.println(lst.get(left));
            return new ExpressionTreeNode(new CalculatorExpression(lst.get(left)));
        }

        ExpressionTreeNode node;
        int i=left+1;
        while(i < right && (lst.get(i).equals("*") || lst.get(i).equals("/"))) { //+ or - has to be root otherwise the first * or /; i<groupSize because the last element cant be an operator
            i = i + 2;
        }
        int middle; // divides the string in right and left expression half
        if(i<right) {
            node = new ExpressionTreeNode(new CalculatorExpression(lst.get(i)));
            middle = i;
        }
        else {
            node = new ExpressionTreeNode(new CalculatorExpression(lst.get(left+1))); //this is only choosen if there is no + or - for the root
            middle = left+1;
        }

        node.insertLeft(parseSubExpr(left, middle-1));
        node.insertRight(parseSubExpr(middle+1, right));
        return node;
    }




}
