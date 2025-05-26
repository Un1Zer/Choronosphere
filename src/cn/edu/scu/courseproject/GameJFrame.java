package cn.edu.scu.courseproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameJFrame extends JFrame {
    //自身引用
    private static GameJFrame gameJFrame;
    private boolean deathHandled = false;
    //初始化房间编号
    private int floorNumber = 2;
    private int roomNumber = 1;
    //创建角色对象，房间二维数组对象
    private Issac issac = new Issac(20,6,2,10,350,22,32,this);
    private Room[][] rooms = new Room[][]
            {{}, {null,new Room(1,1,this)},
            {null,new Room(2,1,this),new Room(2,2,this),new Room(2,3,this)},
                    {}};
    // 初始化碰撞管理器（16x16网格）
    private CollisionManager collisionManager = new CollisionManager(10);
    // 创建游戏面板
    private GamePanel gamePanel = new GamePanel();
    //定时器
    Timer timer = new Timer(44, new ActionListener() {//约60帧每秒
        @Override
        public void actionPerformed(ActionEvent e) {
            // 保存当前位置，若碰撞到障碍物则维持原位置
            int oldX = issac.getX();
            int oldY = issac.getY();

            issac.updateState(); // 更新角色位置及攻击状态
            for (Enemy enemy : rooms[floorNumber][roomNumber].getEnemies()) {
                enemy.trackPlayer(issac);
                enemy.updateState();
            }

            // 碰撞检测
            collisionManager.loadCollisionMap("Choronosphere\\image\\Collison\\Collision"+floorNumber+"_"+roomNumber+".map");
            int width = issac.getWidth();
            int height = issac.getHeight();
            if (collisionManager.isCollision(issac.getX(), issac.getY(), width, height)) {
                // 发生碰撞，恢复之前位置
                issac.setX(oldX);
                issac.setY(oldY);
            }

            // 检查是否到达出口
            if (collisionManager.isAtExit(issac.getX(), issac.getY(), width, height)) {
                // 角色到达出口
                rooms[floorNumber][roomNumber].exitRoom(collisionManager.getExitNumber(issac.getX(),issac.getY(),width,height),issac);
            }

            // 重绘窗口
            gamePanel.repaint();
        }
    });

    //构造方法和创建新对象
    private GameJFrame()  {
    }

    public static GameJFrame newGameJFrame(){
        gameJFrame = new GameJFrame();
        return gameJFrame;
    }

    //游戏界面构造
    public void initGameJFrame() throws HeadlessException{
        initJFrame();
        initJMenu();

        this.add(gamePanel);
        timer.start(); // 启动定时器

        this.setVisible(true);
    }

    private void initJMenu() {
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();
        //创建菜单
        JMenu option = new JMenu("选项");
        JMenu tutorial = new JMenu("教程");
        JMenu about = new JMenu("关于");
        //创建条目
        JMenuItem backHome = new JMenuItem("返回主菜单");
        JMenuItem viewMap = new JMenuItem("查看本层地图");
        JMenuItem contact = new JMenuItem("联系");
        //添加
        option.add(viewMap);
        option.add(backHome);
        about.add(contact);
        jMenuBar.add(option);
        jMenuBar.add(tutorial);
        jMenuBar.add(about);
        this.setJMenuBar(jMenuBar);
        //backHome
        backHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameJFrame.dispose();
                HomeJFrame.newHomeJFrame().initHomeJFrame();
            }
        });
        //contact
        contact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog jDialog = new JDialog();
                jDialog.setSize(500,500);
                jDialog.setAlwaysOnTop(true);
                jDialog.setLocationRelativeTo(null);
                jDialog.setModal(true);
                jDialog.setVisible(true);
            }
        });
    }

    private void initJFrame() {
        this.setSize(1050,790);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setTitle("Choronosphere");
        //this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    // 内部类：游戏面板(绘制游戏画面)
    private class GamePanel extends JPanel {
        //构造方法
        public GamePanel() {
            setOpaque(false); // 设置面板为透明
            setLayout(null);
            setBounds(0, 0, 1050, 790);
        }
        //绘制方法
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 在这里绘制游戏背景
            g.drawImage(InitImage.roomImg[floorNumber-1][roomNumber-1], 0, 0, 1050, 730, this);
            // 销毁死亡对象，绘制存活的角色与敌人
            if (issac != null) {
                issac.paintSelf(g);
                if (!issac.isAlive && !deathHandled) {
                    deathHandled = true;
                    showGameOverDialog();
                    issac = null;
                    timer.stop();
                }
            }

            ArrayList<Enemy> deadEnemies = new ArrayList<>();
            for (Enemy enemy : rooms[floorNumber][roomNumber].getEnemies()) {
                if (enemy.isAlive) {
                    enemy.paintSelf(g);
                } else {
                    deadEnemies.add(enemy);
                }
            }
            rooms[floorNumber][roomNumber].removeEnemies(deadEnemies);
        }
    }

    //展示死亡窗口
    public void showGameOverDialog() {
        SwingUtilities.invokeLater(() -> {
            int option = JOptionPane.showOptionDialog(
                    this,
                    "你已经死亡！",
                    "游戏结束",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new Object[]{"重新开始", "返回主菜单"},
                    "重新开始"
            );
            if (option == JOptionPane.YES_OPTION) {
                // 重启游戏
                this.dispose(); // 关闭当前窗口
                GameJFrame.newGameJFrame().initGameJFrame(); // 重新启动
            } else if (option == JOptionPane.NO_OPTION) {
                this.dispose();
                HomeJFrame.newHomeJFrame().initHomeJFrame();
            }
        });
    }

    //getter和setter
    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Room getRoom() {
        return rooms[floorNumber][roomNumber];
    }

    public Issac getIssac() {
        return issac;
    }
}
