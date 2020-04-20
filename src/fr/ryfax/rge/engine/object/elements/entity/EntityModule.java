package fr.ryfax.rge.engine.object.elements.entity;

import fr.ryfax.rge.engine.global.Engine;

public interface EntityModule {
    void init(Engine engine, Entity entity);
    void update(int tick);
}
