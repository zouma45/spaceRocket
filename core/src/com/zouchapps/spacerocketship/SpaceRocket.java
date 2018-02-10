package com.zouchapps.spacerocketship;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.awt.Font;
import java.util.Random;

public class SpaceRocket extends ApplicationAdapter implements ApplicationListener {
    SpriteBatch batch;
    Texture img;
    Texture[] rocket ;
    Texture obstacle ;
    Texture obstacle2 ;
    Texture meteor  ;
    Texture intro ;
    Texture over ;
    int score ;
    int scoring = 0  ;
    BitmapFont font ;
    BitmapFont font2 ;
    int gameState = 0 ;
    float velocity  ;
    float meteorX = 0;
    float rocketX =0 ;
    float rocketY =0 ;
    float force  = 4f ;
    int a =1 ;
    int distance ;
    int flameState = 0 ;
    Random randomGenerator ;
    int b = 1;



    // obstacle variables
    float obstacleVelocity ;
    float obstacleX = 0  ;
    float[] obstacleY;
    float[] obstacleOffset;
    float distanceBetweenTubes ;
    int numberObstacles  = 3 ;
    float gap = 400 ;
    int current  ;



    //// creating the physical part of the assets
    Circle[]  planet ;
    Circle[]  meteorShape  ;
    ShapeRenderer shapeRenderer ;
    Rectangle[] rocketShape ;
    Rectangle[] satellite  ;
    //get a preferences instance
    Preferences prefs ;








    @Override
    public void create () {






        batch = new SpriteBatch();
        img = new Texture("testbg.png");
        rocket = new Texture[2];
//        rocket[0] = new Texture("rocket11.png");
//        rocket[1]= new Texture("rocket122.png");


//        if (Gdx.graphics.getDensity()>= 3.0) {
//
//            rocket[0] = new Texture("XXHDPI/rocket1111.png");
//            rocket[1]= new Texture("XXHDPI/rocket2222.png");
//            obstacle = new Texture("XXHDPI/planet1.png");
//            obstacle2 = new Texture("XXHDPI/satellite1.png");
//            meteor = new Texture("XXHDPI/meteor1.png");
//            intro = new Texture("XXHDPI/intro2.png");
//            over = new Texture("XXHDPI/gameOver2.png");
//
//
//            font = new BitmapFont();
//            font.setColor(Color.WHITE);
//            font.getData().setScale(6);
//
//            font2 = new BitmapFont();
//            font2.setColor(Color.WHITE);
//            font2.getData().setScale(3);
//
//
//
//
//        }
//        else
        if ((Gdx.graphics.getDensity() >= 2.0)  ) { // xhdpi
            rocket[0] = new Texture("XHDPI/rocket1111.png");
            rocket[1]= new Texture("XHDPI/rocket2222.png");
            obstacle = new Texture("XHDPI/planet1.png");
            obstacle2 = new Texture("XHDPI/satellite1.png");
            meteor = new Texture("XHDPI/meteor1.png");
            intro = new Texture("XHDPI/intro2.png");
            over = new Texture("XHDPI/gameOver2.png");


            font = new BitmapFont();
            font.setColor(Color.WHITE);
            font.getData().setScale(6);

            font2 = new BitmapFont();
            font2.setColor(Color.WHITE);
            font2.getData().setScale(3);
        }
        else if ((Gdx.graphics.getDensity() > 1.5)  && Gdx.graphics.getDensity() < 2.0) { //hdpi


            rocket[0] = new Texture("HDPI/rocket1111.png");
            rocket[1]= new Texture("HDPI/rocket2222.png");
            obstacle = new Texture("HDPI/planet1.png");
            obstacle2 = new Texture("HDPI/satellite1.png");
            meteor = new Texture("HDPI/meteor1.png");
            intro = new Texture("HDPI/intro2.png");
            over = new Texture("HDPI/gameOver2.png");


            font = new BitmapFont();
            font.setColor(Color.WHITE);
            font.getData().setScale(6);

            font2 = new BitmapFont();
            font2.setColor(Color.WHITE);
            font2.getData().setScale(3);
            b=2 ;
        }
        else { // ldpi
            rocket[0] = new Texture("LDPI/rocket1111.png");
            rocket[1] = new Texture("LDPI/rocket2222.png");
            obstacle = new Texture("LDPI/planet1.png");
            obstacle2 = new Texture("LDPI/satellite1.png");
            meteor = new Texture("LDPI/meteor1.png");
            intro = new Texture("LDPI/intro2.png");
            over = new Texture("LDPI/gameOver2.png");


            font = new BitmapFont();
            font.setColor(Color.WHITE);
            font.getData().setScale(6);

            font2 = new BitmapFont();
            font2.setColor(Color.WHITE);
            font2.getData().setScale(3);
            b=3;
        }








        prefs = Gdx.app.getPreferences("My Preferences");
        // config
        // maxTubeOffset = Gdx.graphics.getHeight()/2 - gap/2  - 150 ;
        randomGenerator = new Random() ;
        obstacleOffset = new float[numberObstacles] ;
        distanceBetweenTubes= Gdx.graphics.getHeight()/2 ;


        init();


        //// the rendreding part :


        rocketShape = new Rectangle[2 ];
        planet = new Circle[numberObstacles] ;
        meteorShape = new Circle[numberObstacles] ;
        satellite = new Rectangle[numberObstacles] ;

        for (int i= 0 ; i<numberObstacles; i++) {
            planet[i] = new Circle();


        }
        for (int i= 0 ; i<numberObstacles; i++) {
            meteorShape[i] = new Circle();


        }
        for (int i= 0 ; i<numberObstacles; i++) {
            satellite[i] = new Rectangle();


        }

        for (int i= 0 ; i<2; i++) {

            rocketShape[i] = new Rectangle();
        }


        shapeRenderer = new ShapeRenderer() ;


    }

