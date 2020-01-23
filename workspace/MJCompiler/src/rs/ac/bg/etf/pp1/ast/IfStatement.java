// generated with ast extension for cup
// version 0.8
// 23/0/2020 17:29:8


package rs.ac.bg.etf.pp1.ast;

public class IfStatement extends Statement {

    private IfStart IfStart;
    private IfConditions IfConditions;
    private Statement Statement;

    public IfStatement (IfStart IfStart, IfConditions IfConditions, Statement Statement) {
        this.IfStart=IfStart;
        if(IfStart!=null) IfStart.setParent(this);
        this.IfConditions=IfConditions;
        if(IfConditions!=null) IfConditions.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public IfStart getIfStart() {
        return IfStart;
    }

    public void setIfStart(IfStart IfStart) {
        this.IfStart=IfStart;
    }

    public IfConditions getIfConditions() {
        return IfConditions;
    }

    public void setIfConditions(IfConditions IfConditions) {
        this.IfConditions=IfConditions;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfStart!=null) IfStart.accept(visitor);
        if(IfConditions!=null) IfConditions.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfStart!=null) IfStart.traverseTopDown(visitor);
        if(IfConditions!=null) IfConditions.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfStart!=null) IfStart.traverseBottomUp(visitor);
        if(IfConditions!=null) IfConditions.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfStatement(\n");

        if(IfStart!=null)
            buffer.append(IfStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfConditions!=null)
            buffer.append(IfConditions.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfStatement]");
        return buffer.toString();
    }
}
