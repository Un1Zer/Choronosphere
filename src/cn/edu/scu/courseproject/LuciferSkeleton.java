package cn.edu.scu.courseproject;

import java.awt.*;

public class LuciferSkeleton extends Enemy{
    public LuciferSkeleton(int health, int speed, int attack, int direction, int x, int y, int width, int height, GameJFrame frame) {
        super(health, speed, attack, direction, x, y, width, height, frame);
    }

    @Override
    protected void initFields(){
        visionRange = 250;
        setImg(InitImage.enemyImg2[0][direction]);
        //静止帧
        IDLE_FRAMES = 7;
        //裁剪宽高
        FRAME_WIDTH = 80;   // 每帧宽度为48像素
        FRAME_HEIGHT = 80;
        //移动相关属性
        MOVE_FRAMES = 8;
        // 攻击相关属性
        ATTACK_FRAMES = 7;
        MAX_COOLDOWN = 35;  // 最大冷却时间
        attackRange = 50;
        //受伤相关属性
        HURT_FRAMES = 4;
        //死亡
        DEATH_FRAMES = 9;
    }

    @Override
    //改变方向
    public void changeDirection(int direction){
        setImg(InitImage.enemyImg2[0][direction]);
        moveImage = InitImage.enemyImg2[1][direction];
        attackImage = InitImage.enemyImg2[2][direction];
        hurtImage = InitImage.enemyImg2[3][direction];
        deathImage = InitImage.enemyImg2[4][direction];
    }

    @Override
    //绘制动态图像
    protected void drawImg(Image img, int frame, Graphics g) {
        // 调试用：绘制碰撞框（HitBox）
        g.setColor(Color.RED);
        g.drawRect(getX(), getY(), getWidth(), getHeight());

        if(!isAttacking&&!isHurting&&isMoving&&(direction==LEFT||direction==RIGHT)){
            FRAME_HEIGHT = FRAME_WIDTH = 96;
        }else {
            FRAME_HEIGHT = FRAME_WIDTH = 80;
        }
        // 计算偏移量，使图像中心对齐到碰撞框中心
        int offsetX = (FRAME_WIDTH - getWidth()) / 2;
        int offsetY = (FRAME_HEIGHT - getHeight()) / 2;

        g.drawImage(
                img,
                getX() - offsetX, getY() - offsetY,  // 图像在屏幕上的位置
                getX() - offsetX + FRAME_WIDTH, getY() - offsetY + FRAME_HEIGHT,
                frame * FRAME_WIDTH, 0,
                (frame + 1) * FRAME_WIDTH, FRAME_HEIGHT,
                null
        );
    }
}

