package fr.ryfax.rge.engine.object.elements.entity;

import fr.ryfax.rge.engine.utils.drawing.Drawer;

public interface VisualEntityModule extends EntityModule {
    void draw(Drawer drawer);
}
