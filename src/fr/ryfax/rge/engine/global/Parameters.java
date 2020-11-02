package fr.ryfax.rge.engine.global;

import java.awt.*;

public class Parameters {

    /*
     * Constants
     */
    public static final Color RGE_CLEAR_BUFFER_COLOR_DEFAULT = Color.WHITE;

    public static final Cursor RGE_CURSOR_DEFAULT = Cursor.getDefaultCursor(),
                               RGE_HIDE_CURSOR    = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().createImage("blank.png"), new Point(0, 0), "blankCursor");

    /*
     * Variables
     */
    private boolean antiAliasing = false, qualityRendering = false;
    private Cursor cursor = RGE_CURSOR_DEFAULT;
    private Color clearBufferColor = RGE_CLEAR_BUFFER_COLOR_DEFAULT;
    private int FPSLimit = 60;

    /*
     * Setters
     */
    public void setCursor(Cursor cursor) { this.cursor = cursor; }
    public void setFPSLimit(int FPSLimit) { this.FPSLimit = FPSLimit; }
    public void setAntiAliasing(boolean antiAliasing) { this.antiAliasing = antiAliasing; }
    public void setQualityRendering(boolean qualityRendering) { this.qualityRendering = qualityRendering; }
    public void setClearBufferColor(Color clearBufferColor) { this.clearBufferColor = clearBufferColor; }

    /*
     * Getters
     */
    public int getFPSLimit() { return FPSLimit; }
    public Cursor getCursor() { return cursor; }
    public boolean isAntiAliasing() { return antiAliasing; }
    public boolean isQualityRendering() { return qualityRendering; }
    public Color getClearBufferColor() { return clearBufferColor; }
}
