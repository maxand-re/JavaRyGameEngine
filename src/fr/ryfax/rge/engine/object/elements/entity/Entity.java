package fr.ryfax.rge.engine.object.elements.entity;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.util.ArrayList;
import java.util.TreeMap;

public class Entity implements VisualGameObject {

    /*
     * Variables
     */
    private TreeMap<Integer, ArrayList<EntityModule>> modules = new TreeMap<>();
    private TreeMap<Integer, ArrayList<VisualEntityModule>> visualModules = new TreeMap<>();

    private double height, width;
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


    /*
     * Setters
     */
    public void addModule(EntityModule module, int zindex) {
        boolean isVisualModule = module instanceof VisualEntityModule;

        module.init(engine, this);

        if(modules.containsKey(zindex)) {
            modules.get(zindex).add(module);
            if(isVisualModule) visualModules.get(zindex).add((VisualEntityModule) module);
        }else {
            ArrayList<EntityModule> list = new ArrayList<>();
            list.add(module);
            modules.put(zindex, list);

            if(isVisualModule) {
                ArrayList<VisualEntityModule> list2 = new ArrayList<>();
                list2.add((VisualEntityModule) module);
                visualModules.put(zindex, list2);
            }
        }
    }

    public void setPosition(Vector2D position) { this.position = position; }
    public void setHeight(double height) { this.height = height; }
    public void setWidth(double width) { this.width = width; }


    /*
     * Getters
     */
    public Vector2D getPosition() { return position; }
    public double getHeight() { return height; }
    public double getWidth() { return width; }
}
