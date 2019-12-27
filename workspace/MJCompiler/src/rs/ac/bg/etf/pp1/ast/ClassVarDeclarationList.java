// generated with ast extension for cup
// version 0.8
// 27/11/2019 17:8:17


package rs.ac.bg.etf.pp1.ast;

public class ClassVarDeclarationList extends ClassVarDeclList {

    private ClassVarDeclList ClassVarDeclList;
    private VarDeclar VarDeclar;

    public ClassVarDeclarationList (ClassVarDeclList ClassVarDeclList, VarDeclar VarDeclar) {
        this.ClassVarDeclList=ClassVarDeclList;
        if(ClassVarDeclList!=null) ClassVarDeclList.setParent(this);
        this.VarDeclar=VarDeclar;
        if(VarDeclar!=null) VarDeclar.setParent(this);
    }

    public ClassVarDeclList getClassVarDeclList() {
        return ClassVarDeclList;
    }

    public void setClassVarDeclList(ClassVarDeclList ClassVarDeclList) {
        this.ClassVarDeclList=ClassVarDeclList;
    }

    public VarDeclar getVarDeclar() {
        return VarDeclar;
    }

    public void setVarDeclar(VarDeclar VarDeclar) {
        this.VarDeclar=VarDeclar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassVarDeclList!=null) ClassVarDeclList.accept(visitor);
        if(VarDeclar!=null) VarDeclar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassVarDeclList!=null) ClassVarDeclList.traverseTopDown(visitor);
        if(VarDeclar!=null) VarDeclar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassVarDeclList!=null) ClassVarDeclList.traverseBottomUp(visitor);
        if(VarDeclar!=null) VarDeclar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassVarDeclarationList(\n");

        if(ClassVarDeclList!=null)
            buffer.append(ClassVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclar!=null)
            buffer.append(VarDeclar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassVarDeclarationList]");
        return buffer.toString();
    }
}
