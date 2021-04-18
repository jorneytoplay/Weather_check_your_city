package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane button;

    @FXML
    private Text ViewCity;

    @FXML
    private TextField GetCity;

    @FXML
    private Button ClickButton;

    @FXML
    private Text GetDegrees;

    @FXML
    private Text Min;

    @FXML
    private Text Max;

    @FXML
    private Text Feels;
    @FXML
    void initialize() {
        ClickButton.setOnAction(event -> {
        String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + GetCity.getText().toString().trim() + "&appid=cfe0e3510584be4ceeeedff8c94f87ed&units=metric");
        if(!output.isEmpty()){
            JSONObject obj = new JSONObject(output);
            Min.setText("Min:"+obj.getJSONObject("main").getDouble("temp_min"));
            GetDegrees.setText("Градусов:"+obj.getJSONObject("main").getDouble("temp"));
            Max.setText("Max:"+obj.getJSONObject("main").getDouble("temp_max"));
            Feels.setText("Ощущается:"+obj.getJSONObject("main").getDouble("feels_like"));
            ViewCity.setText(GetCity.getText().toString().trim());
        }
    });
    }
    private static String getUrlContent(String urlAdress){
    StringBuffer content = new StringBuffer();
    try {
        URL url = new URL(urlAdress);
        URLConnection urlConn = url.openConnection();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine())!=null){
            content.append(line+"\n");
        }
        bufferedReader.close();
    }
    catch (Exception e){
        System.out.println("Нет такого города");

    }
    return content.toString();
    }
}

