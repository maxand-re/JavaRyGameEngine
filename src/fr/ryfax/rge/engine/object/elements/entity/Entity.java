package fr.ryfax.rge.engine.object.elements.entity;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class Entity implements VisualGameObject {

    /*
     * Variables
     */
    private final TreeMap<Integer, ArrayList<EntityModule>> modules = new TreeMap<>();
    private final TreeMap<Integer, ArrayList<VisualEntityModule>> visualModules = new TreeMap<>();

    private Dimension size;
    private Vector2D position;
    private Engine engine;

    public void init(Engine engine) { this.engine = engine; }

    public void update(double delta, int accumulator) {
        modules.forEach((z, modules) ->
                modules.forEach(module -> module.update(delta, accumulator)));
    }

    public void draw(Drawer drawer) {
        visualModules.forEach((z, visualModules) ->
                visualModules.forEach(visualModule -> visualModule.draw(drawer)));
    }

    public Entity move(Vector2D vel){
        position.translate(vel);
        return this;
    }

    /*
     * Setters
     */
    public Entity addModule(EntityModule module, int layer) {
        module.init(engine, this);

        if (modules.containsKey(layer)) modules.get(layer).add(module);
        else modules.put(layer, new ArrayList<>() {{ add(module); }});

        if (module instanceof VisualGameObject) {
            if (visualModules.containsKey(layer))
                visualModules.get(layer).add((VisualEntityModule) module);
            else visualModules.put(layer, new ArrayList<>() {{ add((VisualEntityModule) module); }});
        }
        return this;
    }

    public Entity deleteModule(EntityModule module) {
        modules.forEach((z, objs) -> objs.remove(module));
        if (module instanceof VisualGameObject) visualModules.forEach((z, objs) -> objs.remove(module));
        return this;
    }

    public boolean hasModule(EntityModule module) {
        boolean has = false;
        for (ArrayList<EntityModule> objs : modules.values()) {
            if(objs.contains(module)){
                has = true;
                break;
            }
        }
        return has;
    }

    public Entity setPosition(Vector2D position) { this.position = position; return this; }
    public Entity setSize(Dimension size) { this.size = size; return this; }


    /*
     * Getters
     */
    public Vector2D getPosition() { return position; }
    public Dimension getSize() { return size; }
}
