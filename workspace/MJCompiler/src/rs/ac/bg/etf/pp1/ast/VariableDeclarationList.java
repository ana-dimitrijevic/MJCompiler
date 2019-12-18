// generated with ast extension for cup
// version 0.8
// 18/11/2019 19:25:13


package rs.ac.bg.etf.pp1.ast;

public class VariableDeclarationList extends DeclVarList {

    private DeclVarList DeclVarList;
    private DeclVar DeclVar;

    public VariableDeclarationList (DeclVarList DeclVarList, DeclVar DeclVar) {
        this.DeclVarList=DeclVarList;
        if(DeclVarList!=null) DeclVarList.setParent(this);
        this.DeclVar=DeclVar;
        if(DeclVar!=null) DeclVar.setParent(this);
    }

    public DeclVarList getDeclVarList() {
        return DeclVarList;
    }

    public void setDeclVarList(DeclVarList DeclVarList) {
        this.DeclVarList=DeclVarList;
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
        if(DeclVarList!=null) DeclVarList.accept(visitor);
        if(DeclVar!=null) DeclVar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DeclVarList!=null) DeclVarList.traverseTopDown(visitor);
        if(DeclVar!=null) DeclVar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DeclVarList!=null) DeclVarList.traverseBottomUp(visitor);
        if(DeclVar!=null) DeclVar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VariableDeclarationList(\n");

        if(DeclVarList!=null)
            buffer.append(DeclVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DeclVar!=null)
            buffer.append(DeclVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VariableDeclarationList]");
        return buffer.toString();
    }
}
