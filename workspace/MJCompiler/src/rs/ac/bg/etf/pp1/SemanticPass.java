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
	int forLoopDepth = 0;

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

		Obj mainObj = Tab.currentScope.findSymbol("main");

		if (mainObj == null) {
			report_error("Greska: '" + program.getProgName().getPName() + "' main metoda ne postoji ", program);
		} else {

			if (mainObj.getLevel() > 0) {
				report_error("Greska: '" + program.getProgName().getPName() + "' ne sme imati parametre ", program);
			}
			if (mainObj.getType() != Tab.noType) {
				report_error(
						"Greska: '" + program.getProgName().getPName() + "' povratna vrednost mora biti tipa void ",
						program);
			}
		}

		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();

		super.visit(program);
	}

	@Override
	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getPName(), Tab.noType);
		Tab.openScope();

		super.visit(progName);
	}

	@Override
	public void visit(VariableDeclaration variableDeclaration) {
		super.visit(variableDeclaration);
	}

	@Override
	public void visit(SingleVariableIdent singleVariableIdent) {

		if (currentVariableDeclarationType != Tab.noType) {

			if (Tab.currentScope.findSymbol(singleVariableIdent.getName()) != null) {
				report_error("Greska: Promenljiva " + singleVariableIdent.getName() + " je vec deklarisana! ",
						singleVariableIdent);
				return;
			}

			else {

				Obj varDeclObj = Tab.insert(Obj.Var, singleVariableIdent.getName(), currentVariableDeclarationType);
				varDeclObj.setFpPos(-1);
			}

		}

		super.visit(singleVariableIdent);
	}

	@Override
	public void visit(ArrayVariableIdent arrayVariableIdent) {

		if (currentVariableDeclarationType != Tab.noType) {

			if (Tab.currentScope.findSymbol(arrayVariableIdent.getName()) != null) {
				report_error("Greska: Promenljiva " + arrayVariableIdent.getName() + " je vec deklarisana! ",
						arrayVariableIdent);
				return;
			} else {
				Obj arrayTypeObj = Tab.find(currentVariableDeclarationType + "[]");
				Obj varDeclObj = Tab.insert(Obj.Var, arrayVariableIdent.getName(), arrayTypeObj.getType());
				varDeclObj.setFpPos(-1);
			}

		}

		super.visit(arrayVariableIdent);
	}

	@Override
	public void visit(Type type) {

		Obj typeObj = Tab.find(type.getTypeName());

		if (typeObj != Tab.noObj) {

			if (Obj.Type == typeObj.getKind()) {
				type.struct = typeObj.getType();
			} else {
				// javice parser ?
				type.struct = Tab.noType;
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip ", type);
			}

		} else {

			type.struct = Tab.noType;
			report_error("Greska: Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola", null);
		}

		currentVariableDeclarationType = type.struct;

		super.visit(type);
	}

	@Override
	public void visit(MethodDeclaration methodDeclaration) {

		if (currentMethod != null) {

			if (!returnFound && currentMethod.getType() != Tab.noType) {
				report_error("Greska: " + methodDeclaration.getLine() + ": funcija " + currentMethod.getName() + " nema return iskaz!", null);
			}

			Tab.chainLocalSymbols(currentMethod);
			Tab.closeScope();

			currentMethod.setLevel(formalParametersCount);

			returnFound = false;
			currentMethod = null;
			formalParametersCount = 0;

			super.visit(methodDeclaration);
		}
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
			if (retType instanceof VoidReturnType) {
				retType.struct = Tab.noType;
			} else {
				retType.struct = ((ReturnType) retType).getType().struct;
			}

			currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethName(), currentMethodReturnType);
			methodTypeName.obj = currentMethod;
			Tab.openScope();
			report_info("Obradjuje se funkcija " + methodTypeName.getMethName(), methodTypeName);
		}

		super.visit(methodTypeName);
	}

	@Override
	public void visit(SimpleFormalParameter simpleFormalParameter) {

		if (currentVariableDeclarationType != Tab.noType) {

			if (Tab.currentScope.findSymbol(simpleFormalParameter.getName()) != null) {
				report_error("Greska: Parametar '" + simpleFormalParameter.getName() + "' je vec deklarisan",
						simpleFormalParameter);
				// return;
			} else {
				Obj objNode = Tab.insert(Obj.Var, simpleFormalParameter.getName(), currentVariableDeclarationType);
				objNode.setFpPos(formalParametersCount++);
			}
		}

		super.visit(simpleFormalParameter);
	}

	@Override
	public void visit(ArrayFormalParameter arrayFormalParameter) {

		if (currentVariableDeclarationType != Tab.noType) {

			if (Tab.currentScope.findSymbol(arrayFormalParameter.getName()) != null) {
				report_error("Greska: Parametar '" + arrayFormalParameter.getName() + "' je vec deklarisan",
						arrayFormalParameter);
				// return;
			} else {

				// Obj arrayTypeObjNode = Tab.find(arrayFormalParameter.getType().getTypeName()
				// + "[]");
				Obj objNode = Tab.insert(Obj.Var, arrayFormalParameter.getName(),
						new Struct(Struct.Array, currentVariableDeclarationType));
				objNode.setFpPos(formalParametersCount++);
			}
		}

		super.visit(arrayFormalParameter);

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

		if (currentMethod != null) {

			returnFound = true;

			Struct currMethType = currentMethod.getType();
			if (!currMethType.compatibleWith(returnExpressionStatement.getExpr().struct)) {
				report_error("Greska: tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);
			}

		} else {
			// javice parser?
			report_error("Greska: return van funkcije", returnExpressionStatement);
			// return;
		}

		super.visit(returnExpressionStatement);

	}

	@Override
	public void visit(ReturnStatement returnStatement) {

		if (currentMethod != null) {
			returnFound = true;
			Struct currMethType = currentMethod.getType();
			if (!currMethType.compatibleWith(Tab.noType)) {
				report_error("Greska: tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);
			}
		} else {
			// javice parser?
			report_error("Greska: return van funkcije", returnStatement);
			return;
			// return;
		}

		super.visit(returnStatement);
	}

	@Override
	public void visit(PrintStatement printStatement) {
		printCallCount++;
		PrintExpression printExpression = printStatement.getPrintExpression();
		Struct type = null;

		if (printExpression instanceof MultiplePrintExpression) {
			type = ((MultiplePrintExpression) printExpression).getExpr().struct;
		} else
			type = ((SinglePrintExpression) printExpression).getExpr().struct;

		Obj booleanType = Tab.find("bool");

		if (!type.compatibleWith(Tab.intType) && type != Tab.charType && type != booleanType.getType()) {
			report_error("Greska:  Neispravan izraz za print", printStatement);
		}

		super.visit(printStatement);
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
			report_error("Greska: nekompatibilni tipovi u izrazu", null);
			multipleAddopTerm.struct = Tab.noType;
		}

		super.visit(multipleAddopTerm);
	}

	@Override
	public void visit(PositiveExpression positiveExpression) {

		positiveExpression.struct = positiveExpression.getAddopExpr().struct;
		super.visit(positiveExpression);
	}

	@Override
	public void visit(NegativeExpression negativeExpression) {

		if (negativeExpression.getAddopExpr().struct == Tab.intType) {
			negativeExpression.struct = negativeExpression.getAddopExpr().struct;
		} else {
			negativeExpression.struct = Tab.noType;
			report_error("Greska: izraz mora biti celobrojnog tipa.", negativeExpression);
			// return;
		}

		super.visit(negativeExpression);
	}

	@Override
	public void visit(MultipleFactor multipleFactor) {

		Struct term = multipleFactor.getTerm().struct;
		Struct factor = multipleFactor.getFactor().struct;

		if (term.equals(factor) && term == Tab.intType) {
			multipleFactor.struct = term;

		} else {
			report_error("Greska: neispravni tipovi u izrazu", multipleFactor);
			multipleFactor.struct = Tab.noType;
		}

		super.visit(multipleFactor);
	}

	public boolean passed() {
		return !errorDetected;
	}

	@Override
	public void visit(AssignStatement assignStatement) {

		if (!assignStatement.getExpr().struct.assignableTo(assignStatement.getDesignator().obj.getType()))
			report_error("Greska: nekompatibilni tipovi u dodeli vrednosti ", null);
		
		super.visit(assignStatement);
	}

	@Override
	public void visit(SimpleDesignator simpleDesignator) {

		simpleDesignator.obj = Tab.find(simpleDesignator.getName());
		if (simpleDesignator.obj == Tab.noObj) {
			report_error("Greska: " + simpleDesignator.getName() + " nije deklarisano! ", simpleDesignator);
		}

		super.visit(simpleDesignator);
	}

	@Override
	public void visit(ArrayDesignator arrayDesignator) {

		arrayDesignator.obj = Tab.find(arrayDesignator.getDesignator().obj.getName());

		if (arrayDesignator.obj == Tab.noObj) {
			report_error("Greska: " + arrayDesignator.getDesignator().obj.getName() + " nije deklarisano ",
					arrayDesignator);
			return;
		}

		if (arrayDesignator.obj.getKind() != Obj.Var) {
			report_error("Greska: " + arrayDesignator.getDesignator().obj.getName() + " nije promenljiva ",
					arrayDesignator);
			return;
		}

		if (!arrayDesignator.getExpr().struct.compatibleWith(Tab.intType)) {
			report_error("Greska: izraz u zagradama mora biti celobrojnog tipa ", arrayDesignator);
			return;
		}

		if (arrayDesignator.obj.getType().getKind() != Struct.Array) {
			report_error("Greska: ocekuje se nizovski tip", arrayDesignator);
			return;
		}

		arrayDesignator.obj = new Obj(Obj.Elem, arrayDesignator.getDesignator().obj.getName(),
				arrayDesignator.obj.getType().getElemType());
	
		super.visit(arrayDesignator);
	}

	// MEMBER DESIGNATOR

	@Override
	public void visit(DesignatorFactor designatorFactor) {
		designatorFactor.struct = designatorFactor.getDesignator().obj.getType();
		super.visit(designatorFactor);
	}

	@Override
	public void visit(IncDesignatorStatement incDesignatorStatement) {

		if (incDesignatorStatement.getDesignator().obj.getKind() != Obj.Var
				&& incDesignatorStatement.getDesignator().obj.getKind() != Obj.Elem) {
			report_error(
					"Greska: " + incDesignatorStatement.getDesignator().obj.getName() + "' nije promenljiva ", incDesignatorStatement);
			return;
		}
		if (incDesignatorStatement.getDesignator().obj.getType() != Tab.intType) {
			report_error(
					"Greska: " + incDesignatorStatement.getDesignator().obj.getName() + "' nije celobrojnog tipa ", incDesignatorStatement);

		}
		super.visit(incDesignatorStatement);
	}

	@Override
	public void visit(DecDesignatorStatement decDesignatorStatement) {

		if (decDesignatorStatement.getDesignator().obj.getKind() != Obj.Var
				&& decDesignatorStatement.getDesignator().obj.getKind() != Obj.Elem) {
			report_error(
					"Greska: " + decDesignatorStatement.getDesignator().obj.getName() + "' nije promenljiva ", decDesignatorStatement);
			return;
		}
		if (decDesignatorStatement.getDesignator().obj.getType() != Tab.intType) {
			report_error(
					"Greska: " + decDesignatorStatement.getDesignator().obj.getName() + "' nije celobrojnog tipa ", decDesignatorStatement);

		}
		super.visit(decDesignatorStatement);
	}

	@Override
	public void visit(BoolConstInitializer boolConstInitializer) {

		if (currentVariableDeclarationType != Tab.noType) {

			Obj booleanType = Tab.find("bool");

			if (Tab.currentScope.findSymbol(boolConstInitializer.getName()) != null) {
				report_error("Greska : konstanta " + boolConstInitializer.getName() + "' je vec deklarisana! ",
						boolConstInitializer);
				return;
			}

			else {

				if (currentVariableDeclarationType == booleanType.getType()) {

					Obj newConst = Tab.insert(Obj.Con, boolConstInitializer.getName(), booleanType.getType());

					int val;
					if (boolConstInitializer.getVal() == true) {
						val = 1;
					} else {
						val = 0;
					}

					newConst.setAdr(val);

				} else {

					report_error("Greska: deklarisani tip se ne moze inicijalizovati boolean vrednoscu! ",
							boolConstInitializer);
					return;

				}
			}
		}
		super.visit(boolConstInitializer);

	}

	@Override
	public void visit(NumConstInitializer numConstInitializer) {

		if (currentVariableDeclarationType != Tab.noType) {

			if (Tab.currentScope.findSymbol(numConstInitializer.getName()) != null) {
				report_error("Greska : konstanta " + numConstInitializer.getName() + " je vec deklarisana! ",
						numConstInitializer);
				return;
			}

			else {

				if (currentVariableDeclarationType == Tab.intType) {
					Obj initialiserObj = Tab.insert(Obj.Con, numConstInitializer.getName(), Tab.intType);
					initialiserObj.setAdr(numConstInitializer.getVal());
				} else {
					report_error("Greska : deklarisani tip se ne moze inicijalizovati celobrojnom vrednoscu!",
							numConstInitializer);
					return;
				}

			}

		}

		super.visit(numConstInitializer);
	}

	@Override
	public void visit(CharConstInitializer charConstInitializer) {

		if (currentVariableDeclarationType != Tab.noType) {

			if (Tab.currentScope.findSymbol(charConstInitializer.getName()) != null) {
				report_error("Greska: konstanta " + charConstInitializer.getName() + " je vec deklarisana! ",
						charConstInitializer);
				return;
			} else {

				if (currentVariableDeclarationType == Tab.charType) {

					Obj initialiserObj = Tab.insert(Obj.Con, charConstInitializer.getName(), Tab.charType);
					initialiserObj.setAdr(charConstInitializer.getVal());

				} else {

					report_error("Greska: deklarisani tip se ne moze inicijalizovati char vrednoscu! ",
							charConstInitializer);
					return;
				}

			}

		}

		super.visit(charConstInitializer);
	}

	@Override
	public void visit(NewObjectFactor newObjectFactor) {

		if (newObjectFactor.getType().struct != Tab.noType) {

			newObjectFactor.struct = newObjectFactor.getType().struct;

		}

		super.visit(newObjectFactor);
	}

	@Override
	public void visit(NewObjectArrayFactor newObjectArrayFactor) {

		if (newObjectArrayFactor.getType().struct != Tab.noType) {

			if (newObjectArrayFactor.getExpr().struct == Tab.intType) {
				Obj newObjArray = Tab.find(newObjectArrayFactor.getType().getTypeName() + "[]");
				newObjectArrayFactor.struct = newObjArray.getType();
			} else {
				report_error("Greska: duzina niza mora biti celobrojan tip ", newObjectArrayFactor);
				newObjectArrayFactor.struct = Tab.noType;
				// return;
			}

		}
		super.visit(newObjectArrayFactor);
	}

	@Override
	public void visit(ParenFactor parenFactor) {
		parenFactor.struct = parenFactor.getExpr().struct;
		super.visit(parenFactor);
	}

	@Override
	public void visit(ForStatementBegin forStatementBegin) {
		forLoopDepth++;
		super.visit(forStatementBegin);
	}

	@Override
	public void visit(ForStatement forStatement) {
		forLoopDepth--;
		super.visit(forStatement);
	}

	@Override
	public void visit(ReadStatement readStatement) {

		if (readStatement.getDesignator().obj.getKind() != Obj.Var
				&& readStatement.getDesignator().obj.getKind() != Obj.Elem) {
			report_error("Greska: " + readStatement.getDesignator().obj.getName() + "' nije promenljiva ", readStatement);

			return;
		}
		Obj booleanType = Tab.find("bool");
		Struct type = readStatement.getDesignator().obj.getType();

		if (type != Tab.intType && type != Tab.charType && type != booleanType.getType()) {
			report_error("Greska: promenljiva sme biti celobrojnog, char ili boolean tipa ", readStatement);
		}
		
		super.visit(readStatement);
	}

	@Override
	public void visit(ContinueStatement continueStatement) {
		if (forLoopDepth == 0) {
			report_error("Continue mora biti u for petlji", continueStatement);
		}

		super.visit(continueStatement);
	}

	@Override
	public void visit(BreakStatement breakStatement) {
		if (forLoopDepth == 0) {
			report_error("Break mora biti u for petlji", breakStatement);
		}

		super.visit(breakStatement);
	}

	@Override
	public void visit(FuncCall funcCall) {
		Obj func = funcCall.getDesignator().obj;
		if (Obj.Meth == func.getKind()) {
			

			if (func.getType() == Tab.noType) {
				report_error("Greska: povratna vrednost funkcije " + func.getName() + " ne moze biti deo izraza", null);
			}

			funcCall.struct = func.getType();
		} else {
			report_error("Greska: " + func.getName() + " nije funkcija!",
					null);
			funcCall.struct = Tab.noType;
		}
		super.visit(funcCall);

	}

	@Override
	public void visit(ProcCall procCall) {
		Obj func = procCall.getDesignator().obj;
		if (Obj.Meth == func.getKind()) {
			
			// RESULT = func.getType();
		} else {
			report_error("Greska: " + func.getName() + " nije funkcija!",
					null);
			// RESULT = Tab.noType;
		}
		super.visit(procCall);
	}

	// actual poziv fje

}
