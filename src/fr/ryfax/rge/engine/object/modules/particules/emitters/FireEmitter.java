package fr.ryfax.rge.engine.object.modules.particules.emitters;

import fr.ryfax.rge.engine.object.modules.particules.Particule;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class FireEmitter implements Emitter {

    private double acc;
    private double size = 1;
    private double wind = 0.00005;
    private Vector2D pos;

    public FireEmitter(Vector2D position) {
        this.pos = position;
    }

    public void emit(List<Particule> particules) {
        for (int i = 0; i < 10; i++) {
            int alpha = (int) (Math.random() * 100);

            particules.add(new Particule(
                    8,
                    new Vector2D(pos.x, pos.y),
                    new Vector2D((Math.random()/50 - 1/100.f) * size, (-Math.random()/75 - 1/75f) * size),
                    new Vector2D(wind * size, 0),
                    new Color(255, 155 + alpha, (int) (alpha * 1.5)))
            );
        }
    }

    public void update(double delta, List<Particule> particules) {
        for(Iterator<Particule> it = particules.iterator(); it.hasNext();) {
            Particule p = it.next();
            p.update(delta);
            if(p.life <= 0) { it.remove(); }
        }

        acc += delta;

        if(acc > 100) {
            emit(particules);
            acc = 0;
        }
    }
}
