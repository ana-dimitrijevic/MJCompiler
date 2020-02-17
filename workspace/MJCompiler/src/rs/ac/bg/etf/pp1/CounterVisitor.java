package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;

public class CounterVisitor extends VisitorAdaptor {
	
	protected int varCount, countConst, countClass, countFuncCalls, countMethods, countStatements;
	
	
	
	public CounterVisitor() {
		super();
		 countConst = 0;
		 countClass = 0;
		 countFuncCalls = 0;
		 countMethods = 0;
		 countStatements = 0;
		 varCount = 0;
	}

	
	public int getCountConst() {
		return countConst;
	}


	public void setCountConst(int countConst) {
		this.countConst = countConst;
	}


	public int getCountClass() {
		return countClass;
	}


	public void setCountClass(int countClass) {
		this.countClass = countClass;
	}

	public int getCountFuncCalls() {
		return countFuncCalls;
	}


	public void setCountFuncCalls(int countFuncCalls) {
		this.countFuncCalls = countFuncCalls;
	}


	public int getCountMethods() {
		return countMethods;
	}


	public void setCountMethods(int countMethods) {
		this.countMethods = countMethods;
	}


	public int getCountStatements() {
		return countStatements;
	}


	public void setCountStatements(int countStatements) {
		this.countStatements = countStatements;
	}

	@Override
	public void visit(ConstInitializerList varDeclConst) {
		countConst++;
	}

	@Override
	public void visit(VarDeclConst varDeclConst) {
		countConst++;
	}
	
	@Override
	public void visit(VarDeclClass varDeclClass) {
		countClass++;
	}
	
	@Override
	public void visit(MethodDeclaration methodDeclaration) {
		countMethods++;
	}
	
	@Override
	public void visit(ReturnStatement statement) {
		countStatements++;
	}
	
	@Override
	public void visit(BreakStatement statement) {
		countStatements++;
	}
	@Override
	public void visit(ContinueStatement statement) {
		countStatements++;
	}
	@Override
	public void visit(ReturnExpressionStatement statement) {
		countStatements++;
	}
	@Override
	public void visit(ReadStatement statement) {
		countStatements++;
	}
	@Override
	public void visit(PrintStatement statement) {
		countStatements++;
	}
	@Override
	public void visit(IfStatement statement) {
		countStatements++;
	}
	
	@Override
	public void visit(ForStatement statement) {
		countStatements++;
	}
	
	@Override
	public void visit(StatementDesignator statement) {
		countStatements++;
	}
	
	@Override
	public void visit(BlockOfStatements statement) {
		countStatements++;
	}

	@Override
	public void visit(ProcCall procCall) {
		countStatements++;
		countFuncCalls++;
	}
		
	@Override
	public void visit(FuncCall funcCall) {
		countFuncCalls++;
	}

	@Override
	public void visit(SingleVariableIdent singleVariableIdent) {
		SyntaxNode parent =  singleVariableIdent.getParent();
		while(!(parent instanceof DeclVar)) {
			if(parent != null) parent = parent.getParent(); else return;
		}
		if(parent.getParent() instanceof VarDeclar) varCount++;
	}
	
	@Override
	public void visit(ArrayVariableIdent singleVariableIdent) {
		SyntaxNode parent =  singleVariableIdent.getParent();
		while(!(parent instanceof DeclVar)) {
			if(parent != null) parent = parent.getParent(); else return;
		}
		if(parent.getParent() instanceof VarDeclar) varCount++;
	}
}
