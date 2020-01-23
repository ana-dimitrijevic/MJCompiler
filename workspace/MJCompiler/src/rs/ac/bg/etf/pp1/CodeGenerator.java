package rs.ac.bg.etf.pp1;


import java.util.ArrayDeque;
import java.util.Deque;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	
	private Deque<Integer> backpathcingStack = new ArrayDeque<>();
	private Deque<Integer> loopConditionAddressStack = new ArrayDeque<>();
    private Deque<Integer> loopTerminatorAddressStack = new ArrayDeque<>();
    private Deque<Integer> breakAddressStack = new ArrayDeque<>();
    private Deque<Integer> breakCountStack = new ArrayDeque<>();
	
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
    public void visit(DesignatorName ident) {
        Obj objNode = ident.obj;
        // load array pointer on the expression stack
       // if (objNode.getType().getKind() == Struct.Array) {
        if (ident.getParent().getClass() == ArrayDesignator.class) {
			Code.load(objNode);
        }
    }

	@Override
	public void visit(SimpleDesignator simpleDesignator) {	
		
		SyntaxNode parent = simpleDesignator.getParent();

		if (AssignStatement.class != parent.getClass() && FuncCallStart.class != parent.getClass() &&
                ReadStatement.class != parent.getClass() && ProcCallStart.class != parent.getClass()) {
			Code.load(simpleDesignator.obj);
		}
		
	}
	
	@Override
	public void visit(ArrayDesignator simpleDesignator) {
		SyntaxNode parent = simpleDesignator.getParent();
		
		// Code.put(Code.pop); ?? cica ima za sad mi nije problem mozda posle bude

		if (IncDesignatorStatement.class == parent.getClass() || DecDesignatorStatement.class == parent.getClass()) {
                Code.put(Code.dup2);            
        }

    
        if (AssignStatement.class != parent.getClass() && FuncCall.class != parent.getClass() &&
                ReadStatement.class != parent.getClass()) {
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
	    public void visit(ReadStatement readStatement) {
		 
	        if (readStatement.getDesignator().obj.getType() == Tab.charType) {
	            Code.put(Code.bread);
	        } else {
	            Code.put(Code.read);
	        }
	        Code.store(readStatement.getDesignator().obj);
	    }
	 
	
	@Override
	public void visit(MultipleAddopTerm multipleAddopTerm) {
		 if (multipleAddopTerm.getAddop() instanceof AddopADD) {
	       
	            Code.put(Code.add);
	        } else {
	           
	            Code.put(Code.sub);
	        }
	}
	
    

    @Override
    public void visit(MultipleFactor multipleFactor) {
        if (multipleFactor.getMulop() instanceof MulopMUL) {
            Code.put(Code.mul);
        } else if (multipleFactor.getMulop() instanceof MulopDIV) {
            Code.put(Code.div);
        } else {
            Code.put(Code.rem);
        }
    }
    
    
	
    @Override
    public void visit(NewObjectArrayFactor newOperator) {
        Code.put(Code.newarray);
        if (newOperator.getType().struct == Tab.charType) {
            Code.put(0);
        } else {
            Code.put(1);
        }
    }
	
    @Override
    public void visit(NegativeExpression negativeExpression) {
        Code.put(Code.neg);
    }

	public void visit(PrintStatement node) {
	    if(node.getPrintOptVal() instanceof PrintOptionalVal){
	    	PrintOptionalVal printOptNumFieldsNode = (PrintOptionalVal) node.getPrintOptVal();
			Code.loadConst(printOptNumFieldsNode.getVal());
        }
		else Code.put(Code.const_1);
	    
	    Obj boolType = Tab.find("bool");
		if(node.getExpr().struct == Tab.intType || node.getExpr().struct == boolType.getType()){
			Code.put(Code.print);
		}
		else if (node.getExpr().struct == Tab.charType){
			Code.put(Code.bprint);
		}
	}
			

  //print ocekuje na expr steku vrijednost i sirinu(width)
  	//Expr neterminal ce sam postaviti na expr stek svoju vrijednost
  	//mora se jos dodati i odgovarajuca sirina (width)
//  	public void visit(PrintStatement printStatement){
//  		
//  		//mora se umetnuti odgovarajuca konstanta, zavisno od toga da li je naveden opcioni izraz
//  		
//  		if(printStatement.getPrintOptVal() instanceof PrintOptionalVal){
//  			//navedena sirina(width)
//  			PrintOptionalVal yesPrintOptional = (PrintOptionalVal)printStatement.getPrintOptVal();
//  			
//  			int printWidth = yesPrintOptional.getVal();
//  			Obj constObj = new Obj(Obj.Con,"name",Tab.intType,printWidth,0);
//  			Code.load(constObj);
//  			
//  		}else{
//  			//nije navedena sirina, default 2
//  			//ako nije specificirana duzina, ide duzina 3 default
//  			Code.loadConst(3);
//  		}
//  		
//  		//ako nije ispis char-a, radi se print, inace bprint
//  		
//  		Struct exprType = printStatement.getExpr().struct;
//  		
//  		if(!(exprType.equals(Tab.charType))){
//  			//ne ispisuje se char
//  			Code.put(Code.print);
//  			return;
//  		}
//  		//ispisuje se char
//  		Code.put(Code.bprint);
//  	}
  	
  	 @Override
     public void visit(IncDesignatorStatement statement) {
         Code.loadConst(1);
         Code.put(Code.add);
         Code.store(statement.getDesignator().obj);
     }

     @Override
     public void visit(DecDesignatorStatement statement) {
         Code.loadConst(-1);
         Code.put(Code.add);
         Code.store(statement.getDesignator().obj);
     }
  	
     @Override
     public void visit(RelopExprConditionFactor relopExpr) {
         Relop relop = relopExpr.getRelop();
         backpathcingStack.addFirst(Code.pc + 1);
         if (relop instanceof RelopEQ) {
             Code.putFalseJump(Code.eq, 0);
         } else if (relop instanceof RelopNOTEQ) {
             Code.putFalseJump(Code.ne, 0);
         } else if (relop instanceof RelopGR) {
             Code.putFalseJump(Code.gt, 0);
         } else if (relop instanceof RelopGEQ) {
             Code.putFalseJump(Code.ge, 0);
         } else if (relop instanceof RelopLS) {
             Code.putFalseJump(Code.lt, 0);
         } else if (relop instanceof RelopLEQ) {
             Code.putFalseJump(Code.le, 0);
         }
         Code.loadConst(1);
         int temp = backpathcingStack.removeFirst();
         backpathcingStack.addFirst(Code.pc + 1);
         Code.putJump(0);
         Code.fixup(temp);
         Code.loadConst(0);
         Code.fixup(backpathcingStack.removeFirst());
     }

     @Override
     public void visit(MultipleFactorConditionTerm andExpr) {
         Code.put(Code.mul); // AND
     }

     @Override
     public void visit(MultipleCondition orExpr) {
         // OR
         Code.put(Code.add);
         Code.loadConst(0);
         backpathcingStack.addFirst(Code.pc + 1);
         Code.putFalseJump(Code.eq, 0);
         Code.loadConst(0);
         int temp = backpathcingStack.removeFirst();
         backpathcingStack.addFirst(Code.pc + 1);
         Code.putJump(0);
         Code.fixup(temp);
         Code.loadConst(1);
         Code.fixup(backpathcingStack.removeFirst());
     }

     @Override
     public void visit(IfConditions cond) {
         Code.loadConst(1);
         backpathcingStack.addFirst(Code.pc + 1);
         Code.putFalseJump(Code.eq, 0);
     }

     @Override
     public void visit(IfStatement ifStatement) {
         Code.fixup(backpathcingStack.removeFirst());
     }
     
     @Override
     public void visit(IfElseStatement ifElseStatement) {
         Code.fixup(backpathcingStack.removeFirst());
     }

     @Override
     public void visit(Else e) {
         int temp = backpathcingStack.removeFirst();
         backpathcingStack.addFirst(Code.pc + 1);
         Code.putJump(0);
         Code.fixup(temp);
     }

     @Override
     public void visit(NoForStartDesignatorStatement statement) {
         // (0)
         loopConditionAddressStack.addFirst(Code.pc);
         breakCountStack.addFirst(0);
     }

     @Override
     public void visit(ForStartDesignatorStatement statement) {
         // (0)
         loopConditionAddressStack.addFirst(Code.pc);
         breakCountStack.addFirst(0);
     }

     @Override
     public void visit(NoForCondition cond) {
         // (1) jump inside the loop body
         backpathcingStack.addFirst(Code.pc + 1);
         Code.putJump(0);

         // (3)
         loopTerminatorAddressStack.addFirst(Code.pc);
     }

     @Override
     public void visit(ForCondition cond) {
         Code.loadConst(1);
         // (2) jump out of the loop
         backpathcingStack.addFirst(Code.pc + 1);
         Code.putFalseJump(Code.eq, 0);

         // (1) jump inside the loop body
         backpathcingStack.addFirst(Code.pc + 1);
         Code.putJump(0);

         // (3)
         loopTerminatorAddressStack.addFirst(Code.pc);
     }

     @Override
     public void visit(NoForEndDesignatorStatement statement) {
         Code.putJump(loopConditionAddressStack.peekFirst()); // jumps to (0)
         Code.fixup(backpathcingStack.removeFirst()); // fixes (1)
     }

     @Override
     public void visit(ForEndDesignatorStatement statement) {
         Code.putJump(loopConditionAddressStack.peekFirst()); // jumps to (0)
         Code.fixup(backpathcingStack.removeFirst()); // fixes (1)
     }

     @Override
     public void visit(ForStatement statement) {
         Code.putJump(loopTerminatorAddressStack.peekFirst()); // jumps to (3)
         if (statement.getForCond() instanceof ForCondition) {
             Code.fixup(backpathcingStack.removeFirst()); // fixes (2)
         }
         loopConditionAddressStack.removeFirst();
         loopTerminatorAddressStack.removeFirst();
         int breakCount = breakCountStack.removeFirst();
         while (breakCount > 0) {
             Code.fixup(breakAddressStack.removeFirst());
             breakCount--;
         }
     }

     @Override
     public void visit(ContinueStatement statement) {
         Code.putJump(loopTerminatorAddressStack.peekFirst()); // jump to (3)
     }

     @Override
     public void visit(BreakStatement statement) {
         breakCountStack.addFirst(breakCountStack.removeFirst() + 1);
         breakAddressStack.addFirst(Code.pc + 1);
         Code.putJump(0);
     }



}
