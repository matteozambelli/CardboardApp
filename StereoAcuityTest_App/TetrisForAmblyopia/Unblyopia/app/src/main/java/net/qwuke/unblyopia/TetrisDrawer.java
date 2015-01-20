package net.qwuke.unblyopia;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by RAPHAEL on 9/22/2014.
 */
public class TetrisDrawer {

    private Canvas currCanvas;
    private ShapeDrawable mDrawable;
    private Paint paint = new Paint();
    private TetrisModel tm;

    // dimension variables
    private int width;
    private int height;
    private int blockSize;
    private int xSideOffset;
    private int vertPadding;



    /** Turn row and column info into x and y values **/
    private int getX(int column) { return column * blockSize; }
    private int getY(int row)    { return row    * blockSize; }

    /**
     * Draws the blockArray onto the screen
     */
    public void drawShapes() {

        // draw fallen blocks
        for(int r = 0; r < tm.levelheight; r++) {
            for(int c = 0; c < tm.levelwidth; c++) {
                int color = tm.getBlock(c, r);
                if(color != 0) {
                    paint.setColor(tm.rightEyeBlockColor + color);
                    currCanvas.drawRect(xSideOffset + getX(c), getY(r) + vertPadding, xSideOffset + getX(c) + blockSize, getY(r) + blockSize + vertPadding, paint);

                    paint.setColor(tm.activeEyeBlockColor + color);
                    currCanvas.drawRect(xSideOffset + getX(c) + width/2, getY(r) + vertPadding, xSideOffset + getX(c) + blockSize + width/2, getY(r) + blockSize + vertPadding, paint);
                }
            }
        }

        // draw active blocks
        for(int i = 0; i < tm.row.length; i++) {
            int r = tm.row[i];
            int c = tm.col[i];
            int color = tm.getBlock(c, r);
            if(color != 0) {
                // clear active left block
                paint.setColor(tm.bgColor);
                currCanvas.drawRect(xSideOffset + getX(c), getY(r) + vertPadding, xSideOffset + getX(c) + blockSize, getY(r) + blockSize + vertPadding, paint);

                // draw active left block
                paint.setColor(tm.activeEyeBlockColor + color);
                currCanvas.drawRect(xSideOffset + getX(c), getY(r) + vertPadding, xSideOffset + getX(c) + blockSize, getY(r) + blockSize + vertPadding, paint);

                // clear active right block
                paint.setColor(tm.bgColor);
                currCanvas.drawRect(xSideOffset + getX(c) + width/2, getY(r) + vertPadding, xSideOffset + getX(c) + blockSize + width/2, getY(r) + blockSize + vertPadding, paint);

                // draw active left block
                paint.setColor(tm.rightEyeBlockColor + color);
                currCanvas.drawRect(xSideOffset + getX(c) + width/2, getY(r) + vertPadding, xSideOffset + getX(c) + blockSize + width/2, getY(r) + blockSize + vertPadding, paint);
            }
        }
    }



    /**
     * Erases the screen
     */
    public void eraseShapes() {
        paint.setColor(tm.bgColor);
        currCanvas.drawRect(xSideOffset, vertPadding, xSideOffset + blockSize*tm.levelwidth, height - vertPadding, paint);
        currCanvas.drawRect(xSideOffset + width/2, vertPadding, xSideOffset + blockSize*tm.levelwidth + width/2, height - vertPadding, paint);
    }



    /**
     * Method to make it easier to change the paint color
     */
    private void fill(int r, int g, int b) {
        paint.setColor((256 << 12) + (r << 8) + (g << 4) + b);
    }

    /**
     * Method to fill the screen with a color
     */
    private void background(int r, int g, int b) {
        int origColor = paint.getColor();

        fill(r, g, b);
        currCanvas.drawRect(0, 0, width, height, paint);

        paint.setColor(origColor);
    }



    /**
     * Draw GAME OVER on the screen
     */
    public void drawGameOverScreen() {
        // background(255, 255, 255);

        paint.setColor(Color.WHITE);

        paint.setTextSize(blockSize);
        currCanvas.drawText("GAME OVER", xSideOffset + width/48, vertPadding + height/16, paint);
        currCanvas.drawText("GAME OVER", xSideOffset + width/48 + width/2, vertPadding + height/16, paint);

        currCanvas.drawText("SCORE: " + tm.score, xSideOffset + width/48, vertPadding + height/16*2, paint);
        currCanvas.drawText("SCORE: " + tm.score, xSideOffset + width/48 + width/2, vertPadding + height/16*2, paint);

        currCanvas.drawText("Lines Cleared: " + tm.linesCleared, xSideOffset + width/48, vertPadding + height/16*3, paint);
        currCanvas.drawText("Lines Cleared: " + tm.linesCleared, xSideOffset + width/48 + width/2, vertPadding + height/16*3, paint);

        currCanvas.drawText("Act to restart", xSideOffset + width/48, vertPadding + height/16*4, paint);
        currCanvas.drawText("Act to restart", xSideOffset + width/48 + width/2, vertPadding + height/16*4, paint);
    }



    /**
     * Draws the main menu
     */
    public void drawMainMenu() {
        background(157, 184, 51);

        // "tetris"
        int tetrisR = (int) (255.0 * Math.sin(System.currentTimeMillis()/50.0) + 255);
        int tetrisG = (int) (255.0 * Math.cos(System.currentTimeMillis()/50.0) + 255);
        int tetrisB = (int) (100 + 100.0 * Math.cos(System.currentTimeMillis()/50));
        fill(tetrisR, tetrisG, tetrisB);

        paint.setColor(Color.WHITE);
        paint.setTextSize(36);
        currCanvas.drawText("Delaze", 135.0f/400*width/2, 180.0f/400*height, paint);
        currCanvas.drawText("Delaze", 135.0f/400*width/2 + width/2.0f, 180.0f/400*height, paint);

        // Regular mode
        fill(255, 213, 0);
        currCanvas.drawRect(114.0f/400*width/2, 167.0f/400*height, (114 + 182)/400.0f*width/2, (167 + 41)/400.0f*height, paint);
        currCanvas.drawRect(114.0f/400*width/2+width/2.0f, 167/400*height, (114 + 182)/400.0f*width/2+width/2.0f, (167 + 41)/400.0f*height, paint);
        paint.setTextSize(24);
        paint.setColor(Color.CYAN);
        currCanvas.drawText("Click to begin", 127.0f/400*width/2, 220.0f/400*height, paint);
        currCanvas.drawText("Click to begin", 127.0f/400*width/2+width/2.0f, 220.0f/400*height, paint);
    }


    public TetrisDrawer(WindowManager wm, TetrisModel tm) {
        this.tm = tm;

        // get screen width and height
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        // variables reliant on screen width and height
        vertPadding = height/4;
        blockSize = (height - 2*vertPadding) / 20;
        xSideOffset = width/4 - blockSize*tm.levelwidth/2;
    }

    public void setCanvas(Canvas c) {
        currCanvas = c;
    }
}
