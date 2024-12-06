package view;

import controller.Controller;
import exceptions.EmptyStackException;
import model.expressions.*;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.RefType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;
import repository.IRepository;
import repository.Repository;
import view.commands.ExitCommand;
import view.commands.RunExampleCommand;

import java.io.IOException;

public class Interpreter
{
    public static void main(String[] args) throws EmptyStackException, IOException {
        //int v; v=2; Print(v)
        IStmt statement1 = new CompStmt(new VariablesDeclarationStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExpression(new IntValue(2))),
                        new PrintStm(new VariableExpression("v"))));
        IRepository repo1 = new Repository("log1.txt");
        Controller controller1 = new Controller(repo1 , true);
        controller1.addProgram(statement1);

        // int a; int b; a=2+3*5; b=a+1; Print(b)
        IStmt statement2 = new CompStmt(new VariablesDeclarationStmt("a",new IntType()),
                new CompStmt(new VariablesDeclarationStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithmeticalExpression(new ValueExpression(new IntValue(2)),ArithmeticalOperator.ADD,new
                                ArithmeticalExpression(new ValueExpression(new IntValue(3)),ArithmeticalOperator.MULTIPLY,new ValueExpression(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithmeticalExpression(new VariableExpression("a"), ArithmeticalOperator.ADD,new ValueExpression(new
                                        IntValue(1)))), new PrintStm(new VariableExpression("b"))))));
        IRepository repo2 = new Repository("log2.txt");
        Controller controller2 = new Controller(repo2 , true);
        controller2.addProgram(statement2);

        // bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v)
        IStmt statement3 = new CompStmt(new VariablesDeclarationStmt("a", new BoolType()),
                new CompStmt(new VariablesDeclarationStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExpression(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VariableExpression("a"),
                                        new AssignStmt("v", new ValueExpression(new IntValue(2))),
                                        new AssignStmt("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStm(new VariableExpression("v"))))));
        IRepository repo3 = new Repository("log3.txt");
        Controller controller3 = new Controller(repo3,true);
        controller3.addProgram(statement3);

        // string varf; varf = "test.in"; OpenReadFile("varf"); int varc; ReadFile("varf", "varc"); Print(varc); ReadFile("varf", "varc"); Print(varc); CloseReadFile("varf")
        IStmt statement4 = new CompStmt(new VariablesDeclarationStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExpression(
                        new StringValue("test.in"))),
                        new CompStmt(new OpenReadFileStatement(new VariableExpression("varf")),
                                new CompStmt(new VariablesDeclarationStmt("varc", new IntType()),
                                        new CompStmt(new ReadFileStatement(
                                                new VariableExpression("varf"), "varc"),
                                                new CompStmt(new PrintStm(new VariableExpression("varc")),
                                                        new CompStmt(new ReadFileStatement(
                                                                new VariableExpression("varf"), "varc"),
                                                                new CompStmt(
                                                                        new PrintStm(
                                                                                new VariableExpression("varc")),
                                                                        new CloseReadFileStatement(
                                                                                new VariableExpression("varf"))))))))));


        IRepository repo4 = new Repository("log4.txt");
        Controller controller4 = new Controller(repo4,true);
        controller4.addProgram(statement4);

        /* Ref int v; new(v,20); Ref Ref int a; new(a,v); print(v); print(a) */
                IStmt statement5 = new CompStmt(new VariablesDeclarationStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocationStatement(new ValueExpression(new IntValue(20)),"v"),
                        new CompStmt(new VariablesDeclarationStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocationStatement(new VariableExpression("v"),"a"),
                                        new CompStmt(new PrintStm(new VariableExpression("v")), new PrintStm(new VariableExpression("a")))))));

        IRepository repo5 = new Repository("log5.txt");
        Controller controller5 = new Controller(repo5, true);
        controller5.addProgram(statement5);

        /* Ref int v; new(v,20); Ref Ref int a; new(a,v); print(rH(v)); print(rH(rH(a))+5) */
        IStmt statement6= new CompStmt(new VariablesDeclarationStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocationStatement(new ValueExpression(new IntValue(20)),"v"),
                        new CompStmt(new VariablesDeclarationStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocationStatement(new VariableExpression("v"),"a"),
                                        new CompStmt(new PrintStm(new HeapReadExpression(new VariableExpression("v"))),
                                                new PrintStm(new ArithmeticalExpression(new HeapReadExpression(new HeapReadExpression(new VariableExpression("a"))),
                                                        ArithmeticalOperator.ADD,new ValueExpression(new IntValue(5)) )))))));

        IRepository repo6 = new Repository("log6.txt");
        Controller controller6 = new Controller(repo6, true);
        controller6.addProgram(statement6);

        /* Ref int v; new(v,20); print(rH(v)); wH(v,30); print(rH(v)+5); */
        IStmt statement7= new CompStmt(new VariablesDeclarationStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocationStatement(new ValueExpression(new IntValue(20)),"v"),
                        new CompStmt( new PrintStm(new HeapReadExpression(new VariableExpression("v"))),
                                new CompStmt(new HeapWriteStatement(new ValueExpression(new IntValue(30)),"v"),
                                        new PrintStm(new ArithmeticalExpression( new HeapReadExpression(new VariableExpression("v")), ArithmeticalOperator.ADD,new ValueExpression(new IntValue(5))))))));

        IRepository repo7 = new Repository("log7.txt");
        Controller controller7 = new Controller(repo7, true);
        controller7.addProgram(statement7);

        /* Ref int v; new(v,20); Ref Ref int a; new(a,v); new(v,30); print(rH(rH(a))) */
        IStmt statement8 = new CompStmt(new VariablesDeclarationStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocationStatement(new ValueExpression(new IntValue(20)),"v"),
                        new CompStmt(new VariablesDeclarationStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocationStatement( new VariableExpression("v"),"a"),
                                        new CompStmt(new HeapAllocationStatement(new ValueExpression(new IntValue(30)),"v"),
                                                new PrintStm(new HeapReadExpression(new HeapReadExpression(new VariableExpression("a")))))))));

        IRepository repo8 = new Repository("log8.txt");
        Controller controller8 = new Controller(repo8, true);
        controller8.addProgram(statement8);

        //int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStmt statement9 = new CompStmt(new VariablesDeclarationStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExpression(new IntValue(4))),
                        new CompStmt(new WhileStatement(new RelationalExpression(new VariableExpression("v"),">",
                                new ValueExpression(new IntValue(0))),
                                new CompStmt(new PrintStm(new VariableExpression("v")),
                                        new AssignStmt("v", new ArithmeticalExpression(new VariableExpression("v"),
                                                ArithmeticalOperator.SUBTRACT, new ValueExpression(new IntValue(1)))))),
                                new PrintStm(new VariableExpression("v")))));

        IRepository repo9 = new Repository("log9.txt");
        Controller controller9 = new Controller(repo9, true);
        controller9.addProgram(statement9);

        //Ref int a; Ref int b; new (a,20); b=a; new(a,30); print(rH(b)); print(rH(a))
        IStmt statement10 = new CompStmt(
                new VariablesDeclarationStmt("a", new RefType(new IntType())),
                new CompStmt(new VariablesDeclarationStmt("b", new RefType(new IntType())),
                        new CompStmt(new HeapAllocationStatement(new ValueExpression(new IntValue(20)), "a"),
                                new CompStmt(new AssignStmt("b", new VariableExpression("a")),
                                        new CompStmt(new HeapAllocationStatement(new ValueExpression(new IntValue(30)), "a"),
                                                new CompStmt(new PrintStm(new HeapReadExpression(new VariableExpression("b"))),
                                                        new PrintStm(new HeapReadExpression(new VariableExpression("a")))
                                                )
                                        )
                                )
                        )
                )
        );

        IRepository repo10 = new Repository("log10.txt");
        Controller controller10 = new Controller(repo10, true);
        controller10.addProgram(statement10);


        TextMenu menu = new TextMenu();
        menu.addCommand(new RunExampleCommand("1",statement1.toString() , controller1));
        menu.addCommand(new RunExampleCommand("2",statement2.toString() , controller2));
        menu.addCommand(new RunExampleCommand("3",statement3.toString() , controller3));
        menu.addCommand(new RunExampleCommand("4",statement4.toString() , controller4));
        menu.addCommand(new RunExampleCommand("5",statement5.toString() , controller5));
        menu.addCommand(new RunExampleCommand("6",statement6.toString() , controller6));
        menu.addCommand(new RunExampleCommand("7",statement7.toString() , controller7));
        menu.addCommand(new RunExampleCommand("8",statement8.toString() , controller8));
        menu.addCommand(new RunExampleCommand("9",statement9.toString() , controller9));
        menu.addCommand(new RunExampleCommand("10",statement10.toString() , controller10));
        menu.addCommand(new ExitCommand("11" , "Exit"));
        menu.show();

    }

}//Ref int v;new(v,20);new(v,30);print(rH(v))
