package fr.ryfax.rge.engine.global.scenes;

import fr.ryfax.rge.engine.global.Engine;

import java.util.ArrayList;

public class SceneBuilder {

    private Engine engine;
    private String name = "";

    public SceneBuilder(Engine engine) {
        this.engine = engine;
    }

    public Scene build() {
        SceneManager sm = engine.getSceneManager();
        Scene scene = new Scene(engine, sm.getScenes().size(), name);
        sm.getScenes().add(scene);
        return scene;
    }


    /*
     * Setters
     */
    public SceneBuilder setName(String name) {
        this.name = name;
        return this;
    }


}
