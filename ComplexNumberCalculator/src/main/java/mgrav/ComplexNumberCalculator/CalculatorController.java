package mgrav.ComplexNumberCalculator;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorController {
    @FXML
    Label visioneSchermo= new Label();
    @FXML
    Button buttonVariabili;
    @FXML
    Button buttonMultiplication= new Button();
    @FXML
    Button buttonSlash= new Button();
    @FXML
    Button buttonSQRT= new Button();
    @FXML
    Button buttonSwitchSign= new Button();
    @FXML
    Button buttonC= new Button();
    @FXML
    Button buttonAC= new Button();
    @FXML
    TableView<ComplexNumber> stackTable;
    @FXML
    TableColumn<ComplexNumber, String> stackClm;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static String labelTextToSave;
    private StackManagement stack= new StackManagement();
    private Map<Character, ComplexNumber> mapVariables= new HashMap<>();
    private ObservableList<ComplexNumber> stackList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        buttonC.setDisable(visioneSchermo.getText().equals("0"));
        buttonAC.setDisable(visioneSchermo.getText().equals("0"));
        buttonVariabili.setDisable(true);
        buttonSwitchSign.setDisable(stack.isEmpty());
        buttonSlash.setDisable(stack.lengthStack()<2);
        buttonSQRT.setDisable(stack.isEmpty());
        buttonMultiplication.setDisable(stack.lengthStack()<2);
        //If the table is null, the method creates a new one
        if (stackTable == null) {
            stackTable = new TableView<>();
            stackClm = new TableColumn<>();
        }
        stackClm.setCellValueFactory(new PropertyValueFactory("formattedComplexNumber"));
        stackTable.setItems(stackList);
        updateStackTable();
        //Disables horizontal scrolling on stack table
        stackTable.addEventFilter(ScrollEvent.ANY, event->{
            if(event.getDeltaX()!=0){
                event.consume();
            }
        });
        //Enables some buttons if the label contains the required elements
        visioneSchermo.textProperty().addListener((observable, oldValue, newValue)->{
            buttonVariabili.setDisable(!newValue.matches("[+\\-<>]"));
            buttonC.setDisable(visioneSchermo.getText().equals("0"));
            buttonAC.setDisable(visioneSchermo.getText().equals("0"));
        });
        //Enables some operations if the table contains enough elements
        stackTable.getItems().addListener((ListChangeListener<ComplexNumber>)c->{
            buttonMultiplication.setDisable(stack.lengthStack()<2);
            buttonSlash.setDisable(stack.lengthStack()<2);
            buttonSQRT.setDisable(stack.isEmpty());
            buttonSwitchSign.setDisable(stack.isEmpty());
        });
    }

    /*Constructor used to initialize the controller when the application starts*/
    public  CalculatorController(){
    }

    /*Constructor used to initialize the controller in order to save the stack when the user switches to another scene*/
    private CalculatorController(StackManagement stack, Map<Character, ComplexNumber> map){
        this.stack= stack;
        stack.updateList(stackList);
        this.mapVariables= map;
        visioneSchermo.setText("0");
    }


    /*This method updates the table in order to instantly view every element inserted*/
    private void updateStackTable() {
        stack.updateList(stackList);
        stackTable.refresh();
    }
    private void setLabelText(String text) {
        visioneSchermo.setText(text);
    }

    /*The following method is used to verify if the label text matches to a variable*/
    private boolean matcherVariables(){
        Pattern pattern= Pattern.compile("[+\\-<>]+[a-z]");
        Matcher matcher= pattern.matcher(visioneSchermo.getText());
        return matcher.matches();
    }

    /*The next methods are used to switch from a scene to another one, each of them invokes the switchScene method*/
    public void switchToOtherOperationDisplay(ActionEvent event) {
        if(visioneSchermo.getText()!=null)
            switchScene("OtherOperations.fxml", event, visioneSchermo.getText());
        else
            switchScene("OtherOperations.fxml", event, labelTextToSave);
    }
    public void switchToCalculatorDisplay(ActionEvent event) {
        if(visioneSchermo!=null)
            switchScene("Calculator.fxml", event, visioneSchermo.getText());
        else
            switchScene("Calculator.fxml", event, labelTextToSave);
    }
    public void switchToVariables1Display(ActionEvent event) {
        if(visioneSchermo!=null)
            switchScene("Variables1.fxml", event, visioneSchermo.getText());
        else
            switchScene("Variables1.fxml", event, labelTextToSave);
    }
    public void switchToVariables2Display(ActionEvent event){
        if(visioneSchermo!=null)
            switchScene("Variables2.fxml", event, visioneSchermo.getText());
        else
            switchScene("Variables2.fxml", event, labelTextToSave);
    }
    public void switchToStack(ActionEvent event) {
        switchScene("Stack.fxml", event, visioneSchermo.getText());
    }

    /*This method creates a new controller using the fxml file required*/
    private void switchScene(String fxmlFile, ActionEvent event, String labelText) {
        try {
            //creates a new controller by passing the existing stack in order to save the elements during the switch
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            loader.setControllerFactory(param -> new CalculatorController(stack, mapVariables));
            root = loader.load();
            if(fxmlFile.equals("Stack.fxml")){
                labelTextToSave= labelText;
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.getScene().setRoot(root);
                stage.show();
            }
            else{
                CalculatorController newController = loader.getController();
                newController.setLabelText(labelText);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.getScene().setRoot(root);
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void inserimentoNumero(ActionEvent actionEvent) {
        Button bottone= (Button) actionEvent.getSource();
        if(visioneSchermo.getText().equals("0") || visioneSchermo.getText().contains("j"))
            visioneSchermo.setText(bottone.getText());
        else
            visioneSchermo.setText(visioneSchermo.getText() + bottone.getText());
    }
    public void inserimentoVariabile(ActionEvent actionEvent) {
        Button bottone= (Button) actionEvent.getSource();
        if(visioneSchermo.getText().equals("0"))
            visioneSchermo.setText(bottone.getText());
        else
            visioneSchermo.setText(visioneSchermo.getText() + bottone.getText());
    }

    public void cancellaSingoloInserimento(ActionEvent actionEvent) {
        int lenghtLabelText=visioneSchermo.getText().length();
        if(lenghtLabelText>1)
            visioneSchermo.setText(visioneSchermo.getText().substring(0,lenghtLabelText-1));
        else
            visioneSchermo.setText("0");
    }
    public void cancellaNumero(ActionEvent actionEvent) {
        visioneSchermo.setText("0");
    }

    public void enter(ActionEvent actionEvent) {
        //If the text is a variable, the method variableOperations is invoked
        if(matcherVariables())
            variableOperations();
        //If the text is only "+", the system performs an addition
        else if(visioneSchermo.getText().equals("+")){
            ComplexNumber a=stack.pop();
            ComplexNumber b=stack.pop();
            b=b.add(a);
            visioneSchermo.setText(b.toString());
            stack.push(b);
        }
        //If the text is only "-", the system performs a subtraction
        else if(visioneSchermo.getText().equals("-")){
            ComplexNumber a=stack.pop();
            ComplexNumber b=stack.pop();
            b=b.sub(a);
            visioneSchermo.setText(b.toString());
            stack.push(b);
        }
        else {
            double realPart = 0;
            double imagPart = 0;
            //If the label contains "+" or "-" there complex number has both real and imaginary part
            if((visioneSchermo.getText().contains("+")|| visioneSchermo.getText().contains("-"))&&(!visioneSchermo.getText().equals("+J")&&!visioneSchermo.getText().equals("-J"))){
                ComplexNumber complexNumber;
                String input;
                if(visioneSchermo.getText().endsWith("+J")||visioneSchermo.getText().endsWith(("-J")))
                    input= visioneSchermo.getText().replace("J", "1");
                else
                    input = visioneSchermo.getText().replace("J", "");
                String[] parts = input.split("(?=[+-])");
                // Handles the first term alone
                if (parts.length > 0) {
                    realPart = Double.parseDouble(parts[0]);
                }
                // Handles the other elements
                for (int i = 1; i < parts.length; i++) {
                    if (parts[i].endsWith("+") || parts[i].endsWith("-")) {
                        // If the last digit ends with "+" or "-", the system considers the part as real
                        realPart += Double.parseDouble(parts[i]);
                    } else {
                        // Else, it considers the part as imaginary
                        imagPart += Double.parseDouble(parts[i]);
                    }
                }
                complexNumber = new ComplexNumber(realPart, imagPart);
                stack.push(complexNumber);
                updateStackTable();
                visioneSchermo.setText("0");
            }else{
                //If the input is j, 0+1j is pushed
                if(visioneSchermo.getText().equals("J")||visioneSchermo.getText().equals("+J")){
                    imagPart= 1;
                }
                //If the input is -j, 0-1j is pushed
                else if(visioneSchermo.getText().equals("-J")){
                    imagPart= -1;
                }
                //If the last digit is j, the real part is null
                else if(visioneSchermo.getText().contains("J")){
                    String input = visioneSchermo.getText().replace("J", "");
                    imagPart = Double.parseDouble(input);
                }
                //Else the imaginary part is null
                else{
                    realPart= Double.parseDouble(visioneSchermo.getText());
                }
                ComplexNumber complexNumber= new ComplexNumber(realPart, imagPart);
                stack.push(complexNumber);
                updateStackTable();
                visioneSchermo.setText("0");
            }
        }
    }

    /*This method performs the division and the multiplication*/
    public void operazione(ActionEvent actionEvent){
        if(stack.lengthStack()<2)
            inserimentoNumero(actionEvent);
        else {
            Button bottone= (Button) actionEvent.getSource();
            ComplexNumber a=stack.pop();
            ComplexNumber b=stack.pop();
            switch (bottone.getText()) {
                case "*":
                    b=b.molt(a);
                    break;
                case "/":
                    b=b.div(a);
                    break;
            }
            visioneSchermo.setText(b.toString());
            stack.push(b);
        }
    }

    /*This method performs the square root and the sign inversion*/
    public void altraOperazione(ActionEvent actionEvent) {
        ComplexNumber a=stack.pop();
        Button bottone = (Button) actionEvent.getSource();
        switch (bottone.getText()){
            case "√":
                a=a.sqrt();
                break;
            case "+/-":
                a=a.invSign();
                break;
        }
        visioneSchermo.setText(a.toString());
        stack.push(a);
    }

    /*This method is used to handle the variables, using the Hash Map as structure*/
    public void variableOperations(){
        char operator= visioneSchermo.getText().charAt(0);
        char variable= visioneSchermo.getText().charAt(1);
        if(operator=='>'){
            mapVariables.put(variable, stack.pop());
        }
        if(visioneSchermo.getText().charAt(0)==('<'))
            stack.push(mapVariables.get(visioneSchermo.getText().charAt(1)));
        if(visioneSchermo.getText().charAt(0)==('+'))
            mapVariables.put(variable, stack.pop().add(mapVariables.get(variable)));
        if(visioneSchermo.getText().charAt(0)==('-'))
            mapVariables.put(variable, mapVariables.get(variable).sub(stack.pop()));
        visioneSchermo.setText("0");
    }

    /*This method performs the operations of the stack*/
    public void controlStack(ActionEvent actionEvent) {
        Button bottone= (Button) actionEvent.getSource();
        if(bottone.getText().equals("CLEAR"))
            stack.clear();
        if(bottone.getText().equals("DROP"))
            stack.drop();
        if(bottone.getText().equals("DUP"))
            stack.duplicate();
        if(bottone.getText().equals("SWAP"))
            stack.swap();
        if(bottone.getText().equals("OVER"))
            stack.over();
        updateStackTable();
    }
}