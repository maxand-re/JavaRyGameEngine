package fr.ryfax.rge.engine.global;

import java.awt.*;

public class Parameters {

    /*
     * Constants
     */
    public static final int RGE_OVERLOAD_DISABLE = 0,
                            RGE_OVERLOAD_LOW     = 500,
                            RGE_OVERLOAD_MEDIUM  = 1000,
                            RGE_OVERLOAD_HIGH    = 2000;

    public static final Color RGE_CLEAR_BUFFER_COLOR_DEFAULT = Color.WHITE;

    public static final Cursor RGE_CURSOR_DEFAULT = Cursor.getDefaultCursor(),
                               RGE_HIDE_CURSOR    = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().createImage("blank.png"), new Point(0, 0), "blankCursor");

    /*
     * Variables
     */
    private boolean limitFps = true, antiAliasing = false;
    private Cursor cursor = RGE_CURSOR_DEFAULT;
    private Color clearBufferColor = RGE_CLEAR_BUFFER_COLOR_DEFAULT;
    private int limitOverload = RGE_OVERLOAD_MEDIUM;

    /*
     * Setters
     */
    public void setLimitFps(boolean limitFps) { this.limitFps = limitFps; }
    public void setLimitOverload(int limitOverload) { this.limitOverload = limitOverload; }
    public void setAntiAliasing(boolean antiAliasing) { this.antiAliasing = antiAliasing; }
    public void setClearBufferColor(Color clearBufferColor) { this.clearBufferColor = clearBufferColor; }
    public void setCursor(Cursor cursor) { this.cursor = cursor; }

    /*
     * Getters
     */
    public boolean isLimitFps() { return limitFps; }
    public int getLimitOverload() { return limitOverload; }
    public boolean isAntiAliasing() { return antiAliasing; }
    public Color getClearBufferColor() { return clearBufferColor; }
    public Cursor getCursor() { return cursor; }
}
