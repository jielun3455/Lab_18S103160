import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author: z
 * @Version 1.0
 */

//alt+7 查看当前类中所有方法

class Game{//一盘棋类游戏

    public static BoardPaint startGo(){
        BoardPaint cheseBoard = new BoardPaint("go");
        return cheseBoard;
    }
    @Test
    public void startGoTest(){
        startGo();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static BoardPaint startChess(){
        BoardPaint goBoard = new BoardPaint("chess");
        return goBoard;
    }
    @Test
    public void startChessTest(){
        startChess();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Player{//“玩家”
    String name;
    int color;
    Player(String str){
            this.name=str;//黑
    }
    Player(int color){
            this.color = color;
    }
}

class ChessPlayer extends  Player{
    ArrayList<ChessPiece> pieces = new ArrayList<>();
    ChessBoard board;
    ChessPlayer(String str,ChessBoard ChessBoard) {
        super(str);
        this.board = ChessBoard;
    }
    ChessPlayer(int color,ChessBoard ChessBoard) {
        super(color);
        this.board = ChessBoard;
    }

    ChessPlayer(int color,String name,ChessBoard ChessBoard) {
        super(color);
        this.board = ChessBoard;
        this.name = name;
    }
    public  ChessPlayer getPlayer(int color,ChessBoard ChessBoard){
        if(color==1){
            return new ChessPlayer(1,ChessBoard);
        }else if(color ==-1){
            return new ChessPlayer(-1,ChessBoard);
        }else {
            System.out.println("请先设置player颜色");
        }
        return null;
    }

    public void playerPiecesInit(ChessBoard ChessBoard){
        HashMap<String, String> nameAndAddressArrays = ChessPosition.getNameAndAddressMap();
        if(color==1){
            String[] temp = {"Black_Pawn","Black_Rock","Black_Knight","Black_Bishop","Black_Queen","Black_King"};
            for (int i = 0; i <temp.length ; i++) {
                pieces.add(new ChessPiece(getPlayer(this.color,ChessBoard), temp[0],nameAndAddressArrays.get(temp[0]),this.color));
            }
        }else if(color==-1){
            String[] temp = {"White_Pawn","White_Rock","White_Knight","White_Bishop","White_Queen","White_King"};
            for (int i = 0; i <temp.length ; i++) {
                pieces.add(new ChessPiece(getPlayer(this.color,ChessBoard), temp[0],nameAndAddressArrays.get(temp[0]),this.color));
            }
        }else {
            System.out.println("请确定player颜色是否正确！");
        }
    }
}
class Board extends Panel{//“棋盘”

    //象棋操作
    public void initPiece(int width,int height){ }

    public void cleanOne(int x,int y){ }

    public void paintNewPiece(int x,int y,String pieceName,String address,int pieceKind ){}

    public void refreshPiece(){}

    //围棋操作
}

class Piece extends Canvas {//“棋子”

}
class ChessPiece  extends Piece{
    String name ;
    int backgroundColor=0;//背景颜色
    Player player;         //属于哪一个选手
    int[] position;       //位置信息
    String address;       //图片所在地址
    int color;              //1 黑色 -1白色
    ChessBoard board;//棋盘
    ChessPiece(String name,ChessBoard ChessBoard,int backgroundColor,Player player, int[] position,String address,int color){
        setSize(60,60);
        this.backgroundColor = backgroundColor;
        this.player = player;
        this.position = position;
        this.address = address;
        this.color = color;
        this.board = ChessBoard;
    }
    ChessPiece(Player player, String name, String address,int color){
        setSize(60,60);
        this.player = player;
        this.address = address;
        this.color = color;
        this.name = name;
    }
    public void paintOneByArray(Graphics g,int width,int height)
    {
        ImageIcon test = new ImageIcon(this.address);
        Image test1 = test.getImage();
//        g.drawImage(test1 , 60, 60, 60, 60 ,null ); this.position[0] = x 坐标 this.position[1] = y坐标;
        g.drawImage(test1, 120+(this.position[0]-1)*60, 120+(this.position[1]-1)*60, width, height,null);
        // g.clearRect(x,y ,width , height);
        //只有走到这里点的绘制工作才算完结
    }
    public static void paintOneByName(Graphics g,String name,int x,int y){
        HashMap<String, String> nameAndAddressArrays = ChessPosition.getNameAndAddressMap();
        if(nameAndAddressArrays.get(name)!=null){//名字拼写正确
            ImageIcon test = new ImageIcon(nameAndAddressArrays.get(name));
            Image test1 = test.getImage();
            g.drawImage(test1, 120+(x-1)*60, 120+(y-1)*60, 60,60,null);
        }else {
            System.out.println("请检查棋子的名称是否正确");
        }

    }


}

//black
class Pawn extends ChessPiece{
    Pawn(String name, ChessBoard ChessBoard, int backgroundColor, Player player, int[] position, String address, int color) {
        super(name, ChessBoard, backgroundColor, player, position, address, color);
    }
}
class Bishop extends ChessPiece{
    Bishop(String name, ChessBoard ChessBoard, int backgroundColor, Player player, int[] position, String address, int color) {
        super(name, ChessBoard, backgroundColor, player, position, address, color);
    }
}
//国际象棋棋子类型
class Rock extends ChessPiece{
    Rock(String name, ChessBoard ChessBoard, int backgroundColor, Player player, int[] position, String address, int color) {
        super(name, ChessBoard, backgroundColor, player, position, address, color);
    }
}
class Queen extends ChessPiece{
    Queen(String name, ChessBoard ChessBoard, int backgroundColor, Player player, int[] position, String address, int color) {
        super(name, ChessBoard, backgroundColor, player, position, address, color);
    }
}
class King extends ChessPiece{
    King(String name, ChessBoard ChessBoard, int backgroundColor, Player player, int[] position, String address, int color) {
        super(name, ChessBoard, backgroundColor, player, position, address, color);
    }
}
class Knight extends ChessPiece{
    Knight(String name, ChessBoard ChessBoard, int backgroundColor, Player player, int[] position, String address, int color) {
        super(name, ChessBoard, backgroundColor, player, position, address, color);
    }
}


class Position{
    public static ArrayList<ArrayList<String>> playerOperation = new ArrayList<>();
    public static ArrayList<ArrayList<String>> getPlayerOperation(){
        if(playerOperation.size()==0){
            ArrayList<String> tempOperation1 = new ArrayList<>();
            ArrayList<String> tempOperation2 = new ArrayList<>();
            playerOperation.add(tempOperation1 );
            playerOperation.add(tempOperation2 );
            return playerOperation;
        }//playerOperation的size不等于0
        return playerOperation;
    }

}
//white
class ChessPosition extends Position{
    //“棋盘上的位置”  //生成公有位置数组 //单例模式
    //棋子种类
    public static int[][][] pointsRowsAndColumns = new int[9][9][2];
    public static HashMap<String,String> nameAndAddress = new HashMap<>();
    public static HashMap<Integer,String> numAndName = new HashMap<>();
    public static HashMap<Integer,Image> numAndImage = new HashMap<>();
    public static ArrayList<ChessPlayer> players = new ArrayList<>();

    //0号位置表示背景颜色 1号位置表示棋子种类
    // 1-> pawn小兵 2 ->rock | 岩石，3->Knight|骑士，4->Bishop|主教，5->Queen&&6->King 黑 白旗取反
    //
    public static int[][][]  getPositionArrays(){
            return pointsRowsAndColumns;
    }
    public static HashMap<String,String> getNameAndAddressMap(){
        if(nameAndAddress.size()==0){
            //可改成 保存对应的类型 这样只用加载一次
            nameAndAddress.put("Black_Pawn", "picture/Black_Pawn.png");
            nameAndAddress.put("Black_Rock", "picture/Black_Rock.png");
            nameAndAddress.put("Black_Knight", "picture/Black_Knight.png");
            nameAndAddress.put("Black_Bishop", "picture/Black_Bishop.png");
            nameAndAddress.put("Black_Queen", "picture/Black_Queen.png");
            nameAndAddress.put("Black_King", "picture/Black_King.png");
            nameAndAddress.put("White_Pawn", "picture/White_Pawn.png");
            nameAndAddress.put("White_Rock", "picture/White_Rock.png");
            nameAndAddress.put("White_Knight", "picture/White_Knight.png");
            nameAndAddress.put("White_Bishop", "picture/White_Bishop.png");
            nameAndAddress.put("White_Queen", "picture/White_Queen.png");
            nameAndAddress.put("White_King", "picture/White_King.png");
            return nameAndAddress;
        }else if(nameAndAddress.size()>0) {
            return nameAndAddress;
        }
        return null;
    }
    public static HashMap<Integer,String> getnumAndName(){
        if(numAndName.size()==0){
            String[] temp = {"Black_Pawn","Black_Rock","Black_Knight","Black_Bishop","Black_Queen","Black_King",
                    "White_Pawn","White_Rock","White_Knight","White_Bishop","White_Queen","White_King"};
            //0 1 2 3 4 5 6 7 8 9 10 11
            ////1-> pawn小兵 2 ->rock | 岩石，3->Knight|骑士，4->Bishop|主教，5->Queen&&6->King 黑 白旗取反
            for (int i = 0; i <temp.length ; i++) {
                if(i<=5){
                    numAndName.put(i+1, temp[i]);
                }else if(i>5){
                    numAndName.put(5-i, temp[i]);
                }
            }
            return numAndName;
        }else if(numAndName.size()>0){
            return numAndName;
        }
        return null;
    }
    public static HashMap<Integer,Image> getNumAndImage(){
        numAndName = getnumAndName();
       // System.out.println(numAndName);
        nameAndAddress = getNameAndAddressMap();
        if(numAndImage.size()==0){//保存num2image
            Set<Integer> nums = numAndName.keySet();
            String name;
            String address;
            for (int i: nums) {
                name = numAndName.get(i);
                address = nameAndAddress.get(name);
                Image image = new ImageIcon(address).getImage();
                numAndImage.put(i, image);
            }
            return numAndImage;
        }else if (numAndImage.size()>0){
            return  numAndImage;
        }
        return null;
    }

}
class Action{//“下棋动作”
    String actionKind;
    Action(String str){
        actionKind =str;
    }
    //功能3 //给定“棋手、一颗棋子、指定位置的横坐标、指定位置的纵坐标”作为输入参数，将该棋手的该颗棋子放置在棋盘上
    public void put(Player player,String pieceName,int x,int y){}
    //功能4 //移动棋子（针对国际象棋）：给定“棋手、初始位置和目的位置的横纵坐标”，将处于初始位置的棋子移动到目的位置。
    public void move(Player player,int startX,int startY,int endX,int endY){}
    //功能6 吃子（针对国际象棋）
    public void eatPiece(Player player1,Player player2,int startX,int startY,int endX,int endY){}
    //功能7 查询 查询某个位置的占用情况（空闲，或者被哪一方的什么棋子所占
    public void query(int x,int y){}
    //功能8 计算两个玩家分别在棋盘上的棋子总数
    public void sumBoth(){}
    //功能9 跳过
    public void pass(){
        System.out.println("跳过此轮选择！");
    }

}

class ChessAction extends Action{
    String actionKind;
    ChessBoard borad ;
    ChessAction(ChessBoard ChessBoard,String str){
        super(str);
        borad = ChessBoard;
    }
    //功能2

    public ArrayList<ChessPlayer> initPlayer(ChessBoard ChessBoard,int[] colors,String[] names){//功能2
        ArrayList<ChessPlayer> players = new ArrayList<>();
        ChessPlayer player1 = new ChessPlayer(colors[0],names[0],ChessBoard);
        //System.out.println("player1.color->"+player1.color);
        player1.playerPiecesInit(ChessBoard);
        ChessPlayer player2 = new ChessPlayer(colors[1],names[1],ChessBoard);
        player2.playerPiecesInit(ChessBoard);
        players.add(player1 );
        players.add(player2 );
        return players;
    }

    //功能3 //给定“棋手、一颗棋子、指定位置的横坐标、指定位置的纵坐标”作为输入参数，将该棋手的该颗棋子放置在棋盘上
    public int put(ChessPlayer player,String pieceName,int x,int y){

        if(1<=x && x<=8 && 1<=y && y <= 8){
            //判断该坐标上是否有棋子
                    int[][][] positionArrays = ChessPosition.getPositionArrays();
                    if(positionArrays[x][y][1]!=0){
                        System.out.println("该位置上已有棋子！");
                        return 0;
                    }else if(positionArrays[x][y][1]==0){//该位置没有棋子
                        if(player.color==1&&pieceName.contains("White")||
                                player.color==-1&&pieceName.contains("Black")){
                            System.out.println("棋子不属于该棋手！");
                            return 0;
                        }else {
                            ChessPiece.paintOneByName(this.borad.getGraphics(), pieceName, x, y);
                        }
                    }
        }else {
            System.out.println("请检查输入的坐标是否超出范围！");
            return 0;
        }
        return 1;
    }

    //功能4 //移动棋子（针对国际象棋）：给定“棋手、初始位置和目的位置的横纵坐标”，将处于初始位置的棋子移动到目的位置。
    public int move(ChessPlayer player,int startX,int startY,int endX,int endY){
        int[][][] positionArrays = ChessPosition.getPositionArrays();
       // System.out.println(Arrays.deepToString(positionArrays));
        if((1<=startX&&startX<=8)&&(1<=startY&&startY<=8)&&(1<=endX&&endX<=8)&&(1<=endY&&endY<=8)){
            if(startX==endX &&startY==endY){
                System.out.println("两个位置相同！");
                return 0;
            }
            if(positionArrays[startX][startY][1]==0){
                System.out.println("当前位置没有棋子！");
                return 0;
            }else if(positionArrays[startX][startY][1]!=0){//黑|白棋
                if(player.color>0 && positionArrays[startX][startY][1]<0 ||player.color<0 && positionArrays[startX][startY][1]>0){
                    System.out.println("初始位置的棋子并非该棋手所有！");
                    return 0;
                }else if(positionArrays[endX][endY][1]!=0){
                        System.out.println("目的地已有其他棋子!");
                        return 0;
                    }else if(positionArrays[endX][endY][1]==0){
                        int pieceKind = positionArrays[startX][startY][1];
                        HashMap<Integer, String> numAndName = ChessPosition.getnumAndName();
                        HashMap<String, String> nameAndAddress = ChessPosition.getNameAndAddressMap();
                        String name = numAndName.get(positionArrays[startX][startY][1]);
//                        //System.out.println("kind->"+positionArrays[startX][startY][1]);
//                        System.out.println("name->"+name);
//                        System.out.println("address->"+positionArrays[startX][startY][1]);
                        String address = nameAndAddress.get(name);
                        positionArrays[endX][endY][1]  =  positionArrays[startX][startY][1];
                        positionArrays[startX][startY][1] = 0;
                        player.board.cleanOne(startX,startY);//清除原来的
                        player.board.paintNewPiece(endX, endY ,name,address,pieceKind);
                        //player.board
                    }
                }
            }
        else {
            System.out.println("起始坐标或者终止坐标超出棋盘的范围！");
            return 0;
        }
        return 1;
    }
    //功能6 吃子（针对国际象棋）
    public int eatPiece(ChessPlayer player1,ChessPlayer player2,int startX,int startY,int endX,int endY){
        //给定“棋手、两个位置横纵坐标”，将第一个位置上的棋子移动至第二个位置，第二个位置上原有的对手棋子从棋盘上移除。需要处理异常情况，
        int[][][] positionArrays = ChessPosition.getPositionArrays();
        //System.out.println(Arrays.deepToString(positionArrays));
        if((1<=startX&&startX<=8)&&(1<=startY&&startY<=8)&&(1<=endX&&endX<=8)&&(1<=endY&&endY<=8)){
            if(positionArrays[startX][startY][1]==0 && positionArrays[endX][endY][1]==0){
                System.out.println("两个位置上都没有棋子！");
                return 0;
            }
            else if(positionArrays[startX][startY][1]==0){
                System.out.println("第一个位置上没有棋子！");
                return 0;
            }else if(positionArrays[endX][endY][1]==0){
                System.out.println("第二个位置上没有棋子！");
                return 0;
            }else {
               // System.out.println("第二个位置棋子？->"+ positionArrays[endX][endY][1]+" x->"+endX+" y->"+endY);
                int startColor = positionArrays[startX][startY][1]>0?1:-1;//1黑色、-1白色
                int endColor = positionArrays[endX][endY][1]>0?1:-1;
                if(startColor==endColor){
                    System.out.println("两个棋子颜色相同！");
                    return 0;
                }else {
                    if(player1.color!=startColor){
                        System.out.println("第一个位置上的棋子不属于player！");
                        return 0;
                    }else if(player2.color!=endColor){
                        System.out.println("第二个位置上的棋子不属于player！");
                        return 0;
                    }else if(player1.color==player2.color){
                        System.out.println("两个位置的player相同！");
                        return 0;
                    }else {
                        int cheseKind = positionArrays[startX][startY][1];
                        HashMap<Integer, String> numAndName = ChessPosition.getnumAndName();
                        HashMap<String, String> nameAndAddress = ChessPosition.getNameAndAddressMap();
                        String name = numAndName.get(positionArrays[startX][startY][1]);
                        String address = nameAndAddress.get(name);
                        player2.board.cleanOne(endX,endY);//清除第二个位置
                        player1.board.cleanOne(startX,startY);//清除第一个位置
                        player2.board.paintNewPiece(endX, endY ,name,address,cheseKind);//在第二个位置上画上第一个棋子
                    }
                }
            }
        }else {
            System.out.println("指定的位置超出棋盘的范围!");
            return 0;
        }
    return 1;

    }
    //功能7 查询 查询某个位置的占用情况（空闲，或者被哪一方的什么棋子所占
    public int query(int x,int y,ArrayList<ChessPlayer> players){
        int[][][] positionArrays = ChessPosition.getPositionArrays();
        if(positionArrays[x][y][1]==0){
            System.out.println("该位置空闲");
            return 1;
        }else {
            //System.out.println("num->"+positionArrays[x][y][1]);
            HashMap<Integer, String> num2Name = ChessPosition.getnumAndName();
           // System.out.println(num2Name);
            String name = num2Name.get(positionArrays[x][y][1]);
                if(positionArrays[x][y][1]>0){
                    System.out.println(" 该位置为"+players.get(0).name+"的 "+name);
                    return 1;
                }else if(positionArrays[x][y][1]<0){
                    System.out.println(" 该位置为"+players.get(1).name+"的 "+name);
                    return 1;
                }
            }
        return 0;
        }
    //功能8 计算两个玩家分别在棋盘上的棋子总数
    public void sumBoth(ArrayList<ChessPlayer> players){
        int player1Num = 0;
        int player2Num = 0;
        int[][][] positionArrays = ChessPosition.getPositionArrays();
        //System.out.println(Arrays.deepToString(positionArrays));
        for (int i = 0; i <positionArrays.length ; i++) {
            for (int j = 0; j <positionArrays[0].length ; j++) {
                if(positionArrays[i][j][1]>0){
                    player1Num++;
                }else if(positionArrays[i][j][1]<0){
                    player2Num++;
                }
            }
        }
        System.out.println(players.get(0).name+"在棋盘上的棋子总数为："+player1Num);
        System.out.println(players.get(1).name+"在棋盘上的棋子总数为："+player2Num);
    }
    //功能9 跳过
    //功能10 回放
    public void replay(){}
}

class  BoardPaint extends Frame{//棋盘绘制
   final int height = 660;
   final int width = 660;
   ChessBoard board;
   GoBoard goBoard;


     BoardPaint(String str){//输入指令
        if(str.equals("chess")){
            ChessBoard ChessBoard = new ChessBoard(width,height,0);//Panel
            //draw();
            setVisible(true);
            setLayout(null);
            setTitle("国际象棋");
            this.board = ChessBoard;
            //board.paintOwn(board.getGraphics());
            pack(); //自动调整窗体格式
            setLocation(700, 100);   //java 绘图框出现的位置
            setSize(width+200,height+100);   //java 绘图框的大小

            //鼠标点击关闭退出
            addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) { System.exit(0); }});
            add(this.board);
            this.board.setBounds(0, 0, height, width);//这个很重要 可能可以定位ChessBoard的位置 从而不会出现异常情况
        }else if(str.equals("go")){
            GoBoard goBoard = new GoBoard(width,height);//Panel
            setVisible(true);
            setLayout(null);
            setTitle("围棋");
            this.goBoard = goBoard;
            pack(); //自动调整窗体格式
            setLocation(700, 100);   //java 绘图框出现的位置
            setSize(width+150,height+100);   //java 绘图框的大小
            //鼠标点击关闭退出
            addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) { System.exit(0); }});
            this.goBoard.setBounds(0, 0, width+150, height+100);//这个很重要 可能可以定位ChessBoard的位置 从而不会出现异常情况
            add(this.goBoard);
        }

    }

    void initPiece(int width,int height){//初始化国际象棋的棋子
        //得调用ChessBoard的test才行 这样ChessBoard 与 bishop的paint方法才会被自动调用
        this.board.initPiece(width,height);
    }

}


