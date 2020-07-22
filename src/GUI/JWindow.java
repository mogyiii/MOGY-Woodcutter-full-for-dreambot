/*
 * Created by JFormDesigner on Sat Dec 22 20:15:55 CET 2018
 */

package GUI;

import woodcutter.MainClass;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Mogyiii
 */
public class JWindow extends JFrame {
    private woodcutter.MainClass main;
    private boolean debugger = false;
    private boolean gui = true;
    private boolean burn = false;
    private boolean wHop = false;
    public JWindow(MainClass main) {
        this.main = main;
        initComponents();
        TreeListBox.addItem("Achey tree");
        TreeListBox.addItem("Teak tree");
        TreeListBox.addItem("Maple tree");
        TreeListBox.addItem("Arctic pine");
        TreeListBox.addItem("Hollow tree");
        TreeListBox.addItem("Mahogany tree");
        TreeListBox.addItem("Magic tree");
        TreeListBox.addItem("Redwood tree");
    }

    private void StartButtonisClicked(ActionEvent e) {
        main.setStarter(true);
        setVisible(false);
    }

    private void TreeListBoxHasChange(ActionEvent e) {
        BurnCheckBox.setEnabled(true);
        switch (getTreetype()){
            case "Tree":
                AreaListBox.removeAllItems();
                AreaListBox.addItem("GrandExchange");
                AreaListBox.addItem("East-Varrock");
                AreaListBox.addItem("Draynor village");
                AreaListBox.addItem("West-Varrock");
                AreaListBox.addItem("Current area");
                break;
            case "Achey tree":
                AreaListBox.removeAllItems();
                AreaListBox.addItem("Current area");
                break;
            case "Oak":
                AreaListBox.removeAllItems();
                AreaListBox.addItem("GrandExchange");
                AreaListBox.addItem("East-Varrock");
                AreaListBox.addItem("South-Varrock");
                AreaListBox.addItem("South-Falador");
                AreaListBox.addItem("Draynor village");
                AreaListBox.addItem("West-Varrock");
                AreaListBox.addItem("Current area");
                break;
            case "Willow":
                AreaListBox.removeAllItems();
                AreaListBox.addItem("West-Draynor");
                AreaListBox.addItem("South-Draynor");
                AreaListBox.addItem("East-Draynor");
                AreaListBox.addItem("South-Rimmington");
                AreaListBox.addItem("North-Lumbridge");
                AreaListBox.addItem("Current area");
                break;
            case "Teak tree":
                AreaListBox.removeAllItems();
                AreaListBox.addItem("Current area");
                break;
            case "Maple tree":
                AreaListBox.removeAllItems();
                AreaListBox.addItem("Current area");
                break;
            case "Arctic pine":
                AreaListBox.removeAllItems();
                AreaListBox.addItem("Current area");
                break;
            case "Hollow tree":
                AreaListBox.removeAllItems();
                AreaListBox.addItem("Current area");
                break;
            case "Mahogany tree":
                AreaListBox.removeAllItems();
                AreaListBox.addItem("Current area");
                break;
            case "Yew":
                AreaListBox.removeAllItems();
                AreaListBox.addItem("EdgeVillage");
                AreaListBox.addItem("Exchange");
                AreaListBox.addItem("East-Varrock");
                AreaListBox.addItem("West-Draynor");
                AreaListBox.addItem("Falador");
                AreaListBox.addItem("Lumbridge");
                AreaListBox.addItem("Current area");
                break;
            case "Magic tree":
                AreaListBox.removeAllItems();
                AreaListBox.addItem("Mage Training Area");
                AreaListBox.addItem("Current area");
                break;
            case "Redwood tree":
                AreaListBox.removeAllItems();
                AreaListBox.addItem("Current area");
                break;

        }
    }
    public String getTreetype(){
        return TreeListBox.getSelectedItem().toString();
    }
    public String getAreaLocation(){
        return AreaListBox.getSelectedItem().toString();
    }

