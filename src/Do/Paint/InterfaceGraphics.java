package Do.Paint;

import Do.Enums.DrawSkill;
import Do.Factory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.SkillTracker;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.world.Worlds;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class InterfaceGraphics{
    private Image mainpaint = getImage("https://i.imgur.com/PDpOnTp.png");
    private Factory _factory;
    private DrawSkill selectedDrawSkill;
    private Graphics _graphics;
    private Debug debugDrawn;
    private Data data;
    public InterfaceGraphics(Factory factory) {
        _factory = factory;
        debugDrawn = new Debug(factory);
        data = new Data();
    }

    public void Drawn(Graphics graphics,GUI.JWindow window){
        if(window.getgui()){
            _graphics = graphics;
            graphics.drawImage(mainpaint, 0, 0, null);
            Font font = new Font("Century Gothic", Font.BOLD, 12);
            graphics.setFont(font);
            graphics.setColor(Color.white);
            graphics.drawString("Eclapsed time: " + _factory.getTime().eclapsedtime(_factory.getTime().getStartTime()), data.getMainWidth(), data.getMainHeight());
            graphics.drawString("Total level: " + Skills.getTotalLevel(), data.getMainWidth(), data.getMainHeight() + 20);
            graphics.drawString("Current world: " + Worlds.getCurrentWorld(), data.getMainWidth(), data.getMainHeight() + 40);
            graphics.drawString("Total estimated Xp/hour: " + TotalEstimatedXp(), data.getMainWidth(), data.getMainHeight() + 60);
            graphics.drawString("Total gained Xp: " + (SkillTracker.getGainedExperience(Skill.FIREMAKING) + SkillTracker.getGainedExperience(Skill.WOODCUTTING)), data.getMainWidth(), data.getMainHeight() + 80);
            graphics.drawString("What do I do with the logs: " + _factory.getDoLogs().WhatDoIDoWithTheLogs(), data.getMainWidth(), data.getMainHeight() + 100);
            graphics.drawString(_factory.getIU().getActivity() + getData().dot(), data.getMainWidth(), data.getMainHeight() + 120);
            switch (getSelectedDrawSkill()){
                case WoodCutting:
                    WoodcuttingDrawn(graphics);
                    break;
                case FireMaking:
                    FiremakingDrawn(graphics);
                    break;
            }
            if(window.getisenableDebbuger()){
                debugDrawn.DebugDrawn();
            }
        }else{
            graphics.drawString("x", 597, 10);
        }
    }
    private void FiremakingDrawn(Graphics graphics){
        graphics.setColor(Color.orange);
        graphics.drawString("Firemaking level: " + Skills.getRealLevel(Skill.FIREMAKING) + " (" + (SkillTracker.getGainedLevels(Skill.FIREMAKING)) + ")", data.getSecondaryWidth(), data.getSecondaryHeight());
        graphics.drawString("Next level: " + SkillTracker.getTimeToLevel(Skill.FIREMAKING) / 60000 + "(Minutes)", data.getSecondaryWidth(), data.getSecondaryHeight() + 20);
        graphics.drawString("Xp remaining: " + (Skills.getExperienceToLevel(Skill.FIREMAKING)), data.getSecondaryWidth(), data.getSecondaryHeight() + 40);
        graphics.drawString("Xp gained: " + (SkillTracker.getGainedExperience(Skill.FIREMAKING)), data.getSecondaryWidth(), data.getSecondaryHeight() + 60);
        graphics.drawString("Burn Logs/hour: " + getData().getBurned_logs() * (int) (3600D / _factory.getTime().eclapsedsec(_factory.getTime().getStartTime())), data.getSecondaryWidth(), data.getSecondaryHeight() + 80);
        graphics.drawString("Burned logs: " + getData().getBurned_logs(), data.getSecondaryWidth(), data.getSecondaryHeight() + 100);
        graphics.drawString("Estimated Xp/hour: " + SkillTracker.getGainedExperiencePerHour(Skill.FIREMAKING), data.getSecondaryWidth(), data.getSecondaryHeight() + 120);

    }
    private void WoodcuttingDrawn(Graphics graphics){
        Font font = new Font("Century Gothic", Font.BOLD, 12);
        graphics.setFont(font);
        graphics.setColor(new Color(97, 185, 0));
        graphics.drawString("Woodcutting level: " + Skills.getRealLevel(Skill.WOODCUTTING) + " (" + (SkillTracker.getGainedLevels(Skill.WOODCUTTING)) + ")", data.getSecondaryWidth(), data.getSecondaryHeight());
        graphics.drawString("Next level: " + SkillTracker.getTimeToLevel(Skill.WOODCUTTING) / 60000 + "(Minutes)", data.getSecondaryWidth(), data.getSecondaryHeight() + 20);
        graphics.drawString("Xp remaining: " + (Skills.getExperienceToLevel(Skill.WOODCUTTING)), data.getSecondaryWidth(), data.getSecondaryHeight() + 40);
        graphics.drawString("Xp gained: " + (SkillTracker.getGainedExperience(Skill.WOODCUTTING)), data.getSecondaryWidth(), data.getSecondaryHeight() + 60);
        graphics.drawString("Cut Logs/hour: " + getData().getLogcuts() * (int) (3600D / _factory.getTime().eclapsedsec(_factory.getTime().getStartTime())), data.getSecondaryWidth(), data.getSecondaryHeight() + 80);
        graphics.drawString("Logs Cut: " + getData().getLogcuts(), data.getSecondaryWidth(), data.getSecondaryHeight() + 100);
        graphics.drawString("Estimated Xp/hour: " + SkillTracker.getGainedExperiencePerHour(Skill.WOODCUTTING), data.getSecondaryWidth(), data.getSecondaryHeight() + 120);

    }
    protected void MapDraw(Graphics graphics){
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
    public float TotalEstimatedXp(){
        return SkillTracker.getGainedExperiencePerHour(Skill.WOODCUTTING)
                + SkillTracker.getGainedExperiencePerHour(Skill.FIREMAKING);
    }
    /*private void ChangeStopper(){
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
    }*/
    //Gettters and Setters
    public Factory getFactory() {
        return _factory;
    }
    public Graphics getGraphics(){
        return _graphics;
    }

    public Data getData() {
        return data;
    }
    public DrawSkill getSelectedDrawSkill() {
        return selectedDrawSkill;
    }

    public void setSelectedDrawSkill(DrawSkill selectedDrawSkill) {
        this.selectedDrawSkill = selectedDrawSkill;
    }
}
