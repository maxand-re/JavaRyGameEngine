# RyGameEngine (RGE)
RyGameEngine (RGE) - Modern Java Game Engine
In dev !


`TODO: Description`

-----------

### Documentation

#### How to start ?
```Java
public static void main(String[] args) {
  Engine engine = new Engine("GameTitle", 1280, 720); //Title, Width, Height

  engine.getParameters().setLimitFps(false);
  engine.getParameters().setLimitOverload(true);
  engine.getParameters().setCursor(Parameters.RGE_HIDE_CURSOR);
  engine.getParameters().setClearBufferColor(Color.BLACK);

  engine.addGameObject(new InformationsPanel(engine), 1000); // Show stats to the screen

  engine.init(); // Initialize
}
```

![alt text](https://i.imgur.com/4G4MEPl.png "InformationsPanel")


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
param.setLimitOverload(true); // Enable/Disable CPU Overload security
param.setCursor(Parameters.RGE_HIDE_CURSOR); // Set cursor (Cursor class or RGE_HIDE_CURSOR & RGE_DEFAULT_CURSOR)
param.setClearBufferColor(new Color(69, 184, 198)); // Set the background color of the game/buffer
```


### Movements:

#### Vector2D
```Java
Vector2D vec = new Vector2D(5, 10);

doube x = vec.x; // x = 5
doube y = vec.y; // y = 10

vec.translate(2, 4); // x = 7, y = 14
vec.moveTo(1, 2); // x = 1, y = 2

vec.toString() // Return "Vector2D{x=1, y=2}"
```

#### Rotation2D
```Java
Rotation2D rot = new Rotation2D(5, 0, 2);

doube x = rot.x; // x = 0
doube y = rot.y; // y = 2
doube deg = rot.degrees; // deg = 5

vec.addAngle(5); // deg = 10, x = 0, y = 2
vec.addAngle(new Rotation2D(10, 0, 6); // deg = 20, x = 0, y = 2
```

### Utils:

#### Logger
```Java
Logger logger = engine.getLogger();

logger.info('This is info'); // Print: [HH:MM:SS] [INFO] This is info
logger.warn('This is warn'); // Print: [HH:MM:SS] [WARN] This is warn
logger.error('This is error'); // Print: [HH:MM:SS] [ERROR] This is error
try { /*Code*/ } catch(Exception ex) logger.error(ex); // Work too
```

#### Tools
```Java
import fr.ryfax.rge.engine.utils.Tools;

Tools.intToDigit(5); // Return "05"
Tools.round(1.4444, 2); // Return 1.44
```

#### Sleep
```Java
// Include
import static fr.ryfax.rge.engine.utils.Sleep.*;

// And use
sleep(500); // Wait 500ms
```
