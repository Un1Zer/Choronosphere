package cn.edu.scu.courseproject;

import java.awt.*;

public abstract class Enemy extends Entity{
    double visionRange;
    //构造
    public Enemy(){}

    public Enemy(int health, int speed , int attack ,int direction, int x, int y,int width,int height,GameJFrame frame){
        super(health, speed , attack , direction , x, y, width, height, frame);
        initFields();
    }

    //追击玩家
    public void trackPlayer(Issac issac){
        if (getHealth()<=0) return;

        int dx = issac.getX() - this.getX();
        int dy = issac.getY() - this.getY();

        int absDx = Math.abs(dx);
        int absDy = Math.abs(dy);

        double distance = Math.sqrt(dx * dx + dy * dy);

        if(distance<=visionRange){
            if(distance<=attackRange){
                isAttacking = true;
                //第三帧时攻击
                if(attackFrame == 3)
                    hitEntity(direction,getFrame().getIssac());
            }else {
                isMoving = true;
                if (absDx > absDy) {
                    // 优先左右移动
                    if (dx > 0)
                        direction = RIGHT;
                    else
                        direction = LEFT;
                } else {
                    // 优先上下移动
                    if (dy > 0)
                        direction = DOWN;
                    else
                        direction = UP;
                }
            }
            changeDirection(direction);
        }
    }

    //改变方向
    public abstract void changeDirection(int direction);
}