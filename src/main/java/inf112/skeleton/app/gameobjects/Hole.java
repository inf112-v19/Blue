package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Hole implements GameObject  {
    private Texture texture;
    private Sprite sprite;

    public Hole() {evaluateSprite();}

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.HOLE;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void evaluateSprite() {
        try {
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/blackHole/blackHole32x32.png"));
            this.sprite = new Sprite(texture);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in Hole evaluateSprite");
        }
    }

    @Override
    public int compareTo(Object o) {
        if(((GameObject)o).getGameObjectType() == GameObjectType.PLAYER){
            return -1;
        } else {
            return 1;
        }
    }
}