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
    private final Camera camera = new Camera();

    /*
     * Variables & Constants
     */
    private final Engine engine;
    private final int id;
    private final String name;

    private final TreeMap<Integer, ArrayList<GameObject>> gameObjs = new TreeMap<>();
    private final TreeMap<Integer, ArrayList<VisualGameObject>> visualGameObjs = new TreeMap<>();

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
        gameObject.init(engine);

        if (gameObjs.containsKey(layer)) gameObjs.get(layer).add(gameObject);
        else gameObjs.put(layer, new ArrayList<>() {{ add(gameObject); }});

        if (gameObject instanceof VisualGameObject) {
            if (visualGameObjs.containsKey(layer))
                visualGameObjs.get(layer).add((VisualGameObject) gameObject);
            else visualGameObjs.put(layer, new ArrayList<>() {{ add((VisualGameObject) gameObject); }});
        }
        return this;
    }

    public Scene deleteGameObject(GameObject gameObject) {
        gameObjs.forEach((z, objs) -> objs.remove(gameObject));
        if (gameObject instanceof VisualGameObject) visualGameObjs.forEach((z, objs) -> objs.remove(gameObject));
        return this;
    }

    public boolean hasGameObject(GameObject gameObject) {
        boolean has = false;
        for (ArrayList<GameObject> objs : gameObjs.values()) {
            if(objs.contains(gameObject)){
                has = true;
                break;
            }
        }
        return has;
    }


    /*
     * Getters
     */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Camera getCamera() {
        return camera;
    }

}
