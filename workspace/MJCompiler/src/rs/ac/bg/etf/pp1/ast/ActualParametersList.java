// generated with ast extension for cup
// version 0.8
// 29/0/2020 11:12:31


package rs.ac.bg.etf.pp1.ast;

public class ActualParametersList extends ActualParamsList {

    private ActualParams ActualParams;

    public ActualParametersList (ActualParams ActualParams) {
        this.ActualParams=ActualParams;
        if(ActualParams!=null) ActualParams.setParent(this);
    }

    public ActualParams getActualParams() {
        return ActualParams;
    }

    public void setActualParams(ActualParams ActualParams) {
        this.ActualParams=ActualParams;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActualParams!=null) ActualParams.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActualParams!=null) ActualParams.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActualParams!=null) ActualParams.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActualParametersList(\n");

        if(ActualParams!=null)
            buffer.append(ActualParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActualParametersList]");
        return buffer.toString();
    }
}
