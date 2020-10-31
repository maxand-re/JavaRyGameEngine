package fr.ryfax.rge.engine.object.modules.ui.button;

public interface ButtonListener {

    void init(Button button);
    void onMouseEntered();
    void onMouseExited();
    void onClick();
    void onClickExit();

}
