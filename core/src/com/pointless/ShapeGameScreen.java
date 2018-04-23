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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class ShapeGameScreen implements Screen {

    private Texture fish = null;
    private float blockWidth,blockX;
    private int[][] coordinateArray;
    private int alphabet = 97;
    private int incorrect = 0 ;
    private Texture texture;
    private int imageCounter;
    private int index ;
    private float textureX;
    private boolean listen = false, fishLoaded = false, textureLoaded = false;
    private Texture bDnTexture;
    private Texture bUpTexture;
    private Texture bmTexture;
    private Texture win;
    private Texture retry;
    private Texture background ;
    private Animation<TextureRegion> animation;
    private TextureAtlas atlas;

    come game;
    private Stage stage;
    Label name ;
    Label name2 ;
    Label.LabelStyle label1Style = new Label.LabelStyle();
    private float showTime = 0;
    boolean over = false  ;
    boolean again = false  ;
    long timer = 0;
    long timer2 = 0;
    private String[] url;
    private String[] words;
    private Texture texture1;
    private Texture texture2;
    private Texture texture3;
    private Texture texture4;
    Viewport viewport;
    long elapsedTime ;
    private ExerciseData exercise;
    Image nice ;
    second sec;
    Array<TextureRegion> list = new Array<TextureRegion>();
    String a;
    public ShapeGameScreen(final come game , int index , ExerciseData exerciseData) {

        this.game = game;
        this.exercise = exerciseData;
        sec = new second(game);
        viewport = new ScreenViewport();
        atlas = new TextureAtlas(Gdx.files.internal("list.atlas"));
        if (CommonObjects.urlHandler.getExercise() != null) {
            exercise = CommonObjects.urlHandler.getExercise();}
        stage = new Stage(viewport);
        this.index = index;
   //     words = exercise.getWords();
        imageCounter = 0;
   //     url = exercise.getImageUrls();

        list.add(new TextureRegion(atlas.findRegion("button",0)));
        list.add(new TextureRegion(atlas.findRegion("button",1)));
        win = new Texture(Gdx.files.internal("winner.png"));
        retry = new Texture(Gdx.files.internal("try again.png"));
     //   texture1 = new Texture(Gdx.files.internal("winning card.png"));
        background = new Texture(Gdx.files.internal("0.jpg")) ;

        bUpTexture = new Texture(Gdx.files.internal("sound button.png"));
        bmTexture = new Texture(Gdx.files.internal("mike button.png"));
        bDnTexture = new Texture(Gdx.files.internal("blink button.png"));
/*if(index == 0){
        CommonObjects.imageLoader.loadImage(url[0], Gdx.graphics.getWidth() , Gdx.graphics.getHeight()
        );
        texture1 = CommonObjects.imageLoader.getImage();
}
if(index == 1){
            CommonObjects.imageLoader.loadImage(url[1], Gdx.graphics.getWidth() , Gdx.graphics.getHeight()
            );
            texture1 = CommonObjects.imageLoader.getImage();
        }
        if(index == 2) {
            CommonObjects.imageLoader.loadImage(url[2], Gdx.graphics.getWidth() , Gdx.graphics.getHeight()
            );
            texture1 = CommonObjects.imageLoader.getImage();
        }
        if(index == 3) {
            CommonObjects.imageLoader.loadImage(url[3], Gdx.graphics.getWidth() , Gdx.graphics.getHeight()
            );
       texture1 = CommonObjects.imageLoader.getImage();
    }*/
  //      CommonObjects.imageLoader.loadImage(url[index], Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
   //     texture1 = CommonObjects.imageLoader.getImage();

texture1 = new Texture(Gdx.files.internal("shape_"+index+".png"));
          words = new String[]{"circle" , "square" , "triangle ", "pentagon" , "star" , "rectangle"} ;

        CommonObjects.textToSpeech.speak(words[index]);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

       // stage.getBatch().begin();
        showTime += delta;

        game.batch.begin();




        if(again){
     //   game.batch.draw(retry,game.screenWidth/4,game.screenHeight/2);
            game.batch.draw(background,0,0,game.screenWidth,game.screenHeight);
            game.font.draw(game.batch,"Please try Again",game.screenWidth/3,game.screenHeight/3 );
        }


        name.setZIndex(5);
        name.setText(a);
        showTime =+ delta ;


        Image okay = new Image(win);
        okay.setZIndex(5);
        okay.setHeight(6*game.screenWidth/7);
        okay.setWidth(6*game.screenWidth/7);
        okay.setVisible(false);
        okay.setPosition(game.screenWidth/8,game.screenHeight/4);

if(texture1 != null) {
    Image image1 = new Image(texture1);
    image1.setZIndex(3);
    image1.setWidth(game.screenWidth/2);
    image1.setHeight(game.screenHeight / 2);
    image1.setPosition(game.screenWidth / 5, game.screenHeight / 6);
    stage.addActor(image1);
}

 /*       nice =  new Image(retry);
        nice.setWidth(game.screenWidth/2);
        nice.setHeight(game.screenHeight/2);
        nice.setPosition(game.screenWidth/4,game.screenHeight/12);
nice.setZIndex(2);
nice.setVisible(false);*/


        if (over)
        { elapsedTime = TimeUtils.timeSinceMillis(timer);
            okay.setVisible(true);
                 if ((System.currentTimeMillis() - timer) >= 2000 ){
                over = false;
//                CommonObjects.imageLoader.loadImage(url[index], Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
      //               texture1 = CommonObjects.imageLoader.getImage();
                     texture1 = new Texture(Gdx.files.internal("shape_"+index+".png"));
                game.setScreen(new fifth(game, index, exercise));

                 }
                 }
                elapsedTime = 0;


        if (again)
        {
            game.batch.draw(retry,game.screenWidth/4,game.screenHeight/3,game.screenWidth/2,game.screenHeight/2);
            //nice.setVisible(true);
                if ((System.currentTimeMillis() - timer) >= 2000 ){
                 //   nice.setVisible(false);
                    again = false;
                    timer = 0 ;
                   }

        }
//        stage.addActor(nice);
      //  game.font.draw(game.batch,words[index],game.screenWidth/2,game.screenHeight/2);

        if(listen){
            if(game.speechOutput!=null){
                if(game.speechOutput.toLowerCase().equals(words[index].toLowerCase())){
                    game.showToast("Good Job");
                    getImages();
                    over = true ;
                    timer = System.currentTimeMillis() ;
                  //  game.setScreen( new fifth(game , index , exercise));
                }
                else {
                    game.incorrect++;
                    if(game.incorrect>1){
                        game.showToast("We will try this word later");
                        game.incorrect = 0;
                        getImages();
                        game.setScreen(new fifth(game, index, exercise));
                        sec.clear();
                        sec.remove();
                       // game.setScreen( new fifth(game , index , exercise));
                    }
                    else {
                        game.showToast("Please try again ");
                        again = true ;
                        timer = System.currentTimeMillis() ;
                        sec.clear();
                        sec.remove();
                    }
                }
                game.speechOutput = null;
                listen = false;
                sec.remove();
            }
        }


        if(listen){
            sec.setZIndex(5);
            sec.setPosition(2*game.screenWidth/3, 120);
            stage.addActor(sec);
        }


        stage.addActor(okay);

        if(!again ) {
            stage.act(delta);
            stage.draw();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.BACK) && (TimeUtils.millis() - game.lastClick) > 500){
            game.lastClick = TimeUtils.millis();
            game.setScreen(new StartScreen(game, exercise));
        }
        if (textureLoaded) {
            texture = CommonObjects.imageLoader.getImage();
            textureLoaded = false;
        }
       game.batch.end();
    }



    private void getImages(){
     //   if (index >= 4) {
   //         index = 0;
   //     }

        if(index<words.length){
      //      CommonObjects.imageLoader.loadImage(url[imageCounter]);
            //textureX = game.screenWidth;
            index++; //    imageCounter++;
        }
        if (index >= 6) {
            index = 0;
        }

    }

    @Override
    public void show() {

        label1Style.font = game.font2;
        label1Style.font.setColor(Color.PURPLE);
        label1Style.font.getData().setScale(7,10);
        Gdx.input.setInputProcessor(stage);
        //   this.exerciseData = exerciseData;
        //    words = this.exerciseData.getWords();
        //     url = this.exerciseData.getImageUrls();



        Image image = new Image(background);
        image.setZIndex(1);
        image.setWidth(game.screenWidth);
        image.setHeight(game.screenHeight);
        stage.addActor(image);







   /*     if(index == 2){
            Image image1 = new Image(texture3);
            image1.setZIndex(3);
            image1.setWidth(game.screenWidth/2);
            image1.setHeight(game.screenHeight/2);
            image1.setPosition(3*game.screenWidth/4, game.screenHeight/4);
            stage.addActor(image1);
        }if(index == 3){
            Image image1 = new Image(texture4);
            image1.setZIndex(3);
            image1.setWidth(game.screenWidth);
            image1.setHeight(game.screenHeight);
            image1.setPosition(3*game.screenWidth/4, game.screenHeight/4);
            stage.addActor(image1);
        }


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

        name = new Label( "go" , label1Style) ;
        name.setPosition(game.screenWidth/4,game.screenHeight/2 + 15,Align.center);
        stage.addActor(name);





        ImageButton play1 = new ImageButton(

                new TextureRegionDrawable(new TextureRegion(bUpTexture)),
                new TextureRegionDrawable(new TextureRegion(bDnTexture ))

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

        play1.setPosition(2*game.screenWidth/5, 120, Align.center);
        play2.setPosition(2*game.screenWidth/3, 120, Align.center);
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
