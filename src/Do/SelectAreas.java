package Do;

import org.dreambot.api.methods.map.Area;
import woodcutter.MainClass;

public class SelectAreas{

    private Area currentArea;
    private MainClass mainClass;
    private Factory _factory;
    public SelectAreas(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }
    public Area getCurrentArea() {
        return currentArea;
    }
    public void setCurrentArea(Area currentArea) {
        this.currentArea = currentArea;
    }
    public void SelectedArea(String SelectedArea, String SelectedTree){
        switch (SelectedTree) {
            case "Tree":
                switch (SelectedArea){
                    case "GrandExchange":
                        _factory.getCutters().MultiCutter(_factory.getAreas().getGrandExchangeBank(), _factory.getAreas().getGrandExchangeTree(), SelectedTree, "Logs");
                        break;
                    case "East-Varrock":
                        _factory.getCutters().MultiCutter(_factory.getAreas().getEastVarrokbank(), _factory.getAreas().getEastVarrockTree(), SelectedTree, "Logs");
                        break;
                    case "Draynor village":
                        _factory.getCutters().remoteBankcutter(_factory.getAreas().getDraynorBank());
                        break;
                    case "West-Varrock":
                        _factory.getCutters().remoteBankcutter(_factory.getAreas().getWestVarrokbank());
                        break;
                    case "Current area":
                        _factory.getCutters().MultiTypeCutter(_factory.getAreas().GetClosesBank(), currentArea, "Logs");
                        break;
                }
                break;
            case "Oak":
                switch (SelectedArea){
                    case "GrandExchange":
                        _factory.getCutters().MultiCutter(_factory.getAreas().getGrandExchangeBank(), _factory.getAreas().getGrandExchangeOak(), SelectedTree, SelectedTree + " logs");
                        break;
                    case "East-Varrock":
                        _factory.getCutters().MultiCutter(_factory.getAreas().getEastVarrokbank(), _factory.getAreas().getEastVarrockOak(), SelectedTree, SelectedTree + " logs");
                        break;
                    case "South-Varrock":
                        _factory.getCutters().MultiCutter(_factory.getAreas().getEastVarrokbank(), _factory.getAreas().getSouthVarrockOak(), SelectedTree, SelectedTree +" logs");
                        break;
                    case "South-Falador":
                        _factory.getCutters().MultiCutter(_factory.getAreas().getFaladorEastbank(), _factory.getAreas().getSouthFaladorOak(), SelectedTree, SelectedTree + " logs");
                        break;
                    case "Draynor village":
                        _factory.getCutters().remoteBankcutter(_factory.getAreas().getDraynorBank());
                        break;
                    case "West-Varrock":
                        _factory.getCutters().remoteBankcutter(_factory.getAreas().getWestVarrokbank());
                        break;
                    case "Current area":
                        _factory.getCutters().MultiCutter(_factory.getAreas().GetClosesBank(), currentArea, SelectedTree, SelectedTree + " logs");
                        break;
                }
            case "Willow":
                switch (SelectedArea){
                    case "West-Draynor":
                        _factory.getCutters().MultiCutter(_factory.getAreas().getDraynorBank(), _factory.getAreas().getWestDaynorWillow(), SelectedTree, SelectedTree + " logs");
                        break;
                    case "East-Draynor":
                        _factory.getCutters().MultiCutter(_factory.getAreas().getDraynorBank(), _factory.getAreas().getEastDaynorWillow(), SelectedTree, SelectedTree + " logs");
                        break;
                    case "South-Draynor":
                        _factory.getCutters().MultiCutter(_factory.getAreas().getDraynorBank(), _factory.getAreas().getSouthDaynorWillow(), SelectedTree, SelectedTree + " logs");
                        break;
                    case "South-Rimmington":
                        _factory.getCutters().MultiCutter(_factory.getAreas().getFaladorEastbank(), _factory.getAreas().getSouthRimmingtonWillow(), SelectedTree, SelectedTree + " logs");
                        break;
                    case "North-Lumbridge":
                        _factory.getCutters().MultiCutter(_factory.getAreas().GetClosesBank(), _factory.getAreas().getNorthLumbrigeWillow(), SelectedTree, SelectedTree + " logs");
                        break;
                    case "Current area":
                        _factory.getCutters().MultiCutter(_factory.getAreas().GetClosesBank(), currentArea, SelectedTree, SelectedTree + " logs");
                        break;
                }
            case "Yew":
                switch (SelectedArea){
                    case "EdgeVillage":
                        _factory.getCutters().MultiCutter(_factory.getAreas().getEdgevillagebank(),  _factory.getAreas().getEdviligeYew(), SelectedTree, SelectedTree + " logs");
                        break;
                    case "Exchange":
                        _factory.getCutters().MultiCutter(_factory.getAreas().getGrandExchangeBank(),  _factory.getAreas().getExchangeYew(), SelectedTree, SelectedTree + " logs");
                        break;
                    case "East-Varrock":
                        _factory.getCutters().remoteBankcutter(_factory.getAreas().getEastVarrokbank());
                        break;
                    case "West-Draynor":
                        _factory.getCutters().remoteBankcutter(_factory.getAreas().getDraynorBank());
                        break;
                    case "Falador":
                        _factory.getCutters().remoteBankcutter(_factory.getAreas().getFaladorEastbank());
                        break;
                    case "Lumbridge":
                        _factory.getCutters().MultiCutter(_factory.getAreas().getEastVarrokbank(),  _factory.getAreas().getLumbrigeYew(), SelectedTree, SelectedTree + " logs");
                        break;
                    case "Current area":
                        _factory.getCutters().MultiCutter(_factory.getAreas().GetClosesBank(), currentArea, SelectedTree, SelectedTree + " logs");
                        break;
                }
            case "Magic tree":
                switch (SelectedArea) {
                    case "Mage Training Area":
                        _factory.getCutters().MultiCutter(_factory.getAreas().getDuelArenaBank(), _factory.getAreas().getMagicTrainingMagicTree(), SelectedTree, "Magic logs");
                        break;
                    case "Current area":
                        _factory.getCutters().MultiCutter(_factory.getAreas().GetClosesBank(), currentArea, SelectedTree, SelectedTree + " logs");
                        break;
                }
            case "Achey tree":
                switch (SelectedArea) {
                    case "Current area":
                        _factory.getCutters().MultiCutter(_factory.getAreas().GetClosesBank(), currentArea, SelectedTree, SelectedTree + " logs");
                        break;
                }
            case "Teak tree":
                switch (SelectedArea) {
                    case "Current area":
                        _factory.getCutters().MultiCutter(_factory.getAreas().GetClosesBank(), currentArea, SelectedTree, SelectedTree + " logs");
                        break;
                }
            case "Maple tree":
                switch (SelectedArea) {
                    case "Current area":
                        _factory.getCutters().MultiCutter(_factory.getAreas().GetClosesBank(), currentArea, SelectedTree, SelectedTree + " logs");
                        break;
                }
            case "Arctic pine":
                switch (SelectedArea) {
                    case "Current area":
                        _factory.getCutters().MultiCutter(_factory.getAreas().GetClosesBank(), currentArea, SelectedTree, SelectedTree + " logs");
                        break;
                }
            case "Hollow tree":
                switch (SelectedArea) {
                    case "Current area":
                        _factory.getCutters().MultiCutter(_factory.getAreas().GetClosesBank(), currentArea, SelectedTree, SelectedTree + " logs");
                        break;
                }
            case "Mahogany tree":
                switch (SelectedArea) {
                    case "Current area":
                        _factory.getCutters().MultiCutter(_factory.getAreas().GetClosesBank(), currentArea, SelectedTree, SelectedTree + " logs");
                        break;
                }
            case "Redwood tree":
                switch (SelectedArea) {
                    case "Current area":
                        _factory.getCutters().MultiCutter(_factory.getAreas().GetClosesBank(), currentArea, SelectedTree, SelectedTree + " logs");
                        break;
                }
        }
    }
}
