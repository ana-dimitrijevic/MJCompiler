// generated with ast extension for cup
// version 0.8
// 29/0/2020 11:12:31


package rs.ac.bg.etf.pp1.ast;

public class NumConstInitializer extends Initializer {

    private String name;
    private Integer val;

    public NumConstInitializer (String name, Integer val) {
        this.name=name;
        this.val=val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
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
        buffer.append("NumConstInitializer(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NumConstInitializer]");
        return buffer.toString();
    }
}
