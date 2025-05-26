package cn.edu.scu.courseproject;

import java.awt.*;

public class InitImage {
    //角色图片
    //角色上下左右静止
    public static Image upIssacImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Up\\Png\\WarriorUpIdle.png");
    public static Image downIssacImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Down\\Png\\WarriorDownIdle.png");
    public static Image leftIssacImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Left\\Png\\WarriorLeftIdle.png");
    public static Image rightIssacImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Right\\Png\\WarriorRightIdle.png");

    //上下左右攻击
    public static Image upMoveImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Up\\Png\\WarriorUpWalk.png");
    public static Image downMoveImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Down\\Png\\WarriorDownWalk.png");
    public static Image leftMoveImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Left\\Png\\WarriorLeftWalk.png");
    public static Image rightMoveImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Right\\Png\\WarriorRightWalk.png");

    //上下左右攻击
    public static Image upAttackImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Up\\Png\\WarriorUpAttack01.png");
    public static Image downAttackImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Down\\Png\\WarriorDownAttack01.png");
    public static Image leftAttackImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Left\\Png\\WarriorLeftAttack01.png");
    public static Image rightAttackImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Right\\Png\\WarriorRightAttack01.png");

    //上下左右受伤
    public static Image upHurtImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Up\\Png\\WarriorUpHurt.png");
    public static Image downHurtImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Down\\Png\\WarriorDownHurt.png");
    public static Image leftHurtImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Left\\Png\\WarriorLeftHurt.png");
    public static Image rightHurtImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Right\\Png\\WarriorRightHurt.png");

    //死亡
    public static Image upDeathImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Up\\Png\\WarriorUpDeath.png");
    public static Image downDeathImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Down\\Png\\WarriorDownDeath.png");
    public static Image leftDeathImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Left\\Png\\WarriorLeftDeath.png");
    public static Image rightDeathImg = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\MovingWarrior\\Right\\Png\\WarriorRightDeath.png");
    // 背景图片
    public static Image[][] roomImg = new Image[3][10];
    static {
        roomImg[1][0] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Background\\F2room1.png");
        roomImg[1][1] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Background\\F2room2.png");
        roomImg[1][2] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Background\\F2room3.png");
    }

    //敌人图片
    public static Image[][] enemyImg1 = new Image[5][4];
    public static Image[][] enemyImg2 = new Image[5][4];
    static {
        //idle
        enemyImg1[0][0] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Up\\Png\\SkeletonWithSwordUpIdle.png");
        enemyImg1[0][1] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Down\\Png\\SkeletonWithSwordDownIdle.png");
        enemyImg1[0][2] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Left\\Png\\SkeletonWithSwordLeftIdle.png");
        enemyImg1[0][3] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Right\\Png\\SkeletonWithSwordRightIdle.png");
        //move
        enemyImg1[1][0] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Up\\Png\\SkeletonWithSwordUpWalk.png");
        enemyImg1[1][1] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Down\\Png\\SkeletonWithSwordDownWalk.png");
        enemyImg1[1][2] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Left\\Png\\SkeletonWithSwordLeftWalk.png");
        enemyImg1[1][3] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Right\\Png\\SkeletonWithSwordRightWalk.png");
        //attack
        enemyImg1[2][0] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Up\\Png\\SkeletonWithSwordUpAttack01.png");
        enemyImg1[2][1] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Down\\Png\\SkeletonWithSwordDownAttack01.png");
        enemyImg1[2][2] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Left\\Png\\SkeletonWithSwordLeftAttack01.png");
        enemyImg1[2][3] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Right\\Png\\SkeletonWithSwordRightAttack01.png");
        //hurt
        enemyImg1[3][0] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Up\\Png\\SkeletonWithSwordUpHurt.png");
        enemyImg1[3][1] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Down\\Png\\SkeletonWithSwordDownHurt.png");
        enemyImg1[3][2] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Left\\Png\\SkeletonWithSwordLeftHurt.png");
        enemyImg1[3][3] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Right\\Png\\SkeletonWithSwordRightHurt.png");
        //death
        enemyImg1[4][0] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Up\\Png\\SkeletonWithSwordUpDeath.png");
        enemyImg1[4][1] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Down\\Png\\SkeletonWithSwordDownDeath.png");
        enemyImg1[4][2] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Left\\Png\\SkeletonWithSwordLeftDeath.png");
        enemyImg1[4][3] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy1_SkeletonGrunt\\Right\\Png\\SkeletonWithSwordRightDeath.png");

        //enemy2
        //idle
        enemyImg2[0][0] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Up\\Png\\AncientSkeletonUpIdle.png");
        enemyImg2[0][1] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Down\\Png\\AncientSkeletonDownIdle.png");
        enemyImg2[0][2] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Left\\Png\\AncientSkeletonLeftIdle.png");
        enemyImg2[0][3] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Right\\Png\\AncientSkeletonRightIdle.png");
        //move
        enemyImg2[1][0] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Up\\Png\\AncientSkeletonUpWalk.png");
        enemyImg2[1][1] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Down\\Png\\AncientSkeletonDownWalk.png");
        enemyImg2[1][2] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Left\\Png\\AncientSkeletonLeftWalk.png");
        enemyImg2[1][3] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Right\\Png\\AncientSkeletonRightWalk.png");
        //attack
        enemyImg2[2][0] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Up\\Png\\AncientSkeletonUpAttack01.png");
        enemyImg2[2][1] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Down\\Png\\AncientSkeletonDownAttack01.png");
        enemyImg2[2][2] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Left\\Png\\AncientSkeletonLeftAttack01.png");
        enemyImg2[2][3] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Right\\Png\\AncientSkeletonRightAttack01.png");
        //hurt
        enemyImg2[3][0] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Up\\Png\\AncientSkeletonUpHurt.png");
        enemyImg2[3][1] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Down\\Png\\AncientSkeletonDownHurt.png");
        enemyImg2[3][2] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Left\\Png\\AncientSkeletonLeftHurt.png");
        enemyImg2[3][3] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Right\\Png\\AncientSkeletonRightHurt.png");
        //death
        enemyImg2[4][0] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Up\\Png\\AncientSkeletonUpDeath.png");
        enemyImg2[4][1] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Down\\Png\\AncientSkeletonDownDeath.png");
        enemyImg2[4][2] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Left\\Png\\AncientSkeletonLeftDeath.png");
        enemyImg2[4][3] = Toolkit.getDefaultToolkit().getImage("Choronosphere\\image\\GameAssets\\Entities\\Enemy2_LuciferSkeleton\\Right\\Png\\AncientSkeletonRightDeath.png");
    }
}
