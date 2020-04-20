package fr.ryfax.rge.engine.global.scenes;

import fr.ryfax.rge.engine.elements.camera.Camera;
import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.object.GameObject;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.drawing.Drawer;

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
    }

    public void enable() {
        //TODO: Add to SceneListener
    }

    public void disable() {
        //TODO: Add to SceneListener
    }

    public void update(int tick) {
        gameObjs.forEach((z, gameObjects) ->
                gameObjects.forEach(gameObject ->
                        gameObject.update(tick)));
    }

    public void draw(Drawer d) {
        visualGameObjs.forEach((z, visualGameObjects) ->
                visualGameObjects.forEach(visualGameObject ->
                        visualGameObject.draw(d)));
    }


    /*
     * Setters
     */
    public void addGameObject(GameObject gameObject, int zindex) {
        boolean isVisualGO = gameObject instanceof VisualGameObject;

        if(gameObjs.containsKey(zindex)) {
            gameObjs.get(zindex).add(gameObject);
            if(isVisualGO) visualGameObjs.get(zindex).add((VisualGameObject) gameObject);
        }else {
            ArrayList<GameObject> list = new ArrayList<>();
            ArrayList<VisualGameObject> list2 = new ArrayList<>();
            list.add(gameObject);
            list2.add((VisualGameObject) gameObject);

            gameObjs.put(zindex, list);
            if(isVisualGO) visualGameObjs.put(zindex, list2);
        }
    }

    public void deleteGameObject(GameObject gameObject) {
        boolean isVisualGO = gameObject instanceof VisualGameObject;
        gameObjs.forEach((z, objs) -> objs.remove(gameObject));
        if(isVisualGO) visualGameObjs.forEach((z, objs) -> objs.remove(gameObject));
    }


    /*
     * Getters
     */
    public int getId() { return id; }
    public String getName() { return name; }
    public Camera getCamera() { return camera; }
}
