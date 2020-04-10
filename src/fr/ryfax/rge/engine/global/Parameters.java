package fr.ryfax.rge.engine.global;

import java.awt.*;

public class Parameters {

    /*
     * Constants
     */
    public static final Color RGE_DEFAULT_CLEAR_BUFFER_COLOR = Color.WHITE;

    /*
     * Variables
     */
    private boolean limitFps = true;
    private boolean limitOverload = true;
    private Color clearBufferColor = RGE_DEFAULT_CLEAR_BUFFER_COLOR;

    /*
     * Setters
     */
    public void setLimitFps(boolean limitFps) { this.limitFps = limitFps; }
    public void setLimitOverload(boolean limitOverload) { this.limitOverload = limitOverload; }
    public void setClearBufferColor(Color clearBufferColor) { this.clearBufferColor = clearBufferColor; }

    /*
     * Getters
     */
    public boolean isLimitFps() { return limitFps; }
    public boolean isLimitOverload() { return limitOverload; }
    public Color getClearBufferColor() { return clearBufferColor; }
}
