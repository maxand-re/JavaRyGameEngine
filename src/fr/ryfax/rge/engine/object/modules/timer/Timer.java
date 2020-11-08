package fr.ryfax.rge.engine.object.modules.timer;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.object.GameObject;

public class Timer implements GameObject {

    private boolean started = false;
    private int secondToGo = 0;
    private int second = 0;
    private final TimerRunnable runnable;

    public Timer(TimerRunnable runnable){
        this.runnable = runnable;
    }

    @Override
    public void init(Engine engine) { }

    @Override
    public void update(double delta, int accumulator) {
        if (!started) return;
        if (accumulator >= 1000) {
            second++;
        }
        if(second >= secondToGo){
            started = false;
            second = 0;
            runnable.run();
        }
    }

    public void start(int second){
        this.secondToGo = second;
        started = true;
    }

    public void setPause(boolean pause){
        started = !pause;
    }

    public void stop(){
        started = false;
        second = 0;
    }

}
