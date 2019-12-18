// generated with ast extension for cup
// version 0.8
// 18/11/2019 19:25:13


package rs.ac.bg.etf.pp1.ast;

public class NoAbstractModifer extends AbstractModif {

    public NoAbstractModifer () {
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
        buffer.append("NoAbstractModifer(\n");

        buffer.append(tab);
        buffer.append(") [NoAbstractModifer]");
        return buffer.toString();
    }
}
