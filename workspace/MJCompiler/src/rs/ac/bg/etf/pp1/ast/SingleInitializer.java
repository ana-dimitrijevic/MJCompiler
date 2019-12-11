// generated with ast extension for cup
// version 0.8
// 11/11/2019 13:54:36


package rs.ac.bg.etf.pp1.ast;

public class SingleInitializer extends InitList {

    private Init Init;

    public SingleInitializer (Init Init) {
        this.Init=Init;
        if(Init!=null) Init.setParent(this);
    }

    public Init getInit() {
        return Init;
    }

    public void setInit(Init Init) {
        this.Init=Init;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Init!=null) Init.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Init!=null) Init.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Init!=null) Init.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleInitializer(\n");

        if(Init!=null)
            buffer.append(Init.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleInitializer]");
        return buffer.toString();
    }
}
