// generated with ast extension for cup
// version 0.8
// 23/0/2020 17:29:8


package rs.ac.bg.etf.pp1.ast;

public class ForStatementBegin extends ForStmBegin {

    public ForStatementBegin () {
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
        buffer.append("ForStatementBegin(\n");

        buffer.append(tab);
        buffer.append(") [ForStatementBegin]");
        return buffer.toString();
    }
}
