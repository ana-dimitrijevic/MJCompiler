// generated with ast extension for cup
// version 0.8
// 27/11/2019 17:8:18


package rs.ac.bg.etf.pp1.ast;

public class RelopGEQ extends Relop {

    public RelopGEQ () {
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
        buffer.append("RelopGEQ(\n");

        buffer.append(tab);
        buffer.append(") [RelopGEQ]");
        return buffer.toString();
    }
}
