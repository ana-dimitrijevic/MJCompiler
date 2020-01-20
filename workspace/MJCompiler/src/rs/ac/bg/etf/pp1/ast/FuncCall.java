// generated with ast extension for cup
// version 0.8
// 20/0/2020 19:20:46


package rs.ac.bg.etf.pp1.ast;

public class FuncCall extends Factor {

    private FuncCallStart FuncCallStart;
    private ActualParamsList ActualParamsList;

    public FuncCall (FuncCallStart FuncCallStart, ActualParamsList ActualParamsList) {
        this.FuncCallStart=FuncCallStart;
        if(FuncCallStart!=null) FuncCallStart.setParent(this);
        this.ActualParamsList=ActualParamsList;
        if(ActualParamsList!=null) ActualParamsList.setParent(this);
    }

    public FuncCallStart getFuncCallStart() {
        return FuncCallStart;
    }

    public void setFuncCallStart(FuncCallStart FuncCallStart) {
        this.FuncCallStart=FuncCallStart;
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
        if(FuncCallStart!=null) FuncCallStart.accept(visitor);
        if(ActualParamsList!=null) ActualParamsList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FuncCallStart!=null) FuncCallStart.traverseTopDown(visitor);
        if(ActualParamsList!=null) ActualParamsList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FuncCallStart!=null) FuncCallStart.traverseBottomUp(visitor);
        if(ActualParamsList!=null) ActualParamsList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FuncCall(\n");

        if(FuncCallStart!=null)
            buffer.append(FuncCallStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActualParamsList!=null)
            buffer.append(ActualParamsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FuncCall]");
        return buffer.toString();
    }
}
