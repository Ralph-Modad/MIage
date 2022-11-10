package test;
import tp4.uca.shape.*;
import carre.Carre;
import cercle.Cercle;
import ellipse.Ellipse;
import frame.FrameHelper;
import rectangle.Rectangle;
import tp2.vecteur.*;

public class DrawShape {
    public static void main(String[] args) {
        
        IShape[] shapes = new IShape[] {
            new Rectangle(new Vecteur(-200.0, -100.0), 100, 200),
            new Carre(new Vecteur(-80.0, -30.0), 60),
            new Ellipse(new Vecteur(50.0, -100.0), 30, 80),
            new Cercle(new Vecteur(150.0, -80.0), 60)
        };
        
        for (IShape s : shapes)
            System.out.println("le périmètre est : " + s.perimeter());
        
        new FrameHelper(500, 250, true).draw(g -> {
            for (IShape s : shapes)
                s.paint(g);
        });
    }
}