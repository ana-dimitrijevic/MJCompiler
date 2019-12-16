// generated with ast extension for cup
// version 0.8
// 16/11/2019 12:47:7


package rs.ac.bg.etf.pp1.ast;

public class DesignatorListDot extends DesignatorList {

    private DesignatorList DesignatorList;
    private String designatorId;

    public DesignatorListDot (DesignatorList DesignatorList, String designatorId) {
        this.DesignatorList=DesignatorList;
        if(DesignatorList!=null) DesignatorList.setParent(this);
        this.designatorId=designatorId;
    }

    public DesignatorList getDesignatorList() {
        return DesignatorList;
    }

    public void setDesignatorList(DesignatorList DesignatorList) {
        this.DesignatorList=DesignatorList;
    }

    public String getDesignatorId() {
        return designatorId;
    }

    public void setDesignatorId(String designatorId) {
        this.designatorId=designatorId;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorList!=null) DesignatorList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorList!=null) DesignatorList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorList!=null) DesignatorList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorListDot(\n");

        if(DesignatorList!=null)
            buffer.append(DesignatorList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+designatorId);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorListDot]");
        return buffer.toString();
    }
}