class ChessBoard extends Board {//国际象棋棋盘
    //国际象棋棋盘绘制
    int backgroundColor;
    Player player;
    int[] position;
    String address;
    int first=0;
    public ChessBoard(int width,int height,int first){
        setSize(width,height);
        this.first=first;//是否是第一次更新判断
    }

    //继承父类的paint方法
    public void initPiece(int width,int height){
        //初始化所有棋子
        int[][][] positionColorArrays = ChessPosition.getPositionArrays();
        int[] colors = {1,-1};//1黑色，-1白色
        int color = 0;
        for (int k = 0; k < colors.length ; k++) {
            //黑棋小兵
            color = colors[k];//获取颜色
            player = new Player(color);//暂定
            Pawn pawn = null;
            //画pawn
            for (int i = 1; i <=8 ; i++) {
                if(color==1){
                    backgroundColor = positionColorArrays[2][i][0];//获得颜色
                    position = new int[]{i, 2};
                    positionColorArrays[2][i][1] = color * 1;
                }else if(color==-1){
                    backgroundColor = positionColorArrays[7][i][0];
                    position = new int[]{i, 7};
                    positionColorArrays[7][i][1] = color * 1;
                }
                //棋子种类
                address = "picture/"+(color==1?"Black_":"White_")+"Pawn.png";
                pawn = new Pawn("Pawn",this,backgroundColor,player,position,address,color);
                pawn.paintOneByArray(this.getGraphics(),width, height);
            }

            //黑棋的rock | 岩石，Knight|骑士，Bishop|主教，Queen&&King
            Bishop bishop = null;
            Rock rock = null;
            Knight knight = null;
            Queen queen = null;
            King king = null;
            int[][] temp = {{1,8},{2,7},{3,6},{4,5}};
            //rock | 岩石，Knight|骑士，Bishop|主教，Queen&&King
            //1-> pawn小兵 2 ->rock | 岩石，3->Knight|骑士，4->Bishop|主教，5->Queen&&6->King 黑 白旗取反
            for (int i = 0; i <temp.length ; i++) {
                for (int j = 0; j < temp[i].length; j++) {
                    backgroundColor = positionColorArrays[1][temp[i][j]][0];
                    if(color==1){//黑
                        position = new int[]{temp[i][j],1};
                    }else if(color==-1){
                        position = new int[]{temp[i][j],8};
                    }
                    if(i==0){//rock | 岩石
                        address = "picture/"+(color==1?"Black_":"White_")+"Rock.png";
                        rock = new Rock("Rock",this,backgroundColor,player,position,address,color);
                        rock.paintOneByArray(this.getGraphics(),width, height );
                        if(color==1){//保存棋子类型
                            positionColorArrays[1][temp[i][j]][1] = color*2;
                        }else if(color==-1){
                            positionColorArrays[8][temp[i][j]][1] = color*2;
                        }

                    }
                    else if(i==1){//Knight|骑士
                        address = "picture/"+(color==1?"Black_":"White_")+"Knight.png";
                        knight = new Knight("Knight",this,backgroundColor,player,position,address,color);
                        knight.paintOneByArray(this.getGraphics(),width, height );
                        if(color==1){//保存棋子类型
                            positionColorArrays[1][temp[i][j]][1] = color*3;
                        }else if(color==-1){
                            positionColorArrays[8][temp[i][j]][1] = color*3;
                        }
                    }else if(i==2){//Bishop|主教
                        address = "picture/"+(color==1?"Black_":"White_")+"Bishop.png";
                        bishop = new Bishop("Bishop",this,backgroundColor,player,position,address,color);
                        bishop.paintOneByArray(this.getGraphics(),width, height );
                        if(color==1){//保存棋子类型
                            positionColorArrays[1][temp[i][j]][1] = color*4;
                        }else if(color==-1){
                            positionColorArrays[8][temp[i][j]][1] = color*4;
                        }
                    }else if(i==3){//Queen&&King
                        if(j==0){
                            address = "picture/"+(color==1?"Black_":"White_")+"Queen.png";
                            queen = new Queen("Queen",this,backgroundColor,player,position,address,color);
                            queen.paintOneByArray(this.getGraphics(),width, height );
                           // positionColorArrays[2][i][1] = color*5;
                            if(color==1){//保存棋子类型
                                positionColorArrays[1][temp[i][j]][1] = color*5;
                            }else if(color==-1){
                                positionColorArrays[8][temp[i][j]][1] = color*5;
                            }
                        }else if(j==1){
                            address = "picture/"+(color==1?"Black_":"White_")+"King.png";
                            king = new King("King",this,backgroundColor,player,position,address,color);
                            king.paintOneByArray(this.getGraphics(),width, height );
                            if(color==1){//保存棋子类型
                                positionColorArrays[1][temp[i][j]][1] = color*6;
                            }else if(color==-1){
                                positionColorArrays[8][temp[i][j]][1] = color*6;
                            }
                        }
                    }
                }
            }
        }

       
        //

//            Thread.sleep(50);//延迟会儿执行可以防止有两种情况

//        bishop.setBounds(x, y,width ,height );//可能是指令重排序 可能是多线程
        //使用这个才能进入到paint setbounds+paint会出问题 图片出现的位置有两种情况

//        this.add(bishop);

//        this.remove(bishop);
    }


