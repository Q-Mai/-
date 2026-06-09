import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {

    public static void main(String[] args) {
        JFrame f = new JFrame("牛顿摆模拟");
        f.setSize(800, 600);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setLayout(new BorderLayout());
        DrawPanel drawPanel = new DrawPanel();
        JPanel topPanel = new JPanel();
        JButton btn1 = new JButton("更改摆动速度");
        JButton btn2 = new JButton("设置初始角度");
        topPanel.add(btn2);
        topPanel.add(btn1);
        btn1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawPanel.vSpeed = JOptionPane.showInputDialog("请输入速度倍数");
                drawPanel.updateSpeed();
            }
        });
        btn2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawPanel.angleChange = JOptionPane.showInputDialog("请输入初始角度");
                drawPanel.updateAngle();
            }
        });
        f.add(drawPanel,BorderLayout.CENTER);
        f.add(topPanel,BorderLayout.NORTH);
    };
}

