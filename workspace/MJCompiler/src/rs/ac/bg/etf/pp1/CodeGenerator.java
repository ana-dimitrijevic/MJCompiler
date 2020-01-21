package rs.ac.bg.etf.pp1;


import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor {
	
	
	private int mainPc;
	
	public int getMainPc() {
		return mainPc;
	}
	
	@Override
	public void visit(MethodTypeName MethodTypeName) {
		if ("main".equalsIgnoreCase(MethodTypeName.getMethName())) {
			mainPc = Code.pc;
		}
		MethodTypeName.obj.setAdr(Code.pc);
		
		// Collect arguments and local variables.

		// Generate the entry.
		Code.put(Code.enter);
		Code.put(MethodTypeName.obj.getLevel());
		Code.put(MethodTypeName.obj.getLocalSymbols().size());
		
		System.out.println("level :" + MethodTypeName.obj.getLevel());
		System.out.println("local symbols size :" + MethodTypeName.obj.getLocalSymbols().size());
	}
	
	@Override
	public void visit(MethodDeclaration MethodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	
	@Override
	public void visit(ReturnType ReturnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(VoidReturnType ReturnNoExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(AssignStatement assignStatement) {
		Code.store(assignStatement.getDesignator().obj);
	}
	
	@Override
	public void visit(NumberConstFactor Const) {
		Code.load(new Obj(Obj.Con, "$", Const.struct, Const.getVal(), 0));
	}
	
	@Override
	public void visit(CharConstFactor Const) {
		Code.load(new Obj(Obj.Con, "$", Const.struct, Const.getVal(), 0));
	}
	
	@Override
	public void visit(BoolConstFactor Const) {
		if (Const.getVal()==true)
			Code.load(new Obj(Obj.Con, "$", Const.struct, 1, 0));
		else 
			Code.load(new Obj(Obj.Con, "$", Const.struct, 0, 0));
	}
	


	@Override
	public void visit(SimpleDesignator simpleDesignator) {
		SyntaxNode parent = simpleDesignator.getParent();
		if (AssignStatement.class != parent.getClass() && FuncCall.class != parent.getClass()) {
			Code.load(simpleDesignator.obj);
		}
	}
	
	
	@Override
	public void visit(FuncCall FuncCall) {
		Obj functionObj = FuncCall.getFuncCallStart().getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc; 
		Code.put(Code.call);
		Code.put2(offset);
	}

	@Override
	public void visit(PrintStatement printStatement) {
		Code.put(Code.const_5);
		Code.put(Code.print);
	}
	
	@Override
	public void visit(MultipleAddopTerm multipleAddopTerm) {
		Code.put(Code.add);
	}
}
