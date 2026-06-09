import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    final int radi  = 25;       // 小球大小
    int center = 400;       // 中心点
    int len = 200;       // 线长度
    int[] centerBallreal = {375,275};//中间球的实际坐标
    double angle;
    public static String angleChange = "45";     // 初始摆动角
    double angle1 = angle;//
    double angle2 = 0;
    double deltaAngle = 0.000001;
    public static String vSpeed = "1";
    double speed; // 摆动速度
    int[] staticBallsreal = {centerBallreal[0]-50,centerBallreal[0],centerBallreal[0]+50};//三个静止小球的x坐标
    boolean loop = true;//控制循环

    public DrawPanel(){
        updateSpeed();
        Timer timer = new Timer(10,e -> {
            if(loop){
                if(angle1>0){
                    angle1 = angle1 - deltaAngle;
                    deltaAngle = deltaAngle + speed;//模拟视觉上的真实加速效果
                    if(angle1<=0)angle1=0;
                }
                if(angle2<angle&&angle1==0){
                    angle2 = angle2 + deltaAngle;
                    deltaAngle = deltaAngle - speed;
                    if(angle2>=angle) {
                        angle2=angle;deltaAngle=0.000001;loop=false;
                    }
                }
            }
            if(!loop){
                if(angle1==0&&angle2>0){
                    angle2 = angle2 - deltaAngle;
                    deltaAngle = deltaAngle + speed;
                    if(angle2<=0)angle2=0;
                }
                if(angle2==0&&angle1<angle){
                    angle1 = angle1 + deltaAngle;
                    deltaAngle = deltaAngle - speed;
                    if(angle1>=angle)
                    {angle1=angle;deltaAngle=0.000001;loop=true;}
                }
            }
            repaint();
            });timer.start();
    }

    public void updateSpeed() {//更新速度
        try {
            double multiplier = Double.parseDouble(vSpeed);
            multiplier = Math.abs(multiplier);
            speed = 0.0001 * 2 * multiplier;
            angle1 = angle;
            angle2 = 0;
            deltaAngle = 0.000001;
            loop = true;
        } catch (NumberFormatException e) {//异常处理
            speed = 0.0001 * 2;   // 默认值
            angle1 = angle;
            angle2 = 0;
            deltaAngle = 0.000001;
            loop = true;
        }
    }

    public void updateAngle() {//
        try {
            double multiplier = Math.toRadians(Integer.parseInt(angleChange));
            multiplier = Math.abs(multiplier);
            angle =  multiplier;
            angle1 = angle;
            angle2 = 0;
            deltaAngle = 0.000001;
            loop = true;
        } catch (NumberFormatException e) {//异常处理
            angle = Math.PI/45; //初始值
            angle1 = angle;
            angle2 = 0;
            deltaAngle = 0.000001;
            loop = true;
        }
    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        double leftBallX = 300-len*Math.sin(angle1);
        double leftBallY = 100+len*Math.cos(angle1);
        double rightBallX = 500+len*Math.sin(angle2);
        double rightBallY = 100+len*Math.cos(angle2);
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;//使用2D画笔
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );//开启抗锯齿
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);//画质优化
        //下面为绘制部分
        g2.setFont(new Font("楷体",Font.BOLD,20));
        g2.drawString("周佳音",0,20);
        g2.drawLine(center - 150, 100, center + 150, 100);//画出横杆
        for (int i=0;i<3;i++){
            g2.fillOval(staticBallsreal[i],275,50,50);//画出静止的球体
            g2.drawLine(staticBallsreal[i]+25,100,staticBallsreal[i]+25,centerBallreal[1]+25);//画出杆和球连线
        }
        g2.fillOval((int)leftBallX-25, (int)leftBallY-25, 2*radi,2*radi);//绘制左边起始小球
        g2.drawLine(300,100,(int)leftBallX,(int)leftBallY);//绘制左边小球与杆连线
        g2.fillOval((int)rightBallX-25, (int)rightBallY-25, 2*radi,2*radi);//绘制右边起始小球
        g2.drawLine(500,100,(int)rightBallX,(int)rightBallY);//绘制右边小球与杆连线
        //-------------------------------------------------------------------------------
        //下面为数据显示部分





    }
}



