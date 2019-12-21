// generated with ast extension for cup
// version 0.8
// 21/11/2019 22:22:22


package rs.ac.bg.etf.pp1.ast;

public class SingleConstInitializer extends ConstInitializer {

    private Initializer Initializer;

    public SingleConstInitializer (Initializer Initializer) {
        this.Initializer=Initializer;
        if(Initializer!=null) Initializer.setParent(this);
    }

    public Initializer getInitializer() {
        return Initializer;
    }

    public void setInitializer(Initializer Initializer) {
        this.Initializer=Initializer;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Initializer!=null) Initializer.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Initializer!=null) Initializer.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Initializer!=null) Initializer.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleConstInitializer(\n");

        if(Initializer!=null)
            buffer.append(Initializer.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleConstInitializer]");
        return buffer.toString();
    }
}
