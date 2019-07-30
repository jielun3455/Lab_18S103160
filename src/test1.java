import javax.swing.*;
import java.awt.*;

public class test1 {
    public static void main(String[] args) {
        //新建窗口
        JFrame jFrame=new JFrame("国际象棋棋盘");
        //设置窗口的大小（八个格子，每个格子宽，高为20个像素）
        jFrame.setSize(160,160);
        //窗口的位置
        jFrame.setLocation(300,300);
        //格子的行数，列数
        int m=8;
        //格子的像素
        int n=20;
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                //在每个位置添加标签
                JLabel jLabel=new JLabel();
                //标签的大小为格子的大小
                jLabel.setSize(n,n);
                //标签的位置为索引乘以格子的高度/宽度
                jLabel.setLocation(i*n,j*n);
                //放置黑色方块
                if((i+j)%2==0){
                    jLabel.setBackground(Color.BLACK);
                    //设置不透明度为不透明
                    jLabel.setOpaque(true);
                }else{
                    //放置白色方块
                    jLabel.setBackground(Color.WHITE);
                    jLabel.setOpaque(true);
                }
                //设置边界颜色         jLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jFrame.add(jLabel);
            }
        }
        //窗体设置可见
        jFrame.setVisible(true);
        //关闭退出
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}