    @Override
    public void render () {







        if (Gdx.input.justTouched() && gameState== 1   ) {
            gameState = 1;
            a *= -1;


            if ((velocity < 12/b) ) {

                //   b+=5 ;
                velocity ++ ;
                obstacleVelocity++;
            }

        }

        if (Gdx.input.justTouched() && (gameState == 0  ) ) {

            gameState= 1 ;

        }
        if (Gdx.input.justTouched() && (gameState == 2  )  ) {

            gameState=1 ;
        }







        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        if (gameState == 1) {

            if (rocketX > -4) {
                //velocity++ ;
                rocketX = rocketX + a * velocity/b ;//* force; ;
                // rocketY += velocity * force;

                for (int i = 0; i < numberObstacles; i++) {
                    obstacleY[i] -= obstacleVelocity;
                }

            } else {
                rocketX = Gdx.graphics.getWidth() + a * ((int)velocity/b);

            }

            if (rocketX > Gdx.graphics.getWidth()) {

                rocketX = a * ((int)velocity/b);

            }

            meteorX = meteorX - ((int)velocity/b);

            if (meteorX < 0) {

                meteorX = Gdx.graphics.getWidth();

            }


            if (flameState == 0) {

                flameState = 1;
            } else flameState = 0;


            /// the scoring part
            if (obstacleY[scoring] < Gdx.graphics.getWidth() / 2) {
                score++;

                if (scoring < numberObstacles - 1) {

                    scoring++;
                } else scoring = 0;
            }


            // rendering the assets

            for (int i = 0; i < numberObstacles; i++) {

                if (obstacleY[i] < -obstacle.getWidth()) {
                    obstacleY[i] = distanceBetweenTubes * numberObstacles;
                    obstacleOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - (gap + 400)/b);


                } else {
                    obstacleY[i] -= obstacleVelocity;
                }

                batch.draw(obstacle, Gdx.graphics.getWidth() / 2 - gap * 3 / 4 - obstacle.getWidth() / 2 + obstacleOffset[i], obstacleY[i]);
                batch.draw(obstacle2, Gdx.graphics.getWidth() / 2 - gap / i * 3 / 4 - obstacle2.getWidth() / 2 + obstacleOffset[i] + gap / i, obstacleY[i] - gap / i);
                batch.draw(meteor, meteorX, obstacleY[i]);


            }


            batch.draw(rocket[flameState], rocketX, rocketY);
            font.draw(batch, String.valueOf(score), Gdx.graphics.getWidth() / 2 - font.getSpaceWidth() / 2, Gdx.graphics.getHeight() - 100);
            font2.draw(batch, "BEST SCORE: " + String.valueOf(prefs.getInteger("best_score", 0) ), 100/b , Gdx.graphics.getHeight() - 100/b);

        }