    public void paint(Graphics g)//会自动执行
    {
       // System.out.println("drewboard");
        //super.paint(g);
        //画9行 8个间隔
        int[][][] positions = ChessPosition.getPositionArrays();
        int flag=1;//黑色  白色用-1表示 黑色用1表示
        for (int i = 1; i < positions.length; i++) {
            flag = flag*-1; //变换颜色
            for (int j = 1; j < positions.length; j++) {
                if(flag==1){
                    g.setColor(Color.gray);
                    g.fillRect(120+(i-1)*60 , 120+(j-1)*60 , 60, 60);//draw是画边框 fill才是填充
                    g.setColor(Color.black);
                    g.drawRect(120+(i-1)*60 , 120+(j-1)*60  , 60, 60);
                    positions[i][j][0] = flag;
                    flag=-1;
                }else if(flag==-1){
                    g.setColor(Color.white);
                    g.fillRect(120+(i-1)*60 , 120+(j-1)*60  , 60, 60);
                    g.setColor(Color.black);
                    g.drawRect(120+(i-1)*60 , 120+(j-1)*60 , 60, 60);
                    positions[i][j][0] = flag;
                    flag=1;
                }
            }
        }
        //paint会不断的进行刷新 缩小化或者移出屏幕都会刷新
        //更新棋子

        if(this.first==0){
            initPiece(60,60);
            this.first=1;
        }
        else if(this.first==1){
            //first之后加载在缓存里的数据 速度快
            refreshPiece();
        }

    }

