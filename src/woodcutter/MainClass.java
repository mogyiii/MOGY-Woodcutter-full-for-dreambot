package woodcutter;

import org.dreambot.api.methods.magic.Spell;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.interactive.GameObject;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import static java.lang.Math.toIntExact;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.widgets.message.Message;
import org.dreambot.api.methods.map.Tile;

import javax.imageio.ImageIO;

@ScriptManifest(category = Category.WOODCUTTING, name = "Mogy Woodcutter", author = "Mogyiii", version = 2.2)

public class MainClass extends AbstractScript {
    private GUI.JWindow window;
    private Time time;
    private boolean starter = false;
    private int logcuts = 0;
    private int burned_logs = 0;
    private long startTime;
    private int Startedlevelup;
    private int FiremakingStartedlevelup;
    private int current_woodcutting_xp;
    private int current_firemaking_xp;
    private String activity;
    private String treeCloses = "";
    private boolean first = true;
    private String dot = "...";
    private int i = 0;
    private long second2;
    private boolean lighting;
    private int playerpossitionX;
    private int playerpossitionY;
    private String thought = "";
    private Tile TreeTile;
    private  boolean starting = true;
    private Image mainpaint = getImage("https://i.imgur.com/kZPF0p5.png");
    private Image firemakingpaint = getImage("https://i.imgur.com/PRzTXKg.png");
    Area EastVarrokbank = new Area(3250,3422,3256,3420);
    Area WestVarrokbank = new Area(3181,3442,3185,3435);
    Area GrandExchangeBank = new Area(3160, 3493, 3168, 3483);
    Area DraynorBank = new Area(3092, 3246, 3097, 3240);
    Area Edgevillagebank = new Area(3091, 3498, 3099, 3487);
    Area FaladorEastbank = new Area(3009, 3358, 3019, 3355);
    Area DuelArenaBank = new Area(3379, 3266, 3384, 3272);
    Area RimmingtonShop = new Area(2947, 3217, 2950, 3212);
    private int range = 5;

    Area Selectedarea = new Area();
    Area currentArea = new Area();
    @Override
    public void onStart(){
        window = new GUI.JWindow(this);
        window.setVisible(true);
        time = new Time();
        current_woodcutting_xp = getSkills().getExperience(Skill.WOODCUTTING);
        current_firemaking_xp = getSkills().getExperience(Skill.FIREMAKING);
        Startedlevelup = getSkills().getRealLevel(Skill.WOODCUTTING);
        FiremakingStartedlevelup = getSkills().getRealLevel(Skill.FIREMAKING);
        log("MOGY's Woodcutter Full version");
        log("Starting...");
        activity = "Starting";
        startTime = System.currentTimeMillis();
        currentArea = new Area(getLocalPlayer().getX() -range,getLocalPlayer().getY() -range,getLocalPlayer().getX() +range,getLocalPlayer().getY()+range);
    }
    @Override
    public void onExit() {
        super.onExit();
    }
    @Override
    public void onMessage(Message message) {
        if(message.getMessage().contains("You get some")){
            logcuts++;
        }
        if(message.getMessage().contains("The fire catches")){
            burned_logs++;
        }
        if(message.getMessage().contains("Hi!!!")){
            getWidgets().getWidget(548).getChild(43).interact();
            getWidgets().getWidget(548).getChild(79).interact();
            getKeyboard().type("Hi master!!!");
        }
        if(message.getMessage().contains("Hi")){
            getKeyboard().type("Hi");
        }
        if(message.getMessage().contains("Bot?")){
            getKeyboard().type("no");
        }
        if(message.getMessage().contains("You can't light a fire here.")){
            thought= "I can't light a fire here!!";
            lighting = false;
        }
        if(message.getMessage().contains("I can't reach that!")){

            if(getGameObjects().closest("Wilderness Ditch").exists()){
                activity = "Jump!";
                WildernessJumping();
            }else {
                walkingtoarea(Selectedarea);
            }
        }
    }
    @Override
    public void onPaint(Graphics graphics) {
        if(window.getgui()) {
            graphics.drawImage(mainpaint, 2, 341, null);
            Font font = new Font("Arial", Font.PLAIN, 12);
            graphics.setFont(font);
            graphics.setColor(Color.green);

            graphics.drawString("Logs Cut: " + logcuts, 10, 359);
            graphics.drawString("Time running: " + time.eclapsedtime(startTime), 10, 389);
            graphics.drawString("Woodcutting level: " + getSkills().getRealLevel(Skill.WOODCUTTING) + " (" + (getSkills().getRealLevel(Skill.WOODCUTTING) - Startedlevelup) + ")", 10, 415);
            graphics.drawString("Logs/hour: " + logcuts * (int) (3600D / time.eclapsedsec(startTime)), 10, 440);
            graphics.drawString("Xp Gained: " + (getSkills().getExperience(Skill.WOODCUTTING) - current_woodcutting_xp), 10, 460);
            graphics.drawString(activity + dot(), 245, 472);
            if(window.getburn()){
                graphics.drawImage(firemakingpaint, 285, 200, null);
                graphics.setColor(Color.orange);
                graphics.drawString("Burned logs: " + burned_logs, 350, 250);
                graphics.drawString("Firemaking level: " + getSkills().getRealLevel(Skill.FIREMAKING) + " (" + (getSkills().getRealLevel(Skill.FIREMAKING) - FiremakingStartedlevelup) + ")", 360, 270);
                graphics.drawString("Xp Gained: " + ( getSkills().getExperience(Skill.FIREMAKING) - current_firemaking_xp), 370, 290);
            }
        }
        if(window.getcheckbox1()){
            graphics.setColor(Color.white);
            if(!starting){
                Tile[] currentareatiles = currentArea.getTiles();
                for (int i = 0; i < currentArea.getTiles().length-1; i++){
                    graphics.drawPolygon(getMap().getPolygon(currentareatiles[i]));
                }
            }
            graphics.setColor(Color.RED);
            graphics.drawString("Closest tree: " + treeCloses,10,100);
            graphics.drawString("Thought: " + thought,10,120);
            graphics.drawString("Closest bank: " + getBank().getClosestBankLocation(),10,140);
            graphics.drawString("Tree type: " + window.getTreetype(),10,160);

            graphics.setColor(Color.cyan);
            graphics.drawPolygon(TreeTile.getPolygon());
            graphics.setColor(Color.yellow);
            graphics.drawPolygon(getMap().getPolygon(getPlayers().localPlayer().getTile()));
        }
    }

