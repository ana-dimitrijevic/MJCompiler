// generated with ast extension for cup
// version 0.8
// 29/11/2019 13:18:33


package rs.ac.bg.etf.pp1.ast;

public class VariableIdentError extends VarIdent {

    public VariableIdentError () {
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
        buffer.append("VariableIdentError(\n");

        buffer.append(tab);
        buffer.append(") [VariableIdentError]");
        return buffer.toString();
    }
}
