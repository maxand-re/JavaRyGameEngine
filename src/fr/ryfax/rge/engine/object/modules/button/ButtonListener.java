package fr.ryfax.rge.engine.object.modules.button;

public interface ButtonListener {

    void init(Button button);
    void onMouseEntered();
    void onMouseExited();
    void onClick();
    void onClickExit();

}
