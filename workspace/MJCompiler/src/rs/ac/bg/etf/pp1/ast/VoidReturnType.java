// generated with ast extension for cup
// version 0.8
// 27/11/2019 19:21:12


package rs.ac.bg.etf.pp1.ast;

public class VoidReturnType extends RetType {

    public VoidReturnType () {
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
        buffer.append("VoidReturnType(\n");

        buffer.append(tab);
        buffer.append(") [VoidReturnType]");
        return buffer.toString();
    }
}
