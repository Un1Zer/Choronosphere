package cn.edu.scu.courseproject;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MyMapEditor extends JFrame {
    private final int MAP_WIDTH = 1050;
    private final int MAP_HEIGHT = 730;
    private final int GRID_SIZE = 10;
    private final int GRID_COLS = MAP_WIDTH / GRID_SIZE;
    private final int GRID_ROWS = MAP_HEIGHT / GRID_SIZE;

    private int[][] collisionMap;
    private Image backgroundImg;

    private JPanel editorPanel;
    private JCheckBox showGridCheckBox;
    private JComboBox<String> toolSelector;

    // 当前选择的工具类型，对应碰撞值：0=清除，1=障碍物，2+=多个出口
    private int currentToolValue = 1;

    public MyMapEditor() {
        this.collisionMap = new int[GRID_ROWS][GRID_COLS];
        initUI();
    }

    private void initUI() {
        setTitle("地图编辑器 - 多出口版本");
        setSize(MAP_WIDTH, MAP_HEIGHT + 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createEditorPanel();
        createControlPanel();

        setLocationRelativeTo(null);
    }

    private void createEditorPanel() {
        editorPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (backgroundImg != null) {
                    g.drawImage(backgroundImg, 0, 0, MAP_WIDTH, MAP_HEIGHT, this);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(0, 0, MAP_WIDTH, MAP_HEIGHT);
                }

                if (showGridCheckBox != null && showGridCheckBox.isSelected()) {
                    g.setColor(new Color(100, 100, 100, 100));
                    for (int x = 0; x <= MAP_WIDTH; x += GRID_SIZE)
                        g.drawLine(x, 0, x, MAP_HEIGHT);
                    for (int y = 0; y <= MAP_HEIGHT; y += GRID_SIZE)
                        g.drawLine(0, y, MAP_WIDTH, y);
                }

                for (int row = 0; row < GRID_ROWS; row++) {
                    for (int col = 0; col < GRID_COLS; col++) {
                        int x = col * GRID_SIZE;
                        int y = row * GRID_SIZE;
                        int value = collisionMap[row][col];
                        if (value == 1) {
                            g.setColor(new Color(255, 0, 0, 128)); // 障碍物
                            g.fillRect(x, y, GRID_SIZE, GRID_SIZE);
                        } else if (value >= 2) {
                            float hue = (value - 2) / 7.0f;
                            if (hue >= 1.0f) hue = 0.999f;
                            g.setColor(new Color(Color.HSBtoRGB(hue, 0.6f, 1.0f)));
                            g.fillRect(x, y, GRID_SIZE, GRID_SIZE);
                        }
                    }
                }
            }
        };

        editorPanel.setPreferredSize(new Dimension(MAP_WIDTH, MAP_HEIGHT));
        editorPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateGrid(e);
            }
        });
        editorPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateGrid(e);
            }
        });

        add(editorPanel, BorderLayout.CENTER);
    }

    private void createControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // 替代旧按钮组：使用下拉选择器
        String[] toolOptions = {"障碍物", "出口1", "出口2", "出口3", "出口4", "出口5","出口6","出口7","清除"};
        toolSelector = new JComboBox<>(toolOptions);
        toolSelector.setSelectedIndex(0);

        int[] toolValues = {1, 2, 3, 4, 5, 6, 7, 8, 0}; // 与 toolOptions 一一对应
        toolSelector.addActionListener(e -> {
            int index = toolSelector.getSelectedIndex();
            if (index >= 0 && index < toolValues.length) {
                currentToolValue = toolValues[index];
            }
        });
        controlPanel.add(new JLabel("标记类型:"));
        controlPanel.add(toolSelector);

        showGridCheckBox = new JCheckBox("显示网格", true);
        showGridCheckBox.addActionListener(e -> editorPanel.repaint());
        controlPanel.add(showGridCheckBox);

        JButton loadBgButton = new JButton("加载背景图");
        loadBgButton.addActionListener(e -> loadBackgroundImage());
        controlPanel.add(loadBgButton);

        JButton loadMapButton = new JButton("加载碰撞地图");
        loadMapButton.addActionListener(e -> loadCollisionMap());
        controlPanel.add(loadMapButton);

        JButton saveMapButton = new JButton("保存碰撞地图");
        saveMapButton.addActionListener(e -> saveCollisionMap());
        controlPanel.add(saveMapButton);

        JButton clearAllButton = new JButton("清除所有");
        clearAllButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(this, "确定清除所有标记？", "确认", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) clearCollisionMap();
        });
        controlPanel.add(clearAllButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    private void updateGrid(MouseEvent e) {
        int col = e.getX() / GRID_SIZE;
        int row = e.getY() / GRID_SIZE;
        if (row >= 0 && row < GRID_ROWS && col >= 0 && col < GRID_COLS) {
            collisionMap[row][col] = currentToolValue;
            editorPanel.repaint();
        }
    }

    private void loadBackgroundImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("选择背景图片");
        fileChooser.setFileFilter(new FileNameExtensionFilter("图片文件", "png", "jpg", "jpeg", "gif"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                backgroundImg = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath()).getImage();
                MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(backgroundImg, 0);
                tracker.waitForAll();
                editorPanel.repaint();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "加载失败: " + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveCollisionMap() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("保存碰撞地图");
        fileChooser.setFileFilter(new FileNameExtensionFilter("碰撞地图 (*.map)", "map"));
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.getAbsolutePath().toLowerCase().endsWith(".map"))
                file = new File(file.getAbsolutePath() + ".map");

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeInt(GRID_ROWS);
                oos.writeInt(GRID_COLS);
                for (int[] row : collisionMap)
                    for (int cell : row)
                        oos.writeInt(cell);
                JOptionPane.showMessageDialog(this, "保存成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "保存失败: " + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadCollisionMap() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("加载碰撞地图");
        fileChooser.setFileFilter(new FileNameExtensionFilter("碰撞地图 (*.map)", "map"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                int rows = ois.readInt();
                int cols = ois.readInt();
                if (rows != GRID_ROWS || cols != GRID_COLS) {
                    int opt = JOptionPane.showConfirmDialog(this, "地图尺寸不一致，强制加载？", "警告", JOptionPane.YES_NO_OPTION);
                    if (opt != JOptionPane.YES_OPTION) return;
                }
                clearCollisionMap();
                for (int r = 0; r < rows && r < GRID_ROWS; r++) {
                    for (int c = 0; c < cols && c < GRID_COLS; c++) {
                        collisionMap[r][c] = ois.readInt();
                    }
                }
                editorPanel.repaint();
                JOptionPane.showMessageDialog(this, "加载成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "加载失败: " + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void clearCollisionMap() {
        for (int[] row : collisionMap) {
            java.util.Arrays.fill(row, 0);
        }
        editorPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MyMapEditor editor = new MyMapEditor();
            editor.setVisible(true);
        });
    }
}
