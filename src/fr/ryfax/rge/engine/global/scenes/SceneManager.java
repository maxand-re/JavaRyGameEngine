package fr.ryfax.rge.engine.global.scenes;

import java.util.ArrayList;

public class SceneManager {

    private static Scene currentScene;
    private final static ArrayList<Scene> scenes = new ArrayList<>();

    /*
     * Setters
     */
    public static void setScene(Scene scene) {
        if(currentScene != null && currentScene.getId() == scene.getId()) return;
        if(currentScene != null) currentScene.disable();
        scene.enable();
        currentScene = scene;
    }

    /*
     * Getters
     */
    public static Scene getSceneById(int id) {
        Scene out = null;

        for(Scene scene : scenes) {
            if(scene.getId() == id) {
                out = scene;
                break;
            }
        }

        return out;
    }

    public static Scene getSceneByName(String name) {
        Scene out = null;

        for(Scene scene : scenes) {
            if(scene.getName().equals(name)) {
                out = scene;
                break;
            }
        }

        return out;
    }

    public static Scene getCurrentScene() { return currentScene; }
    public static ArrayList<Scene> getScenes() { return scenes; }

}
