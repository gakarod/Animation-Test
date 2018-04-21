package com.pointless;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;


public class WordGameScreen implements Screen {

    private Texture fish = null;
    private float blockWidth,blockX;
    private int[][] coordinateArray;
    private int alphabet = 97;
    private int incorrect = 0 ;
    private Texture texture;
    private int imageCounter;
    private int index ;
    private float textureX;
    private String[] words;
    private String[] url;
    private boolean listen = false, fishLoaded = false, textureLoaded = false;
    private Texture bDnTexture;
    private Texture bUpTexture;
    private Texture bmTexture;
    private Texture win;
    private Texture retry;
    private Texture background ;
    private Animation<TextureRegion> animation;
    private TextureAtlas atlas;
    Array<TextureRegion> frames = new Array<TextureRegion>();
    come game;
    private Stage stage;
    Label name ;
    Label.LabelStyle label1Style = new Label.LabelStyle();
    private float showTime = 0;
    boolean over = false  ;

    public WordGameScreen(final come game , int index) {

        this.game = game;
        stage = new Stage(new StretchViewport(game.screenWidth, game.screenHeight));
        this.index = index;
        imageCounter = 0;
        win = new Texture(Gdx.files.internal("winning card.png"));
        retry = new Texture(Gdx.files.internal("try again.png"));
        background = new Texture(Gdx.files.internal("0.jpg"));
        atlas = new TextureAtlas(Gdx.files.internal("second.atlas"));
        frames.add(new TextureRegion(atlas.findRegion("second",0)));
        frames.add(new TextureRegion(atlas.findRegion("second",1)));
        frames.add(new TextureRegion(atlas.findRegion("second",2)));
        frames.add(new TextureRegion(atlas.findRegion("second",3)));
        frames.add(new TextureRegion(atlas.findRegion("second",4)));


     //   animation = new Animation<TextureRegion>(1/5f,frames);
        //  String words[] = new String[3] ;
        words = new String[]{"start" , "finish" , "good" , "help"} ;
        getImages();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
       // stage.getBatch().begin();
        name.setText(words[index]);
        showTime =+ delta ;
if (index <= 4)
             index = 0;

        Image okay = new Image(win);
        okay.setZIndex(5);
        okay.setWidth(game.screenWidth);
        okay.setHeight(game.screenHeight);

        Image nice = new Image(retry);
        nice.setZIndex(5);
        nice.setWidth(game.screenWidth);
        nice.setHeight(game.screenHeight);
        okay.setPosition(game.screenWidth/2,game.screenHeight/4);
        nice.setPosition(game.screenWidth/2,3*game.screenHeight/4);
        nice.setVisible(false);
        okay.setVisible(false);
        okay.setHeight(150);
        okay.setWidth(150);
        stage.addActor(okay);
        stage.addActor(nice);

    //   stage.addActor(image);
         stage.act(delta);
        stage.draw();

        if (over)
        {
        
        }

   /*     if(texture!=null){

            game.batch.draw(texture, textureX, game.screenHeight/2-texture.getHeight()/2);
            if(textureX>=game.screenWidth - game.screenWidth/4-texture.getWidth()/2)textureX-=30;
        }
        else game.font.draw(game.batch,"Downloading",50,50);*/

        game.font.draw(game.batch,words[index],game.screenWidth/2,game.screenHeight/2);
     //   game.font.draw(game.batch,"Play",game.screenWidth-150,game.screenHeight-50);
     //   game.font.draw(game.batch,"Speak",50,game.screenHeight-50);





  /*      if (Gdx.input.isTouched() && TimeUtils.millis()-game.lastClick>500) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            if(x>=game.screenWidth-150 && x<=game.screenWidth && y<=100){

            }
            else if(x>=50 && x<=200 && y<=100){

            }
            game.lastClick = TimeUtils.millis();

        }*/
        if(listen){
            if(game.speechOutput!=null){
                if(game.speechOutput.toLowerCase().equals(words[index])){
                    game.showToast("Good Job");
                    getImages();
                    okay.setVisible(true);
                    over = true ;
                    game.setScreen( new fifth(game , index));
                }
                else {
                    game.incorrect++;
                    if(game.incorrect>1){
                        game.showToast("We will try this word later");
                        game.incorrect = 0;
                        stage.addActor(okay);
                        getImages();
                        game.setScreen( new fifth(game , index));
                    }
                    else {
                        game.showToast("Please try again ");

                    }
                }
                game.speechOutput = null;
                listen = false;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.BACK) && (TimeUtils.millis() - game.lastClick) > 500){
            game.lastClick = TimeUtils.millis();
            game.setScreen(new StartScreen(game));
        }
        if (textureLoaded) {
            texture = CommonObjects.imageLoader.getImage();
            textureLoaded = false;
        }
       game.batch.end();
    }



    private void getImages(){
        if(index<words.length){
      //      CommonObjects.imageLoader.loadImage(url[imageCounter]);
            //textureX = game.screenWidth;
            index++; //    imageCounter++;
        }

    }

    @Override
    public void show() {

        label1Style.font = game.font;
        Gdx.input.setInputProcessor(stage);
        //   this.exerciseData = exerciseData;
        //    words = this.exerciseData.getWords();
        //     url = this.exerciseData.getImageUrls();
        Image image = new Image(background);
        image.setZIndex(0);
        image.setWidth(game.screenWidth);
        image.setHeight(game.screenHeight);
        stage.addActor(image);


        //CommonObjects.imageLoader.loadImage(exerciseData.getObject_string());
  /*      CommonObjects.imageLoader.setOnImageLoadedListener(new OnImageLoaded() {
            @Override
            public void setTexture() {
                /*if(!fishLoaded){
                    fish = CommonObjects.imageLoader.getImage();
                    fishLoaded = true;
                }
                else{
                    textureLoaded = true;
                }
                textureLoaded = true;
            }
        });*/

        name = new Label( words[index],label1Style) ;
        name.setPosition(game.screenWidth/2,game.screenHeight/2,Align.center);
        stage.addActor(name);


        bUpTexture = new Texture(Gdx.files.internal("sound button.png"));
        bmTexture = new Texture(Gdx.files.internal("mike button.png"));
        bDnTexture = new Texture(Gdx.files.internal("blink button.png"));


        ImageButton play1 = new ImageButton(

                new TextureRegionDrawable(new TextureRegion(bUpTexture)),

                new TextureRegionDrawable(new TextureRegion(bDnTexture))

        );

        play1.addListener(new ActorGestureListener() {

            public void tap(InputEvent event, float x, float y, int count, int button) {

                super.tap(event, x, y, count, button);

                CommonObjects.textToSpeech.speak(words[index]);

                dispose();

            }

        });
        ImageButton play2 = new ImageButton(

                new TextureRegionDrawable(new TextureRegion(bmTexture)),

                new TextureRegionDrawable(new TextureRegion(bDnTexture))

        );

        play2.addListener(new ActorGestureListener() {

            public void tap(InputEvent event, float x, float y, int count, int button) {

                super.tap(event, x, y, count, button);

                game.checkPermissions();
                CommonObjects.speechToText.promptSpeechInput();
                listen = true;

                dispose();

            }

        });
        play1.setPosition(game.screenWidth-120, game.screenHeight-120, Align.center);
        play2.setPosition(120, game.screenHeight-120, Align.center);
        stage.addActor(play1);
        stage.addActor(play2);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


}