    private Image getImage(String url){
    try{
        return ImageIO.read(new URL(url));
    }catch(IOException e){
        return null;
    }
    }
    @Override
    public int onLoop() {
        if(starter) {
            if(starting){
                range = window.getAreaSize();
                currentArea = new Area(getLocalPlayer().getX() -range,getLocalPlayer().getY() -range,getLocalPlayer().getX() +range,getLocalPlayer().getY()+range);
                starting= false;
            }
            SelectedArea();
        }
        if(window.getcheckbox1()){
            debug();
        }
        random_AntiBan();

        return ((int) (Math.random() * 20));
    }

    //Execute order 66
    private void SelectedArea(){
        switch (window.getTreetype()) {
            case "Tree":
                switch (window.getAreaLocation()){
                    case "GrandExchange":
                        remoteBankcutter(GrandExchangeBank);
                        break;
                    case "East-Varrock":
                        MultiCutter(EastVarrokbank, new Area(3264,3485,3284,3446), window.getTreetype(), "Logs");
                        break;
                    case "Draynor village":
                        remoteBankcutter(DraynorBank);
                        break;
                    case "West-Varrock":
                        remoteBankcutter(WestVarrokbank);
                        break;
                    case "Current area":
                        MultiTypeCutter(getBank().getClosestBankLocation().getCenter().getArea(2), currentArea, "Logs");
                        //RemoteCutter(new Area(getLocalPlayer().getX() - range,getLocalPlayer().getY() -range,getLocalPlayer().getX() +range,getLocalPlayer().getY()+range),window.getTreetype());
                        break;
                }
                break;
            case "Oak":
                switch (window.getAreaLocation()){
                    case "GrandExchange":
                        MultiCutter(GrandExchangeBank, new Area(3189,3467,3197,3458), window.getTreetype(), window.getTreetype()+" logs");
                        break;
                    case "East-Varrock":
                        MultiCutter(EastVarrokbank, new Area(3274,3438,3285,3414), window.getTreetype(), window.getTreetype()+" logs");
                        break;
                    case "South-Varrock":
                        MultiCutter(EastVarrokbank, new Area(3200,3373,3217,3358), window.getTreetype(), window.getTreetype()+" logs");
                        break;
                    case "South-Falador":
                        MultiCutter(FaladorEastbank, new Area(2999,3318,3013,3298), window.getTreetype(), window.getTreetype()+" logs");
                        break;
                    case "Draynor village":
                        remoteBankcutter(DraynorBank);
                        break;
                    case "West-Varrock":
                        remoteBankcutter(WestVarrokbank);
                        break;
                    case "Current area":
                        MultiCutter(getBank().getClosestBankLocation().getCenter().getArea(2), currentArea, window.getTreetype(), window.getTreetype()+" logs");
                        //RemoteCutter(new Area(getLocalPlayer().getX() -range,getLocalPlayer().getY() -range,getLocalPlayer().getX() +range,getLocalPlayer().getY()+range),window.getTreetype());
                        break;
                }
            case "Willow":
                switch (window.getAreaLocation()){
                    case "West-Draynor":
                        MultiCutter(DraynorBank, new Area(3056,3256,3064,3251), window.getTreetype(), window.getTreetype()+" logs");
                        break;
                    case "East-Draynor":
                        MultiCutter(DraynorBank, new Area(3158,3277,3183,3260), window.getTreetype(), window.getTreetype()+" logs");
                        break;
                    case "South-Rimmington":
                        MultiCutter(FaladorEastbank, new Area(2957,3201,2975,3191), window.getTreetype(), window.getTreetype()+" logs");
                        break;
                    case "North-Lumbridge":
                        //RemoteCutter(new Area(3219,3311,3224,3297),window.getTreetype());
                        MultiCutter(getBank().getClosestBankLocation().getCenter().getArea(2), new Area(3219,3311,3224,3297), window.getTreetype(), window.getTreetype()+" logs");
                        break;
                    case "Current area":
                        MultiCutter(getBank().getClosestBankLocation().getCenter().getArea(2), currentArea, window.getTreetype(), window.getTreetype()+" logs");
                        //RemoteCutter(new Area(getLocalPlayer().getX() -range,getLocalPlayer().getY() -range,getLocalPlayer().getX() +range,getLocalPlayer().getY()+range),window.getTreetype());
                        break;

                }
            case "Yew":
                switch (window.getAreaLocation()){
                    case "EdgeVillage":
                        MultiCutter(Edgevillagebank,  new Area(3085,3479,3091,3468), window.getTreetype(), window.getTreetype()+" logs");
                        break;
                    case "Exchange":
                        MultiCutter(GrandExchangeBank,  new Area(3201,3506,3224,3498), window.getTreetype(), window.getTreetype()+" logs");
                        break;
                    case "East-Varrock":
                        remoteBankcutter(EastVarrokbank);
                        break;
                    case "West-Draynor":
                        remoteBankcutter(DraynorBank);
                        break;
                    case "Falador":
                        remoteBankcutter(FaladorEastbank);
                        break;
                    case "Lumbridge":
                        MultiCutter(EastVarrokbank,  new Area(3134,3265,3141,3226), window.getTreetype(), window.getTreetype()+" logs");
                        break;
                    case "Current area":
                        MultiCutter(getBank().getClosestBankLocation().getCenter().getArea(2), currentArea, window.getTreetype(), window.getTreetype()+" logs");
                        break;
                }
            case "Magic tree":
                switch (window.getAreaLocation()) {
                    case "Mage Training Area":
                        MultiCutter(DuelArenaBank,new Area(3353,3293,3373,3360), window.getTreetype(), "Magic logs");
                        break;
                    case "Current area":
                        MultiCutter(getBank().getClosestBankLocation().getCenter().getArea(2), currentArea, window.getTreetype(), window.getTreetype()+" logs");
                        break;
                }
            case "Achey tree":
                switch (window.getAreaLocation()) {
                    case "Current area":
                        MultiCutter(getBank().getClosestBankLocation().getCenter().getArea(2), currentArea, window.getTreetype(), window.getTreetype()+" logs");
                        break;
                }
            case "Teak tree":
                switch (window.getAreaLocation()) {
                    case "Current area":
                        MultiCutter(getBank().getClosestBankLocation().getCenter().getArea(2), currentArea, window.getTreetype(), window.getTreetype()+" logs");
                        break;
                }
            case "Maple tree":
                switch (window.getAreaLocation()) {
                    case "Current area":
                        MultiCutter(getBank().getClosestBankLocation().getCenter().getArea(2), currentArea, window.getTreetype(), window.getTreetype()+" logs");
                        break;
                }
            case "Arctic pine":
                switch (window.getAreaLocation()) {
                    case "Current area":
                        MultiCutter(getBank().getClosestBankLocation().getCenter().getArea(2), currentArea, window.getTreetype(), window.getTreetype()+" logs");
                        break;
                }
            case "Hollow tree":
                switch (window.getAreaLocation()) {
                    case "Current area":
                        MultiCutter(getBank().getClosestBankLocation().getCenter().getArea(2), currentArea, window.getTreetype(), window.getTreetype()+" logs");
                        break;
                }
            case "Mahogany tree":
                switch (window.getAreaLocation()) {
                    case "Current area":
                        MultiCutter(getBank().getClosestBankLocation().getCenter().getArea(2), currentArea, window.getTreetype(), window.getTreetype()+" logs");
                        break;
                }
            case "Redwood tree":
                switch (window.getAreaLocation()) {
                    case "Current area":
                        MultiCutter(getBank().getClosestBankLocation().getCenter().getArea(2), currentArea, window.getTreetype(), window.getTreetype()+" logs");
                        break;
                }
        }
    }
    private void MultiCutter(Area bank_area, Area Selected_area, String tree_type, String logs_type){
            currentArea = Selected_area;
            if(getInventory().isFull()){
            if(window.getburn() && checktinderbox()){
                activity = "Burn logs";
                burnlogs(logs_type);
            }else if(window.getburn() && !checktinderbox()){
                activity="Droping logs";
                thought = "oh no :(";
                getInventory().dropAll(logs_type);
            }else{
                walkingtoarea(bank_area);
                if (bank_area.contains(getLocalPlayer())) {
                    banking();
                }
            }
            sleep(500, 1000);
        }else{
            if(Selected_area.contains(getLocalPlayer())){
                if(getLocalPlayer().isAnimating()){
                    cutting();
                }else{
                    if(!getPlayers().localPlayer().isMoving()){
                        chop(tree_type);
                        sleep(200, 400);
                    }
                }
            }else{
                walkingtoarea(Selected_area.getCenter().getArea(2));
            }
        }
    }
    private void MultiTypeCutter(Area bank_area, Area Selected_area, String logs_type){
        if(getInventory().isFull()){
            if(window.getburn() && checktinderbox()){
                activity = "Burn logs";
                burnlogs(logs_type);
            }else if(window.getburn() && !checktinderbox()){
                activity="Droping logs";
                getInventory().dropAll(logs_type);
            }else{
                walkingtoarea(bank_area);
                if (bank_area.contains(getLocalPlayer())) {
                    banking();
                }
            }
            sleep(500, 1000);
        }else{
            if(Selected_area.contains(getLocalPlayer())){
                if(getLocalPlayer().isAnimating()){
                    cutting();
                }else{
                    if(!getPlayers().localPlayer().isMoving()){
                        chop(checktreeType());
                        sleep(200, 400);
                    }
                }
            }else{
                walkingtoarea(Selected_area);
            }
        }
    }
    private void remoteBankcutter(Area bank){
        if(first){
            Selectedarea = bank;

            first = false;
        }
        currentArea = Selectedarea;
        if(getInventory().isFull()){
            if(window.getburn() && checktinderbox()){
                activity = "Burn logs";
                if(window.getTreetype().contains("Tree")){
                    burnlogs("Logs");
                }else{
                    burnlogs(window.getTreetype() + " logs");
                }
            }else {
                walkingtoarea(bank);
                if (bank.contains(getLocalPlayer())) {
                    banking();
                }
            }
            sleep(500, 1500);
        }else{
            if(Selectedarea.contains(getLocalPlayer())){
                if(getLocalPlayer().isAnimating()){
                    cutting();
                }else{
                    if(chopping(window.getTreetype())){
                        Selectedarea = new Area(getGameObjects().closest(window.getTreetype()).getX() - 3,getGameObjects().closest(window.getTreetype()).getY() -3,getGameObjects().closest(window.getTreetype()).getX() +3,getGameObjects().closest(window.getTreetype()).getY()+3);
                        chop(window.getTreetype());
                        sleep(300, 1000);
                    }
                }
            }else{
                walkingtoarea(Selectedarea);
            }
        }

    }
    //DOITANAKIN
    public void setActivity(String data){
        activity = data;
    }
    public void setThought(String data){
        thought = data;
    }
    public GUI.JWindow getWindow(){
        return window;
    }

