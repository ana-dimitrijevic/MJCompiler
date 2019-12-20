// generated with ast extension for cup
// version 0.8
// 20/11/2019 23:45:29


package rs.ac.bg.etf.pp1.ast;

public class ConstInitializerList extends ConstInitializer {

    private ConstInitializer ConstInitializer;
    private Initializer Initializer;

    public ConstInitializerList (ConstInitializer ConstInitializer, Initializer Initializer) {
        this.ConstInitializer=ConstInitializer;
        if(ConstInitializer!=null) ConstInitializer.setParent(this);
        this.Initializer=Initializer;
        if(Initializer!=null) Initializer.setParent(this);
    }

    public ConstInitializer getConstInitializer() {
        return ConstInitializer;
    }

    public void setConstInitializer(ConstInitializer ConstInitializer) {
        this.ConstInitializer=ConstInitializer;
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
        if(ConstInitializer!=null) ConstInitializer.accept(visitor);
        if(Initializer!=null) Initializer.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstInitializer!=null) ConstInitializer.traverseTopDown(visitor);
        if(Initializer!=null) Initializer.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstInitializer!=null) ConstInitializer.traverseBottomUp(visitor);
        if(Initializer!=null) Initializer.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstInitializerList(\n");

        if(ConstInitializer!=null)
            buffer.append(ConstInitializer.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Initializer!=null)
            buffer.append(Initializer.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstInitializerList]");
        return buffer.toString();
    }
}
