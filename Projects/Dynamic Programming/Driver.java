import java.io.File;
import java.io.FileNotFoundException;

import com.sun.javafx.css.CalculatedValue;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Driver extends Application {
	
	static BorderPane root = new BorderPane();
	static Scene scene = new Scene(root,400,400);
	static Stage stage1;
	static int LENGTH = 100;

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage1=primaryStage;
		scene=showFirstStage();
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		launch(args);
		
	}
	
	public static Scene showFirstStage() {

		final Label projOneL = new Label("Project One");
		final Label welcomeL = new Label("This program can find minimum cost and best sequence of routers");
		
		Button loadBt = new Button("Load your file");
		loadBt.setMinWidth(120);
		loadBt.setOnAction(e ->{
			try {
				FileChooser chose = new FileChooser();
				chose.setTitle("choose a file");
				File file = chose.showOpenDialog(new Stage());
				if (file != null) {
				CostCalculation.load(file);
			} }catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		Button calcBt = new Button("Calculate Min Cost");
		calcBt.setMinWidth(120);
		calcBt.setOnAction(e -> {
			getResultStage();
		});
		
		Button exitBt = new Button("Exit");
		exitBt.setMinWidth(120);
		exitBt.setOnAction(e -> {
			stage1.close();
		});
		
		VBox vLine = new VBox(25);
		vLine.getChildren().addAll(projOneL,welcomeL,loadBt,calcBt,exitBt);
		vLine.setAlignment(Pos.CENTER);
		root.setCenter(vLine);
		return scene;
		
	}
	
	public static void getResultStage() {
		try {
		CostCalculation.calculate(); } catch(Exception ex) { }
		BorderPane root2 = new BorderPane();
		Stage ss = new Stage();
		HBox paneRow1 = new HBox(0);
		HBox paneRow2 = new HBox(0);
		HBox paneRow3 = new HBox(0);
		HBox paneRow4 = new HBox(0);
		HBox paneRow5 = new HBox(0);
		VBox resultvB = new VBox(10);
		
		
		for (int i = 1; i <= CostCalculation.SIZE; i++)
			paneRow1.getChildren().add(new LabelCreation(String.valueOf(CostCalculation.prices[i]), LENGTH, LENGTH/2));

		for (int i = 1; i <= CostCalculation.SIZE; i++)
			paneRow2.getChildren().add(new LabelCreation(String.valueOf(CostCalculation.t[i]), LENGTH, LENGTH/2));

		for (int i = 1; i <= CostCalculation.SIZE; i++)
			paneRow3.getChildren().add(new LabelCreation(String.valueOf(CostCalculation.f[i]), LENGTH, LENGTH/2));
		
		for (int i = 1; i <= CostCalculation.SIZE; i++)
			paneRow4.getChildren().add(new LabelCreation(String.valueOf(CostCalculation.ts[i]), LENGTH, LENGTH/2));
		
		for (int i = 1; i <= CostCalculation.SIZE; i++)
			paneRow5.getChildren().add(new LabelCreation(String.valueOf(CostCalculation.fs[i]), LENGTH, LENGTH/2));
		
		Label minCostL = new Label("The Minimum cost is: " + CostCalculation.f[CostCalculation.SIZE]+ " $");
		minCostL.setMinWidth(500);
		minCostL.setFont(Font.font("GEORGIA", 20));
		String optSequence = CostCalculation.fs[CostCalculation.SIZE].charAt(0) == '0' ?
				CostCalculation.fs[CostCalculation.SIZE].substring(2) : CostCalculation.fs[CostCalculation.SIZE];
		Label bestSeqL = new Label("The best sequence is: " + optSequence);
		bestSeqL.setFont(Font.font("GEORGIA", 20));
		bestSeqL.setMinWidth(500);

		Button exitBt = new Button("Exit");
		exitBt.setMinWidth(120);
		exitBt.setOnAction(e -> {
			ss.close();
		});

		VBox vB = new VBox(10);
		resultvB.getChildren().addAll(minCostL,bestSeqL);
		vB.getChildren().addAll(paneRow1,paneRow2,paneRow3,paneRow4,paneRow5,resultvB,exitBt);
		ss.setTitle("Display Result");
		root2.getChildren().add(vB);
		Scene scene2 = new Scene(root2,(CostCalculation.SIZE + 2)*LENGTH + 50,500);
		ss.setScene(scene2);
		ss.show();


	}

}