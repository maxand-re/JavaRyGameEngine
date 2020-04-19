package fr.ryfax.rge.engine.global.scenes;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.object.GameObject;
import fr.ryfax.rge.engine.object.VisualGameObject;

import java.util.ArrayList;
import java.util.TreeMap;

public class SceneManager {

    private Engine engine;
    private Scene currentScene;

    private ArrayList<Scene> scenes = new ArrayList<>();

    public SceneManager(Engine engine) {
        this.engine = engine;
    }

    /*
     * Setters
     */
    public void setScene(Scene scene) {
        if(currentScene != null) currentScene.disable();
        scene.enable();
        currentScene = scene;
    }

    /*
     * Getters
     */
    public Scene getSceneById(int id) {
        Scene out = null;

        for(Scene scene : scenes) {
            if(scene.getId() == id) {
                out = scene;
                break;
            }
        }

        return out;
    }

    public Scene getSceneByName(String name) {
        Scene out = null;

        for(Scene scene : scenes) {
            if(scene.getName().equals(name)) {
                out = scene;
                break;
            }
        }

        return out;
    }

    public Scene getCurrentScene() { return currentScene; }
    public ArrayList<Scene> getScenes() { return scenes; }

}
