// generated with ast extension for cup
// version 0.8
// 28/11/2019 13:6:8


package rs.ac.bg.etf.pp1.ast;

public class NoActualParametersList extends ActualParamsList {

    public NoActualParametersList () {
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
        buffer.append("NoActualParametersList(\n");

        buffer.append(tab);
        buffer.append(") [NoActualParametersList]");
        return buffer.toString();
    }
}
