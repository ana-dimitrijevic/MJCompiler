// generated with ast extension for cup
// version 0.8
// 29/11/2019 13:18:33


package rs.ac.bg.etf.pp1.ast;

public class ProcCall extends Statement {

    private ProcCallStart ProcCallStart;
    private ActualParamsList ActualParamsList;

    public ProcCall (ProcCallStart ProcCallStart, ActualParamsList ActualParamsList) {
        this.ProcCallStart=ProcCallStart;
        if(ProcCallStart!=null) ProcCallStart.setParent(this);
        this.ActualParamsList=ActualParamsList;
        if(ActualParamsList!=null) ActualParamsList.setParent(this);
    }

    public ProcCallStart getProcCallStart() {
        return ProcCallStart;
    }

    public void setProcCallStart(ProcCallStart ProcCallStart) {
        this.ProcCallStart=ProcCallStart;
    }

    public ActualParamsList getActualParamsList() {
        return ActualParamsList;
    }

    public void setActualParamsList(ActualParamsList ActualParamsList) {
        this.ActualParamsList=ActualParamsList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ProcCallStart!=null) ProcCallStart.accept(visitor);
        if(ActualParamsList!=null) ActualParamsList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProcCallStart!=null) ProcCallStart.traverseTopDown(visitor);
        if(ActualParamsList!=null) ActualParamsList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProcCallStart!=null) ProcCallStart.traverseBottomUp(visitor);
        if(ActualParamsList!=null) ActualParamsList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProcCall(\n");

        if(ProcCallStart!=null)
            buffer.append(ProcCallStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActualParamsList!=null)
            buffer.append(ActualParamsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProcCall]");
        return buffer.toString();
    }
}
