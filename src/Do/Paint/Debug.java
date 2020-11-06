package Do.Paint;

import Do.Factory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.wrappers.interactive.GameObject;

import java.awt.*;

public class Debug {
    private Factory _factory;
    public Debug(Factory factory) {
        _factory = factory;
    }

    public void DebugDrawn(){
        if(_factory.getMain().isStarter()) {
            Graphics graphics = _factory.getInterfaceGraphics().getGraphics();
            graphics.setColor(Color.white);
            _factory.getInterfaceGraphics().MapDraw(graphics);
            graphics.setColor(Color.RED);
            graphics.drawString("Closest tree: " + _factory.getDebuger().getTreeCloses(), _factory.getInterfaceGraphics().getData().getMainWidth(), _factory.getInterfaceGraphics().getData().getMainHeight() + 200);
            graphics.drawString("Thought: " + _factory.getIU().getThought(), _factory.getInterfaceGraphics().getData().getMainWidth(), _factory.getInterfaceGraphics().getData().getMainHeight() + 210);
            graphics.drawString("Closest bank: " + Bank.getClosestBankLocation(), _factory.getInterfaceGraphics().getData().getMainWidth(), _factory.getInterfaceGraphics().getData().getMainHeight() + 220);
            graphics.drawString("Tree type: " + _factory.getMain().getWindow().getTreetype(), _factory.getInterfaceGraphics().getData().getMainWidth(), _factory.getInterfaceGraphics().getData().getMainHeight() + 230);
            graphics.setColor(Color.cyan);
            GameObject tree = GameObjects.closest(_factory.getMain().getWindow().getTreetype());
            graphics.drawRect((int)tree.getModel().getEntity().getBoundingBox().getBounds().getX(),
                    (int)tree.getModel().getEntity().getBoundingBox().getBounds().getY(),
                    tree.getModel().getEntity().getBoundingBox().getBounds().width,
                    tree.getModel().getEntity().getBoundingBox().getBounds().height);
            graphics.drawString("Name: " + tree.getName(), (int)tree.getModel().getEntity().getBoundingBox().getBounds().getX() + tree.getModel().getEntity().getBoundingBox().getBounds().width + 5, (int)tree.getModel().getEntity().getBoundingBox().getBounds().getY());
            graphics.drawString("ID: " + tree.getID(), (int)tree.getModel().getEntity().getBoundingBox().getBounds().getX() + tree.getModel().getEntity().getBoundingBox().getBounds().width + 5, (int)tree.getModel().getEntity().getBoundingBox().getBounds().getY() + 30);
            graphics.setColor(Color.yellow);
            graphics.drawPolygon(Map.getPolygon(Players.localPlayer().getTile()));
        }
    }
}
