package cn.edu.scu.courseproject;

import java.awt.*;

public class SkeletonGrunt extends Enemy{
    public SkeletonGrunt(int health, int speed, int attack, int direction, int x, int y, int width, int height, GameJFrame frame) {
        super(health, speed, attack, direction, x, y, width, height, frame);
    }

    @Override
    protected void initFields(){
        visionRange = 150;
        setImg(InitImage.enemyImg1[0][direction]);
        //静止帧
        IDLE_FRAMES = 6;
        //裁剪宽高
        FRAME_WIDTH = 48;   // 每帧宽度为48像素
        FRAME_HEIGHT = 48;
        //移动相关属性
        MOVE_FRAMES = 6;
        // 攻击相关属性
        ATTACK_FRAMES = 8;
        MAX_COOLDOWN = 20;  // 最大冷却时间
        attackRange = 28;
        //受伤相关属性
        HURT_FRAMES = 4;
        //死亡
        DEATH_FRAMES = 8;
    }

    @Override
    //改变方向
    public void changeDirection(int direction){
        setImg(InitImage.enemyImg1[0][direction]);
        moveImage = InitImage.enemyImg1[1][direction];
        attackImage = InitImage.enemyImg1[2][direction];
        hurtImage = InitImage.enemyImg1[3][direction];
        deathImage = InitImage.enemyImg1[4][direction];
    }
}
