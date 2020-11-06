package Do.Paint;

import Do.Enums.DrawSkill;
import Do.Factory;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Buttons {
    private Factory _factory;
    public Buttons(Factory factory) {
        this._factory = factory;
        _factory.getInterfaceGraphics().setSelectedDrawSkill(DrawSkill.WoodCutting);
    }
    //Close/Open
    Point p;
    Rectangle close = new Rectangle(595, 2, 13, 13);
    Rectangle DebugBTN = new Rectangle(594, 20, 13, 13);
    Rectangle Woodcutter = new Rectangle(564, 154, 42, 42);
    Rectangle FireMaking = new Rectangle(515, 154, 42, 42);
    Rectangle HoverBtn;
    public void ButtonsHandle(MouseEvent event){
        ClickButtons(event);
        HoverButtons(event);
    }
    public void ClickButtons(MouseEvent event){
        p = event.getPoint();
        if (close.contains(p)) {
            _factory.getMain().getWindow().setGui(!_factory.getMain().getWindow().getgui());
        } else if (DebugBTN.contains(p)) {
            _factory.getMain().getWindow().setDebugger(!_factory.getMain().getWindow().getisenableDebbuger());
        }else if (Woodcutter.contains(p)){
            _factory.getInterfaceGraphics().setSelectedDrawSkill(DrawSkill.WoodCutting);
        }else if (FireMaking.contains(p)){
            _factory.getInterfaceGraphics().setSelectedDrawSkill(DrawSkill.FireMaking);
        }
    }
    public void HoverButtons(MouseEvent event){
        Point pointHover = new Point();
        pointHover.setLocation(event.getX(), event.getY());
        if (close.contains(pointHover)) {
            HoverBtn = close;
        }else if (DebugBTN.contains(pointHover)) {
            HoverBtn = DebugBTN;
        }else if (Woodcutter.contains(pointHover)){
            HoverBtn = Woodcutter;
        }else if (FireMaking.contains(pointHover)){
            HoverBtn = FireMaking;
        }else{
            HoverBtn = new Rectangle(0, 0, 0, 0);
        }
    }

    public Rectangle getHoverBtn() {
        return HoverBtn;
    }
}
