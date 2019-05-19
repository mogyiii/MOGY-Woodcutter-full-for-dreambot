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

import javax.imageio.ImageIO;

@ScriptManifest(category = Category.WOODCUTTING, name = "Mogy Woodcutter", author = "Mogyiii", version = 1.0)

public class MainClass extends AbstractScript {
    private GUI.JWindow window;
    private boolean starter = false;
    private boolean randomarea = true;
    private int logcuts = 0;
    private long startTime;
    private int Startedlevelup;
    private String activity;
    private int treeClosesX;
    private int treeClosesY;
    private boolean first = true;
    private String dot = "...";
    private int i = 0;
    private long second2;
    //SimpleDateFormat format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
    private long StartTrial;
    private int playerpossitionX;
    private int playerpossitionY;
    private boolean burnarea;
    private boolean burnallLogs;
    private int[] Xposition;
    private int[] Yposition;
    private Image mainpaint = getImage("https://i.imgur.com/kZPF0p5.png");
    Area EastVarrokbank = new Area(3250,3422,3256,3420);
    Area WestVarrokbank = new Area(3181,3442,3185,3435);
    Area GrandExchangeBank = new Area(3160, 3493, 3168, 3483);
    Area DraynorBank = new Area(3092, 3246, 3097, 3240);
    Area Edgevillagebank = new Area(3091, 3498, 3099, 3487);
    Area FaladorEastbank = new Area(3009, 3358, 3019, 3355);
    Area RimmingtonShop = new Area(2947, 3217, 2950, 3212);
    Area Selectedarea = new Area();
    //Area burnedarea = new Area();
    @Override
    public void onStart(){
        //Date date = new Date();
        window = new GUI.JWindow(this);
        window.setVisible(true);
        Startedlevelup = getSkills().getRealLevel(Skill.WOODCUTTING);
        log("MOGY's Woodcztter Full version");
        log("Starting...");
        activity = "Starting";
        startTime = System.currentTimeMillis();
        StartTrial = System.currentTimeMillis() / 1000l + 108003;//3hour
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
        if(message.getMessage().contains("You can't light a fire here")){
            burnarea = true;
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
            graphics.drawString("Time running: " + eclapsedtime(), 10, 389);
            graphics.drawString("Woodcutting level: " + getSkills().getRealLevel(Skill.WOODCUTTING) + " (" + (getSkills().getRealLevel(Skill.WOODCUTTING) - Startedlevelup) + ")", 10, 415);
            graphics.drawString("Logs/hour: " + logcuts * (int) (3600D / eclapsedsec()), 10, 440);
            graphics.drawString(activity + dot(), 245, 472);

        }
        if(window.getcheckbox1()){
            graphics.drawString("X:"+treeClosesX,10,100);
            graphics.drawString("Y:"+treeClosesY,10,120);
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
            switch (window.getTreetype()) {
                case "Tree":
                    switch (window.getAreaLocation()){
                        case "GrandExchange":
                            GrandExchangeTreeWoodcutter();
                            break;
                        case "East-Varrock":
                            EastVarrockTreecutter();
                            break;
                        case "Draynor village":
                            remoteBankcutter(DraynorBank, "Tree");
                            break;
                        case "West-Varrock":
                            remoteBankcutter(WestVarrokbank, "Tree");
                            break;
                    }
                    break;
                case "Oak":
                    switch (window.getAreaLocation()){
                        case "GrandExchange":
                            GrandExchangeOakWoodcutter();
                            break;
                        case "East-Varrock":
                            EastVarrockOakWoodcutter();
                            break;
                        case "South-Varrock":
                            SouthVarrockOakWoodcutter();
                            break;
                        case "South-Falador":
                            SouthFaladorOakWoodcutter();
                            break;
                        case "Draynor village":
                            remoteBankcutter(DraynorBank, "Oak");
                            break;
                        case "West-Varrock":
                            remoteBankcutter(WestVarrokbank, "Oak");
                            break;
                    }
                case "Willow":
                    switch (window.getAreaLocation()){
                        case "West-Draynor":
                            WestDraynorWillowWoodcutter();
                            break;
                        case "East-Draynor":
                            EastDraynorWillowWoodcutter();
                            break;
                        case "South-Rimmington":
                            RimmingtonwillowWoodcutter();
                            break;
                        case "North-Lumbridge":
                            RemoteCutter(new Area(3219,3311,3224,3297),"Willow");
                            break;
                    }
                case "Yew":
                    switch (window.getAreaLocation()){
                        case "EdgeVillage":
                            EdgevillageYewWoodcutter();
                            break;
                        case "Exchange":
                            ExchangeYewWoodcutter();
                            break;
                        case "East-Varrock":
                            RemoteYewcutter(EastVarrokbank, new Area(3280,3471,3287,3465));
                            break;
                        case "West-Draynor":
                            remoteYewcutter(DraynorBank);
                            break;
                        case "Falador":
                            remoteYewcutter(FaladorEastbank);
                            break;
                        case "Lumbridge":
                            RemoteYewcutter(DraynorBank, new Area(3134,3265,3141,3226));
                            break;
                    }
            }
        }
        antiBan();
        if(window.getcheckbox1()){
            debug();
        }
        Trial();
        mousecolorfull();

        return ((int) (Math.random() * 200));
    }

