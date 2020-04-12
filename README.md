# RyGameEngine (RGE)
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

  engine.init(); // Initialize
}
```


### Global:

#### Statistics:
```Java
Statistics stats = engine.getStatistics();

int fps = stats.getCurrentFps(); // return fps (refreshed every secondes)
Vector2D cameraPos = stats.getCameraPosition(); // return pos of camera with Vector2D
String elapsedTime = stats.getElapsedTime(); // return HH:MM:SS
```


### Movements:

#### Vector2D
```Java
Vector2D vec = new Vector2D(5, 10);

doube x = vec.x; // x = 5
doube y = vec.y; // y = 10

vec.translate(2, 4); // x = 7, y = 14
vec.moveTo(1, 2); // x = 1, y = 2

vec.toString() // return "Vector2D{x=1, y=2}"
```
