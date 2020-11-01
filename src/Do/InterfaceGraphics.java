package Do;

import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.SkillTracker;
import org.dreambot.api.methods.skills.Skills;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import static java.lang.Math.toIntExact;

public class InterfaceGraphics{

    private  boolean starting = true;

    private Image mainpaint = getImage("https://i.imgur.com/8WbQblh.png");
    private int logcuts = 0;
    private int burned_logs = 0;
    private String dot = "...";
    private int i = 0;
    private long second2;
    private Factory _factory;
    private boolean ChangeDraw = false;
    private long startTime = 0;
    public InterfaceGraphics(Factory factory) {
        _factory = factory;
    }

    public void Drawn(Graphics graphics,GUI.JWindow window){
        if(window.getgui()){
            ChangeStopper();
            graphics.drawImage(mainpaint, 0, 0, null);
            Font font = new Font("Century Gothic", Font.BOLD, 11);
            graphics.setFont(font);
            graphics.setColor(Color.white);
            graphics.drawString("" + _factory.getTime().eclapsedtime(_factory.getTime().getStartTime()), 45, 495);
            graphics.drawString(_factory.getIU().getActivity() + dot(), 290, 495);
            graphics.drawString((startTime + 10) - (System.currentTimeMillis() / 1000) + "s", 10, 10);
            WoodcuttingDrawn(graphics);
            if(window.getburn()){//FireMaking
                FiremakingDrawn(graphics);
            }

        }
        if(window.getisenableDebbuger()){
            DebugDrawn(graphics);
        }
    }
    private void DebugDrawn(Graphics graphics){
        if(_factory.getMain().isStarter()) {
            graphics.setColor(Color.white);
            /*if (!starting) {
                Tile[] currentareatiles = _factory.getSelectAreas().getCurrentArea().getTiles();
                for (int i = 0; i < currentareatiles.length - 1; i++) {
                    graphics.drawPolygon(_factory.getMain().getMap().getPolygon(currentareatiles[i]));
                }
            }*/
            MapDraw(graphics);
            graphics.setColor(Color.RED);
            graphics.drawString("Closest tree: " + _factory.getDebuger().getTreeCloses(), 10, 100);
            graphics.drawString("Thought: " + _factory.getIU().getThought(), 10, 120);
            graphics.drawString("Closest bank: " + Bank.getClosestBankLocation(), 10, 140);
            graphics.drawString("Tree type: " + _factory.getMain().getWindow().getTreetype(), 10, 160);
            graphics.setColor(Color.cyan);
            graphics.drawPolygon(_factory.getDebuger().getTreeTile().getPolygon());
            graphics.setColor(Color.yellow);
            graphics.drawPolygon(Map.getPolygon(Players.localPlayer().getTile()));
        }
    }
    private void FiremakingDrawn(Graphics graphics){
        graphics.setColor(Color.orange);

        if(ChangeDraw){
            graphics.drawString("Firemaking level: " + Skills.getRealLevel(Skill.FIREMAKING) + " (" + (SkillTracker.getGainedLevels(Skill.FIREMAKING)) + ")", 30, 155);
        }else{
            graphics.drawString("Next level: " + SkillTracker.getTimeToLevel(Skill.FIREMAKING) / 60000 + "(Minutes)", 30, 155);
        }//lvl
        if(ChangeDraw){
            graphics.drawString("Xp remaining: " + (Skills.getExperienceToLevel(Skill.FIREMAKING)), 30, 185);
        }else{
            graphics.drawString("Xp gained: " + (SkillTracker.getGainedExperience(Skill.FIREMAKING)), 30, 185);
        }//Xp
        if(ChangeDraw){
            graphics.drawString("Burned logs: " + burned_logs, 30, 215);
        }else {
            graphics.drawString("Estimated Xp/hour: " + SkillTracker.getGainedExperiencePerHour(Skill.FIREMAKING), 30, 215);
        }
    }
    private void WoodcuttingDrawn(Graphics graphics){
        Font font = new Font("Century Gothic", Font.BOLD, 9);
        graphics.setFont(font);
        graphics.setColor(new Color(97, 185, 0));

        if(ChangeDraw){
            graphics.drawString("Woodcutting level: " + Skills.getRealLevel(Skill.WOODCUTTING) + " (" + (SkillTracker.getGainedLevels(Skill.WOODCUTTING)) + ")", 378, 155);
        }else{
            graphics.drawString("Next level: " + SkillTracker.getTimeToLevel(Skill.WOODCUTTING) / 60000 + "(Minutes)", 378, 155);
        }//lvl
        if(ChangeDraw){//Xp
            graphics.drawString("Xp remaining: " + (Skills.getExperienceToLevel(Skill.WOODCUTTING)), 378, 184);
        }else{
            graphics.drawString("Xp gained: " + (SkillTracker.getGainedExperience(Skill.WOODCUTTING)), 378, 184);
        }//Xp

        graphics.drawString("Logs/hour: " + logcuts * (int) (3600D / _factory.getTime().eclapsedsec(_factory.getTime().getStartTime())), 378, 250);
        if(ChangeDraw){
            graphics.drawString("Logs Cut: " + logcuts, 378, 214);
        }else{
            graphics.drawString("Estimated Xp/hour: " + SkillTracker.getGainedExperiencePerHour(Skill.WOODCUTTING), 378, 214);
        }
    }
    private void MapDraw(Graphics graphics){
        if(_factory.getMain().isStarter()) {
            graphics.setColor(Color.MAGENTA);
            Tile[] currentareatiles = _factory.getSelectAreas().getCurrentArea().getTiles();
            if (_factory.getSelectAreas().getCurrentArea().getTiles() != null) {
                for (int i = 0; i < currentareatiles.length - 1; i++){
                    graphics.drawRect((int)Map.tileToMiniMap(currentareatiles[i]).getX(), (int)Map.tileToMiniMap(currentareatiles[i]).getY(), 2, 2);
                }
            }
        }
    }
    private Image getImage(String url){
        try{
            return ImageIO.read(new URL(url));
        }catch(IOException e){
            return null;
        }
    }
    private String dot(){
        long seconds = System.currentTimeMillis()/ 1000l;
        if(toIntExact(second2) <  toIntExact(seconds)){
            for(int j = 0; j < i; j++){
                dot +=".";
            }
            if(dot.length() >= 5){
                dot ="";
                i = 0;
            }
            i++;
        }
        second2 = System.currentTimeMillis()/ 1000l;
        return dot;
    }
    private void ChangeStopper(){
        if(startTime == 0){
            startTime = System.currentTimeMillis() / 1000;
        }
        long CurrentTime = System.currentTimeMillis() / 1000;
        long elapsedTime = CurrentTime - startTime;
        if(elapsedTime >= 10){
            startTime = 0;
            if(ChangeDraw){
                ChangeDraw = false;
            }else {
                ChangeDraw = true;
            }
        }
    }
    //Gettters and Setters
    public boolean isStarting() {
        return starting;
    }
    public void setStarting(boolean starting) {
        this.starting = starting;
    }

    public int getLogcuts() {
        return logcuts;
    }

    public void setLogcuts(int logcuts) {
        this.logcuts = logcuts;
    }

    public int getBurned_logs() {
        return burned_logs;
    }

    public void setBurned_logs(int burned_logs) {
        this.burned_logs = burned_logs;
    }
}
