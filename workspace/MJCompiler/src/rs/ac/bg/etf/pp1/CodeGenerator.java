package rs.ac.bg.etf.pp1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor {

	private Stack<Queue<Integer>> falseBackpatchingStack = new Stack<>();
	private Stack<Queue<Integer>> trueBackpatchingStack = new Stack<>();
	private Stack<Integer> skipElseBackpatchingStack = new Stack<>();
	private Stack<Queue<Integer>> breakForBackpatchingStack = new Stack<>();
	private Stack<Integer> designatorStatementStartAddresses = new Stack<>();
	private int mainPc, relopCode = -1, conditionCheckForAddress = -1;

	public int getMainPc() {
		return mainPc;
	}

	public int getRelOP(Relop relop) {
		if (relop instanceof RelopEQ) 		  { return Code.eq; }
		else if (relop instanceof RelopNOTEQ) { return Code.ne; }
		else if (relop instanceof RelopGR) 	  {	return Code.gt; }
		else if (relop instanceof RelopGEQ)   { return Code.ge; }
		else if (relop instanceof RelopLS) 	  { return Code.lt; }
		else if (relop instanceof RelopLEQ)   { return Code.le;	}
		return Code.eq;
	}

	@Override
	public void visit(ProgName progName) {
		Tab.find("ord").setAdr(Code.pc);
		Code.put(Code.return_);
		Tab.find("chr").setAdr(Code.pc);
		Code.put(Code.return_);
		Tab.find("len").setAdr(Code.pc);
		Code.put(Code.arraylength);
		Code.put(Code.return_);
	}

	@Override
	public void visit(MethodTypeName methodTypeName) {
		if ("main".equalsIgnoreCase(methodTypeName.getMethName())) {
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(methodTypeName.obj.getLevel());
		Code.put(methodTypeName.obj.getLocalSymbols().size());
	}

	@Override
	public void visit(MethodDeclaration methodDeclaration) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	@Override
	public void visit(ReturnType returnType) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	@Override
	public void visit(VoidReturnType voidReturnType) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	@Override
	public void visit(AssignStatement assignStatement) {
		Code.store(assignStatement.getDesignator().obj);
	}

	@Override
	public void visit(NumberConstFactor numberConstFactor) {
		Code.load(new Obj(Obj.Con, "$", numberConstFactor.struct, numberConstFactor.getVal(), 0));
	}

	@Override
	public void visit(CharConstFactor charConstFactor) {
		Code.load(new Obj(Obj.Con, "$", charConstFactor.struct, charConstFactor.getVal(), 0));
	}

	@Override
	public void visit(BoolConstFactor boolConstFactor) {
		if (boolConstFactor.getVal() == true)
			Code.load(new Obj(Obj.Con, "$", boolConstFactor.struct, 1, 0));
		else
			Code.load(new Obj(Obj.Con, "$", boolConstFactor.struct, 0, 0));
	}

	@Override
	public void visit(DesignatorName designatorName) {
		if (designatorName.getParent().getClass() == ArrayDesignator.class) {
			Code.load(designatorName.obj);
		}
	}

	@Override
	public void visit(SimpleDesignator simpleDesignator) {
		// Code.put(Code.pop);
		if (AssignStatement.class != simpleDesignator.getParent().getClass()
				&& FuncCallStart.class != simpleDesignator.getParent().getClass()
				&& ReadStatement.class != simpleDesignator.getParent().getClass()
				&& ProcCallStart.class != simpleDesignator.getParent().getClass()) {
			Code.load(simpleDesignator.obj);
		}
	}

	@Override
	public void visit(ArrayDesignator arrayDesignator) {
		// Code.put(Code.pop);
		if (IncDesignatorStatement.class == arrayDesignator.getParent().getClass()
				|| DecDesignatorStatement.class == arrayDesignator.getParent().getClass()) {
			Code.put(Code.dup2);
		}
		if (AssignStatement.class != arrayDesignator.getParent().getClass()
				&& FuncCallStart.class != arrayDesignator.getParent().getClass()
				&& ReadStatement.class != arrayDesignator.getParent().getClass()
				&& ProcCallStart.class != arrayDesignator.getParent().getClass()) {
			Code.load(arrayDesignator.obj);
		}
	}

	@Override
	public void visit(FuncCall funcCall) {
		int offs = funcCall.getFuncCallStart().getDesignator().obj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offs);
	}
	
	@Override
	public void visit(ProcCall procCall) {
		int offs = procCall.getProcCallStart().getDesignator().obj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offs);
	}
	
	@Override
	public void visit(StatementsList statementList) {
		if (statementList.getStatement() instanceof ProcCall) {
			ProcCall procCall = (ProcCall) statementList.getStatement();
			if (procCall.getProcCallStart().getDesignator().obj.getType() != Tab.noType) {
	            Code.put(Code.pop);
	    }
	}
	}

	@Override
	public void visit(ReadStatement readStatement) {
		if (readStatement.getDesignator().obj.getType() == Tab.charType)
			Code.put(Code.bread);
		else
			Code.put(Code.read);
		Code.store(readStatement.getDesignator().obj);
	}

	@Override
	public void visit(MultipleAddopTerm multipleAddopTerm) {
		if (multipleAddopTerm.getAddop() instanceof AddopADD)
			Code.put(Code.add);
		else
			Code.put(Code.sub);
	}

	@Override
	public void visit(MultipleFactor multipleFactor) {
		if (multipleFactor.getMulop() instanceof MulopMUL)
			Code.put(Code.mul);
		else if (multipleFactor.getMulop() instanceof MulopDIV)
			Code.put(Code.div);
		else
			Code.put(Code.rem);
	}

	@Override
	public void visit(NewObjectArrayFactor newObjectArrayFactor) {
		Code.put(Code.newarray);
		if (newObjectArrayFactor.getType().struct == Tab.charType)
			Code.put(0);
		else
			Code.put(1);
	}

	@Override
	public void visit(NegativeExpression negativeExpression) {
		Code.put(Code.neg);
	}

	public void visit(PrintStatement printStatement) {
		if (printStatement.getPrintOptVal() instanceof PrintOptionalVal) {
			PrintOptionalVal val = (PrintOptionalVal) printStatement.getPrintOptVal();
			Code.loadConst(val.getVal());
		} 
		else
			Code.put(Code.const_1);
		Obj boolType = Tab.find("bool");
		if (printStatement.getExpr().struct == Tab.intType 
			|| printStatement.getExpr().struct == boolType.getType()) {
			Code.put(Code.print);
		} 
		else if (printStatement.getExpr().struct == Tab.charType) {
			Code.put(Code.bprint);
		}
	}

	@Override
	public void visit(IncDesignatorStatement incDesignatorStatement) {
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(incDesignatorStatement.getDesignator().obj);
	}

	@Override
	public void visit(DecDesignatorStatement decDesignatorStatement) {
		Code.loadConst(-1);
		Code.put(Code.add);
		Code.store(decDesignatorStatement.getDesignator().obj);
	}

	public void visit(Or or) {
		Code.putJump(0);
		int addrToPatch = Code.pc - 2;
		trueBackpatchingStack.peek().add(addrToPatch);
		Queue<Integer> falseIfBackpatchingStackQueue = falseBackpatchingStack.peek();
		while (!falseIfBackpatchingStackQueue.isEmpty()) {
			Code.fixup(falseIfBackpatchingStackQueue.remove());
		}
	}

	public void visit(RelopExprConditionFactor relopExprConditionFactor) {
		Relop relop = relopExprConditionFactor.getRelop();
		relopCode = getRelOP(relop);
		Code.putFalseJump(relopCode, 0);
		int addrToPatch = Code.pc - 2;
		falseBackpatchingStack.peek().add(addrToPatch);
	}

	public void visit(ExprConditionFactor exprConditionFactor) {
		relopCode = Code.eq;
		Code.loadConst(1);
		Code.putFalseJump(relopCode, 0);
		int addrToPatch = Code.pc - 2;
		falseBackpatchingStack.peek().add(addrToPatch);
	}

	public void visit(IfStart ifStart) {
		falseBackpatchingStack.push(new LinkedList<Integer>());
		trueBackpatchingStack.push(new LinkedList<Integer>());
	}

	public void visit(IfStatement ifStatement) {
		falseBackpatchingStack.pop();
		trueBackpatchingStack.pop();
	}

	public void visit(Rparen rparen) {
		Queue<Integer> trueIfBackpatchingStackQueue = trueBackpatchingStack.peek();
		while (!trueIfBackpatchingStackQueue.isEmpty()) {
			Code.fixup(trueIfBackpatchingStackQueue.remove());
		}
	}

	public void visit(Else elseStart) {
		Code.putJump(0);
		int addrToPatch = Code.pc - 2;
		skipElseBackpatchingStack.add(addrToPatch);
		Queue<Integer> falseBackpatchingStackQueue = falseBackpatchingStack.peek();
		while (!falseBackpatchingStackQueue.isEmpty()) {
			Code.fixup(falseBackpatchingStackQueue.remove());
		}

	}

	public void visit(ElseOptional elseOptional) {
		Code.fixup(skipElseBackpatchingStack.pop());
		Queue<Integer> falseBackpatchingStackQueue = falseBackpatchingStack.peek();
		while (!falseBackpatchingStackQueue.isEmpty()) {
			Code.fixup(falseBackpatchingStackQueue.remove());
		}

	}

	public void visit(NoElseOptional noElseOptional) {
		Queue<Integer> falseBackpatchingStackQueue = falseBackpatchingStack.peek();
		while (!falseBackpatchingStackQueue.isEmpty()) {
			Code.fixup(falseBackpatchingStackQueue.remove());
		}
	}

	public void visit(ForStart forStart) {
		trueBackpatchingStack.push(new LinkedList<Integer>());
		falseBackpatchingStack.push(new LinkedList<Integer>());
		breakForBackpatchingStack.push(new LinkedList<Integer>());
		conditionCheckForAddress = -1;
	}

	public void visit(ForStatement forStatement) {
		int adr = designatorStatementStartAddresses.peek();
		Code.putJump(adr);
		Queue<Integer> falseBackpatchingStackQueue = falseBackpatchingStack.peek();
		while (!falseBackpatchingStackQueue.isEmpty()) {
			Code.fixup(falseBackpatchingStackQueue.remove());
		}
		Queue<Integer> breakForBackpatchingStackQueue = breakForBackpatchingStack.peek();
		while (!breakForBackpatchingStackQueue.isEmpty()) {
			Code.fixup(breakForBackpatchingStackQueue.remove());
		}
		breakForBackpatchingStack.pop();
		trueBackpatchingStack.pop();
		falseBackpatchingStack.pop();
		designatorStatementStartAddresses.pop();
	}

	public void visit(ForCondStart forCondStart) {
		conditionCheckForAddress = Code.pc;
	}

	public void visit(ForCondEnd forCondEnd) {
		Code.putJump(0);
		int addrToPatch = Code.pc - 2;
		trueBackpatchingStack.peek().add(addrToPatch);
		addrToPatch = Code.pc;
		designatorStatementStartAddresses.push(addrToPatch);

	}

	public void visit(ForBodyStart forBodyStart) {
		Code.putJump(conditionCheckForAddress);
		Queue<Integer> trueBackpatchingStackQueue = trueBackpatchingStack.peek();
		while (!trueBackpatchingStackQueue.isEmpty()) {
			Code.fixup(trueBackpatchingStackQueue.remove());
		}
	}

	public void visit(BreakStatement breakStatement) {
		Code.putJump(0);
		int addrToPatch = Code.pc - 2;
		breakForBackpatchingStack.peek().add(addrToPatch);
	}

	public void visit(ContinueStatement continueStatement) {
		Code.putJump(designatorStatementStartAddresses.peek());
	}

}
