// generated with ast extension for cup
// version 0.8
// 21/11/2019 22:22:23


package rs.ac.bg.etf.pp1.ast;

public class RelopLEQ extends Relop {

    public RelopLEQ () {
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
        buffer.append("RelopLEQ(\n");

        buffer.append(tab);
        buffer.append(") [RelopLEQ]");
        return buffer.toString();
    }
}
