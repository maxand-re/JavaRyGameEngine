package fr.ryfax.rge.engine.global;

import java.awt.*;

public class Parameters {

    /*
     * Constants
     */
    public static final Color RGE_DEFAULT_CLEAR_BUFFER_COLOR = Color.WHITE;
    public static final Cursor RGE_DEFAULT_CURSOR = Cursor.getDefaultCursor();
    public static final Cursor RGE_HIDE_CURSOR =  Toolkit.getDefaultToolkit().createCustomCursor(
            Toolkit.getDefaultToolkit().createImage("blank.png"),
            new Point(0, 0), "blankCursor");

    /*
     * Variables
     */
    private boolean limitFps = true;
    private boolean limitOverload = true;
    private Cursor cursor = RGE_DEFAULT_CURSOR;
    private Color clearBufferColor = RGE_DEFAULT_CLEAR_BUFFER_COLOR;

    /*
     * Setters
     */
    public void setLimitFps(boolean limitFps) { this.limitFps = limitFps; }
    public void setLimitOverload(boolean limitOverload) { this.limitOverload = limitOverload; }
    public void setClearBufferColor(Color clearBufferColor) { this.clearBufferColor = clearBufferColor; }
    public void setCursor(Cursor cursor) { this.cursor = cursor; }

    /*
     * Getters
     */
    public boolean isLimitFps() { return limitFps; }
    public boolean isLimitOverload() { return limitOverload; }
    public Color getClearBufferColor() { return clearBufferColor; }
    public Cursor getCursor() { return cursor; }
}
