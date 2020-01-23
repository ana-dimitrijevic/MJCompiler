// generated with ast extension for cup
// version 0.8
// 23/0/2020 17:29:8


package rs.ac.bg.etf.pp1.ast;

public class VarDeclConst extends VarDeclar {

    private Type Type;
    private ConstInitializer ConstInitializer;

    public VarDeclConst (Type Type, ConstInitializer ConstInitializer) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ConstInitializer=ConstInitializer;
        if(ConstInitializer!=null) ConstInitializer.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ConstInitializer getConstInitializer() {
        return ConstInitializer;
    }

    public void setConstInitializer(ConstInitializer ConstInitializer) {
        this.ConstInitializer=ConstInitializer;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ConstInitializer!=null) ConstInitializer.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ConstInitializer!=null) ConstInitializer.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ConstInitializer!=null) ConstInitializer.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclConst(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstInitializer!=null)
            buffer.append(ConstInitializer.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclConst]");
        return buffer.toString();
    }
}