        if (gameState == 0) {


            batch.draw(intro, Gdx.graphics.getWidth() / 2 - intro.getWidth() / 2, Gdx.graphics.getHeight() / 2 - intro.getHeight() / 2);
            font2.draw(batch, "BEST SCORE: " + String.valueOf(prefs.getInteger("best_score", 0) ), Gdx.graphics.getWidth()/2 -150/b , Gdx.graphics.getHeight()/5);



        }
        if (gameState == 2) {


            batch.draw(over, Gdx.graphics.getWidth() / 2 - over.getWidth() / 2, Gdx.graphics.getHeight() / 2 - over.getHeight() / 2);
            font.draw(batch,"SCORE : " +  String.valueOf(current), Gdx.graphics.getWidth() / 2 -170/b, Gdx.graphics.getHeight() /5 + 150/b);

            font2.draw(batch, "BEST SCORE: " + String.valueOf(prefs.getInteger("best_score", 0) ), Gdx.graphics.getWidth()/2 -150/b , Gdx.graphics.getHeight()/5);



        }





        batch.end();


//
//        if ((velocity < 15) && (score> 10 + b  ) ) {
//
//            b+=5 ;
//            velocity = velocity +3 ;
//            obstacleVelocity+= 3 ;
//        }







        // shape rendereing








        // shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //shapeRenderer.setColor(Color.RED);

        for (int i = 0 ; i< 2 ; i++) {
            rocketShape[i].set(rocketX + 60/b, rocketY + 60/b, rocket[flameState].getWidth() - 110/b, rocket[flameState].getHeight() - 100/b);
            //shapeRenderer.rect(rocketShape[i].x, rocketShape[i].y, rocketShape[i].getWidth(), rocketShape[i].getHeight());
        }
        for (int i= 0 ; i< numberObstacles ; i++ ) {

            planet[i].set(Gdx.graphics.getWidth() / 2 - gap * 3 / 4 - obstacle.getWidth()/2  + obstacleOffset[i]+150/b , obstacleY[i]+150/b , obstacle.getWidth()/3 +20/b);
            //shapeRenderer.circle(planet[i].x , planet[i].y , planet[i].radius);


            satellite[i].set(Gdx.graphics.getWidth() / 2 - gap/i  * 3 / 4 - obstacle2.getWidth()/2  + obstacleOffset[i] + gap/i + 60/b,  obstacleY[i] -gap/i + 60/b, obstacle2.getWidth() - 140/b, obstacle2.getHeight() - 150/b);
            //shapeRenderer.rect(satellite[i].x, satellite[i].y, satellite[i].getWidth(), satellite[i].getHeight());



            meteorShape[i].set(meteorX+80/b , obstacleY[i]+120/b , meteor.getWidth()/4 +20/b);
            //  shapeRenderer.circle(meteorShape[i].x , meteorShape[i].y , meteorShape[i].radius);









            if(Intersector.overlaps( planet[i] ,rocketShape[flameState] ) || Intersector.overlaps( meteorShape[i] , rocketShape[flameState]) || Intersector.overlaps( satellite[i] , rocketShape[flameState]) ){

                gameState= 2  ;
                Gdx.input.vibrate(300);
                //Gdx.app.log("collision" , " it is happening ");


                if ( score >  prefs.getInteger("best_score", 0)){
                    bestScoreShare();

                }

                current = score ;
                init();

            }

        }
        // shapeRenderer.end();




    }

    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }

    public void init(){
        //    distance = randomGenerator.nextInt(500);

        score = 0 ;
        obstacleVelocity = 5;
        velocity = 10 ;

        rocketX =Gdx.graphics.getWidth() / 2 - rocket[flameState].getWidth() / 2 ;
        rocketY = Gdx.graphics.getHeight()/ 6 ;
        meteorX = Gdx.graphics.getWidth() ;
        // obstacle variables

        obstacleX  = Gdx.graphics.getWidth()/2 ;
        obstacleY = new float[5] ; //;//Gdx.graphics.getHeight()* 3 / 4  ;


        for (int i= 0 ; i<numberObstacles; i++) {

            obstacleOffset[i] = (randomGenerator.nextFloat() - 0.5f ) * (Gdx.graphics.getWidth() - gap - 200);
            obstacleY[i] = Gdx.graphics.getHeight() *2  - obstacle.getWidth() / 2  + i * distanceBetweenTubes;
        }

    }


    public void  bestScoreShare(){




//put some Integer
        prefs.putInteger("best_score", score);

//persist preferences
        prefs.flush();

//get Integer from preferences, 0 is the default value.
        // Gdx.app.log("scorelast" , String.valueOf( prefs.getInteger("best_score", 0) ));

    }










}
