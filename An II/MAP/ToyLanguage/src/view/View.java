package view;

import java.util.Scanner;

import controller.Controller;
import exceptions.EmptyStackException;
import model.expressions.ArithmeticalExpression;
import model.expressions.ArithmeticalOperator;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.statements.*;
import model.types.BoolIType;
import model.types.IntIType;
import model.values.BoolValue;
import model.values.IntIValue;


public class View {
    private final Controller controller;

    public View(Controller c){
        this.controller = c;
    }

    public void run(){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Select a program to run (1, 2, or 3 ):");
    int choice = scanner.nextInt();

    try {
        switch (choice) {
            case 1 -> runProgram1();
            case 2 -> runProgram2();
            case 3 -> runProgram3();
            //case 4 -> { runProgram1(); runProgram2(); runProgram3(); }
            default -> System.out.println("Invalid choice. Please select 1, 2, or 3.");
        }
    } catch (EmptyStackException e) {
        scanner.close();
        throw new RuntimeException(e);
    }
    scanner.close();
}

    private void runProgram1() throws EmptyStackException{
        IStmt statement = new CompStmt(new VariableDeclarationStmt("v", new IntIType()),
                new CompStmt(new AssignStmt("v", new ValueExpression(new IntIValue(2))),
                        new PrintStmt(new VariableExpression("v"))));

        runStatement(statement);
    }

    private void runProgram2() throws EmptyStackException {
        IStmt statement = new CompStmt(new VariableDeclarationStmt("a",new IntIType()),
                new CompStmt(new VariableDeclarationStmt("b",new IntIType()),
                        new CompStmt(new AssignStmt("a", new ArithmeticalExpression(new ValueExpression(new IntIValue(2)),ArithmeticalOperator.ADD,new
                                ArithmeticalExpression(new ValueExpression(new IntIValue(3)),ArithmeticalOperator.MULTIPLY,new ValueExpression(new IntIValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithmeticalExpression(new VariableExpression("a"), ArithmeticalOperator.ADD,new ValueExpression(new
                                        IntIValue(1)))), new PrintStmt(new VariableExpression("b"))))));
        runStatement(statement);
    }


    private void runProgram3() throws EmptyStackException{
        IStmt statement = new CompStmt(new VariableDeclarationStmt("a", new BoolIType()),
                new CompStmt(new VariableDeclarationStmt("v", new IntIType()),
                        new CompStmt(new AssignStmt("a", new ValueExpression(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VariableExpression("a"),
                                        new AssignStmt("v", new ValueExpression(new IntIValue(2))),
                                        new AssignStmt("v", new ValueExpression(new IntIValue(3)))),
                                        new PrintStmt(new VariableExpression("v"))))));
        runStatement(statement);
    }

    private void runStatement(IStmt statement) throws EmptyStackException{
        this.controller.addProgram(statement);

        controller.executeAllSteps();
        System.out.println("Result: " + controller.getRepository().getCurrentProgram().toString());
    }
}
