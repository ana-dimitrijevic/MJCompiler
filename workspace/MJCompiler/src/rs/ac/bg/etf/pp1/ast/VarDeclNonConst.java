// generated with ast extension for cup
// version 0.8
// 20/11/2019 23:45:29


package rs.ac.bg.etf.pp1.ast;

public class VarDeclNonConst extends VarDeclar {

    private DeclVar DeclVar;

    public VarDeclNonConst (DeclVar DeclVar) {
        this.DeclVar=DeclVar;
        if(DeclVar!=null) DeclVar.setParent(this);
    }

    public DeclVar getDeclVar() {
        return DeclVar;
    }

    public void setDeclVar(DeclVar DeclVar) {
        this.DeclVar=DeclVar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DeclVar!=null) DeclVar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DeclVar!=null) DeclVar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DeclVar!=null) DeclVar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclNonConst(\n");

        if(DeclVar!=null)
            buffer.append(DeclVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclNonConst]");
        return buffer.toString();
    }
}