    //Execute order 66
    private void GrandExchangeTreeWoodcutter(){
        //Area GrandExchange = new Area(3141, 3520, 3200, 3450);
        if(randomarea){
            Selectedarea = GrandExchangeAreas();
            //log(Selectedarea.toString());
            randomarea = false;
        }
        if(Selectedarea.contains(getLocalPlayer())){
            if(getInventory().isFull()){
                if(window.getburn() && checktinderbox()){
                    activity = "Burn logs";
                    burnlogs("Logs");
                }else {
                    walkingtoarea(GrandExchangeBank);
                    if (Selectedarea.contains(getLocalPlayer())) {
                        banking("Logs");
                    }
                }
                sleep(500, 1500);
            }else{

                if(getLocalPlayer().isAnimating()){
                    cutting();
                }else{
                    chop("Tree");
                    sleep(200, 1000);
                }
            }

        }else{
            walkingtoarea(Selectedarea);
        }
    }
    private void EastVarrockTreecutter(){
        Selectedarea = new Area(3264,3485,3284,3446);

        if(getInventory().isFull()){
            if(window.getburn() && checktinderbox()){
                activity = "Burn logs";
                burnlogs("Logs");
            }else {
                walkingtoarea(EastVarrokbank);
                if (EastVarrokbank.contains(getLocalPlayer())) {
                    banking("Logs");
                }
            }
            sleep(500, 1500);
        }else{
            if(Selectedarea.contains(getLocalPlayer())){
                if(getLocalPlayer().isAnimating()){
                    cutting();
                }else{
                    chop("Tree");
                    sleep(200, 600);
                }
            }else{
                walkingtoarea(Selectedarea);
            }
        }

    }
    private void GrandExchangeOakWoodcutter(){
        Selectedarea = new Area(3189,3467,3197,3458);

        if(getInventory().isFull()){
            if(window.getburn() && checktinderbox()){
                activity = "Burn logs";
                burnlogs("Oak logs");
            }else {
                walkingtoarea(GrandExchangeBank);
                if (GrandExchangeBank.contains(getLocalPlayer())) {
                    banking("Oak logs");
                }
            }
            sleep(500, 1500);
        }else{
            if(Selectedarea.contains(getLocalPlayer())){
                if(getLocalPlayer().isAnimating()){
                    cutting();
                }else{
                    chop("Oak");
                    sleep(200, 1000);
                }
            }else{
                walkingtoarea(Selectedarea);
            }
        }
    }
    private void EastVarrockOakWoodcutter(){
        Selectedarea = new Area(3274,3438,3285,3414);

        if(getInventory().isFull()){
            if(window.getburn() && checktinderbox()){
                activity = "Burn logs";
                burnlogs("Oak logs");
            }else {
                walkingtoarea(EastVarrokbank);
                if (EastVarrokbank.contains(getLocalPlayer())) {
                    banking("Oak logs");
                }
            }
            sleep(500, 1500);
        }else{
            if(Selectedarea.contains(getLocalPlayer())){
                if(getLocalPlayer().isAnimating()){
                    cutting();
                }else{
                    chop("Oak");
                    sleep(200, 1000);
                }
            }else{
                walkingtoarea(Selectedarea);
            }
        }
    }
    private void SouthVarrockOakWoodcutter(){
        Selectedarea = new Area(3200,3373,3217,3358);

        if(getInventory().isFull()){
            if(window.getburn() && checktinderbox()){
                activity = "Burn logs";
                burnlogs("Oak logs");
            }else {
            walkingtoarea(WestVarrokbank);
            if(WestVarrokbank.contains(getLocalPlayer())){
                banking("Oak logs");
            }
            }
            sleep(500, 1500);
        }else{
            if(Selectedarea.contains(getLocalPlayer())){
                if(getLocalPlayer().isAnimating()){
                    cutting();
                }else{
                    chop("Oak");
                    sleep(200, 1000);
                }
            }else{
                walkingtoarea(Selectedarea);
            }
        }
    }
    private void WestDraynorWillowWoodcutter(){
        Selectedarea = new Area(3056,3256,3064,3251);

        if(getInventory().isFull()){
            if(window.getburn() && checktinderbox()){
                activity = "Burn logs";
                burnlogs("Willow logs");
            }else {
                walkingtoarea(DraynorBank);
                if (DraynorBank.contains(getLocalPlayer())) {
                    banking("Willow logs");
                }
            }
            sleep(500, 1500);
        }else{
            if(Selectedarea.contains(getLocalPlayer())){
                if(getLocalPlayer().isAnimating()){
                    cutting();
                }else{
                    chop("Willow");
                    sleep(200, 1000);
                }
            }else{
                walkingtoarea(Selectedarea);
            }
        }
    }
    private void EdgevillageYewWoodcutter(){
        Selectedarea = new Area(3085,3479,3091,3468);

        if(getInventory().isFull()){
            walkingtoarea(Edgevillagebank);
            if(Edgevillagebank.contains(getLocalPlayer())){
                banking("Yew logs");

            }
            sleep(500, 1500);
        }else{
            if(Selectedarea.contains(getLocalPlayer())){
                if(getLocalPlayer().isAnimating()){
                    cutting();
                }else{
                    chop("Yew");
                    sleep(200, 1000);
                }
            }else{
                walkingtoarea(Selectedarea);
            }
        }
    }
    private void EastDraynorWillowWoodcutter(){
        Selectedarea = new Area(3158,3277,3183,3260);

        if(getInventory().isFull()){
            if(window.getburn() && checktinderbox()){
                activity = "Burn logs";
                burnlogs("Willow logs");
            }else {
                walkingtoarea(DraynorBank);
                if (DraynorBank.contains(getLocalPlayer())) {
                    banking("Willow logs");
                }
            }
            sleep(500, 1500);
        }else{
            if(Selectedarea.contains(getLocalPlayer())){
                if(getLocalPlayer().isAnimating()){
                    cutting();
                }else{
                    chop("Willow");
                    sleep(200, 1000);
                }
            }else{
                walkingtoarea(Selectedarea);
            }
        }
    }
    private void RimmingtonwillowWoodcutter(){
        Selectedarea = new Area(2957,3201,2975,3191);

        if(getInventory().isFull()){
            if(window.getburn() && checktinderbox()){
                activity = "Burn logs";
                burnlogs("Willow logs");
            }else {
                walkingtoarea(RimmingtonShop);
                if (RimmingtonShop.contains(getLocalPlayer())) {
                    selling("Willow logs");
                }
            }
            sleep(500, 1500);
        }else{
            if(Selectedarea.contains(getLocalPlayer())){
                if(getLocalPlayer().isAnimating()){
                    cutting();
                }else{
                    chop("Willow");
                    sleep(200, 1000);
                }
            }else{
                walkingtoarea(Selectedarea);
            }
        }
    }
    private void SouthFaladorOakWoodcutter(){
        Selectedarea = new Area(2999,3318,3013,3298);

        if(getInventory().isFull()){
            if(window.getburn() && checktinderbox()){
                activity = "Burn logs";
                burnlogs("Oak logs");
            }else {
                walkingtoarea(FaladorEastbank);
                if (FaladorEastbank.contains(getLocalPlayer())) {
                    banking("Oak logs");
                }
            }
            sleep(500, 1500);
        }else{
            if(Selectedarea.contains(getLocalPlayer())){
                if(getLocalPlayer().isAnimating()){
                    cutting();
                }else{
                    chop("Oak");
                    sleep(200, 1000);
                }
            }else{
                walkingtoarea(Selectedarea);
            }
        }
    }
    private void ExchangeYewWoodcutter(){
        Selectedarea = new Area(3201,3506,3224,3498);

        if(getInventory().isFull()){
            walkingtoarea(GrandExchangeBank);
            if(GrandExchangeBank.contains(getLocalPlayer())){
                banking("Yew logs");
            }
            sleep(500, 1500);
        }else{
            if(Selectedarea.contains(getLocalPlayer())){
                if(getLocalPlayer().isAnimating()){
                    cutting();
                }else{
                    chop("Yew");
                    sleep(200, 1000);
                }
            }else{
                walkingtoarea(Selectedarea);
            }
        }
    }
    private void remoteYewcutter(Area bank){
        if(first){
            Selectedarea = bank;
            first = false;
        }
        if(getInventory().isFull()){
            walkingtoarea(bank);
            if(bank.contains(getLocalPlayer())){
                banking("Yew logs");
                Selectedarea = closesYew();
            }
            sleep(500, 1500);
        }else{
            if(Selectedarea.contains(getLocalPlayer())){
                if(getLocalPlayer().isAnimating()){
                    cutting();
                }else{
                    if(chopping("Yew")){
                        Selectedarea = closesYew();
                        sleep(300, 1000);
                    }
                }
            }else{
                walkingtoarea(Selectedarea);
            }
        }

    }
    private void remoteBankcutter(Area bank, String treetype){
        if(first){
            Selectedarea = bank;
            first = false;
        }
        if(getInventory().isFull()){
            if(window.getburn() && checktinderbox()){
                activity = "Burn logs";
                burnlogs("Logs");
            }else {
                walkingtoarea(bank);
                if (bank.contains(getLocalPlayer())) {
                    banking(treetype + " logs");
                }
            }
            sleep(500, 1500);
        }else{
            if(Selectedarea.contains(getLocalPlayer())){
                if(getLocalPlayer().isAnimating()){
                    cutting();
                }else{
                    if(chopping(treetype)){
                        Selectedarea = new Area(getLocalPlayer().getX() -100,getLocalPlayer().getY() -100,getLocalPlayer().getX() +100,getLocalPlayer().getY()+100);

                        chop(treetype);
                        sleep(300, 1000);
                    }
                }
            }else{
                walkingtoarea(Selectedarea);
            }
        }

    }
    private void RemoteYewcutter(Area bank, Area areana){
        if(first){
            Selectedarea = areana;
            first = false;
        }
        if(getInventory().isFull()){
            walkingtoarea(bank);
            if(bank.contains(getLocalPlayer())){
                banking("Yew logs");
                Selectedarea = areana;
            }
            sleep(500, 1500);
        }else{
            if(Selectedarea.contains(getLocalPlayer())){
                if(getLocalPlayer().isAnimating()){
                    cutting();
                }else{
                    if(chopping("Yew")){
                        Selectedarea = closesYew();
                        sleep(300, 1000);
                    }
                }
            }else{
                walkingtoarea(Selectedarea);
            }
        }

    }
    private void RemoteCutter(Area areana, String Treetype){
        String logname = Treetype + "Logs";
        if(first){
            Selectedarea = areana;
            first = false;
        }
        if(getInventory().isFull()){
            if(window.getburn() && checktinderbox()){
                activity = "Burn logs";
                burnlogs(Treetype + " Logs");
            }else {
                if(getInventory().contains(Treetype + " Logs")){
                    getInventory().dropAll(Treetype + " Logs");
                }
            }
            sleep(500, 1500);
        }else{
            if(Selectedarea.contains(getLocalPlayer())){
                if(getLocalPlayer().isAnimating()){
                    cutting();
                }else{
                    if(chopping(Treetype)){
                        Selectedarea = closesYew();
                        sleep(300, 1000);
                    }
                }
            }else{
                walkingtoarea(Selectedarea);
            }
        }
    }
    //DOITANAKIN
    private void Trial(){
    long seconds = System.currentTimeMillis()/ 1000l;
    //log("StartTrial: "+StartTrial + " seconds:"+seconds);
        if(StartTrial < seconds){
            activity = "Exit";
            onExit();
            super.onExit();
            stop();
        }
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
    private Area closesYew(){
        GameObject tree = getGameObjects().closest("Yew");
        return  new Area(tree.getX()+1, tree.getY()+1, tree.getX()-1,tree.getY()-1);
    }
    private void debug(){
        closestreelocal(window.getTreetype());
    }
    private void closestreelocal(String treename){
        GameObject tree = getGameObjects().closest(treename);
        treeClosesX =tree.getX();
        treeClosesY =tree.getY();
    }
    private String checkaxe(){
        String[] axes = {"Dragon axe", "Rune axe", "Adamant axe", "Mithril axe", "Black axe", "Steel axe", "Iron axe", "Bronze axe"};
        for(int i = 0; i < 6; i++) {
            if (getInventory().contains(axes[i])){
                return axes[i];
            }
        }
        return  "";
    }
    private boolean checktinderbox(){
        if (getInventory().contains("Tinderbox")){
            return true;
        }else{
            return false;
        }
    }
    private void cutting(){
        activity = "Cutting";
        sleep(200, 400);
    }
    private void walkingtoarea(Area Selectedarea){
        activity = "Walking to area!";
        if((getLocalPlayer().getX() == playerpossitionX && getLocalPlayer().getY() == playerpossitionY)){
             getWalking().walk(Selectedarea.getRandomTile());
        }
        if(getWalking().getRunEnergy() >= 20){
            if(!(getWalking().isRunEnabled())){
                getWalking().toggleRun();
            }
        }
        playerpossitionX = getLocalPlayer().getX();
        playerpossitionY = getLocalPlayer().getY();
        sleep(100, 1000);
    }
    private Area GrandExchangeAreas(){
        Random rand = new Random();
        Area GrandExchangeTrees1 = new Area(3141, 3514, 3154, 3492);
        Area GrandExchangeTrees2 = new Area(3141, 3483, 3154, 3469);
        Area GrandExchangeTrees3 = new Area(3176, 3516, 3200, 3489);
        Area GrandExchangeTrees4 = new Area(3146, 3465, 3200, 3489);
        switch (rand.nextInt(4) + 1){
            case 1:
                return GrandExchangeTrees1;
            case 2:
                return GrandExchangeTrees2;
            case 3:
                return GrandExchangeTrees3;
            case 4:
                return GrandExchangeTrees4;
        }
        return GrandExchangeTrees1;
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
            activity = "Please delete Jagexcache folder!";
        }
    }
    private boolean chopping(String treename){
        activity = "Chop";
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
        if(getLocalPlayer().isAnimating()) {
            sleep(1500, 3000);
            burnlogs(logname);
        }else if(burnarea){
            if((getLocalPlayer().getX() == playerpossitionX && getLocalPlayer().getY() == playerpossitionY)){
                getWalking().walk(Selectedarea.getRandomTile());
            }else{
                burnarea = false;
            }
            playerpossitionX = getLocalPlayer().getX();
            playerpossitionY = getLocalPlayer().getY();
            sleep(500, 1000);
            burnlogs(logname);
        }else{
            if (getInventory().contains(logname)){
                getInventory().interact("Tinderbox", "Use");
                getInventory().interact(logname, "Use");
                sleep(1000, 1500);
                burnlogs(logname);
            }
        }

    }
    private void banking(String logname){
            activity = "Banking";
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
    private void antiBan() {
        Random srand = new Random();
        double chances = srand.nextDouble();
        if (chances < 0.096) {
            log("Anti-ban: Changing Camera angle");
            activity ="Anti-ban: Changing Camera angle";
            getCamera().rotateToEvent(srand.nextInt() + 360, srand.nextInt() + 90);
        }else if(chances > 0.096 && chances < 0.192){
            log("Anti-ban: Changing mouse position");
            activity ="Anti-ban: Changing mouse position";
            getMouse().move();
        }else if(chances > 0.192 && chances < 0.195){
            log("Anti-ban: Random sleep");
            activity = "Anti-ban: Random sleeping";
            sleep(30000, 50000);
        }
        else if(chances > 0.200 && chances < 0.256){
            log("Anti-ban: Checking skill XP");
            activity = "Anti-ban: Checking skill XP";
            getSkills().open();
            sleep(100, 500);
            getSkills().hoverSkill(Skill.WOODCUTTING);
            sleep(1000, 1500);
            getKeyboard().typeSpecialKey(1);

        }else if(chances > 0.296 && chances < 0.350){
            activity = "Anti-ban: Move cursor Outside Screen";
            log("Anti-ban: Move cursor Outside Screen");
            getMouse().moveMouseOutsideScreen();
            sleep(2888, 5111);
            getMouse().isMouseInScreen();
        }else if(chances > 0.350 && chances < 0.355){
            activity = "Anti-ban: Open magic tab";
            log("Anti-ban: Open magic tab");
            sleep(200, 500);
        }else if(chances > 0.355 && chances < 0.360){
            activity = "Anti-ban: Hop world";
            log("Anti-ban: Hop world");
            int[] freeworld = {301, 308, 316, 326, 335, 371, 379, 380, 381, 382, 383, 384, 385, 393, 394, 397, 398, 399, 413, 414, 418, 419, 425, 426, 427, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 451, 452, 453, 454, 455, 456, 457, 458, 459, 469, 470, 471, 472, 473, 474, 475, 476, 477, 497, 498, 499, 500, 501, 502, 503, 504};
            getWorldHopper().hopWorld((freeworld[srand.nextInt(freeworld.length)])-300,getWorldHopper().openWorldHopper());

            getWorldHopper().isWorldHopperOpen();
            sleep(5000, 7000);
        }
    }
    private String eclapsedtime(){
        long elapsed;
        elapsed = ((System.currentTimeMillis() - startTime) / 1000);

        return String.format("%02d:%02d:%02d", elapsed / 3600, (elapsed % 3600) / 60, (elapsed % 60));
    }
    private long eclapsedsec(){
        long elapsed;
        elapsed = ((System.currentTimeMillis() - startTime) / 1000);
        return elapsed;
    }
    public void setStarter(boolean starter) {
        this.starter = starter;
    }
    private void mousecolorfull(){
        log(Integer.toString(getMouse().getX()));
    }
}
