package cn.edu.scu.courseproject;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Issac extends Entity{
    public Issac(int health,int speed,int attack, int x, int y, int width,int height,GameJFrame frame) {
        super(health, speed, attack, x, y, width,height,frame);
        initFields();
        this.getFrame().addKeyListener(new KeyAdapter() { //内部类
            @Override
            public void keyPressed(KeyEvent e) {
                //处理移动按键
                controllDirection(e);
                // 处理攻击按键
                if (e.getKeyCode() == KeyEvent.VK_J && !isAttacking && attackCooldown <= 0) {
                    isAttacking = true;
                    ArrayList<Enemy> enemies = getFrame().getRoom().getEnemies();
                    for(Enemy enemy:enemies) {
                        hitEntity(direction,enemy);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e){
                //只有对应方向的按键释放才停止移动
                if (direction==LEFT && e.getKeyCode() == KeyEvent.VK_A || direction==UP && e.getKeyCode() == KeyEvent.VK_W ||
                        direction==DOWN && e.getKeyCode() == KeyEvent.VK_S || direction==RIGHT && e.getKeyCode() == KeyEvent.VK_D) {
                    isMoving = false;
                    moveFrame = 0;
                }
            }
        });
    }

    @Override
    protected void initFields(){
        direction = RIGHT;
        setImg(InitImage.rightIssacImg);
        attackImage = InitImage.rightAttackImg;
        hurtImage = InitImage.rightHurtImg;
        deathImage = InitImage.rightDeathImg;
        //静止帧
        IDLE_FRAMES = 5;
        //裁剪宽高
        FRAME_WIDTH = 48;   // 每帧宽度为48像素
        FRAME_HEIGHT = 48;
        //移动相关属性
        MOVE_FRAMES = 8;
        // 攻击相关属性
        ATTACK_FRAMES = 6;
        MAX_COOLDOWN = 10;  // 最大冷却时间
        attackRange = 28;
        //受伤相关属性
        HURT_FRAMES = 4;
        //死亡
        DEATH_FRAMES = 5;
    }

    //键盘控制方向
    public void controllDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_A :
                direction = LEFT;
                isMoving = true;
                setImg(InitImage.leftIssacImg);
                moveImage = InitImage.leftMoveImg;
                attackImage = InitImage.leftAttackImg;
                hurtImage = InitImage.leftHurtImg;
                deathImage = InitImage.leftDeathImg;
                break;
            case KeyEvent.VK_W :
                direction = UP;
                isMoving = true;
                setImg(InitImage.upIssacImg);
                moveImage = InitImage.upMoveImg;
                attackImage = InitImage.upAttackImg;
                hurtImage = InitImage.upHurtImg;
                deathImage = InitImage.upDeathImg;
                break;
            case KeyEvent.VK_S :
                direction = DOWN;
                isMoving = true;
                setImg(InitImage.downIssacImg);
                moveImage = InitImage.downMoveImg;
                attackImage = InitImage.downAttackImg;
                hurtImage = InitImage.downHurtImg;
                deathImage = InitImage.downDeathImg;
                break;
            case KeyEvent.VK_D :
                direction = RIGHT;
                isMoving = true;
                setImg(InitImage.rightIssacImg);
                moveImage = InitImage.rightMoveImg;
                attackImage = InitImage.rightAttackImg;
                hurtImage = InitImage.rightHurtImg;
                deathImage = InitImage.rightDeathImg;
                break;
            default:
                break;
        }
    }

}