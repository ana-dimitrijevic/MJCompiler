// generated with ast extension for cup
// version 0.8
// 15/11/2019 21:58:32


package rs.ac.bg.etf.pp1.ast;

public class AssignStatementError extends AssignStmnt {

    public AssignStatementError () {
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
        buffer.append("AssignStatementError(\n");

        buffer.append(tab);
        buffer.append(") [AssignStatementError]");
        return buffer.toString();
    }
}
