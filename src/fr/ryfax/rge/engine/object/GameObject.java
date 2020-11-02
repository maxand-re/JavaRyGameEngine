package fr.ryfax.rge.engine.object;


import fr.ryfax.rge.engine.global.Engine;

public interface GameObject {

    void init(Engine engine);
    void update(double delta, int accumulator);

}
