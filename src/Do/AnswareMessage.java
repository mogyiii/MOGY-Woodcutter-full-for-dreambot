package Do;

import GUI.JWindow;
import org.dreambot.api.methods.input.Keyboard;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.skills.Skill;
import sun.net.www.http.HttpClient;
import org.dreambot.api.wrappers.widgets.message.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

public class AnswareMessage {
    private Factory _factory;
    public AnswareMessage(Factory factory) {
        _factory = factory;
    }
    public void Answer(Message message, JWindow window){
        if(!window.isTalker()){
            if(PlayerIsExist(message.getUsername()) && !(message.getUsername().equals(_factory.getMain().getLocalPlayer().getName()))){
                Keyboard.type(ResponseCutter(SendPostMessage(message.getMessage())));
            }
        }
    }
    private boolean PlayerIsExist(String message){
        for(int i = 0; i < Players.all().size(); i++){
            if(message.contains(Players.all().get(i).getName())){
                return true;
            }
        }
        return false;
    }
    private String SendPostMessage(String msg){
        String rawData = "ENTRY=" + msg + "";
        String type = "application/x-www-form-urlencoded";
        String Response = "";
        try {
            String encodedData = URLEncoder.encode( rawData, "UTF-8" );
            URL u = new URL("http://elbot-e.artificial-solutions.com/cgi-bin/elbot.cgi");
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty( "Content-Type", type );
            conn.setRequestProperty( "Content-Length", String.valueOf(encodedData.length()));
            OutputStream os = conn.getOutputStream();
            os.write(encodedData.getBytes());
            BufferedReader br = null;
            if (conn.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String strCurrentLine;
                while ((strCurrentLine = br.readLine()) != null) {
                    Response += strCurrentLine;
                }
            }
            return Response;
        } catch (java.io.IOException e) {
            _factory.getMain().log("" + e.toString());
        }
        return Response;
    }
    private String ResponseCutter(String response){
        response = response.replaceAll("<!-- Country:   -->", "");
        String Message = "";
        for(int i = response.indexOf("<!-- Begin Response !-->"); i < response.indexOf("<!-- End Response !-->"); i++){
            Message += response.charAt(i);
        }
        _factory.getIU().SetActivity("Chatting...");
        return Message.replace('"', ' ').replace("<!-- Begin Response !-->", "").replace("elbot", Players.localPlayer().getName()).replace("Elbot", Players.localPlayer().getName());
    }
}
