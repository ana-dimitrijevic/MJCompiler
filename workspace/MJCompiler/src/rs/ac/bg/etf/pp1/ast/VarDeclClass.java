// generated with ast extension for cup
// version 0.8
// 20/0/2020 19:20:46


package rs.ac.bg.etf.pp1.ast;

public class VarDeclClass extends VarDeclar {

    private AbstractModif AbstractModif;
    private String I2;
    private ClassExtends ClassExtends;
    private ClassVarDeclList ClassVarDeclList;
    private ClassMethDeclList ClassMethDeclList;

    public VarDeclClass (AbstractModif AbstractModif, String I2, ClassExtends ClassExtends, ClassVarDeclList ClassVarDeclList, ClassMethDeclList ClassMethDeclList) {
        this.AbstractModif=AbstractModif;
        if(AbstractModif!=null) AbstractModif.setParent(this);
        this.I2=I2;
        this.ClassExtends=ClassExtends;
        if(ClassExtends!=null) ClassExtends.setParent(this);
        this.ClassVarDeclList=ClassVarDeclList;
        if(ClassVarDeclList!=null) ClassVarDeclList.setParent(this);
        this.ClassMethDeclList=ClassMethDeclList;
        if(ClassMethDeclList!=null) ClassMethDeclList.setParent(this);
    }

    public AbstractModif getAbstractModif() {
        return AbstractModif;
    }

    public void setAbstractModif(AbstractModif AbstractModif) {
        this.AbstractModif=AbstractModif;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
    }

    public ClassExtends getClassExtends() {
        return ClassExtends;
    }

    public void setClassExtends(ClassExtends ClassExtends) {
        this.ClassExtends=ClassExtends;
    }

    public ClassVarDeclList getClassVarDeclList() {
        return ClassVarDeclList;
    }

    public void setClassVarDeclList(ClassVarDeclList ClassVarDeclList) {
        this.ClassVarDeclList=ClassVarDeclList;
    }

    public ClassMethDeclList getClassMethDeclList() {
        return ClassMethDeclList;
    }

    public void setClassMethDeclList(ClassMethDeclList ClassMethDeclList) {
        this.ClassMethDeclList=ClassMethDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AbstractModif!=null) AbstractModif.accept(visitor);
        if(ClassExtends!=null) ClassExtends.accept(visitor);
        if(ClassVarDeclList!=null) ClassVarDeclList.accept(visitor);
        if(ClassMethDeclList!=null) ClassMethDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AbstractModif!=null) AbstractModif.traverseTopDown(visitor);
        if(ClassExtends!=null) ClassExtends.traverseTopDown(visitor);
        if(ClassVarDeclList!=null) ClassVarDeclList.traverseTopDown(visitor);
        if(ClassMethDeclList!=null) ClassMethDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AbstractModif!=null) AbstractModif.traverseBottomUp(visitor);
        if(ClassExtends!=null) ClassExtends.traverseBottomUp(visitor);
        if(ClassVarDeclList!=null) ClassVarDeclList.traverseBottomUp(visitor);
        if(ClassMethDeclList!=null) ClassMethDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclClass(\n");

        if(AbstractModif!=null)
            buffer.append(AbstractModif.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+I2);
        buffer.append("\n");

        if(ClassExtends!=null)
            buffer.append(ClassExtends.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassVarDeclList!=null)
            buffer.append(ClassVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassMethDeclList!=null)
            buffer.append(ClassMethDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclClass]");
        return buffer.toString();
    }
}
