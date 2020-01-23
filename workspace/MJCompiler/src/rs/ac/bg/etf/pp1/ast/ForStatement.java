// generated with ast extension for cup
// version 0.8
// 23/0/2020 17:29:8


package rs.ac.bg.etf.pp1.ast;

public class ForStatement extends Statement {

    private ForStmBegin ForStmBegin;
    private ForStartDesignatorStm ForStartDesignatorStm;
    private ForCond ForCond;
    private ForEndDesignatorStm ForEndDesignatorStm;
    private Statement Statement;

    public ForStatement (ForStmBegin ForStmBegin, ForStartDesignatorStm ForStartDesignatorStm, ForCond ForCond, ForEndDesignatorStm ForEndDesignatorStm, Statement Statement) {
        this.ForStmBegin=ForStmBegin;
        if(ForStmBegin!=null) ForStmBegin.setParent(this);
        this.ForStartDesignatorStm=ForStartDesignatorStm;
        if(ForStartDesignatorStm!=null) ForStartDesignatorStm.setParent(this);
        this.ForCond=ForCond;
        if(ForCond!=null) ForCond.setParent(this);
        this.ForEndDesignatorStm=ForEndDesignatorStm;
        if(ForEndDesignatorStm!=null) ForEndDesignatorStm.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ForStmBegin getForStmBegin() {
        return ForStmBegin;
    }

    public void setForStmBegin(ForStmBegin ForStmBegin) {
        this.ForStmBegin=ForStmBegin;
    }

    public ForStartDesignatorStm getForStartDesignatorStm() {
        return ForStartDesignatorStm;
    }

    public void setForStartDesignatorStm(ForStartDesignatorStm ForStartDesignatorStm) {
        this.ForStartDesignatorStm=ForStartDesignatorStm;
    }

    public ForCond getForCond() {
        return ForCond;
    }

    public void setForCond(ForCond ForCond) {
        this.ForCond=ForCond;
    }

    public ForEndDesignatorStm getForEndDesignatorStm() {
        return ForEndDesignatorStm;
    }

    public void setForEndDesignatorStm(ForEndDesignatorStm ForEndDesignatorStm) {
        this.ForEndDesignatorStm=ForEndDesignatorStm;
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
        if(ForStmBegin!=null) ForStmBegin.accept(visitor);
        if(ForStartDesignatorStm!=null) ForStartDesignatorStm.accept(visitor);
        if(ForCond!=null) ForCond.accept(visitor);
        if(ForEndDesignatorStm!=null) ForEndDesignatorStm.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForStmBegin!=null) ForStmBegin.traverseTopDown(visitor);
        if(ForStartDesignatorStm!=null) ForStartDesignatorStm.traverseTopDown(visitor);
        if(ForCond!=null) ForCond.traverseTopDown(visitor);
        if(ForEndDesignatorStm!=null) ForEndDesignatorStm.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForStmBegin!=null) ForStmBegin.traverseBottomUp(visitor);
        if(ForStartDesignatorStm!=null) ForStartDesignatorStm.traverseBottomUp(visitor);
        if(ForCond!=null) ForCond.traverseBottomUp(visitor);
        if(ForEndDesignatorStm!=null) ForEndDesignatorStm.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStatement(\n");

        if(ForStmBegin!=null)
            buffer.append(ForStmBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForStartDesignatorStm!=null)
            buffer.append(ForStartDesignatorStm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForCond!=null)
            buffer.append(ForCond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForEndDesignatorStm!=null)
            buffer.append(ForEndDesignatorStm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForStatement]");
        return buffer.toString();
    }
}