    private void debug(){
        treeCloses = "X: " + getGameObjects().closest(window.getTreetype()).getX() + ", Y: " + getGameObjects().closest(window.getTreetype()).getY();
        if(!window.getTreetype().equals("Tree")){
            TreeTile = getGameObjects().closest(window.getTreetype()).getTile();
        }else{
            TreeTile = getGameObjects().closest(checktreeType()).getTile();
        }


    }
    private String checktreeType(){
        String[] trees = {"Dying tree", "Evergreen", "Jungle tree", "Dead tree", "Tree"};
        for(int i = 0; i < trees.length; i++) {
            thought = trees[i] + " is " +String.valueOf(getGameObjects().all().toString().contains(trees[i]));
            if (getGameObjects().all().toString().contains(trees[i])){
                return trees[i];
            }
        }
        return "";
    }
    private String dot(){
        long seconds = System.currentTimeMillis()/ 1000l;
        if(toIntExact(second2) <  toIntExact(seconds)){
            for(int j = 0; j < i; j++){
                dot +=".";
            }
            if(dot.length() >= 4){
                dot ="";
                i = 0;
            }
            i++;
        }
        second2 = System.currentTimeMillis()/ 1000l;
        return dot;
    }

    private String checkaxe(){
        String[] axes = {"Infernal axe", "3rd age axe", "Dragon axe", "Rune axe", "Adamant axe", "Mithril axe", "Black axe", "Blessed axe", "Steel axe", "Iron axe", "Bronze axe"};
        for(int i = 0; i < axes.length; i++) {
            if (getInventory().contains(axes[i])){
                setThought(axes[i] + " is exist!");
                return axes[i];
            }
        }
        return  "";
    }
    private boolean checktinderbox(){
        if (getInventory().contains("Tinderbox")){
            thought = "Tinderbox is exist!";
            return true;
        }else{
            return false;
        }
    }
    private void cutting(){
        activity = "Cutting";
        thought = "This's boring!";
        sleep(200, 400);
    }
    private void walkingtoarea(Area Selectedarea){
        activity = "Walking to area!";
        thought = "Few can foresee whither their road will lead them, till they come to its end.";
        getWalking().walk(Selectedarea.getRandomTile());
        if(getWalking().getRunEnergy() >= 20){
            if(!(getWalking().isRunEnabled())){
                getWalking().toggleRun();
            }
        }

        sleep(100, 300);
    }
    private void WildernessJumping(){
        thought = "Bunny hop";
        GameObject gap = getGameObjects().closest("Wilderness Ditch");
        gap.interact("Cross");
        sleep(1000, 1500);
        getWidgets().getWidget(475).getChild(11).interact();
        sleep(100, 250);
    }
    private void chop(String treename){
        activity = "Chop";
        GameObject tree = getGameObjects().closest(treename);
        if(tree != null){
            if((getLocalPlayer().getX() == playerpossitionX && getLocalPlayer().getY() == playerpossitionY)){
                tree.interact("Chop down");
            }
            playerpossitionX = getLocalPlayer().getX();
            playerpossitionY = getLocalPlayer().getY();
            sleep(200, 500);
        }
        if(tree.getX() == 0 && tree.getY() == 0){
            log("Please delete Jagexcache folder");
            thought = "ah shit here we go again";
            activity = "Please delete Jagexcache folder!";
        }
    }
    private boolean chopping(String treename){
        activity = "Chop";
        thought = "Chop Chop";
        GameObject tree = getGameObjects().closest(treename);
        if(tree != null){
            if((getLocalPlayer().getX() == playerpossitionX && getLocalPlayer().getY() == playerpossitionY)){
                tree.interact("Chop down");
            }
            playerpossitionX = getLocalPlayer().getX();
            playerpossitionY = getLocalPlayer().getY();
            sleep(200, 1000);
            return true;
        }else{
            return false;
        }
    }
    private void burnlogs(String logname){
        thought = "i hope, i find Bernie!";
        lighting = true;
        while(getInventory().contains(logname)){
            log(logname);
            if(getLocalPlayer().isAnimating()){
                thought="Light up now!!!";
                sleep(1500, 3000);
            }else if(getPlayers().localPlayer().isMoving()){
                thought="Moving...";
                sleep(200,300);
            }else if(!lighting){
                getWalking().walk(currentArea.getRandomTile());
                sleep(200,300);
                lighting = true;
            }else{
                thought="Burning...";
                getInventory().interact("Tinderbox", "Use");
                getInventory().interact(logname, "Use");
                sleep(1500, 3000);
            }
        }
    }
    private void banking(){
            activity = "Banking";
            thought = "take care of it!";
            NPC banker = getNpcs().closest(npc -> npc != null && npc.hasAction("Bank"));
            if (banker.interact("Bank")) {
                if (sleepUntil(() -> getBank().open(), 9000)) {
                    getBank().depositAllExcept(checkaxe());
                    getBank().close();
                    sleep(200, 3000);
                }
            }
    }
    private void selling(String logname){
        activity = "Selling";
        NPC shopper = getNpcs().closest(npc -> npc != null && npc.hasAction("Trade"));
        if(shopper.interact("Trade")){
            if(sleepUntil(() ->getShop().open(), 9000)){
                getShop().sellFifty(logname);
                getShop().close();
                if(getInventory().contains(logname)){
                    getInventory().dropAll(logname);
                }
                sleep(500, 3000);
            }
        }
    }
    public void random_AntiBan() {

        Random srand = new Random();
        double chances = srand.nextDouble();
        double chances2 = srand.nextDouble();
        if (chances < 0.096) {
            log("Anti-ban: Changing Camera angle");
            setActivity("Anti-ban: Changing Camera angle");
            getCamera().rotateToEvent(srand.nextInt() + 360, srand.nextInt() + 90);
        }else if(chances > 0.096 && chances < 0.192){
            setThought("hmm...");
            log("Anti-ban: Changing mouse position");
            setActivity("Anti-ban: Changing mouse position");
            getMouse().move();
        }else if(chances > 0.192 && chances < 0.194){
            setThought("Zzz...");
            log("Anti-ban: Random sleep");
            setActivity("Anti-ban: Random sleeping");
            sleep(10000, 50000);
        }
        else if(chances > 0.200 && chances < 0.256) {
            setThought("Check XP...");
            log("Anti-ban: Checking skill XP");
            setActivity("Anti-ban: Checking skill XP");
            getSkills().open();
            sleep(100, 500);
            getSkills().hoverSkill(Skill.WOODCUTTING);
            sleep(1000, 1500);
            getKeyboard().typeSpecialKey(1);
        }
        else if(chances > 0.257 && chances < 0.295){
                setThought("Check XP...");
                log("Anti-ban: Checking skill XP");
                setActivity("Anti-ban: Checking skill XP");
                getSkills().open();
                sleep(100, 500);
                getSkills().hoverSkill(Skill.FIREMAKING);
                sleep(1000, 1500);
                getKeyboard().typeSpecialKey(1);

            }else if(chances > 0.296 && chances < 0.350){
            setThought("This music is not good...");
            setActivity("Anti-ban: Move cursor Outside Screen");
            log("Anti-ban: Move cursor Outside Screen");
            getMouse().moveMouseOutsideScreen();
            sleep(2888, 5111);
            getMouse().isMouseInScreen();
        }else if(chances > 0.350 && chances < 0.395){
            setThought("What is this?");
            if(chances2 < 0.2){
                getWidgets().getWidget(548).getChild(61).interact();
            } else if(chances2 > 0.2 && chances2 < 0.4){
                getWidgets().getWidget(548).getChild(43).interact();
            } else{
                getWidgets().getWidget(548).getChild(40).interact();
            }
            setActivity("Anti-ban: Open Random tab");
            log("Anti-ban: Open Random tab");
            sleep(200, 500);
        }else if(chances > 0.395 && chances < 0.400){
            if(!getWindow().getwhop()) {
                setThought("I don't like this world!");
                setActivity("Anti-ban: Hop world");
                log("Anti-ban: Hop world");
                if (!getClient().isMembers()) {
                    getWorldHopper().hopWorld(getWorlds().f2p().get(srand.nextInt(getWorlds().f2p().size())).getID(), getWorldHopper().openWorldHopper());
                    getWorldHopper().isWorldHopperOpen();
                    sleep(5000, 7000);
                } else {
                    getWorldHopper().hopWorld(getWorlds().members().get(srand.nextInt(getWorlds().f2p().size())).getID(), getWorldHopper().openWorldHopper());
                    getWorldHopper().isWorldHopperOpen();
                    sleep(5000, 7000);
                }
            }
        }
    }
    public void setStarter(boolean starter) {
        this.starter = starter;
    }
}
