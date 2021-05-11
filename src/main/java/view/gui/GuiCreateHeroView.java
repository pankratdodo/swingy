package view.gui;

import models.enemy.Enemy;
import models.hero.Hero;
import utils.ErrorCode;
import utils.exceptions.ThreadErrorException;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    private final int maxWinSizeX = 1750;
    private final int maxWinSizeY = 1000;
    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isDown = false;
    private boolean isUp = false;


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
    public boolean wannaNewHero() {
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
            sleep();
            buttOld.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    returnValue.set(false);
                    buttonPressed.set(true);
                }
            });
            container.add(buttOld);
            buttNew.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    returnValue.set(true);
                    buttonPressed.set(true);
                }
            });
            container.add(buttNew);
            frame.setVisible(true);
        }
        frame.dispose();
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
            sleep();
            buttOld.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonPressed.set(true);
                }
            });
            container.add(buttOld);
            frame.setVisible(true);
        }
        frame.dispose();
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
            sleep();
            buttOld.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonPressed.set(true);
                    hero.setClas(comboBox.getItemAt(comboBox.getSelectedIndex()));
                }
            });
            container.add(buttOld);
            frame.setVisible(true);
        }
        frame.dispose();
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
            sleep();
            buttOld.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonPressed.set(true);
                    chosenName.set(comboBox.getItemAt(comboBox.getSelectedIndex()));
                }
            });
            container.add(buttOld);
            frame.setVisible(true);
        }
        frame.dispose();
        return chosenName.get();
    }

    @Override
    public void printMap(Hero hero, List<Enemy> enemies, int map_size) {
        class GuiPanel extends JPanel {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                Graphics2D g2d = (Graphics2D) g;
                int sq_size = maxWinSizeY / map_size - 1;
                for (int i = 0; i < map_size; i++) {
                    for (int j = 0; j < map_size; j++) {
                        g2d.drawRect(sq_size * j, sq_size * i, sq_size, sq_size);
                    }
                }
                //todo: сделать конкатинацию на нужную картинку
                Image image = getToolkit().getImage(System.getProperty("user.dir").concat("/src/main/resources/").concat("human.jpg"));
                prepareImage(image, this);
                g2d.drawImage(image, hero.getX() * sq_size, hero.getY() * sq_size, sq_size, sq_size, this);
                enemies.forEach(enemy -> {
                    Image enemyImage = getToolkit().getImage(System.getProperty("user.dir").concat("/src/main/resources/").concat("human.jpg"));
                    prepareImage(enemyImage, this);
                    g2d.drawImage(enemyImage, enemy.getX() * sq_size, enemy.getY() * sq_size, sq_size, sq_size, this);
                });
            }
        }
        frame.setTitle("Play");
        frame.getContentPane().removeAll();
        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(0, 1, 2, 2));
        container.add(new GuiPanel());
        frame.setSize(maxWinSizeX, maxWinSizeY);
        frame.setVisible(true);
    }

    @Override
    public Hero move(Hero hero){
        isUp = false;
        isDown = false;
        isLeft = false;
        isRight = false;
        sleep();
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        isUp = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        isDown = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        isLeft = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        isRight = true;
                        break;
                    default:
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        isUp = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        isDown = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        isLeft = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        isRight = false;
                        break;
                    default:
                }
            }
        });
        animate(hero);
        return hero;
    }

    private void animate(Hero hero)
    {
        if (isUp)
        {
            hero.setBeforeX(hero.getX());
            hero.setBeforeY(hero.getY());
            hero.setY(hero.getY() - 1);
        }
        if (isDown)
        {
            hero.setBeforeX(hero.getX());
            hero.setBeforeY(hero.getY());
            hero.setY(hero.getY() + 1);
        }
        if (isLeft)
        {
            hero.setBeforeY(hero.getY());
            hero.setBeforeX(hero.getX());
            hero.setX(hero.getX() - 1);
        }
        if (isRight)
        {
            hero.setBeforeY(hero.getY());
            hero.setBeforeX(hero.getX());
            hero.setX(hero.getX() + 1);
        }
    }

    @Override
    public boolean readyToFight(Hero hero, Enemy enemy) {
        int n = JOptionPane.showConfirmDialog(frame, "Your hero is " + hero.getClas() + ", attack: " + hero.getAttack() + " defence: " + hero.getDefense() + "" +
                "hp: " + hero.getActualHp() + " artefactPower: " + hero.getArtefactAttack() + "\n" + "Your enemy is " + enemy.getRace() + ", attack: " + enemy.getAttack() + " defence: " + enemy.getDefence() +
                " hp: " + enemy.getActualHp() + "\n" + "Do you wanna fight?\nIf you run away, you will take 50 points of damage\n",
                "Do you wanna fight?", JOptionPane.YES_NO_OPTION);
        return n == 0;
    }

    @Override
    public Hero enemyIsDead(Hero hero, Enemy enemy, int heroHp, int enemyHp) {
        if (!enemy.getArtefactName().equals("no one"))
        {
            if (enemy.getArtefactName().equals("helm")) {
                heroHp += hero.getActualHp() + enemy.getArtefactAttack();
                JOptionPane.showMessageDialog(frame, "Enemy is dead.\n" + "Your experience is " + hero.getExp() + " now.\n" +
                                "You got a " + enemy.getArtefactName() + " with power " + enemy.getArtefactAttack() +
                                "The helm gave you health " + enemy.getArtefactAttack() + ". Now you have " + hero.getActualHp(),
                        "Enemy is dead.", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(frame, "Enemy is dead.\n" + "Your experience is " + hero.getExp() + " now.\n" +
                                "You got a " + enemy.getArtefactName() + " with power " + enemy.getArtefactAttack(),
                        "Enemy is dead.", JOptionPane.INFORMATION_MESSAGE);
                hero.setArtefactName(enemy.getArtefactName());
                hero.setArtefactAttack(enemy.getArtefactAttack());
            }
        }
        else
            JOptionPane.showMessageDialog(frame, "Enemy is dead.\n" + "Your experience is " + hero.getExp() + " now.",
                    "Enemy is dead.", JOptionPane.INFORMATION_MESSAGE);
        hero.setActualHp(heroHp);
        return hero;

    }

    @Override
    public void dead() {
        JOptionPane.showMessageDialog(frame, "You are dead.",
                "You are dead.", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void lvlUp(Hero hero) {
        JOptionPane.showMessageDialog(frame, "Your hero is " + hero.getClas() + ", lvl = "  + hero.getLevel() + ", attack: " + hero.getAttack() + " defence: " + hero.getDefense() +
                        " hp: " + hero.getActualHp() + " artefactPower: " + hero.getArtefactAttack(),
                "Your hero is lvl up!", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void win() {
        //todo: конкатинция строки с правильной картинкой
        ImageIcon icon = new ImageIcon(System.getProperty("user.dir").concat("/src/main/resources/").concat("human.jpg"));
        JOptionPane.showMessageDialog(frame, "You are win! Congratulation!",
                "You are win! Congratulation!", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    @Override
    public void printEndOfMap() {
        JOptionPane.showMessageDialog(frame, "Impossible to go outside.",
                "End of map.", JOptionPane.ERROR_MESSAGE);
    }

    private void sleep()
    {
        try {
            Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
            throw new ThreadErrorException("Ошибка задержки потока.", e, ErrorCode.THREAD_ERROR.getCode());
        }
    }
}
