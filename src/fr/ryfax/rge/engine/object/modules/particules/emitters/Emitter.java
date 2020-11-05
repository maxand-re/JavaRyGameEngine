package fr.ryfax.rge.engine.object.modules.particules.emitters;

import fr.ryfax.rge.engine.object.modules.particules.Particule;

import java.util.List;

public interface Emitter {
    void emit(List<Particule> particules);
    void update(double delta, List<Particule> particules);
}
