package cn.edu.scu.courseproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeJFrame extends JFrame{
    private static HomeJFrame homeJFrame;
    private JLayeredPane jLayeredPane = new JLayeredPane();

    private HomeJFrame() {
    }

    public void initHomeJFrame()  throws HeadlessException{
        initJFrame();
        // 设置分层面板
        jLayeredPane.setBounds(0, 0, 1050, 730);
        this.add(jLayeredPane);
        initText();
        initButton();
        initBackGround();


        this.setVisible(true);
    }

    public static HomeJFrame newHomeJFrame(){
        homeJFrame = new HomeJFrame();
        return homeJFrame;
    }

    private void initBackGround() {
        // 在底层添加背景
        ImageIcon homeBk = new ImageIcon("Choronosphere\\image\\GameAssets\\Background\\portal.png");
        JLabel backgroundLabel = new JLabel(homeBk);
        backgroundLabel.setBounds(0, 0, 1050, 730);
        jLayeredPane.add(backgroundLabel, Integer.valueOf(0)); // 底层
    }

    // 修改initButton方法，将按钮添加到分层面板而不是内容面板
    private void initButton() {
        JButton startBt = new JButton("开始游戏");
        JButton tutorialBt = new JButton("游戏教程");
        JButton aboutBt = new JButton("关于我们");
        JButton exitBt = new JButton("退出游戏");

        setButton(startBt,250);
        setButton(tutorialBt,300);
        setButton(aboutBt,350);
        setButton(exitBt,400);
        //start
        startBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeJFrame.dispose();
                GameJFrame.newGameJFrame().initGameJFrame();
            }
        });
        //exit
        exitBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //about
        aboutBt.addActionListener(new ActionListener() {
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
        this.setSize(1050,738);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setTitle("Welcome to Choronosphere!");
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setButton(JButton jButton,int y){
        jButton.setBounds(480,y,90,20);
        jButton.setVisible(true);
        // 将按钮添加到分层面板而不是内容面板
        jLayeredPane.add(jButton, Integer.valueOf(2)); // 最高层
    }

    // 自定义面板，用来绘制文字
    class MyPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                // 加载字体文件
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File("Choronosphere\\image\\GameAssets\\Fonts\\GothicPixels.ttf"));
                customFont = customFont.deriveFont(66f); // 设置字体大小
                g.setFont(customFont);
                g.setColor(Color.white);
            } catch (Exception e) {
                e.printStackTrace();
                g.setFont(new Font("仿宋", Font.BOLD, 66)); // 如果失败，用默认字体
            }
            g.drawString("Choronosphere", 300, 150);
        }
    }

    public void initText(){
        // 在较高层添加文本面板
        MyPanel textPanel = new MyPanel();
        textPanel.setBounds(0, 0, 1050, 730);
        textPanel.setOpaque(false);
        jLayeredPane.add(textPanel, Integer.valueOf(1)); // 比背景更高的层
    }

    public static void main(String[] args) {
        HomeJFrame.newHomeJFrame().initHomeJFrame();
    }
}
