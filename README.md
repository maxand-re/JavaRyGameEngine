# RGE
RyGameEngine (RGE) - Modern Java Game Engine


`TODO: Description`

-----------

### Documentation

#### How to start ?
```Java
public static void main(String[] args) {
  Engine engine = new Engine("GameTitle", 1280, 720); //Title, Width, Height

  engine.getParameters().setLimitFps(false);
  engine.getParameters().setLimitOverload(true);
  engine.getParameters().setClearBufferColor(Color.BLACK);

  engine.addGameObject(new InformationsPanel(engine)); // Show stats to the screen

  engine.init(); // Init
}

```
