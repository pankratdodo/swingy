package view.gui;

import models.enemy.Enemy;
import models.hero.Hero;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class GuiCreateHeroView  implements View {
    private JButton buttOld = new JButton();
    private JButton buttNew = new JButton();
    private JLabel label = new JLabel();
    private JFrame frame = new JFrame();
    private JTextField input = new JTextField();
    private JComboBox<String> comboBox = new JComboBox();

    private JFrame createFrame()
    {
        frame.setBounds(100, 100, 250, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 200);
        return frame;
    }

    @Override
    public boolean oldOrNewHero() {
        AtomicBoolean returnValue = new AtomicBoolean(false);
        AtomicBoolean buttonPressed = new AtomicBoolean(false);
        buttOld = new JButton("OLD");
        buttNew = new JButton("NEW");
        label = new JLabel("Choose old or new hero:");
        frame = new JFrame("Old or new hero");
        createFrame();
        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));
        container.add(label);
        while (!buttonPressed.get()) {
            buttOld.addActionListener(e -> {
                returnValue.set(false);
                buttonPressed.set(true);
            });
            container.add(buttOld);
            buttNew.addActionListener(e -> {
                returnValue.set(true);
                buttonPressed.set(true);
            });
            container.add(buttNew);
            frame.setVisible(true);
        }
        frame.setVisible(false);
        return returnValue.get();
    }

    @Override
    public String createHeroName() {
        buttOld = new JButton("Create hero");
        label = new JLabel("Print name of your hero:");
        input = new JTextField("", 100);
        AtomicBoolean buttonPressed = new AtomicBoolean(false);
        frame = new JFrame("Create name of hero");
        createFrame();
        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));
        container.add(label);
        container.add(input);
        while (!buttonPressed.get()) {
            buttOld.addActionListener(e -> {
                buttonPressed.set(true);
            });
            container.add(buttOld);
            frame.setVisible(true);
        }
        frame.setVisible(false);
        return input.getText();
    }


    @Override
    public Hero createHeroClass(Hero hero) {
        buttOld = new JButton("Choose race");
        label = new JLabel("Choose race of hero:");
        comboBox.addItem("Leon");
        comboBox.addItem("Jessie");
        comboBox.addItem("Edgar");
        comboBox.addItem("Poco");
        AtomicBoolean buttonPressed = new AtomicBoolean(false);
        frame = new JFrame("Create race of hero");
        createFrame();
        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));
        container.add(label);
        container.add(comboBox);
        while (!buttonPressed.get()) {
            buttOld.addActionListener(e -> {
                buttonPressed.set(true);
                hero.setClas(comboBox.getItemAt(comboBox.getSelectedIndex()));
            });
            container.add(buttOld);
            frame.setVisible(true);
        }
        frame.setVisible(false);
        return hero;
    }

    @Override
    public String chooseHeroName(List<String> names) {
        if (names.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Hero not found. Create new one.",
                    "Not found", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        AtomicReference<String> chosenName = new AtomicReference<>();
        frame = new JFrame("Choose hero from existing");
        buttOld = new JButton("Choose hero");
        label = new JLabel("Choose hero:");
        names.forEach(name -> comboBox.addItem(name));
        AtomicBoolean buttonPressed = new AtomicBoolean(false);
        createFrame();
        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));
        container.add(label);
        container.add(comboBox);
        while (!buttonPressed.get()) {
            buttOld.addActionListener(e -> {
                buttonPressed.set(true);
                chosenName.set(comboBox.getItemAt(comboBox.getSelectedIndex()));
            });
            container.add(buttOld);
            frame.setVisible(true);
        }
        frame.setVisible(false);
        return chosenName.get();
    }

    @Override
    public Hero printMap(Hero hero, List<Enemy> enemies, int map_size) {
        return new Hero();

    }

    @Override
    public Hero move(Hero hero, List<Enemy> enemies, int map_size){
        return new Hero();
    }

    @Override
    public boolean readyToFight(Hero hero, Enemy enemy) {
        return false;
    }

    @Override
    public Hero enemyIsDead(Hero hero, Enemy enemy, int heroHp, int enemyHp) {
        return new Hero();

    }

    @Override
    public void dead() {
        JOptionPane.showMessageDialog(frame, "You are dead.",
                "You are dead.", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void lvlUp(Hero hero) {
    }

    @Override
    public void win() {

    }
}
