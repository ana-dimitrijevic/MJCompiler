// generated with ast extension for cup
// version 0.8
// 27/11/2019 17:8:17


package rs.ac.bg.etf.pp1.ast;

public class NoVariableDeclarationList extends DeclVarList {

    public NoVariableDeclarationList () {
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
        buffer.append("NoVariableDeclarationList(\n");

        buffer.append(tab);
        buffer.append(") [NoVariableDeclarationList]");
        return buffer.toString();
    }
}
