// generated with ast extension for cup
// version 0.8
// 11/11/2019 12:27:32


package rs.ac.bg.etf.pp1.ast;

public class InitializerList extends InitList {

    private InitList InitList;
    private Init Init;

    public InitializerList (InitList InitList, Init Init) {
        this.InitList=InitList;
        if(InitList!=null) InitList.setParent(this);
        this.Init=Init;
        if(Init!=null) Init.setParent(this);
    }

    public InitList getInitList() {
        return InitList;
    }

    public void setInitList(InitList InitList) {
        this.InitList=InitList;
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
        if(InitList!=null) InitList.accept(visitor);
        if(Init!=null) Init.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(InitList!=null) InitList.traverseTopDown(visitor);
        if(Init!=null) Init.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(InitList!=null) InitList.traverseBottomUp(visitor);
        if(Init!=null) Init.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("InitializerList(\n");

        if(InitList!=null)
            buffer.append(InitList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Init!=null)
            buffer.append(Init.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [InitializerList]");
        return buffer.toString();
    }
}