    private void DebugCheckBoxisChanged(ActionEvent e) {
        if(EnableDebugCheckBox.isSelected()){
            debugger = true;
        }else{
            debugger = false;
        }
    }
    public boolean getisenableDebbuger(){
        return debugger;
    }
    public boolean getgui(){
        return gui;
    }
    public boolean getburn(){
        return burn;
    }
    public int getAreaSize(){
        return Integer.parseInt(AreaSizeTextBox.getText());
    }
    public boolean getwhop(){
        return wHop;
    }
    private void GuiCheckBoxisChanged(ActionEvent e) {
        if(EnableGuiCheckBox.isSelected()){
            gui = true;
        }else{
            gui = false;
        }
    }
    private void BurnCheckBoxisChanged(ActionEvent e) {
        if(BurnCheckBox.isSelected()){
            burn = true;
        }else{
            burn = false;
        }
    }
    private void WorldHopCheckBoxisChanged(ActionEvent e) {
        if(WorldHopCheckBox.isSelected()){
            wHop = true;
        }else{
            wHop = false;
        }
    }
    private void initComponents() {
        TreeListBox = new JComboBox<>();
        TreeTypeText = new JLabel();
        AreaListBox = new JComboBox();
        AreaSizeText = new JLabel();
        StartButton = new JButton();
        EnableDebugCheckBox = new JCheckBox();
        EnableGuiCheckBox = new JCheckBox();
        BurnCheckBox = new JCheckBox();
        WorldHopCheckBox = new JCheckBox();
        AreaSizeTextBox = new JTextField();
        //======== this ========
        setTitle("Mogy WoodCutter");
        setBackground(Color.black);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- comboBox1 ----
        TreeListBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "Select",
            "Tree",
            "Oak",
            "Willow",
            "Yew"
        }));
        TreeListBox.addActionListener(e -> TreeListBoxHasChange(e));
        contentPane.add(TreeListBox);
        TreeListBox.setBounds(70, 30, 170, TreeListBox.getPreferredSize().height);

        //---- TreeTypeText ----
        TreeTypeText.setText("Tree type");
        contentPane.add(TreeTypeText);
        TreeTypeText.setBounds(10, 35, TreeTypeText.getPreferredSize().width, 20);
        contentPane.add(AreaListBox);
        AreaListBox.setBounds(70, 80, 170, AreaListBox.getPreferredSize().height);

        //---- AreaSizeText ----
        AreaSizeText.setText("Location");
        contentPane.add(AreaSizeText);
        AreaSizeText.setBounds(10, 85, AreaSizeText.getPreferredSize().width, 20);

        //---- StartButton ----
        StartButton.setText("Start");
        StartButton.addActionListener(e -> StartButtonisClicked(e));
        contentPane.add(StartButton);
        StartButton.setBounds(65, 225, 170, StartButton.getPreferredSize().height);

        //---- EnableDebugCheckBox ----
        EnableDebugCheckBox.setText("Debug");
        EnableDebugCheckBox.addActionListener(e -> DebugCheckBoxisChanged(e));
        contentPane.add(EnableDebugCheckBox);
        EnableDebugCheckBox.setBounds(120, 260, 80, EnableDebugCheckBox.getPreferredSize().height);

        //---- EnableGuiCheckBox ----
        EnableGuiCheckBox.setText("GUI");
        EnableGuiCheckBox.setSelected(true);
        EnableGuiCheckBox.addActionListener(e -> GuiCheckBoxisChanged(e));
        contentPane.add(EnableGuiCheckBox);
        EnableGuiCheckBox.setBounds(115, 200, 65, EnableGuiCheckBox.getPreferredSize().height);

        //---- BurnCheckBox ----
        BurnCheckBox.setText("Burn/Drop logs");
        BurnCheckBox.addActionListener(e -> BurnCheckBoxisChanged(e));
        contentPane.add(BurnCheckBox);
        BurnCheckBox.setBounds(new Rectangle(new Point(115, 145), BurnCheckBox.getPreferredSize()));
        //---- WorldHopCheckBox ----
        WorldHopCheckBox.setText("Disable World-Hop");
        WorldHopCheckBox.addActionListener(e -> WorldHopCheckBoxisChanged(e));
        contentPane.add(WorldHopCheckBox);
        WorldHopCheckBox.setBounds(new Rectangle(new Point(115, 170), WorldHopCheckBox.getPreferredSize()));
        //---- AreaSizeTextBox ----
        AreaSizeTextBox.setText("8");
        contentPane.add(AreaSizeTextBox);
        AreaSizeTextBox.setBounds(115, 115, 50, StartButton.getPreferredSize().height);
        //---- AreaSizeText ----
        AreaSizeText.setText("Area Size");
        contentPane.add(AreaSizeText);
        AreaSizeText.setBounds(50, 120, AreaSizeText.getPreferredSize().width, 15);

        contentPane.setPreferredSize(new Dimension(285, 330));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    private JComboBox<String> TreeListBox;
    private JLabel TreeTypeText;
    private JComboBox AreaListBox;
    private JLabel AreaSizeText;
    private JButton StartButton;
    private JCheckBox EnableDebugCheckBox;
    private JCheckBox EnableGuiCheckBox;
    private JCheckBox BurnCheckBox;
    private JCheckBox WorldHopCheckBox;
    private JTextField AreaSizeTextBox;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
