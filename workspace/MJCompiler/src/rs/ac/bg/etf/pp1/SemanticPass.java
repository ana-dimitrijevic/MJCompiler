package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.concepts.*;
import java.util.function.Function;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class SemanticPass extends VisitorAdaptor {

	boolean errorDetected = false;
	int printCallCount = 0;
	int formalParametersCount = 0;
	Obj currentMethod = null;
	boolean returnFound = false;
	int nVars;
	Struct currentVariableDeclarationType = null;
	Struct currentMethodReturnType = null;

	Logger log = Logger.getLogger(getClass());

	SemanticPass() {
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", new Struct(5)));
	}

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());

	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

	@Override
	public void visit(Program program) {

		Obj main = Tab.currentScope.findSymbol("main");

		if (main == null) {
			report_error("Greska: '" + program.getProgName().getPName() + "' main metoda ne postoji ", null);
		} else {
			if (main.getType() != Tab.noType) {
				report_error(
						"Greska: '" + program.getProgName().getPName() + "' povratna vrednost mora biti tipa void ",
						null);
			}
			if (main.getLevel() > 0) {
				report_error("Greska: '" + program.getProgName().getPName() + "' ne sme imati parametre ", null);
			}
		}

		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}

	@Override
	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getPName(), Tab.noType);
		Tab.openScope();
	}

	@Override
	public void visit(VariableDeclaration variableDeclaration) {
		super.visit(variableDeclaration);
	}

	@Override
	public void visit(SingleVariableIdent singleVariableIdent) {

		if (currentVariableDeclarationType == Tab.noType)
			return;

		if (Tab.currentScope.findSymbol(singleVariableIdent.getName()) != null) {
			report_error("Greska: Promenljiva " + singleVariableIdent.getName() + " je vec deklarisana ", singleVariableIdent);
			return;
		}

		report_info("Deklarisana promenljiva " + singleVariableIdent.getName(), singleVariableIdent);
		Obj varNode = Tab.insert(Obj.Var, singleVariableIdent.getName(), currentVariableDeclarationType);
		//objNode.setFpPos(FP_POS_INVALID_VALUE);
		super.visit(singleVariableIdent);
	}
	
	@Override
    public void visit(ArrayVariableIdent arrayVariableIdent) {
        if (currentVariableDeclarationType == Tab.noType)
            return;
        
        if (Tab.currentScope.findSymbol(arrayVariableIdent.getName()) != null) {
			report_error("Greska: Promenljiva " + arrayVariableIdent.getName() + " je vec deklarisana ", arrayVariableIdent);
			return;
		}
        
        Obj arrayTypeObjNode = Tab.find(currentVariableDeclarationType + "[]");
        Obj objNode = Tab.insert(Obj.Var, arrayVariableIdent.getName(), arrayTypeObjNode.getType());
        //objNode.setFpPos(FP_POS_INVALID_VALUE);
	}


	@Override
	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		if (typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola", null);
			type.struct = Tab.noType;
		} else {
			if (Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
			} else {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip ", type);
				type.struct = Tab.noType;
			}
		}

		currentVariableDeclarationType = type.struct;
	}

	@Override
	public void visit(MethodDeclaration methodDeclaration) {

		// ako neko pokusa da redefinise vec deklarisan
		if (currentMethod == null) {
			return;
		}

		if (!returnFound && currentMethod.getType() != Tab.noType) {
			report_error("Semanticka greska na liniji " + methodDeclaration.getLine() + ": funcija "
					+ currentMethod.getName() + " nema return iskaz!", null);
		}

		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();

		currentMethod.setLevel(formalParametersCount);

		returnFound = false;
		currentMethod = null;
		formalParametersCount = 0;

		super.visit(methodDeclaration);
	}

	// apstraktna

	@Override
	public void visit(MethodTypeName methodTypeName) {

		// javi ovo ali jer mu nije namesten currentMethod
		if (Tab.currentScope.findSymbol(methodTypeName.getMethName()) != null) {
			report_error("Greska: '" + methodTypeName.getMethName() + "' je vec deklarisana", methodTypeName);
			return;
		} else {

			RetType retType = methodTypeName.getRetType();
			if (retType instanceof VoidReturnType)
				retType.struct = Tab.noType;
			else
				retType.struct = ((ReturnType) retType).getType().struct;

			currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethName(), currentMethodReturnType);
			methodTypeName.obj = currentMethod;
			Tab.openScope();
			report_info("Obradjuje se funkcija " + methodTypeName.getMethName(), methodTypeName);
		}

	}

	@Override
	public void visit(SimpleFormalParameter simpleFormalParameter) {

		if (currentVariableDeclarationType == Tab.noType)
			return;

		if (Tab.currentScope.findSymbol(simpleFormalParameter.getName()) != null) {
			report_error("Greska: '" + simpleFormalParameter.getName() + "' je vec deklarisan", simpleFormalParameter);
			return;
		} else {
			Obj objNode = Tab.insert(Obj.Var, simpleFormalParameter.getName(), currentVariableDeclarationType);
			objNode.setFpPos(formalParametersCount++);
		}
	}

	@Override
	public void visit(ArrayFormalParameter arrayFormalParameter) {

		if (currentVariableDeclarationType == Tab.noType)
			return;

		if (Tab.currentScope.findSymbol(arrayFormalParameter.getName()) != null) {
			report_error("Greska: '" + arrayFormalParameter.getName() + "' je vec deklarisan", arrayFormalParameter);
			return;
		} else {
			// cemu ovo?
			Obj arrayTypeObjNode = Tab.find(arrayFormalParameter.getType().getTypeName() + "[]");
			Obj objNode = Tab.insert(Obj.Var, arrayFormalParameter.getName(),
					new Struct(Struct.Array, currentVariableDeclarationType));
			objNode.setFpPos(formalParametersCount++);
		}

	}

	@Override
	public void visit(ReturnType returnType) {
		currentMethodReturnType = returnType.getType().struct;
		super.visit(returnType);
	}

	@Override
	public void visit(VoidReturnType voidReturnType) {
		currentMethodReturnType = Tab.noType;
		super.visit(voidReturnType);
	}

	@Override
	public void visit(ReturnExpressionStatement returnExpressionStatement) {

		if (currentMethod == null) {
			report_error("Greska: return van funkcije", returnExpressionStatement);
			return;
		}

		returnFound = true;

		Struct currMethType = currentMethod.getType();
		if (!currMethType.compatibleWith(returnExpressionStatement.getExpr().struct)) {
			report_error("Greska na liniji " + returnExpressionStatement.getLine() + " : "
					+ "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije "
					+ currentMethod.getName(), null);
		}

		super.visit(returnExpressionStatement);

	}

	@Override
	public void visit(ReturnStatement returnStatement) {

		if (currentMethod == null) {
			report_error("Greska: return van funkcije", returnStatement);
			return;
		}

		returnFound = true;
		Struct currMethType = currentMethod.getType();
		if (!currMethType.compatibleWith(Tab.noType)) {
			report_error("Greska na liniji " + returnStatement.getLine() + " : "
					+ "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije "
					+ currentMethod.getName(), null);
		}
		super.visit(returnStatement);
	}

	@Override
	public void visit(PrintStatement printStatement) {
		printCallCount++;
	}

	@Override
	public void visit(NumberConstFactor numberConstFactor) {
		
		numberConstFactor.struct = Tab.intType;
		super.visit(numberConstFactor);
	}

	@Override
	public void visit(CharConstFactor charConstFactor) {
		
		charConstFactor.struct = Tab.charType;
		super.visit(charConstFactor);
	}

	@Override
	public void visit(BoolConstFactor boolConstFactor) {
		
		boolConstFactor.struct = Tab.find("bool").getType();
		super.visit(boolConstFactor);
	}

	@Override
	public void visit(SingleFactor singleFactor) {
		singleFactor.struct = singleFactor.getFactor().struct;
		super.visit(singleFactor);
	}

	@Override
	public void visit(SingleAddopTerm singleAddopTerm) {
		singleAddopTerm.struct = singleAddopTerm.getTerm().struct;
		super.visit(singleAddopTerm);
	}

	@Override
	public void visit(MultipleAddopTerm multipleAddopTerm) {

		Struct rightTerm = multipleAddopTerm.getTerm().struct;
		Struct leftTerm = multipleAddopTerm.getAddopExpr().struct;

		if (rightTerm.equals(leftTerm) && rightTerm == Tab.intType)
			multipleAddopTerm.struct = rightTerm;
		else {
			report_error("Greska na liniji " + multipleAddopTerm.getLine() + " : nekompatibilni tipovi u izrazu.",
					null);
			multipleAddopTerm.struct = Tab.noType;
		}

		super.visit(multipleAddopTerm);
	}

	@Override
	public void visit(PositiveExpression positiveExpression) {
		
		positiveExpression.struct = positiveExpression.getAddopExpr().struct;
		super.visit(positiveExpression);
	}

	public boolean passed() {
		return !errorDetected;
	}

	@Override
	public void visit(AssignStatement assignStatement) {
		
		if (!assignStatement.getExpr().struct.assignableTo(assignStatement.getDesignator().obj.getType()))
			report_error("Greska na liniji " + assignStatement.getLine() + " : "
					+ " nekompatibilni tipovi u dodeli vrednosti ", null);
	}

	@Override
	public void visit(SimpleDesignator simpleDesignator) {

		simpleDesignator.obj = Tab.find(simpleDesignator.getName());
		if (simpleDesignator.obj == Tab.noObj) {
			report_error("Greska na liniji " + simpleDesignator.getLine() + " : ime " + simpleDesignator.getName()
					+ " nije deklarisano! ", simpleDesignator);
		}

	}

	@Override
	public void visit(ArrayDesignator arrayDesignator) {

		arrayDesignator.obj = Tab.find(arrayDesignator.getDesignator().obj.getName());

		if (arrayDesignator.obj == Tab.noObj) {
			report_error("Greska na liniji " + arrayDesignator.getLine() + " : ime "
					+ arrayDesignator.getDesignator().obj.getName() + " nije deklarisano ", arrayDesignator);
			return;
		}

		if (arrayDesignator.obj.getKind() != Obj.Var) {
			report_error("Greska na liniji " + arrayDesignator.getLine() + " : ime "
					+ arrayDesignator.getDesignator().obj.getName() + " nije promenljiva ", arrayDesignator);
			return;
		}

		if (!arrayDesignator.getExpr().struct.compatibleWith(Tab.intType)) {
			report_error("Greska na liniji " + arrayDesignator.getLine()
					+ " : izraz u zagradama mora biti celobrojnog tipa ", arrayDesignator);
			return;
		}

		if (arrayDesignator.obj.getType().getKind() != Struct.Array) {
			report_error("Greska na liniji " + arrayDesignator.getLine() + " : ocekuje se nizovski tip",
					arrayDesignator);
			return;
		}

		arrayDesignator.obj = new Obj(Obj.Elem, arrayDesignator.getDesignator().obj.getName(),
				arrayDesignator.obj.getType().getElemType());
	}

	// MEMBER DESIGNATOR

	@Override
	public void visit(DesignatorFactor designatorFactor) {
		designatorFactor.struct = designatorFactor.getDesignator().obj.getType();
	}

	@Override
	public void visit(IncDesignatorStatement incDesignatorStatement) {

		if (incDesignatorStatement.getDesignator().obj.getKind() != Obj.Var
				&& incDesignatorStatement.getDesignator().obj.getKind() != Obj.Elem) {
			report_error(
					"Greska na liniji " + incDesignatorStatement.getLine() + " : ime "
							+ incDesignatorStatement.getDesignator().obj.getName() + "' nije promenljiva ",
					incDesignatorStatement);
			return;
		}
		if (incDesignatorStatement.getDesignator().obj.getType() != Tab.intType) {
			report_error(
					"Greska na liniji " + incDesignatorStatement.getLine() + " : "
							+ incDesignatorStatement.getDesignator().obj.getName() + "' nije celobrojnog tipa ",
					incDesignatorStatement);

		}
	}

	@Override
	public void visit(DecDesignatorStatement decDesignatorStatement) {

		if (decDesignatorStatement.getDesignator().obj.getKind() != Obj.Var
				&& decDesignatorStatement.getDesignator().obj.getKind() != Obj.Elem) {
			report_error(
					"Greska na liniji " + decDesignatorStatement.getLine() + " : ime "
							+ decDesignatorStatement.getDesignator().obj.getName() + "' nije promenljiva ",
					decDesignatorStatement);
			return;
		}
		if (decDesignatorStatement.getDesignator().obj.getType() != Tab.intType) {
			report_error(
					"Greska na liniji " + decDesignatorStatement.getLine() + " : "
							+ decDesignatorStatement.getDesignator().obj.getName() + "' nije celobrojnog tipa ",
					decDesignatorStatement);

		}
	}

	@Override
	public void visit(NumConstInitializer numConstInitializer) {
		if (currentVariableDeclarationType == Tab.noType)
			return;
		if (currentVariableDeclarationType != Tab.intType) {
			report_error(
					"Greska na liniji " + numConstInitializer.getLine()
							+ " : deklarisani tip se ne moze inicijalizovati celobrojnom vrednoscu",
					numConstInitializer);
			return;
		}

		if (Tab.currentScope.findSymbol(numConstInitializer.getName()) != null) {
			report_error("Greska na liniji " + numConstInitializer.getLine() + " : " + numConstInitializer.getName()
					+ "' je vec deklarisan ", numConstInitializer);
			return;
		}

		Obj newConst = Tab.insert(Obj.Con, numConstInitializer.getName(), Tab.intType);
		newConst.setAdr(numConstInitializer.getVal());
	}

	/*@Override
	public void visit(BoolConstInitializer boolConstInitializer) {
		if (currentVariableDeclarationType == Tab.noType)
			return;

		//ne moze ovako
		SymbolDataStructure s = Tab.currentScope().getLocals();
		Struct boolType = s.searchKey("bool").getType();

		if (currentVariableDeclarationType != boolType) {
			report_error("Greska na liniji " + boolConstInitializer.getLine()
					+ " : deklarisani tip se ne moze inicijalizovati boolean vrednoscu ", boolConstInitializer);
			return;
		}

		if (Tab.currentScope.findSymbol(boolConstInitializer.getName()) != null) {
			report_error("Greska na liniji " + boolConstInitializer.getLine() + " : " + boolConstInitializer.getName()
					+ "' je vec deklarisan ", boolConstInitializer);
			return;
		}

		Obj newConst = Tab.insert(Obj.Con, boolConstInitializer.getName(), boolType);

		int val;
		if (boolConstInitializer.getVal() == true) {
			val = 1;
		} else {
			val = 0;
		}

		newConst.setAdr(val);

	}*/

	@Override
	public void visit(CharConstInitializer charConstInitializer) {
		if (currentVariableDeclarationType == Tab.noType)
			return;
		if (currentVariableDeclarationType != Tab.charType) {
			report_error("Greska na liniji " + charConstInitializer.getLine()
					+ " : deklarisani tip se ne moze inicijalizovati char vrednoscu ", charConstInitializer);
			return;
		}

		if (Tab.currentScope.findSymbol(charConstInitializer.getName()) != null) {
			report_error("Greska na liniji " + charConstInitializer.getLine() + " : " + charConstInitializer.getName()
					+ "' je vec deklarisan ", charConstInitializer);
			return;
		}

		Obj newConst = Tab.insert(Obj.Con, charConstInitializer.getName(), Tab.charType);
		newConst.setAdr(charConstInitializer.getVal());

	}
	

	@Override
	public void visit(FuncCall funcCall) {
		Obj func = funcCall.getDesignator().obj;
		if (Obj.Meth == func.getKind()) {
			report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + funcCall.getLine(), null);
			funcCall.struct = func.getType();
		} else {
			report_error("Greska na liniji " + funcCall.getLine() + " : ime " + func.getName() + " nije funkcija!",
					null);
			funcCall.struct = Tab.noType;
		}

	}

	@Override
	public void visit(ProcCall procCall) {
		Obj func = procCall.getDesignator().obj;
		if (Obj.Meth == func.getKind()) {
			report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + procCall.getLine(), null);
			// RESULT = func.getType();
		} else {
			report_error("Greska na liniji " + procCall.getLine() + " : ime " + func.getName() + " nije funkcija!",
					null);
			// RESULT = Tab.noType;
		}
	}

}