    public void cleanOne(int x,int y){
            Graphics g = this.getGraphics();
            int[][][] positionArrays = ChessPosition.getPositionArrays();
            g.clearRect(120+(y-1)*60 , 120+(x-1)*60 , 60, 60 );
            if(positionArrays[x][y][0]==1){
                g.setColor(Color.gray);
            }else if(positionArrays[x][y][0]==-1){
                g.setColor(Color.white);
            }
            //画图的时候x和y的顺序要相反
            g.fillRect(120+(y-1)*60 , 120+(x-1)*60 , 60, 60 );//填充
            g.setColor(Color.black);
            g.drawRect(120+(y-1)*60 , 120+(x-1)*60 , 60, 60);//边框
            positionArrays[x][y][1]=0; //清除掉原来的棋子类型

    }

    public void paintNewPiece(int x,int y,String pieceName,String address,int pieceKind ){
        Graphics g = this.getGraphics();
        int[][][] positionArrays = ChessPosition.getPositionArrays();
        ImageIcon test = new ImageIcon(address);
        Image test1 = test.getImage();
        g.drawImage(test1, 120+(y-1)*60, 120+(x-1)*60, 60, 60,null);
        positionArrays[x][y][1] = pieceKind;
    }

    public void refreshPiece(){
        Graphics g = this.getGraphics();
        int[][][] positionArrays = ChessPosition.getPositionArrays();
        HashMap<Integer, Image> numAndImage = ChessPosition.getNumAndImage();
        //System.out.println(numAndImage);
        for (int i = 0; i < positionArrays.length ; i++) {
            for (int j = 0; j < positionArrays[0].length; j++) {
                if(positionArrays[i][j][1]!=0){
                    if(numAndImage!=null){
                        Image image = numAndImage.get(positionArrays[i][j][1]);
                        g.drawImage(image, 120+(j-1)*60, 120+(i-1)*60, 60, 60,null);
                    }
                }
            }
        }
    }

}

