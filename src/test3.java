import javax.swing.*;
import java.awt.*;

public class test3 {
    public static void main(String[] args) {
        JFrame jFrame=new JFrame("国际象棋棋盘");
        jFrame.setSize(600,600);
        jFrame.setLocation(300,300);

        int m=8;
        int n=20;
        JLabel jLabel=new JLabel();

        jLabel.setSize(n,n);
        jLabel.setLocation(200,200);

        jLabel.setBackground(Color.BLACK);
        jLabel.setOpaque(true);
        jLabel.setBorder(BorderFactory.createLineBorder(Color.white));


        JLabel jLabel1=new JLabel();

        jLabel1.setSize(n,n);
        jLabel1.setLocation(200,200);

        jLabel1.setBackground(Color.green);
        jLabel1.setOpaque(true);
        jLabel1.setBorder(BorderFactory.createLineBorder(Color.white));

        jFrame.add(jLabel);
        jFrame.add(jLabel1);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        for(int i=0;i<m;i++){
//            for(int j=0;j<m;j++){
//                JLabel jLabel=new JLabel();
//                jLabel.setSize(n,n);
//                jLabel.setLocation(i*n,j*n);
//                if((i+j)%2==0){
//                    jLabel.setBackground(Color.BLACK);
//                    jLabel.setOpaque(true);
//                }else{
//                    jLabel.setBackground(Color.WHITE);
//                    jLabel.setOpaque(true);
//                }
//                jLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//                jFrame.add(jLabel);
//            }
//        }
//        jFrame.setVisible(true);
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
