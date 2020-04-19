# RyGameEngine (RGE)
RyGameEngine (RGE) - Modern Java Game Engine
In dev !


`TODO: Description`


## Documentation


#### How to start ?
```Java
public static void main(String[] args) {
  Engine engine = new Engine("GameTitle", 1280, 720); //Title, Width, Height
  SceneBuilder sb = engine.getSceneBuilder();
  SceneManager sm = engine.getSceneManager();
  Parameters param = engine.getParameters();
  
  param.setLimitFps(false);
  param.setLimitOverload(Parameters.RGE_OVERLOAD_MEDIUM);
  param.setAntiAliasing(false);
  param.setCursor(Parameters.RGE_CURSOR_DEFAULT);
  param.setClearBufferColor(new Color(69, 184, 198));

  Scene scene = sb.setName("scene").build();
  scene.addGameObject(new InformationsPanel(engine), 1000); // Show stats to the screen in scene "scene"
  
  sm.setScene(scene); // Set the current scene to "scene"

  engine.init(); // Initialize
}
```

#### Example:
![alt text](https://i.imgur.com/iO3JLg6.gif "SplashScreen and InformationsPanel")


### Global:

#### Statistics:
```Java
Statistics stats = engine.getStatistics();

int fps = stats.getCurrentFps(); // Return fps (refreshed every secondes)
int tps = stats.getCurrentTps(); // Return tps (refreshed every secondes, for best performance you need to have 60)
int ramUsage = stats.getUsedRam(); // Return used RAM in MB
int ramTotal = stats.getTotalRam(); // Return total (max) RAM in MB
Vector2D cameraPos = stats.getCameraPosition(); // Return pos of camera with Vector2D
String elapsedTime = stats.getElapsedTime(); // Return HH:MM:SS
```

#### Parameters:
```Java
Parameters param = engine.getParameters();

param.setLimitFps(false); // Enable/Disable fps limit to 60
param.setLimitOverload(Parameters.RGE_OVERLOAD_MEDIUM); // Enable/Disable CPU Overload security with different level
param.setCursor(Parameters.RGE_CURSOR_DEFAULT); // Set cursor (Cursor class or RGE_HIDE_CURSOR & RGE_DEFAULT_CURSOR)
param.setClearBufferColor(new Color(69, 184, 198)); // Set the background color of the game/buffer
param.setAntiAliasing(false); // Enable/Disable AntiAliasing
```

#### Scenes:
```Java
SceneBuilder sb = engine.getSceneBuilder();
SceneManager sm = engine.getSceneManager();

Scene introduction = sb.setName("introduction").build(); // Create introduction Scene
Scene menu         = sb.setName("menu").build(); // Create menu Scene

introduction.getCamera(); // Get camera of introduction scene
introduction.getName(); // Get name of introduction scene
introduction.getId(); // Get id of introduction scene

introduction.addGameObject(new InformationsPanel(engine), 1000); // Add GameObject to the introduction Scene

sm.setScene(introduction); // Set the scene to introduction
```

### Font:

#### FontLoader:
```Java
FontLoader fl = engine.getFontLoader();
fontLoader.load("Name", "path/to/yourfile.png"); // exemple: fr/ryfax/rge/assets/fonts/ascii.png
HashMap<Character, Integer> chars = new HashMap<>();
chars.put('i', 8); // Remove 8 transparent pixel after the letter i
chars.put('I', 4);
chars.put('l', 6);
chars.put('t', 4);
chars.put('f', 2);
chars.put('.', 8);
chars.put(':', 8);
chars.put('!', 8);
fontLoader.setSpecialCharsSize("Name", chars);
```

#### Font:
```Java
Font font = engine.getFontLoader().getDefaultFont();
Font font2 = engine.getFontLoader().getLoadedFonts().get("Name");
```

#### FontRenderer:
```Java
FontRenderer fontRenderer = new FontRenderer(font);

fontRenderer.setBackgroundColor(BACKGROUND_COLOR);
fontRenderer.setFontColor(FONT_COLOR);
fontRenderer.setSize(16); // Default: 16

BufferedImage img = fontRenderer.build("Hello World!"); // Build a BufferedImage with "Hello World!" with the font, color, background and size.
```

### Movements:

#### Vector2D:
```Java
Vector2D vec = new Vector2D(5, 10);

doube x = vec.x; // x = 5
doube y = vec.y; // y = 10

vec.translate(2, 4); // x = 7, y = 14
vec.moveTo(1, 2); // x = 1, y = 2

vec.toString() // Return "Vector2D{x=1, y=2}"
```

#### Rotation2D:
```Java
Rotation2D rot = new Rotation2D(45, 0, 2); //degree, x_offset, y_offset

doube x = rot.offset.x; // x = 0
doube y = rot.offset.y; // y = 2
doube deg = rot.degree; // deg = 45

vec.setAngle(5); // deg = 5, x = 0, y = 2
vec.setAngle(new Rotation2D(45, 0, 0); // deg = 45, x = 0, y = 2
```

### Utils:

#### Logger:
```Java
Logger logger = engine.getLogger();

logger.info('This is info'); // Print: [HH:MM:SS] [INFO] This is info
logger.warn('This is warn'); // Print: [HH:MM:SS] [WARN] This is warn
logger.error('This is error'); // Print: [HH:MM:SS] [ERROR] This is error
try { /*Code*/ } catch(Exception ex) logger.error(ex); // Work too
```

#### Tools:
```Java
import fr.ryfax.rge.engine.utils.Tools;

Tools.intToDigit(5); // Return "05"
Tools.round(1.4444, 2); // Return 1.44
```

#### Sleep:
```Java
// Include
import static fr.ryfax.rge.engine.utils.Sleep.*;

// And use
sleep(500); // Wait 500ms
sleepMicros(500); // Wait 500µs
```
