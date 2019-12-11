// generated with ast extension for cup
// version 0.8
// 11/11/2019 19:32:20


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(MethodDecl MethodDecl);
    public void visit(RetType RetType);
    public void visit(Factor Factor);
    public void visit(ActualParamList ActualParamList);
    public void visit(DeclVar DeclVar);
    public void visit(Expr Expr);
    public void visit(FormalParamList FormalParamList);
    public void visit(AbstractModif AbstractModif);
    public void visit(FormPars FormPars);
    public void visit(VarIdent VarIdent);
    public void visit(DeclVarList DeclVarList);
    public void visit(VarDeclList VarDeclList);
    public void visit(Unmatched Unmatched);
    public void visit(ClassExtends ClassExtends);
    public void visit(FormalParamDecl FormalParamDecl);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(Statement Statement);
    public void visit(ClassMethDeclList ClassMethDeclList);
    public void visit(InitList InitList);
    public void visit(ClassVarDeclList ClassVarDeclList);
    public void visit(VarIdentList VarIdentList);
    public void visit(StatementList StatementList);
    public void visit(Matched Matched);
    public void visit(ActualPars ActualPars);
    public void visit(VarDeclar VarDeclar);
    public void visit(Init Init);
    public void visit(Addop Addop);
    public void visit(Designator Designator);
    public void visit(ActualParam ActualParam);
    public void visit(ActualParams ActualParams);
    public void visit(NoActuals NoActuals);
    public void visit(Actuals Actuals);
    public void visit(FuncCall FuncCall);
    public void visit(Var Var);
    public void visit(Const Const);
    public void visit(Term Term);
    public void visit(TermExpr TermExpr);
    public void visit(AddExpr AddExpr);
    public void visit(ProcCall ProcCall);
    public void visit(MatchedIf MatchedIf);
    public void visit(ReturnNoExpr ReturnNoExpr);
    public void visit(ReturnExpr ReturnExpr);
    public void visit(PrintStmt PrintStmt);
    public void visit(ErrAssignment ErrAssignment);
    public void visit(Assignment Assignment);
    public void visit(UnmatchedIfElse UnmatchedIfElse);
    public void visit(UnmatchedIf UnmatchedIf);
    public void visit(UnmachedStmt UnmachedStmt);
    public void visit(MatchedStmt MatchedStmt);
    public void visit(NoStmt NoStmt);
    public void visit(Statements Statements);
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
    public void visit(NoMethodDecl NoMethodDecl);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(VariableIdentError VariableIdentError);
    public void visit(ArrayVariableIdent ArrayVariableIdent);
    public void visit(SingleVariableIdent SingleVariableIdent);
    public void visit(OneVariableIdentList OneVariableIdentList);
    public void visit(MultipleVariableIdentList MultipleVariableIdentList);
    public void visit(NoVariableDeclarationList NoVariableDeclarationList);
    public void visit(VariableDeclarationList VariableDeclarationList);
    public void visit(VariableDeclarationError VariableDeclarationError);
    public void visit(VariableDeclaration VariableDeclaration);
    public void visit(CharConstInitializer CharConstInitializer);
    public void visit(BoolConstInitializer BoolConstInitializer);
    public void visit(NumConstInitializer NumConstInitializer);
    public void visit(SingleInitializer SingleInitializer);
    public void visit(InitializerList InitializerList);
    public void visit(Type Type);
    public void visit(NoClassMethodDeclList NoClassMethodDeclList);
    public void visit(ClassMethodDeclList ClassMethodDeclList);
    public void visit(NoClassVarDeclarationList NoClassVarDeclarationList);
    public void visit(ClassVarDeclarationList ClassVarDeclarationList);
    public void visit(NoExtesion NoExtesion);
    public void visit(Extension Extension);
    public void visit(NoAbstractModifer NoAbstractModifer);
    public void visit(AbstractModifer AbstractModifer);
    public void visit(ClassDecl ClassDecl);
    public void visit(VarDeclNonConst VarDeclNonConst);
    public void visit(VarDeclConst VarDeclConst);
    public void visit(NoVarDeclarations NoVarDeclarations);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
