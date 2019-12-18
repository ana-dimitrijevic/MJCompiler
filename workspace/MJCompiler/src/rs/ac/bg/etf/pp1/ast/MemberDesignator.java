// generated with ast extension for cup
// version 0.8
// 18/11/2019 19:25:13


package rs.ac.bg.etf.pp1.ast;

public class MemberDesignator extends Designator {

    private String name;
    private String memberName;

    public MemberDesignator (String name, String memberName) {
        this.name=name;
        this.memberName=memberName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName=memberName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MemberDesignator(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        buffer.append(" "+tab+memberName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MemberDesignator]");
        return buffer.toString();
    }
}
