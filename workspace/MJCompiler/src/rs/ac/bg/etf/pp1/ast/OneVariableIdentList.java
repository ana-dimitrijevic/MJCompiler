// generated with ast extension for cup
// version 0.8
// 11/11/2019 19:32:20


package rs.ac.bg.etf.pp1.ast;

public class OneVariableIdentList extends VarIdentList {

    private VarIdent VarIdent;

    public OneVariableIdentList (VarIdent VarIdent) {
        this.VarIdent=VarIdent;
        if(VarIdent!=null) VarIdent.setParent(this);
    }

    public VarIdent getVarIdent() {
        return VarIdent;
    }

    public void setVarIdent(VarIdent VarIdent) {
        this.VarIdent=VarIdent;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarIdent!=null) VarIdent.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarIdent!=null) VarIdent.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarIdent!=null) VarIdent.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OneVariableIdentList(\n");

        if(VarIdent!=null)
            buffer.append(VarIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneVariableIdentList]");
        return buffer.toString();
    }
}
