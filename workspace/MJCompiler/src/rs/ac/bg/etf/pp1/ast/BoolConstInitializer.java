// generated with ast extension for cup
// version 0.8
// 28/11/2019 22:21:52


package rs.ac.bg.etf.pp1.ast;

public class BoolConstInitializer extends Initializer {

    private String name;
    private Boolean val;

    public BoolConstInitializer (String name, Boolean val) {
        this.name=name;
        this.val=val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public Boolean getVal() {
        return val;
    }

    public void setVal(Boolean val) {
        this.val=val;
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
        buffer.append("BoolConstInitializer(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BoolConstInitializer]");
        return buffer.toString();
    }
}
