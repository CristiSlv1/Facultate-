package view.gui.selectwindow;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.adt.MyDictionary;
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
import view.gui.executewindow.ExecuteStatementController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SelectStatementController implements Initializable {

    //ATTRIBUTES
    private ExecuteStatementController executeController;
    IStmt selectedStatement;
    List<IStmt> statements = new ArrayList<>();

    //FXML ATRIBUTES
    @FXML
    private ListView<IStmt> statementsListView;

    @FXML
    private Label selectStatementLabel;

    @FXML
    private Button executeButton;

    //METHODS
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateStatements();
        for(IStmt statement : statements)
        {
            statementsListView.getItems().add(statement);
        }
        this.statementsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.selectedStatement = statementsListView.getSelectionModel().getSelectedItem();
            this.selectStatementLabel.setText("Selected statement: " + this.selectedStatement);
        });
    }

    public void setExecuteController(ExecuteStatementController executeController) {
        this.executeController = executeController;
    }

    @FXML
    private void handleExecuteButtonAction(ActionEvent event) {
        int selectedStmtIndex = statementsListView.getSelectionModel().getSelectedIndex();
        if(selectedStmtIndex == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No statement selected");
            alert.setContentText("Please select a statement from the list.");
            alert.showAndWait();
        }
        else {
            IStmt selectedStatement = statements.get(selectedStmtIndex);
            selectedStmtIndex++;
            IRepository repository = new Repository("log" + selectedStmtIndex + ".txt");
            Controller controller = new Controller(repository);
            controller.addProgram(selectedStatement);
            showExecutionWindow(selectedStatement,controller);
        }
    }


    private void showExecutionWindow(IStmt selectedStatement,Controller controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/gui/executewindow/ExecuteStatementWindow.fxml"));
            Parent root = loader.load();

            selectedStatement.typeCheck(new MyDictionary<>());

            ExecuteStatementController executeStatementController = loader.getController();
            executeStatementController.setController(controller);
            executeStatementController.initialize(selectedStatement);

            Stage stage = new Stage();
            stage.setTitle("Execute Statement");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void populateStatements()
    {
        IStmt statement1 = new CompStmt(new VariablesDeclarationStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExpression(new IntValue(2))),
                        new PrintStm(new VariableExpression("v"))));
        statements.add(statement1);

        // int a; int b; a=2+3*5; b=a+1; Print(b)
        IStmt statement2 = new CompStmt(new VariablesDeclarationStmt("a",new IntType()),
                new CompStmt(new VariablesDeclarationStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithmeticalExpression(new ValueExpression(new IntValue(2)), ArithmeticalOperator.ADD,new
                                ArithmeticalExpression(new ValueExpression(new IntValue(3)),ArithmeticalOperator.MULTIPLY,new ValueExpression(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithmeticalExpression(new VariableExpression("a"), ArithmeticalOperator.ADD,new ValueExpression(new
                                        IntValue(1)))), new PrintStm(new VariableExpression("b"))))));
        statements.add(statement2);

        // bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v)
        IStmt statement3 = new CompStmt(new VariablesDeclarationStmt("a", new BoolType()),
                new CompStmt(new VariablesDeclarationStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExpression(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VariableExpression("a"),
                                        new AssignStmt("v", new ValueExpression(new IntValue(2))),
                                        new AssignStmt("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStm(new VariableExpression("v"))))));

        statements.add(statement3);

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


        statements.add(statement4);

        /* Ref int v; new(v,20); Ref Ref int a; new(a,v); print(v); print(a) */
        IStmt statement5 = new CompStmt(new VariablesDeclarationStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocationStatement(new ValueExpression(new IntValue(20)),"v"),
                        new CompStmt(new VariablesDeclarationStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocationStatement(new VariableExpression("v"),"a"),
                                        new CompStmt(new PrintStm(new VariableExpression("v")), new PrintStm(new VariableExpression("a")))))));

        statements.add(statement5);

        /* Ref int v; new(v,20); Ref Ref int a; new(a,v); print(rH(v)); print(rH(rH(a))+5) */
        IStmt statement6= new CompStmt(new VariablesDeclarationStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocationStatement(new ValueExpression(new IntValue(20)),"v"),
                        new CompStmt(new VariablesDeclarationStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocationStatement(new VariableExpression("v"),"a"),
                                        new CompStmt(new PrintStm(new HeapReadExpression(new VariableExpression("v"))),
                                                new PrintStm(new ArithmeticalExpression(new HeapReadExpression(new HeapReadExpression(new VariableExpression("a"))),
                                                        ArithmeticalOperator.ADD,new ValueExpression(new IntValue(5)) )))))));

        statements.add(statement6);

        /* Ref int v; new(v,20); print(rH(v)); wH(v,30); print(rH(v)+5); */
        IStmt statement7= new CompStmt(new VariablesDeclarationStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocationStatement(new ValueExpression(new IntValue(20)),"v"),
                        new CompStmt( new PrintStm(new HeapReadExpression(new VariableExpression("v"))),
                                new CompStmt(new HeapWriteStatement(new ValueExpression(new IntValue(30)),"v"),
                                        new PrintStm(new ArithmeticalExpression( new HeapReadExpression(new VariableExpression("v")), ArithmeticalOperator.ADD,new ValueExpression(new IntValue(5))))))));

        statements.add(statement7);

        /* Ref int v; new(v,20); Ref Ref int a; new(a,v); new(v,30); print(rH(rH(a))) */
        IStmt statement8 = new CompStmt(new VariablesDeclarationStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocationStatement(new ValueExpression(new IntValue(20)),"v"),
                        new CompStmt(new VariablesDeclarationStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocationStatement( new VariableExpression("v"),"a"),
                                        new CompStmt(new HeapAllocationStatement(new ValueExpression(new IntValue(30)),"v"),
                                                new PrintStm(new HeapReadExpression(new HeapReadExpression(new VariableExpression("a")))))))));

        statements.add(statement8);

        //int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStmt statement9 = new CompStmt(new VariablesDeclarationStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExpression(new IntValue(4))),
                        new CompStmt(new WhileStatement(new RelationalExpression(new VariableExpression("v"),">",
                                new ValueExpression(new IntValue(0))),
                                new CompStmt(new PrintStm(new VariableExpression("v")),
                                        new AssignStmt("v", new ArithmeticalExpression(new VariableExpression("v"),
                                                ArithmeticalOperator.SUBTRACT, new ValueExpression(new IntValue(1)))))),
                                new PrintStm(new VariableExpression("v")))));

        statements.add(statement9);

        // int v; Ref int a; v=10; new(a,22); fork(wH(a,30); v=32; print(v); print(rH(a))); print(v); print(rH(a))
        IStmt statement10 = new CompStmt(new VariablesDeclarationStmt("v", new IntType()),
                new CompStmt(new VariablesDeclarationStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExpression(new IntValue(10))),
                                new CompStmt(new HeapAllocationStatement( new ValueExpression(new IntValue(22)),"a"),
                                        new CompStmt(new ForkStatement(new CompStmt(new HeapWriteStatement(new ValueExpression(new IntValue(30)),"a"),
                                                new CompStmt(new AssignStmt("v", new ValueExpression(new IntValue(32))),
                                                        new CompStmt(new PrintStm(new VariableExpression("v")), new PrintStm(new HeapReadExpression(new VariableExpression("a"))))))),
                                                new CompStmt(new PrintStm(new VariableExpression("v")), new PrintStm(new HeapReadExpression(new VariableExpression("a")))))))));


        statements.add(statement10);

    }


}