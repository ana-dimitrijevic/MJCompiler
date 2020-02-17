// generated with ast extension for cup
// version 0.8
// 29/0/2020 11:12:31


package rs.ac.bg.etf.pp1.ast;

public class ForStatement extends Statement {

    private ForStart ForStart;
    private ForStartDsgnStm ForStartDsgnStm;
    private ForCondStart ForCondStart;
    private ForCond ForCond;
    private ForCondEnd ForCondEnd;
    private ForEndDsgnStm ForEndDsgnStm;
    private ForBodyStart ForBodyStart;
    private Statement Statement;

    public ForStatement (ForStart ForStart, ForStartDsgnStm ForStartDsgnStm, ForCondStart ForCondStart, ForCond ForCond, ForCondEnd ForCondEnd, ForEndDsgnStm ForEndDsgnStm, ForBodyStart ForBodyStart, Statement Statement) {
        this.ForStart=ForStart;
        if(ForStart!=null) ForStart.setParent(this);
        this.ForStartDsgnStm=ForStartDsgnStm;
        if(ForStartDsgnStm!=null) ForStartDsgnStm.setParent(this);
        this.ForCondStart=ForCondStart;
        if(ForCondStart!=null) ForCondStart.setParent(this);
        this.ForCond=ForCond;
        if(ForCond!=null) ForCond.setParent(this);
        this.ForCondEnd=ForCondEnd;
        if(ForCondEnd!=null) ForCondEnd.setParent(this);
        this.ForEndDsgnStm=ForEndDsgnStm;
        if(ForEndDsgnStm!=null) ForEndDsgnStm.setParent(this);
        this.ForBodyStart=ForBodyStart;
        if(ForBodyStart!=null) ForBodyStart.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ForStart getForStart() {
        return ForStart;
    }

    public void setForStart(ForStart ForStart) {
        this.ForStart=ForStart;
    }

    public ForStartDsgnStm getForStartDsgnStm() {
        return ForStartDsgnStm;
    }

    public void setForStartDsgnStm(ForStartDsgnStm ForStartDsgnStm) {
        this.ForStartDsgnStm=ForStartDsgnStm;
    }

    public ForCondStart getForCondStart() {
        return ForCondStart;
    }

    public void setForCondStart(ForCondStart ForCondStart) {
        this.ForCondStart=ForCondStart;
    }

    public ForCond getForCond() {
        return ForCond;
    }

    public void setForCond(ForCond ForCond) {
        this.ForCond=ForCond;
    }

    public ForCondEnd getForCondEnd() {
        return ForCondEnd;
    }

    public void setForCondEnd(ForCondEnd ForCondEnd) {
        this.ForCondEnd=ForCondEnd;
    }

    public ForEndDsgnStm getForEndDsgnStm() {
        return ForEndDsgnStm;
    }

    public void setForEndDsgnStm(ForEndDsgnStm ForEndDsgnStm) {
        this.ForEndDsgnStm=ForEndDsgnStm;
    }

    public ForBodyStart getForBodyStart() {
        return ForBodyStart;
    }

    public void setForBodyStart(ForBodyStart ForBodyStart) {
        this.ForBodyStart=ForBodyStart;
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
        if(ForStart!=null) ForStart.accept(visitor);
        if(ForStartDsgnStm!=null) ForStartDsgnStm.accept(visitor);
        if(ForCondStart!=null) ForCondStart.accept(visitor);
        if(ForCond!=null) ForCond.accept(visitor);
        if(ForCondEnd!=null) ForCondEnd.accept(visitor);
        if(ForEndDsgnStm!=null) ForEndDsgnStm.accept(visitor);
        if(ForBodyStart!=null) ForBodyStart.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForStart!=null) ForStart.traverseTopDown(visitor);
        if(ForStartDsgnStm!=null) ForStartDsgnStm.traverseTopDown(visitor);
        if(ForCondStart!=null) ForCondStart.traverseTopDown(visitor);
        if(ForCond!=null) ForCond.traverseTopDown(visitor);
        if(ForCondEnd!=null) ForCondEnd.traverseTopDown(visitor);
        if(ForEndDsgnStm!=null) ForEndDsgnStm.traverseTopDown(visitor);
        if(ForBodyStart!=null) ForBodyStart.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForStart!=null) ForStart.traverseBottomUp(visitor);
        if(ForStartDsgnStm!=null) ForStartDsgnStm.traverseBottomUp(visitor);
        if(ForCondStart!=null) ForCondStart.traverseBottomUp(visitor);
        if(ForCond!=null) ForCond.traverseBottomUp(visitor);
        if(ForCondEnd!=null) ForCondEnd.traverseBottomUp(visitor);
        if(ForEndDsgnStm!=null) ForEndDsgnStm.traverseBottomUp(visitor);
        if(ForBodyStart!=null) ForBodyStart.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStatement(\n");

        if(ForStart!=null)
            buffer.append(ForStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForStartDsgnStm!=null)
            buffer.append(ForStartDsgnStm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForCondStart!=null)
            buffer.append(ForCondStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForCond!=null)
            buffer.append(ForCond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForCondEnd!=null)
            buffer.append(ForCondEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForEndDsgnStm!=null)
            buffer.append(ForEndDsgnStm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForBodyStart!=null)
            buffer.append(ForBodyStart.toString("  "+tab));
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
