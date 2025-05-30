package cn.edu.scu.courseproject;

import java.awt.*;

public abstract class Entity {
    private Image img;
    //坐标
    private int x;
    private int y;
    //宽高
    private int width;
    private int height;
    //引用要绘制在哪个窗口上
    private GameJFrame frame;
    //生命值
    private int health;
    int speed;
    int attack;

    //方向常数
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    //方向
    int direction;
    //静止
    int IDLE_FRAMES = 6;
    int idleFrame = 0;
    //敌人宽高
    int WIDTH;
    int HEIGHT;
    //裁剪宽高
    int FRAME_WIDTH;   // 每帧宽度为48像素
    int FRAME_HEIGHT;// 每帧高度为48像素
    //移动相关属性
    int MOVE_FRAMES;
    boolean isMoving = false;
    int moveFrame = 0;
    Image moveImage;
    // 攻击相关属性
    int ATTACK_FRAMES;
    boolean isAttacking = false;
    int attackFrame = 0;  // 当前攻击动画帧
    Image attackImage;            // 当前攻击方向的动画图片
    int attackCooldown = 0;       // 攻击冷却时间
    int MAX_COOLDOWN;  // 最大冷却时间
    int attackRange;
    //受伤相关属性
    int HURT_FRAMES;
    boolean isHurting = false;
    int hurtFrame = 0;
    Image hurtImage;
    //死亡
    boolean isAlive = true;
    int DEATH_FRAMES;
    int deathFrame = 0;
    Image deathImage;
    
    //构造，getter和setter
    public Entity() {
    }

    public Entity(int health,int speed,int attack,int direction, int x, int y, int width, int height, GameJFrame frame) {
        this.health = health;
        this.speed = speed;
        this.attack = attack;
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frame = frame;
    }

    public Entity(int health,int speed,int attack, int x, int y, int width, int height, GameJFrame frame) {
        this.health = health;
        this.speed = speed;
        this.attack = attack;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frame = frame;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public GameJFrame getFrame() {
        return frame;
    }
    
    //抽象方法
    protected abstract void initFields();

    // 更新状态与动画
    public void updateState() {
        if(getHealth()<=0){
            deathFrame++;
            if(deathFrame >= DEATH_FRAMES)
                isAlive = false;
        }
        else if(isHurting) {
            hurtFrame++;
            if(hurtFrame >= HURT_FRAMES){
                isHurting = false;
                hurtFrame = 0;
            }
        }
        //如果正在攻击就更新攻击动画
        else if (isAttacking) {
            // 更新动画帧
            attackFrame++;
            // 如果动画播放完毕
            if (attackFrame >= ATTACK_FRAMES) {
                isAttacking = false;
                attackFrame = 0;
                attackCooldown = MAX_COOLDOWN;  // 设置冷却时间
            }
        }
        //如果正在移动
        else if(isMoving){
            //更新位置坐标
            switch (direction){
                case UP:
                    setY(getY()-speed);
                    break;
                case DOWN:
                    setY(getY()+speed);
                    break;
                case RIGHT:
                    setX(getX()+speed);
                    break;
                case LEFT:
                    setX(getX()-speed);
                    break;
                default:
                    break;
            }
            //更新帧数
            moveFrame++;
            if (moveFrame >= MOVE_FRAMES) {
                isMoving = false;
                moveFrame = 0;
            }
        }
        else {
            idleFrame++;
            if(idleFrame >= IDLE_FRAMES)
                idleFrame = 0;
        }
        //如果没有在攻击就减少冷却时间
        if (!isAttacking && attackCooldown > 0) {
            // 更新冷却时间
            attackCooldown--;
        }
    }
    
    //击中实体的判定
    public void hitEntity (int direction,Entity entity){
        int centerX = getX() + getWidth() / 2;
        int centerY = getY() + getHeight() / 2;

        Rectangle attackRect = null;
        switch (direction) {
            case UP:
                attackRect = new Rectangle(centerX - attackRange / 2, getY() - attackRange, attackRange, attackRange);
                break;
            case DOWN:
                attackRect = new Rectangle(centerX - attackRange / 2, getY() + getHeight(), attackRange, attackRange);
                break;
            case LEFT:
                attackRect = new Rectangle(getX() - attackRange, centerY - attackRange / 2, attackRange, attackRange);
                break;
            case RIGHT:
                attackRect = new Rectangle(getX() + getWidth(), centerY - attackRange / 2, attackRange, attackRange);
                break;
        }
        
        Rectangle entityRect = new Rectangle(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
        if (attackRect.intersects(entityRect)) {
            entity.beHit(attack);
        }
    }

    //被击中
    public void beHit(int attack){
        this.setHealth(getHealth()-attack);
        isHurting = true;
    }
    
    //绘制方法
    public void paintSelf(Graphics g) {
        if(getHealth()<=0){
            drawImg(deathImage,deathFrame,g);
        }
        else if(isHurting){
            drawImg(hurtImage,hurtFrame,g);
        }
        else if (isAttacking) {
            // 绘制攻击动画
            drawImg(attackImage,attackFrame,g);
        } else if(isMoving){
            // 绘制移动动画
            drawImg(moveImage,moveFrame,g);
        }
        else {
            drawImg(this.getImg(),idleFrame,g);
        }
    }

    //绘制动态图像
    //图像绘制、HitBox 和视觉对齐三者之间的 “坐标参考系”错位问题
    protected void drawImg(Image img,int frame,Graphics g) {
        // 调试用：绘制碰撞框（HitBox）
        //g.setColor(Color.RED);
        //g.drawRect(getX(), getY(), getWidth(), getHeight());

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
