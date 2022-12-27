package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;
import javax.swing.text.StyledDocument;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {
	private static String zz = "";
	private static double ss = 0;
	private static double ss1 = 0;
private static double moath=0;
	private static String zz2 = "";
	private static double ss2 = 0;
	private static double ss12 = 0;
	private static int i = 0;
	private static double ridi =0;
	 private static double tayseer=0;
	 private static double ahmad=0;
	 private static double weam=0;
	private static boolean sceneFlag = false;
	private static File refresh = new File("Refresh.txt");
	private static StringBuilder area = new StringBuilder();

	private static ArrayList<Bill> billNumber = new ArrayList<>();
	private static ArrayList<Bill2> billNumber2 = new ArrayList<>();
	private static ArrayList<Accounts> account = new ArrayList<>();
	private static ArrayList<Inputs> input = new ArrayList<>();
	private static ArrayList<Label> labelList = new ArrayList<Label>();

	static BorderPane dataPage = new BorderPane();

	static Scene scene1 = new Scene(dataPage, 1700, 800);

	static BorderPane dataPageA = new BorderPane();
	static Scene scene1A = new Scene(dataPageA, 1700, 800);

	static BorderPane logInPane = new BorderPane();
	static Scene scene = new Scene(logInPane, 1540, 800);

	private static ToggleGroup ttg = new ToggleGroup();

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("ACCOUNTING SYSTEM");
		File userName = new File("Users.txt");
		File kashef = new File("Kashfhesab.txt");

		File fat = new File("fatora.txt");
		File fat2 = new File("fatora2.txt");

		if (!fat.exists()) {
			fat.createNewFile();
		}

		if (!fat2.exists()) {
			fat2.createNewFile();
		}

		if (!kashef.exists()) {
			kashef.createNewFile();
		}
		if (!userName.exists()) {
			userName.createNewFile();
		}

		if (!refresh.exists()) {
			refresh.createNewFile();
		}

		creatAccounts();
		refreshAccounts();
		fillSanads();

		FileWriter z = new FileWriter(fat, true);
		PrintWriter bi = new PrintWriter(z);

		FileWriter z2 = new FileWriter(fat2, true);
		PrintWriter bi2 = new PrintWriter(z2);

		Scanner infm = new Scanner(fat);
		Scanner infm2 = new Scanner(fat2);

		while (infm.hasNextLine()) {

			String a = infm.nextLine();
			a = a.replaceAll("[\\[\\](){} ]", "");

			String[] vvz = a.split(":");
			for (int i = 0; i < vvz.length; i++) {
			}
			try {
				String m1 = vvz[0];
				String m2 = vvz[1];
				String m3 = vvz[2];
				String m4 = vvz[3];
				String m55 = vvz[4];
				String m6 = vvz[5];
			
				billNumber.add(new Bill(m1, m2, m3, m4, m55, m6));
			} catch (Exception v) {

			}
		}
		infm.close();
		while (infm2.hasNextLine()) {
			String a = infm2.nextLine();
			a = a.replaceAll("[\\[\\](){} ]", "");

			String[] vvz = a.split(":");
			String m1 = vvz[0];
			String m2 = vvz[1];
			String m3 = vvz[2];
			String m4 = vvz[3];
			String m55 = vvz[4];
			String m6 = vvz[5];

			billNumber2.add(new Bill2(m1, m2, m3, m4, m55, m6));
		}
		infm2.close();

		labelList.add(new Label("(110) Current Assets"));
		labelList.add(new Label("(111) Cash"));
		labelList.add(new Label("(112) Account Recevible"));
		labelList.add(new Label("(113) Supplies"));
		labelList.add(new Label("(114) Prepaid Insurance"));
		labelList.add(new Label("(120) Fixed Assets"));
		labelList.add(new Label("(121) Machinery"));
		labelList.add(new Label("(122) Furniture"));
		labelList.add(new Label("(123) Vehicles"));
		labelList.add(new Label("(124) Buildings"));
		labelList.add(new Label("(1000) Accumulated Depreciation"));
		labelList.add(new Label("(2000) Income summary"));// 11
		// for Liability only
		labelList.add(new Label("(210) Current Liability")); // 12
		labelList.add(new Label("(211) Account Payable"));
		labelList.add(new Label("(212) Unearned Service Revenue"));
		labelList.add(new Label("(213) Salaries and wages Payable"));
		labelList.add(new Label("(214) Interest Payable"));
		labelList.add(new Label("(220) Value Added Tax Provision"));
		labelList.add(new Label("(230) Long Term Debt"));// 18

		// for Equity

		labelList.add(new Label("(310) Capital"));
		labelList.add(new Label("(320) Retained Earnings"));
		labelList.add(new Label("(330) Current Year Earning"));
		labelList.add(new Label("(340) Drawings"));

		// for revenue
		labelList.add(new Label("(410) Revenue"));// 23

		// for expense
		// expense
		labelList.add(new Label("(510) Salaries and wages expense"));
		labelList.add(new Label("(520) Insuranse expense"));
		labelList.add(new Label("(530) Depreciation Expense"));
		labelList.add(new Label("(540) General Purchase Expense"));
		labelList.add(new Label("(550) Utility expense"));

		labelList.add(new Label("(1)Assets\n__________"));
		labelList.add(new Label("(2)Laibility\n__________"));
		labelList.add(new Label("(3) Owner Equity\n__________"));
		labelList.add(new Label("(4)Revenue\n__________"));
		labelList.add(new Label("(5)Expense\n__________"));

		try {

			BorderPane p = new BorderPane();
			Scene sel = new Scene(p, 1700, 800);

			BorderPane pn = new BorderPane();
			Scene seln = new Scene(pn, 1700, 800);

			BorderPane pna = new BorderPane();
			Scene selna = new Scene(pna, 1700, 800);

			BorderPane px = new BorderPane();
			Scene selx = new Scene(px, 1700, 800);

			BorderPane px1 = new BorderPane();
			Scene selx1 = new Scene(px1, 1700, 800);

			BorderPane px1a = new BorderPane();
			Scene selx1a = new Scene(px1a, 1700, 800);

			BorderPane px2 = new BorderPane();
			Scene selx2 = new Scene(px2, 1700, 800);

			BorderPane px2aa = new BorderPane();
			Scene selx2aa = new Scene(px2aa, 1700, 800);

			BorderPane pa = new BorderPane();
			Scene sela = new Scene(pa, 1700, 800);

			BorderPane pxa = new BorderPane();
			Scene selxa = new Scene(pxa, 1700, 800);

			BorderPane Jornalsch = new BorderPane();
			Scene Jornalschs = new Scene(Jornalsch, 1700, 800);

			BorderPane Jornalsch1 = new BorderPane();
			Scene Jornalschs1 = new Scene(Jornalsch1, 1700, 800);

			BorderPane Jornalsch2 = new BorderPane();
			Scene Jornalschs2 = new Scene(Jornalsch2, 1700, 800);

			BorderPane Jornalsch11 = new BorderPane();
			Scene Jornalschs11 = new Scene(Jornalsch11, 1540, 800);

			BorderPane xq = new BorderPane();
			Scene xs = new Scene(xq, 1700, 800);

			BorderPane xqa = new BorderPane();
			Scene xsa = new Scene(xqa, 1700, 800);

			BorderPane qq = new BorderPane();
			Scene qqs = new Scene(qq, 1700, 800);
			BorderPane qqa = new BorderPane();
			Scene qqsa = new Scene(qqa, 1700, 800);

			BorderPane tar = new BorderPane();
			Scene tars = new Scene(tar, 1540, 800);

			BorderPane tara = new BorderPane();
			Scene tarsa = new Scene(tara, 1540, 800);

			BorderPane Jornalscha = new BorderPane();
			Scene Jornalschsa = new Scene(Jornalscha, 1700, 800);

			BorderPane Jornalsch1a = new BorderPane();
			Scene Jornalschs1a = new Scene(Jornalsch1a, 1700, 800);

			BorderPane Jornalsch2a = new BorderPane();
			Scene Jornalschs2a = new Scene(Jornalsch2a, 1700, 800);

			BorderPane Jornalsch11a = new BorderPane();
			Scene Jornalschs11a = new Scene(Jornalsch11a, 1540, 800);

			BorderPane incst = new BorderPane();
			Scene incsts = new Scene(incst, 1700, 800);

			BorderPane incstret = new BorderPane();
			Scene incstsret = new Scene(incstret, 1700, 800);

			BorderPane incst1 = new BorderPane();
			Scene incsts1 = new Scene(incst1, 1700, 800);

			BorderPane incstbal = new BorderPane();
			Scene incstsbal = new Scene(incstbal, 1700, 800);

			BorderPane incsta = new BorderPane();
			Scene incstsa = new Scene(incsta, 1700, 800);

			BorderPane incstreta = new BorderPane();
			Scene incstsreta = new Scene(incstreta, 1700, 800);

			BorderPane incst1a = new BorderPane();
			Scene incsts1a = new Scene(incst1a, 1700, 800);

			BorderPane incstbala = new BorderPane();
			Scene incstsbala = new Scene(incstbala, 1700, 800);

			StackPane root = new StackPane();
			BorderPane ff = new BorderPane();
			root.getChildren().add(ff);

			Image img = new Image("PM31.jpg");
			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);
			logInPane.setCenter(root);
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);

			Label logon = new Label("                                                AMW ACCOUNTING DEPATMENT  ");
			logon.setFont(Font.font(35));
			HBox hh = new HBox();

			hh.setMaxSize(2100, 50);
			hh.getChildren().add(logon);
			hh.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			logon.setFont(Font.font(null, FontWeight.BOLD, logon.getFont().getSize()));

			TextField userAvilable1 = new TextField();
			userAvilable1.setPrefSize(300, 30);

			PasswordField passAvilable1 = new PasswordField();
			userAvilable1.setPrefSize(300, 30);
			ImageView ww1 = new ImageView("User-Interface-Login-icon.png");

			Button logInButton1 = new Button("Login", ww1);
			logInButton1.setFont(Font.font(20));
			logInButton1.setPrefSize(120, 30);
			logInButton1.setStyle("-fx-Background-color:#1999f1");
			logInButton1.setOnMouseEntered(e -> {
				logInButton1.setStyle("-fx-Background-color:blue");
			});
			logInButton1.setOnMouseExited(e -> {
				logInButton1.setStyle("-fx-Background-color:#1999f1");
			});

			HBox buttonsBox1 = new HBox(20);
			buttonsBox1.setAlignment(Pos.CENTER);
			buttonsBox1.setPadding(new Insets(0, 15, 15, 0));
			// buttonsBox.getChildren().addAll(logInButton);

			GridPane logInGrid1 = new GridPane();
			logInGrid1.setHgap(60);

			// logInGrid.setAlignment(Pos.CENTER);
			Label logInLabel1 = new Label("Username :");
			Label logInLabel21 = new Label("Password :");
			logInLabel1.setFont(Font.font(20));
			logInLabel21.setFont(Font.font(20));
			logInLabel1.setUnderline(true);
			logInLabel21.setUnderline(true);

			logInGrid1.add(logInLabel1, 0, 0);
			logInGrid1.add(userAvilable1, 1, 0);
			logInGrid1.add(logInLabel21, 0, 1);
			logInGrid1.add(passAvilable1, 1, 1);

			logInGrid1.add(logInButton1, 1, 2);

			logInGrid1.setPadding(new Insets(10, 10, 20, 10));

			logInGrid1
					.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			logInLabel1.setFont(Font.font(null, FontWeight.BOLD, logon.getFont().getSize()));
			logInLabel21.setFont(Font.font(null, FontWeight.BOLD, logon.getFont().getSize()));

			ff.setTop(logInGrid1);
			ff.setPadding(new Insets(20, 30, 0, 940));

			logInPane.setTop(hh);

			logInButton1.setOnAction(ccc -> {
				try {
					if (checkLogIn(userName, userAvilable1.getText(), passAvilable1.getText())) {
						primaryStage.setScene(scene1);
						userAvilable1.clear();
						passAvilable1.clear();
					} else {
						JOptionPane.showMessageDialog(null, "Wrong , Please check your password or your user number");
					}
				} catch (Exception g) {

				}
				logInButton1.requestFocus();
			});

			// logInPane.setBottom(buttonsBox);

			HBox hn = new HBox();
			hn.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

			hn.setSpacing(30);
			hn.setPadding(new Insets(0, 10, 10, 1330));

			// hn.setBackground(new Background(new BackgroundFill(Color.WHITE,
			// CornerRadii.EMPTY, Insets.EMPTY)));

			RadioButton limb = new RadioButton("العربية");
			limb.setTextFill(Color.BLACK);
			limb.setFont(Font.font(18));
			RadioButton unlimbt = new RadioButton("ENGLISH");
			unlimbt.setSelected(true);
			unlimbt.setFont(Font.font(18));
			unlimbt.setTextFill(Color.BLACK);

			hn.getChildren().addAll(limb, unlimbt);
			ToggleGroup tg = new ToggleGroup();
			limb.setToggleGroup(tg);
			unlimbt.setToggleGroup(tg);

			ImageView iamg = new ImageView("F1.jpg");
			HBox hsb = new HBox();
			hsb.setSpacing(40);hsb.setSpacing(60);
			hsb.setSpacing(40);
			hsb.setPadding(new Insets(20, 10, 0, 0));

			hsb.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			hsb.getChildren().addAll(iamg, limb, unlimbt);
			logInPane.setBottom(hsb);

			limb.setOnAction(e -> {
				Label logon1a = new Label(
						"                                                                 AMW  قسم المحاسبة  ");
				logon1a.setFont(Font.font(35));
				HBox hh1 = new HBox();

				hh1.setMaxSize(2100, 50);
				hh1.getChildren().add(logon1a);
				hh1.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
				logon1a.setFont(Font.font(null, FontWeight.BOLD, logon1a.getFont().getSize()));
				TextField userAvilable = new TextField();
				userAvilable.setPrefSize(300, 30);

				PasswordField passAvilable = new PasswordField();
				userAvilable.setPrefSize(300, 30);
				ImageView ww = new ImageView("User-Interface-Login-icon.png");

				Button logInButton = new Button("دخول", ww);
				logInButton.setFont(Font.font(20));
				logInButton.setPrefSize(120, 30);
				logInButton.setStyle("-fx-Background-color:#1999f1");
				logInButton.setOnMouseEntered(ex -> {
					logInButton.setStyle("-fx-Background-color:blue");
				});
				logInButton.setOnMouseExited(ex -> {
					logInButton.setStyle("-fx-Background-color:#1999f1");
				});

				HBox buttonsBox = new HBox(20);
				buttonsBox.setAlignment(Pos.CENTER);
				buttonsBox.setPadding(new Insets(0, 15, 15, 0));
				// buttonsBox.getChildren().addAll(logInButton);

				GridPane logInGrid = new GridPane();
				logInGrid.setHgap(60);

				// logInGrid.setAlignment(Pos.CENTER);
				Label logInLabel = new Label("رقم المستخدم :");
				Label logInLabel2 = new Label("كلمة المرور :");
				logInLabel.setFont(Font.font(20));
				logInLabel2.setFont(Font.font(20));
				logInLabel.setUnderline(true);
				logInLabel2.setUnderline(true);

				logInGrid.add(logInLabel, 1, 0);
				logInGrid.add(userAvilable, 0, 0);
				logInGrid.add(logInLabel2, 1, 1);
				logInGrid.add(passAvilable, 0, 1);

				logInGrid.add(logInButton, 0, 2);

				logInGrid.setPadding(new Insets(10, 10, 20, 10));

				logInGrid.setBackground(
						new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
				logInLabel.setFont(Font.font(null, FontWeight.BOLD, logon.getFont().getSize()));
				logInLabel2.setFont(Font.font(null, FontWeight.BOLD, logon.getFont().getSize()));

				ff.setTop(logInGrid);
				ff.setPadding(new Insets(20, 20, 0, 900));

				logInPane.setTop(hh1);

				HBox hn12 = new HBox();
				hn12.setSpacing(10);
				hn12.setPadding(new Insets(0, 0, 0, 50));
				hn12.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

				RadioButton limb12 = new RadioButton("العربية");

				limb12.setFont(Font.font(15));
				RadioButton unlimbt12 = new RadioButton("ENGLISH");
				unlimbt12.setFont(Font.font(15));

				hn12.getChildren().addAll(limb12, unlimbt12);
				ToggleGroup tg12 = new ToggleGroup();
				limb12.setToggleGroup(tg12);
				unlimbt12.setToggleGroup(tg12);
				logInPane.setBottom(hn12);
				
				
				ImageView iamg1 = new ImageView("F1.jpg");
				HBox hsb1 = new HBox();
				hsb1.setSpacing(60);
				hsb1.setSpacing(40);
				hsb1.setPadding(new Insets(20, 10, 0, 0));

			
				hsb1.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
				hsb1.getChildren().addAll(iamg, limb, unlimbt);
				logInPane.setBottom(hsb1);
				
				
			

				logInButton.setOnAction(c1 -> {
					try {
						if (checkLogIn(userName, userAvilable.getText(), passAvilable.getText())) {
							primaryStage.setScene(scene1A);
							userAvilable.clear();
							passAvilable.clear();
						} else {
							JOptionPane.showMessageDialog(null, "كلمة المرور او رقم المستخدم خاطئة");
						}
					} catch (Exception g) {

					}
				});

			});

			/////////////////////////////////////////////////////////////////////////////
			Label logon1aw = new Label(
					"                                                                 AMW  قسم المحاسبة  ");
			logon1aw.setFont(Font.font(35));

			ImageView limgaw = new ImageView("Logout-icon.png");

			Button outaw = new Button(" تسجبل الخروج ", limgaw);
			outaw.setStyle("-fx-font-size: 10pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
			outaw.setOnAction(w -> {
				primaryStage.setScene(scene);
				primaryStage.setMaximized(true);
				primaryStage.show();
			});

			/*
			 * out.setOnMouseEntered(e1 ->{ out.setStyle("-fx-Background-color:wheat"); });
			 * out.setOnMouseExited(e1 ->{ out.setStyle("-fx-Background-color:lightblue");
			 * });
			 */
			HBox hh1aw = new HBox();
			hh1aw.setSpacing(378);
			hh1aw.setMaxSize(2100, 50);
			hh1aw.getChildren().addAll(logon1aw, outaw);
			hh1aw.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			logon1aw.setFont(Font.font(null, FontWeight.BOLD, logon1aw.getFont().getSize()));

			StackPane root3a = new StackPane();
			// Scene scene3 = new Scene(dataPage, 650, 650);
			Image img3aw = new Image("PM315.jpg");
			BackgroundImage bImg3aw = new BackgroundImage(img3aw, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround3a = new Background(bImg3aw);
			root3a.setBackground(bGround3a);
			dataPageA.setCenter(root3a);

			dataPageA.setTop(hh1aw);

			BorderPane bba = new BorderPane();
			bba.setPadding(new Insets(80, 90, 0, 0));
			HBox upha = new HBox();
			upha.setSpacing(200);
			upha.setAlignment(Pos.CENTER);
			bba.setTop(upha);

			ComboBox combup1a = new ComboBox();

			String[] ya = { "دليل الحسابات                      ", "كشف الحسابات                    " };

			ComboBox combupa = new ComboBox(FXCollections.observableArrayList(ya));
			combupa.setOnMouseClicked(r -> {
				combupa.setValue("الحسابات		");
			});
			Image imma = new Image("kkInvoice-icon.png");

			BackgroundImage bmga = new BackgroundImage(imma, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background brounda = new Background(bmga);
			combupa.setBackground(brounda);
			combupa.getEditor().setFont(Font.font(40));
			combupa.setStyle("-fx-font-size: 15pt;-fx-border-color:PLUM;-fx-font-weight: bold;");
			combupa.setPadding(new Insets(20, 20, 20, 40));
			combupa.setPromptText("الحسابات          ");

			combupa.setPrefWidth(400);

			Image imm12a = new Image("Business-Multiple-Input-icon.png");

			BackgroundImage bImg12a = new BackgroundImage(imm12a, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround12a = new Background(bImg12a);

			String[] sswa = { "فاتورة المبيعات               ", "فاتورة المشتريات              ",
					"بحث في الفواتير           " };
			ComboBox billsa = new ComboBox(FXCollections.observableArrayList(sswa));
			billsa.setPromptText("               الفواتير                         ");

			String[] ssw1a = { "بحث في السندات             ", "ادخال سند            " };
			ComboBox bills1a = new ComboBox(FXCollections.observableArrayList(ssw1a));
			bills1a.setPromptText("                 سند قيد                      ");

			String[] ssw2a = { "بحث في السندات             ", "ادخال سند            " };
			ComboBox bills2a = new ComboBox(FXCollections.observableArrayList(ssw2a));
			bills2a.setPromptText("                 سند قبض                   ");

			String[] ssw3a = { "بحث في السندات             ", "ادخال سند            " };
			ComboBox bills3a = new ComboBox(FXCollections.observableArrayList(ssw3a));
			bills3a.setPromptText("                 سند صرف                   ");

			combup1a.setBackground(bGround12a);
			combup1a.setPadding(new Insets(20, 20, 20, 40));
			combup1a.setPromptText("   المدخلات               ");
			combup1a.setPrefWidth(400);
			combup1a.getItems().addAll(billsa, bills1a, bills2a, bills3a);
			combup1a.setStyle("-fx-font-size: 15pt;-fx-border-color:PLUM;-fx-font-weight: bold;");

			String[] y2a = { "ميزان المراجعة المعدل                   ", "قائمة الدخل                   "
					, "بداية رأس مال المالك                   ","المركز المالي                   "};

			ComboBox combup2a = new ComboBox(FXCollections.observableArrayList(y2a));
			Image imm1a = new Image("lllloices-icon (1).png");

			BackgroundImage bImg1a = new BackgroundImage(imm1a, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround1a = new Background(bImg1a);
			combup2a.setBackground(bGround1a);
			// combup2.getEditor().setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 40));
			combup2a.setStyle("-fx-font-size: 15pt;-fx-border-color:PLUM;-fx-font-weight: bold;");

			combup2a.setPadding(new Insets(20, 20, 20, 40));
			combup2a.setPromptText("  التقارير المالية      ");
			combup2a.setPrefWidth(400);

			Label inpw = new Label(">>  مدخلات  <<");
			inpw.setUnderline(true);
			inpw.setStyle("-fx-font-size: 19pt;-fx-font-weight: bold;");
			upha.getChildren().addAll(combupa, inpw, combup2a);

			billsa.setOnMouseClicked(oo -> {
				billsa.setValue("فواتير");
				billsa.setPromptText("فواتير");
			});

			bills1a.setOnMouseClicked(oo -> {
				bills1a.setValue("سند قيد");
				bills1a.setPromptText("سند قيد");
			});

			bills2a.setOnMouseClicked(oo -> {
				bills2a.setValue("سند قبض");
				bills2a.setPromptText("سند قبض");

			});

			bills3a.setOnMouseClicked(oo -> {
				bills3a.setValue("سند صرف");
				bills3a.setPromptText("سند صرف");
			});

			billsa.setPrefSize(390, 40);
			bills1a.setPrefSize(390, 40);
			bills2a.setPrefSize(390, 40);
			bills3a.setPrefSize(390, 40);

			GridPane grisw = new GridPane();
			grisw.setPadding(new Insets(60, 0, 0, 400));
			grisw.add(billsa, 0, 0);
			grisw.add(bills1a, 0, 1);
			grisw.add(bills2a, 0, 2);
			grisw.add(bills3a, 0, 3);
			grisw.setVgap(40);

			bba.setCenter(grisw);
			// gris.setAlignment(Pos.CENTER);

			grisw.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");

			root3a.getChildren().add(bba);

			ImageView iamg13a = new ImageView("F1.jpg");
			HBox hsb13a = new HBox();
			hsb13a.setPrefHeight(60);
			hsb13a.setPadding(new Insets(20, 10, 0, 0));
			hsb13a.setSpacing(40);
			hsb13a.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			hsb13a.getChildren().addAll(iamg13a);
			dataPageA.setBottom(hsb13a);

			Label mua = new Label("العملات");
			mua.setStyle("-fx-font-size: 20pt;-fx-font-weight: bold;");
			mua.setUnderline(true);

			RadioButton ilsa = new RadioButton(" ILS (₪)");
			ilsa.setStyle("-fx-font-size: 15pt;-fx-font-weight: bold;");
			RadioButton usda = new RadioButton(" USD ($)");
			usda.setStyle("-fx-font-size: 15pt;-fx-font-weight: bold;");
			RadioButton joda = new RadioButton(" JOD (JD)");
			joda.setStyle("-fx-font-size: 15pt;-fx-font-weight: bold;");
			VBox laa = new VBox();
			laa.setSpacing(20);
			laa.getChildren().addAll(mua, ilsa, usda, joda);
			ToggleGroup ttga = new ToggleGroup();
			ilsa.setToggleGroup(ttga);
			usda.setToggleGroup(ttga);
			joda.setToggleGroup(ttga);
			bba.setLeft(laa);
			laa.setAlignment(Pos.CENTER_LEFT);

			laa.setPadding(new Insets(200, 0, 0, 100));

			ilsa.setOnAction(w -> {
				input.clear();
				String coin = "";
				try {
					Scanner in = new Scanner(refresh);
					String[] s = in.nextLine().split(",");
					if (!s[5].equalsIgnoreCase("ILS")) {
						changeCoin(s[5], "ILS");
					}
				} catch (Exception g) {
					System.out.println("Error in Change Coin in ILS radi Button");
				}
				fillSanads();
			});

			usda.setOnAction(w -> {
				input.clear();
				String coin = "";
				try {
					Scanner in = new Scanner(refresh);
					String[] s = in.nextLine().split(",");
					if (!s[5].equalsIgnoreCase("USD")) {
						changeCoin(s[5], "USD");
					}
				} catch (Exception g) {
					System.out.println("Error in Change Coin in ILS radi Button");
				}
				fillSanads();
			});

			joda.setOnAction(w -> {
				input.clear();
				String coin = "";
				try {
					Scanner in = new Scanner(refresh);
					String[] s = in.nextLine().split(",");
					if (!s[5].equalsIgnoreCase("JOD")) {
						changeCoin(s[5], "JOD");
					}
				} catch (Exception g) {
					System.out.println("Error in Change Coin in ILS radi Button");
				}
				fillSanads();
			});

			VBox law = new VBox();
			law.setSpacing(20);
			law.getChildren().addAll(mua, ilsa, usda, joda);

			ilsa.setToggleGroup(ttg);
			usda.setToggleGroup(ttg);
			joda.setToggleGroup(ttg);
			bba.setLeft(law);
			law.setAlignment(Pos.CENTER_LEFT);

			law.setPadding(new Insets(200, 0, 0, 100));

			EventHandler<ActionEvent> evenxdacca = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					if (combupa.getValue() == "دليل الحسابات                      ") {

						ArrayList<Label> labelList = new ArrayList<Label>();
						// for assets only
						BorderPane mm = new BorderPane();
						StackPane ql = new StackPane();

						ImageView back1 = new ImageView("dsBuffer.bmp.png");
						back1.setPreserveRatio(true);
						Button bk1 = new Button("رجوع", back1);
						bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151 = new Label(
								"                                  دليل الحسابات                        ");
						logon151.setFont(Font.font(35));

						bk1.setOnAction(ss -> {
							combupa.setValue("الحسابات          ");
							primaryStage.setScene(scene1A);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("تسجيل الخروج", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk1, logon151, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
						xqa.setTop(hh15);

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						ql.setBackground(bGround5);
						xqa.setCenter(ql);

						labelList.add(new Label("(110) الموجودات المتداوله "));
						labelList.add(new Label("(111) النقدية "));
						labelList.add(new Label("(112) ذمم  مدينه"));
						labelList.add(new Label("(113) مستلزمات "));
						labelList.add(new Label("(114) تامين مدفوع مسبقاً "));
						labelList.add(new Label("(120) الموجودات الثابتة "));
						labelList.add(new Label("(121) اّلات و معدات "));
						labelList.add(new Label("(122) اثاث "));
						labelList.add(new Label("(123) مركبات "));
						labelList.add(new Label("(124) المباني "));
						labelList.add(new Label("(1000) اهتلاك متراكم "));
						labelList.add(new Label("(2000) ملخص الدخل "));// 11
						// for Liability only
						labelList.add(new Label("(210) التزامات متداولة ")); // 12
						labelList.add(new Label("(211) ذمم دائنة "));
						labelList.add(new Label("(212) الايرادات الخدمية غير المكتسبة "));
						labelList.add(new Label("(213) الاجور والرواتب المستحقة للدفع "));
						labelList.add(new Label("(214) الفوائد المستحقة للدفع "));
						labelList.add(new Label("(220) مخصص ضريبة القيمة المضافة "));
						labelList.add(new Label("(230)  التزام طويل الأجل "));// 18

						// for Equity

						labelList.add(new Label("(310) رأس المال "));
						labelList.add(new Label("(320) الارباح/الخسائر المحتجزة "));
						labelList.add(new Label("(330) دخل السنة  الحالية"));
						labelList.add(new Label("(340) المسحوبات "));

						// for revenue
						labelList.add(new Label("(410) الايرادات"));// 23

						// for expense
						// expense
						labelList.add(new Label("(510) مصروفات رواتب و اجور "));
						labelList.add(new Label("(520) مصروفات تامين "));
						labelList.add(new Label("(530) مصروف استهلاك "));
						labelList.add(new Label("(540) مصروف مشتريات  عام"));
						labelList.add(new Label("(550) مصروف المرافق العامة "));

						labelList.add(new Label("(1)الموجودات\n__________"));
						labelList.add(new Label("(2) التزامات\n__________"));
						labelList.add(new Label("(3) حقوق ملكية\n__________"));
						labelList.add(new Label("(4)ايرادات\n__________"));
						labelList.add(new Label("(5)مصروفات \n__________"));

						GridPane gDaleel = new GridPane();
						gDaleel.setAlignment(Pos.CENTER);
						gDaleel.setHgap(80);
						gDaleel.setVgap(25);
						gDaleel.setPadding(new Insets(0, 70, 0, 0));
						// gDaleel.setPadding(new Insets(0,0,20,0));
						// Assets
						gDaleel.add(labelList.get(labelList.size() - 5), 0, 0); // assets
						gDaleel.add(labelList.get(0), 0, 1);
						gDaleel.add(labelList.get(1), 0, 2);
						gDaleel.add(labelList.get(2), 0, 3);
						gDaleel.add(labelList.get(3), 0, 4);
						gDaleel.add(labelList.get(4), 0, 5);
						gDaleel.add(labelList.get(5), 0, 6);
						gDaleel.add(labelList.get(6), 0, 7);
						gDaleel.add(labelList.get(7), 0, 8);
						gDaleel.add(labelList.get(8), 0, 9);
						gDaleel.add(labelList.get(9), 0, 10);
						gDaleel.add(labelList.get(10), 0, 11);
						gDaleel.add(labelList.get(11), 0, 12);

						gDaleel.add(labelList.get(labelList.size() - 4), 1, 0); // liability
						gDaleel.add(labelList.get(12), 1, 1);
						gDaleel.add(labelList.get(13), 1, 2);
						gDaleel.add(labelList.get(14), 1, 3);
						gDaleel.add(labelList.get(15), 1, 4);
						gDaleel.add(labelList.get(16), 1, 5);
						gDaleel.add(labelList.get(17), 1, 6);
						gDaleel.add(labelList.get(18), 1, 7);

						gDaleel.add(labelList.get(labelList.size() - 3), 2, 0); // Equity
						// gDaleel.add(labelList.get(19), 2, 1);
						gDaleel.add(labelList.get(19), 2, 1);
						gDaleel.add(labelList.get(20), 2, 2);
						gDaleel.add(labelList.get(21), 2, 3);
						gDaleel.add(labelList.get(22), 2, 4);

						gDaleel.add(labelList.get(labelList.size() - 2), 3, 0); // Revenues
						gDaleel.add(labelList.get(23), 3, 1);

						gDaleel.add(labelList.get(labelList.size() - 1), 4, 0);
						gDaleel.add(labelList.get(24), 4, 1);
						gDaleel.add(labelList.get(25), 4, 2);
						gDaleel.add(labelList.get(26), 4, 3);
						gDaleel.add(labelList.get(27), 4, 4);
						gDaleel.add(labelList.get(28), 4, 5);

						gDaleel.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						Button backForDaleel = new Button("Back");

						backForDaleel.setOnAction(ex -> {
							// scene after login
						});

						GridPane searchDaleelGrid = new GridPane();
						searchDaleelGrid.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						searchDaleelGrid.setHgap(15);
						searchDaleelGrid.setVgap(5);
						Button searchForDaleel = new Button("بحث");
						searchForDaleel.setPrefSize(75, 25);
						TextField tDaleel = new TextField();
						TextField daleelResult = new TextField();
						daleelResult.setEditable(false);
						searchDaleelGrid.add(searchForDaleel, 0, 0);
						searchDaleelGrid.add(tDaleel, 1, 0);
						searchDaleelGrid.add(daleelResult, 1, 1);
						searchDaleelGrid.setAlignment(Pos.CENTER);
						searchDaleelGrid.setPadding(new Insets(0, 0, 15, 0));

						searchForDaleel.setOnAction(ex -> {
							boolean flag = false;
							String[] ok = null;

							for (int i = 0; i < labelList.size(); i++) {
								String s = labelList.get(i).getText();
								s = s.replaceAll("[()]", "");
								ok = s.split(" ");
								if (ok[0].equalsIgnoreCase(tDaleel.getText())) {

									flag = true;
									break;
								}
							}
							if (flag) {
								switch(ok.length) {
								case 2:daleelResult.setText(ok[1]); break;
								case 3:daleelResult.setText(ok[1]+" "+ok[2]);break;
								case 4:daleelResult.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
								case 5:daleelResult.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
								}
							}
						});
						ql.getChildren().add(mm);
						mm.setCenter(gDaleel);
						mm.setBottom(searchDaleelGrid);
						xqa.setCenter(ql);
						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						// hsb13a5.setPrefHeight(30);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						xqa.setBottom(hsb13a5);

						primaryStage.setScene(xsa);
					}

					else if (combupa.getValue() == "كشف الحسابات                    ") {

						BorderPane mmx = new BorderPane();

						StackPane root51x = new StackPane();

						Image img5x = new Image("PM316.jpg");
						BackgroundImage bImg5x = new BackgroundImage(img5x, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5x = new Background(bImg5x);
						root51x.setBackground(bGround5x);

						root51x.getChildren().add(mmx);

						ImageView back1x = new ImageView("dsBuffer.bmp.png");
						back1x.setPreserveRatio(true);
						Button bk1x = new Button("Back", back1x);
						bk1x.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151x = new Label(
								"                           Account Statements                     ");
						logon151x.setFont(Font.font(35));

						HBox hh15x = new HBox();
						ImageView limg5x = new ImageView("Logout-icon.png");

						Button out5x = new Button("LogOut", limg5x);
						out5x.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5x.setOnAction(tx -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						hh15x.setSpacing(228);
						hh15x.setMaxSize(2100, 50);
						hh15x.getChildren().addAll(bk1x, logon151x, out5x);
						hh15x.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151x.setFont(Font.font(null, FontWeight.BOLD, logon151x.getFont().getSize()));
						tara.setTop(hh15x);

						tara.setCenter(root51x);

						TextArea tt = new TextArea();
						tt.setPrefSize(400, 380);
						tt.setDisable(true);
						tt.setStyle("-fx-font-size: 15pt;-fx-font-weight: bold;");
						tt.setText("Account number\t\t\t\t\t\tAccount Name\t\t\t\t\t\tAmount\t\t\t\t\t\t\tDate\n");
						mmx.setPadding(new Insets(100, 100, 100, 100));
						mmx.setCenter(tt);

						HBox hsb13a5x = new HBox();
						hsb13a5x.setPrefHeight(50);
						hsb13a5x.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

						tara.setBottom(hsb13a5x);

						//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

						BorderPane mm = new BorderPane();

						StackPane root51 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root51.setBackground(bGround5);
						px1.setCenter(root51);
						root51.getChildren().add(mm);

						ImageView back1 = new ImageView("dsBuffer.bmp.png");
						back1.setPreserveRatio(true);
						Button bk1 = new Button("رجوع", back1);
						bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151 = new Label(
								"                                كشف الحسابات                        ");
						logon151.setFont(Font.font(35));

						bk1.setOnAction(ss -> {
							primaryStage.setScene(scene1A);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("تسجيل الخروج", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});
						HBox hh15 = new HBox();
						HBox hh16 = new HBox(10);
						hh16.setAlignment(Pos.CENTER);
						TextField ff = new TextField();
						Button bSearch = new Button("بحث");
						hh16.getChildren().addAll(new Label("أدخل رقم الحساب"), ff, bSearch);

						bk1x.setOnAction(ssx -> {
							ff.clear();
							tt.clear();
							tt.setText("  رقم الحساب\t\t\t\t\t\tاسم الحساب\t\t\t\t\t\tالمبلغ\t\t\t\t\t\t\tالتاريخ\n");
							primaryStage.setScene(qqs);
							primaryStage.show();

						});

						StringBuilder area = new StringBuilder(0);
						bSearch.setOnAction(n -> {

							try {
								for (Accounts c : account) {
									Scanner ar = new Scanner(refresh);
									while (ar.hasNextLine()) {
										String[] s = ar.nextLine().split(",");

										String[] len = s[3].split(" ");
										
										switch (len.length) {
										case 1:
											tt.setText(
													"  Account number\t\t\t   Amount\t\t\tVoucher Number \t\t\t Account Name\t\t\t\t\tDate\n");
											break;
										case 2:
											tt.setText(
													"  Account number\t\t\t   Amount\t\t\tVoucher Number \t\t\t Account Name\t\t\tDate\n");
											break;
										case 3:
											tt.setText(
													"  Account number\t\t\t   Amount\t\t\tVoucher Number \t\t\t Account Name\t\t\tDate\n");
											break;
										case 4:
											tt.setText(
													"  Account number\t\t\t   Amount\t\t\tVoucher Number \t\t\t Account Name\t\t\t\t  Date\n");
											break;
										}

										if (c.getAccountNumber() == Long.parseLong(s[0])) {
											if (s[0].equalsIgnoreCase(ff.getText())) {
												sceneFlag = true;

												switch (len.length) {
												case 1:
													area.append("\t" + s[0] + " \t\t\t\t\t  " + s[2] + "       \t\t\t"
															+ s[1] + " \t\t\t\t\t\t" + s[3] + " \t\t\t\t  " + s[4]
															+ "\n");
													System.out.print("case1");
													break;

												case 2:
													area.append("\t" + s[0] + " \t\t\t\t\t    " + s[2]
															+ "\t\t\t\t\t\t     " + s[1] + "    \t\t\t\t\t\t\t" + s[3]
															+ " \t\t\t\t\t" + s[4] + "\n");
													System.out.print("case2");
													break;

												case 3:
													area.append("\t" + s[0] + " \t\t\t     " + s[2] + "\t\t\t\t\t"
															+ s[1] + "    \t\t\t\t\t" + s[3] + " \t\t\t" + s[4] + "\n");
													System.out.print("case3");
													break;

												case 4:
													area.append("\t" + s[0] + " \t\t\t\t\t  " + s[2] + "\t\t\t\t" + s[1]
															+ "    \t\t\t\t  " + s[3] + "\t\t\t\t" + s[4] + "\n");
													System.out.print("case4");
													break;

												}

											}
										}
									}

								}
							} catch (Exception m) {
								System.out.println("Fail in search ");
							}
							if (sceneFlag) {

								tt.appendText(area.toString());
								primaryStage.setScene(tars);
								primaryStage.setMaximized(true);
								primaryStage.show();
							}
						});
						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk1, logon151, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
						qq.setTop(hh15);
						mm.setCenter(hh16);
						hh16.setStyle("-fx-font-size: 23pt;-fx-font-weight: bold;");
						hh16.setAlignment(Pos.CENTER);
						qq.setCenter(root51);

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						// hsb13a5.setPrefHeight(30);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						qq.setBottom(hsb13a5);

						primaryStage.setScene(qqs);
						primaryStage.show();
					}

				}
			};
			combupa.setOnAction(evenxdacca);

			EventHandler<ActionEvent> evenxda = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					if (bills1a.getValue() == "ادخال سند            ") {

						ImageView back1 = new ImageView("dsBuffer.bmp.png");
						back1.setPreserveRatio(true);
						Button bk1 = new Button("رجوع", back1);
						bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151 = new Label(
								"                                        سند قيد                            ");
						logon151.setFont(Font.font(35));

						bk1.setOnAction(ss -> {
							primaryStage.setScene(scene1A);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("تسجيل الخروج", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk1, logon151, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
						px1a.setTop(hh15);

						StackPane root51 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root51.setBackground(bGround5);
						px1a.setCenter(root51);
						String[] cointxt = { "ILS", "USD", "JOD" };
						ComboBox coinCombo = new ComboBox(FXCollections.observableArrayList(cointxt));
						coinCombo.setStyle("-fx-font-size: 10pt;-fx-font-weight: bold;");
						coinCombo.setPromptText("اختر العمله");
						TextField status = new TextField();
						status.setPrefColumnCount(6);
						status.setText("جديد");
						status.setDisable(true);

						Rectangle ttop = new Rectangle(400, 40, 1100, 150);
						ttop.setStroke(Color.BLACK);
						ttop.setFill(Color.TRANSPARENT);
						StackPane santop = new StackPane();
						santop.setPadding(new Insets(33, 160, 0, 0));

						BorderPane sanadKabdPane = new BorderPane();
						HBox hAll = new HBox(50);
						hAll.setAlignment(Pos.CENTER);
						hAll.setPadding(new Insets(25, 10, 10, 0));
						GridPane g1 = new GridPane();
						g1.setHgap(15);
						g1.setVgap(20);

						HBox h2 = new HBox(7.5);
						HBox h3 = new HBox(15);
						TextField sanadKabdNumber = new TextField();
						TextField sanadKabdDate = new TextField();
						h2.getChildren().addAll(new Label("Voucher Date : "), sanadKabdDate);
						g1.add(new Label("Voucher Number : "), 0, 0);
						g1.add(sanadKabdNumber, 1, 0);
						g1.add(new Label("Coin"), 0, 1);
						g1.add(coinCombo, 1, 1);

						h3.getChildren().addAll(new Label(" Status "), status);
						g1.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
						h3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
						hAll.getChildren().addAll(g1, h2, h3);
						h2.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");

						sanadKabdPane.setTop(santop);
						santop.getChildren().addAll(ttop, hAll);
						/// Top ^
						TextField accNumber = new TextField();
						TextField Name = new TextField();
						TextField Cost = new TextField();

						TextField note = new TextField();

						TextField accNumber1 = new TextField();
						TextField Name1 = new TextField();
						TextField Cost1 = new TextField();
						TextField note1 = new TextField();

						TextField accNumber2 = new TextField();
						TextField Name2 = new TextField();
						TextField Cost2 = new TextField();
						TextField note2 = new TextField();

						TextField accNumber22 = new TextField();
						TextField Name22 = new TextField();
						TextField Cost22 = new TextField();
						TextField note22 = new TextField();
						
						Name.setDisable(true);
						Name1.setDisable(true);
						Name2.setDisable(true);
						Name22.setDisable(true);
						
						accNumber.setOnKeyReleased(d -> {
							boolean flag = false;
							String[] ok = null;

							for (int i = 0; i < labelList.size(); i++) {
								String s = labelList.get(i).getText();
								s = s.replaceAll("[()]", "");
								ok = s.split(" ");
								if (ok[0].equalsIgnoreCase(accNumber.getText())) {

									flag = true;
									break;
								}
							}
							if (flag) {
								switch(ok.length) {
								case 2:Name.setText(ok[1]); break;
								case 3:Name.setText(ok[1]+" "+ok[2]);break;
								case 4:Name.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
								case 5:Name.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
								}
							}
						});

						accNumber1.setOnKeyReleased(d -> {
							boolean flag = false;
							String[] ok = null;

							for (int i = 0; i < labelList.size(); i++) {
								String s = labelList.get(i).getText();
								s = s.replaceAll("[()]", "");
								ok = s.split(" ");
								if (ok[0].equalsIgnoreCase(accNumber1.getText())) {

									flag = true;
									break;
								}
							}
							if (flag) {
								switch(ok.length) {
								case 2:Name1.setText(ok[1]); break;
								case 3:Name1.setText(ok[1]+" "+ok[2]);break;
								case 4:Name1.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
								case 5:Name1.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
								}
							}
						});

						accNumber2.setOnKeyReleased(d -> {
							boolean flag = false;
							String[] ok = null;

							for (int i = 0; i < labelList.size(); i++) {
								String s = labelList.get(i).getText();
								s = s.replaceAll("[()]", "");
								ok = s.split(" ");
								if (ok[0].equalsIgnoreCase(accNumber2.getText())) {

									flag = true;
									break;
								}
							}
							if (flag) {
								switch(ok.length) {
								case 2:Name2.setText(ok[1]); break;
								case 3:Name2.setText(ok[1]+" "+ok[2]);break;
								case 4:Name2.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
								case 5:Name2.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
								}
							}
						});

						accNumber22.setOnKeyReleased(d -> {
							boolean flag = false;
							String[] ok = null;

							for (int i = 0; i < labelList.size(); i++) {
								String s = labelList.get(i).getText();
								s = s.replaceAll("[()]", "");
								ok = s.split(" ");
								if (ok[0].equalsIgnoreCase(accNumber22.getText())) {

									flag = true;
									break;
								}
							}
							if (flag) {
								switch(ok.length) {
								case 2:Name22.setText(ok[1]); break;
								case 3:Name22.setText(ok[1]+" "+ok[2]);break;
								case 4:Name22.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
								case 5:Name22.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
								}
							}
						});

						Rectangle tcent = new Rectangle(400, 130, 1100, 430);
						tcent.setStroke(Color.BLACK);
						tcent.setFill(Color.TRANSPARENT);
						StackPane stcent = new StackPane();
						stcent.setPadding(new Insets(0, 160, 0, 0));
						GridPane g2 = new GridPane();
						g2.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						g2.setPadding(new Insets(30, 0, 0, 0));
						g2.setHgap(45);
						g2.setVgap(10);
						Label mm3 = new Label("Credit :");
						mm3.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						mm3.setUnderline(true);
						g2.add(mm3, 0, 0);
						g2.add(new Label("Account Number"), 0, 1);

						g2.add(new Label("Account Name"), 1, 1);

						g2.add(new Label("Amount"), 2, 1);
						g2.add(new Label("Note"), 3, 1);

						g2.add(accNumber, 0, 2);
						g2.add(Name, 1, 2);
						g2.add(Cost, 2, 2);
						g2.add(note, 3, 2);

						g2.add(accNumber1, 0, 3);
						g2.add(Name1, 1, 3);
						g2.add(Cost1, 2, 3);
						g2.add(note1, 3, 3);

						Label mm = new Label("Debit :");
						mm.setUnderline(true);
						mm.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						g2.add(mm, 0, 4);
						g2.add(new Label("Account Number"), 0, 5);
						g2.add(new Label("Name"), 1, 5);
						g2.add(new Label("Amount"), 2, 5);
						g2.add(new Label("Note"), 3, 5);

						g2.add(accNumber2, 0, 6);
						g2.add(Name2, 1, 6);
						g2.add(Cost2, 2, 6);
						g2.add(note2, 3, 6);

						g2.add(accNumber22, 0, 7);
						g2.add(Name22, 1, 7);
						g2.add(Cost22, 2, 7);
						g2.add(note22, 3, 7);

						Button bst = new Button(" Check Status ");
						bst.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						g2.add(bst, 0, 8);
						Label gr = new Label("Balanced");
						Label gr2 = new Label("Unbalanced");

						bst.setOnAction(w -> {
							if (Cost1.getText().trim().isEmpty() && !Cost22.getText().trim().isEmpty()) {
								double summ = Double.parseDouble(Cost.getText()) + 0;
								double dep = Double.parseDouble(Cost2.getText()) + Double.parseDouble(Cost22.getText());
								if (dep == summ) {
									gr2.setTextFill(Color.WHITE);
									gr2.setFont(Font.font(0.1));

									gr.setTextFill(Color.GREEN);
									gr.setFont(Font.font(15));

									g2.add(gr, 1, 8);
								} else if (dep != summ) {
									gr.setTextFill(Color.WHITE);
									gr.setFont(Font.font(0.1));
									gr2.setFont(Font.font(15));
									gr2.setTextFill(Color.RED);
									g2.add(gr2, 1, 8);
								}
							}
							if (Cost22.getText().trim().isEmpty() && !Cost1.getText().trim().isEmpty()) {
								double summ = Double.parseDouble(Cost.getText()) + Double.parseDouble(Cost1.getText());
								double dep = Double.parseDouble(Cost2.getText()) + 0;
								if (dep == summ) {
									gr2.setTextFill(Color.WHITE);
									gr2.setFont(Font.font(0.1));

									gr.setTextFill(Color.GREEN);
									gr.setFont(Font.font(15));

									g2.add(gr, 1, 8);
								} else if (dep != summ) {
									gr.setTextFill(Color.WHITE);
									gr.setFont(Font.font(0.1));
									gr2.setFont(Font.font(15));
									gr2.setTextFill(Color.RED);
									g2.add(gr2, 1, 8);
								}
							}
							if (Cost22.getText().trim().isEmpty() && Cost1.getText().trim().isEmpty()) {
								double summ = Double.parseDouble(Cost.getText()) + 0;
								double dep = Double.parseDouble(Cost2.getText()) + 0;
								if (dep == summ) {
									gr2.setTextFill(Color.WHITE);
									gr2.setFont(Font.font(0.1));

									gr.setTextFill(Color.GREEN);
									gr.setFont(Font.font(15));

									g2.add(gr, 1, 8);
								} else if (dep != summ) {
									gr.setTextFill(Color.WHITE);
									gr.setFont(Font.font(0.1));
									gr2.setFont(Font.font(15));
									gr2.setTextFill(Color.RED);
									g2.add(gr2, 1, 8);
								}
							} else {
								double summ = Double.parseDouble(Cost.getText()) + Double.parseDouble(Cost1.getText());
								double dep = Double.parseDouble(Cost2.getText()) + Double.parseDouble(Cost22.getText());
								if (dep == summ) {
									gr2.setTextFill(Color.WHITE);
									gr2.setFont(Font.font(0.1));

									gr.setTextFill(Color.GREEN);
									gr.setFont(Font.font(15));

									g2.add(gr, 1, 8);
								} else if (dep != summ) {
									gr.setTextFill(Color.WHITE);
									gr.setFont(Font.font(0.1));
									gr2.setFont(Font.font(15));
									gr2.setTextFill(Color.RED);
									g2.add(gr2, 1, 8);
								}
							}

						});

						g2.setAlignment(Pos.TOP_CENTER);
						stcent.getChildren().addAll(tcent, g2);
						sanadKabdPane.setCenter(stcent);

						sanadKabdNumber.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									Cost.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});
						Cost.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									Cost.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});
						Cost1.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									Cost1.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});
						Cost2.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									Cost2.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});
						Cost22.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									Cost22.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});

						GridPane g3 = new GridPane();

						Button save = new Button("Save");

						save.setPrefSize(120, 30);

						save.setOnAction(f -> {
							status.setText("مسوده");
							try {

								if (!accNumber.getText().trim().isEmpty()) {

									input.add(new SanadKayd(Long.parseLong(sanadKabdNumber.getText()),
											coinCombo.getValue().toString(), sanadKabdDate.getText(),
											new Accounts(Long.parseLong(accNumber.getText()), Name.getText(),
													Double.parseDouble(Cost.getText()), note.getText())));
									if(input.get(input.size() - 1) instanceof SanadKayd) {
										((SanadKayd)input.get(input.size() - 1)).setCnum("c0");
									}
									input.get(input.size() - 1).saveSanad();
								}
								if (!accNumber1.getText().trim().isEmpty()) {

									input.add(new SanadKayd(Long.parseLong(sanadKabdNumber.getText()),
											coinCombo.getValue().toString(), sanadKabdDate.getText(),
											new Accounts(Long.parseLong(accNumber1.getText()), Name1.getText(),
													Double.parseDouble(Cost1.getText()), note1.getText())));
									if(input.get(input.size() - 1) instanceof SanadKayd) {
										((SanadKayd)input.get(input.size() - 1)).setCnum("c1");
									}
									input.get(input.size() - 1).saveSanad();
								}
								if (!accNumber2.getText().trim().isEmpty()) {

									input.add(new SanadKayd(Long.parseLong(sanadKabdNumber.getText()),
											coinCombo.getValue().toString(), sanadKabdDate.getText(),
											new Accounts(Long.parseLong(accNumber2.getText()), Name2.getText(),
													Double.parseDouble(Cost2.getText()), note2.getText())));
									if(input.get(input.size() - 1) instanceof SanadKayd) {
										((SanadKayd)input.get(input.size() - 1)).setCnum("c2");
									}
									input.get(input.size() - 1).saveSanad();
								}

								if (!accNumber22.getText().trim().isEmpty()) {

									input.add(new SanadKayd(Long.parseLong(sanadKabdNumber.getText()),
											coinCombo.getValue().toString(), sanadKabdDate.getText(),
											new Accounts(Long.parseLong(accNumber22.getText()), Name22.getText(),
													Double.parseDouble(Cost22.getText()), note22.getText())));
									if(input.get(input.size() - 1) instanceof SanadKayd) {
										((SanadKayd)input.get(input.size() - 1)).setCnum("c3");
									}
									input.get(input.size() - 1).saveSanad();

								}

							} catch (Exception n) {
								JOptionPane.showMessageDialog(null, "The Voucher Saved Before");
							}
						});
						Button tarhel = new Button("ترحيل");
						tarhel.setOnAction(h -> {
							status.setText("مرحل");
							String rr = "";
							try {
								Scanner inFile = new Scanner(refresh);
								while(inFile.hasNextLine()) {
									rr+= inFile.nextLine()+"\n";
								}
							}
							catch(Exception g) {
								
							}
							try(PrintWriter p = new PrintWriter(refresh)){
								long n=0;
								long n1=0;
								long n2=0;
								long n3=0;
								try {
									n=Long.parseLong(accNumber.getText());
								}
								catch(Exception m) {
									 n=0;
								}
								try {
									n1=Long.parseLong(accNumber1.getText());
								}
								catch(Exception m) {
									 n1=0;
								}
								try {
									n2=Long.parseLong(accNumber2.getText());
								}
								catch(Exception m) {
									 n2=0;
								}
								try {
									n3=Long.parseLong(accNumber22.getText());
								}
								catch(Exception m) {
									 n3=0;
								}
								
								
								if(rr == "") {   // assets and expense  drawings
									if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
									
										p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
									
									}
									else if(n!=0){
										p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
										
									}
									if((n1>110 && n1<125) || (n1==340) || (n1>509 && n1<551) && n1!=0) {
										
										p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",+"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
									
									}
									else if(n1!=0){
										p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",-"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
										
									}
									if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
										
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
									
									}
									else if(n2!=0){
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
										
									}
									if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
										
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
									
									}
									else if(n3!=0){
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
										
									}
								}
								else {
									p.append(rr);
									if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
										
										p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
									
									}
									else if(n!=0){
										p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
										
									}
									if((n1>110 && n1<125) || (n1==340) || (n1>509 && n1<551) && n1!=0) {
										
										p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",+"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
									
									}
									else if(n1!=0){
										p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",-"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
										
									}
									if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
										
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
									
									}
									else if(n2!=0){
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
										
									}
									if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
										
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
									
									}
									else if(n3!=0){
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
										
									}
										
								}
							} catch (FileNotFoundException e1) {
								System.out.println("Creat Refresh file");
							}

						});
						tarhel.setPrefSize(120, 30);
						Button printAndTarhel = new Button("مسح");
						printAndTarhel.setOnAction(wa->{
							sanadKabdDate.clear();
							sanadKabdNumber.clear();
							accNumber.clear();
							accNumber1.clear();
							accNumber2.clear();
							accNumber22.clear();
							
							Name.clear();
							Name1.clear();
							Name2.clear();
							Name22.clear();
							Cost.clear();
							Cost1.clear();
							Cost2.clear();
							Cost22.clear();

							note.clear();
							note1.clear();
							note2.clear();
							note22.clear();
							status.setText("جديد");
							
						});
						printAndTarhel.setPrefSize(150, 30);
						Button printt = new Button("Print");
						printt.setPrefSize(120, 30);

						g3.add(save, 0, 0);
						g3.add(tarhel, 1, 0);
						g3.add(printt, 2, 0);
						g3.add(printAndTarhel, 3, 0);
						g3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
						g3.setHgap(140);
						g3.setPadding(new Insets(0, 60, 20, 0));
						g3.setAlignment(Pos.CENTER);
						sanadKabdPane.setBottom(g3);
						root51.getChildren().add(sanadKabdPane);

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						// hsb13a5.setPrefHeight(30);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						px1a.setBottom(hsb13a5);

						primaryStage.setScene(selx1a);
						primaryStage.show();

					}

					else if (bills1a.getValue() == "بحث في السندات             ") {

						ImageView back1 = new ImageView("dsBuffer.bmp.png");
						back1.setPreserveRatio(true);
						Button bk1 = new Button("رجوع", back1);
						bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151 = new Label(
								"                                    بحث في السندات                               ");
						logon151.setFont(Font.font(30));

						bk1.setOnAction(ss -> {
							primaryStage.setScene(scene1A);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("تسجيل الخروج", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setPadding(new Insets(7, 0, 0, 0));
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk1, logon151, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
						Jornalsch2a.setTop(hh15);

						StackPane root51 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root51.setBackground(bGround5);

						GridPane ss = new GridPane();
						ss.setHgap(40);
						Label xd = new Label("ادخل رقم السند");
						DropShadow shadow = new DropShadow();
						shadow.setOffsetX(2);
						shadow.setOffsetY(3);
						shadow.setColor(Color.SKYBLUE);
						xd.setEffect(shadow);
						TextField ll = new TextField();
						Button find = new Button(" اذهب ");
						ll.setStyle("-fx-control-inner-background:	#F3DEA1");
						ss.add(xd, 2, 0);
						ss.add(ll, 1, 0);
						ss.add(find, 0, 0);
						ss.setStyle("-fx-font-size: 22pt;-fx-font-weight: bold;");
						root51.getChildren().add(ss);
						ss.setPadding(new Insets(250, 0, 0, 370));
						Jornalsch2a.setCenter(root51);
						find.setOnAction(o -> {
							try {
								primaryStage.setScene(kayd(ll.getText(), primaryStage));
							} catch (Exception e1) {
								// TODO Auto-generated catch blockSV
								e1.printStackTrace();
							}
							primaryStage.show();
						});
						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(10, 10, 0, 50));
						hsb13a5.setSpacing(40);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						Jornalsch2a.setBottom(hsb13a5);

						primaryStage.setScene(Jornalschs2a);
						primaryStage.show();

					}

				}
			};
			bills1a.setOnAction(evenxda);

			EventHandler<ActionEvent> evenxa = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					if (bills2a.getValue() == "ادخال سند            ") {

						ImageView back1 = new ImageView("dsBuffer.bmp.png");
						back1.setPreserveRatio(true);
						Button bk1 = new Button("رجوع", back1);
						bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151 = new Label(
								"                                    سند قبض                            ");
						logon151.setFont(Font.font(35));

						bk1.setOnAction(ss -> {
							primaryStage.setScene(scene1A);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("تسجيل الخروج", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk1, logon151, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
						pxa.setTop(hh15);

						StackPane root51 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root51.setBackground(bGround5);
						pxa.setCenter(root51);
						String[] cointxt = { "شيقل", "دولار", "دينار" };
						ComboBox coinCombo = new ComboBox(FXCollections.observableArrayList(cointxt));
						coinCombo.setStyle("-fx-font-size: 10pt;-fx-font-weight: bold;");
						coinCombo.setPromptText("اختر عملة");

						TextField status = new TextField();
						status.setPrefColumnCount(6);
						status.setText("جديد");
						status.setDisable(true);

						Rectangle ttop = new Rectangle(400, 40, 1100, 200);
						ttop.setStroke(Color.BLACK);
						ttop.setFill(Color.TRANSPARENT);
						StackPane santop = new StackPane();
						santop.setPadding(new Insets(33, 160, 0, 0));

						BorderPane sanadKabdPane = new BorderPane();
						HBox hAll = new HBox(60);
						hAll.setAlignment(Pos.CENTER);
						hAll.setPadding(new Insets(25, 10, 10, 0));
						GridPane g1 = new GridPane();
						g1.setHgap(15);
						g1.setVgap(20);
						HBox h2 = new HBox(7.5);
						HBox h3 = new HBox(15);
						TextField sanadKabdNumber = new TextField();
						TextField sanadKabdDate = new TextField();
						TextField ccv = new TextField();
						h2.getChildren().addAll(new Label("تاريخ السند  "), sanadKabdDate);
						g1.add(new Label("رقم السند  "), 0, 0);
						g1.add(sanadKabdNumber, 1, 0);
						g1.add(new Label("العملة"), 0, 1);
						g1.add(coinCombo, 1, 1);
						g1.add(new Label("رقم الفاتوره  "), 0, 2);
						g1.add(ccv, 1, 2);

						Button fin = new Button("جد الفاتورة");
						g1.add(fin, 2, 2);

						h3.getChildren().addAll(new Label(" الحاله "), status);
						g1.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
						h3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
						hAll.getChildren().addAll(g1, h2, h3);
						h2.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");

						sanadKabdPane.setTop(santop);
						santop.getChildren().addAll(ttop, hAll);
						/// Top ^
						TextField accNumber = new TextField();
						accNumber.setDisable(true);
						accNumber.setText("111");
						TextField Name = new TextField();
						Name.setDisable(true);
						Name.setText("Cash");
						TextField Cost = new TextField();
						TextField note = new TextField();

						TextField accNumber2 = new TextField();
						accNumber2.setDisable(true);
						accNumber2.setText("410");
						TextField Name2 = new TextField();
						Name2.setDisable(true);
						Name2.setText("Revenue");
						TextField Cost2 = new TextField();
						TextField note2 = new TextField();

						TextField accNumber22 = new TextField();
						accNumber22.setDisable(true);
						accNumber22.setText("220");
						TextField Name22 = new TextField();
						Name22.setDisable(true);
						Name22.setText("Value Added tax Provision");
						TextField Cost22 = new TextField();
						TextField note22 = new TextField();

						Rectangle tcent = new Rectangle(400, 130, 1100, 360);
						tcent.setStroke(Color.BLACK);
						tcent.setFill(Color.TRANSPARENT);
						StackPane stcent = new StackPane();
						stcent.setPadding(new Insets(0, 160, 0, 0));
						GridPane g2 = new GridPane();
						g2.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
						g2.setPadding(new Insets(30, 0, 0, 0));
						g2.setHgap(45);
						g2.setVgap(10);
						Label mm3 = new Label("مدين :");
						mm3.setStyle("-fx-font-size: 15pt;-fx-font-weight: bold;");
						mm3.setUnderline(true);
						g2.add(mm3, 0, 0);
						g2.add(new Label("رقم الحساب"), 0, 1);
						g2.add(new Label("الاسم"), 1, 1);
						g2.add(new Label("المبلغ"), 2, 1);
						g2.add(new Label("ملاحظات"), 3, 1);

						g2.add(accNumber, 0, 2);
						g2.add(Name, 1, 2);
						g2.add(Cost, 2, 2);
						g2.add(note, 3, 2);
						Label mm = new Label("دائن :");
						mm.setUnderline(true);
						mm.setStyle("-fx-font-size: 15pt;-fx-font-weight: bold;");
						g2.add(mm, 0, 3);
						g2.add(new Label("رقم "), 0, 4);
						g2.add(new Label("الاسم"), 1, 4);
						g2.add(new Label("المبلغ"), 2, 4);
						g2.add(new Label("ملاحظات"), 3, 4);

						g2.add(accNumber2, 0, 5);
						g2.add(Name2, 1, 5);
						g2.add(Cost2, 2, 5);
						g2.add(note2, 3, 5);
						g2.add(accNumber22, 0, 6);
						g2.add(Name22, 1, 6);
						g2.add(Cost22, 2, 6);
						g2.add(note22, 3, 6);
						Button bst = new Button(" فحص الحاله ");
						bst.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						g2.add(bst, 0, 7);
						Label gr = new Label("متوازن ");
						Label gr2 = new Label("غير متوازن");

						g2.setAlignment(Pos.TOP_CENTER);
						stcent.getChildren().addAll(tcent, g2);
						sanadKabdPane.setCenter(stcent);

						// center ^
						sanadKabdNumber.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									Cost.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});
						Cost.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									Cost.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});

						Cost2.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									Cost2.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});
						Cost22.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									Cost22.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});

						sanadKabdNumber.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									Cost.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});

						ccv.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									ccv.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});

						GridPane g3 = new GridPane();

						Button save = new Button("حفظ");
						save.setOnAction(f -> {
							status.setText("مسوده");

							try {
								input.add(new SanadKabd(Long.parseLong(sanadKabdNumber.getText()),
										coinCombo.getValue().toString(), Long.parseLong(ccv.getText()),
										sanadKabdDate.getText(), new Accounts(Long.parseLong(accNumber.getText()),
												Name.getText(), Double.parseDouble(Cost.getText()), note.getText())));
								try {
									input.get(input.size() - 1).saveSanad();
								} catch (Exception n) {
									JOptionPane.showMessageDialog(null, "تم حفظ السند من قبل ");
								}

							} catch (Exception c) {
								System.out.println(c.getMessage());
							}
						});
						save.setPrefSize(120, 30);
						Button printt = new Button("طباعة");
						printt.setPrefSize(120, 30);
						Button tarhel = new Button("ترحيل");
						tarhel.setOnAction(h -> {

							String rr = "";
							try {
								Scanner inFile = new Scanner(refresh);
								while(inFile.hasNextLine()) {
									rr+= inFile.nextLine()+"\n";
								}
							}
							catch(Exception g) {
								
							}
							try(PrintWriter p = new PrintWriter(refresh)){
								long n=0;

								long n2=0;
								long n3=0;
								try {
									n=Long.parseLong(accNumber.getText());
								}
								catch(Exception m) {
									 n=0;
								}
								
								try {
									n2=Long.parseLong(accNumber2.getText());
								}
								catch(Exception m) {
									 n2=0;
								}
								try {
									n3=Long.parseLong(accNumber22.getText());
								}
								catch(Exception m) {
									 n3=0;
								}
								
								
								if(rr == "") {   // assets and expense  drawings
									if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
									
										p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
									
									}
									else if(n!=0){
										p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
										
									}
								
									if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
										
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
									
									}
									else if(n2!=0){
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
										
									}
									if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
										
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
									
									}
									else if(n3!=0){
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
										
									}
								}
								else {
									p.append(rr);
									if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
										
										p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
									
									}
									else if(n!=0){
										p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
										
									}
									
									if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
										
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
									
									}
									else if(n2!=0){
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
										
									}
									if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
										
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
									
									}
									else if(n3!=0){
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
										
									}
										
								}
							} catch (FileNotFoundException e1) {
								System.out.println("Creat Refresh file");
							}
status.setText("مرحل");
						});
						tarhel.setPrefSize(120, 30);
						Button printAndTarhel = new Button("Clear");
						printAndTarhel.setOnAction(wa->{
							sanadKabdDate.clear();
							sanadKabdNumber.clear();
							accNumber.clear();
						
							accNumber2.clear();
							accNumber22.clear();
							
							Name.clear();
						
							Name2.clear();
							Name22.clear();
							Cost.clear();
						
							Cost2.clear();
							Cost22.clear();

							note.clear();
						
							note2.clear();
							note22.clear();
							status.setText("جديد");
							
							
						});
						printAndTarhel.setPrefSize(150, 30);

						g3.add(save, 0, 0);
						g3.add(tarhel, 1, 0);
						g3.add(printt, 2, 0);
						g3.add(printAndTarhel, 3, 0);
						g3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
						g3.setHgap(140);
						g3.setPadding(new Insets(0, 60, 20, 0));
						g3.setAlignment(Pos.CENTER);
						sanadKabdPane.setBottom(g3);
						root51.getChildren().add(sanadKabdPane);

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						// hsb13a5.setPrefHeight(30);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						pxa.setBottom(hsb13a5);

						fin.setOnAction(ll -> {
							for (int i = 0; i < billNumber.size(); i++) {
								if (billNumber.get(i).getBillnum().equalsIgnoreCase(ccv.getText())) {

									Cost.setText(billNumber.get(i).getShamel());
									Cost2.setText(billNumber.get(i).getUnshamel());
									Cost22.setText(billNumber.get(i).getVat());
									coinCombo.setValue(billNumber.get(i).getType());
								}
							}
						});
						bst.setOnAction(w -> {
							double summ = Double.parseDouble(Cost2.getText()) + Double.parseDouble(Cost22.getText());
							double dep = Double.parseDouble(Cost.getText());
							if (summ == dep) {
								gr2.setTextFill(Color.WHITE);
								gr2.setFont(Font.font(0.1));

								gr.setTextFill(Color.GREEN);
								gr.setFont(Font.font(15));

								g2.add(gr, 1, 7);
							} else if (summ != dep) {
								gr.setTextFill(Color.WHITE);
								gr.setFont(Font.font(0.1));
								gr2.setFont(Font.font(15));
								gr2.setTextFill(Color.RED);
								g2.add(gr2, 1, 7);
							}
						});

						primaryStage.setScene(selxa);
						primaryStage.show();

					} else if (bills2a.getValue() == "بحث في السندات             ") {

						ImageView back1 = new ImageView("dsBuffer.bmp.png");
						back1.setPreserveRatio(true);
						Button bk1 = new Button("رجوع", back1);
						bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151 = new Label(
								"                                    بحث في السندات                                ");
						logon151.setFont(Font.font(30));

						bk1.setOnAction(ss -> {
							primaryStage.setScene(scene1A);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("تسجيل الخروج", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setPadding(new Insets(7, 0, 0, 0));
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk1, logon151, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
						Jornalscha.setTop(hh15);

						StackPane root51 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root51.setBackground(bGround5);

						GridPane ss = new GridPane();
						ss.setHgap(40);
						Label xd = new Label("ادخل رقم السند");
						DropShadow shadow = new DropShadow();
						shadow.setOffsetX(2);
						shadow.setOffsetY(3);
						shadow.setColor(Color.SKYBLUE);
						xd.setEffect(shadow);
						TextField ll = new TextField();
						Button go = new Button(" اذهب ");
						ll.setStyle("-fx-control-inner-background:	#F3DEA1");
						ss.add(xd, 2, 0);
						ss.add(ll, 1, 0);
						ss.add(go, 0, 0);
						ss.setStyle("-fx-font-size: 22pt;-fx-font-weight: bold;");
						root51.getChildren().add(ss);
						ss.setPadding(new Insets(250, 0, 0, 370));
						Jornalscha.setCenter(root51);
						go.setOnAction(pp -> {
							try {
								primaryStage.setScene(kabd(ll.getText(), primaryStage));
								primaryStage.show();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						});
						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(10, 10, 0, 50));
						hsb13a5.setSpacing(40);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						Jornalscha.setBottom(hsb13a5);

						primaryStage.setScene(Jornalschsa);
						primaryStage.show();

					}

				}
			};
			bills2a.setOnAction(evenxa);

			EventHandler<ActionEvent> evenx3a = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					if (bills3a.getValue() == "ادخال سند            ") {

						ImageView back1 = new ImageView("dsBuffer.bmp.png");
						back1.setPreserveRatio(true);
						Button bk1 = new Button("رجوع", back1);
						bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151 = new Label(
								"                                  سند صرف                             ");
						logon151.setFont(Font.font(35));

						bk1.setOnAction(ss -> {
							primaryStage.setScene(scene1A);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("تسجيل الخروج", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk1, logon151, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
						px2aa.setTop(hh15);

						StackPane root51 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root51.setBackground(bGround5);
						px2aa.setCenter(root51);
						String[] cointxt = { "ILS", "USD", "JOD" };
						ComboBox coinCombo = new ComboBox(FXCollections.observableArrayList(cointxt));
						coinCombo.setStyle("-fx-font-size: 10pt;-fx-font-weight: bold;");
						
						coinCombo.setPromptText("اختر عملة");
					
						TextField status = new TextField();
						status.setPrefColumnCount(6);
						status.setText("جديد");
						status.setDisable(true);

						Rectangle ttop = new Rectangle(400, 40, 1100, 200);
						ttop.setStroke(Color.BLACK);
						ttop.setFill(Color.TRANSPARENT);
						StackPane santop = new StackPane();
						santop.setPadding(new Insets(33, 160, 0, 0));

						BorderPane sanadKabdPane = new BorderPane();
						HBox hAll = new HBox(40);
						hAll.setAlignment(Pos.CENTER);
						hAll.setPadding(new Insets(25, 10, 10, 0));
						GridPane g1 = new GridPane();
						g1.setHgap(10);
						g1.setVgap(20);
						HBox h2 = new HBox(7.5);
						HBox h3 = new HBox(15);
						TextField sanadKabdNumber = new TextField();
						TextField sanadKabdDate = new TextField();
						TextField xs = new TextField();
						h2.getChildren().addAll(new Label("تاريخ السند "), sanadKabdDate);
						g1.add(new Label("رقم السند "), 0, 0);
						g1.add(sanadKabdNumber, 1, 0);
						g1.add(new Label("عمله"), 0, 1);
						g1.add(coinCombo, 1, 1);
						g1.add(new Label("رقم الفاتورة "), 0, 2);
						g1.add(xs, 1, 2);
						Button fin = new Button("جد الفاتورة");
						g1.add(fin, 2, 2);

						h3.getChildren().addAll(new Label(" الحالة "), status);
						g1.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						h3.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						hAll.getChildren().addAll(g1, h2, h3);
						h2.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");

						sanadKabdPane.setTop(santop);
						santop.getChildren().addAll(ttop, hAll);
						/// Top ^
						String [] payacc ={"113","121","122","123","124"};
						ComboBox<String> accNumber = new ComboBox<>(FXCollections.observableArrayList(payacc));
						accNumber.setPromptText("Select acc #");
						
						TextField Name = new TextField();
						TextField Cost = new TextField();
						Name.setDisable(true);
						TextField note = new TextField();

						accNumber.setOnAction(p->{
							boolean flag = false;
							String[] ok = null;

							for (int i = 0; i < labelList.size(); i++) {
								String sx = labelList.get(i).getText();
								sx = sx.replaceAll("[()]", "");
								ok = sx.split(" ");
								if (ok[0].equalsIgnoreCase(accNumber.getValue())) {
									
									flag = true;
									break;
								}
							}
							if (flag) {
								switch(ok.length) {
								case 2:Name.setText(ok[1]); break;
								case 3:Name.setText(ok[1]+" "+ok[2]);break;
								case 4:Name.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
								case 5:Name.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
								}
							}
						});
					

						TextField accNumber1 = new TextField();
						accNumber1.setText("220");
						accNumber1.setDisable(true);
						TextField Name1 = new TextField();
						Name1.setText("Value Added tax Provision");
						Name1.setDisable(true);
						TextField Cost1 = new TextField();
						TextField note1 = new TextField();

						TextField accNumber2 = new TextField();
						accNumber2.setText("111");
						accNumber2.setDisable(true);
						TextField Name2 = new TextField();
						Name2.setText("Cash");
						Name2.setDisable(true);
						TextField Cost2 = new TextField();
						TextField note2 = new TextField();

						TextField accNumber22 = new TextField();
						TextField Name22 = new TextField();
						TextField Cost22 = new TextField();
						TextField note22 = new TextField();

						Rectangle tcent = new Rectangle(400, 130, 1100, 360);
						tcent.setStroke(Color.BLACK);
						tcent.setFill(Color.TRANSPARENT);
						StackPane stcent = new StackPane();
						stcent.setPadding(new Insets(0, 160, 0, 0));
						GridPane g2 = new GridPane();
						g2.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						g2.setPadding(new Insets(30, 0, 0, 0));
						g2.setHgap(45);
						g2.setVgap(10);
						Label mm3 = new Label("دائن :");
						mm3.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						mm3.setUnderline(true);
						g2.add(mm3, 0, 0);
						g2.add(new Label("رقم الحساب"), 0, 1);
						g2.add(new Label("اسم الحساب"), 1, 1);
						g2.add(new Label("المبلغ"), 2, 1);
						g2.add(new Label("ملاحظات"), 3, 1);

						g2.add(accNumber, 0, 2);
						g2.add(Name, 1, 2);
						g2.add(Cost, 2, 2);
						g2.add(note, 3, 2);

						g2.add(accNumber1, 0, 3);
						g2.add(Name1, 1, 3);
						g2.add(Cost1, 2, 3);
						g2.add(note1, 3, 3);

						Label mm = new Label("مدين");
						mm.setUnderline(true);
						mm.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						g2.add(mm, 0, 4);
						g2.add(new Label("رقم الحساب"), 0, 5);
						g2.add(new Label("اسم الحساب"), 1, 5);
						g2.add(new Label("المبلغ"), 2, 5);
						g2.add(new Label("ملاحظات"), 3, 5);

						g2.add(accNumber2, 0, 6);
						g2.add(Name2, 1, 6);
						g2.add(Cost2, 2, 6);
						g2.add(note2, 3, 6);

						Button bst = new Button(" افحص الحالة ");
						bst.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						g2.add(bst, 0, 7);
						Label gr = new Label("متوازن");
						Label gr2 = new Label("غير متوازن");
						bst.setOnAction(w -> {
							double summ = Double.parseDouble(Cost.getText()) + Double.parseDouble(Cost1.getText());
							double dep = Double.parseDouble(Cost2.getText());
							if (dep == summ) {
								gr2.setTextFill(Color.WHITE);
								gr2.setFont(Font.font(0.1));

								gr.setTextFill(Color.GREEN);
								gr.setFont(Font.font(15));
								try {
									g2.add(gr, 1, 7);
								} catch (Exception es) {
									// TODO: handle exception
								}
							} else if (dep != summ) {
								gr.setTextFill(Color.WHITE);
								gr.setFont(Font.font(0.1));
								gr2.setFont(Font.font(15));
								gr2.setTextFill(Color.RED);
								g2.add(gr2, 1, 7);
							}
						});

						fin.setOnAction(cc -> {
							for (int i = 0; i < billNumber2.size(); i++) {
								if (billNumber2.get(i).getBillnum().equalsIgnoreCase(xs.getText())) {
									Cost.setText(billNumber2.get(i).getUnshamel());
									Cost1.setText(billNumber2.get(i).getVat());
									Cost2.setText(billNumber2.get(i).getShamel());
								}
							}

						});

						g2.setAlignment(Pos.TOP_CENTER);
						stcent.getChildren().addAll(tcent, g2);
						sanadKabdPane.setCenter(stcent);

						// center ^

						xs.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									xs.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});
						Cost.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									Cost.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});
						Cost1.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									Cost1.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});
						Cost2.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									Cost2.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});

						GridPane g3 = new GridPane();

						Button save = new Button("حفظ");
						save.setOnAction(f -> {
status.setText("مسوده");
							try {
								input.add(new SanadSarf(Long.parseLong(sanadKabdNumber.getText()),
										coinCombo.getValue().toString(), Long.parseLong(xs.getText()),
										sanadKabdDate.getText(), new Accounts(Long.parseLong(accNumber.getValue()),
												Name.getText(), Double.parseDouble(Cost2.getText()), note.getText())));
								try {
									input.get(input.size() - 1).saveSanad();
								} catch (Exception n) {
									JOptionPane.showMessageDialog(null, "تم حفظ السند من قبل ");
								}

							} catch (Exception c) {
								System.out.println(c.getMessage());
							}
						});
						save.setPrefSize(120, 30);
						Button printt = new Button("طباعة");
						printt.setPrefSize(120, 30);
						Button tarhel = new Button("ترحيل");
						tarhel.setPrefSize(120, 30);
						tarhel.setOnAction(h -> {

							String rr = "";
							try {
								Scanner inFile = new Scanner(refresh);
								while(inFile.hasNextLine()) {
									rr+= inFile.nextLine()+"\n";
								}
							}
							catch(Exception g) {
								
							}
							try(PrintWriter p = new PrintWriter(refresh)){
								long n=0;
								long n1=0;
								long n2=0;
								long n3=0;
								try {
									n=Long.parseLong(accNumber.getValue());
								}
								catch(Exception m) {
									 n=0;
								}
								try {
									n1=Long.parseLong(accNumber1.getText());
								}
								catch(Exception m) {
									 n1=0;
								}
								try {
									n2=Long.parseLong(accNumber2.getText());
								}
								catch(Exception m) {
									 n2=0;
								}
								try {
									n3=Long.parseLong(accNumber22.getText());
								}
								catch(Exception m) {
									 n3=0;
								}
								
								
								if(rr == "") {   // assets and expense  drawings
									if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
									
										p.append(accNumber.getValue()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
									
									}
									else if(n!=0){
										p.append(accNumber.getValue()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
										
									}
									if((n1>110 && n1<125) || (n1==340) || (n1>509 && n1<551) && n1!=0) {
										
										p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",+"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
									
									}
									else if(n1!=0){
										p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",-"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
										
									}
									if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
										
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
									
									}
									else if(n2!=0){
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
										
									}
									if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
										
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
									
									}
									else if(n3!=0){
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
										
									}
								}
								else {
									p.append(rr);
									if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
										
										p.append(accNumber.getValue()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
									
									}
									else if(n!=0){
										p.append(accNumber.getValue()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
										
									}
									if((n1>110 && n1<125) || (n1==340) || (n1>509 && n1<551) && n1!=0) {
										
										p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",+"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
									
									}
									else if(n1!=0){
										p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",-"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
										
									}
									if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
										
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
									
									}
									else if(n2!=0){
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
										
									}
									if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
										
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
									
									}
									else if(n3!=0){
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
										
									}
										
								}
							} catch (FileNotFoundException e1) {
								System.out.println("Creat Refresh file");
							}

							status.setText("مرحل");
						});
						Button printAndTarhel = new Button("Clear");
						printAndTarhel.setOnAction(wa->{
							sanadKabdDate.clear();
							sanadKabdNumber.clear();
						//	accNumber.clear();
							accNumber1.clear();
							accNumber2.clear();
							accNumber22.clear();
							
							Name.clear();
							Name1.clear();
							Name2.clear();
							Name22.clear();
							Cost.clear();
							Cost1.clear();
							Cost2.clear();
							Cost22.clear();

							note.clear();
							note1.clear();
							note2.clear();
							note22.clear();
							status.setText("جديد");
							
						});
						printAndTarhel.setPrefSize(150, 30);

						g3.add(save, 0, 0);
						g3.add(tarhel, 1, 0);
						g3.add(printt, 2, 0);
						g3.add(printAndTarhel, 3, 0);
						g3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
						g3.setHgap(140);
						g3.setPadding(new Insets(0, 60, 20, 0));
						g3.setAlignment(Pos.CENTER);
						sanadKabdPane.setBottom(g3);
						root51.getChildren().add(sanadKabdPane);

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						// hsb13a5.setPrefHeight(30);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						px2aa.setBottom(hsb13a5);

						primaryStage.setScene(selx2aa);
						primaryStage.show();

					} else if (bills3a.getValue() == "بحث في السندات             ") {

						ImageView back1 = new ImageView("dsBuffer.bmp.png");
						back1.setPreserveRatio(true);
						Button bk1 = new Button("رجوع", back1);
						bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151 = new Label(
								"                                    بحث في السندات                                ");
						logon151.setFont(Font.font(30));

						bk1.setOnAction(ss -> {
							primaryStage.setScene(scene1A);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("تسجيل الخروج", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setPadding(new Insets(7, 0, 0, 0));
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk1, logon151, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
						Jornalsch1a.setTop(hh15);

						StackPane root51 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root51.setBackground(bGround5);

						GridPane ss = new GridPane();
						ss.setHgap(40);
						Label xd = new Label("ادخل رقم السند");
						DropShadow shadow = new DropShadow();
						shadow.setOffsetX(2);
						shadow.setOffsetY(3);
						shadow.setColor(Color.SKYBLUE);
						xd.setEffect(shadow);
						TextField ll = new TextField();
						Button go = new Button(" اذهب ");
						go.setOnAction(p -> {
							try {
								primaryStage.setScene(sarf(ll.getText(), primaryStage));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							primaryStage.show();
						});
						ll.setStyle("-fx-control-inner-background:	#F3DEA1");
						ss.add(xd, 2, 0);
						ss.add(ll, 1, 0);
						ss.add(go, 0, 0);
						ss.setStyle("-fx-font-size: 22pt;-fx-font-weight: bold;");
						root51.getChildren().add(ss);
						ss.setPadding(new Insets(250, 0, 0, 370));
						Jornalsch1a.setCenter(root51);

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(10, 10, 0, 50));
						hsb13a5.setSpacing(40);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						Jornalsch1a.setBottom(hsb13a5);

						primaryStage.setScene(Jornalschs1a);
						primaryStage.show();

					}

				}
			};
			bills3a.setOnAction(evenx3a);

			EventHandler<ActionEvent> evenba = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					if (billsa.getValue() == "فاتورة المبيعات               ") {
						ImageView back = new ImageView("dsBuffer.bmp.png");
						back.setPreserveRatio(true);
						Button bk = new Button("رجوع", back);
						bk.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon15 = new Label(
								"                                   فاتورة المبيعات                       ");
						logon15.setFont(Font.font(35));

						bk.setOnAction(ss -> {
							primaryStage.setScene(scene1A);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("تسجيل الخروج", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk, logon15, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon15.setFont(Font.font(null, FontWeight.BOLD, logon15.getFont().getSize()));
						pa.setTop(hh15);

						StackPane root5 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root5.setBackground(bGround5);
						pa.setCenter(root5);

						GridPane grid = new GridPane();
						grid.setHgap(15);
						grid.setVgap(20);
						grid.setPadding(new Insets(20, 20, 20, 20));

						Label dat = new Label("التاريخ :");
						dat.setFont(Font.font(25));
						dat.setStyle("-fx-font-weight: bold;");
						dat.setUnderline(true);

						Label numb = new Label("رقم الفاتورة :");
						numb.setFont(Font.font(25));
						numb.setStyle("-fx-font-weight: bold;");
						numb.setUnderline(true);
						grid.setAlignment(Pos.TOP_LEFT);

						TextField datef = new TextField();
						datef.setStyle("-fx-font-weight: bold;-fx-font-color: black;");
						datef.setFont(Font.font(15));

						datef.setFont(Font.font(15));
						TextField billn = new TextField();
						grid.add(dat, 0, 0);
						grid.add(datef, 1, 0);
						grid.add(numb, 0, 1);
						grid.add(billn, 1, 1);
						BorderPane sba = new BorderPane();
						root5.getChildren().add(sba);
						sba.setTop(grid);
						billn.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									billn.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});

						StackPane stak2 = new StackPane();
						stak2.setAlignment(Pos.CENTER);

						Rectangle r1 = new Rectangle(0, 10, 1200, 400);
						r1.setStroke(Color.BLACK);
						r1.setFill(Color.TRANSPARENT);

						Rectangle r2 = new Rectangle(820, 10, 380, 400);
						r2.setStroke(Color.BLACK);
						r2.setFill(Color.TRANSPARENT);

						Group gb = new Group();
						gb.getChildren().addAll(r1, r2);
						stak2.getChildren().addAll(gb);
						sba.setCenter(stak2);
						BorderPane por = new BorderPane();
						// sba.setAlignment(Pos.CENTER);

						GridPane grid2 = new GridPane();
						VBox vbox = new VBox();
						HBox h12 = new HBox();
						VBox vbox2 = new VBox();

						vbox.setAlignment(Pos.CENTER);
						Label ttyb = new Label("نوع التذكرة:");
						ttyb.setFont(Font.font(25));
						ttyb.setStyle("-fx-font-weight: bold;");
						Label tnum = new Label("عدد التذاكر:");
						tnum.setFont(Font.font(25));
						tnum.setStyle("-fx-font-weight: bold;");
						// dat.setUnderline(true);

						Label tpric = new Label(" سعر التذكرة:   ");
						tpric.setFont(Font.font(25));
						tpric.setStyle("-fx-font-weight: bold;");
						// dat.setUnderline(true);
						String[] ts = { "امريكا", "فرنسا", "الامارات" };
						ComboBox<String> tybe = new ComboBox<>(FXCollections.observableArrayList(ts));
						tybe.setPromptText(" اختر نوع ");
						tybe.setStyle("-fx-font-size: 15pt;-fx-border-color:PLUM;-fx-font-weight: bold;");
						TextField tnf = new TextField();
						tnf.setPrefSize(15, 50);
						TextField tpf = new TextField();
						tpf.setFont(Font.font(25));
						tpf.setStyle("-fx-font-weight: bold;");
						tpf.setDisable(true);
						tpf.setPrefSize(15, 50);

						EventHandler<ActionEvent> even12 = new EventHandler<ActionEvent>() {
							public void handle(ActionEvent e) {
								if (tybe.getValue() == "امريكا") {

									tpf.setText("1200");
								} else if (tybe.getValue() == "الامارات") {
									tpf.setText("900");
								} else {
									tpf.setText("1300");
								}
							}
						};

						tybe.setOnAction(even12);

						grid2.setVgap(20);
						grid2.setHgap(60);
						grid2.add(ttyb, 0, 0);
						grid2.add(tybe, 0, 1);

						grid2.add(tnum, 1, 0);
						grid2.add(tnf, 1, 1);

						grid2.add(tpric, 2, 0);
						grid2.add(tpf, 2, 1);

						stak2.setPadding(new Insets(0, 175, 150, 0));
						// vbox.setPadding(new Insets(0, 100, 0, 0));
						// por.setPadding(new Insets(0,100,100,0));
						vbox.setSpacing(20);
						TextArea ta = new TextArea();
						ta.setMaxSize(600, 150);

						VBox v2 = new VBox();
						v2.setAlignment(Pos.CENTER);
						GridPane gg2 = new GridPane();

						Label amao = new Label(" المبلغ غير شامل ضريبة القيمة المضافة :");
						amao.setFont(Font.font(20));
						amao.setStyle("-fx-font-weight: bold;");

						TextField amof = new TextField();
						amof.setPrefHeight(50);

						Label amao1 = new Label(" القيمة المضافة :");
						amao1.setFont(Font.font(20));
						amao1.setStyle("-fx-font-weight: bold;");

						TextField amof1 = new TextField();
						amof1.setPrefHeight(50);

						Label amao2 = new Label("  المبلغ شامل ضريبة القيمة المضافة  :");
						amao2.setFont(Font.font(20));
						amao2.setStyle("-fx-font-weight: bold;");

						TextField amof2 = new TextField();
						amof2.setPrefHeight(50);
						gg2.setVgap(15);
						v2.setAlignment(Pos.TOP_LEFT);

						gg2.add(amao, 0, 0);
						gg2.add(amof, 0, 1);

						gg2.add(amao1, 0, 2);
						gg2.add(amof1, 0, 3);

						gg2.add(amao2, 0, 4);
						gg2.add(amof2, 0, 5);
						por.setPadding(new Insets(0, 0, 0, 192));
						v2.getChildren().addAll(gg2);
						v2.setPadding(new Insets(13, 0, 0, 0));
						// vbox.setPadding(new Insets(15,0,60,0));
						h12.getChildren().addAll(vbox, v2);
						por.setCenter(vbox2);
						stak2.getChildren().add(por);
						h12.setSpacing(220);
						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(10, 10, 0, 0));
						hsb13a5.setSpacing(40);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						pa.setBottom(hsb13a5);

						HBox butt = new HBox();
						vbox2.getChildren().addAll(h12, butt);

						ImageView add = new ImageView("images.png");
						Button btAdd = new Button("اضافة", add);
						btAdd.setStyle(
								"-fx-font-size: 13pt;-fx-border-color:blue;-fx-font-weight: bold;-fx-background-color:skyblue;");
						add.setPreserveRatio(true);

						ImageView cal = new ImageView("imhhhhh.png");
						Button calc = new Button("حساب", cal);
						calc.setStyle(
								"-fx-font-size: 13pt;-fx-border-color:black;-fx-font-weight: bold;-fx-background-color:wheat;");
						cal.setPreserveRatio(true);
						butt.setPadding(new Insets(0, 0, 10, 0));
						butt.getChildren().addAll(btAdd, calc);
						butt.setSpacing(518);
						vbox.getChildren().addAll(grid2, ta);
						amof.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						amof1.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						amof2.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						ta.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						amof.setDisable(true);
						amof1.setDisable(true);
						amof2.setDisable(true);
						btAdd.setOnAction(x -> {

							int sum = 0;
							try {
								sum = Integer.parseInt(tpf.getText()) * Integer.parseInt(tnf.getText());
							} catch (Exception l) {
								System.out.print(l.getMessage());
							}
							ss = ss + sum;
							zz += "\n" + tybe.getValue() + " : " + (sum);
							ta.setText(zz);
							tnf.clear();
						});

						calc.setOnAction(ssz -> {
							String[] s = ttg.getSelectedToggle().toString().split(" ");
							ta.clear();
							ss1 = ss * 0.16;
							amof.setText(String.valueOf(ss));
							amof1.setText(String.valueOf(ss1));
							amof2.setText(String.valueOf(ss + ss1));
							billNumber.add(new Bill(billn.getText(), datef.getText(), amof2.getText(), amof.getText(),
									amof1.getText(), s[1]));
							bi.write(billNumber.get(billNumber.size() - 1).toString() + "\n");
				
							datef.clear();
							billn.clear();
							ss = 0;
							ss1 = 0;
							zz = "";
							bi.flush();

						});

						primaryStage.setScene(sela);
						primaryStage.show();

					} else if (billsa.getValue() == "فاتورة المشتريات              ") {
						ImageView back = new ImageView("dsBuffer.bmp.png");
						back.setPreserveRatio(true);
						Button bk = new Button("رجوع", back);
						bk.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon15 = new Label(
								"                                       فاتورة المشتريات               ");
						logon15.setFont(Font.font(35));

						bk.setOnAction(ss -> {
							primaryStage.setScene(scene1A);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("تسجيل الخروج", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk, logon15, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon15.setFont(Font.font(null, FontWeight.BOLD, logon15.getFont().getSize()));
						pna.setTop(hh15);

						StackPane root5 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root5.setBackground(bGround5);
						pna.setCenter(root5);

						GridPane grid = new GridPane();
						grid.setHgap(15);
						grid.setVgap(20);
						grid.setPadding(new Insets(20, 20, 20, 20));

						Label dat = new Label("التاريخ ");
						dat.setFont(Font.font(25));
						dat.setStyle("-fx-font-weight: bold;");
						dat.setUnderline(true);

						Label numb = new Label("رقم الفاتورة");
						numb.setFont(Font.font(25));
						numb.setStyle("-fx-font-weight: bold;");
						numb.setUnderline(true);
						grid.setAlignment(Pos.TOP_LEFT);

						TextField datef = new TextField();
						datef.setStyle("-fx-font-weight: bold;-fx-font-color: black;");
						datef.setFont(Font.font(15));

						TextField billn = new TextField();
						grid.add(dat, 0, 0);
						grid.add(datef, 1, 0);
						grid.add(numb, 0, 1);
						grid.add(billn, 1, 1);

						billn.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									billn.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});

						BorderPane sba = new BorderPane();
						root5.getChildren().add(sba);
						sba.setTop(grid);

						StackPane stak2 = new StackPane();
						stak2.setAlignment(Pos.CENTER);

						Rectangle r1 = new Rectangle(0, 10, 1200, 400);
						r1.setStroke(Color.BLACK);
						r1.setFill(Color.TRANSPARENT);

						Rectangle r2 = new Rectangle(820, 10, 380, 400);
						r2.setStroke(Color.BLACK);
						r2.setFill(Color.TRANSPARENT);

						Group gb = new Group();
						gb.getChildren().addAll(r1, r2);
						stak2.getChildren().addAll(gb);
						sba.setCenter(stak2);
						BorderPane por = new BorderPane();
						// sba.setAlignment(Pos.CENTER);

						GridPane grid2 = new GridPane();
						VBox vbox = new VBox();
						HBox h12 = new HBox();
						VBox vbox2 = new VBox();

						vbox.setAlignment(Pos.CENTER);
						Label ttyb = new Label("اسم الغرض");
						ttyb.setFont(Font.font(25));
						ttyb.setStyle("-fx-font-weight: bold;");
						Label tnum = new Label("  سعر الغرض     ");
						tnum.setFont(Font.font(25));
						tnum.setStyle("-fx-font-weight: bold;");
						// dat.setUnderline(true);

						// dat.setUnderline(true);
						TextField namef = new TextField();
						namef.setStyle("-fx-font-size: 13pt;-fx-border-color:PLUM;-fx-font-weight: bold;");
						TextField tnf = new TextField();
						tnf.setStyle("-fx-font-weight: bold;");
						tnf.setPrefSize(15, 50);

						grid2.setVgap(20);
						grid2.setHgap(100);
						grid2.add(ttyb, 0, 0);
						grid2.add(namef, 0, 1);

						grid2.add(tnum, 1, 0);
						grid2.add(tnf, 1, 1);

						stak2.setPadding(new Insets(0, 175, 150, 0));
						// vbox.setPadding(new Insets(0, 100, 0, 0));
						// por.setPadding(new Insets(0,100,100,0));
						TextArea ta = new TextArea();
						ta.setMaxSize(600, 150);

						VBox v2 = new VBox();
						v2.setAlignment(Pos.CENTER);
						GridPane gg2 = new GridPane();

						Label amao = new Label(" المبلغ شامل ضريبة القيمة المضافة");
						amao.setFont(Font.font(20));
						amao.setStyle("-fx-font-weight: bold;");

						TextField amof = new TextField();
						amof.setPrefHeight(25);

						Label amao1 = new Label("ضريبة القيمه المضافة");
						amao1.setFont(Font.font(20));
						amao1.setStyle("-fx-font-weight: bold;");

						TextField amof1 = new TextField();
						amof1.setPrefHeight(25);

						Label amao2 = new Label(" المبلغ غير شامل ضريبة القيمة المضافة");
						amao2.setFont(Font.font(20));
						amao2.setStyle("-fx-font-weight: bold;");

						TextField amof2 = new TextField();
						amof2.setPrefHeight(25);
						gg2.setVgap(15);
						v2.setAlignment(Pos.TOP_LEFT);
						vbox.setSpacing(20);
						gg2.add(amao, 0, 0);
						gg2.add(amof, 0, 1);

						gg2.add(amao1, 0, 2);
						gg2.add(amof1, 0, 3);

						gg2.add(amao2, 0, 4);
						gg2.add(amof2, 0, 5);
						por.setPadding(new Insets(0, 0, 0, 185));
						v2.getChildren().addAll(gg2);
						v2.setPadding(new Insets(10, 0, 0, 0));
						h12.getChildren().addAll(vbox, v2);
						por.setCenter(vbox2);
						stak2.getChildren().add(por);
						h12.setSpacing(240);
						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(10, 10, 0, 0));
						hsb13a5.setSpacing(40);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						pna.setBottom(hsb13a5);

						HBox butt = new HBox();
						vbox2.getChildren().addAll(h12, butt);

						ImageView add = new ImageView("images.png");
						Button btAdd = new Button("اضافة", add);
						btAdd.setStyle(
								"-fx-font-size: 13pt;-fx-border-color:blue;-fx-font-weight: bold;-fx-background-color:skyblue;");
						add.setPreserveRatio(true);

						ImageView cal = new ImageView("imhhhhh.png");
						Button calc = new Button("حساب", cal);
						calc.setStyle(
								"-fx-font-size: 13pt;-fx-border-color:black;-fx-font-weight: bold;-fx-background-color:wheat;");
						cal.setPreserveRatio(true);
						butt.setPadding(new Insets(0, 0, 20, 0));
						butt.getChildren().addAll(btAdd, calc);
						butt.setSpacing(518);
						vbox.getChildren().addAll(grid2, ta);
						amof.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						amof1.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						amof2.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						ta.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						amof.setDisable(true);
						amof1.setDisable(true);
						amof2.setDisable(true);
						btAdd.setOnAction(x -> {

							int sum = Integer.parseInt(tnf.getText());
							ss2 = ss2 + sum;
							zz2 += "\n" + namef.getText() + " : " + (sum);
							ta.setText(zz2);
							tnf.clear();
							namef.clear();
						});

						calc.setOnAction(ssz -> {
							String[] ss = ttg.getSelectedToggle().toString().split(" ");

							ss12 = ss2 * 0.16;
							amof.setText(String.valueOf(ss2));
							amof1.setText(String.valueOf(ss12));
							amof2.setText(String.valueOf(ss2-ss12));
							billNumber2.add(new Bill2(billn.getText(), datef.getText(), amof.getText(), amof2.getText(),
									amof1.getText(), ss[1]));
							bi2.write(billNumber2.get(billNumber2.size() - 1).toString() + "\n");
							namef.clear();
							datef.clear();
							billn.clear();
							ta.clear();
							bi2.flush();
							ss2 = 0;
							ss12 = 0;
							zz2 = "";

						});

						primaryStage.setScene(selna);
						primaryStage.show();

					}

					else if (billsa.getValue() == "بحث في الفواتير           ") {

						ImageView back1 = new ImageView("dsBuffer.bmp.png");
						back1.setPreserveRatio(true);
						Button bk1 = new Button("رجوع", back1);
						bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151 = new Label(
								"                                    بحث في الفواتير                                 ");
						logon151.setFont(Font.font(30));

						bk1.setOnAction(ss -> {
							primaryStage.setScene(scene1A);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("تسجيل الخروج", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setPadding(new Insets(7, 0, 0, 0));
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk1, logon151, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
						Jornalsch11a.setTop(hh15);

						BorderPane serh = new BorderPane();
						StackPane root51 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root51.setBackground(bGround5);

						GridPane ss = new GridPane();
						ss.setHgap(40);
						Label xd = new Label("ادخل رقم الفاتورة");
						DropShadow shadow = new DropShadow();
						shadow.setOffsetX(2);
						shadow.setOffsetY(3);
						shadow.setColor(Color.SKYBLUE);
						xd.setEffect(shadow);
						TextField ll = new TextField();

						ll.setStyle("-fx-control-inner-background:	#F3DEA1");
						ss.add(xd, 2, 0);
						ss.add(ll, 1, 0);

						ss.setStyle("-fx-font-size: 22pt;-fx-font-weight: bold;");
						root51.getChildren().add(serh);
						serh.setTop(ss);
						ss.setAlignment(Pos.TOP_CENTER);
						// ss.setPadding(new Insets(100, 0, 0, 0));
						Button go = new Button("بحث");
						ss.add(go, 0, 0);
						Jornalsch11a.setCenter(root51);
						TextArea tt = new TextArea();
						serh.setCenter(tt);
						tt.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						serh.setPadding(new Insets(100, 100, 100, 100));
						String o = "";
						go.setOnAction(l -> {
							tt.clear();

							for (int k = 0; k < billNumber.size(); k++) {
								if (billNumber.get(k).getBillnum().equalsIgnoreCase(ll.getText())) {
									tt.setText(billNumber.get(k).toStringa());
								}
							}

							for (int k = 0; k < billNumber2.size(); k++) {
								if (billNumber2.get(k).getBillnum().equalsIgnoreCase(ll.getText())) {
									tt.setText(billNumber2.get(k).toStringa());
								}
							}

						});

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(10, 10, 0, 50));
						hsb13a5.setSpacing(40);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						Jornalsch11a.setBottom(hsb13a5);

						primaryStage.setScene(Jornalschs11a);
						primaryStage.show();

					}
				}
			};
			billsa.setOnAction(evenba);

			EventHandler<ActionEvent> inceva = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					if (combup2a.getValue() == "قائمة الدخل                   ") {
						ImageView back = new ImageView("dsBuffer.bmp.png");
						back.setPreserveRatio(true);
						Button bk = new Button("رجوع", back);
						bk.setStyle("-fx-font-size: 12pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon15 = new Label(
								"                                         قائمة الدخل                                        ");
						logon15.setFont(Font.font(30));

						bk.setOnAction(ss -> {
							primaryStage.setScene(scene1A);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("تسجيل الخروج", limg5);
						out5.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 30);
						hh15.getChildren().addAll(bk, logon15, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon15.setFont(Font.font(null, FontWeight.BOLD, logon15.getFont().getSize()));
						incsta.setTop(hh15);

						StackPane root5 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root5.setBackground(bGround5);
						incsta.setCenter(root5);

						BorderPane ss = new BorderPane();
						root5.getChildren().add(ss);

						ss.setPadding(new Insets(7, 20, 0, 0));

						Label compn = new Label(" شركة AMW ");
						compn.setStyle("-fx-font-size: 22pt;-fx-font-style:italic;-fx-font-weight: bold;");

						Label inc = new Label(" قائمة الدخل");
						inc.setAlignment(Pos.CENTER);
						inc.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");

						TextField fdat = new TextField();
						TextField tdat = new TextField();
						HBox dh = new HBox(3);
						dh.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						Button go = new Button("إصدار");
						dh.getChildren().addAll(new Label("For year"), fdat, go);
						VBox toop = new VBox(8);
						toop.setAlignment(Pos.CENTER);

						Label r = new Label("Revenue");

						Label s = new Label("Salaries and wages expense");

						Label i = new Label("Insuranse expense");

						Label d = new Label("Depreciation Expense");

						Label g = new Label("General Purchase Expense");

						Label u = new Label("Utility expense");

						Label te = new Label("Total expense");

						Label n = new Label("Net income");


						// toop.setPadding(new Insets(0,0,0,0));
						toop.getChildren().addAll(compn, inc, dh);

						ImageView prnt = new ImageView("machine.png");
						ImageView eml = new ImageView("emil.jpg");
						Button print = new Button("طباعة", prnt);
						Button email = new Button("ارسال بريد", eml);

						HBox hh = new HBox(400);
						hh.setPadding(new Insets(4, 0, 0, 30));
						hh.getChildren().addAll(email, toop, print);
						ss.setTop(hh);

						Label sal = new Label("ايرادات المبيعات :");
						sal.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");
						sal.setUnderline(true);

						Label ex = new Label("نفقات :");
						ex.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");
						ex.setUnderline(true);

						GridPane grid = new GridPane();

						grid.setAlignment(Pos.CENTER);
						grid.setHgap(290);
						grid.setVgap(5);
						// grid.setPadding(new Insets(0, 10, 0, 0));
						grid.add(sal, 0, 0);
						grid.add(new Label("ربح "), 0, 1);
						grid.add(new Label(""), 0, 2);
						grid.add(ex, 0, 3);

						grid.add(new Label("نفقات الرواتب والأجور"), 0, 4);
						grid.add(new Label("مصاريف التأمين"), 0, 5);
						grid.add(new Label("مصاريف الاستهلاك"), 0, 6);
						grid.add(new Label("مصاريف الشراء العامة"), 0, 7);
						grid.add(new Label("حساب المرافق"), 0, 8);
						grid.add(new Label("المصاريف الكلية"), 0, 9);
						grid.add(new Label(""), 0, 10);

						grid.add(new Label("صافي الدخل "), 0, 11);
						
						grid.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");

						TextField t = new TextField();
						t.setDisable(true);
						TextField t1 = new TextField();
						t1.setDisable(true);
						TextField t2 = new TextField();
						t2.setDisable(true);
						TextField t3 = new TextField();
						t3.setDisable(true);
						TextField t4 = new TextField();
						t4.setDisable(true);

						TextField t5 = new TextField();
						t5.setDisable(true);
						TextField t6 = new TextField();
						t6.setDisable(true);
						TextField t7 = new TextField();
						t7.setDisable(true);
						TextField t8 = new TextField();
						TextField t9 = new TextField();
						t9.setDisable(true);

						t8.setDisable(true);
						grid.add(t, 1, 1);

						grid.add(t1, 1, 4);
						grid.add(t2, 1, 5);
						grid.add(t3, 1, 6);
						grid.add(t4, 1, 7);
						grid.add(t5, 1, 8);

						// grid.add(new TextField(), 9, 1);

						grid.add(t6, 1, 9);
						grid.add(t7, 1, 11);
						
						ss.setCenter(grid);

						go.setOnAction(j -> {

							try {

								t.setText("" + fillReports(r.getText(), fdat.getText()));

								t1.setText("" + fillReports(s.getText(), fdat.getText()));
								t2.setText("" + fillReports(i.getText(), fdat.getText()));
								t3.setText("" + fillReports(d.getText(), fdat.getText()));
								t4.setText("" + fillReports(g.getText(), fdat.getText()));
								t5.setText("" + fillReports(u.getText(), fdat.getText()));

								t6.setText(String.valueOf(Double.parseDouble(t2.getText())
										+ Double.parseDouble(t3.getText()) + Double.parseDouble(t1.getText())
										+ Double.parseDouble(t4.getText()) + Double.parseDouble(t5.getText())));
								t7.setText(String
										.valueOf(Double.parseDouble(t.getText()) - Double.parseDouble(t6.getText())));
								
								
								moath =Double.parseDouble(t7.getText()) ;

							} catch (Exception e1) {
								e1.printStackTrace();
							}

						});

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(20, 10, 0, 50));
						hsb13a5.setSpacing(150);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						incsta.setBottom(hsb13a5);

						primaryStage.setScene(incstsa);
						primaryStage.show();

					}

					else if (combup2a.getValue() == "ميزان المراجعة المعدل                   ") {
						ImageView back = new ImageView("dsBuffer.bmp.png");
						back.setPreserveRatio(true);
						Button bk = new Button("رجوع", back);
						bk.setStyle("-fx-font-size: 12pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon15 = new Label(
								"                                     ميزان المراجعة المعدل                          ");
						logon15.setFont(Font.font(30));

						bk.setOnAction(ss -> {
							primaryStage.setScene(scene1A);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("تسجيل الخروج", limg5);
						out5.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 30);
						hh15.getChildren().addAll(bk, logon15, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon15.setFont(Font.font(null, FontWeight.BOLD, logon15.getFont().getSize()));
						incst1a.setTop(hh15);

						StackPane root5 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root5.setBackground(bGround5);
						incst1a.setCenter(root5);

						BorderPane ss = new BorderPane();
						root5.getChildren().add(ss);

						ss.setPadding(new Insets(7, 20, 0, 0));

						Label compn = new Label(" AMW COMPANY ");
						compn.setStyle("-fx-font-size: 22pt;-fx-font-style:italic;-fx-font-weight: bold;");

						Label inc = new Label(" ميزان المراجعة المعدل");
						inc.setAlignment(Pos.CENTER);
						inc.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");

						TextField fdat = new TextField();
						TextField tdat = new TextField();
						HBox dh = new HBox(3);
						dh.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");

						Button go = new Button("إصدار");
						dh.getChildren().addAll(new Label("For year"), fdat, go);
						VBox toop = new VBox(8);
						toop.setAlignment(Pos.CENTER);

						// toop.setPadding(new Insets(0,0,0,0));
						toop.getChildren().addAll(compn, inc, dh);

						ImageView prnt = new ImageView("machine.png");
						ImageView eml = new ImageView("emil.jpg");
						Button print = new Button("طباعة", prnt);
						Button email = new Button("ارسال بريد", eml);

						HBox hh = new HBox(400);
						hh.setPadding(new Insets(4, 0, 0, 30));
						hh.getChildren().addAll(email, toop, print);
						ss.setTop(hh);

						Label sal = new Label("       مدين");
						sal.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");

						Label sal1 = new Label("      دائن");
						sal1.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");

						Label sal2 = new Label("      مدين");
						sal2.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");

						Label sal12 = new Label("     دائن");
						sal12.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");

						GridPane grid = new GridPane();

						grid.setHgap(30);
						grid.setVgap(2);
						grid.setPadding(new Insets(0, 0, 0, 30));
						grid.add(sal, 1, 0);
						grid.add(sal1, 2, 0);

						grid.add(new Label("نقدي "), 0, 1);
						grid.add(new Label("ذمم مدينه"), 0, 2);
						grid.add(new Label("مستلزمات"), 0, 3);

						grid.add(new Label("تأمين مدفوع مسبقاً"), 0, 4);
						grid.add(new Label("ألات ومعدات"), 0, 5);
						grid.add(new Label("أثاث"), 0, 6);
						grid.add(new Label("مركبات"), 0, 7);
						grid.add(new Label("بنايات"), 0, 8);
						/////////// debit

						grid.add(new Label(" الاهتلاك التراكمي"), 0, 9);

						grid.add(new Label("ذمم دائنه"), 0, 10);
						grid.add(new Label("الايرادات الخدمية غير المكتسبه"), 0, 11);
						grid.add(new Label("الرواتب والاجور الميتحقة للدفع"), 0, 12);

						grid.add(sal2, 4, 0);
						grid.add(sal12, 5, 0);

						grid.add(new Label("الفوائد المستحقة للدفع"), 0, 13);
						grid.add(new Label("مخصص ضريبة القيمة المضافة "), 3, 1);
						grid.add(new Label("التزام طويل الامد"), 3, 2);
						grid.add(new Label("رأس المال"), 3, 3);
					
						grid.add(new Label("المسحوبات"), 3, 4);
						/// credit

						grid.add(new Label("مصروفات رواتب وأجور"), 3, 5);
						grid.add(new Label("مصروف تأمين"), 3, 6);
						grid.add(new Label("مصروف اهتلاك"), 3, 7);
						grid.add(new Label("مصروف مشتريات عامة"), 3, 8);
						grid.add(new Label("مصروف المرافق العامة"), 3, 9);
						grid.add(new Label("ايرادات"), 3, 10);

						grid.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");

						Label csh = new Label("Cash");
						Label ar = new Label("Account receivable");
						Label s = new Label("Supplies");

						Label pi = new Label("Prepaid Insurance");
						Label m = new Label("Machinery");
						Label f = new Label("Furniture");
						Label v = new Label("Vehicles");
						Label b = new Label("Buildings");
						/////////// debit

						Label ad = new Label("Accumulated Depreciation");

						Label ap = new Label("Account payable");
						Label us = new Label("Unearned Service revenue");
						Label sa = new Label("Salaries and wages payable");

						Label ip = new Label("Inetrest payable");
						Label va = new Label("Value Added tax Provision");
						Label lt = new Label("Long Term Debt");
						Label ca = new Label("Capital");
						Label re = new Label("Retained earnings");

						Label cy = new Label("Current year earning");
						Label dr = new Label("Drawings");
						/// credit

						Label saw = new Label("Salaries and wages expense");
						Label ie = new Label("Insuranse expense");
						Label de = new Label("Depreciation Expense");
						Label gp = new Label("General Purchase Expense");
						Label ve = new Label("Utility expense");
						Label r = new Label("Revenue");

						TextField t = new TextField();
						t.setDisable(true);
						TextField t1 = new TextField();
						t1.setDisable(true);
						TextField t2 = new TextField();
						t2.setDisable(true);
						TextField t3 = new TextField();
						t3.setDisable(true);
						TextField t4 = new TextField();
						t4.setDisable(true);

						TextField t5 = new TextField();
						t5.setDisable(true);
						TextField t6 = new TextField();
						t6.setDisable(true);
						TextField t7 = new TextField();
						t7.setDisable(true);
						TextField t8 = new TextField();
						t8.setDisable(true);

						TextField t9 = new TextField();
						t9.setDisable(true);

						TextField t11 = new TextField();
						t11.setDisable(true);
						TextField t12 = new TextField();
						t12.setDisable(true);
						TextField t13 = new TextField();
						t13.setDisable(true);
						TextField t14 = new TextField();
						t14.setDisable(true);
						TextField t15 = new TextField();
						t15.setDisable(true);
						TextField t16 = new TextField();
						t16.setDisable(true);
						TextField t17 = new TextField();
						t17.setDisable(true);
						TextField t18 = new TextField();
						t18.setDisable(true);
						TextField t19 = new TextField();
						t19.setDisable(true);
						TextField t20 = new TextField();
						t20.setDisable(true);
						TextField t21 = new TextField();
						t21.setDisable(true);
						TextField t0 = new TextField();
						t0.setDisable(true);
						TextField t00 = new TextField();
						t00.setDisable(true);
						TextField t211 = new TextField();
						t211.setDisable(true);
						TextField t212 = new TextField();
						t212.setDisable(true);
						TextField c = new TextField();
						c.setDisable(true);
						TextField d = new TextField();
						d.setDisable(true);

						grid.add(t, 1, 1);
						grid.add(t0, 1, 2);
						grid.add(t00, 1, 3);
						grid.add(t1, 1, 4);
						grid.add(t2, 1, 5);
						grid.add(t3, 1, 6);
						grid.add(t4, 1, 7);
						grid.add(t5, 1, 8);

						// grid.add(new TextField(), 9, 1);

						grid.add(t6, 2, 9);
						grid.add(t7, 2, 10);
						grid.add(t8, 2, 11);
						grid.add(t9, 2, 12);
						grid.add(t212, 2, 13);

						grid.add(t11, 5, 1);
						grid.add(t12, 5, 2);
						grid.add(t13, 5, 3);
						
						grid.add(t16, 4, 4);
						grid.add(t17, 4, 5);

						grid.add(t18, 4, 6);
						grid.add(t19, 4, 7);
						grid.add(t20, 4, 8);
						grid.add(t21, 4, 9);
						grid.add(t211, 5, 10);
						HBox eq = new HBox(10);
						eq.setPadding(new Insets(8, 0, 0, 0));
						eq.getChildren().addAll(c, new Label(" = "));

						grid.add(eq, 4, 12);
						grid.add(d, 5, 12);

						ss.setCenter(grid);

						go.setOnAction(p -> {
							try {
								t.setText("" + fillReports(csh.getText(), fdat.getText()));
								t0.setText("" + fillReports(ar.getText(), fdat.getText()));
								
								
								t00.setText("" + fillReports(s.getText(), fdat.getText()));
								t1.setText("" + fillReports(pi.getText(), fdat.getText()));
								t2.setText("" + fillReports(m.getText(), fdat.getText()));
								t3.setText("" + fillReports(f.getText(), fdat.getText()));
								t4.setText("" + fillReports(v.getText(), fdat.getText()));
								t5.setText("" + fillReports(b.getText(), fdat.getText()));
								t6.setText("" + fillReports(ad.getText(), fdat.getText()));
								t7.setText("" + fillReports(ap.getText(), fdat.getText()));
								t8.setText("" + fillReports(us.getText(), fdat.getText()));
								t9.setText("" + fillReports(sa.getText(), fdat.getText()));
								t212.setText("" + fillReports(ip.getText(), fdat.getText()));
								t11.setText("" + fillReports(va.getText(), fdat.getText()));
								t12.setText("" + fillReports(lt.getText(), fdat.getText()));
								tayseer=fillReports(ca.getText(), fdat.getText());
								t13.setText("" +tayseer );
							
								t16.setText("" + fillReports(dr.getText(), fdat.getText()));
								t17.setText("" + fillReports(saw.getText(), fdat.getText()));
								t18.setText("" + fillReports(ie.getText(), fdat.getText()));
								t19.setText("" + fillReports(de.getText(), fdat.getText()));
								t20.setText("" + fillReports(gp.getText(), fdat.getText()));
								t21.setText("" + fillReports(ve.getText(), fdat.getText()));
								t211.setText("" + fillReports(r.getText(), fdat.getText()));
								c.setText(String.valueOf(Double.parseDouble(t.getText())
										+ Double.parseDouble(t0.getText()) + Double.parseDouble(t00.getText())
										+ Double.parseDouble(t1.getText()) + Double.parseDouble(t2.getText())
										+ Double.parseDouble(t3.getText()) + Double.parseDouble(t4.getText())
										+ Double.parseDouble(t5.getText()) + Double.parseDouble(t18.getText())
										+ Double.parseDouble(t19.getText()) + Double.parseDouble(t20.getText())
										+ Double.parseDouble(t21.getText()) 
										+ Double.parseDouble(t16.getText())+ Double.parseDouble(t17.getText()) ));

								d.setText(String.valueOf(Double.parseDouble(t6.getText())
										+ Double.parseDouble(t7.getText()) + Double.parseDouble(t8.getText())
										+ Double.parseDouble(t9.getText()) + Double.parseDouble(t212.getText())
										+ Double.parseDouble(t11.getText()) + Double.parseDouble(t12.getText())
										+ Double.parseDouble(t13.getText()) 
									+ Double.parseDouble(t211.getText()) ));

								
;							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						});


						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(20, 10, 0, 50));
						hsb13a5.setSpacing(150);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						incst1a.setBottom(hsb13a5);

						primaryStage.setScene(incsts1a);
						primaryStage.show();
					}

					else if (combup2a.getValue() == "بداية رأس مال المالك                   ") {
						ImageView back = new ImageView("dsBuffer.bmp.png");
						back.setPreserveRatio(true);
						Button bk = new Button("رجوع", back);
						bk.setStyle("-fx-font-size: 12pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon15 = new Label(
								"                               بداية رأس مال المالك                                    ");
						logon15.setFont(Font.font(30));

						bk.setOnAction(ss -> {
							primaryStage.setScene(scene1A);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("تسجيل الخروج", limg5);
						out5.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 30);
						hh15.getChildren().addAll(bk, logon15, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon15.setFont(Font.font(null, FontWeight.BOLD, logon15.getFont().getSize()));
						incstreta.setTop(hh15);

						StackPane root5 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root5.setBackground(bGround5);
						incstreta.setCenter(root5);

						BorderPane ss = new BorderPane();
						root5.getChildren().add(ss);

						ss.setPadding(new Insets(7, 20, 0, 0));

						Label compn = new Label(" AMW COMPANY ");
						compn.setStyle("-fx-font-size: 22pt;-fx-font-style:italic;-fx-font-weight: bold;");

						Label inc = new Label(" بداية رأس مال المالك");
						inc.setAlignment(Pos.CENTER);
						inc.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");

						TextField fdat = new TextField();
						TextField tdat = new TextField();
						HBox dh = new HBox(3);
						dh.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						Button go = new Button("GO");
						dh.getChildren().addAll(new Label("For year"), fdat, go);
						VBox toop = new VBox(8);
						toop.setAlignment(Pos.CENTER);

						// toop.setPadding(new Insets(0,0,0,0));
						toop.getChildren().addAll(compn, inc, dh);

						ImageView prnt = new ImageView("machine.png");
						ImageView eml = new ImageView("emil.jpg");
						Button print = new Button("Print", prnt);
						Button email = new Button("Send as Email", eml);

						HBox hh = new HBox(400);
						hh.setPadding(new Insets(4, 0, 0, 30));
						hh.getChildren().addAll(email, toop, print);
						ss.setTop(hh);

						GridPane grid = new GridPane();

						grid.setAlignment(Pos.CENTER);
						grid.setHgap(250);
						grid.setVgap(15);

						Label br = new Label("Beginning Owner's Capital");
						Label ai = new Label("Add : Investment");
						Label an = new Label("Add : Net income");
						Label ld = new Label("Less : Drawing");
						Label er = new Label("Ending Owner's Capital");

						grid.add(new Label("بداية رأس مال المالك"), 0, 0);
						grid.add(new Label("زياده: الاستثمار"), 0, 1);
						grid.add(new Label("زياده: صافي الدخل"), 0, 2);
						grid.add(new Label("نقصان: المسحوبات"), 0, 3);
						grid.add(new Label("نهاية رأس مال المالك"), 0, 4);

						grid.setStyle("-fx-font-size: 18pt;-fx-font-weight: bold;");

						TextField t = new TextField();
						t.setDisable(true);
						TextField t1 = new TextField();
						t1.setDisable(true);
						TextField t2 = new TextField();
						t2.setDisable(true);
						TextField t3 = new TextField();
						t3.setDisable(true);
						TextField t4 = new TextField();
						t3.setDisable(true);
						t4.setDisable(true);
						grid.add(t, 1, 0);
						grid.add(t1, 1, 1);
						grid.add(t2, 1, 2);
						grid.add(t3, 1, 3);
						grid.add(t4, 1, 4);
						go.setOnAction(p -> {
							try {
								t.setText("" + fillReports(br.getText(), fdat.getText()));
								t1.setText("" +tayseer);
								t2.setText(""+moath);
								t3.setText("" + fillReports("Drawings", fdat.getText()));
								weam= Double.parseDouble(t.getText())+Double.parseDouble(t1.getText()) +Double.parseDouble(t2.getText())-Double.parseDouble(t3.getText());
								t4.setText(""+weam);
                                  ahmad+= Double.parseDouble(t4.getText());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						});

						ss.setCenter(grid);


						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(20, 10, 0, 50));
						hsb13a5.setSpacing(150);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						incstreta.setBottom(hsb13a5);

						primaryStage.setScene(incstsreta);
						primaryStage.show();

					} else if (combup2a.getValue() == "المركز المالي                   ") {
						ImageView back = new ImageView("dsBuffer.bmp.png");
						back.setPreserveRatio(true);
						Button bk = new Button("رجوع", back);
						bk.setStyle("-fx-font-size: 12pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon15 = new Label(
								"                                        المركز المالي                                       ");
						logon15.setFont(Font.font(30));

						bk.setOnAction(ss -> {
							primaryStage.setScene(scene1A);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("تسجيل الخروج", limg5);
						out5.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 30);
						hh15.getChildren().addAll(bk, logon15, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon15.setFont(Font.font(null, FontWeight.BOLD, logon15.getFont().getSize()));
						incstbala.setTop(hh15);

						StackPane root5 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root5.setBackground(bGround5);
						incstbala.setCenter(root5);

						BorderPane ss = new BorderPane();
						root5.getChildren().add(ss);

						ss.setPadding(new Insets(5, 20, 0, 0));

						Label compn = new Label(" AMW COMPANY ");
						compn.setStyle("-fx-font-size: 18pt;-fx-font-style:italic;-fx-font-weight: bold;");

						Label inc = new Label(" المركز المالي");
						inc.setAlignment(Pos.CENTER);
						inc.setStyle("-fx-font-size: 14pt;-fx-font-weight: bold;");

						TextField fdat = new TextField();
						TextField tdat = new TextField();
						HBox dh = new HBox(3);
						dh.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						Button go = new Button("إصدار");
						dh.getChildren().addAll(new Label("For year"), fdat, go);
						VBox toop = new VBox(8);
						toop.setAlignment(Pos.CENTER);

						// toop.setPadding(new Insets(0,0,0,0));
						toop.getChildren().addAll(compn, inc, dh);

						ImageView prnt = new ImageView("machine.png");
						ImageView eml = new ImageView("emil.jpg");
						Button print = new Button("طباعة", prnt);
						Button email = new Button("ارسال بريد", eml);

						HBox hh = new HBox(400);
						hh.setPadding(new Insets(4, 0, 0, 30));
						hh.getChildren().addAll(email, toop, print);
						ss.setTop(hh);

						Label sal = new Label("الاصول");
						sal.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						sal.setUnderline(true);

						Label sal1 = new Label("المسؤوليات");
						sal1.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						sal1.setUnderline(true);

						Label sal12 = new Label("حقوق المالك");
						sal12.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						sal12.setUnderline(true);

						GridPane grid = new GridPane();
						grid.setAlignment(Pos.CENTER);
						grid.setHgap(90);
						grid.setVgap(2);
						grid.setPadding(new Insets(0, 10, 10, 30));
						grid.add(sal, 0, 0);

						grid.add(new Label("الاصول الحاليه"), 0, 1);
						grid.add(new Label("نقدي "), 0, 2);
						grid.add(new Label("حساب مستحق"), 0, 3);
						grid.add(new Label("اللوازم"), 0, 4);

						grid.add(new Label("التامين المدفوع"), 0, 5);
						grid.add(new Label("اجمالي الاصول الحاليه"), 0, 6);
						grid.add(new Label(""), 0, 7);
						grid.add(new Label("الاصول الثابتة"), 0, 8);
						grid.add(new Label("ادوات ومعدات"), 0, 9);

						grid.add(new Label("اثاث"), 0, 10);

						grid.add(new Label("مركبات"), 0, 11);
						grid.add(new Label("بنايات"), 0, 12);
						grid.add(new Label(""), 0, 13);

						grid.add(new Label("الموجودات الثابتة الكلية"), 0, 14);
						grid.add(new Label("نقصان : الاهتلاك التراكمي"), 0, 15);
						grid.add(new Label("صافي الأصول الثابتة"), 0, 16);
						grid.add(new Label("اجمالي الاصول"), 0, 17);

						grid.add(sal1, 3, 0);
						grid.add(new Label("التزامات متداولة"), 3, 1);

						grid.add(new Label("ذمم دائنه"), 3, 2);
						grid.add(new Label("الايرادات الخدمية غير المكتسبه"), 3, 3);
						/// credit

						grid.add(new Label("الفوائد المستحقة الدفع"), 3, 4);
						grid.add(new Label("مخصص ضريبة القيمة المضافة"), 3, 5);
						grid.add(new Label("إجمالي المطلوبات الحالية"), 3, 6);
						grid.add(new Label(""), 3, 7);
						grid.add(new Label("دين بعيد الامد"), 3, 8);
						grid.add(new Label("اجمالي المطلوبات"), 3, 9);
						grid.add(new Label(""), 3, 10);
						grid.add(sal12, 3, 11);
						grid.add(new Label("رأس مال المالك"), 3, 12);
						
						grid.add(new Label(""), 3, 17);
						grid.add(new Label("إجمالي المطلوبات وحقوق الملكية"), 3, 15);

						Label ca = new Label("Current assets ");
						Label csh = new Label("Cash");
						Label ar = new Label("Account receivable");
						Label su = new Label("Supplies");

						Label pi = new Label("prepaid insurance");
						Label tc = new Label("Total current assets");
						Label fa = new Label("Fixed assets");////////////////////////////
						fa.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						Label m = new Label("Machinery");

						Label fu = new Label("Furniture");

						Label v = new Label("Vehicles");
						Label b = new Label("Buildings");
						//Label ad = new Label("Depreciation Expense");
						Label tf = new Label("Total fixed assets");
						Label la = new Label("Less : Accumulated depreciation");
						Label nf = new Label("Net fixed assets");
						Label ta = new Label("Total assets");

						Label cl = new Label("Current liabilities");

						Label ap = new Label("Account payble");
						Label us = new Label("Unearned Service Revenue");
						/// credit

						Label rp = new Label("Interest payable");
						Label va = new Label("Value added tax provision");
						Label tcl = new Label("Total current liabilities");
						Label lt = new Label("Long tearm debt");
						Label tl = new Label("Total liabilities");
						
						Label tla = new Label("Total liabities and owner equity");
						Label oc = new Label("Owner's Capital");
						Label saw = new Label("Salaries and wages Payable");
						
						grid.setStyle("-fx-font-size: 9pt;-fx-font-weight: bold;");

						TextField t = new TextField();
						t.setDisable(true);
						TextField t1 = new TextField();
						t1.setDisable(true);
						TextField t2 = new TextField();
						t2.setDisable(true);
						TextField t3 = new TextField();
						t3.setDisable(true);
						TextField t4 = new TextField();
						t4.setDisable(true);

						TextField t5 = new TextField();
						t5.setDisable(true);
						TextField t6 = new TextField();
						t6.setDisable(true);
						TextField t7 = new TextField();
						t7.setDisable(true);
						TextField t8 = new TextField();
						t8.setDisable(true);

						TextField t9 = new TextField();
						t9.setDisable(true);

						TextField t11 = new TextField();
						t11.setDisable(true);
						TextField t12 = new TextField();
						t12.setDisable(true);
						TextField t13 = new TextField();
						t13.setDisable(true);
						TextField t14 = new TextField();
						t14.setDisable(true);
						TextField t15 = new TextField();
						t15.setDisable(true);
						TextField t16 = new TextField();
						t16.setDisable(true);
						TextField t17 = new TextField();
						t17.setDisable(true);
						TextField t18 = new TextField();
						t18.setDisable(true);
						TextField t19 = new TextField();
						t19.setDisable(true);
						TextField t20 = new TextField();
						t20.setDisable(true);
						TextField t21 = new TextField();
						t21.setDisable(true);
						TextField t0 = new TextField();
						t0.setDisable(true);
						TextField t00 = new TextField();
						t00.setDisable(true);
						TextField t211 = new TextField();
						t211.setDisable(true);
						TextField t212 = new TextField();
						t212.setDisable(true);
						TextField c = new TextField();
						c.setDisable(true);
						TextField d = new TextField();
						d.setDisable(true);

						TextField sawf = new TextField();
						sawf.setDisable(true);
						TextField tx = new TextField();
						tx.setDisable(true);
						TextField t1x = new TextField();
						t1x.setDisable(true);
						TextField t2x = new TextField();
						t2x.setDisable(true);
						TextField t3x = new TextField();
						t3x.setDisable(true);
						TextField t4x = new TextField();
						t4x.setDisable(true);

						grid.add(new Label(""), 1, 1);
						grid.add(t0, 1, 2);
						grid.add(t00, 1, 3);
						grid.add(t1, 1, 4);
						grid.add(t2, 1, 5);
						grid.add(t3, 1, 6);
						// grid.add(t4, 1, 7);
						grid.add(new Label(""), 1, 8);

						// grid.add(new TextField(), 9, 1);

						grid.add(t6, 1, 9);
						grid.add(t7, 1, 10);
						grid.add(t8, 1, 11);
						grid.add(t9, 1, 12);
						// grid.add(t212, 1, 13);

						grid.add(t11, 1, 14);
						grid.add(t12, 1, 15);
						grid.add(t13, 1, 16);
						grid.add(t14, 1, 17);

						grid.add(t15, 1, 5);

						grid.add(new Label(""), 4, 1);
						grid.add(t4x, 4, 2);
						grid.add(t4, 4, 3);
						
						grid.add(sawf, 4, 4);
						grid.add(t2x, 4, 5);
						grid.add(t212, 4, 6);
						grid.add(t16, 4, 7);
                       
						grid.add(t18, 4, 9);
						grid.add(t19, 4, 10);

						grid.add(new Label(""), 4, 11);
						 grid.add(t17, 4,13);
						grid.add(t3x, 4, 15);

						ss.setCenter(grid);

						go.setOnAction(q -> {
							try {

								t0.setText("" + fillReports(csh.getText(), fdat.getText()));
								t00.setText("" + fillReports(ar.getText(), fdat.getText()));
								t1.setText("" + fillReports(su.getText(), fdat.getText()));
								t2.setText("" + fillReports(pi.getText(), fdat.getText()));
								sawf.setText("" + fillReports(saw.getText(), fdat.getText()));
								t3.setText(String

										.valueOf(Double.parseDouble(t0.getText()) + Double.parseDouble(t00.getText())
												+ Double.parseDouble(t1.getText()) + Double.parseDouble(t2.getText())));
								t6.setText("" + fillReports(m.getText(), fdat.getText()));
								t7.setText("" + fillReports(fu.getText(), fdat.getText()));
								t8.setText("" + fillReports(v.getText(), fdat.getText()));
								t9.setText("" + fillReports(b.getText(), fdat.getText()));
								
                                 d.setText(""+moath);
								t11.setText(String
										.valueOf(Double.parseDouble(t6.getText()) + Double.parseDouble(t7.getText())
												+ Double.parseDouble(t8.getText()) + Double.parseDouble(t9.getText())));

                                ridi+=fillReports("Accumulated depreciation", fdat.getText());
								
								t12.setText("" + ridi);
								t13.setText(String.valueOf(
										Double.parseDouble(t11.getText()) - Double.parseDouble(t12.getText())));

								t14.setText(String
										.valueOf(Double.parseDouble(t3.getText()) + Double.parseDouble(t13.getText())));
								/////////////////////////////////////////////////////////////
								
								

								t4x.setText("" + fillReports(ap.getText(), fdat.getText()));
								t4.setText("" + fillReports(us.getText(), fdat.getText()));
								t2x.setText("" + fillReports(rp.getText(), fdat.getText()));
								t212.setText("" + fillReports(va.getText(), fdat.getText()));
								t16.setText(String.valueOf(Double.parseDouble(t4x.getText())
										+ Double.parseDouble(t4.getText()) + Double.parseDouble(t2x.getText())+ Double.parseDouble(sawf.getText())
										+ Double.parseDouble(t212.getText())));

								t18.setText("" + fillReports(lt.getText(), fdat.getText()));
								t19.setText(String.valueOf(
										Double.parseDouble(t16.getText()) + Double.parseDouble(t18.getText())));

							t17.setText(""+weam);
																
							t3x.setText(String.valueOf(Double.parseDouble(t17.getText()) + Double.parseDouble(t19.getText())));

							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						});


						ss.setCenter(grid);

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(20, 10, 0, 50));
						hsb13a5.setSpacing(150);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						incstbala.setBottom(hsb13a5);

						primaryStage.setScene(incstsbala);
						primaryStage.show();
					}
				}
			};

			combup2a.setOnAction(inceva);

			unlimbt.setOnAction(x -> {
				if (unlimbt.isSelected()) {

					TextField userAvilable = new TextField();
					userAvilable.setPrefSize(300, 30);

					PasswordField passAvilable = new PasswordField();
					userAvilable.setPrefSize(300, 30);
					ImageView ww = new ImageView("User-Interface-Login-icon.png");

					Button logInButton = new Button("Login", ww);
					logInButton.setFont(Font.font(20));
					logInButton.setPrefSize(120, 30);
					logInButton.setStyle("-fx-Background-color:#1999f1");
					logInButton.setOnMouseEntered(e -> {
						logInButton.setStyle("-fx-Background-color:blue");
					});
					logInButton.setOnMouseExited(e -> {
						logInButton.setStyle("-fx-Background-color:#1999f1");
					});

					HBox buttonsBox = new HBox(20);
					buttonsBox.setAlignment(Pos.CENTER);
					buttonsBox.setPadding(new Insets(0, 15, 15, 0));
					// buttonsBox.getChildren().addAll(logInButton);

					GridPane logInGrid = new GridPane();
					logInGrid.setHgap(60);

					// logInGrid.setAlignment(Pos.CENTER);
					Label logInLabel = new Label("Username :");
					Label logInLabel2 = new Label("Password :");
					logInLabel.setFont(Font.font(20));
					logInLabel2.setFont(Font.font(20));
					logInLabel.setUnderline(true);
					logInLabel2.setUnderline(true);

					logInGrid.add(logInLabel, 0, 0);
					logInGrid.add(userAvilable, 1, 0);
					logInGrid.add(logInLabel2, 0, 1);
					logInGrid.add(passAvilable, 1, 1);

					logInGrid.add(logInButton, 1, 2);

					logInGrid.setPadding(new Insets(10, 10, 20, 10));

					logInGrid.setBackground(
							new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
					logInLabel.setFont(Font.font(null, FontWeight.BOLD, logon.getFont().getSize()));
					logInLabel2.setFont(Font.font(null, FontWeight.BOLD, logon.getFont().getSize()));

					ff.setTop(logInGrid);
					ff.setPadding(new Insets(20, 30, 0, 940));

					logInPane.setTop(hh);

					HBox hn1 = new HBox();
					hn1.setSpacing(10);
					hn1.setPadding(new Insets(0, 0, 0, 50));
					hn1.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

					RadioButton limb1 = new RadioButton("العربية");

					limb1.setFont(Font.font(15));
					RadioButton unlimbt1 = new RadioButton("ENGLISH");
					unlimbt1.setFont(Font.font(15));

					hn1.getChildren().addAll(limb1, unlimbt1);
					ToggleGroup tg1 = new ToggleGroup();
					limb1.setToggleGroup(tg1);
					unlimbt1.setToggleGroup(tg1);
					ImageView iamg1 = new ImageView("F1.jpg");
					HBox hsb1 = new HBox();
					hsb1.setSpacing(60);
					hsb1.setSpacing(40);
					hsb1.setPadding(new Insets(20, 10, 0, 0));
					hsb1.setBackground(
							new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
					hsb1.getChildren().addAll(iamg1, limb, unlimbt);
					logInPane.setBottom(hsb1);

					
					
					
					
					
					logInButton.setOnAction(c -> {
						try {
							if (checkLogIn(userName, userAvilable.getText(), passAvilable.getText())) {
								primaryStage.setScene(scene1);
								userAvilable.clear();
								passAvilable.clear();
							} else {
								JOptionPane.showMessageDialog(null, "Wrong Number");
							}
						} catch (Exception g) {

						}
					});

				}

			});

			Label logon1 = new Label("                                                AMW ACCOUNTING DEPATMENT  ");
			logon1.setFont(Font.font(35));

			ImageView limg = new ImageView("Logout-icon.png");

			Button out = new Button("LogOut", limg);
			out.setStyle("-fx-font-size: 10pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
			out.setOnAction(w -> {
				primaryStage.setScene(scene);
				primaryStage.setMaximized(true);
				primaryStage.show();
			});

			HBox hh1 = new HBox();
			hh1.setSpacing(378);
			hh1.setMaxSize(2100, 50);
			hh1.getChildren().addAll(logon1, out);
			hh1.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			logon1.setFont(Font.font(null, FontWeight.BOLD, logon1.getFont().getSize()));

			StackPane root3 = new StackPane();
			Image img3 = new Image("PM315.jpg");
			BackgroundImage bImg3 = new BackgroundImage(img3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround3 = new Background(bImg3);
			root3.setBackground(bGround3);
			dataPage.setCenter(root3);
			primaryStage.setScene(scene1);
			primaryStage.setMaximized(true);

			dataPage.setTop(hh1);

			BorderPane bb = new BorderPane();
			bb.setPadding(new Insets(80, 90, 0, 0));
			HBox uph = new HBox();
			uph.setSpacing(200);
			uph.setAlignment(Pos.CENTER);
			bb.setTop(uph);

			String[] y = { "             Accounts Guide", "             Account Statements" };

			ComboBox combup = new ComboBox(FXCollections.observableArrayList(y));
			Image imm = new Image("kkInvoice-icon.png");

			BackgroundImage bmg = new BackgroundImage(imm, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bround = new Background(bmg);
			combup.setBackground(bround);
			combup.getEditor().setFont(Font.font(40));
			combup.setStyle("-fx-font-size: 15pt;-fx-border-color:PLUM;-fx-font-weight: bold;");
			combup.setPadding(new Insets(20, 20, 20, 40));
			combup.setPromptText("          ACCOUNTS  ");
			combup.setPrefWidth(400);

			combup.setOnMouseClicked(oo -> {
				combup.setValue("          ACCOUNTS  ");
				combup.setPromptText("          ACCOUNTS  ");
			});
			Image imm12 = new Image("Business-Multiple-Input-icon.png");

			BackgroundImage bImg12 = new BackgroundImage(imm12, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

			Background bGround12 = new Background(bImg12);

			String[] ssw = { "             Sales Bill", "             Purchases Bill", "             Search Bills" };
			ComboBox bills = new ComboBox(FXCollections.observableArrayList(ssw));
			bills.setPromptText("                 Bills                            ");

			String[] ssw1 = { "             Search in Voucher", "             Enter Voucher" };
			ComboBox bills1 = new ComboBox(FXCollections.observableArrayList(ssw1));
			bills1.setPromptText("                Jornal Voucher           ");

			String[] ssw2 = { "             Search in Voucher", "             Enter Voucher" };
			ComboBox bills2 = new ComboBox(FXCollections.observableArrayList(ssw2));
			bills2.setPromptText("                Receipt Voucher         ");

			String[] ssw3 = { "             Search in Voucher", "             Enter Voucher" };
			ComboBox bills3 = new ComboBox(FXCollections.observableArrayList(ssw3));
			bills3.setPromptText("                Payment Voucher       ");

			ComboBox combup1 = new ComboBox();

			combup1.setBackground(bGround12);
			combup1.setPadding(new Insets(20, 20, 20, 40));
			combup1.setPromptText("                INPUTS  ");
			combup1.setPrefWidth(400);
			combup1.setStyle("-fx-font-size: 17pt;-fx-border-color:PLUM;-fx-font-weight: bold;");
			combup1.getItems().addAll(bills, bills1, bills2, bills3);

			Label inp = new Label(">>  INPUTS  <<");
			inp.setUnderline(true);
			inp.setStyle("-fx-font-size: 19pt;-fx-font-weight: bold;");

			bills.setOnMouseClicked(oo -> {
				bills.setValue("Bills");
				bills.setPromptText("Bills");
			});

			bills1.setOnMouseClicked(oo -> {
				bills1.setValue("Jornal Voucher");
				bills1.setPromptText("Jornal Voucher");
			});

			bills2.setOnMouseClicked(oo -> {
				bills2.setValue("Recipet Voucher");
				bills2.setPromptText("Recipet Voucher");

			});

			bills3.setOnMouseClicked(oo -> {
				bills3.setValue("Payment Voucher");
				bills3.setPromptText("Payment Voucher");
			});

			bills.setPrefSize(390, 40);
			bills1.setPrefSize(390, 40);
			bills2.setPrefSize(390, 40);
			bills3.setPrefSize(390, 40);

			GridPane gris = new GridPane();
			gris.setPadding(new Insets(60, 0, 0, 400));
			gris.add(bills, 0, 0);
			gris.add(bills1, 0, 1);
			gris.add(bills2, 0, 2);
			gris.add(bills3, 0, 3);
			gris.setVgap(40);

			bb.setCenter(gris);
			// gris.setAlignment(Pos.CENTER);

			gris.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");

			root3.getChildren().add(bb);

			ImageView iamg13 = new ImageView("F1.jpg");
			HBox hsb13 = new HBox();
			hsb13.setPrefHeight(60);
			hsb13.setPadding(new Insets(20, 10, 0, 0));
			hsb13.setSpacing(40);
			hsb13.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			hsb13.getChildren().addAll(iamg13);
			dataPage.setBottom(hsb13);

			Label mu = new Label("Currency");
			mu.setStyle("-fx-font-size: 20pt;-fx-font-weight: bold;");
			mu.setUnderline(true);

			RadioButton ils = new RadioButton(" ILS (₪)");
			ils.setStyle("-fx-font-size: 15pt;-fx-font-weight: bold;");
			RadioButton usd = new RadioButton(" USD ($)");
			usd.setStyle("-fx-font-size: 15pt;-fx-font-weight: bold;");
			RadioButton jod = new RadioButton(" JOD (JD)");
			jod.setStyle("-fx-font-size: 15pt;-fx-font-weight: bold;");

			ils.setOnAction(w -> {
				input.clear();
				String coin = "";
				try {
					Scanner in = new Scanner(refresh);
					String[] s = in.nextLine().split(",");
					if (!s[5].equalsIgnoreCase("ILS")) {
						changeCoin(s[5], "ILS");
					}
				} catch (Exception g) {
					System.out.println("Error in Change Coin in ILS radi Button");
				}
				fillSanads();
			});

			usd.setOnAction(w -> {
				input.clear();
				String coin = "";
				try {
					Scanner in = new Scanner(refresh);
					String[] s = in.nextLine().split(",");
					if (!s[5].equalsIgnoreCase("USD")) {
						changeCoin(s[5], "USD");
					}
				} catch (Exception g) {
					System.out.println("Error in Change Coin in ILS radi Button");
				}
				fillSanads();
			});

			jod.setOnAction(w -> {
				input.clear();
				String coin = "";
				try {
					Scanner in = new Scanner(refresh);
					String[] s = in.nextLine().split(",");
					if (!s[5].equalsIgnoreCase("JOD")) {
						changeCoin(s[5], "JOD");
					}
				} catch (Exception g) {
					System.out.println("Error in Change Coin in ILS radi Button");
				}
				fillSanads();
			});

			
			
			
			
			
			VBox la = new VBox();
			la.setSpacing(20);
			la.getChildren().addAll(mu, ils, usd, jod);

			ils.setToggleGroup(ttg);
			usd.setToggleGroup(ttg);
			jod.setToggleGroup(ttg);
			bb.setLeft(la);
			la.setAlignment(Pos.CENTER_LEFT);

			la.setPadding(new Insets(200, 0, 0, 100));

			EventHandler<ActionEvent> evenxdacc = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					if (combup.getValue() == "             Accounts Guide") {

						// for assets only
						BorderPane mm = new BorderPane();
						StackPane ql = new StackPane();

						ImageView back1 = new ImageView("dsBuffer.bmp.png");
						back1.setPreserveRatio(true);
						Button bk1 = new Button("Back", back1);
						bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151 = new Label(
								"                                  Account Guid                        ");
						logon151.setFont(Font.font(35));

						bk1.setOnAction(ss -> {
							primaryStage.setScene(scene1);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("LogOut", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk1, logon151, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
						xq.setTop(hh15);

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						ql.setBackground(bGround5);
						xq.setCenter(ql);

						GridPane gDaleel = new GridPane();
						gDaleel.setAlignment(Pos.CENTER);
						gDaleel.setHgap(40);
						gDaleel.setVgap(25);
						gDaleel.setPadding(new Insets(0, 50, 0, 0));
						// gDaleel.setPadding(new Insets(0,0,20,0));
						// Assets
						gDaleel.add(labelList.get(labelList.size() - 5), 0, 0); // assets
						gDaleel.add(labelList.get(0), 0, 1);
						gDaleel.add(labelList.get(1), 0, 2);
						gDaleel.add(labelList.get(2), 0, 3);
						gDaleel.add(labelList.get(3), 0, 4);
						gDaleel.add(labelList.get(4), 0, 5);
						gDaleel.add(labelList.get(5), 0, 6);
						gDaleel.add(labelList.get(6), 0, 7);
						gDaleel.add(labelList.get(7), 0, 8);
						gDaleel.add(labelList.get(8), 0, 9);
						gDaleel.add(labelList.get(9), 0, 10);
						gDaleel.add(labelList.get(10), 0, 11);
						gDaleel.add(labelList.get(11), 0, 12);

						gDaleel.add(labelList.get(labelList.size() - 4), 1, 0); // liability
						gDaleel.add(labelList.get(12), 1, 1);
						gDaleel.add(labelList.get(13), 1, 2);
						gDaleel.add(labelList.get(14), 1, 3);
						gDaleel.add(labelList.get(15), 1, 4);
						gDaleel.add(labelList.get(16), 1, 5);
						gDaleel.add(labelList.get(17), 1, 6);
						gDaleel.add(labelList.get(18), 1, 7);

						gDaleel.add(labelList.get(labelList.size() - 3), 2, 0); // Equity
						// gDaleel.add(labelList.get(19), 2, 1);
						gDaleel.add(labelList.get(19), 2, 1);
						gDaleel.add(labelList.get(20), 2, 2);
						gDaleel.add(labelList.get(21), 2, 3);
						gDaleel.add(labelList.get(22), 2, 4);

						gDaleel.add(labelList.get(labelList.size() - 2), 3, 0); // Revenues
						gDaleel.add(labelList.get(23), 3, 1);

						gDaleel.add(labelList.get(labelList.size() - 1), 4, 0);
						gDaleel.add(labelList.get(24), 4, 1);
						gDaleel.add(labelList.get(25), 4, 2);
						gDaleel.add(labelList.get(26), 4, 3);
						gDaleel.add(labelList.get(27), 4, 4);
						gDaleel.add(labelList.get(28), 4, 5);

						gDaleel.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						Button backForDaleel = new Button("Back");

						backForDaleel.setOnAction(ex -> {
							// scene after login
						});

						GridPane searchDaleelGrid = new GridPane();
						searchDaleelGrid.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						searchDaleelGrid.setHgap(10);
						searchDaleelGrid.setVgap(5);
						Button searchForDaleel = new Button("Search");
						searchForDaleel.setPrefSize(75, 25);
						TextField tDaleel = new TextField();
						TextField daleelResult = new TextField();
						daleelResult.setEditable(false);
						searchDaleelGrid.add(searchForDaleel, 0, 0);
						searchDaleelGrid.add(tDaleel, 1, 0);
						searchDaleelGrid.add(daleelResult, 1, 1);
						searchDaleelGrid.setAlignment(Pos.CENTER);
						searchDaleelGrid.setPadding(new Insets(0, 0, 15, 0));

						searchForDaleel.setOnAction(ex -> {
							boolean flag = false;
							String[] ok = null;

							for (int i = 0; i < labelList.size(); i++) {
								String s = labelList.get(i).getText();
								s = s.replaceAll("[()]", "");
								ok = s.split(" ");
								if (ok[0].equalsIgnoreCase(tDaleel.getText())) {

									flag = true;
									break;
								}
							}
							if (flag) {
								switch(ok.length) {
								case 2:daleelResult.setText(ok[1]); break;
								case 3:daleelResult.setText(ok[1]+" "+ok[2]);break;
								case 4:daleelResult.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
								case 5:daleelResult.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
								}
							}
						});
						if(!ils.isSelected()&&!jod.isSelected()&&!usd.isSelected()) {
							JOptionPane.showMessageDialog(null, "Please choose a curancy");
						}
						
						ql.getChildren().add(mm);
						mm.setCenter(gDaleel);
						mm.setBottom(searchDaleelGrid);
						xq.setCenter(ql);
						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						// hsb13a5.setPrefHeight(30);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						xq.setBottom(hsb13a5);

						primaryStage.setScene(xs);
						primaryStage.show();
					} else if (combup.getValue() == "             Account Statements") {

						BorderPane mmx = new BorderPane();

						StackPane root51x = new StackPane();

						Image img5x = new Image("PM316.jpg");
						BackgroundImage bImg5x = new BackgroundImage(img5x, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5x = new Background(bImg5x);
						root51x.setBackground(bGround5x);

						root51x.getChildren().add(mmx);

						ImageView back1x = new ImageView("dsBuffer.bmp.png");
						back1x.setPreserveRatio(true);
						Button bk1x = new Button("Back", back1x);
						bk1x.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151x = new Label(
								"                           Account Statements                     ");
						logon151x.setFont(Font.font(35));

						HBox hh15x = new HBox();
						ImageView limg5x = new ImageView("Logout-icon.png");

						Button out5x = new Button("LogOut", limg5x);
						out5x.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5x.setOnAction(tx -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						hh15x.setSpacing(228);
						hh15x.setMaxSize(2100, 50);
						hh15x.getChildren().addAll(bk1x, logon151x, out5x);
						hh15x.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151x.setFont(Font.font(null, FontWeight.BOLD, logon151x.getFont().getSize()));
						tar.setTop(hh15x);

						tar.setCenter(root51x);

						TextArea tt = new TextArea();
						tt.setPrefSize(400, 380);
						tt.setDisable(true);
						tt.setStyle("-fx-font-size: 15pt;-fx-font-weight: bold;");
						tt.setText(
								"  Account number\t\t\t\t Amount\t\t\t\t\tVoucher Number \t\t\t\t\tAccount Name\t\t\t  Date\n");
						mmx.setPadding(new Insets(100, 100, 100, 100));
						mmx.setCenter(tt);

						HBox hsb13a5x = new HBox();
						hsb13a5x.setPrefHeight(50);
						hsb13a5x.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

						tar.setBottom(hsb13a5x);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

						BorderPane mm = new BorderPane();

						StackPane root51 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root51.setBackground(bGround5);
						px1.setCenter(root51);
						root51.getChildren().add(mm);

						ImageView back1 = new ImageView("dsBuffer.bmp.png");
						back1.setPreserveRatio(true);
						Button bk1 = new Button("Back", back1);
						bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151 = new Label(
								"                           Account Statements                     ");
						logon151.setFont(Font.font(35));

						bk1.setOnAction(ss -> {
							primaryStage.setScene(scene1);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("LogOut", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});
						HBox hh15 = new HBox();
						HBox hh16 = new HBox(10);
						hh16.setAlignment(Pos.CENTER);
						TextField ff = new TextField();
						Button bSearch = new Button("Search");
						hh16.getChildren().addAll(new Label("Enter Account Number"), ff, bSearch);

						bk1x.setOnAction(ssx -> {
							ff.clear();
							tt.clear();
							tt.setText(
									"  Account number\t\t\t\t Amount\t\t\t\tVoucher Number \t\t\t\t\tAccount Name\t\t\t Date\n");
							area = new StringBuilder();
							primaryStage.setScene(qqs);
							primaryStage.show();

						});

						bSearch.setOnAction(n -> {

							try {
								for (Accounts c : account) {
									Scanner ar = new Scanner(refresh);
									while (ar.hasNextLine()) {
										String[] s = ar.nextLine().split(",");

										String[] len = s[3].split(" ");
										switch (len.length) {
										case 1:
											tt.setText(
													"  Account number\t\t\t   Amount\t\t\tVoucher Number \t\t\t Account Name\t\t\t\t\tDate\n");
											break;
										case 2:
											tt.setText(
													"  Account number\t\t\t   Amount\t\t\tVoucher Number \t\t\t Account Name\t\t\tDate\n");
											break;
										case 3:
											tt.setText(
													"  Account number\t\t\t   Amount\t\t\tVoucher Number \t\t\t Account Name\t\t\tDate\n");
											break;
										case 4:
											tt.setText(
													"  Account number\t\t\t   Amount\t\t\tVoucher Number \t\t\t Account Name\t\t\t\t  Date\n");
											break;
										}

										if (c.getAccountNumber() == Long.parseLong(s[0])) {
											if (s[0].equalsIgnoreCase(ff.getText())) {
												sceneFlag = true;

												switch (len.length) {
												case 1:
													area.append("\t" + s[0] + " \t\t\t\t\t  " + s[2] + "       \t\t\t"
															+ s[1] + " \t\t\t\t\t\t" + s[3] + " \t\t\t\t  " + s[4]
															+ "\n");
													System.out.print("case1");
													break;

												case 2:
													area.append("\t" + s[0] + " \t\t\t\t\t    " + s[2]
															+ "\t\t\t\t\t\t     " + s[1] + "    \t\t\t\t\t\t\t" + s[3]
															+ " \t\t\t\t\t" + s[4] + "\n");
													System.out.print("case2");
													break;

												case 3:
													area.append("\t" + s[0] + " \t\t\t     " + s[2] + "\t\t\t\t\t"
															+ s[1] + "    \t\t\t\t\t" + s[3] + " \t\t\t" + s[4] + "\n");
													System.out.print("case3");
													break;

												case 4:
													area.append("\t" + s[0] + " \t\t\t\t\t  " + s[2] + "\t\t\t\t" + s[1]
															+ "    \t\t\t\t  " + s[3] + "\t\t\t\t" + s[4] + "\n");
													System.out.print("case4");
													break;

												}

											}
										}
									}

								}
							} catch (Exception m) {
								System.out.println("Fail in search ");
							}
							if (sceneFlag) {

								tt.appendText(area.toString());
								primaryStage.setScene(tars);
								primaryStage.setMaximized(true);
								primaryStage.show();
							}
						});

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk1, logon151, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
						qq.setTop(hh15);
						mm.setCenter(hh16);
						hh16.setStyle("-fx-font-size: 23pt;-fx-font-weight: bold;");
						hh16.setAlignment(Pos.CENTER);
						qq.setCenter(root51);

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						// hsb13a5.setPrefHeight(30);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						qq.setBottom(hsb13a5);

						primaryStage.setScene(qqs);
						primaryStage.show();
					}

				}
			};
			combup.setOnAction(evenxdacc);

			EventHandler<ActionEvent> evenxd = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					if(!ils.isSelected()&&!jod.isSelected()&&!usd.isSelected()) {
						JOptionPane.showMessageDialog(null, "Please choose a curancy");
					}
					
					if (bills1.getValue() == "             Enter Voucher") {

						ImageView back1 = new ImageView("dsBuffer.bmp.png");
						back1.setPreserveRatio(true);
						Button bk1 = new Button("Back", back1);
						bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151 = new Label(
								"                                    Jornal Voucher                      ");
						logon151.setFont(Font.font(35));

						bk1.setOnAction(ss -> {
							primaryStage.setScene(scene1);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("LogOut", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk1, logon151, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
						px1.setTop(hh15);

						StackPane root51 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root51.setBackground(bGround5);
						px1.setCenter(root51);
						String[] cointxt = { "ILS", "USD", "JOD" };
						ComboBox coinCombo = new ComboBox(FXCollections.observableArrayList(cointxt));
						coinCombo.setStyle("-fx-font-size: 10pt;-fx-font-weight: bold;");
						coinCombo.setPromptText("Select Currency");
						String[] sw = ttg.getSelectedToggle().toString().split(" ");
						coinCombo.setValue(sw[1]);
						coinCombo.setDisable(true);
						TextField status = new TextField();
						status.setPrefColumnCount(6);
						status.setText("New");
						status.setDisable(true);

						Rectangle ttop = new Rectangle(400, 40, 1100, 150);
						ttop.setStroke(Color.BLACK);
						ttop.setFill(Color.TRANSPARENT);
						StackPane santop = new StackPane();
						santop.setPadding(new Insets(33, 160, 0, 0));

						BorderPane sanadKabdPane = new BorderPane();
						HBox hAll = new HBox(60);
						hAll.setAlignment(Pos.CENTER);
						hAll.setPadding(new Insets(25, 10, 10, 0));
						GridPane g1 = new GridPane();
						g1.setHgap(15);
						g1.setVgap(20);
						HBox h2 = new HBox(7.5);
						HBox h3 = new HBox(15);
						TextField sanadKabdNumber = new TextField();
						TextField sanadKabdDate = new TextField();
						h2.getChildren().addAll(new Label("Voucher Date : "), sanadKabdDate);
						g1.add(new Label("Voucher Number : "), 0, 0);
						g1.add(sanadKabdNumber, 1, 0);
						g1.add(new Label("Coin"), 0, 1);
						g1.add(coinCombo, 1, 1);

						h3.getChildren().addAll(new Label(" Status "), status);
						g1.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
						h3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
						hAll.getChildren().addAll(g1, h2, h3);
						h2.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");

						sanadKabdPane.setTop(santop);
						santop.getChildren().addAll(ttop, hAll);
						/// Top ^
						TextField accNumber = new TextField();
						TextField Name = new TextField();
						TextField Cost = new TextField();
						TextField note = new TextField();

						TextField accNumber1 = new TextField();
						TextField Name1 = new TextField();
						TextField Cost1 = new TextField();
						TextField note1 = new TextField();

						TextField accNumber2 = new TextField();
						TextField Name2 = new TextField();
						TextField Cost2 = new TextField();
						TextField note2 = new TextField();

						TextField accNumber22 = new TextField();
						TextField Name22 = new TextField();
						TextField Cost22 = new TextField();
						TextField note22 = new TextField();
						
						Name.setDisable(true);
						Name1.setDisable(true);
						Name2.setDisable(true);
						Name22.setDisable(true);
						sanadKabdNumber.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									Cost.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});
						Cost.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									Cost.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});
						Cost1.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									Cost1.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});
						Cost2.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									Cost2.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});
						Cost22.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									Cost22.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});
						accNumber.setOnKeyReleased(d -> {
							boolean flag = false;
							String[] ok = null;

							for (int i = 0; i < labelList.size(); i++) {
								String s = labelList.get(i).getText();
								s = s.replaceAll("[()]", "");
								ok = s.split(" ");
								if (ok[0].equalsIgnoreCase(accNumber.getText())) {

									flag = true;
									break;
								}
							}
							if (flag) {
								switch(ok.length) {
								case 2:Name.setText(ok[1]); break;
								case 3:Name.setText(ok[1]+" "+ok[2]);break;
								case 4:Name.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
								case 5:Name.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
								}
							}
						});

						accNumber1.setOnKeyReleased(d -> {
							boolean flag = false;
							String[] ok = null;

							for (int i = 0; i < labelList.size(); i++) {
								String s = labelList.get(i).getText();
								s = s.replaceAll("[()]", "");
								ok = s.split(" ");
								if (ok[0].equalsIgnoreCase(accNumber1.getText())) {

									flag = true;
									break;
								}
							}
							if (flag) {
								switch(ok.length) {
								case 2:Name1.setText(ok[1]); break;
								case 3:Name1.setText(ok[1]+" "+ok[2]);break;
								case 4:Name1.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
								case 5:Name1.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
								}
							}
						});

						accNumber2.setOnKeyReleased(d -> {
							boolean flag = false;
							String[] ok = null;

							for (int i = 0; i < labelList.size(); i++) {
								String s = labelList.get(i).getText();
								s = s.replaceAll("[()]", "");
								ok = s.split(" ");
								if (ok[0].equalsIgnoreCase(accNumber2.getText())) {

									flag = true;
									break;
								}
							}
							if (flag) {
								switch(ok.length) {
								case 2:Name2.setText(ok[1]); break;
								case 3:Name2.setText(ok[1]+" "+ok[2]);break;
								case 4:Name2.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
								case 5:Name2.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
								}
							}
						});

						accNumber22.setOnKeyReleased(d -> {
							boolean flag = false;
							String[] ok = null;

							for (int i = 0; i < labelList.size(); i++) {
								String s = labelList.get(i).getText();
								s = s.replaceAll("[()]", "");
								ok = s.split(" ");
								if (ok[0].equalsIgnoreCase(accNumber22.getText())) {

									flag = true;
									break;
								}
							}
							if (flag) {
								switch(ok.length) {
								case 2:Name22.setText(ok[1]); break;
								case 3:Name22.setText(ok[1]+" "+ok[2]);break;
								case 4:Name22.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
								case 5:Name22.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
								}
							}
						});

						Rectangle tcent = new Rectangle(400, 130, 1100, 430);
						tcent.setStroke(Color.BLACK);
						tcent.setFill(Color.TRANSPARENT);
						StackPane stcent = new StackPane();
						stcent.setPadding(new Insets(0, 160, 0, 0));
						GridPane g2 = new GridPane();
						g2.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						g2.setPadding(new Insets(30, 0, 0, 0));
						g2.setHgap(45);
						g2.setVgap(10);
						Label mm3 = new Label("Debit :");
						mm3.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						mm3.setUnderline(true);
						g2.add(mm3, 0, 0);
						g2.add(new Label("Account Number"), 0, 1);

						g2.add(new Label("Account Name"), 1, 1);

						g2.add(new Label("Amount"), 2, 1);
						g2.add(new Label("Note"), 3, 1);

						g2.add(accNumber, 0, 2);
						g2.add(Name, 1, 2);
						g2.add(Cost, 2, 2);
						g2.add(note, 3, 2);

						g2.add(accNumber1, 0, 3);
						g2.add(Name1, 1, 3);
						g2.add(Cost1, 2, 3);
						g2.add(note1, 3, 3);

						Label mm = new Label("Credit :");
						mm.setUnderline(true);
						mm.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						g2.add(mm, 0, 4);
						g2.add(new Label("Account Number"), 0, 5);
						g2.add(new Label("Name"), 1, 5);
						g2.add(new Label("Amount"), 2, 5);
						g2.add(new Label("Note"), 3, 5);

						g2.add(accNumber2, 0, 6);
						g2.add(Name2, 1, 6);
						g2.add(Cost2, 2, 6);
						g2.add(note2, 3, 6);

						g2.add(accNumber22, 0, 7);
						g2.add(Name22, 1, 7);
						g2.add(Cost22, 2, 7);
						g2.add(note22, 3, 7);

						Button bst = new Button(" Check Status ");
						bst.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						g2.add(bst, 0, 8);
						Label gr = new Label("Balanced");
						Label gr2 = new Label("Unbalanced");

						bst.setOnAction(w -> {
							if (Cost1.getText().trim().isEmpty() && !Cost22.getText().trim().isEmpty()) {
								double summ = Double.parseDouble(Cost.getText()) + 0;
								double dep = Double.parseDouble(Cost2.getText()) + Double.parseDouble(Cost22.getText());
								if (dep == summ) {
									gr2.setTextFill(Color.WHITE);
									gr2.setFont(Font.font(0.1));

									gr.setTextFill(Color.GREEN);
									gr.setFont(Font.font(15));

									g2.add(gr, 1, 8);
								} else if (dep != summ) {
									gr.setTextFill(Color.WHITE);
									gr.setFont(Font.font(0.1));
									gr2.setFont(Font.font(15));
									gr2.setTextFill(Color.RED);
									g2.add(gr2, 1, 8);
								}
							}
							if (Cost22.getText().trim().isEmpty() && !Cost1.getText().trim().isEmpty()) {
								double summ = Double.parseDouble(Cost.getText()) + Double.parseDouble(Cost1.getText());
								double dep = Double.parseDouble(Cost2.getText()) + 0;
								if (dep == summ) {
									gr2.setTextFill(Color.WHITE);
									gr2.setFont(Font.font(0.1));

									gr.setTextFill(Color.GREEN);
									gr.setFont(Font.font(15));

									g2.add(gr, 1, 8);
								} else if (dep != summ) {
									gr.setTextFill(Color.WHITE);
									gr.setFont(Font.font(0.1));
									gr2.setFont(Font.font(15));
									gr2.setTextFill(Color.RED);
									g2.add(gr2, 1, 8);
								}
							}
							if (Cost22.getText().trim().isEmpty() && Cost1.getText().trim().isEmpty()) {
								double summ = Double.parseDouble(Cost.getText()) + 0;
								double dep = Double.parseDouble(Cost2.getText()) + 0;
								if (dep == summ) {
									gr2.setTextFill(Color.WHITE);
									gr2.setFont(Font.font(0.1));

									gr.setTextFill(Color.GREEN);
									gr.setFont(Font.font(15));

									g2.add(gr, 1, 8);
								} else if (dep != summ) {
									gr.setTextFill(Color.WHITE);
									gr.setFont(Font.font(0.1));
									gr2.setFont(Font.font(15));
									gr2.setTextFill(Color.RED);
									g2.add(gr2, 1, 8);
								}
							} else {
								double summ = Double.parseDouble(Cost.getText()) + Double.parseDouble(Cost1.getText());
								double dep = Double.parseDouble(Cost2.getText()) + Double.parseDouble(Cost22.getText());
								if (dep == summ) {
									gr2.setTextFill(Color.WHITE);
									gr2.setFont(Font.font(0.1));

									gr.setTextFill(Color.GREEN);
									gr.setFont(Font.font(15));

									g2.add(gr, 1, 8);
								} else if (dep != summ) {
									gr.setTextFill(Color.WHITE);
									gr.setFont(Font.font(0.1));
									gr2.setFont(Font.font(15));
									gr2.setTextFill(Color.RED);
									g2.add(gr2, 1, 8);
								}
							}

						});

						g2.setAlignment(Pos.TOP_CENTER);
						stcent.getChildren().addAll(tcent, g2);
						sanadKabdPane.setCenter(stcent);

						// center ^

						GridPane g3 = new GridPane();

						Button save = new Button("Save");

						save.setPrefSize(120, 30);

						save.setOnAction(f -> {
                         status.setText("Draft");
							try {
								if (!accNumber.getText().trim().isEmpty()) {

									input.add(new SanadKayd(Long.parseLong(sanadKabdNumber.getText()),
											coinCombo.getValue().toString(), sanadKabdDate.getText(),
											new Accounts(Long.parseLong(accNumber.getText()), Name.getText(),
													Double.parseDouble(Cost.getText()), note.getText())));
									if(input.get(input.size() - 1) instanceof SanadKayd) {
										((SanadKayd)input.get(input.size() - 1)).setCnum("c0");
									}
									input.get(input.size() - 1).saveSanad();
								}
								if (!accNumber1.getText().trim().isEmpty()) {

									input.add(new SanadKayd(Long.parseLong(sanadKabdNumber.getText()),
											coinCombo.getValue().toString(), sanadKabdDate.getText(),
											new Accounts(Long.parseLong(accNumber1.getText()), Name1.getText(),
													Double.parseDouble(Cost1.getText()), note1.getText())));
									if(input.get(input.size() - 1) instanceof SanadKayd) {
										((SanadKayd)input.get(input.size() - 1)).setCnum("c1");
									}
									input.get(input.size() - 1).saveSanad();
								}
								if (!accNumber2.getText().trim().isEmpty()) {
									
									input.add(new SanadKayd(Long.parseLong(sanadKabdNumber.getText()),
											coinCombo.getValue().toString(), sanadKabdDate.getText(),
											new Accounts(Long.parseLong(accNumber2.getText()), Name2.getText(),
													Double.parseDouble(Cost2.getText()), note2.getText())));
									if(input.get(input.size() - 1) instanceof SanadKayd) {
										((SanadKayd)input.get(input.size() - 1)).setCnum("c2");
									}
									input.get(input.size() - 1).saveSanad();
								}

								if (!accNumber22.getText().trim().isEmpty()) {

									input.add(new SanadKayd(Long.parseLong(sanadKabdNumber.getText()),
											coinCombo.getValue().toString(), sanadKabdDate.getText(),
											new Accounts(Long.parseLong(accNumber22.getText()), Name22.getText(),
													Double.parseDouble(Cost22.getText()), note22.getText())));
									if(input.get(input.size() - 1) instanceof SanadKayd) {
										((SanadKayd)input.get(input.size() - 1)).setCnum("c3");
									}
									input.get(input.size() - 1).saveSanad();

								}

							} catch (Exception n) {
								JOptionPane.showMessageDialog(null, "The Voucher Saved Before");
							}
						});

						Button printt = new Button("Print");
						printt.setPrefSize(120, 30);
						Button tarhel = new Button("Post");
						tarhel.setOnAction(h -> {

							String rr = "";
							try {
								Scanner inFile = new Scanner(refresh);
								while(inFile.hasNextLine()) {
									rr+= inFile.nextLine()+"\n";
								}
							}
							catch(Exception g) {
								
							}
							try(PrintWriter p = new PrintWriter(refresh)){
								long n=0;
								long n1=0;
								long n2=0;
								long n3=0;
								try {
									n=Long.parseLong(accNumber.getText());
								}
								catch(Exception m) {
									 n=0;
								}
								try {
									n1=Long.parseLong(accNumber1.getText());
								}
								catch(Exception m) {
									 n1=0;
								}
								try {
									n2=Long.parseLong(accNumber2.getText());
								}
								catch(Exception m) {
									 n2=0;
								}
								try {
									n3=Long.parseLong(accNumber22.getText());
								}
								catch(Exception m) {
									 n3=0;
								}
								
								
								if(rr == "") {   // assets and expense  drawings
									if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
									
										p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+"," +coinCombo.getValue()+"," +note.getText()+"\n");
									
									}
									else if(n!=0){
										p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
										
									}
									if((n1>110 && n1<125) || (n1==340) || (n1>509 && n1<551) && n1!=0) {
										
										p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",+"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
									
									}
									else if(n1!=0){
										p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",-"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
										
									}
									if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
										
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
									
									}
									else if(n2!=0){
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
										
									}
									if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
										
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
									
									}
									else if(n3!=0){
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
										
									}
								}
								else {
									p.append(rr);
									if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
										
										p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
									
									}
									else if(n!=0){
										p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
										
									}
									if((n1>110 && n1<125) || (n1==340) || (n1>509 && n1<551) && n1!=0) {
										
										p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",+"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
									
									}
									else if(n1!=0){
										p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",-"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
										
									}
									if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
										
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
									
									}
									else if(n2!=0){
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
										
									}
									if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
										
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
									
									}
									else if(n3!=0){
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
										
									}
										
								}
							} catch (FileNotFoundException e1) {
								System.out.println("Creat Refresh file");
							}
							status.setText("Posted");

						});
						tarhel.setPrefSize(120, 30);
						Button printAndTarhel = new Button("Clear");
						printAndTarhel.setOnAction(wa->{
							sanadKabdDate.clear();
							sanadKabdNumber.clear();
							accNumber.clear();
							accNumber1.clear();
							accNumber2.clear();
							accNumber22.clear();
							
							Name.clear();
							Name1.clear();
							Name2.clear();
							Name22.clear();
							Cost.clear();
							Cost1.clear();
							Cost2.clear();
							Cost22.clear();

							note.clear();
							note1.clear();
							note2.clear();
							note22.clear();
							status.setText("New");
							gr.setTextFill(Color.WHITE);
							gr.setFont(Font.font(0.1));
							gr2.setTextFill(Color.WHITE);
							gr2.setFont(Font.font(0.1));
							
						});
						printAndTarhel.setPrefSize(150, 30);

						g3.add(save, 0, 0);
						g3.add(tarhel, 1, 0);
						g3.add(printt, 2, 0);
						g3.add(printAndTarhel, 3, 0);
						g3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
						g3.setHgap(110);
						g3.setPadding(new Insets(0, 60, 20, 0));
						g3.setAlignment(Pos.CENTER);
						sanadKabdPane.setBottom(g3);
						root51.getChildren().add(sanadKabdPane);

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						// hsb13a5.setPrefHeight(30);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						px1.setBottom(hsb13a5);

						primaryStage.setScene(selx1);
						primaryStage.show();

					} else if (bills1.getValue() == "             Search in Voucher") {

						ImageView back1 = new ImageView("dsBuffer.bmp.png");
						back1.setPreserveRatio(true);
						Button bk1 = new Button("Back", back1);
						bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151 = new Label(
								"                               Search Jornal Voucher                          ");
						logon151.setFont(Font.font(30));

						bk1.setOnAction(ss -> {
							primaryStage.setScene(scene1);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("LogOut", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setPadding(new Insets(7, 0, 0, 0));
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk1, logon151, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
						Jornalsch.setTop(hh15);

						StackPane root51 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root51.setBackground(bGround5);

						GridPane ss = new GridPane();
						ss.setHgap(40);
						Label xd = new Label("Enter Voucher Number");
						DropShadow shadow = new DropShadow();
						shadow.setOffsetX(2);
						shadow.setOffsetY(3);
						shadow.setColor(Color.SKYBLUE);
						xd.setEffect(shadow);
						Button find = new Button(" Search ");
						TextField ll = new TextField();
						find.setOnAction(o -> {
							try {
								primaryStage.setScene(kayd1(ll.getText(), primaryStage));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							primaryStage.show();
						});

						ss.add(xd, 0, 0);
						ss.add(ll, 1, 0);
						ss.add(find, 2, 0);
						ss.setStyle("-fx-font-size: 22pt;-fx-font-weight: bold;");
						root51.getChildren().add(ss);
						ss.setPadding(new Insets(250, 0, 0, 370));
						Jornalsch.setCenter(root51);

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(10, 10, 0, 50));
						hsb13a5.setSpacing(40);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						Jornalsch.setBottom(hsb13a5);

						primaryStage.setScene(Jornalschs);
						primaryStage.show();

					}

				}
			};
			bills1.setOnAction(evenxd);

			EventHandler<ActionEvent> evenx = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					if (bills2.getValue() == "             Enter Voucher") {

						ImageView back1 = new ImageView("dsBuffer.bmp.png");
						back1.setPreserveRatio(true);
						Button bk1 = new Button("Back", back1);
						bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151 = new Label(
								"                                  Receipt Voucher                     ");
						logon151.setFont(Font.font(35));
						if(!ils.isSelected()&&!jod.isSelected()&&!usd.isSelected()) {
							JOptionPane.showMessageDialog(null, "Please choose a curancy");
						}
						
						bk1.setOnAction(ss -> {

							primaryStage.setScene(scene1);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("LogOut", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk1, logon151, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
						px.setTop(hh15);

						StackPane root51 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root51.setBackground(bGround5);
						px.setCenter(root51);
						String[] cointxt = { "ILS", "USD", "JOD" };
						ComboBox coinCombo = new ComboBox(FXCollections.observableArrayList(cointxt));
						coinCombo.setStyle("-fx-font-size: 10pt;-fx-font-weight: bold;");
						coinCombo.setPromptText("Select Currency");
						String[] s = ttg.getSelectedToggle().toString().split(" ");
						coinCombo.setValue(s[1]);
						coinCombo.setDisable(true);
						TextField status = new TextField();
						status.setPrefColumnCount(6);
						status.setText("New");
						status.setDisable(true);

						Rectangle ttop = new Rectangle(400, 40, 1100, 200);
						ttop.setStroke(Color.BLACK);
						ttop.setFill(Color.TRANSPARENT);
						StackPane santop = new StackPane();
						santop.setPadding(new Insets(33, 160, 0, 0));

						BorderPane sanadKabdPane = new BorderPane();
						HBox hAll = new HBox(40);
						hAll.setAlignment(Pos.CENTER);
						hAll.setPadding(new Insets(25, 10, 10, 0));
						GridPane g1 = new GridPane();
						HBox hdamg = new HBox();
						TextField td = new TextField();
						td.setPrefColumnCount(1);
						td.setDisable(true);
						td.setText("A");
						g1.setHgap(15);
						g1.setHgap(15);
						g1.setVgap(20);
						HBox h2 = new HBox(7.5);
						HBox h3 = new HBox(10);
						TextField sanadKabdNumber = new TextField();
						sanadKabdNumber.setPrefWidth(150);
						TextField sanadKabdDate = new TextField();
						TextField ssa = new TextField();
						hdamg.getChildren().addAll(td, sanadKabdNumber);
						h2.getChildren().addAll(new Label("Voucher Date : "), sanadKabdDate);
						g1.add(new Label("Voucher Number : "), 0, 0);
						g1.add(hdamg, 1, 0);
						g1.add(new Label("Coin"), 0, 1);
						g1.add(coinCombo, 1, 1);
						g1.add(new Label("Bill Number : "), 0, 2);
						g1.add(ssa, 1, 2);
						Button fin = new Button("Find Bill");
						g1.add(fin, 2, 2);
						h3.getChildren().addAll(new Label(" Status "), status);
						g1.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
						h3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
						hAll.getChildren().addAll(g1, h2, h3);
						h2.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");

						sanadKabdPane.setTop(santop);
						santop.getChildren().addAll(ttop, hAll);
						/// Top ^
						TextField accNumber = new TextField();
						accNumber.setDisable(true);
						accNumber.setText("111");
						TextField Name = new TextField();
						Name.setDisable(true);
						Name.setText("Cash");
						TextField Cost = new TextField();
						TextField note = new TextField();

						TextField accNumber2 = new TextField();
						accNumber2.setDisable(true);
						accNumber2.setText("410");
						TextField Name2 = new TextField();
						Name2.setDisable(true);
						Name2.setText("Revenue");
						TextField Cost2 = new TextField();
						TextField note2 = new TextField();

						TextField accNumber22 = new TextField();
						accNumber22.setDisable(true);
						accNumber22.setText("220");
						TextField Name22 = new TextField();
						Name22.setDisable(true);
						Name22.setText("Value Added tax Provision");
						TextField Cost22 = new TextField();
						TextField note22 = new TextField();

						Cost.setText(String.valueOf(ss + ss1));
						Cost.setDisable(true);

						Cost2.setText(String.valueOf(ss1));
						Cost2.setDisable(true);

						Cost22.setText(String.valueOf(ss));
						Cost22.setDisable(true);

						Rectangle tcent = new Rectangle(400, 130, 1100, 360);
						tcent.setStroke(Color.BLACK);
						tcent.setFill(Color.TRANSPARENT);
						StackPane stcent = new StackPane();
						stcent.setPadding(new Insets(0, 160, 0, 0));
						GridPane g2 = new GridPane();
						g2.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
						g2.setPadding(new Insets(30, 0, 0, 0));
						g2.setHgap(45);
						g2.setVgap(10);
						Label mm3 = new Label("Debit :");
						mm3.setStyle("-fx-font-size: 15pt;-fx-font-weight: bold;");
						mm3.setUnderline(true);
						g2.add(mm3, 0, 0);
						g2.add(new Label("Account Number"), 0, 1);
						g2.add(new Label("Name"), 1, 1);
						g2.add(new Label("Amount"), 2, 1);
						g2.add(new Label("Note"), 3, 1);

						g2.add(accNumber, 0, 2);
						g2.add(Name, 1, 2);
						g2.add(Cost, 2, 2);
						g2.add(note, 3, 2);
						Label mm = new Label("Credit :");
						mm.setUnderline(true);
						mm.setStyle("-fx-font-size: 15pt;-fx-font-weight: bold;");
						g2.add(mm, 0, 3);
						g2.add(new Label("Account Number"), 0, 4);
						g2.add(new Label("Name"), 1, 4);
						g2.add(new Label("Amount"), 2, 4);
						g2.add(new Label("Note"), 3, 4);

						g2.add(accNumber2, 0, 5);
						g2.add(Name2, 1, 5);
						g2.add(Cost2, 2, 5);
						g2.add(note2, 3, 5);

						g2.add(accNumber22, 0, 6);
						g2.add(Name22, 1, 6);
						g2.add(Cost22, 2, 6);
						g2.add(note22, 3, 6);
						Button bst = new Button(" Check Status ");
						bst.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						g2.add(bst, 0, 7);
						Label gr = new Label("Balanced");
						Label gr2 = new Label("Unbalanced");

						bst.setOnAction(w -> {
							double summ = Double.parseDouble(Cost2.getText()) + Double.parseDouble(Cost22.getText());
							double dep = Double.parseDouble(Cost.getText());
							if (summ == dep) {
								gr2.setTextFill(Color.WHITE);
								gr2.setFont(Font.font(0.1));

								gr.setTextFill(Color.GREEN);
								gr.setFont(Font.font(15));

								g2.add(gr, 1, 7);
							} else if (summ != dep) {
								gr.setTextFill(Color.WHITE);
								gr.setFont(Font.font(0.1));
								gr2.setFont(Font.font(15));
								gr2.setTextFill(Color.RED);
								g2.add(gr2, 1, 7);
							}
						});

						g2.setAlignment(Pos.TOP_CENTER);
						stcent.getChildren().addAll(tcent, g2);
						sanadKabdPane.setCenter(stcent);

						// center ^
						ssa.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									ssa.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});
					
						GridPane g3 = new GridPane();

						Button save = new Button("Save");
						save.setOnAction(f -> {
							status.setText("Draft");
							
							try {
								if (!accNumber.getText().trim().isEmpty()) {

									input.add(new SanadKabd(Long.parseLong(sanadKabdNumber.getText()),
											coinCombo.getValue().toString(), Long.parseLong(ssa.getText()),
											sanadKabdDate.getText(),
											new Accounts(Long.parseLong(accNumber.getText()), Name.getText(),
													Double.parseDouble(Cost.getText()), note.getText())));
									input.get(input.size() - 1).saveSanad();
								}

								if (!accNumber2.getText().trim().isEmpty()) {

									input.add(new SanadKabd(Long.parseLong(sanadKabdNumber.getText()),
											coinCombo.getValue().toString(), Long.parseLong(ssa.getText()),
											sanadKabdDate.getText(),
											new Accounts(Long.parseLong(accNumber2.getText()), Name2.getText(),
													Double.parseDouble(Cost2.getText()), note2.getText())));
									input.get(input.size() - 1).saveSanad();
								}

								if (!accNumber22.getText().trim().isEmpty()) {
									input.add(new SanadKabd(Long.parseLong(sanadKabdNumber.getText()),
											coinCombo.getValue().toString(), Long.parseLong(ssa.getText()),
											sanadKabdDate.getText(),
											new Accounts(Long.parseLong(accNumber22.getText()), Name22.getText(),
													Double.parseDouble(Cost22.getText()), note22.getText())));
									input.get(input.size() - 1).saveSanad();

								}

							} catch (Exception n) {
								JOptionPane.showMessageDialog(null, "The Voucher Saved Before");
							}
						});
						save.setPrefSize(120, 30);
						Button printt = new Button("Print");
						printt.setPrefSize(120, 30);
						Button tarhel = new Button("Post");
						tarhel.setOnAction(h -> {

							String rr = "";
							try {
								Scanner inFile = new Scanner(refresh);
								while(inFile.hasNextLine()) {
									rr+= inFile.nextLine()+"\n";
								}
							}
							catch(Exception g) {
								
							}
							try(PrintWriter p = new PrintWriter(refresh)){
								long n=0;
								long n2=0;
								long n3=0;
								try {
									n=Long.parseLong(accNumber.getText());
								}
								catch(Exception m) {
									 n=0;
								}
								
								try {
									n2=Long.parseLong(accNumber2.getText());
								}
								catch(Exception m) {
									 n2=0;
								}
								try {
									n3=Long.parseLong(accNumber22.getText());
								}
								catch(Exception m) {
									 n3=0;
								}
								
								
								if(rr == "") {   // assets and expense  drawings
									if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
									
										p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
									
									}
									else if(n!=0){
										p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
										
									}
									
									if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
										
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
									
									}
									else if(n2!=0){
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
										
									}
									if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
										
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+coinCombo.getValue()+","+","+note22.getText()+"\n");
									
									}
									else if(n3!=0){
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+coinCombo.getValue()+","+","+note22.getText()+"\n");
										
									}
								}
								else {
									p.append(rr);
									if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
										
										p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
									
									}
									else if(n!=0){
										p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
										
									}
									
									if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
										
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
									
									}
									else if(n2!=0){
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
										
									}
									if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
										
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
									
									}
									else if(n3!=0){
										p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
										
									}
										
								}
							} catch (FileNotFoundException e1) {
								System.out.println("Creat Refresh file");
							}
							status.setText("Posted");

						});
						tarhel.setPrefSize(120, 30);
						Button printAndTarhel = new Button("Clear");
						printAndTarhel.setOnAction(wa->{
							sanadKabdDate.clear();
							sanadKabdNumber.clear();
							
							Cost.clear();
						
							Cost2.clear();
							Cost22.clear();

							note.clear();
							
							note2.clear();
							note22.clear();
							status.setText("New");
							ssa.clear();
							
							
						});
						printAndTarhel.setPrefSize(150, 30);

						g3.add(save, 0, 0);
						g3.add(tarhel, 1, 0);
						g3.add(printt, 2, 0);
						g3.add(printAndTarhel, 3, 0);
						g3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
						g3.setHgap(140);
						g3.setPadding(new Insets(0, 60, 20, 0));
						g3.setAlignment(Pos.CENTER);
						sanadKabdPane.setBottom(g3);
						root51.getChildren().add(sanadKabdPane);

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						// hsb13a5.setPrefHeight(30);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						px.setBottom(hsb13a5);

						fin.setOnAction(ll -> {
							for (int i = 0; i < billNumber.size(); i++) {
								if (billNumber.get(i).getBillnum().equalsIgnoreCase(ssa.getText())) {

									Cost.setText(billNumber.get(i).getShamel());
									Cost2.setText(billNumber.get(i).getUnshamel());
									Cost22.setText(billNumber.get(i).getVat());
									coinCombo.setValue(billNumber.get(i).getType());
								}
							}
						});

						primaryStage.setScene(selx);
						primaryStage.show();

					}

					else if (bills2.getValue() == "             Search in Voucher") {

						ImageView back1 = new ImageView("dsBuffer.bmp.png");
						back1.setPreserveRatio(true);
						Button bk1 = new Button("Back", back1);
						bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151 = new Label(
								"                              Search Receipt Voucher                          ");
						logon151.setFont(Font.font(30));

						bk1.setOnAction(ss -> {
							primaryStage.setScene(scene1);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("LogOut", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setPadding(new Insets(7, 0, 0, 0));
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk1, logon151, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
						Jornalsch1.setTop(hh15);

						StackPane root51 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root51.setBackground(bGround5);

						GridPane ss = new GridPane();
						ss.setHgap(40);
						Label xd = new Label("Enter Voucher Number");
						DropShadow shadow = new DropShadow();
						shadow.setOffsetX(2);
						shadow.setOffsetY(3);
						shadow.setColor(Color.SKYBLUE);
						xd.setEffect(shadow);
						Button find = new Button(" Search ");
						TextField ll = new TextField();
						find.setOnAction(o -> {
							try {
								primaryStage.setScene(kabd1(ll.getText(), primaryStage));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							primaryStage.show();
						});

						ss.add(xd, 0, 0);
						ss.add(ll, 1, 0);
						ss.add(find, 2, 0);
						ss.setStyle("-fx-font-size: 22pt;-fx-font-weight: bold;");
						root51.getChildren().add(ss);
						ss.setPadding(new Insets(250, 0, 0, 370));
						Jornalsch1.setCenter(root51);

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(10, 10, 0, 50));
						hsb13a5.setSpacing(40);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						Jornalsch1.setBottom(hsb13a5);

						primaryStage.setScene(Jornalschs1);
						primaryStage.show();

					}

				}
			};
			bills2.setOnAction(evenx);

			EventHandler<ActionEvent> evenx2 = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					if (bills3.getValue() == "             Enter Voucher") {

						ImageView back1 = new ImageView("dsBuffer.bmp.png");
						back1.setPreserveRatio(true);
						Button bk1 = new Button("Back", back1);
						bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151 = new Label(
								"                                  Payment Voucher                   ");
						logon151.setFont(Font.font(35));

						bk1.setOnAction(ss -> {
							primaryStage.setScene(scene1);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("LogOut", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();
						if(!ils.isSelected()&&!jod.isSelected()&&!usd.isSelected()) {
							JOptionPane.showMessageDialog(null, "Please choose a curancy");
						}
						
						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk1, logon151, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
						px2.setTop(hh15);

						StackPane root51 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root51.setBackground(bGround5);
						px2.setCenter(root51);
						String[] cointxt = { "ILS", "USD", "JOD" };
						ComboBox coinCombo = new ComboBox(FXCollections.observableArrayList(cointxt));
						coinCombo.setStyle("-fx-font-size: 10pt;-fx-font-weight: bold;");
						coinCombo.setPromptText("Select Currency");
						String[] s = ttg.getSelectedToggle().toString().split(" ");
						coinCombo.setValue(s[1]);
						coinCombo.setDisable(true);
						TextField status = new TextField();
						status.setPrefColumnCount(6);
						status.setText("New");
						status.setDisable(true);

						Rectangle ttop = new Rectangle(400, 40, 1100, 200);
						ttop.setStroke(Color.BLACK);
						ttop.setFill(Color.TRANSPARENT);
						StackPane santop = new StackPane();
						santop.setPadding(new Insets(33, 160, 0, 0));

						BorderPane sanadKabdPane = new BorderPane();
						HBox hAll = new HBox(40);
						hAll.setAlignment(Pos.CENTER);
						hAll.setPadding(new Insets(25, 10, 10, 0));
						GridPane g1 = new GridPane();
						HBox hdamg = new HBox();
						TextField td = new TextField();
						td.setPrefColumnCount(1);
						td.setDisable(true);
						td.setText("B");
						g1.setHgap(10);
						g1.setVgap(20);
						HBox h2 = new HBox(7.5);
						HBox h3 = new HBox(15);
						TextField sanadKabdNumber = new TextField();
						TextField sanadKabdDate = new TextField();
						sanadKabdNumber.setPrefWidth(150);
						hdamg.getChildren().addAll(td, sanadKabdNumber);
						TextField xs = new TextField();
						h2.getChildren().addAll(new Label("Voucher Date : "), sanadKabdDate);
						g1.add(new Label("Voucher Number : "), 0, 0);
						g1.add(hdamg, 1, 0);
						g1.add(new Label("Coin"), 0, 1);
						g1.add(coinCombo, 1, 1);
						g1.add(new Label("Bill Number : "), 0, 2);
						g1.add(xs, 1, 2);
						Button fin = new Button("Find Bill");
						g1.add(fin, 2, 2);

						h3.getChildren().addAll(new Label(" Status "), status);
						g1.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						h3.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						hAll.getChildren().addAll(g1, h2, h3);
						h2.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");

						sanadKabdPane.setTop(santop);
						santop.getChildren().addAll(ttop, hAll);
						/// Top ^
						
						String [] payacc ={"113","121","122","123","124"};
						ComboBox<String> accNumber = new ComboBox<>(FXCollections.observableArrayList(payacc));
						accNumber.setPromptText("Select acc #");
						
						TextField Name = new TextField();
						TextField Cost = new TextField();
						Name.setDisable(true);
						TextField note = new TextField();

						accNumber.setOnAction(p->{
							boolean flag = false;
							String[] ok = null;

							for (int i = 0; i < labelList.size(); i++) {
								String sx = labelList.get(i).getText();
								sx = sx.replaceAll("[()]", "");
								ok = sx.split(" ");
								if (ok[0].equalsIgnoreCase(accNumber.getValue())) {
									
									flag = true;
									break;
								}
							}
							if (flag) {
								switch(ok.length) {
								case 2:Name.setText(ok[1]); break;
								case 3:Name.setText(ok[1]+" "+ok[2]);break;
								case 4:Name.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
								case 5:Name.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
								}
							}
						});
					
						TextField accNumber1 = new TextField();
						accNumber1.setDisable(true);
						TextField Name1 = new TextField();
						Name1.setDisable(true);
						TextField Cost1 = new TextField();
						TextField note1 = new TextField();

						TextField accNumber2 = new TextField();
						TextField Name2 = new TextField();
						TextField Cost2 = new TextField();
						TextField note2 = new TextField();

						Rectangle tcent = new Rectangle(400, 130, 1100, 360);
						tcent.setStroke(Color.BLACK);
						tcent.setFill(Color.TRANSPARENT);
						StackPane stcent = new StackPane();
						stcent.setPadding(new Insets(0, 160, 0, 0));
						GridPane g2 = new GridPane();
						g2.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						g2.setPadding(new Insets(30, 0, 0, 0));
						g2.setHgap(45);
						g2.setVgap(10);
						Label mm3 = new Label("Debit :");
						mm3.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						mm3.setUnderline(true);
						g2.add(mm3, 0, 0);
						g2.add(new Label("Account Number"), 0, 1);

						g2.add(new Label("Account Name"), 1, 1);

						g2.add(new Label("Amount"), 2, 1);
						g2.add(new Label("Note"), 3, 1);

						g2.add(accNumber, 0, 2);
						
						g2.add(Name, 1, 2);
						
						
						
					///////////////////////////////////////////////////////	Name.setText("General Purchase Expense");
						g2.add(Cost, 2, 2);
						g2.add(note, 3, 2);

						g2.add(accNumber1, 0, 3);
						accNumber1.setText("220");
						g2.add(Name1, 1, 3);
						Name1.setText("Value Added tax Provision");
						g2.add(Cost1, 2, 3);
						g2.add(note1, 3, 3);

						Cost.setDisable(true);
						Cost1.setDisable(true);
						Cost2.setDisable(true);

						Label mm = new Label("Credit :");
						mm.setUnderline(true);
						mm.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						g2.add(mm, 0, 4);
						g2.add(new Label("Account Number"), 0, 5);
						g2.add(new Label("Name"), 1, 5);
						g2.add(new Label("Amount"), 2, 5);
						g2.add(new Label("Note"), 3, 5);

						g2.add(accNumber2, 0, 6);
						accNumber2.setText("111");
						accNumber2.setDisable(true);
						Name2.setText("Cash");
						Name2.setDisable(true);
						g2.add(Name2, 1, 6);
						g2.add(Cost2, 2, 6);
						g2.add(note2, 3, 6);

						Button bst = new Button(" Check Status ");
						bst.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						g2.add(bst, 0, 8);
						Label gr = new Label("Balanced");
						Label gr2 = new Label("Unbalanced");

						fin.setOnAction(cc -> {
							for (int i = 0; i < billNumber2.size(); i++) {

								if (billNumber2.get(i).getBillnum().equalsIgnoreCase(xs.getText())) {
									Cost.setText(billNumber2.get(i).getUnshamel());
									Cost1.setText(billNumber2.get(i).getVat());
									Cost2.setText(billNumber2.get(i).getShamel());
									//coinCombo.setValue(billNumber.get(i).getType());
								}
							}

						});

						bst.setOnAction(w -> {
							try {
								double summ = Double.parseDouble(Cost.getText()) + Double.parseDouble(Cost1.getText());
								double dep = Double.parseDouble(Cost2.getText());
								if (dep == summ) {
									gr2.setTextFill(Color.WHITE);
									gr2.setFont(Font.font(0.1));

									gr.setTextFill(Color.GREEN);
									gr.setFont(Font.font(15));

									g2.add(gr, 1, 8);
								} else if (dep != summ) {
									gr.setTextFill(Color.WHITE);
									gr.setFont(Font.font(0.1));
									gr2.setFont(Font.font(15));
									gr2.setTextFill(Color.RED);
									g2.add(gr2, 1, 8);
								}

							} catch (Exception se) {
								// TODO: handle exception
							}

						});
						g2.setAlignment(Pos.TOP_CENTER);
						stcent.getChildren().addAll(tcent, g2);
						sanadKabdPane.setCenter(stcent);

						// center ^

						xs.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									xs.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});
						

						GridPane g3 = new GridPane();

						Button save = new Button("Save");
						save.setOnAction(f -> {
status.setText("Draft");
							try {
								if (!accNumber.getValue().trim().isEmpty()) {

									input.add(new SanadSarf(Long.parseLong(sanadKabdNumber.getText()),
											coinCombo.getValue().toString(), Long.parseLong(xs.getText()),
											sanadKabdDate.getText(),
											new Accounts(Long.parseLong(accNumber.getValue()), Name.getText(),
													Double.parseDouble(Cost.getText()), note.getText())));
									input.get(input.size() - 1).saveSanad();
								}
								if (!accNumber1.getText().trim().isEmpty()) {

									input.add(new SanadSarf(Long.parseLong(sanadKabdNumber.getText()),
											coinCombo.getValue().toString(), Long.parseLong(xs.getText()),
											sanadKabdDate.getText(),
											new Accounts(Long.parseLong(accNumber1.getText()), Name1.getText(),
													Double.parseDouble(Cost1.getText()), note1.getText())));

									input.get(input.size() - 1).saveSanad();
								}
								if (!accNumber2.getText().trim().isEmpty()) {

									input.add(new SanadSarf(Long.parseLong(sanadKabdNumber.getText()),
											coinCombo.getValue().toString(), Long.parseLong(xs.getText()),
											sanadKabdDate.getText(),
											new Accounts(Long.parseLong(accNumber2.getText()), Name2.getText(),
													Double.parseDouble(Cost2.getText()), note2.getText())));
									input.get(input.size() - 1).saveSanad();
								}

							} catch (Exception n) {
								JOptionPane.showMessageDialog(null, "The Voucher Saved Before");
							}
						});
						save.setPrefSize(120, 30);
						Button printt = new Button("Print");
						printt.setPrefSize(120, 30);
						Button tarhel = new Button("Post");
						tarhel.setOnAction(h -> {

							String rr = "";
							try {
								Scanner inFile = new Scanner(refresh);
								while(inFile.hasNextLine()) {
									rr+= inFile.nextLine()+"\n";
								}
							}
							catch(Exception g) {
								
							}
							try(PrintWriter p = new PrintWriter(refresh)){
								long n=0;
								long n1=0;
								long n2=0;
								
								try {
									n=Long.parseLong(accNumber.getValue());
								}
								catch(Exception m) {
									 n=0;
								}
								try {
									n1=Long.parseLong(accNumber1.getText());
								}
								catch(Exception m) {
									 n1=0;
								}
								try {
									n2=Long.parseLong(accNumber2.getText());
								}
								catch(Exception m) {
									 n2=0;
								}
								
								if(rr == "") {   // assets and expense  drawings
									if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
									
										p.append(accNumber.getValue()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
									
									}
									else if(n!=0){
										p.append(accNumber.getValue()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
										
									}
									if((n1>110 && n1<125) || (n1==340) || (n1>509 && n1<551) && n1!=0) {
										
										p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",+"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
									
									}
									else if(n1!=0){
										p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",-"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
										
									}
									if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
										
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
									
									}
									else if(n2!=0){
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
										
									}
									
								}
								else {
									p.append(rr);
									if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
										
										p.append(accNumber.getValue()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
									
									}
									else if(n!=0){
										p.append(accNumber.getValue()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
										
									}
									if((n1>110 && n1<125) || (n1==340) || (n1>509 && n1<551) && n1!=0) {
										
										p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",+"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
									
									}
									else if(n1!=0){
										p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",-"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
										
									}
									if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
										
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
									
									}
									else if(n2!=0){
										p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
										
									}
								
										
								}
							} catch (FileNotFoundException e1) {
								System.out.println("Creat Refresh file");
							}
							status.setText("Posted");

						});
						tarhel.setPrefSize(120, 30);
						Button printAndTarhel = new Button("Clear");
						printAndTarhel.setOnAction(wa->{
							sanadKabdDate.clear();
							sanadKabdNumber.clear();
						
							Cost.clear();
							Cost1.clear();
							Cost2.clear();
xs.clear();
							note.clear();
							note1.clear();
							note2.clear();
							status.setText("New");
							gr2.setTextFill(Color.WHITE);
							gr2.setFont(Font.font(0.1));
							gr2.setTextFill(Color.WHITE);
							gr2.setFont(Font.font(0.1));
						});
						printAndTarhel.setPrefSize(150, 30);

						g3.add(save, 0, 0);
						g3.add(tarhel, 1, 0);
						g3.add(printt, 2, 0);
						g3.add(printAndTarhel, 3, 0);
						g3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
						g3.setHgap(140);
						g3.setPadding(new Insets(0, 60, 20, 0));
						g3.setAlignment(Pos.CENTER);
						sanadKabdPane.setBottom(g3);
						root51.getChildren().add(sanadKabdPane);

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						// hsb13a5.setPrefHeight(30);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						px2.setBottom(hsb13a5);

						primaryStage.setScene(selx2);

						primaryStage.show();

					}

					else if (bills3.getValue() == "             Search in Voucher") {

						ImageView back1 = new ImageView("dsBuffer.bmp.png");
						back1.setPreserveRatio(true);
						Button bk1 = new Button("Back", back1);
						bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151 = new Label(
								"                              Search Payment Voucher                         ");
						logon151.setFont(Font.font(30));

						bk1.setOnAction(ss -> {
							primaryStage.setScene(scene1);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("LogOut", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setPadding(new Insets(7, 0, 0, 0));
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk1, logon151, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
						Jornalsch2.setTop(hh15);

						StackPane root51 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root51.setBackground(bGround5);

						GridPane ss = new GridPane();
						ss.setHgap(40);
						Label xd = new Label("Enter Voucher Number");
						DropShadow shadow = new DropShadow();
						shadow.setOffsetX(2);
						shadow.setOffsetY(3);
						shadow.setColor(Color.SKYBLUE);
						xd.setEffect(shadow);
						Button find = new Button(" Search ");
						TextField ll = new TextField();
						find.setOnAction(o -> {
							try {
								primaryStage.setScene(sarf1(ll.getText(), primaryStage));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							primaryStage.show();
						});

						ss.add(xd, 0, 0);
						ss.add(ll, 1, 0);
						ss.add(find, 2, 0);
						ss.setStyle("-fx-font-size: 22pt;-fx-font-weight: bold;");
						root51.getChildren().add(ss);
						ss.setPadding(new Insets(250, 0, 0, 370));
						Jornalsch2.setCenter(root51);

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(10, 10, 0, 50));
						hsb13a5.setSpacing(40);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						Jornalsch2.setBottom(hsb13a5);

						primaryStage.setScene(Jornalschs2);
						primaryStage.show();

					}

				}
			};
			bills3.setOnAction(evenx2);

			EventHandler<ActionEvent> evenb = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					if (bills.getValue() == "             Sales Bill") {
						ImageView back = new ImageView("dsBuffer.bmp.png");
						back.setPreserveRatio(true);
						Button bk = new Button("Back", back);
						bk.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon15 = new Label(
								"                                    Sales Bill                                ");
						logon15.setFont(Font.font(35));

						bk.setOnAction(ss -> {
							primaryStage.setScene(scene1);

							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("LogOut", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk, logon15, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon15.setFont(Font.font(null, FontWeight.BOLD, logon15.getFont().getSize()));
						p.setTop(hh15);

						StackPane root5 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root5.setBackground(bGround5);
						p.setCenter(root5);

						GridPane grid = new GridPane();
						grid.setHgap(15);
						grid.setVgap(20);
						grid.setPadding(new Insets(20, 20, 20, 20));

						Label dat = new Label("Date :");
						dat.setFont(Font.font(25));
						dat.setStyle("-fx-font-weight: bold;");
						dat.setUnderline(true);

						Label numb = new Label("Bills Number :");
						numb.setFont(Font.font(25));
						numb.setStyle("-fx-font-weight: bold;");
						numb.setUnderline(true);
						grid.setAlignment(Pos.TOP_LEFT);

						TextField datef = new TextField();
						datef.setStyle("-fx-font-weight: bold;-fx-font-color: black;");
						datef.setFont(Font.font(15));

						datef.setFont(Font.font(15));
						TextField billn = new TextField();
						grid.add(dat, 0, 0);
						grid.add(datef, 1, 0);
						grid.add(numb, 0, 1);
						grid.add(billn, 1, 1);
						BorderPane sba = new BorderPane();
						root5.getChildren().add(sba);
						sba.setTop(grid);

						billn.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									billn.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});

						StackPane stak2 = new StackPane();
						stak2.setAlignment(Pos.CENTER);

						Rectangle r1 = new Rectangle(0, 10, 1200, 400);
						r1.setStroke(Color.BLACK);
						r1.setFill(Color.TRANSPARENT);

						Rectangle r2 = new Rectangle(820, 10, 380, 400);
						r2.setStroke(Color.BLACK);
						r2.setFill(Color.TRANSPARENT);

						Group gb = new Group();
						gb.getChildren().addAll(r1, r2);
						stak2.getChildren().addAll(gb);
						sba.setCenter(stak2);
						BorderPane por = new BorderPane();
						// sba.setAlignment(Pos.CENTER);

						GridPane grid2 = new GridPane();
						VBox vbox = new VBox();
						HBox h12 = new HBox();
						VBox vbox2 = new VBox();

						vbox.setAlignment(Pos.CENTER);
						Label ttyb = new Label("Tickets Tybe:");
						ttyb.setFont(Font.font(25));
						ttyb.setStyle("-fx-font-weight: bold;");
						Label tnum = new Label("Tickets Number:");
						tnum.setFont(Font.font(25));
						tnum.setStyle("-fx-font-weight: bold;");
						// dat.setUnderline(true);

						Label tpric = new Label("  Ticket Price:   ");
						tpric.setFont(Font.font(25));
						tpric.setStyle("-fx-font-weight: bold;");
						// dat.setUnderline(true);
						String[] ts = { "USA", "FRANCE", "UAE" };
						ComboBox<String> tybe = new ComboBox<>(FXCollections.observableArrayList(ts));
						tybe.setPromptText(" Choose Tybe ");
						tybe.setStyle("-fx-font-size: 15pt;-fx-border-color:PLUM;-fx-font-weight: bold;");
						TextField tnf = new TextField();
						tnf.setPrefSize(15, 50);
						TextField tpf = new TextField();
						tpf.setFont(Font.font(25));
						tpf.setStyle("-fx-font-weight: bold;");
						tpf.setDisable(true);
						tpf.setPrefSize(15, 50);

						EventHandler<ActionEvent> even12 = new EventHandler<ActionEvent>() {
							public void handle(ActionEvent e) {
								if (tybe.getValue() == "USA") {

									tpf.setText("1200");
								} else if (tybe.getValue() == "UAE") {
									tpf.setText("900");
								} else {
									tpf.setText("1300");
								}
							}
						};

						tybe.setOnAction(even12);

						grid2.setVgap(20);
						grid2.setHgap(60);
						grid2.add(ttyb, 0, 0);
						grid2.add(tybe, 0, 1);

						grid2.add(tnum, 1, 0);
						grid2.add(tnf, 1, 1);

						grid2.add(tpric, 2, 0);
						grid2.add(tpf, 2, 1);

						stak2.setPadding(new Insets(0, 175, 150, 0));
						// vbox.setPadding(new Insets(0, 100, 0, 0));
						// por.setPadding(new Insets(0,100,100,0));
						vbox.setSpacing(20);
						TextArea ta = new TextArea();
						ta.setMaxSize(600, 150);

						VBox v2 = new VBox();
						v2.setAlignment(Pos.CENTER);
						GridPane gg2 = new GridPane();

						Label amao = new Label(" Amount exclude VAT :");
						amao.setFont(Font.font(25));
						amao.setStyle("-fx-font-weight: bold;");

						TextField amof = new TextField();
						amof.setPrefHeight(50);

						Label amao1 = new Label(" VAT :");
						amao1.setFont(Font.font(25));
						amao1.setStyle("-fx-font-weight: bold;");

						TextField amof1 = new TextField();
						amof1.setPrefHeight(50);

						Label amao2 = new Label(" Amount include VAT :");
						amao2.setFont(Font.font(25));
						amao2.setStyle("-fx-font-weight: bold;");

						TextField amof2 = new TextField();
						amof2.setPrefHeight(50);
						gg2.setVgap(15);
						v2.setAlignment(Pos.TOP_LEFT);

						gg2.add(amao, 0, 0);
						gg2.add(amof, 0, 1);

						gg2.add(amao1, 0, 2);
						gg2.add(amof1, 0, 3);

						gg2.add(amao2, 0, 4);
						gg2.add(amof2, 0, 5);
						por.setPadding(new Insets(0, 0, 0, 192));
						v2.getChildren().addAll(gg2);
						v2.setPadding(new Insets(13, 0, 0, 0));
						// vbox.setPadding(new Insets(15,0,60,0));
						h12.getChildren().addAll(vbox, v2);
						por.setCenter(vbox2);
						stak2.getChildren().add(por);
						h12.setSpacing(130);
						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(10, 10, 0, 0));
						hsb13a5.setSpacing(40);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						p.setBottom(hsb13a5);

						HBox butt = new HBox();
						vbox2.getChildren().addAll(h12, butt);

						ImageView add = new ImageView("images.png");
						Button btAdd = new Button("Add", add);
						btAdd.setStyle(
								"-fx-font-size: 13pt;-fx-border-color:blue;-fx-font-weight: bold;-fx-background-color:skyblue;");
						add.setPreserveRatio(true);

						ImageView cal = new ImageView("imhhhhh.png");
						Button calc = new Button("Calculate", cal);
						calc.setStyle(
								"-fx-font-size: 13pt;-fx-border-color:black;-fx-font-weight: bold;-fx-background-color:wheat;");
						cal.setPreserveRatio(true);
						butt.setPadding(new Insets(0, 0, 10, 0));
						butt.getChildren().addAll(btAdd, calc);
						butt.setSpacing(518);
						vbox.getChildren().addAll(grid2, ta);
						amof.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						amof1.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						amof2.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						ta.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						amof.setDisable(true);
						amof1.setDisable(true);
						amof2.setDisable(true);
						tnf.textProperty().addListener(new ChangeListener<String>() {
						    @Override
						    public void changed(ObservableValue<? extends String> observable, String oldValue, 
						        String newValue) {
						        if (!newValue.matches("\\d*")) {
						        	tnf.setText(newValue.replaceAll("[^\\d]", ""));
						        }
						    }
	
						});
				
						btAdd.setOnAction(x -> {

							int sum = Integer.parseInt(tpf.getText()) * Integer.parseInt(tnf.getText());
							ss = ss + sum;
							zz += "\n" + tybe.getValue() + " : " + (sum);
							ta.setText(zz);
							tnf.clear();
						});

						calc.setOnAction(ssz -> {
							String[] s = ttg.getSelectedToggle().toString().split(" ");
							ta.clear();
							ss1 = ss * 0.16;
							amof.setText(String.valueOf(ss));
							amof1.setText(String.valueOf(ss1));
							amof2.setText(String.valueOf(ss + ss1));
							billNumber.add(new Bill(billn.getText(), datef.getText(), amof2.getText(), amof.getText(),
									amof1.getText(), s[1]));
							bi.write(billNumber.get(billNumber.size() - 1).toString() + "\n");
							datef.clear();
							billn.clear();
							bi.flush();
							ss = 0;
							ss1 = 0;
							zz = "";

						});

						primaryStage.setScene(sel);
						primaryStage.show();

					} else if (bills.getValue() == "             Purchases Bill") {
						ImageView back = new ImageView("dsBuffer.bmp.png");
						back.setPreserveRatio(true);
						Button bk = new Button("Back", back);
						bk.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon15 = new Label(
								"                                       Purchases Bill                      ");
						logon15.setFont(Font.font(35));

						bk.setOnAction(ss -> {
							primaryStage.setScene(scene1);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("LogOut", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk, logon15, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon15.setFont(Font.font(null, FontWeight.BOLD, logon15.getFont().getSize()));
						pn.setTop(hh15);

						StackPane root5 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root5.setBackground(bGround5);
						pn.setCenter(root5);

						GridPane grid = new GridPane();
						grid.setHgap(15);
						grid.setVgap(20);
						grid.setPadding(new Insets(20, 20, 20, 20));

						Label dat = new Label("Date :");
						dat.setFont(Font.font(25));
						dat.setStyle("-fx-font-weight: bold;");
						dat.setUnderline(true);

						Label numb = new Label("Bills Number :");
						numb.setFont(Font.font(25));
						numb.setStyle("-fx-font-weight: bold;");
						numb.setUnderline(true);
						grid.setAlignment(Pos.TOP_LEFT);

						TextField datef = new TextField();
						datef.setStyle("-fx-font-weight: bold;-fx-font-color: black;");
						datef.setFont(Font.font(15));

						TextField billn = new TextField();
						grid.add(dat, 0, 0);
						grid.add(datef, 1, 0);
						grid.add(numb, 0, 1);
						grid.add(billn, 1, 1);
						BorderPane sba = new BorderPane();
						root5.getChildren().add(sba);
						sba.setTop(grid);

						billn.textProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue,
									String newValue) {
								if (!newValue.matches("\\d*")) {
									billn.setText(newValue.replaceAll("[^\\d]", ""));
								}
							}

						});

						StackPane stak2 = new StackPane();
						stak2.setAlignment(Pos.CENTER);

						Rectangle r1 = new Rectangle(0, 10, 1200, 400);
						r1.setStroke(Color.BLACK);
						r1.setFill(Color.TRANSPARENT);

						Rectangle r2 = new Rectangle(820, 10, 380, 400);
						r2.setStroke(Color.BLACK);
						r2.setFill(Color.TRANSPARENT);

						Group gb = new Group();
						gb.getChildren().addAll(r1, r2);
						stak2.getChildren().addAll(gb);
						sba.setCenter(stak2);
						BorderPane por = new BorderPane();
						// sba.setAlignment(Pos.CENTER);

						GridPane grid2 = new GridPane();
						VBox vbox = new VBox();
						HBox h12 = new HBox();
						VBox vbox2 = new VBox();

						vbox.setAlignment(Pos.CENTER);
						Label ttyb = new Label("Items Tybe:");
						ttyb.setFont(Font.font(25));
						ttyb.setStyle("-fx-font-weight: bold;");
						Label tnum = new Label("  Item price :     ");
						tnum.setFont(Font.font(25));
						tnum.setStyle("-fx-font-weight: bold;");
						// dat.setUnderline(true);

						// dat.setUnderline(true);
						TextField namef = new TextField();
						namef.setStyle("-fx-font-size: 13pt;-fx-border-color:PLUM;-fx-font-weight: bold;");
						TextField tnf = new TextField();
						tnf.setStyle("-fx-font-weight: bold;");
						tnf.setPrefSize(15, 50);

						grid2.setVgap(20);
						grid2.setHgap(100);
						grid2.add(ttyb, 0, 0);
						grid2.add(namef, 0, 1);

						grid2.add(tnum, 1, 0);
						grid2.add(tnf, 1, 1);

						stak2.setPadding(new Insets(0, 175, 150, 0));
						// vbox.setPadding(new Insets(0, 100, 0, 0));
						// por.setPadding(new Insets(0,100,100,0));
						TextArea ta = new TextArea();
						ta.setMaxSize(600, 150);

						VBox v2 = new VBox();
						v2.setAlignment(Pos.CENTER);
						GridPane gg2 = new GridPane();

						Label amao = new Label(" Amount include VAT :");
						amao.setFont(Font.font(25));
						amao.setStyle("-fx-font-weight: bold;");

						TextField amof = new TextField();
						amof.setPrefHeight(50);

						Label amao1 = new Label(" VAT :");
						amao1.setFont(Font.font(25));
						amao1.setStyle("-fx-font-weight: bold;");

						TextField amof1 = new TextField();
						amof1.setPrefHeight(50);

						Label amao2 = new Label(" Amount exclude VAT :");
						amao2.setFont(Font.font(25));
						amao2.setStyle("-fx-font-weight: bold;");

						TextField amof2 = new TextField();
						amof2.setPrefHeight(50);
						gg2.setVgap(15);
						v2.setAlignment(Pos.TOP_LEFT);
						vbox.setSpacing(20);
						gg2.add(amao, 0, 0);
						gg2.add(amof, 0, 1);

						gg2.add(amao1, 0, 2);
						gg2.add(amof1, 0, 3);

						gg2.add(amao2, 0, 4);
						gg2.add(amof2, 0, 5);
						por.setPadding(new Insets(0, 0, 0, 192));
						v2.getChildren().addAll(gg2);
						v2.setPadding(new Insets(10, 0, 0, 0));
						h12.getChildren().addAll(vbox, v2);
						por.setCenter(vbox2);
						stak2.getChildren().add(por);
						h12.setSpacing(240);
						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(10, 10, 0, 0));
						hsb13a5.setSpacing(40);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						pn.setBottom(hsb13a5);

						HBox butt = new HBox();
						vbox2.getChildren().addAll(h12, butt);

						ImageView add = new ImageView("images.png");
						Button btAdd = new Button("Add", add);
						btAdd.setStyle(
								"-fx-font-size: 13pt;-fx-border-color:blue;-fx-font-weight: bold;-fx-background-color:skyblue;");
						add.setPreserveRatio(true);

						ImageView cal = new ImageView("imhhhhh.png");
						Button calc = new Button("Calculate", cal);
						calc.setStyle(
								"-fx-font-size: 13pt;-fx-border-color:black;-fx-font-weight: bold;-fx-background-color:wheat;");
						cal.setPreserveRatio(true);
						butt.setPadding(new Insets(0, 0, 20, 0));
						butt.getChildren().addAll(btAdd, calc);
						butt.setSpacing(518);
						vbox.getChildren().addAll(grid2, ta);
						amof.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						amof1.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						amof2.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						ta.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						amof.setDisable(true);
						amof1.setDisable(true);
						amof2.setDisable(true);
						btAdd.setOnAction(x -> {

							int sum = Integer.parseInt(tnf.getText());
							ss2 = ss2 + sum;
							zz2 += "\n" + namef.getText() + " : " + (sum);
							ta.setText(zz2);
							tnf.clear();
							namef.clear();
						});
					

						calc.setOnAction(ssz -> {
							
							String[] ss = ttg.getSelectedToggle().toString().split(" ");
							ss12 = ss2 * 0.16;
							amof.setText(String.valueOf(ss2));
							amof1.setText(String.valueOf(ss12));
							amof2.setText(String.valueOf(ss2-ss12));
							billNumber2.add(new Bill2(billn.getText(), datef.getText(), amof.getText(), amof2.getText(),
									amof1.getText(), ss[1]));
							bi2.write(billNumber2.get(billNumber2.size() - 1).toString() + "\n");
							namef.clear();
							datef.clear();
							billn.clear();
							ta.clear();
							bi2.flush();
							ss2 = 0;
							ss12 = 0;
							zz2 = "";

						});

						primaryStage.setScene(seln);
						primaryStage.show();

					}

					else if (bills.getValue() == "             Search Bills") {

						ImageView back1 = new ImageView("dsBuffer.bmp.png");
						back1.setPreserveRatio(true);
						Button bk1 = new Button("Back", back1);
						bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon151 = new Label(
								"                                       Bill Scearch                                      ");
						logon151.setFont(Font.font(30));

						bk1.setOnAction(ss -> {
							primaryStage.setScene(scene1);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("LogOut", limg5);
						out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setPadding(new Insets(7, 0, 0, 0));
						hh15.setMaxSize(2100, 50);
						hh15.getChildren().addAll(bk1, logon151, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
						Jornalsch11.setTop(hh15);
						BorderPane serh = new BorderPane();
						StackPane root51 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root51.setBackground(bGround5);

						GridPane ss = new GridPane();
						ss.setHgap(40);
						Label xd = new Label("Enter Bill Number");
						DropShadow shadow = new DropShadow();
						shadow.setOffsetX(2);
						shadow.setOffsetY(3);
						shadow.setColor(Color.SKYBLUE);
						xd.setEffect(shadow);
						TextField ll = new TextField();
						Button go = new Button("Go");
						ll.setStyle("-fx-control-inner-background:	#F3DEA1");
						ss.add(xd, 0, 0);
						ss.add(ll, 1, 0);
						ss.add(go, 2, 0);
						ss.setStyle("-fx-font-size: 22pt;-fx-font-weight: bold;");
						root51.getChildren().add(serh);
						serh.setTop(ss);
						ss.setAlignment(Pos.CENTER);
						// ss.setPadding(new Insets(100, 0, 0, 0));
						Jornalsch11.setCenter(root51);
						TextArea tt = new TextArea();
						serh.setCenter(tt);
						tt.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						serh.setPadding(new Insets(100, 100, 100, 100));
						String o = "";
						go.setOnAction(l -> {
							tt.clear();

							for (int k = 0; k < billNumber.size(); k++) {
								if (billNumber.get(k).getBillnum().equalsIgnoreCase(ll.getText())) {
									tt.setText(billNumber.get(k).toString2());
								}
							}

							for (int k = 0; k < billNumber2.size(); k++) {
								if (billNumber2.get(k).getBillnum().equalsIgnoreCase(ll.getText())) {
									tt.setText(billNumber2.get(k).toString2());
								}
							}

						});

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(10, 10, 0, 50));
						hsb13a5.setSpacing(40);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						Jornalsch11.setBottom(hsb13a5);

						primaryStage.setScene(Jornalschs11);
						primaryStage.show();

					}

				}

			};
			bills.setOnAction(evenb);

			String[] y2 = { "             Adjusted trail balance", "              Income Statements",
					 "                Owner's Equity Statement","              Balance Sheet"
				 };

			ComboBox combup2 = new ComboBox(FXCollections.observableArrayList(y2));
			combup2.setOnMouseClicked(r -> {
				combup2.setValue("	FINANTIAL REPORTS");

			});

			uph.getChildren().addAll(combup, inp, combup2);
			combup.getEditor().setFont(Font.font(40));
			combup2.setLayoutY(15);
			Image imm1 = new Image("lllloices-icon (1).png");

			BackgroundImage bImg1 = new BackgroundImage(imm1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround1 = new Background(bImg1);
			combup2.setBackground(bGround1);
			// combup2.getEditor().setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 40));
			combup2.getEditor().setFont(Font.font(40));
			combup2.setStyle("-fx-font-size: 15pt;-fx-border-color:PLUM;-fx-font-weight: bold;");

			combup2.setPadding(new Insets(20, 20, 20, 40));
			combup2.setPromptText("      FINANCIAL REPORTS  ");
			// combup2.getEditor().setFont(Font.font(30));

			combup2.setPrefWidth(400);

			EventHandler<ActionEvent> incev = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					if (combup2.getValue() == "              Income Statements") {
						ImageView back = new ImageView("dsBuffer.bmp.png");
						back.setPreserveRatio(true);
						Button bk = new Button("Back", back);
						bk.setStyle("-fx-font-size: 12pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon15 = new Label(
								"                                   Income Statements                                ");
						logon15.setFont(Font.font(30));

						bk.setOnAction(ss -> {
							primaryStage.setScene(scene1);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("LogOut", limg5);
						out5.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 30);
						hh15.getChildren().addAll(bk, logon15, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon15.setFont(Font.font(null, FontWeight.BOLD, logon15.getFont().getSize()));
						incst.setTop(hh15);

						StackPane root5 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root5.setBackground(bGround5);
						incst.setCenter(root5);

						BorderPane ss = new BorderPane();
						root5.getChildren().add(ss);

						ss.setPadding(new Insets(7, 20, 0, 0));

						Label compn = new Label(" AMW COMPANY ");
						compn.setStyle("-fx-font-size: 22pt;-fx-font-style:italic;-fx-font-weight: bold;");

						Label inc = new Label(" INCOME STATEMENT");
						inc.setAlignment(Pos.CENTER);
						inc.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");

						TextField fdat = new TextField();
						TextField tdat = new TextField();
						HBox dh = new HBox(3);
						dh.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						Button go = new Button("GO");
						dh.getChildren().addAll(new Label("For year"), fdat, go);
						VBox toop = new VBox(8);
						toop.setAlignment(Pos.CENTER);

						// toop.setPadding(new Insets(0,0,0,0));
						toop.getChildren().addAll(compn, inc, dh);

						ImageView prnt = new ImageView("machine.png");
						ImageView eml = new ImageView("emil.jpg");
						Button print = new Button("Print", prnt);
						Button email = new Button("Send as Email", eml);

						HBox hh = new HBox(400);
						hh.setPadding(new Insets(4, 0, 0, 30));
						hh.getChildren().addAll(email, toop, print);
						ss.setTop(hh);

						Label sal = new Label("Sales Revenue :");
						sal.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");
						sal.setUnderline(true);

						Label ex = new Label("Expenses :");
						ex.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");
						ex.setUnderline(true);

						GridPane grid = new GridPane();

						Label r = new Label("Revenue");

						Label s = new Label("Salaries and wages expense");

						Label i = new Label("Insuranse expense");

						Label d = new Label("Depreciation Expense");

						Label g = new Label("General Purchase Expense");

						Label u = new Label("Utility expense");

						Label te = new Label("Total expense");

						Label n = new Label("Net income Statment ");

					

						grid.setAlignment(Pos.CENTER);
						grid.setHgap(290);
						grid.setVgap(5);
						// grid.setPadding(new Insets(0, 10, 0, 0));
						grid.add(sal, 0, 0);
						grid.add(r, 0, 1);
						grid.add(new Label(""), 0, 2);
						grid.add(ex, 0, 3);

						grid.add(s, 0, 4);
						grid.add(i, 0, 5);
						grid.add(d, 0, 6);
						grid.add(g, 0, 7);
						grid.add(u, 0, 8);
						grid.add(te, 0, 9);
						grid.add(new Label(""), 0, 10);

						grid.add(n, 0, 11);
						
						grid.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");

						TextField t = new TextField();
						t.setDisable(true);
						TextField t1 = new TextField();
						t1.setDisable(true);
						TextField t2 = new TextField();
						t2.setDisable(true);
						TextField t3 = new TextField();
						t3.setDisable(true);
						TextField t4 = new TextField();
						t4.setDisable(true);

						TextField t5 = new TextField();
						t5.setDisable(true);
						TextField t6 = new TextField();
						t6.setDisable(true);
						TextField t7 = new TextField();
						t7.setDisable(true);
						TextField t8 = new TextField();
						TextField t9 = new TextField();
						t9.setDisable(true);

						t8.setDisable(true);
						grid.add(t, 1, 1);

						grid.add(t1, 1, 4);
						grid.add(t2, 1, 5);
						grid.add(t3, 1, 6);
						grid.add(t4, 1, 7);
						grid.add(t5, 1, 8);

						// grid.add(new TextField(), 9, 1);

						grid.add(t6, 1, 9);
						grid.add(t7, 1, 11);
						
						ss.setCenter(grid);

						go.setOnAction(j -> {

							try {

								t.setText("" + fillReports(r.getText(), fdat.getText()));

								t1.setText("" + fillReports(s.getText(), fdat.getText()));
								t2.setText("" + fillReports(i.getText(), fdat.getText()));
								t3.setText("" + fillReports(d.getText(), fdat.getText()));
								t4.setText("" + fillReports(g.getText(), fdat.getText()));
								t5.setText("" + fillReports(u.getText(), fdat.getText()));

								t6.setText(String.valueOf(Double.parseDouble(t2.getText())
										+ Double.parseDouble(t3.getText()) + Double.parseDouble(t1.getText())
										+ Double.parseDouble(t4.getText()) + Double.parseDouble(t5.getText())));
								t7.setText(String
										.valueOf(Double.parseDouble(t.getText()) - Double.parseDouble(t6.getText())));
								
								
								moath =Double.parseDouble(t7.getText()) ;

							} catch (Exception e1) {
								e1.printStackTrace();
							}

						});

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(20, 10, 0, 50));
						hsb13a5.setSpacing(150);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						incst.setBottom(hsb13a5);

						primaryStage.setScene(incsts);
						primaryStage.show();

					}

					else if (combup2.getValue() == "             Adjusted trail balance") {
						ImageView back = new ImageView("dsBuffer.bmp.png");
						back.setPreserveRatio(true);
						Button bk = new Button("Back", back);
						bk.setStyle("-fx-font-size: 12pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon15 = new Label(
								"                                Adjusted trail balance                             ");
						logon15.setFont(Font.font(30));

						bk.setOnAction(ss -> {
							primaryStage.setScene(scene1);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("LogOut", limg5);
						out5.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 30);
						hh15.getChildren().addAll(bk, logon15, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon15.setFont(Font.font(null, FontWeight.BOLD, logon15.getFont().getSize()));
						incst1.setTop(hh15);

						StackPane root5 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root5.setBackground(bGround5);
						incst1.setCenter(root5);

						BorderPane ss = new BorderPane();
						root5.getChildren().add(ss);

						ss.setPadding(new Insets(7, 20, 0, 0));

						Label compn = new Label(" AMW COMPANY ");
						compn.setStyle("-fx-font-size: 22pt;-fx-font-style:italic;-fx-font-weight: bold;");

						Label inc = new Label(" Adjusted Trail Balance");
						inc.setAlignment(Pos.CENTER);
						inc.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");

						TextField fdat = new TextField();
						TextField tdat = new TextField();
						HBox dh = new HBox(8);
						dh.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						Button go = new Button("GO");
						dh.getChildren().addAll(new Label("For year"), fdat, go);
						VBox toop = new VBox(8);
						toop.setAlignment(Pos.CENTER);

						// toop.setPadding(new Insets(0,0,0,0));
						toop.getChildren().addAll(compn, inc, dh);

						ImageView prnt = new ImageView("machine.png");
						ImageView eml = new ImageView("emil.jpg");
						Button print = new Button("Print", prnt);
						Button email = new Button("Send as Email", eml);

						HBox hh = new HBox(430);
						hh.setPadding(new Insets(4, 0, 0, 30));
						hh.getChildren().addAll(email, toop, print);
						ss.setTop(hh);

						Label sal = new Label("       Debit");
						sal.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");

						Label sal1 = new Label("      Credit");
						sal1.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");

						Label sal2 = new Label("      Debit");
						sal2.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");

						Label sal12 = new Label("     Credit");
						sal12.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");

						GridPane grid = new GridPane();

						grid.setHgap(30);
						grid.setVgap(2);
						grid.setPadding(new Insets(0, 0, 0, 30));
						grid.add(sal, 1, 0);
						grid.add(sal1, 2, 0);

						Label csh = new Label("Cash");
						Label ar = new Label("Account receivable");
						Label s = new Label("Supplies");

						Label pi = new Label("prepaid insurance");
						Label m = new Label("Machinery");
						Label f = new Label("Furniture");
						Label v = new Label("Vehicles");
						Label b = new Label("Buildings");
						/////////// debit

						Label ad = new Label("Accumulated Depreciation");

						Label ap = new Label("Account payable");
						Label us = new Label("Unearned Service Revenue");
						Label sa = new Label("Salaries and wages Payable");

						Label it = new Label("Interest payable");
						Label va = new Label("Value added tax provision");
						Label lt = new Label("Long Term Debt");
						Label ca = new Label("Capital");
						Label re = new Label("Retained earnings");

						Label cy = new Label("Current year earning");
						Label dr = new Label("Drawings");
						/// credit

						Label saw = new Label("Salaries and wages expense");
						Label ie = new Label("Insuranse expense");
						Label de = new Label("Depreciation Expense");
						Label gp = new Label("General Purchase Expense");
						Label ve = new Label("Utility expense");
						Label r = new Label("Revenue");

						grid.add(csh, 0, 1);
						grid.add(ar, 0, 2);
						grid.add(s, 0, 3);

						grid.add(pi, 0, 4);
						grid.add(m, 0, 5);
						grid.add(f, 0, 6);
						grid.add(v, 0, 7);
						grid.add(b, 0, 8);
						/////////// debit

						grid.add(ad, 0, 9);

						grid.add(ap, 0, 10);
						grid.add(us, 0, 11);
						grid.add(sa, 0, 12);

						grid.add(sal2, 4, 0);
						grid.add(sal12, 5, 0);

						grid.add(it, 0, 13);
						grid.add(va, 3, 1);
						grid.add(lt, 3, 2);
						grid.add(ca, 3, 3);
						
						grid.add(dr, 3, 4);
						/// credit

						grid.add(saw, 3, 5);
						grid.add(ie, 3, 6);
						grid.add(de, 3, 7);
						grid.add(gp, 3, 8);
						grid.add(ve, 3, 9);
						grid.add(r, 3, 10);

						grid.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");

						TextField t = new TextField();
						t.setDisable(true);
						TextField t1 = new TextField();
						t1.setDisable(true);
						TextField t2 = new TextField();
						t2.setDisable(true);
						TextField t3 = new TextField();
						t3.setDisable(true);
						TextField t4 = new TextField();
						t4.setDisable(true);

						TextField t5 = new TextField();
						t5.setDisable(true);
						TextField t6 = new TextField();
						t6.setDisable(true);
						TextField t7 = new TextField();
						t7.setDisable(true);
						TextField t8 = new TextField();
						t8.setDisable(true);

						TextField t9 = new TextField();
						t9.setDisable(true);

						TextField t11 = new TextField();
						t11.setDisable(true);
						TextField t12 = new TextField();
						t12.setDisable(true);
						TextField t13 = new TextField();
						t13.setDisable(true);
						TextField t14 = new TextField();
						t14.setDisable(true);
						TextField t15 = new TextField();
						t15.setDisable(true);
						TextField t16 = new TextField();
						t16.setDisable(true);
						TextField t17 = new TextField();
						t17.setDisable(true);
						TextField t18 = new TextField();
						t18.setDisable(true);
						TextField t19 = new TextField();
						t19.setDisable(true);
						TextField t20 = new TextField();
						t20.setDisable(true);
						TextField t21 = new TextField();
						t21.setDisable(true);
						TextField t0 = new TextField();
						t0.setDisable(true);
						TextField t00 = new TextField();
						t00.setDisable(true);
						TextField t211 = new TextField();
						t211.setDisable(true);
						TextField t212 = new TextField();
						t212.setDisable(true);
						TextField c = new TextField();
						c.setDisable(true);
						TextField d = new TextField();
						d.setDisable(true);

						grid.add(t, 1, 1);
						grid.add(t0, 1, 2);
						grid.add(t00, 1, 3);
						grid.add(t1, 1, 4);
						grid.add(t2, 1, 5);
						grid.add(t3, 1, 6);
						grid.add(t4, 1, 7);
						grid.add(t5, 1, 8);

						// grid.add(new TextField(), 9, 1);

						grid.add(t6, 2, 9);
						grid.add(t7, 2, 10);
						grid.add(t8, 2, 11);
						grid.add(t9, 2, 12);
						grid.add(t212, 2, 13);

						grid.add(t11, 5, 1);
						grid.add(t12, 5, 2);
						grid.add(t13, 5, 3);
						

						grid.add(t16, 4, 4);
						grid.add(t17, 4, 5);

						grid.add(t18, 4, 6);
						grid.add(t19, 4, 7);
						grid.add(t20, 4, 8);
						grid.add(t21, 4, 9);
						grid.add(t211, 5, 10);
						go.setOnAction(p -> {
							try {
								t.setText("" + fillReports(csh.getText(), fdat.getText()));
								t0.setText("" + fillReports(ar.getText(), fdat.getText()));
								
								
								t00.setText("" + fillReports(s.getText(), fdat.getText()));
								t1.setText("" + fillReports(pi.getText(), fdat.getText()));
								t2.setText("" + fillReports(m.getText(), fdat.getText()));
								t3.setText("" + fillReports(f.getText(), fdat.getText()));
								t4.setText("" + fillReports(v.getText(), fdat.getText()));
								t5.setText("" + fillReports(b.getText(), fdat.getText()));
								t6.setText("" + fillReports(ad.getText(), fdat.getText()));
								t7.setText("" + fillReports(ap.getText(), fdat.getText()));
								t8.setText("" + fillReports(us.getText(), fdat.getText()));
								t9.setText("" + fillReports(sa.getText(), fdat.getText()));
								t212.setText("" + fillReports(it.getText(), fdat.getText()));
								t11.setText("" + fillReports(va.getText(), fdat.getText()));
								t12.setText("" + fillReports(lt.getText(), fdat.getText()));
								tayseer=fillReports(ca.getText(), fdat.getText());
								t13.setText("" +tayseer );
								t14.setText("" + fillReports(re.getText(), fdat.getText()));
								t15.setText("" + fillReports(cy.getText(), fdat.getText()));
								t16.setText("" + fillReports(dr.getText(), fdat.getText()));
								t17.setText("" + fillReports(saw.getText(), fdat.getText()));
								t18.setText("" + fillReports(ie.getText(), fdat.getText()));
								t19.setText("" + fillReports(de.getText(), fdat.getText()));
								t20.setText("" + fillReports(gp.getText(), fdat.getText()));
								t21.setText("" + fillReports(ve.getText(), fdat.getText()));
								t211.setText("" + fillReports(r.getText(), fdat.getText()));
								c.setText(String.valueOf(Double.parseDouble(t.getText())
										+ Double.parseDouble(t0.getText()) + Double.parseDouble(t00.getText())
										+ Double.parseDouble(t1.getText()) + Double.parseDouble(t2.getText())
										+ Double.parseDouble(t3.getText()) + Double.parseDouble(t4.getText())
										+ Double.parseDouble(t5.getText()) + Double.parseDouble(t18.getText())
										+ Double.parseDouble(t19.getText()) + Double.parseDouble(t20.getText())
										+ Double.parseDouble(t21.getText()) 
										+ Double.parseDouble(t16.getText())+ Double.parseDouble(t17.getText()) ));

								d.setText(String.valueOf(Double.parseDouble(t6.getText())
										+ Double.parseDouble(t7.getText()) + Double.parseDouble(t8.getText())
										+ Double.parseDouble(t9.getText()) + Double.parseDouble(t212.getText())
										+ Double.parseDouble(t11.getText()) + Double.parseDouble(t12.getText())
										+ Double.parseDouble(t13.getText()) + Double.parseDouble(t14.getText())
										+ Double.parseDouble(t15.getText())+ Double.parseDouble(t211.getText()) ));

							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						});

						HBox eq = new HBox(10);
						eq.setPadding(new Insets(8, 0, 0, 0));
						eq.getChildren().addAll(c, new Label(" = "));

						grid.add(eq, 4, 12);
						///// c dibit
						grid.add(d, 5, 12);
						////////////// d cridit
						ss.setCenter(grid);

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(20, 10, 0, 50));
						hsb13a5.setSpacing(150);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						incst1.setBottom(hsb13a5);

						primaryStage.setScene(incsts1);
						primaryStage.show();
					}

					else if (combup2.getValue() == "                Owner's Equity Statement") {
						ImageView back = new ImageView("dsBuffer.bmp.png");
						back.setPreserveRatio(true);
						Button bk = new Button("Back", back);
						bk.setStyle("-fx-font-size: 12pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon15 = new Label(
								"                               Owner's Equity Statement                        ");
						logon15.setFont(Font.font(30));

						bk.setOnAction(ss -> {
							primaryStage.setScene(scene1);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("LogOut", limg5);
						out5.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 30);
						hh15.getChildren().addAll(bk, logon15, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon15.setFont(Font.font(null, FontWeight.BOLD, logon15.getFont().getSize()));
						incstret.setTop(hh15);

						StackPane root5 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root5.setBackground(bGround5);
						incstret.setCenter(root5);

						BorderPane ss = new BorderPane();
						root5.getChildren().add(ss);

						ss.setPadding(new Insets(7, 20, 0, 0));

						Label compn = new Label(" AMW COMPANY ");
						compn.setStyle("-fx-font-size: 22pt;-fx-font-style:italic;-fx-font-weight: bold;");

						Label inc = new Label(" Owner's Equity Statement ");
						inc.setAlignment(Pos.CENTER);
						inc.setStyle("-fx-font-size: 16pt;-fx-font-weight: bold;");

						TextField fdat = new TextField();
						TextField tdat = new TextField();
						HBox dh = new HBox(3);
						dh.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						Button go = new Button("GO");
						dh.getChildren().addAll(new Label("For year"), fdat, go);
						VBox toop = new VBox(8);
						toop.setAlignment(Pos.CENTER);

						// toop.setPadding(new Insets(0,0,0,0));
						toop.getChildren().addAll(compn, inc, dh);

						ImageView prnt = new ImageView("machine.png");
						ImageView eml = new ImageView("emil.jpg");
						Button print = new Button("Print", prnt);
						Button email = new Button("Send as Email", eml);

						HBox hh = new HBox(400);
						hh.setPadding(new Insets(4, 0, 0, 30));
						hh.getChildren().addAll(email, toop, print);
						ss.setTop(hh);

						GridPane grid = new GridPane();

						grid.setAlignment(Pos.CENTER);
						grid.setHgap(250);
						grid.setVgap(15);

						Label br = new Label("Beginning Owner's Capital");
						Label ai = new Label("Add : Investment");
						Label an = new Label("Add : Net income");
						Label ld = new Label("Less : Drawing");
						Label er = new Label("Ending Owner's Capital");

						grid.add(br, 0, 0);
						grid.add(ai, 0, 1);
						grid.add(an, 0, 2);
						grid.add(ld, 0, 3);
						grid.add(er, 0, 4);

						grid.setStyle("-fx-font-size: 18pt;-fx-font-weight: bold;");

						TextField t = new TextField();
						t.setDisable(true);
						TextField t1 = new TextField();
						t1.setDisable(true);
						TextField t2 = new TextField();
						t2.setDisable(true);
						TextField t3 = new TextField();
						t3.setDisable(true);
						TextField t4 = new TextField();
						t3.setDisable(true);
						t4.setDisable(true);
						grid.add(t, 1, 0);
						grid.add(t1, 1, 1);
						grid.add(t2, 1, 2);
						grid.add(t3, 1, 3);
						grid.add(t4, 1, 4);
						go.setOnAction(p -> {
							try {
								t.setText("" + fillReports(br.getText(), fdat.getText()));
								t1.setText("" +tayseer);
								t2.setText(""+moath);
								t3.setText("" + fillReports("Drawings", fdat.getText()));
								weam= Double.parseDouble(t.getText())+Double.parseDouble(t1.getText()) +Double.parseDouble(t2.getText())-Double.parseDouble(t3.getText());
								t4.setText(""+weam);
                                  ahmad+= Double.parseDouble(t4.getText());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						});

						ss.setCenter(grid);

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(20, 10, 0, 50));
						hsb13a5.setSpacing(150);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						incstret.setBottom(hsb13a5);

						primaryStage.setScene(incstsret);
						primaryStage.show();

					} else if (combup2.getValue() == "              Balance Sheet") {
						ImageView back = new ImageView("dsBuffer.bmp.png");
						back.setPreserveRatio(true);
						Button bk = new Button("Back", back);
						bk.setStyle("-fx-font-size: 12pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
						Label logon15 = new Label(
								"                                        Balance Sheet                                     ");
						logon15.setFont(Font.font(30));

						bk.setOnAction(ss -> {
							primaryStage.setScene(scene1);
							primaryStage.show();
						});
						ImageView limg5 = new ImageView("Logout-icon.png");

						Button out5 = new Button("LogOut", limg5);
						out5.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
						out5.setOnAction(t -> {
							primaryStage.setScene(scene);
							primaryStage.setMaximized(true);
							primaryStage.show();
						});

						HBox hh15 = new HBox();

						hh15.setSpacing(228);
						hh15.setMaxSize(2100, 30);
						hh15.getChildren().addAll(bk, logon15, out5);
						hh15.setBackground(
								new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
						logon15.setFont(Font.font(null, FontWeight.BOLD, logon15.getFont().getSize()));
						incstbal.setTop(hh15);

						StackPane root5 = new StackPane();

						Image img5 = new Image("PM316.jpg");
						BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
						Background bGround5 = new Background(bImg5);
						root5.setBackground(bGround5);
						incstbal.setCenter(root5);

						BorderPane ss = new BorderPane();
						root5.getChildren().add(ss);

						ss.setPadding(new Insets(5, 20, 0, 0));

						Label compn = new Label(" AMW COMPANY ");
						compn.setStyle("-fx-font-size: 18pt;-fx-font-style:italic;-fx-font-weight: bold;");

						Label inc = new Label(" Balance Sheet");
						inc.setAlignment(Pos.CENTER);
						inc.setStyle("-fx-font-size: 14pt;-fx-font-weight: bold;");

						TextField fdat = new TextField();
						TextField tdat = new TextField();
						HBox dh = new HBox(3);
						Button go = new Button("GO");
						dh.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
						dh.getChildren().addAll(new Label("For year"), fdat, go);
						VBox toop = new VBox(8);
						toop.setAlignment(Pos.CENTER);

						// toop.setPadding(new Insets(0,0,0,0));
						toop.getChildren().addAll(compn, inc, dh);

						ImageView prnt = new ImageView("machine.png");
						ImageView eml = new ImageView("emil.jpg");
						Button print = new Button("Print", prnt);
						Button email = new Button("Send as Email", eml);

						HBox hh = new HBox(400);
						hh.setPadding(new Insets(4, 0, 0, 30));
						hh.getChildren().addAll(email, toop, print);
						ss.setTop(hh);

						Label sal = new Label("Assets");
						sal.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						sal.setUnderline(true);

						Label sal1 = new Label("Liabilities");
						sal1.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						sal1.setUnderline(true);

						Label sal12 = new Label("Owner's equity ");
						sal12.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						sal12.setUnderline(true);

						GridPane grid = new GridPane();
						grid.setAlignment(Pos.CENTER);
						grid.setHgap(90);
						grid.setVgap(5);
						grid.setPadding(new Insets(0, 10, 10, 30));
						grid.add(sal, 0, 0);

						Label ca = new Label("Current assets ");
						Label csh = new Label("Cash");
						Label ar = new Label("Account receivable");
						Label su = new Label("Supplies");

						Label pi = new Label("prepaid insurance");
						Label tc = new Label("Total current assets");
						Label fa = new Label("Fixed assets");////////////////////////////
						fa.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
						Label m = new Label("Machinery");

						Label fu = new Label("Furniture");

						Label v = new Label("Vehicles");
						Label b = new Label("Buildings");
						//Label ad = new Label("Depreciation Expense");
						Label tf = new Label("Total fixed assets");
						Label la = new Label("Less : Accumulated depreciation");
						Label nf = new Label("Net fixed assets");
						Label ta = new Label("Total assets");

						Label cl = new Label("Current liabilities");

						Label ap = new Label("Account payble");
						Label us = new Label("Unearned Service Revenue");
						/// credit

						Label rp = new Label("Interest payable");
						Label va = new Label("Value added tax provision");
						Label tcl = new Label("Total current liabilities");
						Label lt = new Label("Long tearm debt");
						Label tl = new Label("Total liabilities");
						Label saw = new Label("Salaries and wages Payable");
						Label tla = new Label("Total liabities and owner equity");
						Label oc = new Label("Owner's Capital");
						grid.add(ca, 0, 1);
						grid.add(csh, 0, 2);
						grid.add(ar, 0, 3);
						grid.add(su, 0, 4);

						grid.add(pi, 0, 5);
						grid.add(tc, 0, 6);
						grid.add(new Label(""), 0, 7);
						grid.add(fa, 0, 8);
						fa.setUnderline(true);
						grid.add(m, 0, 9);

						grid.add(fu, 0, 10);

						grid.add(v, 0, 11);
						grid.add(b, 0, 12);
						grid.add(new Label(""), 0, 13);

						grid.add(tf, 0, 14);
						grid.add(nf, 0, 16);
						grid.add(la, 0, 15);
						grid.add(ta, 0, 17);

						grid.add(sal1, 3, 0);
						grid.add(cl, 3, 1);

						grid.add(ap, 3, 2);
						grid.add(us, 3, 3);
						/// credit
						grid.add(saw, 3, 4);
						grid.add(rp, 3, 5);
						grid.add(va, 3, 6);
						grid.add(tcl, 3, 7);
						grid.add(new Label(""), 3, 8);
						grid.add(lt, 3, 9);
						grid.add(tl, 3, 10);
						grid.add(new Label(""), 3, 11);
						grid.add(sal12, 3, 12);
						grid.add(oc, 3, 13);
						
						grid.add(tla, 3, 15);

						grid.setStyle("-fx-font-size: 9pt;-fx-font-weight: bold;");

						TextField t = new TextField();
						t.setDisable(true);
						TextField t1 = new TextField();
						t1.setDisable(true);
						TextField t2 = new TextField();
						t2.setDisable(true);
						TextField t3 = new TextField();
						t3.setDisable(true);
						TextField t4 = new TextField();
						t4.setDisable(true);

						TextField t5 = new TextField();
						t5.setDisable(true);
						TextField t6 = new TextField();
						t6.setDisable(true);
						TextField t7 = new TextField();
						t7.setDisable(true);
						TextField t8 = new TextField();
						t8.setDisable(true);

						TextField t9 = new TextField();
						t9.setDisable(true);

						TextField t11 = new TextField();
						t11.setDisable(true);
						TextField t12 = new TextField();
						t12.setDisable(true);
						TextField t13 = new TextField();
						t13.setDisable(true);
						TextField t14 = new TextField();
						t14.setDisable(true);
						TextField t15 = new TextField();
						t15.setDisable(true);
						TextField t16 = new TextField();
						t16.setDisable(true);
						TextField t17 = new TextField();
						t17.setDisable(true);
						TextField t18 = new TextField();
						t18.setDisable(true);
						TextField t19 = new TextField();
						t19.setDisable(true);
						TextField t20 = new TextField();
						t20.setDisable(true);
						TextField t21 = new TextField();
						t21.setDisable(true);
						TextField t0 = new TextField();
						t0.setDisable(true);
						TextField t00 = new TextField();
						t00.setDisable(true);
						TextField t211 = new TextField();
						t211.setDisable(true);
						TextField t212 = new TextField();
						t212.setDisable(true);
						TextField c = new TextField();
						c.setDisable(true);
						TextField d = new TextField();
						d.setDisable(true);

						TextField sawf = new TextField();
						sawf.setDisable(true);
						TextField tx = new TextField();
						tx.setDisable(true);
						TextField t1x = new TextField();
						t1x.setDisable(true);
						TextField t2x = new TextField();
						t2x.setDisable(true);
						TextField t3x = new TextField();
						t3x.setDisable(true);
						TextField t4x = new TextField();
						t4x.setDisable(true);

						grid.add(new Label(""), 1, 1);
						grid.add(t0, 1, 2);
						grid.add(t00, 1, 3);
						grid.add(t1, 1, 4);
						grid.add(t2, 1, 5);
						grid.add(t3, 1, 6);
						// grid.add(t4, 1, 7);
						grid.add(new Label(""), 1, 8);

						// grid.add(new TextField(), 9, 1);

						grid.add(t6, 1, 9);
						grid.add(t7, 1, 10);
						grid.add(t8, 1, 11);
						grid.add(t9, 1, 12);
						// grid.add(t212, 1, 13);

						grid.add(t11, 1, 14);
						grid.add(t12, 1, 15);
						grid.add(t13, 1, 16);
						grid.add(t14, 1, 17);

						grid.add(t15, 1, 5);

						grid.add(new Label(""), 4, 1);
						grid.add(t4x, 4, 2);
						grid.add(t4, 4, 3);
						
						grid.add(sawf, 4, 4);
						grid.add(t2x, 4, 5);
						grid.add(t212, 4, 6);
						grid.add(t16, 4, 7);
                       
						grid.add(t18, 4, 9);
						grid.add(t19, 4, 10);

						grid.add(new Label(""), 4, 11);
						 grid.add(t17, 4,13);
						grid.add(t3x, 4, 15);

						ss.setCenter(grid);

						go.setOnAction(q -> {
							try {

								t0.setText("" + fillReports(csh.getText(), fdat.getText()));
								t00.setText("" + fillReports(ar.getText(), fdat.getText()));
								t1.setText("" + fillReports(su.getText(), fdat.getText()));
								t2.setText("" + fillReports(pi.getText(), fdat.getText()));
								sawf.setText("" + fillReports(saw.getText(), fdat.getText()));
								t3.setText(String

										.valueOf(Double.parseDouble(t0.getText()) + Double.parseDouble(t00.getText())
												+ Double.parseDouble(t1.getText()) + Double.parseDouble(t2.getText())));
								t6.setText("" + fillReports(m.getText(), fdat.getText()));
								t7.setText("" + fillReports(fu.getText(), fdat.getText()));
								t8.setText("" + fillReports(v.getText(), fdat.getText()));
								t9.setText("" + fillReports(b.getText(), fdat.getText()));
								
                                 d.setText(""+moath);
								t11.setText(String
										.valueOf(Double.parseDouble(t6.getText()) + Double.parseDouble(t7.getText())
												+ Double.parseDouble(t8.getText()) + Double.parseDouble(t9.getText())));

                                ridi+=fillReports("Accumulated depreciation", fdat.getText());
								
								t12.setText("" + ridi);
								t13.setText(String.valueOf(
										Double.parseDouble(t11.getText()) - Double.parseDouble(t12.getText())));

								t14.setText(String
										.valueOf(Double.parseDouble(t3.getText()) + Double.parseDouble(t13.getText())));
								/////////////////////////////////////////////////////////////
								
								

								t4x.setText("" + fillReports(ap.getText(), fdat.getText()));
								t4.setText("" + fillReports(us.getText(), fdat.getText()));
								t2x.setText("" + fillReports(rp.getText(), fdat.getText()));
								t212.setText("" + fillReports(va.getText(), fdat.getText()));
								t16.setText(String.valueOf(Double.parseDouble(t4x.getText())
										+ Double.parseDouble(t4.getText()) + Double.parseDouble(t2x.getText())+ Double.parseDouble(sawf.getText())
										+ Double.parseDouble(t212.getText())));

								t18.setText("" + fillReports(lt.getText(), fdat.getText()));
								t19.setText(String.valueOf(
										Double.parseDouble(t16.getText()) + Double.parseDouble(t18.getText())));

							t17.setText(""+weam);
																
							t3x.setText(String.valueOf(Double.parseDouble(t17.getText()) + Double.parseDouble(t19.getText())));

							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						});

						ImageView iamg13a5 = new ImageView("F1.jpg");
						HBox hsb13a5 = new HBox();
						hsb13a5.setPrefHeight(60);
						hsb13a5.setPadding(new Insets(20, 10, 0, 50));
						hsb13a5.setSpacing(150);
						hsb13a5.setBackground(
								new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						hsb13a5.getChildren().addAll(iamg13a5);
						incstbal.setBottom(hsb13a5);

						primaryStage.setScene(incstsbal);
						primaryStage.show();
					}

				}
			};

			combup2.setOnAction(incev);

			// Label errorMessage = new Label("Error in Data Please Try Again");

			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static boolean checkLogIn(File userName, String fromField, String pass) throws Exception {
		try {
			Scanner fileScan = new Scanner(userName);
			while (fileScan.hasNextLine()) {
				String reader = fileScan.nextLine();
				String[] v = reader.split(":");
				if (v[0].equalsIgnoreCase(fromField) && v[1].equalsIgnoreCase(pass)) {
					return true;
				}

			}
		} catch (Exception e) {
			throw new Exception("File not Found");
		}

		return false;
	}

	public static void creatAccounts() {
		int counter = 0;
		int account_number = 111;
		String[] arr = { "Cash", "Account Recevible", "Supplies", "Prepaid Insurance", "Machinery", "Furniture",
				"Vehicles", "Building", "Account Payable", "Unearned Service Revenue", "Salaries and wages Payable",
				"Interest Payable", "Value Added Tax Provision", "Long Term Debt", "Capital", "Retained Earnings",
				"Current Year Earning", "Drawings", "Revenue", "Salaries and wages expense", "Insuranse expense",
				"Depreciation Expense", "General Purchase Expense", "Utility expense" };
		for (int i = 0; i < 8; i++) {
			account.add(new Accounts(account_number, arr[i], 0, ""));
			account_number++;
			if (account_number == 115) {
				account_number = 121;
			}
		}
		account_number = 211;
		for (int i = 8; i < 14; i++) {
			account.add(new Accounts(account_number, arr[i], 0, ""));
			account_number++;
			if (account_number == 215) {
				account_number = 220;
			} else if (account_number == 221) {
				account_number = 230;
			}
		}
		account_number = 310;
		for (int i = 14; i < 18; i++) {
			account.add(new Accounts(account_number, arr[i], 0, ""));
			account_number += 10;
		}
		account_number = 410;
		account.add(new Accounts(account_number, arr[18], 0, ""));

		account_number = 510;
		for (int i = 19; i < 24; i++) {
			account.add(new Accounts(account_number, arr[i], 0, ""));
			account_number += 10;
		}
		account.add(new Accounts(1000, "Accumulated Depreciation", 0, ""));
		account.add(new Accounts(2000, "Income Summary", 0, ""));
	}

	public static void refreshAccounts() {
		try {
			Scanner in = new Scanner(refresh);
			while (in.hasNextLine()) {
				String[] s = in.nextLine().split(",");
				if (s[1].startsWith("-")) {
					for (Accounts c : account) {
						if (c.getAccountNumber() == Long.parseLong(s[0])) {
							c.setAmount(c.getAmount() - Double.parseDouble(s[1].substring(1, s[1].length())));
						}
					}
				} else {
					for (Accounts c : account) {
						if (c.getAccountNumber() == Long.parseLong(s[0])) {
							c.setAmount(c.getAmount() + Double.parseDouble(s[1].substring(1, s[1].length())));
						}
					}
				}
			}

		} catch (Exception e) {

		}
	}

	public static void fillSanads() {
		File f = Inputs.sanadsFile;
		try {
			Scanner in = new Scanner(f);
			while (in.hasNextLine()) {
				String[] s = in.nextLine().split(",");
				if (s[0].equalsIgnoreCase("Kabd")) {
					input.add(new SanadKabd(Long.parseLong(s[1]), s[3], Long.parseLong(s[4]), s[2],
							new Accounts(Long.parseLong(s[5]), s[6], Double.parseDouble(s[7]), "")));
				} else if (s[0].equalsIgnoreCase("Sarf")) {
					input.add(new SanadSarf(Long.parseLong(s[1]), s[3], Long.parseLong(s[4]), s[2],
							new Accounts(Long.parseLong(s[5]), s[6], Double.parseDouble(s[7]), "")));
				} else if (s[0].equalsIgnoreCase("Kayd")) {
					input.add(new SanadKayd(Long.parseLong(s[1]), s[3], s[2],
							new Accounts(Long.parseLong(s[5]), s[6], Double.parseDouble(s[7]), "")));
				}
			}
		} catch (Exception e) {

		}
	}

	public static double fillReports(String accName, String year) throws Exception {
		Scanner in = new Scanner(refresh);
		double ret = 0;
		while (in.hasNextLine()) {
			String[] ok = in.nextLine().split(",");

			String[] comp = ok[4].split("/");

			Accounts c = new Accounts();

			if (ok[3].equalsIgnoreCase(accName)) {

				int comp1 = Integer.parseInt(year);

				try {
					if (comp1 == Integer.parseInt(comp[2].trim())) {
						if (ok[2].startsWith("-")) {
							c.setAmount(c.getAmount() - Double.parseDouble(ok[2].replaceAll("-", "")));
							ret -= Double.parseDouble(ok[2].replaceAll("-", ""));

						} else if (ok[2].startsWith("+")) {
							c.setAmount(c.getAmount() + Double.parseDouble(ok[2].replaceAll("[+]", "")));
							ret += Double.parseDouble(ok[2].replaceAll("[+]", ""));
						}
					}
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Check database and enter date");
				}
			}

		}
		return ret;
	}

	public static Scene kayd(String sanadnum, Stage primaryStage) throws Exception {
		BorderPane px1a = new BorderPane();
		Scene kayds = new Scene(px1a, 1540, 800);
		ImageView back1 = new ImageView("dsBuffer.bmp.png");
		back1.setPreserveRatio(true);
		Button bk1 = new Button("رجوع", back1);
		bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
		Label logon151 = new Label("                                        سند قيد                            ");
		logon151.setFont(Font.font(35));

		bk1.setOnAction(ss -> {

			primaryStage.setScene(scene1A);
			primaryStage.show();
		});
		ImageView limg5 = new ImageView("Logout-icon.png");

		Button out5 = new Button("تسجيل الخروج", limg5);
		out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
		out5.setOnAction(t -> {
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		});

		HBox hh15 = new HBox();

		hh15.setSpacing(228);
		hh15.setMaxSize(2100, 50);
		hh15.getChildren().addAll(bk1, logon151, out5);
		hh15.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
		px1a.setTop(hh15);

		StackPane root51 = new StackPane();

		Image img5 = new Image("PM316.jpg");
		BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background bGround5 = new Background(bImg5);
		root51.setBackground(bGround5);
		px1a.setCenter(root51);
		String[] cointxt = { "شيقل", "دولار", "دينار" };
		ComboBox coinCombo = new ComboBox(FXCollections.observableArrayList(cointxt));
		coinCombo.setStyle("-fx-font-size: 10pt;-fx-font-weight: bold;");
		coinCombo.setPromptText("اختر العمله");

		TextField status = new TextField();
		status.setPrefColumnCount(6);
		status.setText("جديد");
		status.setDisable(true);

		Rectangle ttop = new Rectangle(400, 40, 1100, 150);
		ttop.setStroke(Color.BLACK);
		ttop.setFill(Color.TRANSPARENT);
		StackPane santop = new StackPane();
		santop.setPadding(new Insets(33, 160, 0, 0));

		BorderPane sanadKabdPane = new BorderPane();
		HBox hAll = new HBox(50);
		hAll.setAlignment(Pos.CENTER);
		hAll.setPadding(new Insets(25, 10, 10, 0));
		GridPane g1 = new GridPane();
		g1.setHgap(15);
		g1.setVgap(20);
		HBox h2 = new HBox(7.5);
		HBox h3 = new HBox(15);
		TextField sanadKabdNumber = new TextField();
		sanadKabdNumber.setText(sanadnum);
		TextField sanadKabdDate = new TextField();
		h2.getChildren().addAll(new Label("Voucher Date : "), sanadKabdDate);
		g1.add(new Label("Voucher Number : "), 0, 0);
		g1.add(sanadKabdNumber, 1, 0);
		g1.add(new Label("Coin"), 0, 1);
		g1.add(coinCombo, 1, 1);

		h3.getChildren().addAll(new Label(" Status "), status);
		g1.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
		h3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
		hAll.getChildren().addAll(g1, h2, h3);
		h2.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");

		sanadKabdPane.setTop(santop);
		santop.getChildren().addAll(ttop, hAll);
		/// Top ^
		TextField accNumber = new TextField();
		TextField Name = new TextField();
		TextField Cost = new TextField();

		TextField note = new TextField();

		TextField accNumber1 = new TextField();
		TextField Name1 = new TextField();
		TextField Cost1 = new TextField();
		TextField note1 = new TextField();

		TextField accNumber2 = new TextField();
		TextField Name2 = new TextField();
		TextField Cost2 = new TextField();
		TextField note2 = new TextField();

		TextField accNumber22 = new TextField();
		TextField Name22 = new TextField();
		TextField Cost22 = new TextField();
		TextField note22 = new TextField();
		
		
		
		accNumber.setOnKeyReleased(d -> {
			boolean flag = false;
			String[] ok = null;

			for (int i = 0; i < labelList.size(); i++) {
				String s = labelList.get(i).getText();
				s = s.replaceAll("[()]", "");
				ok = s.split(" ");
				if (ok[0].equalsIgnoreCase(accNumber.getText())) {

					flag = true;
					break;
				}
			}
			if (flag) {
				switch(ok.length) {
				case 2:Name.setText(ok[1]); break;
				case 3:Name.setText(ok[1]+" "+ok[2]);break;
				case 4:Name.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
				case 5:Name.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
				}
			}
		});

		accNumber1.setOnKeyReleased(d -> {
			boolean flag = false;
			String[] ok = null;

			for (int i = 0; i < labelList.size(); i++) {
				String s = labelList.get(i).getText();
				s = s.replaceAll("[()]", "");
				ok = s.split(" ");
				if (ok[0].equalsIgnoreCase(accNumber1.getText())) {

					flag = true;
					break;
				}
			}
			if (flag) {
				switch(ok.length) {
				case 2:Name1.setText(ok[1]); break;
				case 3:Name1.setText(ok[1]+" "+ok[2]);break;
				case 4:Name1.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
				case 5:Name1.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
				}
			}
		});

		accNumber2.setOnKeyReleased(d -> {
			boolean flag = false;
			String[] ok = null;

			for (int i = 0; i < labelList.size(); i++) {
				String s = labelList.get(i).getText();
				s = s.replaceAll("[()]", "");
				ok = s.split(" ");
				if (ok[0].equalsIgnoreCase(accNumber2.getText())) {

					flag = true;
					break;
				}
			}
			if (flag) {
				switch(ok.length) {
				case 2:Name2.setText(ok[1]); break;
				case 3:Name2.setText(ok[1]+" "+ok[2]);break;
				case 4:Name2.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
				case 5:Name2.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
				}
			}
		});

		accNumber22.setOnKeyReleased(d -> {
			boolean flag = false;
			String[] ok = null;

			for (int i = 0; i < labelList.size(); i++) {
				String s = labelList.get(i).getText();
				s = s.replaceAll("[()]", "");
				ok = s.split(" ");
				if (ok[0].equalsIgnoreCase(accNumber22.getText())) {

					flag = true;
					break;
				}
			}
			if (flag) {
				switch(ok.length) {
				case 2:Name22.setText(ok[1]); break;
				case 3:Name22.setText(ok[1]+" "+ok[2]);break;
				case 4:Name22.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
				case 5:Name22.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
				}
			}
		});

		Rectangle tcent = new Rectangle(400, 130, 1100, 430);
		tcent.setStroke(Color.BLACK);
		tcent.setFill(Color.TRANSPARENT);
		StackPane stcent = new StackPane();
		stcent.setPadding(new Insets(0, 160, 0, 0));
		GridPane g2 = new GridPane();
		g2.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
		g2.setPadding(new Insets(30, 0, 0, 0));
		g2.setHgap(45);
		g2.setVgap(10);
		Label mm3 = new Label("Credit :");
		mm3.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
		mm3.setUnderline(true);
		g2.add(mm3, 0, 0);
		g2.add(new Label("Account Number"), 0, 1);

		g2.add(new Label("Account Name"), 1, 1);

		g2.add(new Label("Amount"), 2, 1);
		g2.add(new Label("Note"), 3, 1);

		g2.add(accNumber, 0, 2);
		g2.add(Name, 1, 2);
		g2.add(Cost, 2, 2);
		g2.add(note, 3, 2);

		g2.add(accNumber1, 0, 3);
		g2.add(Name1, 1, 3);
		g2.add(Cost1, 2, 3);
		g2.add(note1, 3, 3);

		
		File f = Inputs.sanadsFile;
		Scanner in = new Scanner(f);
		while(in.hasNextLine()) {
			String[] ok = in.nextLine().split(",");
			if(sanadnum.equalsIgnoreCase(ok[1])) {
				if(ok[9].equalsIgnoreCase("c0")) {
					accNumber.setText(ok[5]);
					Name.setText(ok[6]);
					Cost.setText(ok[7]);
					note.setText(ok[8]);
				}
				
				else if(ok[9].equalsIgnoreCase("c1")) {
					accNumber1.setText(ok[5]);
					Name1.setText(ok[6]);
					Cost1.setText(ok[7]);
					note1.setText(ok[8]);
				}
				else if(ok[9].equalsIgnoreCase("c2")) {
					accNumber2.setText(ok[5]);
					Name2.setText(ok[6]);
					Cost2.setText(ok[7]);
					note2.setText(ok[8]);
				}
				else if(ok[9].equalsIgnoreCase("c3")) {
					accNumber22.setText(ok[5]);
					Name22.setText(ok[6]);
					Cost22.setText(ok[7]);
					note22.setText(ok[8]);
				}
				
			}
		}
		Name.setDisable(true);
		Name1.setDisable(true);
		Name2.setDisable(true);
		Name22.setDisable(true);
		Cost.setDisable(true);
		Cost1.setDisable(true);
		Cost2.setDisable(true);
		Cost22.setDisable(true);
		accNumber.setDisable(true);
		accNumber1.setDisable(true);
		accNumber2.setDisable(true);
		accNumber22.setDisable(true);
		note.setDisable(true);
		note1.setDisable(true);
		note2.setDisable(true);
		note22.setDisable(true);
		for (int i = 0; i < input.size(); i++) {
			if (sanadnum.equalsIgnoreCase(String.valueOf(input.get(i).getSanadNumber()))) {
				sanadKabdDate.setText(input.get(i).getDate());
				sanadKabdDate.setDisable(true);
				coinCombo.setValue(input.get(i).getCoinName());
				coinCombo.setDisable(true);
				status.setText("Posted");
				status.setDisable(true);
			}

		}

		Label mm = new Label("Debit :");
		mm.setUnderline(true);
		mm.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
		g2.add(mm, 0, 4);
		g2.add(new Label("Account Number"), 0, 5);
		g2.add(new Label("Name"), 1, 5);
		g2.add(new Label("Amount"), 2, 5);
		g2.add(new Label("Note"), 3, 5);

		g2.add(accNumber2, 0, 6);
		g2.add(Name2, 1, 6);
		g2.add(Cost2, 2, 6);
		g2.add(note2, 3, 6);

		g2.add(accNumber22, 0, 7);
		g2.add(Name22, 1, 7);
		g2.add(Cost22, 2, 7);
		g2.add(note22, 3, 7);

		Button bst = new Button(" Check Status ");
		bst.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
		g2.add(bst, 0, 8);
		Label gr = new Label("Balanced");
		Label gr2 = new Label("Unbalanced");

		bst.setOnAction(w -> {
			if (Cost1.getText().trim().isEmpty() && !Cost22.getText().trim().isEmpty()) {
				double summ = Double.parseDouble(Cost.getText()) + 0;
				double dep = Double.parseDouble(Cost2.getText()) + Double.parseDouble(Cost22.getText());
				if (dep == summ) {
					gr2.setTextFill(Color.WHITE);
					gr2.setFont(Font.font(0.1));

					gr.setTextFill(Color.GREEN);
					gr.setFont(Font.font(15));

					g2.add(gr, 1, 8);
				} else if (dep != summ) {
					gr.setTextFill(Color.WHITE);
					gr.setFont(Font.font(0.1));
					gr2.setFont(Font.font(15));
					gr2.setTextFill(Color.RED);
					g2.add(gr2, 1, 8);
				}
			}
			if (Cost22.getText().trim().isEmpty() && !Cost1.getText().trim().isEmpty()) {
				double summ = Double.parseDouble(Cost.getText()) + Double.parseDouble(Cost1.getText());
				double dep = Double.parseDouble(Cost2.getText()) + 0;
				if (dep == summ) {
					gr2.setTextFill(Color.WHITE);
					gr2.setFont(Font.font(0.1));

					gr.setTextFill(Color.GREEN);
					gr.setFont(Font.font(15));

					g2.add(gr, 1, 8);
				} else if (dep != summ) {
					gr.setTextFill(Color.WHITE);
					gr.setFont(Font.font(0.1));
					gr2.setFont(Font.font(15));
					gr2.setTextFill(Color.RED);
					g2.add(gr2, 1, 8);
				}
			}
			if (Cost22.getText().trim().isEmpty() && Cost1.getText().trim().isEmpty()) {
				double summ = Double.parseDouble(Cost.getText()) + 0;
				double dep = Double.parseDouble(Cost2.getText()) + 0;
				if (dep == summ) {
					gr2.setTextFill(Color.WHITE);
					gr2.setFont(Font.font(0.1));

					gr.setTextFill(Color.GREEN);
					gr.setFont(Font.font(15));

					g2.add(gr, 1, 8);
				} else if (dep != summ) {
					gr.setTextFill(Color.WHITE);
					gr.setFont(Font.font(0.1));
					gr2.setFont(Font.font(15));
					gr2.setTextFill(Color.RED);
					g2.add(gr2, 1, 8);
				}
			} else {
				double summ = Double.parseDouble(Cost.getText()) + Double.parseDouble(Cost1.getText());
				double dep = Double.parseDouble(Cost2.getText()) + Double.parseDouble(Cost22.getText());
				if (dep == summ) {
					gr2.setTextFill(Color.WHITE);
					gr2.setFont(Font.font(0.1));

					gr.setTextFill(Color.GREEN);
					gr.setFont(Font.font(15));

					g2.add(gr, 1, 8);
				} else if (dep != summ) {
					gr.setTextFill(Color.WHITE);
					gr.setFont(Font.font(0.1));
					gr2.setFont(Font.font(15));
					gr2.setTextFill(Color.RED);
					g2.add(gr2, 1, 8);
				}
			}

		});

		g2.setAlignment(Pos.TOP_CENTER);
		stcent.getChildren().addAll(tcent, g2);
		sanadKabdPane.setCenter(stcent);

		// center ^

		GridPane g3 = new GridPane();

		Button save = new Button("Save");

		save.setPrefSize(120, 30);

		save.setOnAction(fv -> {
			try {
				String tq[] = Cost.getText().split("");
				String tq1[] = Cost1.getText().split("");
				String tq2[] = Cost2.getText().split("");
				String tq3[] = Cost22.getText().split("");

				for (int r = 0; r < tq.length; r++) {
					try {
						if (Cost.getText().trim().isEmpty()
								|| Integer.parseInt(tq[r]) > 0 && Integer.parseInt(tq[r]) < 9 || tq[r] == ".") {

						}
					}

					catch (Exception q) {
						JOptionPane.showMessageDialog(null, "Amount Should be just numbers");
					}
				}
				for (int r1 = 0; r1 < tq1.length; r1++) {
					try {
						if (Cost1.getText().trim().isEmpty()
								|| Integer.parseInt(tq1[r1]) > 0 && Integer.parseInt(tq1[r1]) < 9 || tq1[r1] == ".") {

						}
					} catch (Exception q1) {
						JOptionPane.showMessageDialog(null, "Amount Should be just numbers");
					}

				}

				for (int r1 = 0; r1 < tq2.length; r1++) {
					try {
						if (Cost2.getText().trim().isEmpty()
								|| Integer.parseInt(tq2[r1]) > 0 && Integer.parseInt(tq2[r1]) < 9 || tq2[r1] == ".") {

						}
					} catch (Exception q1) {
						JOptionPane.showMessageDialog(null, "Amount Should be just numbers");
					}

				}
				for (int r1 = 0; r1 < tq3.length; r1++) {
					try {
						if (Cost22.getText().trim().isEmpty()
								|| Integer.parseInt(tq3[r1]) > 0 && Integer.parseInt(tq3[r1]) < 9) {

						}
					} catch (Exception q1) {
						JOptionPane.showMessageDialog(null, "Amount Should be just numbers");
					}

				}

				if (!accNumber.getText().trim().isEmpty()) {

					input.add(new SanadKayd(Long.parseLong(sanadKabdNumber.getText()), coinCombo.getValue().toString(),
							sanadKabdDate.getText(), new Accounts(Long.parseLong(accNumber.getText()), Name.getText(),
									Double.parseDouble(Cost.getText()), note.getText())));
					if(input.get(input.size() - 1) instanceof SanadKayd) {
						((SanadKayd)input.get(input.size() - 1)).setCnum("c0");
					}
					input.get(input.size() - 1).saveSanad();
				}
				if (!accNumber1.getText().trim().isEmpty()) {

					input.add(new SanadKayd(Long.parseLong(sanadKabdNumber.getText()), coinCombo.getValue().toString(),
							sanadKabdDate.getText(), new Accounts(Long.parseLong(accNumber1.getText()), Name1.getText(),
									Double.parseDouble(Cost1.getText()), note1.getText())));
					if(input.get(input.size() - 1) instanceof SanadKayd) {
						((SanadKayd)input.get(input.size() - 1)).setCnum("c1");
					}
					input.get(input.size() - 1).saveSanad();
				}
				if (!accNumber2.getText().trim().isEmpty()) {

					input.add(new SanadKayd(Long.parseLong(sanadKabdNumber.getText()), coinCombo.getValue().toString(),
							sanadKabdDate.getText(), new Accounts(Long.parseLong(accNumber2.getText()), Name2.getText(),
									Double.parseDouble(Cost2.getText()), note2.getText())));
					if(input.get(input.size() - 1) instanceof SanadKayd) {
						((SanadKayd)input.get(input.size() - 1)).setCnum("c2");
					}
					input.get(input.size() - 1).saveSanad();
				}

				if (!accNumber22.getText().trim().isEmpty()) {

					input.add(new SanadKayd(Long.parseLong(sanadKabdNumber.getText()), coinCombo.getValue().toString(),
							sanadKabdDate.getText(), new Accounts(Long.parseLong(accNumber22.getText()),
									Name22.getText(), Double.parseDouble(Cost22.getText()), note22.getText())));
					if(input.get(input.size() - 1) instanceof SanadKayd) {
						((SanadKayd)input.get(input.size() - 1)).setCnum("c3");
					}
					input.get(input.size() - 1).saveSanad();

				}

			} catch (Exception n) {
				JOptionPane.showMessageDialog(null, "The Voucher Saved Before");
			}
		});
		Button tarhel = new Button("ترحيل");
		tarhel.setOnAction(h -> {

			String rr = "";
			try {
				Scanner inFile = new Scanner(refresh);
				while(inFile.hasNextLine()) {
					rr+= inFile.nextLine()+"\n";
				}
			}
			catch(Exception g) {
				
			}
			try(PrintWriter p = new PrintWriter(refresh)){
				long n=0;
				long n1=0;
				long n2=0;
				long n3=0;
				try {
					n=Long.parseLong(accNumber.getText());
				}
				catch(Exception m) {
					 n=0;
				}
				try {
					n1=Long.parseLong(accNumber1.getText());
				}
				catch(Exception m) {
					 n1=0;
				}
				try {
					n2=Long.parseLong(accNumber2.getText());
				}
				catch(Exception m) {
					 n2=0;
				}
				try {
					n3=Long.parseLong(accNumber22.getText());
				}
				catch(Exception m) {
					 n3=0;
				}
				
				
				if(rr == "") {   // assets and expense  drawings
					if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
					
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
					
					}
					else if(n!=0){
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
						
					}
					if((n1>110 && n1<125) || (n1==340) || (n1>509 && n1<551) && n1!=0) {
						
						p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",+"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
					
					}
					else if(n1!=0){
						p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",-"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
						
					}
					if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
						
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
					
					}
					else if(n2!=0){
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
						
					}
					if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
						
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
					
					}
					else if(n3!=0){
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
						
					}
				}
				else {
					p.append(rr);
					if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
						
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
					
					}
					else if(n!=0){
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
						
					}
					if((n1>110 && n1<125) || (n1==340) || (n1>509 && n1<551) && n1!=0) {
						
						p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",+"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
					
					}
					else if(n1!=0){
						p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",-"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
						
					}
					if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
						
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
					
					}
					else if(n2!=0){
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
						
					}
					if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
						
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
					
					}
					else if(n3!=0){
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
						
					}
						
				}
			} catch (FileNotFoundException e1) {
				System.out.println("Creat Refresh file");
			}

		});
		tarhel.setPrefSize(120, 30);
		Button printAndTarhel = new Button("ترحيل و طباعة");
		printAndTarhel.setPrefSize(150, 30);
		Button printt = new Button("Print");
		printt.setPrefSize(120, 30);

		g3.add(save, 0, 0);
		g3.add(tarhel, 1, 0);
		g3.add(printt, 2, 0);
		g3.add(printAndTarhel, 3, 0);
		g3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
		g3.setHgap(140);
		g3.setPadding(new Insets(0, 60, 70, 0));
		g3.setAlignment(Pos.CENTER);
		sanadKabdPane.setBottom(g3);
		root51.getChildren().add(sanadKabdPane);

		ImageView iamg13a5 = new ImageView("F1.jpg");
		HBox hsb13a5 = new HBox();
		// hsb13a5.setPrefHeight(30);
		hsb13a5.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		hsb13a5.getChildren().addAll(iamg13a5);
		px1a.setBottom(hsb13a5);

		return kayds;

	}

	public static Scene kabd(String sanadnum, Stage primaryStage) throws Exception {

		BorderPane pxa = new BorderPane();
		Scene selxa = new Scene(pxa, 1540, 800);

		ImageView back1 = new ImageView("dsBuffer.bmp.png");
		back1.setPreserveRatio(true);
		Button bk1 = new Button("رجوع", back1);
		bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
		Label logon151 = new Label("                                    سند قبض                            ");
		logon151.setFont(Font.font(35));

		bk1.setOnAction(ss -> {
			primaryStage.setScene(scene1A);
			primaryStage.show();
		});
		ImageView limg5 = new ImageView("Logout-icon.png");

		Button out5 = new Button("تسجيل الخروج", limg5);
		out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
		out5.setOnAction(t -> {
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		});

		HBox hh15 = new HBox();

		hh15.setSpacing(228);
		hh15.setMaxSize(2100, 50);
		hh15.getChildren().addAll(bk1, logon151, out5);
		hh15.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
		pxa.setTop(hh15);

		StackPane root51 = new StackPane();

		Image img5 = new Image("PM316.jpg");
		BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background bGround5 = new Background(bImg5);
		root51.setBackground(bGround5);
		pxa.setCenter(root51);
		String[] cointxt = { "شيقل", "دولار", "دينار" };
		ComboBox coinCombo = new ComboBox(FXCollections.observableArrayList(cointxt));
		coinCombo.setStyle("-fx-font-size: 10pt;-fx-font-weight: bold;");
		coinCombo.setPromptText("اختر عملة");

		TextField status = new TextField();
		status.setPrefColumnCount(6);
		status.setText("جديد");
		status.setDisable(true);

		Rectangle ttop = new Rectangle(400, 40, 1100, 200);
		ttop.setStroke(Color.BLACK);
		ttop.setFill(Color.TRANSPARENT);
		StackPane santop = new StackPane();
		santop.setPadding(new Insets(33, 160, 0, 0));

		BorderPane sanadKabdPane = new BorderPane();
		HBox hAll = new HBox(60);
		hAll.setAlignment(Pos.CENTER);
		hAll.setPadding(new Insets(25, 10, 10, 0));
		GridPane g1 = new GridPane();
		g1.setHgap(15);
		g1.setVgap(20);
		HBox h2 = new HBox(7.5);
		HBox h3 = new HBox(15);
		TextField sanadKabdNumber = new TextField();
		TextField sanadKabdDate = new TextField();
		TextField ccv = new TextField();
		h2.getChildren().addAll(new Label("تاريخ السند  "), sanadKabdDate);
		g1.add(new Label("رقم السند  "), 0, 0);
		g1.add(sanadKabdNumber, 1, 0);
		g1.add(new Label("العملة"), 0, 1);
		g1.add(coinCombo, 1, 1);
		g1.add(new Label("رقم الفاتوره  "), 0, 2);
		g1.add(ccv, 1, 2);

		Button fin = new Button("جد الفاتورة");
		g1.add(fin, 2, 2);

		h3.getChildren().addAll(new Label(" الحاله "), status);
		g1.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
		h3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
		hAll.getChildren().addAll(g1, h2, h3);
		h2.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");

		sanadKabdPane.setTop(santop);
		santop.getChildren().addAll(ttop, hAll);
		/// Top ^
		TextField accNumber = new TextField();
		accNumber.setDisable(true);
		accNumber.setText("111");
		TextField Name = new TextField();
		Name.setDisable(true);
		Name.setText("Cash");
		TextField Cost = new TextField();
		TextField note = new TextField();

		TextField accNumber2 = new TextField();
		accNumber2.setDisable(true);
		accNumber2.setText("410");
		TextField Name2 = new TextField();
		Name2.setDisable(true);
		Name2.setText("Revenue");
		TextField Cost2 = new TextField();
		TextField note2 = new TextField();

		TextField accNumber22 = new TextField();
		accNumber22.setDisable(true);
		accNumber22.setText("220");
		TextField Name22 = new TextField();
		Name22.setDisable(true);
		Name22.setText("Value Added tax Provision");
		TextField Cost22 = new TextField();
		TextField note22 = new TextField();

		Rectangle tcent = new Rectangle(400, 130, 1100, 360);
		tcent.setStroke(Color.BLACK);
		tcent.setFill(Color.TRANSPARENT);
		StackPane stcent = new StackPane();
		stcent.setPadding(new Insets(0, 160, 0, 0));
		GridPane g2 = new GridPane();
		g2.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
		g2.setPadding(new Insets(30, 0, 0, 0));
		g2.setHgap(45);
		g2.setVgap(10);
		Label mm3 = new Label("مدين :");
		mm3.setStyle("-fx-font-size: 15pt;-fx-font-weight: bold;");
		mm3.setUnderline(true);
		g2.add(mm3, 0, 0);
		g2.add(new Label("رقم الحساب"), 0, 1);
		g2.add(new Label("الاسم"), 1, 1);
		g2.add(new Label("المبلغ"), 2, 1);
		g2.add(new Label("ملاحظات"), 3, 1);

		g2.add(accNumber, 0, 2);
		g2.add(Name, 1, 2);
		g2.add(Cost, 2, 2);
		g2.add(note, 3, 2);
		Label mm = new Label("دائن :");
		mm.setUnderline(true);
		mm.setStyle("-fx-font-size: 15pt;-fx-font-weight: bold;");
		g2.add(mm, 0, 3);
		g2.add(new Label("رقم "), 0, 4);
		g2.add(new Label("الاسم"), 1, 4);
		g2.add(new Label("المبلغ"), 2, 4);
		g2.add(new Label("ملاحظات"), 3, 4);

		g2.add(accNumber2, 0, 5);
		g2.add(Name2, 1, 5);
		g2.add(Cost2, 2, 5);
		g2.add(note2, 3, 5);
		g2.add(accNumber22, 0, 6);
		g2.add(Name22, 1, 6);
		g2.add(Cost22, 2, 6);
		g2.add(note22, 3, 6);
		Button bst = new Button(" فحص الحاله ");
		bst.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
		g2.add(bst, 0, 7);
		Label gr = new Label("متوازن ");
		Label gr2 = new Label("غير متوازن");

		g2.setAlignment(Pos.TOP_CENTER);
		stcent.getChildren().addAll(tcent, g2);
		sanadKabdPane.setCenter(stcent);
		sanadKabdNumber.setDisable(true);
		// center ^

		for (int i = 0; i < input.size(); i++) {
			if (sanadnum.equalsIgnoreCase(String.valueOf(input.get(i).getSanadNumber()))) {
				sanadKabdDate.setText(input.get(i).getDate());

				sanadKabdDate.setDisable(true);

				coinCombo.setDisable(true);
				status.setText("Posted");
				status.setDisable(true);
				ccv.setText(String.valueOf(input.get(i).getFatoraNumber()));
				ccv.setDisable(true);
				sanadKabdNumber.setText(sanadnum);
				sanadKabdNumber.setDisable(true);
				note.setText(input.get(i).getNote());
				note2.setText(input.get(i).getNote());
				note22.setText(input.get(i).getNote());
				note.setDisable(true);
				note2.setDisable(true);
				note22.setDisable(true);

			}

		}
		for (int i = 0; i < billNumber.size(); i++) {
			if (ccv.getText().equalsIgnoreCase(billNumber.get(i).getBillnum())) {

				Cost.setText(billNumber.get(i).getShamel());
				coinCombo.setValue(billNumber.get(i).getType());
				Cost2.setText(billNumber.get(i).getUnshamel());
				Cost22.setText(billNumber.get(i).getVat());
				coinCombo.setValue(billNumber.get(i).getType());
				Cost2.setDisable(true);
				Cost22.setDisable(true);
				Cost.setDisable(true);
			}

		}

		GridPane g3 = new GridPane();

		Button save = new Button("حفظ");
		save.setOnAction(f -> {

			String tq[] = Cost.getText().split("");

			String tq2[] = Cost2.getText().split("");
			String tq3[] = Cost22.getText().split("");

			for (int r = 0; r < tq.length; r++) {
				try {
					if (Cost.getText().trim().isEmpty() || Integer.parseInt(tq[r]) > 0 && Integer.parseInt(tq[r]) < 9
							|| tq[r] == ".") {

					}
				}

				catch (Exception q) {
					JOptionPane.showMessageDialog(null, "Amount Should be just numbers");
				}
			}

			for (int r1 = 0; r1 < tq2.length; r1++) {
				try {
					if (Cost2.getText().trim().isEmpty()
							|| Integer.parseInt(tq2[r1]) > 0 && Integer.parseInt(tq2[r1]) < 9 || tq2[r1] == ".") {

					}
				} catch (Exception q1) {
					JOptionPane.showMessageDialog(null, "Amount Should be just numbers");
				}

			}
			for (int r1 = 0; r1 < tq3.length; r1++) {
				try {
					if (Cost22.getText().trim().isEmpty()
							|| Integer.parseInt(tq3[r1]) > 0 && Integer.parseInt(tq3[r1]) < 9 || tq3[r1] == ".") {

					}
				} catch (Exception q1) {
					JOptionPane.showMessageDialog(null, "Amount Should be just numbers");
				}

			}
			try {
				input.add(new SanadKabd(Long.parseLong(sanadKabdNumber.getText()), coinCombo.getValue().toString(),
						Long.parseLong(ccv.getText()), sanadKabdDate.getText(),
						new Accounts(Long.parseLong(accNumber.getText()), Name.getText(),
								Double.parseDouble(Cost.getText()), note.getText())));
				try {
					input.get(input.size() - 1).saveSanad();
				} catch (Exception n) {
					JOptionPane.showMessageDialog(null, "تم حفظ السند من قبل ");
				}

			} catch (Exception c) {
				System.out.println(c.getMessage());
			}
		});
		save.setPrefSize(120, 30);
		Button printt = new Button("طباعة");
		printt.setPrefSize(120, 30);
		Button tarhel = new Button("ترحيل");
		tarhel.setOnAction(h -> {

			String rr = "";
			try {
				Scanner inFile = new Scanner(refresh);
				while(inFile.hasNextLine()) {
					rr+= inFile.nextLine()+"\n";
				}
			}
			catch(Exception g) {
				
			}
			try(PrintWriter p = new PrintWriter(refresh)){
				long n=0;
				long n2=0;
				long n3=0;
				try {
					n=Long.parseLong(accNumber.getText());
				}
				catch(Exception m) {
					 n=0;
				}
			
				try {
					n2=Long.parseLong(accNumber2.getText());
				}
				catch(Exception m) {
					 n2=0;
				}
				try {
					n3=Long.parseLong(accNumber22.getText());
				}
				catch(Exception m) {
					 n3=0;
				}
				
				
				if(rr == "") {   // assets and expense  drawings
					if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
					
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
					
					}
					else if(n!=0){
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
						
					}
					
					if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
						
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
					
					}
					else if(n2!=0){
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
						
					}
					if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
						
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
					
					}
					else if(n3!=0){
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
						
					}
				}
				else {
					p.append(rr);
					if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
						
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
					
					}
					else if(n!=0){
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
						
					}
					
					if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
						
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
					
					}
					else if(n2!=0){
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
						
					}
					if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
						
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
					
					}
					else if(n3!=0){
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
						
					}
						
				}
			} catch (FileNotFoundException e1) {
				System.out.println("Creat Refresh file");
			}

		});
		tarhel.setPrefSize(120, 30);
		Button printAndTarhel = new Button("طباعة وترحيل");
		printAndTarhel.setPrefSize(150, 30);

		g3.add(save, 0, 0);
		g3.add(tarhel, 1, 0);
		g3.add(printt, 2, 0);
		g3.add(printAndTarhel, 3, 0);
		g3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
		g3.setHgap(140);
		g3.setPadding(new Insets(0, 60, 20, 0));
		g3.setAlignment(Pos.CENTER);
		sanadKabdPane.setBottom(g3);
		root51.getChildren().add(sanadKabdPane);

		ImageView iamg13a5 = new ImageView("F1.jpg");
		HBox hsb13a5 = new HBox();
		// hsb13a5.setPrefHeight(30);
		hsb13a5.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		hsb13a5.getChildren().addAll(iamg13a5);
		pxa.setBottom(hsb13a5);
		fin.setDisable(true);

		bst.setOnAction(w -> {
			double summ = Double.parseDouble(Cost2.getText()) + Double.parseDouble(Cost22.getText());
			double dep = Double.parseDouble(Cost.getText());
			if (summ == dep) {
				gr2.setTextFill(Color.WHITE);
				gr2.setFont(Font.font(0.1));

				gr.setTextFill(Color.GREEN);
				gr.setFont(Font.font(15));

				g2.add(gr, 1, 7);
			} else if (summ != dep) {
				gr.setTextFill(Color.WHITE);
				gr.setFont(Font.font(0.1));
				gr2.setFont(Font.font(15));
				gr2.setTextFill(Color.RED);
				g2.add(gr2, 1, 7);
			}
		});

		return selxa;

	}

	public static Scene sarf(String sanadnum, Stage primaryStage) throws Exception {
		BorderPane px2aa = new BorderPane();
		Scene selx2aa = new Scene(px2aa, 1700, 800);

		ImageView back1 = new ImageView("dsBuffer.bmp.png");
		back1.setPreserveRatio(true);
		Button bk1 = new Button("رجوع", back1);
		bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
		Label logon151 = new Label("                                  سند صرف                             ");
		logon151.setFont(Font.font(35));

		bk1.setOnAction(ss -> {
			primaryStage.setScene(scene1A);
			primaryStage.show();
		});
		ImageView limg5 = new ImageView("Logout-icon.png");

		Button out5 = new Button("تسجيل الخروج", limg5);
		out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
		out5.setOnAction(t -> {
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		});

		HBox hh15 = new HBox();

		hh15.setSpacing(228);
		hh15.setMaxSize(2100, 50);
		hh15.getChildren().addAll(bk1, logon151, out5);
		hh15.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
		px2aa.setTop(hh15);

		StackPane root51 = new StackPane();

		Image img5 = new Image("PM316.jpg");
		BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background bGround5 = new Background(bImg5);
		root51.setBackground(bGround5);
		px2aa.setCenter(root51);
		String[] cointxt = { "ILS", "USD", "JOD" };
		ComboBox coinCombo = new ComboBox(FXCollections.observableArrayList(cointxt));
		coinCombo.setStyle("-fx-font-size: 10pt;-fx-font-weight: bold;");
		coinCombo.setPromptText("اختر عملة");
		String[] s = ttg.getSelectedToggle().toString().split(" ");
		coinCombo.setValue(s[1]);
		coinCombo.setDisable(true);

		TextField status = new TextField();
		status.setPrefColumnCount(6);
		status.setText("جديد");
		status.setDisable(true);

		Rectangle ttop = new Rectangle(400, 40, 1100, 200);
		ttop.setStroke(Color.BLACK);
		ttop.setFill(Color.TRANSPARENT);
		StackPane santop = new StackPane();
		santop.setPadding(new Insets(33, 160, 0, 0));

		BorderPane sanadKabdPane = new BorderPane();
		HBox hAll = new HBox(40);
		hAll.setAlignment(Pos.CENTER);
		hAll.setPadding(new Insets(25, 10, 10, 0));
		GridPane g1 = new GridPane();
		g1.setHgap(10);
		g1.setVgap(20);
		HBox h2 = new HBox(7.5);
		HBox h3 = new HBox(15);
		TextField sanadKabdNumber = new TextField();
		TextField sanadKabdDate = new TextField();
		TextField xs = new TextField();
		h2.getChildren().addAll(new Label("تاريخ السند "), sanadKabdDate);
		g1.add(new Label("رقم السند "), 0, 0);
		g1.add(sanadKabdNumber, 1, 0);
		g1.add(new Label("عمله"), 0, 1);
		g1.add(coinCombo, 1, 1);
		g1.add(new Label("رقم الفاتورة "), 0, 2);
		g1.add(xs, 1, 2);
		Button fin = new Button("جد الفاتورة");
		g1.add(fin, 2, 2);

		h3.getChildren().addAll(new Label(" الحالة "), status);
		g1.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
		h3.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
		hAll.getChildren().addAll(g1, h2, h3);
		h2.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");

		sanadKabdPane.setTop(santop);
		santop.getChildren().addAll(ttop, hAll);
		/// Top ^
		TextField accNumber = new TextField();
		accNumber.setText("540");
		accNumber.setDisable(true);
		TextField Name = new TextField();
		Name.setText("General Purchase Expense");
		Name.setDisable(true);
		TextField Cost = new TextField();
		TextField note = new TextField();

		TextField accNumber1 = new TextField();
		accNumber1.setText("220");
		accNumber1.setDisable(true);
		TextField Name1 = new TextField();
		Name1.setText("Value Added tax Provision");
		Name1.setDisable(true);
		TextField Cost1 = new TextField();
		TextField note1 = new TextField();

		TextField accNumber2 = new TextField();
		accNumber2.setText("111");
		accNumber2.setDisable(true);
		TextField Name2 = new TextField();
		Name2.setText("Cash");
		Name2.setDisable(true);
		TextField Cost2 = new TextField();
		TextField note2 = new TextField();

		TextField accNumber22 = new TextField();
		TextField Name22 = new TextField();
		TextField Cost22 = new TextField();
		TextField note22 = new TextField();

		Rectangle tcent = new Rectangle(400, 130, 1100, 360);
		tcent.setStroke(Color.BLACK);
		tcent.setFill(Color.TRANSPARENT);
		StackPane stcent = new StackPane();
		stcent.setPadding(new Insets(0, 160, 0, 0));
		GridPane g2 = new GridPane();
		g2.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
		g2.setPadding(new Insets(30, 0, 0, 0));
		g2.setHgap(45);
		g2.setVgap(10);
		Label mm3 = new Label("دائن :");
		mm3.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
		mm3.setUnderline(true);
		g2.add(mm3, 0, 0);
		g2.add(new Label("رقم الحساب"), 0, 1);
		g2.add(new Label("اسم الحساب"), 1, 1);
		g2.add(new Label("المبلغ"), 2, 1);
		g2.add(new Label("ملاحظات"), 3, 1);

		g2.add(accNumber, 0, 2);
		g2.add(Name, 1, 2);
		g2.add(Cost, 2, 2);
		g2.add(note, 3, 2);

		g2.add(accNumber1, 0, 3);
		g2.add(Name1, 1, 3);
		g2.add(Cost1, 2, 3);
		g2.add(note1, 3, 3);

		Label mm = new Label("مدين");
		mm.setUnderline(true);
		mm.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
		g2.add(mm, 0, 4);
		g2.add(new Label("رقم الحساب"), 0, 5);
		g2.add(new Label("اسم الحساب"), 1, 5);
		g2.add(new Label("المبلغ"), 2, 5);
		g2.add(new Label("ملاحظات"), 3, 5);

		g2.add(accNumber2, 0, 6);
		g2.add(Name2, 1, 6);
		g2.add(Cost2, 2, 6);
		g2.add(note2, 3, 6);

		Button bst = new Button(" افحص الحالة ");
		bst.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
		g2.add(bst, 0, 7);
		Label gr = new Label("متوازن");
		Label gr2 = new Label("غير متوازن");
		bst.setOnAction(w -> {
			double summ = Double.parseDouble(Cost.getText()) + Double.parseDouble(Cost1.getText());
			double dep = Double.parseDouble(Cost2.getText());
			if (dep == summ) {
				gr2.setTextFill(Color.WHITE);
				gr2.setFont(Font.font(0.1));

				gr.setTextFill(Color.GREEN);
				gr.setFont(Font.font(15));

				g2.add(gr, 1, 7);
			} else if (dep != summ) {
				gr.setTextFill(Color.WHITE);
				gr.setFont(Font.font(0.1));
				gr2.setFont(Font.font(15));
				gr2.setTextFill(Color.RED);
				g2.add(gr2, 1, 7);
			}
		});

		for (int i = 0; i < input.size(); i++) {
			if (sanadnum.equalsIgnoreCase(String.valueOf(input.get(i).getSanadNumber()))) {
				sanadKabdDate.setText(input.get(i).getDate());

				sanadKabdDate.setDisable(true);
				coinCombo.setValue(input.get(i).getCoinName());
				coinCombo.setDisable(true);
				status.setText("Posted");
				status.setDisable(true);
				xs.setText(String.valueOf(input.get(i).getFatoraNumber()));
				xs.setDisable(true);
				sanadKabdNumber.setText(sanadnum);
				sanadKabdNumber.setDisable(true);
				note.setText(input.get(i).getNote());
				note2.setText(input.get(i).getNote());
				note22.setText(input.get(i).getNote());
				note.setDisable(true);
				note2.setDisable(true);
				note22.setDisable(true);

			}

		}
		for (int i = 0; i < billNumber2.size(); i++) {
			if (xs.getText().equalsIgnoreCase(billNumber2.get(i).getBillnum())) {
				coinCombo.setValue(billNumber2.get(i).getType());
				Cost.setText(billNumber2.get(i).getUnshamel());
				Cost1.setText(billNumber2.get(i).getVat());
				Cost2.setText(billNumber2.get(i).getShamel());
				Cost2.setDisable(true);
				Cost1.setDisable(true);
				Cost.setDisable(true);
			}

		}

		g2.setAlignment(Pos.TOP_CENTER);
		stcent.getChildren().addAll(tcent, g2);
		sanadKabdPane.setCenter(stcent);

		// center ^

		GridPane g3 = new GridPane();

		Button save = new Button("حفظ");
		save.setOnAction(f -> {
			String tq[] = Cost.getText().split("");
			String tq1[] = Cost1.getText().split("");
			String tq2[] = Cost2.getText().split("");

			for (int r = 0; r < tq.length; r++) {
				try {
					if (Cost.getText().trim().isEmpty() || Integer.parseInt(tq[r]) > 0 && Integer.parseInt(tq[r]) < 9
							|| tq[r] == ".") {

					}
				}

				catch (Exception q) {
					JOptionPane.showMessageDialog(null, "Amount Should be just numbers");
				}
			}
			for (int r1 = 0; r1 < tq1.length; r1++) {
				try {
					if (Cost1.getText().trim().isEmpty()
							|| Integer.parseInt(tq1[r1]) > 0 && Integer.parseInt(tq1[r1]) < 9 || tq1[r1] == ".") {

					}
				} catch (Exception q1) {
					JOptionPane.showMessageDialog(null, "Amount Should be just numbers");
				}

			}

			for (int r1 = 0; r1 < tq2.length; r1++) {
				try {
					if (Cost2.getText().trim().isEmpty()
							|| Integer.parseInt(tq2[r1]) > 0 && Integer.parseInt(tq2[r1]) < 9 || tq2[r1] == ".") {

					}
				} catch (Exception q1) {
					JOptionPane.showMessageDialog(null, "Amount Should be just numbers");
				}

			}

			try {
				input.add(new SanadSarf(Long.parseLong(sanadKabdNumber.getText()), coinCombo.getValue().toString(),
						Long.parseLong(xs.getText()), sanadKabdDate.getText(),
						new Accounts(Long.parseLong(accNumber.getText()), Name.getText(),
								Double.parseDouble(Cost2.getText()), note.getText())));
				try {
					input.get(input.size() - 1).saveSanad();
				} catch (Exception n) {
					JOptionPane.showMessageDialog(null, "تم حفظ السند من قبل ");
				}

			} catch (Exception c) {
				System.out.println(c.getMessage());
			}
		});
		save.setPrefSize(120, 30);
		Button printt = new Button("طباعة");
		printt.setPrefSize(120, 30);
		Button tarhel = new Button("ترحيل");
		tarhel.setPrefSize(120, 30);
		tarhel.setOnAction(h -> {

			String rr = "";
			try {
				Scanner inFile = new Scanner(refresh);
				while(inFile.hasNextLine()) {
					rr+= inFile.nextLine()+"\n";
				}
			}
			catch(Exception g) {
				
			}
			try(PrintWriter p = new PrintWriter(refresh)){
				long n=0;
				long n1=0;
				long n2=0;
				long n3=0;
				try {
					n=Long.parseLong(accNumber.getText());
				}
				catch(Exception m) {
					 n=0;
				}
				try {
					n1=Long.parseLong(accNumber1.getText());
				}
				catch(Exception m) {
					 n1=0;
				}
				try {
					n2=Long.parseLong(accNumber2.getText());
				}
				catch(Exception m) {
					 n2=0;
				}
				try {
					n3=Long.parseLong(accNumber22.getText());
				}
				catch(Exception m) {
					 n3=0;
				}
				
				
				if(rr == "") {   // assets and expense  drawings
					if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
					
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
					
					}
					else if(n!=0){
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
						
					}
					if((n1>110 && n1<125) || (n1==340) || (n1>509 && n1<551) && n1!=0) {
						
						p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",+"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
					
					}
					else if(n1!=0){
						p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",-"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
						
					}
					if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
						
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
					
					}
					else if(n2!=0){
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
						
					}
					if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
						
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
					
					}
					else if(n3!=0){
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
						
					}
				}
				else {
					p.append(rr);
					if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
						
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
					
					}
					else if(n!=0){
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
						
					}
					if((n1>110 && n1<125) || (n1==340) || (n1>509 && n1<551) && n1!=0) {
						
						p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",+"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
					
					}
					else if(n1!=0){
						p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",-"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
						
					}
					if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
						
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
					
					}
					else if(n2!=0){
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
						
					}
					if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
						
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
					
					}
					else if(n3!=0){
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
						
					}
						
				}
			} catch (FileNotFoundException e1) {
				System.out.println("Creat Refresh file");
			}

		});
		Button printAndTarhel = new Button("طباعة وترحيل");
		printAndTarhel.setPrefSize(150, 30);

		g3.add(save, 0, 0);
		g3.add(tarhel, 1, 0);
		g3.add(printt, 2, 0);
		g3.add(printAndTarhel, 3, 0);
		g3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
		g3.setHgap(140);
		g3.setPadding(new Insets(0, 60, 20, 0));
		g3.setAlignment(Pos.CENTER);
		sanadKabdPane.setBottom(g3);
		root51.getChildren().add(sanadKabdPane);

		ImageView iamg13a5 = new ImageView("F1.jpg");
		HBox hsb13a5 = new HBox();
		// hsb13a5.setPrefHeight(30);
		hsb13a5.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		hsb13a5.getChildren().addAll(iamg13a5);
		px2aa.setBottom(hsb13a5);

		return selx2aa;
	}

	public static Scene kayd1(String sanadnum, Stage primaryStage) throws Exception {

		BorderPane px1 = new BorderPane();
		Scene selx1 = new Scene(px1, 1540, 800);
		ImageView back1 = new ImageView("dsBuffer.bmp.png");
		back1.setPreserveRatio(true);
		Button bk1 = new Button("Back", back1);
		bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
		Label logon151 = new Label("                                    Jornal Voucher                      ");
		logon151.setFont(Font.font(35));

		bk1.setOnAction(ss -> {

			primaryStage.setScene(scene1);
			primaryStage.show();
		});
		ImageView limg5 = new ImageView("Logout-icon.png");

		Button out5 = new Button("LogOut", limg5);
		out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
		out5.setOnAction(t -> {
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		});

		HBox hh15 = new HBox();

		hh15.setSpacing(228);
		hh15.setMaxSize(2100, 50);
		hh15.getChildren().addAll(bk1, logon151, out5);
		hh15.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
		px1.setTop(hh15);

		StackPane root51 = new StackPane();

		Image img5 = new Image("PM316.jpg");
		BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background bGround5 = new Background(bImg5);
		root51.setBackground(bGround5);
		px1.setCenter(root51);
		String[] cointxt = { "ILS", "USD", "JOD" };
		ComboBox coinCombo = new ComboBox(FXCollections.observableArrayList(cointxt));
		coinCombo.setStyle("-fx-font-size: 10pt;-fx-font-weight: bold;");
		coinCombo.setPromptText("Select Currency");

		TextField status = new TextField();
		status.setPrefColumnCount(6);
		status.setText("New");
		status.setDisable(true);

		Rectangle ttop = new Rectangle(400, 40, 1100, 150);
		ttop.setStroke(Color.BLACK);
		ttop.setFill(Color.TRANSPARENT);
		StackPane santop = new StackPane();
		santop.setPadding(new Insets(33, 160, 0, 0));

		BorderPane sanadKabdPane = new BorderPane();
		HBox hAll = new HBox(60);
		hAll.setAlignment(Pos.CENTER);
		hAll.setPadding(new Insets(25, 10, 10, 0));
		GridPane g1 = new GridPane();
		g1.setHgap(15);
		g1.setVgap(20);

		HBox h2 = new HBox(7.5);
		HBox h3 = new HBox(15);
		TextField sanadKabdNumber = new TextField();
		TextField sanadKabdDate = new TextField();

		h2.getChildren().addAll(new Label("Voucher Date : "), sanadKabdDate);
		g1.add(new Label("Voucher Number : "), 0, 0);
		g1.add(sanadKabdNumber, 1, 0);
		g1.add(new Label("Coin"), 0, 1);
		g1.add(coinCombo, 1, 1);

		h3.getChildren().addAll(new Label(" Status "), status);
		g1.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
		h3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
		hAll.getChildren().addAll(g1, h2, h3);
		h2.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");

		sanadKabdPane.setTop(santop);
		santop.getChildren().addAll(ttop, hAll);
		/// Top ^
		TextField accNumber = new TextField();
		TextField Name = new TextField();
		TextField Cost = new TextField();
		TextField note = new TextField();

		TextField accNumber1 = new TextField();
		TextField Name1 = new TextField();
		TextField Cost1 = new TextField();
		TextField note1 = new TextField();

		TextField accNumber2 = new TextField();
		TextField Name2 = new TextField();
		TextField Cost2 = new TextField();
		TextField note2 = new TextField();

		TextField accNumber22 = new TextField();
		TextField Name22 = new TextField();
		TextField Cost22 = new TextField();
		TextField note22 = new TextField();

		accNumber.setOnKeyReleased(d -> {
			boolean flag = false;
			String[] ok = null;

			for (int i = 0; i < labelList.size(); i++) {
				String s = labelList.get(i).getText();
				s = s.replaceAll("[()]", "");
				ok = s.split(" ");
				
				if (ok[0].equalsIgnoreCase(accNumber.getText())) {

					flag = true;
					break;
				}
			}
			if (flag) {
				switch(ok.length) {
				case 0:Name.setText(ok[1]); break;
				case 1:Name.setText(ok[1]+ok[2]);break;
				case 2:Name.setText(ok[1]+ok[2]+ok[3]);break;
				case 3:Name.setText(ok[1]+ok[2]+ok[3]+ok[4]);break;
				}
			}
		});

		accNumber1.setOnKeyReleased(d -> {
			boolean flag = false;
			String[] ok = null;

			for (int i = 0; i < labelList.size(); i++) {
				String s = labelList.get(i).getText();
				s = s.replaceAll("[()]", "");
				ok = s.split(" ");
				if (ok[0].equalsIgnoreCase(accNumber1.getText())) {

					flag = true;
					break;
				}
			}
			if (flag) {
				switch(ok.length) {
				case 2:Name1.setText(ok[1]); break;
				case 3:Name1.setText(ok[1]+" "+ok[2]);break;
				case 4:Name1.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
				case 5:Name1.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
				}
			}
		});

		accNumber2.setOnKeyReleased(d -> {
			boolean flag = false;
			String[] ok = null;

			for (int i = 0; i < labelList.size(); i++) {
				String s = labelList.get(i).getText();
				s = s.replaceAll("[()]", "");
				ok = s.split(" ");
				if (ok[0].equalsIgnoreCase(accNumber2.getText())) {

					flag = true;
					break;
				}
			}
			if (flag) {
				switch(ok.length) {
				case 2:Name2.setText(ok[1]); break;
				case 3:Name2.setText(ok[1]+" "+ok[2]);break;
				case 4:Name2.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
				case 5:Name2.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
				}
			}
		});

		accNumber22.setOnKeyReleased(d -> {
			boolean flag = false;
			String[] ok = null;

			for (int i = 0; i < labelList.size(); i++) {
				String s = labelList.get(i).getText();
				s = s.replaceAll("[()]", "");
				ok = s.split(" ");
				if (ok[0].equalsIgnoreCase(accNumber22.getText())) {

					flag = true;
					break;
				}
			}
			if (flag) {
				switch(ok.length) {
				case 2:Name22.setText(ok[1]); break;
				case 3:Name22.setText(ok[1]+" "+ok[2]);break;
				case 4:Name22.setText(ok[1]+" "+ok[2]+" "+ok[3]);break;
				case 5:Name22.setText(ok[1]+" "+ok[2]+" "+ok[3]+" "+ok[4]);break;
				}
			}
		});

		
		
		
		
		
		
		
		
		
		
		
		
		
		Rectangle tcent = new Rectangle(400, 130, 1100, 430);
		tcent.setStroke(Color.BLACK);
		tcent.setFill(Color.TRANSPARENT);
		StackPane stcent = new StackPane();
		stcent.setPadding(new Insets(0, 160, 0, 0));
		GridPane g2 = new GridPane();
		g2.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
		g2.setPadding(new Insets(30, 0, 0, 0));
		g2.setHgap(45);
		g2.setVgap(10);
		Label mm3 = new Label("Credit :");
		mm3.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
		mm3.setUnderline(true);
		g2.add(mm3, 0, 0);
		g2.add(new Label("Account Number"), 0, 1);

		g2.add(new Label("Account Name"), 1, 1);

		g2.add(new Label("Amount"), 2, 1);
		g2.add(new Label("Note"), 3, 1);

		g2.add(accNumber, 0, 2);
		g2.add(Name, 1, 2);
		g2.add(Cost, 2, 2);
		g2.add(note, 3, 2);

		g2.add(accNumber1, 0, 3);
		g2.add(Name1, 1, 3);
		g2.add(Cost1, 2, 3);
		g2.add(note1, 3, 3);

		Label mm = new Label("Debit :");
		mm.setUnderline(true);
		mm.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
		g2.add(mm, 0, 4);
		g2.add(new Label("Account Number"), 0, 5);
		g2.add(new Label("Name"), 1, 5);
		g2.add(new Label("Amount"), 2, 5);
		g2.add(new Label("Note"), 3, 5);

		g2.add(accNumber2, 0, 6);
		g2.add(Name2, 1, 6);
		g2.add(Cost2, 2, 6);
		g2.add(note2, 3, 6);

		g2.add(accNumber22, 0, 7);
		g2.add(Name22, 1, 7);
		g2.add(Cost22, 2, 7);
		g2.add(note22, 3, 7);

		Button bst = new Button(" Check Status ");
		bst.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
		g2.add(bst, 0, 8);
		Label gr = new Label("Balanced");
		Label gr2 = new Label("Unbalanced");

		bst.setOnAction(w -> {
			if (Cost1.getText().trim().isEmpty() && !Cost22.getText().trim().isEmpty()) {
				double summ = Double.parseDouble(Cost.getText()) + 0;
				double dep = Double.parseDouble(Cost2.getText()) + Double.parseDouble(Cost22.getText());
				if (dep == summ) {
					gr2.setTextFill(Color.WHITE);
					gr2.setFont(Font.font(0.1));

					gr.setTextFill(Color.GREEN);
					gr.setFont(Font.font(15));

					g2.add(gr, 1, 8);
				} else if (dep != summ) {
					gr.setTextFill(Color.WHITE);
					gr.setFont(Font.font(0.1));
					gr2.setFont(Font.font(15));
					gr2.setTextFill(Color.RED);
					g2.add(gr2, 1, 8);
				}
			}
			if (Cost22.getText().trim().isEmpty() && !Cost1.getText().trim().isEmpty()) {
				double summ = Double.parseDouble(Cost.getText()) + Double.parseDouble(Cost1.getText());
				double dep = Double.parseDouble(Cost2.getText()) + 0;
				if (dep == summ) {
					gr2.setTextFill(Color.WHITE);
					gr2.setFont(Font.font(0.1));

					gr.setTextFill(Color.GREEN);
					gr.setFont(Font.font(15));

					g2.add(gr, 1, 8);
				} else if (dep != summ) {
					gr.setTextFill(Color.WHITE);
					gr.setFont(Font.font(0.1));
					gr2.setFont(Font.font(15));
					gr2.setTextFill(Color.RED);
					g2.add(gr2, 1, 8);
				}
			}
			if (Cost22.getText().trim().isEmpty() && Cost1.getText().trim().isEmpty()) {
				double summ = Double.parseDouble(Cost.getText()) + 0;
				double dep = Double.parseDouble(Cost2.getText()) + 0;
				if (dep == summ) {
					gr2.setTextFill(Color.WHITE);
					gr2.setFont(Font.font(0.1));

					gr.setTextFill(Color.GREEN);
					gr.setFont(Font.font(15));

					g2.add(gr, 1, 8);
				} else if (dep != summ) {
					gr.setTextFill(Color.WHITE);
					gr.setFont(Font.font(0.1));
					gr2.setFont(Font.font(15));
					gr2.setTextFill(Color.RED);
					g2.add(gr2, 1, 8);
				}
			} else {
				double summ = Double.parseDouble(Cost.getText()) + Double.parseDouble(Cost1.getText());
				double dep = Double.parseDouble(Cost2.getText()) + Double.parseDouble(Cost22.getText());
				if (dep == summ) {
					gr2.setTextFill(Color.WHITE);
					gr2.setFont(Font.font(0.1));

					gr.setTextFill(Color.GREEN);
					gr.setFont(Font.font(15));

					g2.add(gr, 1, 8);
				} else if (dep != summ) {
					gr.setTextFill(Color.WHITE);
					gr.setFont(Font.font(0.1));
					gr2.setFont(Font.font(15));
					gr2.setTextFill(Color.RED);
					g2.add(gr2, 1, 8);
				}
			}

		});

		g2.setAlignment(Pos.TOP_CENTER);
		stcent.getChildren().addAll(tcent, g2);
		sanadKabdPane.setCenter(stcent);

		// center ^
		File f = Inputs.sanadsFile;
		Scanner in = new Scanner(f);
		while(in.hasNextLine()) {
			String[] ok = in.nextLine().split(",");
			if(sanadnum.equalsIgnoreCase(ok[1])) {
				if(ok[9].equalsIgnoreCase("c0")) {
					accNumber.setText(ok[5]);
					Name.setText(ok[6]);
					Cost.setText(ok[7]);
					note.setText(ok[8]);
				}
				
				else if(ok[9].equalsIgnoreCase("c1")) {
					accNumber1.setText(ok[5]);
					Name1.setText(ok[6]);
					Cost1.setText(ok[7]);
					note1.setText(ok[8]);
				}
				else if(ok[9].equalsIgnoreCase("c2")) {
					accNumber2.setText(ok[5]);
					Name2.setText(ok[6]);
					Cost2.setText(ok[7]);
					note2.setText(ok[8]);
				}
				else if(ok[9].equalsIgnoreCase("c3")) {
					accNumber22.setText(ok[5]);
					Name22.setText(ok[6]);
					Cost22.setText(ok[7]);
					note22.setText(ok[8]);
				}
				
			}
		}
Name.setDisable(true);
Name1.setDisable(true);
Name2.setDisable(true);
Name22.setDisable(true);
Cost.setDisable(true);
Cost1.setDisable(true);
Cost2.setDisable(true);
Cost22.setDisable(true);
accNumber.setDisable(true);
accNumber1.setDisable(true);
accNumber2.setDisable(true);
accNumber22.setDisable(true);
note.setDisable(true);
note1.setDisable(true);
note2.setDisable(true);
note22.setDisable(true);



		for (int i = 0; i < input.size(); i++) {
			if(sanadnum.equalsIgnoreCase(String.valueOf(input.get(i).getSanadNumber()))) {
				sanadKabdDate.setText(input.get(i).getDate());

				sanadKabdDate.setDisable(true);
				coinCombo.setValue(input.get(i).getCoinName());
				coinCombo.setDisable(true);
				status.setText("Posted");
				status.setDisable(true);

				sanadKabdNumber.setText(sanadnum);
				sanadKabdNumber.setDisable(true);
				note.setText(input.get(i).getNote());
				note2.setText(input.get(i).getNote());
				note22.setText(input.get(i).getNote());
				note.setDisable(true);
				note2.setDisable(true);
				note22.setDisable(true);
				
				

			}

		}

		GridPane g3 = new GridPane();

		Button save = new Button("Save");

		save.setPrefSize(120, 30);

		save.setOnAction(fc -> {

			String tq[] = Cost.getText().split("");
			String tq1[] = Cost1.getText().split("");
			String tq2[] = Cost2.getText().split("");
			String tq3[] = Cost22.getText().split("");

			for (int r = 0; r < tq.length; r++) {
				try {
					if (Cost.getText().trim().isEmpty() || Integer.parseInt(tq[r]) > 0 && Integer.parseInt(tq[r]) < 9
							|| tq[r] == ".") {

					}
				}

				catch (Exception q) {
					JOptionPane.showMessageDialog(null, "Amount Should be just numbers");
				}
			}
			for (int r1 = 0; r1 < tq1.length; r1++) {
				try {
					if (Cost1.getText().trim().isEmpty()
							|| Integer.parseInt(tq1[r1]) > 0 && Integer.parseInt(tq1[r1]) < 9 || tq1[r1] == ".") {

					}
				} catch (Exception q1) {
					JOptionPane.showMessageDialog(null, "Amount Should be just numbers");
				}

			}

			for (int r1 = 0; r1 < tq2.length; r1++) {
				try {
					if (Cost2.getText().trim().isEmpty()
							|| Integer.parseInt(tq2[r1]) > 0 && Integer.parseInt(tq2[r1]) < 9 || tq2[r1] == ".") {

					}
				} catch (Exception q1) {
					JOptionPane.showMessageDialog(null, "Amount Should be just numbers");
				}

			}
			for (int r1 = 0; r1 < tq3.length; r1++) {
				try {
					if (Cost22.getText().trim().isEmpty()
							|| Integer.parseInt(tq3[r1]) > 0 && Integer.parseInt(tq3[r1]) < 9) {

					}
				} catch (Exception q1) {
					JOptionPane.showMessageDialog(null, "Amount Should be just numbers");
				}

			}

			try {
				if (!accNumber.getText().trim().isEmpty()) {

					input.add(new SanadKayd(Long.parseLong(sanadKabdNumber.getText()), coinCombo.getValue().toString(),
							sanadKabdDate.getText(), new Accounts(Long.parseLong(accNumber.getText()), Name.getText(),
									Double.parseDouble(Cost.getText()), note.getText())));
					input.get(input.size() - 1).saveSanad();
				}
				if (!accNumber1.getText().trim().isEmpty()) {

					input.add(new SanadKayd(Long.parseLong(sanadKabdNumber.getText()), coinCombo.getValue().toString(),
							sanadKabdDate.getText(), new Accounts(Long.parseLong(accNumber1.getText()), Name1.getText(),
									Double.parseDouble(Cost1.getText()), note1.getText())));

					input.get(input.size() - 1).saveSanad();
				}
				if (!accNumber2.getText().trim().isEmpty()) {

					input.add(new SanadKayd(Long.parseLong(sanadKabdNumber.getText()), coinCombo.getValue().toString(),
							sanadKabdDate.getText(), new Accounts(Long.parseLong(accNumber2.getText()), Name2.getText(),
									Double.parseDouble(Cost2.getText()), note2.getText())));
					input.get(input.size() - 1).saveSanad();
				}

				if (!accNumber22.getText().trim().isEmpty()) {

					input.add(new SanadKayd(Long.parseLong(sanadKabdNumber.getText()), coinCombo.getValue().toString(),
							sanadKabdDate.getText(), new Accounts(Long.parseLong(accNumber22.getText()),
									Name22.getText(), Double.parseDouble(Cost22.getText()), note22.getText())));
					input.get(input.size() - 1).saveSanad();

				}

			} catch (Exception n) {
				JOptionPane.showMessageDialog(null, "The Voucher Saved Before");
			}
		});

		Button printt = new Button("Print");
		printt.setPrefSize(120, 30);
		Button tarhel = new Button("Post");
		tarhel.setOnAction(h -> {

			String rr = "";
			try {
				Scanner inFile = new Scanner(refresh);
				while(inFile.hasNextLine()) {
					rr+= inFile.nextLine()+"\n";
				}
			}
			catch(Exception g) {
				
			}
			try(PrintWriter p = new PrintWriter(refresh)){
				long n=0;
				long n1=0;
				long n2=0;
				long n3=0;
				try {
					n=Long.parseLong(accNumber.getText());
				}
				catch(Exception m) {
					 n=0;
				}
				try {
					n1=Long.parseLong(accNumber1.getText());
				}
				catch(Exception m) {
					 n1=0;
				}
				try {
					n2=Long.parseLong(accNumber2.getText());
				}
				catch(Exception m) {
					 n2=0;
				}
				try {
					n3=Long.parseLong(accNumber22.getText());
				}
				catch(Exception m) {
					 n3=0;
				}
				
				
				if(rr == "") {   // assets and expense  drawings
					if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
					
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+note.getText()+"\n");
					
					}
					else if(n!=0){
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
						
					}
					if((n1>110 && n1<125) || (n1==340) || (n1>509 && n1<551) && n1!=0) {
						
						p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",+"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
					
					}
					else if(n1!=0){
						p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",-"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
						
					}
					if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
						
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
					
					}
					else if(n2!=0){
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
						
					}
					if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
						
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
					
					}
					else if(n3!=0){
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
						
					}
				}
				else {
					p.append(rr);
					if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
						
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
					
					}
					else if(n!=0){
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
						
					}
					if((n1>110 && n1<125) || (n1==340) || (n1>509 && n1<551) && n1!=0) {
						
						p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",+"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
					
					}
					else if(n1!=0){
						p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",-"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
						
					}
					if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
						
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
					
					}
					else if(n2!=0){
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
						
					}
					if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
						
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
					
					}
					else if(n3!=0){
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
						
					}
						
				}
			} catch (FileNotFoundException e1) {
				System.out.println("Creat Refresh file");
			}

		});
		tarhel.setPrefSize(120, 30);
		Button printAndTarhel = new Button("Print And Post");
		printAndTarhel.setPrefSize(150, 30);

		g3.add(save, 0, 0);
		g3.add(tarhel, 1, 0);
		g3.add(printt, 2, 0);
		g3.add(printAndTarhel, 3, 0);
		g3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
		g3.setHgap(140);
		g3.setPadding(new Insets(0, 60, 70, 0));
		g3.setAlignment(Pos.CENTER);
		sanadKabdPane.setBottom(g3);
		root51.getChildren().add(sanadKabdPane);

		ImageView iamg13a5 = new ImageView("F1.jpg");
		HBox hsb13a5 = new HBox();
		// hsb13a5.setPrefHeight(30);
		hsb13a5.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		hsb13a5.getChildren().addAll(iamg13a5);
		px1.setBottom(hsb13a5);

		return selx1;

	}

	public static Scene kabd1(String sanadnum, Stage primaryStage) throws Exception {

		BorderPane px = new BorderPane();
		Scene selx = new Scene(px, 1540, 800);

		ImageView back1 = new ImageView("dsBuffer.bmp.png");
		back1.setPreserveRatio(true);
		Button bk1 = new Button("Back", back1);
		bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
		Label logon151 = new Label("                                  Receipt Voucher                     ");
		logon151.setFont(Font.font(35));

		bk1.setOnAction(ss -> {
			primaryStage.setScene(scene1);
			primaryStage.show();
		});
		ImageView limg5 = new ImageView("Logout-icon.png");

		Button out5 = new Button("LogOut", limg5);
		out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
		out5.setOnAction(t -> {
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		});

		HBox hh15 = new HBox();

		hh15.setSpacing(228);
		hh15.setMaxSize(2100, 50);
		hh15.getChildren().addAll(bk1, logon151, out5);
		hh15.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
		px.setTop(hh15);

		StackPane root51 = new StackPane();

		Image img5 = new Image("PM316.jpg");
		BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background bGround5 = new Background(bImg5);
		root51.setBackground(bGround5);
		px.setCenter(root51);
		String[] cointxt = { "ILS", "USD", "JOD" };
		ComboBox coinCombo = new ComboBox(FXCollections.observableArrayList(cointxt));
		coinCombo.setStyle("-fx-font-size: 10pt;-fx-font-weight: bold;");
		coinCombo.setPromptText("Select Currency");

		TextField status = new TextField();
		status.setPrefColumnCount(6);
		status.setText("New");
		status.setDisable(true);

		Rectangle ttop = new Rectangle(400, 40, 1100, 200);
		ttop.setStroke(Color.BLACK);
		ttop.setFill(Color.TRANSPARENT);
		StackPane santop = new StackPane();
		santop.setPadding(new Insets(33, 160, 0, 0));

		BorderPane sanadKabdPane = new BorderPane();
		HBox hAll = new HBox(40);
		hAll.setAlignment(Pos.CENTER);
		hAll.setPadding(new Insets(25, 10, 10, 0));
		GridPane g1 = new GridPane();
		g1.setHgap(15);
		g1.setVgap(20);
		HBox h2 = new HBox(7.5);
		HBox h3 = new HBox(10);
		TextField sanadKabdNumber = new TextField();
		sanadKabdNumber.setPrefWidth(30);
		TextField sanadKabdDate = new TextField();
		TextField ssa = new TextField();
		h2.getChildren().addAll(new Label("Voucher Date : "), sanadKabdDate);
		g1.add(new Label("Voucher Number : "), 0, 0);
		g1.add(sanadKabdNumber, 1, 0);
		g1.add(new Label("Coin"), 0, 1);
		g1.add(coinCombo, 1, 1);
		g1.add(new Label("Bill Number : "), 0, 2);
		g1.add(ssa, 1, 2);
		Button fin = new Button("Find Bill");
		g1.add(fin, 2, 2);
		h3.getChildren().addAll(new Label(" Status "), status);
		g1.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
		h3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
		hAll.getChildren().addAll(g1, h2, h3);
		h2.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");

		sanadKabdPane.setTop(santop);
		santop.getChildren().addAll(ttop, hAll);
		/// Top ^
		TextField accNumber = new TextField();
		accNumber.setDisable(true);
		accNumber.setText("111");
		TextField Name = new TextField();
		Name.setDisable(true);
		Name.setText("Cash");
		TextField Cost = new TextField();
		TextField note = new TextField();

		TextField accNumber2 = new TextField();
		accNumber2.setDisable(true);
		accNumber2.setText("410");
		TextField Name2 = new TextField();
		Name2.setDisable(true);
		Name2.setText("Revenue");
		TextField Cost2 = new TextField();
		TextField note2 = new TextField();

		TextField accNumber22 = new TextField();
		accNumber22.setDisable(true);
		accNumber22.setText("220");
		TextField Name22 = new TextField();
		Name22.setDisable(true);
		Name22.setText("Value Added tax Provision");
		TextField Cost22 = new TextField();
		TextField note22 = new TextField();

		Cost.setText(String.valueOf(ss + ss1));
		Cost.setDisable(true);

		Cost2.setText(String.valueOf(ss1));
		Cost2.setDisable(true);

		Cost22.setText(String.valueOf(ss));
		Cost22.setDisable(true);

		Rectangle tcent = new Rectangle(400, 130, 1100, 360);
		tcent.setStroke(Color.BLACK);
		tcent.setFill(Color.TRANSPARENT);
		StackPane stcent = new StackPane();
		stcent.setPadding(new Insets(0, 160, 0, 0));
		GridPane g2 = new GridPane();
		g2.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
		g2.setPadding(new Insets(30, 0, 0, 0));
		g2.setHgap(45);
		g2.setVgap(10);
		Label mm3 = new Label("Debit :");
		mm3.setStyle("-fx-font-size: 15pt;-fx-font-weight: bold;");
		mm3.setUnderline(true);
		g2.add(mm3, 0, 0);
		g2.add(new Label("Account Number"), 0, 1);
		g2.add(new Label("Name"), 1, 1);
		g2.add(new Label("Amount"), 2, 1);
		g2.add(new Label("Note"), 3, 1);

		g2.add(accNumber, 0, 2);
		g2.add(Name, 1, 2);
		g2.add(Cost, 2, 2);
		g2.add(note, 3, 2);
		Label mm = new Label("Credit :");
		mm.setUnderline(true);
		mm.setStyle("-fx-font-size: 15pt;-fx-font-weight: bold;");
		g2.add(mm, 0, 3);
		g2.add(new Label("Account Number"), 0, 4);
		g2.add(new Label("Name"), 1, 4);
		g2.add(new Label("Amount"), 2, 4);
		g2.add(new Label("Note"), 3, 4);

		g2.add(accNumber2, 0, 5);
		g2.add(Name2, 1, 5);
		g2.add(Cost2, 2, 5);
		g2.add(note2, 3, 5);

		g2.add(accNumber22, 0, 6);
		g2.add(Name22, 1, 6);
		g2.add(Cost22, 2, 6);
		g2.add(note22, 3, 6);
		Button bst = new Button(" Check Status ");
		bst.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
		g2.add(bst, 0, 7);
		Label gr = new Label("Balanced");
		Label gr2 = new Label("Unbalanced");

		bst.setOnAction(w -> {
			double summ = Double.parseDouble(Cost2.getText()) + Double.parseDouble(Cost22.getText());
			double dep = Double.parseDouble(Cost.getText());
			if (summ == dep) {
				gr2.setTextFill(Color.WHITE);
				gr2.setFont(Font.font(0.1));

				gr.setTextFill(Color.GREEN);
				gr.setFont(Font.font(15));

				g2.add(gr, 1, 7);
			} else if (summ != dep) {
				gr.setTextFill(Color.WHITE);
				gr.setFont(Font.font(0.1));
				gr2.setFont(Font.font(15));
				gr2.setTextFill(Color.RED);
				g2.add(gr2, 1, 7);
			}
		});

		g2.setAlignment(Pos.TOP_CENTER);
		stcent.getChildren().addAll(tcent, g2);
		sanadKabdPane.setCenter(stcent);

		// center ^

		for (int i = 0; i < input.size(); i++) {
			if (sanadnum.equalsIgnoreCase(String.valueOf(input.get(i).getSanadNumber()))) {
				sanadKabdDate.setText(input.get(i).getDate());

				sanadKabdDate.setDisable(true);

				coinCombo.setDisable(true);
				status.setText("Posted");
				status.setDisable(true);
				ssa.setText(String.valueOf(input.get(i).getFatoraNumber()));
				ssa.setDisable(true);
				sanadKabdNumber.setText(sanadnum);
				sanadKabdNumber.setDisable(true);
				note.setText(input.get(i).getNote());
				note2.setText(input.get(i).getNote());
				note22.setText(input.get(i).getNote());
				note.setDisable(true);
				note2.setDisable(true);
				note22.setDisable(true);

			}

		}
		for (int i = 0; i < billNumber.size(); i++) {
			if (ssa.getText().equalsIgnoreCase(billNumber.get(i).getBillnum())) {
				coinCombo.setValue(billNumber.get(i).getType());
				Cost.setText(billNumber.get(i).getShamel());

				Cost2.setText(billNumber.get(i).getUnshamel());
				Cost22.setText(billNumber.get(i).getVat());
				Cost2.setDisable(true);
				Cost22.setDisable(true);
				Cost.setDisable(true);
			}

		}

		GridPane g3 = new GridPane();

		Button save = new Button("Save");
		save.setOnAction(f -> {
			try {
				if (!accNumber.getText().trim().isEmpty()) {

					input.add(new SanadKabd(Long.parseLong(sanadKabdNumber.getText()), coinCombo.getValue().toString(),
							Long.parseLong(ssa.getText()), sanadKabdDate.getText(),
							new Accounts(Long.parseLong(accNumber.getText()), Name.getText(),
									Double.parseDouble(Cost.getText()), note.getText())));
					input.get(input.size() - 1).saveSanad();
				}

				if (!accNumber2.getText().trim().isEmpty()) {

					input.add(new SanadKabd(Long.parseLong(sanadKabdNumber.getText()), coinCombo.getValue().toString(),
							Long.parseLong(ssa.getText()), sanadKabdDate.getText(),
							new Accounts(Long.parseLong(accNumber2.getText()), Name2.getText(),
									Double.parseDouble(Cost2.getText()), note2.getText())));
					input.get(input.size() - 1).saveSanad();
				}

				if (!accNumber22.getText().trim().isEmpty()) {
					input.add(new SanadKabd(Long.parseLong(sanadKabdNumber.getText()), coinCombo.getValue().toString(),
							Long.parseLong(ssa.getText()), sanadKabdDate.getText(),
							new Accounts(Long.parseLong(accNumber22.getText()), Name22.getText(),
									Double.parseDouble(Cost22.getText()), note22.getText())));
					input.get(input.size() - 1).saveSanad();

				}

			} catch (Exception n) {
				JOptionPane.showMessageDialog(null, "The Voucher Saved Before");
			}
		});
		save.setPrefSize(120, 30);
		Button printt = new Button("Print");
		printt.setPrefSize(120, 30);
		Button tarhel = new Button("Post");
		tarhel.setOnAction(h -> {

			String rr = "";
			try {
				Scanner inFile = new Scanner(refresh);
				while(inFile.hasNextLine()) {
					rr+= inFile.nextLine()+"\n";
				}
			}
			catch(Exception g) {
				
			}
			try(PrintWriter p = new PrintWriter(refresh)){
				long n=0;
				
				long n2=0;
				long n3=0;
				try {
					n=Long.parseLong(accNumber.getText());
				}
				catch(Exception m) {
					 n=0;
				}
				
				try {
					n2=Long.parseLong(accNumber2.getText());
				}
				catch(Exception m) {
					 n2=0;
				}
				try {
					n3=Long.parseLong(accNumber22.getText());
				}
				catch(Exception m) {
					 n3=0;
				}
				
				
				if(rr == "") {   // assets and expense  drawings
					if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
					
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+note.getText()+"\n");
					
					}
					else if(n!=0){
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+note.getText()+"\n");
						
					}
				
					if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
						
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+note2.getText()+"\n");
					
					}
					else if(n2!=0){
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+note2.getText()+"\n");
						
					}
					if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
						
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+note22.getText()+"\n");
					
					}
					else if(n3!=0){
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+note22.getText()+"\n");
						
					}
				}
				else {
					p.append(rr);
					if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
						
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
					
					}
					else if(n!=0){
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
						
					}
				
					if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
						
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
					
					}
					else if(n2!=0){
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
						
					}
					if((n3>110 && n3<125) || (n3==340) || (n3>509 && n3<551) && n3!=0) {
						
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",+"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
					
					}
					else if(n3!=0){
						p.append(accNumber22.getText()+","+sanadKabdNumber.getText()+",-"+Cost22.getText()+","+Name22.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note22.getText()+"\n");
						
					}
						
				}
			} catch (FileNotFoundException e1) {
				System.out.println("Creat Refresh file");
			}

		});
		tarhel.setPrefSize(120, 30);
		Button printAndTarhel = new Button("Print And Post");
		printAndTarhel.setPrefSize(150, 30);

		g3.add(save, 0, 0);
		g3.add(tarhel, 1, 0);
		g3.add(printt, 2, 0);
		g3.add(printAndTarhel, 3, 0);
		g3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
		g3.setHgap(140);
		g3.setPadding(new Insets(0, 60, 20, 0));
		g3.setAlignment(Pos.CENTER);
		sanadKabdPane.setBottom(g3);
		root51.getChildren().add(sanadKabdPane);

		ImageView iamg13a5 = new ImageView("F1.jpg");
		HBox hsb13a5 = new HBox();
		// hsb13a5.setPrefHeight(30);
		hsb13a5.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		hsb13a5.getChildren().addAll(iamg13a5);
		px.setBottom(hsb13a5);

		return selx;

	}

	public static Scene sarf1(String sanadnum, Stage primaryStage) throws Exception {

		BorderPane px2 = new BorderPane();
		Scene selx2 = new Scene(px2, 1540, 800);

		ImageView back1 = new ImageView("dsBuffer.bmp.png");
		back1.setPreserveRatio(true);
		Button bk1 = new Button("Back", back1);
		bk1.setStyle("-fx-font-size: 17pt;-fx-background-color:skyblue;-fx-font-weight: bold;");
		Label logon151 = new Label("                                  Payment Voucher                   ");
		logon151.setFont(Font.font(35));

		bk1.setOnAction(ss -> {
			primaryStage.setScene(scene1);
			primaryStage.show();
		});
		ImageView limg5 = new ImageView("Logout-icon.png");

		Button out5 = new Button("LogOut", limg5);
		out5.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;-fx-background-color:skyblue;");
		out5.setOnAction(t -> {
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		});

		HBox hh15 = new HBox();

		hh15.setSpacing(228);
		hh15.setMaxSize(2100, 50);
		hh15.getChildren().addAll(bk1, logon151, out5);
		hh15.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		logon151.setFont(Font.font(null, FontWeight.BOLD, logon151.getFont().getSize()));
		px2.setTop(hh15);

		StackPane root51 = new StackPane();

		Image img5 = new Image("PM316.jpg");
		BackgroundImage bImg5 = new BackgroundImage(img5, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background bGround5 = new Background(bImg5);
		root51.setBackground(bGround5);
		px2.setCenter(root51);
		String[] cointxt = { "ILS", "USD", "JOD" };
		ComboBox coinCombo = new ComboBox(FXCollections.observableArrayList(cointxt));
		coinCombo.setStyle("-fx-font-size: 10pt;-fx-font-weight: bold;");
		coinCombo.setPromptText("Select Currency");
		TextField status = new TextField();
		status.setPrefColumnCount(6);
		status.setText("New");
		status.setDisable(true);

		Rectangle ttop = new Rectangle(400, 40, 1100, 200);
		ttop.setStroke(Color.BLACK);
		ttop.setFill(Color.TRANSPARENT);
		StackPane santop = new StackPane();
		santop.setPadding(new Insets(33, 160, 0, 0));

		BorderPane sanadKabdPane = new BorderPane();
		HBox hAll = new HBox(40);
		hAll.setAlignment(Pos.CENTER);
		hAll.setPadding(new Insets(25, 10, 10, 0));
		GridPane g1 = new GridPane();
		g1.setHgap(10);
		g1.setVgap(20);
		HBox h2 = new HBox(7.5);
		HBox h3 = new HBox(15);
		TextField sanadKabdNumber = new TextField();
		TextField sanadKabdDate = new TextField();
		TextField xs = new TextField();
		h2.getChildren().addAll(new Label("Voucher Date : "), sanadKabdDate);
		g1.add(new Label("Voucher Number : "), 0, 0);
		g1.add(sanadKabdNumber, 1, 0);
		g1.add(new Label("Coin"), 0, 1);
		g1.add(coinCombo, 1, 1);
		g1.add(new Label("Bill Number : "), 0, 2);
		g1.add(xs, 1, 2);
		Button fin = new Button("Find Bill");
		g1.add(fin, 2, 2);

		h3.getChildren().addAll(new Label(" Status "), status);
		g1.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
		h3.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
		hAll.getChildren().addAll(g1, h2, h3);
		h2.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");

		sanadKabdPane.setTop(santop);
		santop.getChildren().addAll(ttop, hAll);
		/// Top ^
		TextField accNumber = new TextField();
		accNumber.setDisable(true);
		TextField Name = new TextField();
		TextField Cost = new TextField();
		Name.setDisable(true);
		TextField note = new TextField();

		TextField accNumber1 = new TextField();
		accNumber1.setDisable(true);
		TextField Name1 = new TextField();
		Name1.setDisable(true);
		TextField Cost1 = new TextField();
		TextField note1 = new TextField();

		TextField accNumber2 = new TextField();
		TextField Name2 = new TextField();
		TextField Cost2 = new TextField();
		TextField note2 = new TextField();

		Rectangle tcent = new Rectangle(400, 130, 1100, 360);
		tcent.setStroke(Color.BLACK);
		tcent.setFill(Color.TRANSPARENT);
		StackPane stcent = new StackPane();
		stcent.setPadding(new Insets(0, 160, 0, 0));
		GridPane g2 = new GridPane();
		g2.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
		g2.setPadding(new Insets(30, 0, 0, 0));
		g2.setHgap(45);
		g2.setVgap(10);
		Label mm3 = new Label("Debit :");
		mm3.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
		mm3.setUnderline(true);
		g2.add(mm3, 0, 0);
		g2.add(new Label("Account Number"), 0, 1);

		g2.add(new Label("Account Name"), 1, 1);

		g2.add(new Label("Amount"), 2, 1);
		g2.add(new Label("Note"), 3, 1);

		g2.add(accNumber, 0, 2);
		
		accNumber.setDisable(true);
		g2.add(Name, 1, 2);
		
		g2.add(Cost, 2, 2);
		g2.add(note, 3, 2);

		g2.add(accNumber1, 0, 3);
		accNumber1.setText("220");
		g2.add(Name1, 1, 3);
		Name1.setText("Value Added tax Provision");
		g2.add(Cost1, 2, 3);
		g2.add(note1, 3, 3);

		Cost.setDisable(true);
		Cost1.setDisable(true);
		Cost2.setDisable(true);

		Label mm = new Label("Credit :");
		mm.setUnderline(true);
		mm.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold;");
		g2.add(mm, 0, 4);
		g2.add(new Label("Account Number"), 0, 5);
		g2.add(new Label("Name"), 1, 5);
		g2.add(new Label("Amount"), 2, 5);
		g2.add(new Label("Note"), 3, 5);

		g2.add(accNumber2, 0, 6);
		accNumber2.setText("111");
		accNumber2.setDisable(true);
		Name2.setText("Cash");
		Name2.setDisable(true);
		g2.add(Name2, 1, 6);
		g2.add(Cost2, 2, 6);
		g2.add(note2, 3, 6);

		Button bst = new Button(" Check Status ");
		bst.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
		g2.add(bst, 0, 8);
		Label gr = new Label("Balanced");
		Label gr2 = new Label("Unbalanced");

		for (int i = 0; i < input.size(); i++) {
			if (sanadnum.equalsIgnoreCase(String.valueOf(input.get(i).getSanadNumber()))) {
				sanadKabdDate.setText(input.get(i).getDate());

				sanadKabdDate.setDisable(true);
			//	coinCombo.setValue(billNumber2.get(i).getType());
				coinCombo.setDisable(true);
				status.setText("Posted");
				status.setDisable(true);
				xs.setText(String.valueOf(input.get(i).getFatoraNumber()));
				xs.setDisable(true);
				sanadKabdNumber.setText(sanadnum);
				sanadKabdNumber.setDisable(true);
				note.setText(input.get(i).getNote());
				note2.setText(input.get(i).getNote());
				note1.setText(input.get(i).getNote());
				note.setDisable(true);
				note2.setDisable(true);
				note1.setDisable(true);

			}

		}
		for (int i = 0; i < billNumber2.size(); i++) {
			if (xs.getText().equalsIgnoreCase(billNumber2.get(i).getBillnum())) {
				coinCombo.setValue(billNumber2.get(i).getType());
				Cost.setText(billNumber2.get(i).getUnshamel());
				Cost1.setText(billNumber2.get(i).getVat());
				Cost2.setText(billNumber2.get(i).getShamel());
				Cost2.setDisable(true);
				Cost1.setDisable(true);
				Cost.setDisable(true);
			}

		}

		bst.setOnAction(w -> {
			double summ = Double.parseDouble(Cost.getText()) + Double.parseDouble(Cost1.getText());
			double dep = Double.parseDouble(Cost2.getText());
			if (dep == summ) {
				gr2.setTextFill(Color.WHITE);
				gr2.setFont(Font.font(0.1));

				gr.setTextFill(Color.GREEN);
				gr.setFont(Font.font(15));

				g2.add(gr, 1, 8);
			} else if (dep != summ) {
				gr.setTextFill(Color.WHITE);
				gr.setFont(Font.font(0.1));
				gr2.setFont(Font.font(15));
				gr2.setTextFill(Color.RED);
				g2.add(gr2, 1, 8);
			}
		});

		g2.setAlignment(Pos.TOP_CENTER);
		stcent.getChildren().addAll(tcent, g2);
		sanadKabdPane.setCenter(stcent);

		// center ^

		GridPane g3 = new GridPane();

		Button save = new Button("Save");
		save.setOnAction(f -> {

			String tq[] = Cost.getText().split("");
			String tq1[] = Cost1.getText().split("");
			String tq2[] = Cost2.getText().split("");

			for (int r = 0; r < tq.length; r++) {
				try {
					if (Cost.getText().trim().isEmpty() || Integer.parseInt(tq[r]) > 0 && Integer.parseInt(tq[r]) < 9
							|| tq[r] == ".") {

					}
				}

				catch (Exception q) {
					JOptionPane.showMessageDialog(null, "Amount Should be just numbers");
				}
			}
			for (int r1 = 0; r1 < tq1.length; r1++) {
				try {
					if (Cost1.getText().trim().isEmpty()
							|| Integer.parseInt(tq1[r1]) > 0 && Integer.parseInt(tq1[r1]) < 9 || tq1[r1] == ".") {

					}
				} catch (Exception q1) {
					JOptionPane.showMessageDialog(null, "Amount Should be just numbers");
				}

			}

			for (int r1 = 0; r1 < tq2.length; r1++) {
				try {
					if (Cost2.getText().trim().isEmpty()
							|| Integer.parseInt(tq2[r1]) > 0 && Integer.parseInt(tq2[r1]) < 9 || tq2[r1] == ".") {

					}
				} catch (Exception q1) {
					JOptionPane.showMessageDialog(null, "Amount Should be just numbers");
				}

			}

			try {
				if (!accNumber.getText().trim().isEmpty()) {

					input.add(new SanadSarf(Long.parseLong(sanadKabdNumber.getText()), coinCombo.getValue().toString(),
							Long.parseLong(xs.getText()), sanadKabdDate.getText(),
							new Accounts(Long.parseLong(accNumber.getText()), Name.getText(),
									Double.parseDouble(Cost.getText()), note.getText())));
					input.get(input.size() - 1).saveSanad();
				}
				if (!accNumber1.getText().trim().isEmpty()) {

					input.add(new SanadSarf(Long.parseLong(sanadKabdNumber.getText()), coinCombo.getValue().toString(),
							Long.parseLong(xs.getText()), sanadKabdDate.getText(),
							new Accounts(Long.parseLong(accNumber1.getText()), Name1.getText(),
									Double.parseDouble(Cost1.getText()), note1.getText())));

					input.get(input.size() - 1).saveSanad();
				}
				if (!accNumber2.getText().trim().isEmpty()) {

					input.add(new SanadSarf(Long.parseLong(sanadKabdNumber.getText()), coinCombo.getValue().toString(),
							Long.parseLong(xs.getText()), sanadKabdDate.getText(),
							new Accounts(Long.parseLong(accNumber2.getText()), Name2.getText(),
									Double.parseDouble(Cost2.getText()), note2.getText())));
					input.get(input.size() - 1).saveSanad();
				}

			} catch (Exception n) {
				JOptionPane.showMessageDialog(null, "The Voucher Saved Before");
			}
		});
		save.setPrefSize(120, 30);
		Button printt = new Button("Print");
		printt.setPrefSize(120, 30);
		Button tarhel = new Button("Post");
		tarhel.setOnAction(h -> {

			String rr = "";
			try {
				Scanner inFile = new Scanner(refresh);
				while(inFile.hasNextLine()) {
					rr+= inFile.nextLine()+"\n";
				}
			}
			catch(Exception g) {
				
			}
			try(PrintWriter p = new PrintWriter(refresh)){
				long n=0;
				long n1=0;
				long n2=0;
				
				try {
					n=Long.parseLong(accNumber.getText());
				}
				catch(Exception m) {
					 n=0;
				}
				try {
					n1=Long.parseLong(accNumber1.getText());
				}
				catch(Exception m) {
					 n1=0;
				}
				try {
					n2=Long.parseLong(accNumber2.getText());
				}
				catch(Exception m) {
					 n2=0;
				}
				
				
				if(rr == "") {   // assets and expense  drawings
					if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
					
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
					
					}
					else if(n!=0){
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
						
					}
					if((n1>110 && n1<125) || (n1==340) || (n1>509 && n1<551) && n1!=0) {
						
						p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",+"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
					
					}
					else if(n1!=0){
						p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",-"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
						
					}
					if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
						
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
					
					}
					else if(n2!=0){
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
						
					}
				
				}
				else {
					p.append(rr);
					if((n>110 && n<125) || (n==340) || (n>509 && n<551) && n!=0) {
						
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",+"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
					
					}
					else if(n!=0){
						p.append(accNumber.getText()+","+sanadKabdNumber.getText()+",-"+Cost.getText()+","+Name.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note.getText()+"\n");
						
					}
					if((n1>110 && n1<125) || (n1==340) || (n1>509 && n1<551) && n1!=0) {
						
						p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",+"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
					
					}
					else if(n1!=0){
						p.append(accNumber1.getText()+","+sanadKabdNumber.getText()+",-"+Cost1.getText()+","+Name1.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note1.getText()+"\n");
						
					}
					if((n2>110 && n2<125) || (n2==340) || (n2>509 && n2<551) && n2!=0) {
						
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",+"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
					
					}
					else if(n2!=0){
						p.append(accNumber2.getText()+","+sanadKabdNumber.getText()+",-"+Cost2.getText()+","+Name2.getText()+","+sanadKabdDate.getText()+","+coinCombo.getValue()+","+note2.getText()+"\n");
						
					}
					
						
				}
			} catch (FileNotFoundException e1) {
				System.out.println("Creat Refresh file");
			}

		});
		tarhel.setPrefSize(120, 30);
		Button printAndTarhel = new Button("Print And Post");
		printAndTarhel.setPrefSize(150, 30);

		g3.add(save, 0, 0);
		g3.add(tarhel, 1, 0);
		g3.add(printt, 2, 0);
		g3.add(printAndTarhel, 3, 0);
		g3.setStyle("-fx-font-size: 12pt;-fx-font-weight: bold;");
		g3.setHgap(140);
		g3.setPadding(new Insets(0, 60, 20, 0));
		g3.setAlignment(Pos.CENTER);
		sanadKabdPane.setBottom(g3);
		root51.getChildren().add(sanadKabdPane);

		ImageView iamg13a5 = new ImageView("F1.jpg");
		HBox hsb13a5 = new HBox();
		// hsb13a5.setPrefHeight(30);
		hsb13a5.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		hsb13a5.getChildren().addAll(iamg13a5);
		px2.setBottom(hsb13a5);

		return selx2;

	}

	public static Scene bake() {

		return scene;

	}

	public static void changeCoin(String before, String after) throws Exception {
		File f = Inputs.sanadsFile;
		String ok = "";
		try {
			Scanner in = new Scanner(f);
			while (in.hasNextLine()) {
				String[] s = in.nextLine().split(",");
				// s[3] have coin name
				if (before.equalsIgnoreCase("USD") && after.equalsIgnoreCase("ILS")) {
					ok += s[0] + "," + s[1] + "," + s[2] + "," + "ILS," + s[4] + "," + s[5] + "," + s[6] + ","
							+ Accounts.fromUsdToIls(Math.round(Double.parseDouble(s[7]) * 100.0)) / 100.0 + "\n";
				}
				if (before.equalsIgnoreCase("ILS") && after.equalsIgnoreCase("USD")) {
					ok += s[0] + "," + s[1] + "," + s[2] + "," + "USD," + s[4] + "," + s[5] + "," + s[6] + ","
							+ Accounts.fromIlsToUsd(Math.round(Double.parseDouble(s[7]) * 100.0)) / 100.0 + "\n";
				}
				// jod ILS
				if (before.equalsIgnoreCase("JOD") && after.equalsIgnoreCase("ILS")) {
					ok += s[0] + "," + s[1] + "," + s[2] + "," + "ILS," + s[4] + "," + s[5] + "," + s[6] + ","
							+ Accounts.fromJodToIls(Math.round(Double.parseDouble(s[7]) * 100.0)) / 100.0 + "\n";
				}
				if (before.equalsIgnoreCase("ILS") && after.equalsIgnoreCase("JOD")) {
					ok += s[0] + "," + s[1] + "," + s[2] + "," + "JOD," + s[4] + "," + s[5] + "," + s[6] + ","
							+ Accounts.fromIlsToJod(Math.round(Double.parseDouble(s[7]) * 100.0)) / 100.0 + "\n";
				}
				// JOD USD
				if (before.equalsIgnoreCase("JOD") && after.equalsIgnoreCase("USD")) {
					ok += s[0] + "," + s[1] + "," + s[2] + "," + "USD," + s[4] + "," + s[5] + "," + s[6] + ","
							+ Accounts.fromJodToUsd(Math.round(Double.parseDouble(s[7]) * 100.0)) / 100.0 + "\n";
				}
				if (before.equalsIgnoreCase("USD") && after.equalsIgnoreCase("JOD")) {
					ok += s[0] + "," + s[1] + "," + s[2] + "," + "JOD," + s[4] + "," + s[5] + "," + s[6] + ","
							+ Accounts.fromUsdToJod(Math.round(Double.parseDouble(s[7]) * 100.0)) / 100.0 + "\n";
				}
			}
		} catch (Exception e) {

		}
		try (PrintWriter p = new PrintWriter(f)) {
			p.append(ok);
		}
		String ok1 = "";
		try {
			char temp;
			Scanner inn = new Scanner(refresh);
			while (inn.hasNextLine()) {
				String[] s = inn.nextLine().split(",");
				temp = s[2].charAt(0);
				s[2] = s[2].replaceAll("[+-]", "");

				if (before.equalsIgnoreCase("USD") && after.equalsIgnoreCase("ILS")) {
					ok1 += s[0] + "," + s[1] + "," + temp
							+ Accounts.fromUsdToIls(Math.round(Double.parseDouble(s[2]) * 100.0)) / 100.0 + "," + s[3]
							+ "," + s[4] + "," + "ILS," + "\n";
				}
				if (before.equalsIgnoreCase("ILS") && after.equalsIgnoreCase("USD")) {
					ok1 += s[0] + "," + s[1] + "," + temp
							+ Accounts.fromIlsToUsd(Math.round(Double.parseDouble(s[2]) * 100.0)) / 100.0 + "," + s[3]
							+ "," + s[4] + "," + "USD," + "\n";
				}
				// jod ILS
				if (before.equalsIgnoreCase("JOD") && after.equalsIgnoreCase("ILS")) {
					ok1 += s[0] + "," + s[1] + "," + temp
							+ Accounts.fromJodToIls(Math.round(Double.parseDouble(s[2]) * 100.0)) / 100.0 + "," + s[3]
							+ "," + s[4] + "," + "ILS," + "\n";
				}
				if (before.equalsIgnoreCase("ILS") && after.equalsIgnoreCase("JOD")) {
					ok1 += s[0] + "," + s[1] + "," + temp
							+ Accounts.fromIlsToJod(Math.round(Double.parseDouble(s[2]) * 100.0)) / 100.0 + "," + s[3]
							+ "," + s[4] + "," + "JOD," + "\n";
				}
				// JOD USD
				if (before.equalsIgnoreCase("JOD") && after.equalsIgnoreCase("USD")) {
					ok1 += s[0] + "," + s[1] + "," + temp
							+ Accounts.fromJodToUsd(Math.round(Double.parseDouble(s[2]) * 100.0)) / 100.0 + "," + s[3]
							+ "," + s[4] + "," + "USD," + "\n";
				}
				if (before.equalsIgnoreCase("USD") && after.equalsIgnoreCase("JOD")) {
					ok1 += s[0] + "," + s[1] + "," + temp
							+ Accounts.fromUsdToJod(Math.round(Double.parseDouble(s[2]) * 100.0)) / 100.0 + "," + s[3]
							+ "," + s[4] + "," + "JOD," + "\n";
				}
			}
		} catch (Exception e) {
			System.out.println("Error");
		}

		try (PrintWriter p = new PrintWriter(refresh)) {
			p.append(ok1);
		}
	}
}