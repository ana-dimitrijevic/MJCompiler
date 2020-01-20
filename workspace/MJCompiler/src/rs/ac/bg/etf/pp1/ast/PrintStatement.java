// generated with ast extension for cup
// version 0.8
// 20/0/2020 19:20:46


package rs.ac.bg.etf.pp1.ast;

public class PrintStatement extends Statement {

    private PrintExpression PrintExpression;

    public PrintStatement (PrintExpression PrintExpression) {
        this.PrintExpression=PrintExpression;
        if(PrintExpression!=null) PrintExpression.setParent(this);
    }

    public PrintExpression getPrintExpression() {
        return PrintExpression;
    }

    public void setPrintExpression(PrintExpression PrintExpression) {
        this.PrintExpression=PrintExpression;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(PrintExpression!=null) PrintExpression.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(PrintExpression!=null) PrintExpression.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(PrintExpression!=null) PrintExpression.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStatement(\n");

        if(PrintExpression!=null)
            buffer.append(PrintExpression.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStatement]");
        return buffer.toString();
    }
}
