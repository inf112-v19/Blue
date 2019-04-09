package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.cards.AbilityCard;
import inf112.skeleton.app.cards.ProgramCard;
import inf112.skeleton.app.cards.RRCard;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.tiletypes.Flag;
import inf112.skeleton.app.gameobjects.tiletypes.Teleporter;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class SpriteContainer {

    private Sprite cardTestSprite;
    private Sprite cardSlot;
    private Sprite currentSprite;
    private ProgramCard currentCard;
    private String abilityText;
    private Sprite goButton;
    private Sprite muteButton;
    private Sprite unMuteButton;
    private Sprite background;
    private Sprite lifeHeart;
    private Sprite cardBack;
    private Sprite emptyCard;
    private SpriteBatch batch;
    private int drawPositionX;
    private int drawPositionY;
    private final int TILE_SIZE = 32;
    private AbilityCard currentAbility;
    private AbilityCard emptyAbility;
    private BitmapFont font;
    private int gridRows = 12;
    private int gridColumns = 12;
    private boolean mute = false;

    public SpriteContainer(SpriteBatch batch){
        this.batch = batch;
        initiate();
    }

    public SpriteContainer(SpriteBatch batch, int rows, int columns){
        this.gridRows = rows;
        this.gridColumns = columns;
        this.batch = batch;
        initiate();
    }

    private void initiate(){
        this.background = setSprite("./assets/background.png");
        this.background.setPosition(0, 0);
        this.cardSlot = setSprite("./assets/cards/cardSlot.png");
        this.cardTestSprite = setSprite("./assets/cards/back-up.png");
        this.goButton = setSprite("./assets/buttons/runButton.png");
        this.goButton.setPosition(33, 220);
        this.muteButton = setSprite("./assets/buttons/muteButton.png");
        this.muteButton.setPosition(25, 300);
        this.unMuteButton = setSprite("./assets/buttons/unMuteButton.png");
        this.unMuteButton.setPosition(25, 300);
        this.lifeHeart = setSprite("./assets/player/lifeHeart32x32.png");
        this.cardBack = setSprite("./assets/cards/cardBackside.png");
        this.cardBack.setPosition(730,400);
        this.emptyCard = setSprite("./assets/cards/emptyCard.png");
        this.emptyCard.setPosition(550,30);
        this.emptyAbility = new AbilityCard(" ");
        this.currentAbility = emptyAbility;
        this.abilityText = "";

        this.font = new BitmapFont();
        this.font.setColor(0,0,0,1);
    }

    public void renderDealtCards(ArrayList<ProgramCard> programHand) {
        this.drawPositionX = 0;
        this.drawPositionY = 40 + TILE_SIZE * 4;

        for (int i = 0; i < 5; i++) {
            this.cardSlot.setPosition((20+100*i),25);
            this.cardSlot.draw(this.batch);
            this.font.draw(this.batch,(i+1)+"",(60+100*i),94);
        }
        String ability = (""+currentAbility).substring(0, (""+currentAbility).indexOf(':'));
        this.emptyCard.draw(this.batch);
        font.draw(this.batch,ability,emptyCard.getX()+20-ability.length()/2,emptyCard.getY()+60);
        //this.currentAbility.getSprite().draw(this.batch);

        for (ProgramCard card : programHand) {
            card.getSprite().draw(this.batch);
            font.setColor(255,255,255,1);
            font.draw(this.batch,""+card.getPriority(),card.getSprite().getX()+7,card.getSprite().getY()+100);
            font.draw(this.batch,""+card.getMove(),card.getSprite().getX()+7,card.getSprite().getY()+17);
        }
        this.cardBack.draw(this.batch);

    }

    public void getCardSprite(AbilityCard abilityCard){
        this.currentAbility=abilityCard;

    }

    public Sprite setSprite(String texturePath) {
        Texture texture = new Texture(Gdx.files.internal(texturePath));
        return new Sprite(texture);
    }

    public void renderGrid(TileGrid tileGrid) {
        background.draw(this.batch);

        // Work in progress

        // Start draw position after the dealt cards.
        this.drawPositionX = TILE_SIZE * 4;
        this.drawPositionY = 40 + TILE_SIZE * 4;
        for (int row = 0; row < this.gridRows; row++) {
            for (int column = 0; column < this.gridColumns; column++) {

                // Retrieve current tile from grid
                Tile tileBeingDrawn = tileGrid.getTile(row, column);
                // Retrieve PriorityQueue of GameObjects on current tile
                PriorityQueue<GameObject> objectsOnTile = tileBeingDrawn.getObjectsOnTile();

                // Draw the tile
                Sprite spriteOfTile = tileBeingDrawn.getSprite();
                spriteOfTile.setPosition(this.drawPositionX, this.drawPositionY);
                spriteOfTile.draw(this.batch);

                // Draw GameObjects on tile
                for (GameObject gameObject : objectsOnTile) {
                    Sprite spriteOfGameObject = gameObject.getSprite();
                    spriteOfGameObject.setPosition(this.drawPositionX, this.drawPositionY);
                    spriteOfGameObject.draw(this.batch);
                    if (gameObject.getGameObjectType() == GameObjectType.FLAG){
                        this.font.draw(this.batch,((Flag)gameObject).getFlagNumber()+"",drawPositionX+spriteOfGameObject.getWidth()/2,drawPositionY+spriteOfGameObject.getHeight()/2);
                    }
                    if (gameObject.getGameObjectType() == GameObjectType.TELEPORTER){
                        this.font.draw(this.batch,((Teleporter)gameObject).getTeleporterNr()+"",drawPositionX+spriteOfGameObject.getWidth()/3,drawPositionY+spriteOfGameObject.getHeight()/2+7);
                    }
                }

                this.drawPositionX += this.TILE_SIZE;    // Moving the horizontal drawPosition, one tile over.
            }
            this.drawPositionX = this.TILE_SIZE * 4; // Resetting the horizontal drawPosition.
            this.drawPositionY += this.TILE_SIZE;    // Moving the vertical drawPosition, one tile up.
        }
        // Resetting the vertical drawPosition.
        this.drawPositionY = 0;
        //Drawing the go button
        goButton.draw(this.batch);
        if (mute){
            muteButton.draw(this.batch);
        } else {
            unMuteButton.draw(this.batch);
        }
        for (int i = 0; i < tileGrid.getPlayer(0).getLives(); i++){
            this.lifeHeart.setPosition((12+40*i), 350);
            this.lifeHeart.draw(this.batch);
        }
    }

    public boolean isInsideSprite(float screenX, float screenY, Sprite sprite){

        // Boolean to see if the coordinates is inside given sprite in the x-axis
        if (screenX >= sprite.getX() && screenX < sprite.getX() + sprite.getWidth()) {
            // Checks y-axis, but considered that the Y given is starting at the top of the screen
            // Moves the given sprite
            return Gdx.graphics.getHeight() - screenY >= sprite.getY() &&
                    Gdx.graphics.getHeight() - screenY < sprite.getY() + sprite.getHeight();
        }
        return false;
    }

    private void setAbilityText(String text){this.abilityText = text;}

    public String getAbilityText(){return this.abilityText;}

    private void setCurrentCard(ProgramCard card){this.currentCard = card;}

    public ProgramCard getCurrentCard() {return this.currentCard; }

    public Sprite getCurrentSprite() {return this.currentCard.getSprite();}

    public boolean isInsideCard(float screenX, float screenY, RRCard card) {
        Sprite sprite = card.getSprite();
        if (isInsideSprite(screenX,screenY,sprite)){
            // Moves the given sprite
            if (card instanceof ProgramCard){
                moveSprite(sprite, screenX - sprite.getWidth() / 2, Gdx.graphics.getHeight() - screenY - sprite.getHeight() / 2);
                setCurrentCard((ProgramCard) card);
            }
            if (card instanceof AbilityCard){
                if(this.abilityText.equals("")){
                    setAbilityText(((AbilityCard) card).getAbility().toString());
                } else {
                    setAbilityText("");
                }
            }
            return true;
        }
        return false;
    }

    public void moveSprite(Sprite sprite, float newX, float newY) {
        this.batch.begin();
        sprite.setPosition(newX, newY);
        sprite.draw(this.batch);
        this.batch.end();
    }

    public boolean isInsideGo(float screenX, float screenY){
        return isInsideSprite(screenX,screenY,this.goButton);
    }

    public boolean isInsideMute(float screenX, float screenY){
        if (isInsideSprite(screenX,screenY,this.muteButton)){
            if(!mute){
                mute = true;
            } else{
                mute = false;
            }
            return true;
        }
        return isInsideSprite(screenX,screenY,this.muteButton);
    }

    public void drawTextBox(String text, int length){
        ArrayList<String> lines = new ArrayList<>();

        String sentence = "";
        int i = 0;
        for (String words : text.split(" ")){
            if ((sentence+words).length() > length){
                lines.add(i,sentence);
                i++;
                sentence = "";
            }
            sentence += words + " ";
        }
        if (sentence.length() > 0){
            lines.add(i,sentence);
        }
        int y = 170+i*20;
        i = 0;
        for (String line : lines){
            this.font.draw(this.batch,line,540,y-i*20);
            i++;
        }
    }

    public void drawAbilityText(){
        drawTextBox(this.abilityText, 50);
    }
    }
