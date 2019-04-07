package sample;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller{
    private byte turn = 1;
    boolean begin_player;
    boolean remis = false;
    @FXML
    Button b00, b01, b02, b10, b11, b12, b20, b21, b22;
    Button buttony[][]= new Button[3][3];
    @FXML
    CheckBox zaczyna_gracz;
    @FXML
    FlowPane result_pane;
    @FXML
    Label who_win;
    @FXML
    HBox minimize_hbox, exit_hbox;
    @FXML
    BorderPane border_pane;
    @FXML
    FontAwesomeIconView close_icon;
    @FXML
    FontAwesomeIconView minimize_icon;

    double x;
    double y;
    String style, style_icon;

    static Stage stage;
    @FXML
    void dragged(MouseEvent event){

        stage.setOpacity(0.95);

        stage.setX(event.getScreenX()-x);
        stage.setY(event.getScreenY()-y);


    }
    @FXML
    void pressed(MouseEvent event){
        x = event.getSceneX();
        y = event.getSceneY();

        Main.root.setStyle(Main.rootStyle+"-fx-cursor:CLOSED_HAND;");
    }
    @FXML
    void released(){
            stage.setOpacity(1);
            Main.root.setStyle(Main.rootStyle);
    }

    @FXML
    void exit_clicked(){
        stage.close();
    }
    @FXML
    void minimize(){
        stage.setIconified(true);
    }
    HBox hbox_from_source;
    @FXML
    void mousemoved(MouseEvent event){
    Object source = event.getSource();
    hbox_from_source = (HBox)source;

    if(hbox_from_source.equals(minimize_hbox)){
        hbox_from_source.setStyle("-fx-background-color: #FFC107; -fx-cursor:hand");
        minimize_icon.setStyle("-fx-fill: #212121");
    }

    else if(hbox_from_source.equals(exit_hbox)){
        hbox_from_source.setStyle("-fx-background-color: #B71C1C; -fx-cursor:hand");
        close_icon.setStyle("-fx-fill: #212121");
    }

    }
    @FXML
    void exited(){

        if(hbox_from_source.equals(minimize_hbox)){
            minimize_hbox.setStyle(style);
            minimize_icon.setStyle(style_icon);
        }
        else if(hbox_from_source.equals(exit_hbox)){
            exit_hbox.setStyle(style);
            close_icon.setStyle(style_icon);
        }

    }
    @FXML
    void initialize(){
        buttony[0][0] = b00;    buttony[1][0] = b10;    buttony[2][0] = b20;
        buttony[0][1] = b01;    buttony[1][1] = b11;    buttony[2][1] = b21;
        buttony[0][2] = b02;    buttony[1][2] = b12;    buttony[2][2] = b22;

        result_pane.setVisible(false);

        if(zaczyna_gracz.isSelected())
            begin_player=true;
        else
            begin_player=false;

        if(!begin_player)
            ruch_komputera(turn);

        style = border_pane.getStyle();
        style_icon = minimize_icon.getStyle();
       // System.out.println(style);
    }

    @FXML
    void restart(){
        result_pane.setVisible(false);
        clear_board(buttony);
        turn=1;
        if(zaczyna_gracz.isSelected())
            begin_player=true;
        else
            begin_player=false;

        if(!begin_player)
            ruch_komputera(turn);
    }
    void clear_board(Button[][] b){
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++){
                b[i][j].setText("");
                b[i][j].setDisable(false);
                b[i][j].setTextFill(Color.web("#ffc107"));
            }

    }
    void block_board(Button[][] b){
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(!b[i][j].isDisable())
                    b[i][j].setDisable(true);
    }
    Button return_free_corner(){

        for(int i=0;i<3;i=i+2)
            for(int j=0;j<3;j=j+2){
                if(buttony[i][j].getText().equals("")){
                    return buttony[i][j];
                }
            }
            return null;
    }
    Button return_free_space(){
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(buttony[i][j].getText().equals(""))
                    return buttony[i][j];

                return null;
    }
    Button temp_button = new Button();
    void ruch_komputera(int turn){

        if(!begin_player && turn==1){
            buttony[1][1].setText("O");
            buttony[1][1].setDisable(true);
        }

        else if(chech("O",2)!=null){
            temp_button = chech("O",2);
            temp_button.setText("O");
           temp_button.setDisable(true);
        }
        else if(turn>=2 && chech("X",2)!=null){
            temp_button = chech("X", 2);
            temp_button.setText("O");
            temp_button.setDisable(true);
        }
        else if(buttony[1][1].getText().equals("")){
            buttony[1][1].setText("O");
            buttony[1][1].setDisable(true);
        }
        else if((buttony[0][0].getText().equals("X") && buttony[2][2].getText().equals("X"))&&buttony[0][1].getText().equals("")){
            buttony[0][1].setText("O");
            buttony[0][1].setDisable(true);
        }
        else if((buttony[0][0].getText().equals("X") && buttony[2][2].getText().equals("X"))&&buttony[0][1].getText().equals("")){
            buttony[0][1].setText("O");
            buttony[0][1].setDisable(true);
        }
        else if((buttony[0][2].getText().equals("X") && buttony[2][0].getText().equals("X"))&&buttony[2][1].getText().equals("")){
            buttony[2][1].setText("O");
            buttony[2][1].setDisable(true);
        }
        else if(return_free_corner()!=null){
            temp_button = return_free_corner();
            temp_button.setText("O");
           temp_button.setDisable(true);
        }
        else if(return_free_space()!=null){
            temp_button = return_free_space();
            temp_button.setText("O");
            temp_button.setDisable(true);

            if(!begin_player){
                remis=true;
            }
        }
        else{
            remis=true;
        }
        if(remis){
            result_pane.setVisible(true);
            who_win.setText("Remis");
            remis=false;
        }


    }
    @FXML
    void click_on_button(ActionEvent e)
    {
        Object source = e.getSource();
        Button button = ((Button)source);

        if(!begin_player){
            button.setTextFill(Color.web("#795548"));
            button.setText("X");
            button.setDisable(true);
            turn++;
            ruch_komputera(turn);
        }
        else{
            button.setTextFill(Color.web("#795548"));
            button.setText("X");
            button.setDisable(true);
            ruch_komputera(turn);
            turn++;
        }

        if(turn>=3){
            if(check_win("X")){
                result_pane.setVisible(true);
                who_win.setText("Wygrał X");
                block_board(buttony);
            }
            else if(check_win("O")){
                result_pane.setVisible(true);
                who_win.setText("Wygrał O");
                block_board(buttony);
            }
        }

    }
    Button chech(String z, int n){

        int counter_vertical, counter_horizontal, counter_diagonally_l=0, counter_diagonally_r=0;

        //Sprawdzanie po ukosie z lewej
        for(int i=0;i<3;i++) {
            String button_text_diagonally_l = buttony[i][i].getText();
            if (button_text_diagonally_l.equals(z))
                counter_diagonally_l++;

            if (counter_diagonally_l == n) {
                for(int k = -2; k <= 2; k++) {
                    int sum = i + k;
                    if(sum>=0 && sum<=2 && buttony[sum][sum].getText().equals(""))
                        return buttony[sum][sum];
                }
            }
        }
        //Sprawdzanie po ukosie z prawej:
        int a=2, b=0;
        while(a>=0){
            String button_text_diagonally_r = buttony[a][b].getText();
            if(button_text_diagonally_r.equals(z))
                counter_diagonally_r++;
            a--;
            b++;

            if(counter_diagonally_r == n){
                for(int k = -2; k <= 2; k++) {
                    int sum1 = a + k;
                    int sum2 = b - k;
                    if(sum1>=0 && sum1<=2 && sum2>=0 && sum2<=2 && buttony[sum1][sum2].getText().equals(""))
                        return buttony[sum1][sum2];
                }
            }
        }

        //Sprawdzanie poziomo i pionowo
        for(int i=0;i<3;i++){
            counter_vertical=0;
            counter_horizontal=0;
            for(int j=0;j<3;j++){
                String button_text_vertical = buttony[i][j].getText(); // Z kolumn - pionowo
                String button_text_horizontal = buttony[j][i].getText();// Z wierszy - poziomo
                if(button_text_vertical.equals(z)){
                    counter_vertical++;
                }
                if(button_text_horizontal.equals(z)){
                    counter_horizontal++;
                }

                if(counter_vertical==n){
                    for(int k=-2;k<=2;k++){
                        int sum = j+k;
                        if(sum>=0 && sum<=2 && buttony[i][sum].getText().equals(""))
                            return buttony[i][sum];
                    }
                }
                if(counter_horizontal==n){
                    for(int k=-2;k<=2;k++){
                        int sum = j+k;
                        if(sum>=0 && sum<=2 && buttony[sum][i].getText().equals(""))
                            return buttony[sum][i];
                    }
                }

            }
        }
        return null;
    }
    boolean check_win(String z){
        byte counter_vertical, counter_horizontal;

        for(byte i=0;i<3;i++){
            counter_vertical=0;
            counter_horizontal=0;
            for(byte j=0;j<3;j++){
                String button_text_vertical = buttony[i][j].getText(); // Z kolumn - pionowo
                String button_text_horizontal = buttony[j][i].getText();// Z wierszy - poziomo
                if(button_text_vertical.equals(z)){
                    counter_vertical++;
                }
                if(button_text_horizontal.equals(z)){
                    counter_horizontal++;
                }

                if(counter_vertical==3 || counter_horizontal==3){
                    return true;
                }

            }
        }
        //Na ukos. Od lewy górny róg do prawy dolny róg:
        String button_text_00 = buttony[0][0].getText();
        String button_text_22 = buttony[2][2].getText();

        //Srodek:
        String button_text_11 = buttony[1][1].getText();

        //Na ukos. Od prawy górny róg do lewy dolny róg:
        String button_text_20 = buttony[2][0].getText();
        String button_text_02 = buttony[0][2].getText();

        //Sprawdzanie na ukos
        if(button_text_00.equals(z) && button_text_11.equals(z) && button_text_22.equals(z) || button_text_20.equals(z) && button_text_11.equals(z) && button_text_02.equals(z)){
            System.out.println("Na ukos");
            return true;
        }


        return false;
    }

}