class WordDescribe{

    public  static void printMenu(int flag, Player player) {
        System.out.println();
        System.out.println("第"+(flag+1)+"个玩家"+player.name+"，请输入你想要执行的操作:");
        System.out.println("1->将尚未在棋盘上的一颗棋子放在棋盘上的指定位置");
        System.out.println("2->移动棋盘上某个位置的棋子至新位置");
        System.out.println("3->提子或吃子");
        System.out.println("4->查询某个位置的占用情况（空闲，或者被哪一方的什么棋子所占用）");
        System.out.println("5->计算两个玩家分别在棋盘上的棋子总数。");
        System.out.println("6->跳过");
        System.out.println("end->游戏结束");
    }
    @Test
    public void printMenuTest(){
        printMenu(1, new Player("test1"));
    }


    public static void _1printXYTips(int[] xY,Scanner in){
        while (true){
            try {
                System.out.println("请输入你想放置的棋子位置，依次输入横坐标与纵坐标，用空格分开");
                //in.next遇到空格后面的不会输出
                String[] numsStr = in.nextLine().split(" ");
                xY[0] = Integer.parseInt(numsStr[0]);
                xY[1] = Integer.parseInt(numsStr[1]);
                break;
            }catch (Exception e){
                System.out.println("格式不符合要求！请检查输入格式！");
            }
        }
    }
    @Test
    public void _1printXYTipsTest(){
        Scanner in = new Scanner(System.in);
        _1printXYTips(new int[2],in);
    }

    public static void _2And3(int[] startXY,int[] endXY ,Scanner in  ){
        String[] numsStr = null;
        System.out.println("请输入棋子的初始位置，依次输入横坐标与纵坐标，用空格分开");
        while (true){
            try {
                numsStr = in.nextLine().split(" ");
                startXY[0] = Integer.parseInt(numsStr[0]);
                startXY[1] = Integer.parseInt(numsStr[1]);
                break;
            }catch (Exception e){
                System.out.println("初始位置不合法！请重新输入!");
            }
        }
        System.out.println("请输入棋子的新位置，依次输入横坐标与纵坐标，用空格分开");
        while (true){
            try {
                numsStr = in.nextLine().split(" ");
                endXY[0] = Integer.parseInt(numsStr[0]);
                endXY[1] = Integer.parseInt(numsStr[1]);
                if(startXY[0]==endXY[0]&&startXY[1]==endXY[1]){
                    System.out.println("两个位置相同！请重新输入棋子的新位置！");
                }else {
                    break;
                }
            }catch (Exception e){
                System.out.println("新位置不合法！");
            }
        }
    }

    public static void _4(int[] xY ,Scanner in){
        System.out.println("请输入你要查询的位置，依次输入横坐标与纵坐标，用空格分开");

        String[] numsStr = in.nextLine().split(" ");
        try {
            xY[0] = Integer.parseInt(numsStr[0]);
            xY[1] = Integer.parseInt(numsStr[1]);
        }catch (Exception e){
            System.out.println("输入位置格式有误！！");
        }
    }
    public static void printSkip(Player player,int flag,ArrayList<ArrayList<String>> playerOperation){
        System.out.println(player.name+"选择跳过！");
        String  tempAc = player.name+" 选择了跳过";
        playerOperation.get(flag).add(tempAc );
    }

    @Test
    public void printSkipTest(){
        ArrayList<ArrayList<String>> test = new ArrayList<>();
        test.add(new ArrayList<String>() );
        printSkip(new Player("test1"),0,test);
    }


    public static void printHistoryRecord(Scanner in, ArrayList<ArrayList<String>> playerOperation) {
        System.out.println("是否查看走棋记录？请输入 y/n");
        String choose = in.nextLine();
        if(choose.equals("y")){
            //进行操作回放
            int maxSize = playerOperation.get(0).size()>playerOperation.get(1).size()?playerOperation.get(0).size():playerOperation.get(1).size();
            for (int i = 0; i < maxSize  ; i++) {
                System.out.println("----------------------------------------");
                if(i<playerOperation.get(0).size()){
                    System.out.println(playerOperation.get(0).get(i));
                }
                if(i<playerOperation.get(1).size()){
                    System.out.println(playerOperation.get(1).get(i));
                }
                System.out.println("----------------------------------------");
            }
        }
        System.out.println("所有步骤结束！");
    }
    public static void compareName(Scanner in, String[] playerName) {
        while (true) {
            for (int i = 0; i < 2; i++) {
                System.out.println("请输入第" + (i + 1) + "个玩家的名字");
                playerName[i] = in.nextLine();
            }
            if (playerName[0].equals(playerName[1])) {
                System.out.println("名字相同！请重新输入！");
            } else {
                break;
            }
        }
    }

}

//------------------------------------------------围棋------------------------------------------
class GoBoard extends Board{//围棋棋盘
    Player player;
    int start = 120;
    int end = start+18*30;
    int width = 10;
    int height = 10;
    int base = start+85;//145;
    int[][] fivePoints = {{base,base},{base+30*6,base+30*6},{base+(60)*(6),base},{base,base+(60)*(6)},{base+(60)*(6),base+(60)*(6)}};

    public GoBoard(int width,int height){
        setSize(width,height);
        setBackground(Color.orange);
        //this.first=first;//是否是第一次更新判断
    }

    public void paint(Graphics g)//会自动执行
    {   //清除所有的
        g.clearRect(100,100 , 1000, 1000);
        //画20行 19个间隔
        int[][] positions = GoPosition.getPositionArrays();
        int flag=1;//黑色  白色用-1表示 黑色用1表示

        //画19行
        for(int i= start;i<= end; i += 30)    //围棋有19行
        {
            g.drawLine(start,i,end,i);
        }
        for(int j=start;j<= end;j+=30)
        {
            g.drawLine(j, start,j,end);
        }
        //五个小点

        g.fillOval(base,base,width,height);
        g.fillOval(base+30*6,base+30*6,width,height);
        g.fillOval(base+(60)*(6),base,width,height);
        g.fillOval(base,base+(60)*(6),width,height);
        g.fillOval(base+(60)*(6),base+(60)*(6),width,height);

        refreshPiece();


    }

    public void cleanOne(int x,int y){
        Graphics g = this.getGraphics();
        int[][] positionArrays = GoPosition.getPositionArrays();
        g.clearRect(90-10+x*30 , 90-10+y*30 ,  20, 20 );
        g.setColor(Color.yellow);
        //画图的时候x和y的顺序要相反
        for (int i = 0; i <fivePoints.length ; i++) {
            if(x==fivePoints[i][0]&&y==fivePoints[i][1]){
                g.setColor(Color.black);
                g.fillOval(base,base,width,height);
                g.setColor(Color.yellow);
                break;
            }
        }
        //补画线
        g.setColor(Color.black);
        g.drawLine(start,start+(x-1)*30,end,start+(x-1)*30);
        g.drawLine(start+(y-1)*30, start,start+(y-1)*30,end);

        //填充
//        g.clearRect(90-10+x*30 , 90-10+y*30 , 40, 40 );;//填充
        positionArrays[x][y]=0; //清除掉原来的棋子类型
    }

