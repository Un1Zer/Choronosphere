package cn.edu.scu.courseproject;

import java.io.*;

/**
 * 碰撞检测管理器
 * 负责加载碰撞地图和提供碰撞检测服务
 */
public class CollisionManager {
    // 碰撞地图数据
    private int[][] collisionMap;

    // 地图尺寸
    private int mapRows;
    private int mapCols;

    // 网格尺寸
    private final int GRID_SIZE;

    // 碰撞类型常量
    public static final int EMPTY = 0;
    public static final int OBSTACLE = 1;
    public static final int EXIT = 2;

    /**
     * 构造函数
     * @param gridSize 网格尺寸
     */
    public CollisionManager(int gridSize) {
        this.GRID_SIZE = gridSize;
    }

    /**
     * 加载碰撞地图
     * @param filePath 地图文件路径
     * @return 是否加载成功
     */
    public boolean loadCollisionMap(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            // 读取地图尺寸
            mapRows = ois.readInt();
            mapCols = ois.readInt();

            // 初始化碰撞地图
            collisionMap = new int[mapRows][mapCols];

            // 读取碰撞数据
            for (int row = 0; row < mapRows; row++) {
                for (int col = 0; col < mapCols; col++) {
                    collisionMap[row][col] = ois.readInt();
                }
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 检测是否碰撞（遍历覆盖区域网格）
     * @param x 游戏世界中的X坐标
     * @param y 游戏世界中的Y坐标
     * @param width 实体宽度
     * @param height 实体高度
     * @return 是否发生碰撞
     */
    public boolean isCollision(int x, int y, int width, int height) {
        // 检查碰撞地图是否已加载
        if (collisionMap == null) {
            return false;
        }

        // 计算实体覆盖区域的起始和结束网格坐标
        int startRow = y / GRID_SIZE;
        int endRow = (y + height - 1) / GRID_SIZE;
        int startCol = x / GRID_SIZE;
        int endCol = (x + width - 1) / GRID_SIZE;

        // 检查是否越界（整个实体有一部分越界也视为碰撞）
        if (startRow < 0 || startCol < 0 || endRow >= mapRows || endCol >= mapCols) {
            return true;
        }

        // 遍历覆盖区域所有网格，检查是否有障碍物
        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                if (checkObstacle(row, col)) {
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * 检查指定网格是否为障碍物
     * @param row 行
     * @param col 列
     * @return 是否为障碍物
     */
    private boolean checkObstacle(int row, int col) {
        // 检查是否越界
        if (row < 0 || col < 0 || row >= mapRows || col >= mapCols) {
            return true; // 超出边界视为障碍物
        }

        // 检查是否是障碍物
        return collisionMap[row][col] == OBSTACLE;
    }

    /**
     * 检查是否在出口
     * @param x 游戏世界中的X坐标
     * @param y 游戏世界中的Y坐标
     * @param width 实体宽度
     * @param height 实体高度
     * @return 是否在出口
     */
    public boolean isAtExit(int x, int y, int width, int height) {
        // 检查碰撞地图是否已加载
        if (collisionMap == null) {
            return false;
        }

        // 计算实体中心点
        int centerX = x + width / 2;
        int centerY = y + height / 2;

        // 计算中心点所在的网格
        int centerRow = centerY / GRID_SIZE;
        int centerCol = centerX / GRID_SIZE;

        // 检查边界
        if (centerRow < 0 || centerCol < 0 ||
                centerRow >= mapRows || centerCol >= mapCols) {
            return false;
        }

        // 检查是否是出口
        return collisionMap[centerRow][centerCol] >= EXIT;
    }

    public int getExitNumber(int x,int y,int width,int height){
        // 计算实体中心点
        int centerX = x + width / 2;
        int centerY = y + height / 2;

        // 计算中心点所在的网格
        int centerRow = centerY / GRID_SIZE;
        int centerCol = centerX / GRID_SIZE;

        return collisionMap[centerRow][centerCol];
    }

    /**
     * 获取网格尺寸
     * @return 网格尺寸
     */
    public int getGridSize() {
        return GRID_SIZE;
    }

    /**
     * 获取特定位置的网格值
     * @param row 行
     * @param col 列
     * @return 网格值
     */
    public int getGridValue(int row, int col) {
        if (collisionMap == null || row < 0 || col < 0 || row >= mapRows || col >= mapCols) {
            return -1;
        }
        return collisionMap[row][col];
    }

    /**
     * 获取地图行数
     * @return 行数
     */
    public int getMapRows() {
        return mapRows;
    }

    /**
     * 获取地图列数
     * @return 列数
     */
    public int getMapCols() {
        return mapCols;
    }
}