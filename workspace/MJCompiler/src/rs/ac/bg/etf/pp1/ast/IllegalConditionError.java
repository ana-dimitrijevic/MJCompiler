// generated with ast extension for cup
// version 0.8
// 28/11/2019 22:21:52


package rs.ac.bg.etf.pp1.ast;

public class IllegalConditionError extends Condition {

    public IllegalConditionError () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IllegalConditionError(\n");

        buffer.append(tab);
        buffer.append(") [IllegalConditionError]");
        return buffer.toString();
    }
}