    public void refreshPiece(){
        Graphics g = this.getGraphics();
        int[][] positionArrays = GoPosition.getPositionArrays();
        //System.out.println(numAndImage);
        for (int i = 1; i < positionArrays.length ; i++) {
            for (int j = 1; j < positionArrays[0].length; j++) {
                if(positionArrays[i][j]!=0){
                    if(positionArrays[i][j]==1){
                        g.setColor(Color.black);
                    }else if(positionArrays[i][j]==-1){
                        g.setColor(Color.white);
                    }
                    g.fillOval(90-10+i*30 , 90-10+j*30,20,20);
                }
            }
        }
    }
}

class GoPosition extends Position{
    public static int[][] pointsRowsAndColumns = new int[20][20];//实时更新
    public static ArrayList<GoPlayer> players = new ArrayList<>();
    public static int[][]  getPositionArrays(){
        return pointsRowsAndColumns;
    }
    public static ArrayList<GoPlayer> getPlayers (){
        return players;
    }
    public static void setPlayers (ArrayList<GoPlayer> playersOut){
        players = playersOut ;
    }
}
class GoPlayer extends  Player{
    GoBoard goboard;
    GoPlayer(String str,GoBoard goBoard) {
        super(str);
        this.goboard = goBoard;
    }
    GoPlayer(int color,GoBoard goBoard) {
        super(color);
        this.goboard = goBoard;
    }
    GoPlayer(int color,String name,GoBoard goBoard) {
        super(color);
        this.goboard = goBoard;
        this.name = name;
    }
    public  GoPlayer getPlayer(int color,GoBoard goBoard){
        if(color==1){
            return new GoPlayer(1,goBoard);
        }else if(color ==-1){
            return new GoPlayer(-1,goBoard);
        }else {
            System.out.println("请先设置player颜色");
        }
        return null;
    }
}
class GoAction extends Action{
    String actionKindc;
    GoBoard goBorad ;
    GoAction(GoBoard goBoard,String str){
        super(str);
        goBorad = goBoard;
    }
    //功能2 //初始化选手

    public ArrayList<GoPlayer> initPlayer(GoBoard goBoard,int[] colors,String[] names){//功能2
        ArrayList<GoPlayer> players = new ArrayList<>();
        GoPlayer player1 = new GoPlayer(colors[0],names[0],goBoard);
        GoPlayer player2 = new GoPlayer(colors[1],names[1],goBoard);
        players.add(player1 );
        players.add(player2 );
        return players;
    }
    //功能3 棋手、一颗棋子、指定位置的横坐标、指定位置的纵坐标”作为输入参数，将该棋手的该颗棋子放置在棋盘上
    public int put(GoPlayer player,String pieceName,int x,int y){
        if(1<=x && x<=19 && 1<=y && y <= 19){
            //判断该坐标上是否有棋子
            int[][] positionArrays = GoPosition.getPositionArrays();
            if(positionArrays[x][y]!=0){
                System.out.println("该位置上已有棋子！");
                return 0;
            }else if(positionArrays[x][y]==0){//该位置没有棋子
                if(player.color==1&&pieceName.contains("White")||
                        player.color==-1&&pieceName.contains("Black")){
                    System.out.println("棋子不属于该棋手！");
                    return 0;
                }else {
                    GoPiece.paintOne(this.goBorad.getGraphics(), x,y, player.color);
                }
            }
        }else {
            System.out.println("请检查输入的坐标是否超出范围！");
            return 0;
        }
        return 1;
    }
    //功能4
    public int move(GoPlayer player,int startX,int startY,int endX,int endY){
        //移动棋子（针对国际象棋）：给定“棋手、初始位置和目的位置的横纵坐标”，将处于初始位置的棋子移动到目的位置。
        int[][] positionArrays = GoPosition.getPositionArrays();
        // System.out.println(Arrays.deepToString(positionArrays));
        if((1<=startX&&startX<=19)&&(1<=startY&&startY<=19)&&(1<=endX&&endX<=19)&&(1<=endY&&endY<=19)){
            if(startX==endX &&startY==endY){
                System.out.println("两个位置相同！");
                return 0;
            }
            if(positionArrays[startX][startY]==0){
                System.out.println("当前位置没有棋子！");
                return 0;
            }else if(positionArrays[startX][startY]!=0){//黑|白棋
                if(player.color>0 && positionArrays[startX][startY]<0 ||player.color<0 && positionArrays[startX][startY]>0){
                    System.out.println("初始位置的棋子并非该棋手所有！");
                    return 0;
                }else if(positionArrays[endX][endY]!=0){
                    System.out.println("目的地已有其他棋子!");
                    return 0;
                }else if(positionArrays[endX][endY]==0){
                    positionArrays[endX][endY] =  positionArrays[startX][startY];
                    this.goBorad.cleanOne(startX,startY );
                    player.goboard.paint(player.goboard.getGraphics());//直接重画
                    GoPiece.paintOne(this.goBorad.getGraphics(), endX,endY, player.color); //画一个新的
                    //player.board
                }
            }
        }
        else {
            System.out.println("起始坐标或者终止坐标超出棋盘的范围！");
            return 0;
        }
        return 1;


    }
    //功能6 吃子（针对国际象棋）
    public int eatPiece(GoPlayer player1,GoPlayer player2,int startX,int startY,int endX,int endY){
        //给定“棋手、两个位置横纵坐标”，将第一个位置上的棋子移动至第二个位置，第二个位置上原有的对手棋子从棋盘上移除。需要处理异常情况，
        int[][] positionArrays = GoPosition.getPositionArrays();
        //System.out.println(Arrays.deepToString(positionArrays));
        if((1<=startX&&startX<=19)&&(1<=startY&&startY<=19)&&(1<=endX&&endX<=19)&&(1<=endY&&endY<=19)){
            if(positionArrays[startX][startY]==0 && positionArrays[endX][endY]==0){
                System.out.println("两个位置上都没有棋子！");
                return 0;
            }
            else if(positionArrays[startX][startY]==0){
                System.out.println("第一个位置上没有棋子！");
                return 0;
            }else if(positionArrays[endX][endY]==0){
                System.out.println("第二个位置上没有棋子！");
                return 0;
            }else {
                // System.out.println("第二个位置棋子？->"+ positionArrays[endX][endY][1]+" x->"+endX+" y->"+endY);
                int startColor = positionArrays[startX][startY]>0?1:-1;//1黑色、-1白色
                int endColor = positionArrays[endX][endY]>0?1:-1;
                if(startColor==endColor){
                    System.out.println("两个棋子颜色相同！");
                    return 0;
                }else {
                    if(player1.color!=startColor){
                        System.out.println("第一个位置上的棋子不属于player！");
                        return 0;
                    }else if(player2.color!=endColor){
                        System.out.println("第二个位置上的棋子不属于player！");
                        return 0;
                    }else if(player1.color==player2.color){
                        System.out.println("两个位置的player相同！");
                        return 0;
                    }else {
                        int cheseKind = positionArrays[startX][startY];
                        positionArrays[startX][startY] = 0;
                        positionArrays[endX][endY] = cheseKind;
                        player1.goboard.paint(player1.goboard.getGraphics());//直接清除重画
                    }
                }
            }
        }else {
            System.out.println("指定的位置超出棋盘的范围!");
            return 0;
        }

        return  1;
    }
    //功能7 查询 查询某个位置的占用情况（空闲，或者被哪一方的什么棋子所占
    public int query(int x,int y,ArrayList<GoPlayer> players){
        int[][] positionArrays = GoPosition.getPositionArrays();
        if(positionArrays[x][y]==0){
            System.out.println("该位置空闲");
            return 1;
        }else {
            if(positionArrays[x][y]>0){
                System.out.println(" 该位置为"+players.get(0).name+"的BlackPiece ");
                return 1;
            }else if(positionArrays[x][y]<0){
                System.out.println(" 该位置为"+players.get(1).name+"的WhitePiece ");
                return 1;
            }
        }
        return 0;
    }
    //功能8 计算两个玩家分别在棋盘上的棋子总数
    public void sumBoth(ArrayList<GoPlayer> players){
        int player1Num = 0;
        int player2Num = 0;
        int[][] positionArrays = GoPosition.getPositionArrays();
        //System.out.println(Arrays.deepToString(positionArrays));
        for (int i = 0; i <positionArrays.length ; i++) {
            for (int j = 0; j <positionArrays[0].length ; j++) {
                if(positionArrays[i][j]>0){
                    player1Num++;
                }else if(positionArrays[i][j]<0){
                    player2Num++;
                }
            }
        }
        System.out.println(players.get(0).name+"在棋盘上的棋子总数为："+player1Num);
        System.out.println(players.get(1).name+"在棋盘上的棋子总数为："+player2Num);
    }
    //功能9 跳过
    public void pass(){
        System.out.println("跳过此轮选择！");
    }
    //功能10 回放
    public void replay(){}


}
class GoPiece  extends Piece{
    String name ;
    Player player;         //属于哪一个选手
    int color;              //1 黑色 -1白色
    GoBoard goBoard;//棋盘
    GoPiece(GoBoard goBoard,Player player, int color){
        setSize(20,20);
        this.player = player;
        this.color = color;
        this.goBoard = goBoard;
        if(color==1){
            this.name="BlackPiece";
        }else if(color==-1){
            this.name="WhitePiece";
        }
    }
    GoPiece(GoBoard goBoard, int color){
        setSize(20,20);
        this.color = color;
        this.goBoard = goBoard;
        if(color==1){
            this.name="BlackPiece";
        }else if(color==-1){
            this.name="WhitePiece";
        }
    }

