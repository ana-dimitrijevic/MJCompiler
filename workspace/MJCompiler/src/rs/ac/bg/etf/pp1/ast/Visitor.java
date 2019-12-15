// generated with ast extension for cup
// version 0.8
// 15/11/2019 21:58:32


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Mulop Mulop);
    public void visit(MethodDecl MethodDecl);
    public void visit(VarIdent VarIdent);
    public void visit(AddopExpr AddopExpr);
    public void visit(Relop Relop);
    public void visit(Initializer Initializer);
    public void visit(FormalParamDecl FormalParamDecl);
    public void visit(ActualParamsList ActualParamsList);
    public void visit(VarDeclar VarDeclar);
    public void visit(Addop Addop);
    public void visit(FuncCall FuncCall);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(ClassVarDeclList ClassVarDeclList);
    public void visit(PrintExpression PrintExpression);
    public void visit(Term Term);
    public void visit(RetType RetType);
    public void visit(Condition Condition);
    public void visit(Statements Statements);
    public void visit(AbstractModif AbstractModif);
    public void visit(ElsePart ElsePart);
    public void visit(ActualParams ActualParams);
    public void visit(ClassExtends ClassExtends);
    public void visit(AssignStmnt AssignStmnt);
    public void visit(VarDeclList VarDeclList);
    public void visit(FormalParamList FormalParamList);
    public void visit(DeclVarList DeclVarList);
    public void visit(Expr Expr);
    public void visit(DesignatorList DesignatorList);
    public void visit(ConstInitializer ConstInitializer);
    public void visit(DeclVar DeclVar);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(ForDesignatorStm ForDesignatorStm);
    public void visit(ClassMethDeclList ClassMethDeclList);
    public void visit(Statement Statement);
    public void visit(VarIdentList VarIdentList);
    public void visit(CondFact CondFact);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(ForCond ForCond);
    public void visit(FormPars FormPars);
    public void visit(RelopLEQ RelopLEQ);
    public void visit(RelopLS RelopLS);
    public void visit(RelopGEQ RelopGEQ);
    public void visit(RelopGR RelopGR);
    public void visit(RelopNOTEQ RelopNOTEQ);
    public void visit(RelopEQ RelopEQ);
    public void visit(MulopMOD MulopMOD);
    public void visit(MulopDIV MulopDIV);
    public void visit(MulopMUL MulopMUL);
    public void visit(AddopSUB AddopSUB);
    public void visit(AddopADD AddopADD);
    public void visit(RelopExprConditionFactor RelopExprConditionFactor);
    public void visit(ExprConditionFactor ExprConditionFactor);
    public void visit(SingleFactorConditionTerm SingleFactorConditionTerm);
    public void visit(MultipleFactorConditionTerm MultipleFactorConditionTerm);
    public void visit(IllegalConditionError IllegalConditionError);
    public void visit(SingleCondition SingleCondition);
    public void visit(MultipleCondition MultipleCondition);
    public void visit(NoForCondition NoForCondition);
    public void visit(ForCondition ForCondition);
    public void visit(NoForDesignatorStatement NoForDesignatorStatement);
    public void visit(ForDesignatorStatement ForDesignatorStatement);
    public void visit(NoElseStatement NoElseStatement);
    public void visit(ElseStatement ElseStatement);
    public void visit(MultiplePrintExpression MultiplePrintExpression);
    public void visit(SinglePrintExpression SinglePrintExpression);
    public void visit(SingleActualParameter SingleActualParameter);
    public void visit(MultipleActualParameters MultipleActualParameters);
    public void visit(NoActualParametersList NoActualParametersList);
    public void visit(ActualParametersList ActualParametersList);
    public void visit(FunctionCall FunctionCall);
    public void visit(AssignStatementError AssignStatementError);
    public void visit(AssignStatement AssignStatement);
    public void visit(FuncCallDesignatorStatement FuncCallDesignatorStatement);
    public void visit(AssignDesignatorStatement AssignDesignatorStatement);
    public void visit(DecDesignatorStatement DecDesignatorStatement);
    public void visit(IncDesignatorStatement IncDesignatorStatement);
    public void visit(NoDesignatorList NoDesignatorList);
    public void visit(DesignatorListExpr DesignatorListExpr);
    public void visit(DesignatorListDot DesignatorListDot);
    public void visit(Designator Designator);
    public void visit(ParenFactor ParenFactor);
    public void visit(FuncCallFactor FuncCallFactor);
    public void visit(NewObjectArrayFactor NewObjectArrayFactor);
    public void visit(NewObjectFactor NewObjectFactor);
    public void visit(DesignatorFactor DesignatorFactor);
    public void visit(BoolConstFactor BoolConstFactor);
    public void visit(CharConstFactor CharConstFactor);
    public void visit(NumberConstFactor NumberConstFactor);
    public void visit(SingleFactor SingleFactor);
    public void visit(MultipleFactor MultipleFactor);
    public void visit(SingleAddopTerm SingleAddopTerm);
    public void visit(MultipleAddopTerm MultipleAddopTerm);
    public void visit(NegativeExpression NegativeExpression);
    public void visit(PositiveExpression PositiveExpression);
    public void visit(BlockOfStatements BlockOfStatements);
    public void visit(StatementDesignator StatementDesignator);
    public void visit(ForStatement ForStatement);
    public void visit(IfStatement IfStatement);
    public void visit(PrintStatement PrintStatement);
    public void visit(ReadStatement ReadStatement);
    public void visit(ReturnExpressionStatement ReturnExpressionStatement);
    public void visit(ContinueStatement ContinueStatement);
    public void visit(BreakStatement BreakStatement);
    public void visit(ReturnStatement ReturnStatement);
    public void visit(NoStatementsList NoStatementsList);
    public void visit(StatementsList StatementsList);
    public void visit(FormalParameterError FormalParameterError);
    public void visit(ArrayFormalParameter ArrayFormalParameter);
    public void visit(SimpleFormalParameter SimpleFormalParameter);
    public void visit(SingleFormalParamDecl SingleFormalParamDecl);
    public void visit(FormalParamDecls FormalParamDecls);
    public void visit(NoFormParam NoFormParam);
    public void visit(FormParams FormParams);
    public void visit(ReturnType ReturnType);
    public void visit(VoidReturnType VoidReturnType);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(AbstractMethodDeclaration AbstractMethodDeclaration);
    public void visit(MethodDeclaration MethodDeclaration);
    public void visit(NoMethodDeclarations NoMethodDeclarations);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(BoolConstInitializer BoolConstInitializer);
    public void visit(CharConstInitializer CharConstInitializer);
    public void visit(NumConstInitializer NumConstInitializer);
    public void visit(SingleConstInitializer SingleConstInitializer);
    public void visit(ConstInitializerList ConstInitializerList);
    public void visit(Type Type);
    public void visit(NoClassMethodDeclList NoClassMethodDeclList);
    public void visit(ClassMethodDeclList ClassMethodDeclList);
    public void visit(NoClassVarDeclarationList NoClassVarDeclarationList);
    public void visit(ClassVarDeclarationList ClassVarDeclarationList);
    public void visit(NoExtesion NoExtesion);
    public void visit(Extension Extension);
    public void visit(NoAbstractModifer NoAbstractModifer);
    public void visit(AbstractModifer AbstractModifer);
    public void visit(NoVariableDeclarationList NoVariableDeclarationList);
    public void visit(VariableDeclarationList VariableDeclarationList);
    public void visit(VariableIdentError VariableIdentError);
    public void visit(ArrayVariableIdent ArrayVariableIdent);
    public void visit(SingleVariableIdent SingleVariableIdent);
    public void visit(SingleVariableIdentList SingleVariableIdentList);
    public void visit(MultipleVariableIdentList MultipleVariableIdentList);
    public void visit(VariableDeclarationError VariableDeclarationError);
    public void visit(VariableDeclaration VariableDeclaration);
    public void visit(VarDeclClass VarDeclClass);
    public void visit(VarDeclNonConst VarDeclNonConst);
    public void visit(VarDeclConst VarDeclConst);
    public void visit(NoVarDeclarations NoVarDeclarations);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
