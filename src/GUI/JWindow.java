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
    private woodcutter.MainClass ctx;
    private boolean debugger = false;
    private boolean gui = true;
    private boolean burn = false;
    public JWindow(MainClass main) {
        this.ctx = main;
        initComponents();
        comboBox1.addItem("Magic tree");
    }

    private void button1ActionPerformed(ActionEvent e) {
        ctx.setStarter(true);
        setVisible(false);
    }

    private void comboBox1ActionPerformed(ActionEvent e) {
        checkBox3.setEnabled(true);
        switch (getTreetype()){
            case "Tree":
                comboBox2.removeAllItems();
                comboBox2.addItem("GrandExchange");
                comboBox2.addItem("East-Varrock");
                comboBox2.addItem("Draynor village");
                comboBox2.addItem("West-Varrock");
                comboBox2.addItem("Current area");
                break;
            case "Oak":
                comboBox2.removeAllItems();
                comboBox2.addItem("GrandExchange");
                comboBox2.addItem("East-Varrock");
                comboBox2.addItem("South-Varrock");
                comboBox2.addItem("South-Falador");
                comboBox2.addItem("Draynor village");
                comboBox2.addItem("West-Varrock");
                comboBox2.addItem("Current area");
                break;
            case "Willow":
                comboBox2.removeAllItems();
                comboBox2.addItem("West-Draynor");
                comboBox2.addItem("South-Draynor");
                comboBox2.addItem("East-Draynor");
                comboBox2.addItem("South-Rimmington");
                comboBox2.addItem("North-Lumbridge");
                comboBox2.addItem("Current area");
                break;
            case "Yew":
                comboBox2.removeAllItems();
                comboBox2.addItem("EdgeVillage");
                comboBox2.addItem("Exchange");
                comboBox2.addItem("East-Varrock");
                comboBox2.addItem("West-Draynor");
                comboBox2.addItem("Falador");
                comboBox2.addItem("Lumbridge");
                comboBox2.addItem("Current area");
                checkBox3.setEnabled(false);
                break;
            case "Magic tree":
                comboBox2.removeAllItems();
                comboBox2.addItem("Mage Training Area");
                checkBox3.setEnabled(false);
        }
    }
    public String getTreetype(){
        return comboBox1.getSelectedItem().toString();
    }
    public String getAreaLocation(){
        return comboBox2.getSelectedItem().toString();
    }

    private void checkBox1ActionPerformed(ActionEvent e) {
        if(checkBox1.isSelected()){
            debugger = true;
        }else{
            debugger = false;
        }
    }
    public boolean getcheckbox1(){
        return debugger;
    }
    public boolean getgui(){
        return gui;
    }
    public boolean getburn(){
        return burn;
    }
    private void checkBox2ActionPerformed(ActionEvent e) {
        if(checkBox2.isSelected()){
            gui = true;
        }else{
            gui = false;
        }
    }

    private void checkBox3ActionPerformed(ActionEvent e) {
        if(checkBox3.isSelected()){
            burn = true;
        }else{
            burn = false;
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - hfghfgh
        comboBox1 = new JComboBox<>();
        label1 = new JLabel();
        comboBox2 = new JComboBox();
        label2 = new JLabel();
        button1 = new JButton();
        checkBox1 = new JCheckBox();
        checkBox2 = new JCheckBox();
        checkBox3 = new JCheckBox();

        //======== this ========
        setTitle("Mogy WoodCutter");
        setBackground(Color.black);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "Select",
            "Tree",
            "Oak",
            "Willow",
            "Yew"
        }));
        comboBox1.addActionListener(e -> comboBox1ActionPerformed(e));
        contentPane.add(comboBox1);
        comboBox1.setBounds(70, 30, 170, comboBox1.getPreferredSize().height);

        //---- label1 ----
        label1.setText("Tree type");
        contentPane.add(label1);
        label1.setBounds(10, 35, label1.getPreferredSize().width, 20);
        contentPane.add(comboBox2);
        comboBox2.setBounds(70, 80, 170, comboBox2.getPreferredSize().height);

        //---- label2 ----
        label2.setText("Location");
        contentPane.add(label2);
        label2.setBounds(10, 85, label2.getPreferredSize().width, 20);

        //---- button1 ----
        button1.setText("Start");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(65, 215, 170, button1.getPreferredSize().height);

        //---- checkBox1 ----
        checkBox1.setText("Debug");
        checkBox1.addActionListener(e -> checkBox1ActionPerformed(e));
        contentPane.add(checkBox1);
        checkBox1.setBounds(120, 260, 80, checkBox1.getPreferredSize().height);

        //---- checkBox2 ----
        checkBox2.setText("GUI");
        checkBox2.setSelected(true);
        checkBox2.addActionListener(e -> checkBox2ActionPerformed(e));
        contentPane.add(checkBox2);
        checkBox2.setBounds(115, 180, 65, checkBox2.getPreferredSize().height);

        //---- checkBox3 ----
        checkBox3.setText("Burn logs");
        checkBox3.addActionListener(e -> checkBox3ActionPerformed(e));
        contentPane.add(checkBox3);
        checkBox3.setBounds(new Rectangle(new Point(115, 125), checkBox3.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(285, 330));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - hfghfgh
    private JComboBox<String> comboBox1;
    private JLabel label1;
    private JComboBox comboBox2;
    private JLabel label2;
    private JButton button1;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