    public static void paintOne(Graphics g,int x, int y ,int color)
    {
        int[][] positionArrays = GoPosition.getPositionArrays();
        if(color==1){
            g.setColor(Color.black);
        }else if(color==-1){
            g.setColor(Color.white);
        }
        g.fillOval(90+x*30-10 , 90+ y*30-10,20,20);
        positionArrays[x][y] = color;
        //只有走到这里点的绘制工作才算完结
    }
    public static String getName(int color){
        if(color==1){
            return "BlackPiece";
        }else if(color==-1){
            return "WhitePiece";
        }
        return null;
    }

}

public class MyChessAndGoGame {
    //数组内容代表颜色
    //使用 Java OOP 实现一个简单的棋类模拟软件。为“一盘棋类游戏”、“玩家”、“棋盘”、“棋子”、“棋盘上的位置”、“下棋动作”设计 ADT（类或接口），
    // 命名分别为 Game、Player、Board、Piece、Position、Action。如
    //果针对不同的棋类游戏需要从这些类派生子类或者实现接口，请自行进行设计

    public static void main(String[] args) {
        while (true){
        Scanner in = new Scanner (System.in);
//        System.out.println(in.nextLine());
        //画棋盘
        System.out.println("请输入你想要玩的游戏：chess or go");
        String temp = in.nextLine();
        if(temp.equals("chess")){
//            BoardPaint cheseBoard = new BoardPaint("chess");
            BoardPaint cheseBoard =  Game.startChess();
            //      Thread.sleep(500);
        // 可能是指令重排序 可能是多线程 setbounds+棋子的paint方法会出问题 图片出现的位置有两种情况 使用Thread.sleep貌似可以解决这个问题
           // ChessPieceInit  chessInit= new ChessPieceInit(cheseBoard);
            ChessAction action = new ChessAction(cheseBoard.board,"chess");//初始化方法
            System.out.println("chess棋盘初始化完毕！");
            int [] initColor = {1,-1};
            String[] playerName = new String[2];
            WordDescribe.compareName(in, playerName);
            ArrayList<ChessPlayer> players = action.initPlayer(cheseBoard.board,initColor,playerName);
            System.out.println("player初始化完毕！");

            int flag = 0;//第一个玩家
            ChessPlayer  player=null;
            ArrayList<ArrayList<String>> playerOperation = ChessPosition.getPlayerOperation();

            //添加两个数组存储历史记录

            while (true){
                player = players.get(flag);
                WordDescribe.printMenu(flag, player);
                String act = in.nextLine();
                if(act.equals("end")){
                    System.out.println("游戏结束！");
                    String  tempAc = player.name+" 选择了结束游戏";
                    playerOperation.get(flag).add(tempAc );
                    break;
                }else if(act.equals("1")){
                    while (true){
                        int[] xY = new int[2];
                        String pieceName=null;
                        HashMap<String, String> nameAndAddress = ChessPosition.getNameAndAddressMap();
                        while (true){
                            System.out.println("请输入你想放置的棋子名称");//和位置");
                            System.out.println("黑棋：Black_Pawn,Black_Rock,Black_Knight,Black_Bishop,Black_Queen,Black_King");
                            System.out.println("白棋：White_Pawn,White_Rock,White_Knight,White_Bishop,White_Queen,White_King");

                            pieceName = in.nextLine();
                            if(nameAndAddress.get(pieceName)==null){
                                System.out.println("棋子名称不合法！");
                            }else {
                                break;
                            }
                        }
                        WordDescribe._1printXYTips(xY, in);
                        if(action.put(player, pieceName,xY[0] ,xY[1] )==1){
                            String  tempAc = player.name+"将"+pieceName+"放置到了"+xY[0] +","+xY[1]+"处";
                            playerOperation.get(flag).add(tempAc);
                            break;
                        }
                    }

                }else if(act.equals("2")){

                    int[] startXY = new int[2];
                    int[] endXY = new int[2];
                    String[] preAfterName = new String[2];
                    WordDescribe._2And3(startXY,endXY, in);
                    int[][][] positionArrays = ChessPosition.getPositionArrays();
                    HashMap<Integer, String> numAndName = ChessPosition.getnumAndName();
                    preAfterName[0] = numAndName.get(positionArrays[startXY[0]][startXY[1]][1]);
                    // preAfterName[1] = numAndName.get(positionArrays[endXY[0]][endXY[1]][1]);
                    if(action.move(player,startXY[0] ,startXY[1] , endXY[0], endXY[1])==1){
                        String tempAc = player.name+"将 "+preAfterName[0]+" 从 "+startXY[0]+","+startXY[1]+" 处放置到了 "+endXY[0]+","+endXY[1]+" 处";
                        playerOperation.get(flag).add(tempAc);
                    }else {
                        flag =1-flag;
                    }

                }else if(act.equals("3")){

                    int[] startXY = new int[2];
                    int[] endXY = new int[2];
                    String[] preAfterName = new String[2];
                    WordDescribe._2And3(startXY,endXY, in);
                    int[][][] positionArrays = ChessPosition.getPositionArrays();
                    HashMap<Integer, String> numAndName = ChessPosition.getnumAndName();
                    preAfterName[0] = numAndName.get(positionArrays[startXY[0]][startXY[1]][1]);
                    preAfterName[1] = numAndName.get(positionArrays[endXY[0]][endXY[1]][1]);
                    int over = action.eatPiece(players.get(flag), players.get(1-flag), startXY[0] ,startXY[1] , endXY[0], endXY[1] );
                    if(over==1){
                        String tempAc = players.get(flag).name+"用自己 "+startXY[0]+","+startXY[1]+" 位置的 "+preAfterName[0]
                                + " 吃掉了 "+players.get(1-flag).name+"在 "+endXY[0]+","+endXY[1]+" 处的"+preAfterName[1];
                        playerOperation.get(flag).add(tempAc );
                    }else {
                        flag =1-flag;
                    }


                }else if(act.equals("4")){
                    int flag4 = 0;
                    System.out.println("请输入你要查询的位置，依次输入横坐标与纵坐标，用空格分开");
                    int x=0,y=0;
                    String[] numsStr = in.nextLine().split(" ");
                    try {
                        x = Integer.parseInt(numsStr[0]);
                        y = Integer.parseInt(numsStr[1]);
                        flag4 = action.query(x, y,players);
                        if(flag4==1){
                            String  tempAc = player.name+"查询了 "+x+","+y+" 处位置的占用情况";
                            playerOperation.get(flag).add(tempAc );
                        }
                    }catch (Exception e){
                        System.out.println("输入位置格式有误！！");
                    }
                    if(flag4==0){
                        flag = 1-flag;
                    }
                }else if(act.equals("5")){
                    action.sumBoth(players);
                    String  tempAc = player.name+" 计算了两个玩家在国际象棋棋盘上的棋子总数";
                    playerOperation.get(flag).add(tempAc );
                }else if(act.equals("6")){
                    WordDescribe.printSkip(player,flag ,playerOperation );
                }else {
                    flag =1-flag;
                }
                //换成下一个player
                flag=1-flag;

            }
            WordDescribe.printHistoryRecord(in, playerOperation);
            break;
            //System.out.println("sss");
            // chessInit.initPiece(60,60);
            // System.out.println(Arrays.deepToString(ChessPosition.getPositionArrays()));


            //action.put("player1", "Black_Pawn",5 ,5 );
            //action.move(players.get(0),1 ,1 , 4, 4);
            //action.eatPiece(players.get(0),players.get(1),1 ,2 , 8, 6);
            //action.query(2, 2);
            //cheseBoard.initPiece(200,200,60,60);//只画一次图有问题  会出现正常和不正常两种情况 可能是指令重拍 导致顺序出现异常
            //action.sumBoth();
        }else if(temp.equals("go")){//围棋
            //System.out.println("here1?");
            BoardPaint cheseBoard =  Game.startGo();
//            BoardPaint cheseBoard = new BoardPaint("go");
            GoAction action = new GoAction(cheseBoard.goBoard,"go");//初始化方法
            System.out.println("go棋盘初始化完毕！");
            int [] initColor = {1,-1};//1->黑色 -1->白色
            String[] playerName = new String[2];
            WordDescribe.compareName(in, playerName);
            ArrayList<GoPlayer> players = action.initPlayer(cheseBoard.goBoard,initColor,playerName);
            System.out.println("player初始化完毕！");
            int flag = 0;//第一个玩家
            GoPlayer  player = null;
            ArrayList<ArrayList<String>> playerOperation = GoPosition.getPlayerOperation();//添加两个数组存储历史记录
            while (true){
                player = players.get(flag);
                WordDescribe.printMenu(flag, player);
                String act = in.nextLine();
                if(act.equals("end")){
                    System.out.println("游戏结束！");
                    String  tempAc = player.name+" 选择了结束游戏";
                    playerOperation.get(flag).add(tempAc );
                    break;
                }else if(act.equals("1")){
                    int[] xY = new int[2];
                    String pieceName=null;
                    while (true) {
                        System.out.println("请输入你想放置的棋子名称 BlackPiece or WhitePiece");//和位置");
                        pieceName = in.nextLine();
                        if (!pieceName.equals("BlackPiece") && !pieceName.equals("WhitePiece")) {
                            System.out.println("棋子名称不合法！");
                        }else {
                            break;
                        }
                    }
                    WordDescribe._1printXYTips(xY, in);
                    if(action.put(player, pieceName,xY[0] ,xY[1] )==1){
                        String  tempAc = player.name+"将"+pieceName+"放置到了"+xY[0] +","+xY[1]+"处";
                        playerOperation.get(flag).add(tempAc );
                    }else {
                        flag = 1-flag;
                    }
                }else if(act.equals("2")){
                    int[] startXY = new int[2];
                    int[] endXY = new int[2];
                    String[] preAfterName = new String[2];
                    int[][] positionArrays = GoPosition.getPositionArrays();
                    WordDescribe._2And3(startXY,endXY, in);
                    //处理完了startXY、endXY
                    preAfterName[0] = GoPiece.getName(positionArrays[startXY[0]][startXY[1]]);
                    preAfterName[1] = GoPiece.getName(positionArrays[endXY[0]][endXY[1]]);
                    int over = action.move(player,startXY[0] ,startXY[1] , endXY[0], endXY[1]);
                    if(over==1){
                        String tempAc = player.name+"将 "+preAfterName[0]+" 从 "+startXY[0]+","+startXY[1]+" 处放置到了 "+endXY[0]+","+endXY[1]+" 处";
                        playerOperation.get(flag).add(tempAc);
                    }else {
                        flag = 1-flag;
                    }
                }else if(act.equals("3")){
                    int[] startXY = new int[2];
                    int[] endXY = new int[2];
                    String[] preAfterName = new String[2];
                    int[][] positionArrays = GoPosition.getPositionArrays();
                    WordDescribe._2And3(startXY,endXY, in);
                    preAfterName[0] = GoPiece.getName(positionArrays[startXY[0]][startXY[1]]);
                    preAfterName[1] = GoPiece.getName(positionArrays[endXY[0]][endXY[1]]);
                    int over = action.eatPiece(players.get(flag), players.get(1-flag), startXY[0] ,startXY[1] , endXY[0], endXY[1] );
                    if(over==1){

                        String tempAc = players.get(flag).name+"用自己 "+startXY[0]+","+startXY[1]+" 位置的 "+preAfterName[0]
                                + " 吃掉了 "+players.get(1-flag).name+"在 "+endXY[0]+","+endXY[1]+" 处的"+preAfterName[1];
                        playerOperation.get(flag).add(tempAc );
                    }else {
                        flag = 1-flag;
                    }
                }else if(act.equals("4")){
                    int flag4 = 0;
                    System.out.println("请输入你要查询的位置，依次输入横坐标与纵坐标，用空格分开");
                    int[] xY= new int[2];
                    String[] numsStr = in.nextLine().split(" ");
                    try {
                        xY[0] = Integer.parseInt(numsStr[0]);
                        xY[1] = Integer.parseInt(numsStr[1]);
                        flag4 = action.query(xY[0], xY[1],players);
                        if(flag4==1){
                            String  tempAc = player.name+"查询了 "+xY[0]+","+xY[1]+" 处位置的占用情况";
                            playerOperation.get(flag).add(tempAc );
                        }
                    }catch (Exception e){
                        System.out.println("输入位置格式有误！！");
                    }
                    if(flag4==0){
                        flag = 1-flag;
                    }
                }else if(act.equals("5")){
                    action.sumBoth(players);
                    String  tempAc = player.name+" 计算了两个玩家在围棋棋盘上的棋子总数";
                    playerOperation.get(flag).add(tempAc );
                }else if(act.equals("6")){
                    WordDescribe.printSkip(player,flag ,playerOperation );
                }else {
                    System.out.println("输入指令错误，请重新输入！");
                    flag = 1-flag;
                }
                //换成下一个player
                flag = 1-flag;
            }
            WordDescribe.printHistoryRecord(in, playerOperation);
            break;
        }else {
            System.out.println("输入错误，请重新输入");
        }
    }
    }

}
