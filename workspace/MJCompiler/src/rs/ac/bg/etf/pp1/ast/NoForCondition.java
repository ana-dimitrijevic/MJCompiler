// generated with ast extension for cup
// version 0.8
// 18/11/2019 19:25:13


package rs.ac.bg.etf.pp1.ast;

public class NoForCondition extends ForCond {

    public NoForCondition () {
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
        buffer.append("NoForCondition(\n");

        buffer.append(tab);
        buffer.append(") [NoForCondition]");
        return buffer.toString();
    }
}
