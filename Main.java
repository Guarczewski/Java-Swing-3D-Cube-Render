import CFrame.CFrame;
import CPoint.Point3D;
import Controllers.ClickType;
import Controllers.MouseController;
import Shapes.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable{

    private final CFrame frame;
    private Thread thread;
    private Tetrahedron JanuszV2;
    private MouseController _Mouse;
    Main() {
        this.frame = new CFrame();
        _Mouse = new MouseController();
        this.addMouseListener(this._Mouse);
        this.addMouseMotionListener(this._Mouse);
        this.addMouseWheelListener(this._Mouse);
    }

    public static void main(String[] args) {
        Main display = new Main();
        display.frame.add(display);
        display.start();
    }

    public void start(){
        this.thread = new Thread(this,"Display");
        this.thread.start();
    }

    public void stop(){
        System.out.println("stop");
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60;
        double delta = 0;
        int frames = 0;
        int s = 100;

        Point3D P1 = new Point3D(s/2,-s/2,-s/2);
        Point3D P2 = new Point3D(s/2,s/2,-s/2);
        Point3D P3 = new Point3D(s/2,s/2,s/2);
        Point3D P4 = new Point3D(s/2,-s/2,s/2);

        Point3D P5 = new Point3D(-s/2,-s/2,-s/2);
        Point3D P6 = new Point3D(-s/2,s/2,-s/2);
        Point3D P7 = new Point3D(-s/2,s/2,s/2);
        Point3D P8 = new Point3D(-s/2,-s/2,s/2);

        JanuszV2 = new Tetrahedron(
            new Polygon3D(Color.WHITE,P1,P2,P3,P4),
            new Polygon3D(Color.WHITE,P5,P6,P7,P8),
            new Polygon3D(Color.WHITE,P1,P2,P6,P5),
            new Polygon3D(Color.WHITE,P1,P5,P8,P4),
            new Polygon3D(Color.WHITE,P2,P6,P7,P3),
            new Polygon3D(Color.WHITE,P4,P3,P7,P8)
        );

        JanuszV2.Rotate(true,0,0,20);

        while (true){

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                update();
                delta--;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                this.frame.setTitle("Unknown || " + frames + " fps");
                frames = 0;
            }

        }
    }

    ClickType prevClick = ClickType.Unknown;
    int initX, initY;
    private void update(){

       // System.out.println(this._Mouse.getX() + " || " + this._Mouse.getY());

        int tmpX = this._Mouse.getX();
        int tmpY = this._Mouse.getY();

        if (this._Mouse.getButton() == ClickType.LeftClick){
            int xDif = tmpX - initX;
            int yDif = tmpY - initY;
            JanuszV2.Rotate(true,0,-1 * yDif,-1 * xDif);
        }
        else if (this._Mouse.getButton() == ClickType.RightClick){
            int xDif = tmpX - initX;
            int yDif = tmpY - initY;
            JanuszV2.Rotate(true,xDif,0,0);
        }

        if (this._Mouse.isScrollingUp()) {
            Point3D.scale *= 1.2;
            this._Mouse.resetScroll();
        }
        else if (this._Mouse.isScrollingDown()) {
            Point3D.scale /= 1.2;
            this._Mouse.resetScroll();
        }



        initX = tmpX;
        initY = tmpY;

    }

    private void render(){

        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1280,720);
        JanuszV2.Render(g);
        g.dispose();
        bs.show();
    }

}
