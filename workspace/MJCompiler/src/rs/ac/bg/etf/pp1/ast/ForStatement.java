// generated with ast extension for cup
// version 0.8
// 27/11/2019 17:8:17


package rs.ac.bg.etf.pp1.ast;

public class ForStatement extends Statement {

    private ForDesignatorStm ForDesignatorStm;
    private ForCond ForCond;
    private ForDesignatorStm ForDesignatorStm1;
    private Statement Statement;

    public ForStatement (ForDesignatorStm ForDesignatorStm, ForCond ForCond, ForDesignatorStm ForDesignatorStm1, Statement Statement) {
        this.ForDesignatorStm=ForDesignatorStm;
        if(ForDesignatorStm!=null) ForDesignatorStm.setParent(this);
        this.ForCond=ForCond;
        if(ForCond!=null) ForCond.setParent(this);
        this.ForDesignatorStm1=ForDesignatorStm1;
        if(ForDesignatorStm1!=null) ForDesignatorStm1.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ForDesignatorStm getForDesignatorStm() {
        return ForDesignatorStm;
    }

    public void setForDesignatorStm(ForDesignatorStm ForDesignatorStm) {
        this.ForDesignatorStm=ForDesignatorStm;
    }

    public ForCond getForCond() {
        return ForCond;
    }

    public void setForCond(ForCond ForCond) {
        this.ForCond=ForCond;
    }

    public ForDesignatorStm getForDesignatorStm1() {
        return ForDesignatorStm1;
    }

    public void setForDesignatorStm1(ForDesignatorStm ForDesignatorStm1) {
        this.ForDesignatorStm1=ForDesignatorStm1;
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
        if(ForDesignatorStm!=null) ForDesignatorStm.accept(visitor);
        if(ForCond!=null) ForCond.accept(visitor);
        if(ForDesignatorStm1!=null) ForDesignatorStm1.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForDesignatorStm!=null) ForDesignatorStm.traverseTopDown(visitor);
        if(ForCond!=null) ForCond.traverseTopDown(visitor);
        if(ForDesignatorStm1!=null) ForDesignatorStm1.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForDesignatorStm!=null) ForDesignatorStm.traverseBottomUp(visitor);
        if(ForCond!=null) ForCond.traverseBottomUp(visitor);
        if(ForDesignatorStm1!=null) ForDesignatorStm1.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStatement(\n");

        if(ForDesignatorStm!=null)
            buffer.append(ForDesignatorStm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForCond!=null)
            buffer.append(ForCond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForDesignatorStm1!=null)
            buffer.append(ForDesignatorStm1.toString("  "+tab));
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
