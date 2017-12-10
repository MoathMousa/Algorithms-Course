import java.io.File;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Driver extends Application{
	
	static BorderPane root = new BorderPane();
	static Scene scene = new Scene(root,400,400);
	static Stage stage1;
	static int LENGTH = 20;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage1=primaryStage;
		scene=showFirstStage();
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static Scene showFirstStage() {

		final Label projTwoL = new Label("Project Two");
		final Label welcomeL = new Label("Find  text files similarity");
		
		Button LoadPatternBt = new Button("Load Pattern File");
		LoadPatternBt.setMinWidth(180);
		LoadPatternBt.setOnAction(e ->{
			try {
				FileChooser choose = new FileChooser();
				choose.setTitle("choose a file");
				File file = choose.showOpenDialog(new Stage());
				if (file != null) {
				Similarity.LoadPatternFile(file);
			} }catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		Button buildPatternBt = new Button("Build Pattern");
		buildPatternBt.setMinWidth(180);
		buildPatternBt.setOnAction(e -> {
			Similarity.BuildPattern(Similarity.I);
		});
		
		
		Button loadTextFilesBt = new Button("Load Text Files");
		loadTextFilesBt.setMinWidth(180);
		loadTextFilesBt.setOnAction(e -> {
			Similarity.LoadTextFiles();
		});
		
		Button resultBt = new Button("Show Pattern Table & Result");
		resultBt.setMinWidth(120);
		resultBt.setOnAction(e -> {
			getResultStage();
		});
		
		Button exitBt = new Button("Exit");
		exitBt.setMinWidth(120);
		exitBt.setOnAction(e -> {
			stage1.close();
		});
		
		VBox vLine = new VBox(25);
		vLine.getChildren().addAll(projTwoL,welcomeL,LoadPatternBt,buildPatternBt,
				loadTextFilesBt,resultBt,exitBt);
		vLine.setAlignment(Pos.CENTER);
		root.setCenter(vLine);
		return scene;
		
	}
	
	public static void getResultStage() {
		try {
		Similarity.Search(); } catch(Exception ex) { }
		BorderPane root2 = new BorderPane();
		Stage ss = new Stage();
		HBox paneRow1 = new HBox(0);
		HBox paneRow2 = new HBox(0);
		HBox paneRow3 = new HBox(0);
		HBox paneRow4 = new HBox(0);
		HBox paneRow5 = new HBox(0);
		VBox resultvB = new VBox(10);
			
		for (int i = 1 ; i < Similarity.rowsChar.length; i++)
			for(int j = 0; j < Similarity.onceOccurence.length;i++)
			paneRow1.getChildren().add(new LabelCreation(String.valueOf(Similarity.patternTable[i][j]), LENGTH, LENGTH/2));
		
		Label OverAllL = new Label("Over all similarity = " + Similarity.CalculateOverSimilarity()+ "%");
		OverAllL.setMinWidth(500);
		OverAllL.setFont(Font.font("GEORGIA", 20));
	
//		Label bestSeqL = new Label("The best sequence is: " + optSequence);
//		bestSeqL.setFont(Font.font("GEORGIA", 20));
//		bestSeqL.setMinWidth(500);

		Button exitBt = new Button("Exit");
		exitBt.setMinWidth(120);
		exitBt.setOnAction(e -> {
			ss.close();
		});

		VBox vB = new VBox(10);
		resultvB.getChildren().addAll(OverAllL);
		vB.getChildren().addAll(paneRow1,paneRow2,paneRow3,paneRow4,paneRow5,resultvB,exitBt);
		ss.setTitle("Display Result");
		root2.getChildren().add(vB);
		Scene scene2 = new Scene(root2,(20+ 2)*LENGTH + 50,500);
		ss.setScene(scene2);
		ss.show();


	}

}
