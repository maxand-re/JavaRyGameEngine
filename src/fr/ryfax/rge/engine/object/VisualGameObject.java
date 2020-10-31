package fr.ryfax.rge.engine.object;


import fr.ryfax.rge.engine.utils.drawing.Drawer;

public interface VisualGameObject extends GameObject {

    void draw(Drawer drawer);

}
