package cn.edu.scu.courseproject;

import java.util.ArrayList;

public class Room {
    private int floorNumber;
    private int roomNumber;
    private GameJFrame gameJFrame;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public Room(){
    }

    public Room(int floorNumber, int roomNumber,GameJFrame gameJFrame) {
        this.floorNumber = floorNumber;
        this.roomNumber = roomNumber;
        this.gameJFrame = gameJFrame;
        initEnemies(floorNumber,roomNumber);
    }

    public void exitRoom(int exitNumber,Issac issac){
        switch (floorNumber){
            case 1:
                break;
            case 2:
                switch (roomNumber){
                    case 1:
                        switch (exitNumber){
                            case 2:
                            issac.setX(295);
                            issac.setY(672);
                            break;
                            case 3:
                                gameJFrame.setRoomNumber(3);
                                issac.setX(1010);
                                issac.setY(350);
                                break;
                            case 4:
                                break;
                            case 5:
                                gameJFrame.setRoomNumber(2);
                                issac.setX(20);
                                issac.setY(350);
                                break;
                            case 6:
                                issac.setX(250);
                                issac.setY(580);
                                break;
                            case 7:
                                issac.setX(40);
                                issac.setY(580);
                                break;
                            case 8:
                                issac.setX(750);
                                issac.setY(45);
                                break;
                        }
                        break;
                    case 2:
                        gameJFrame.setRoomNumber(1);
                        issac.setX(995);
                        issac.setY(385);
                        break;
                    case 3:
                        switch (exitNumber){
                            case 2:
                                gameJFrame.setRoomNumber(1);
                                issac.setX(10);
                                issac.setY(350);
                                break;
                            case 3:
                                issac.beHit(5);
                                break;
                        }
                        break;
                }
                break;
        }
    }

    public void initEnemies(int floorNumber,int roomNumber){
        switch (floorNumber){
            case 1:
                break;
            case 2:
                switch (roomNumber){
                    case 1:
                        enemies.add(new SkeletonGrunt(5,3,1,2,200,350,22,32,gameJFrame));
                        enemies.add(new SkeletonGrunt(5,3,1,2,350,100,22,32,gameJFrame));
                        enemies.add(new SkeletonGrunt(5,3,1,2,300,400,22,32,gameJFrame));
                        enemies.add(new SkeletonGrunt(5,3,1,1,295,600,22,32,gameJFrame));
                        enemies.add(new SkeletonGrunt(5,3,1,1,900,410,22,32,gameJFrame));
                        enemies.add(new SkeletonGrunt(5,3,1,1,900,320,22,32,gameJFrame));
                        enemies.add(new SkeletonGrunt(5,3,1,1,750,200,22,32,gameJFrame));
                        break;
                    case 2:
                        enemies.add(new SkeletonGrunt(5,3,1,2,500,100,22,32,gameJFrame));
                        enemies.add(new SkeletonGrunt(5,3,1,2,500,200,22,32,gameJFrame));
                        enemies.add(new SkeletonGrunt(5,3,1,2,500,300,22,32,gameJFrame));
                        enemies.add(new SkeletonGrunt(5,3,1,2,500,400,22,32,gameJFrame));
                        enemies.add(new SkeletonGrunt(5,3,1,2,500,500,22,32,gameJFrame));
                        enemies.add(new SkeletonGrunt(5,3,1,2,500,600,22,32,gameJFrame));
                        enemies.add(new SkeletonGrunt(5,3,1,2,900,450,22,32,gameJFrame));
                        enemies.add(new SkeletonGrunt(5,3,1,2,900,150,22,32,gameJFrame));
                        enemies.add(new LuciferSkeleton(45,5,3,2,900,300,35,55,gameJFrame));
                        break;
                    case 3:
                        break;
                }
                break;
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void removeEnemies(ArrayList<Enemy> deadEnemies){
        this.enemies.removeAll(deadEnemies);
    }
}
