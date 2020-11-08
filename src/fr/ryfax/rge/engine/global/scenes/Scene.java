package fr.ryfax.rge.engine.global.scenes;

import fr.ryfax.rge.engine.camera.Camera;
import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.GameCanvas;
import fr.ryfax.rge.engine.object.GameObject;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.util.ArrayList;
import java.util.TreeMap;

public class Scene {

    /*
     * Elements
     */
    private Camera camera = new Camera();

    /*
     * Variables & Constants
     */
    private Engine engine;
    private int id;
    private String name;

    private TreeMap<Integer, ArrayList<GameObject>> gameObjs = new TreeMap<>();
    private TreeMap<Integer, ArrayList<VisualGameObject>> visualGameObjs = new TreeMap<>();

    public Scene(Engine engine, int id, String name) {
        this.engine = engine;
        this.id = id;
        this.name = name;

        GameCanvas gc = engine.getWindow().getCanvas();
        camera.getRotation().offset = new Vector2D(gc.getWidth() / 2.f, gc.getHeight() / 2.f);
    }

    public void enable() {
        //TODO: Add to SceneListener
    }

    public void disable() {
        //TODO: Add to SceneListener
    }

    public void update(double delta, int accumulator) {
        TreeMap<Integer, ArrayList<GameObject>> tempGameObjs = new TreeMap<>(gameObjs);
        tempGameObjs.forEach((z, gameObjects) ->
                gameObjects.forEach(gameObject ->
                        gameObject.update(delta, accumulator)));
    }

    public void draw(Drawer d) {
        TreeMap<Integer, ArrayList<VisualGameObject>> tempVisualGameObjs = new TreeMap<>(visualGameObjs);
        tempVisualGameObjs.forEach((z, visualGameObjects) ->
                visualGameObjects.forEach(visualGameObject ->
                        visualGameObject.draw(d)));
    }


    /*
     * Setters
     */
    public Scene addGameObject(GameObject gameObject, int layer) {
        boolean isVisualGO = gameObject instanceof VisualGameObject;

        gameObject.init(engine);

        if(gameObjs.containsKey(layer)) {
            gameObjs.get(layer).add(gameObject);

            if(isVisualGO) {
                if(visualGameObjs.containsKey(layer))
                    visualGameObjs.get(layer).add((VisualGameObject) gameObject);
                else {
                    ArrayList<VisualGameObject> list2 = new ArrayList<>();
                    list2.add((VisualGameObject) gameObject);
                    visualGameObjs.put(layer, list2);
                }
            }
        }else {
            ArrayList<GameObject> list = new ArrayList<>();
            list.add(gameObject);

            if(isVisualGO) {
                ArrayList<VisualGameObject> list2 = new ArrayList<>();
                list2.add((VisualGameObject) gameObject);
                visualGameObjs.put(layer, list2);
            }

            gameObjs.put(layer, list);
        }
        return this;
    }

    public Scene deleteGameObject(GameObject gameObject) {
        boolean isVisualGO = gameObject instanceof VisualGameObject;
        gameObjs.forEach((z, objs) -> objs.remove(gameObject));
        if(isVisualGO) visualGameObjs.forEach((z, objs) -> objs.remove(gameObject));
        return this;
    }

    public boolean hasGameObject(GameObject gameObject){
        boolean has = false;
        for (ArrayList<GameObject> objs: gameObjs.values()) {
            has = objs.contains(gameObject);
        }
        return has;
    }


    /*
     * Getters
     */
    public int getId() { return id; }
    public String getName() { return name; }
    public Camera getCamera() { return camera; }
}
