// generated with ast extension for cup
// version 0.8
// 21/11/2019 20:46:40


package rs.ac.bg.etf.pp1.ast;

public class NoClassVarDeclarationList extends ClassVarDeclList {

    public NoClassVarDeclarationList () {
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
        buffer.append("NoClassVarDeclarationList(\n");

        buffer.append(tab);
        buffer.append(") [NoClassVarDeclarationList]");
        return buffer.toString();
    }
}
