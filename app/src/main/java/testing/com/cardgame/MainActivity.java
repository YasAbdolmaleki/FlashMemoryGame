package testing.com.cardgame;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    ImageButton ibTL;
    ImageButton ibTR;
    ImageButton ibBL;
    ImageButton ibBR;
    ImageButton ibPrevImageButtonID;
    Button bnRestart, bnBigger;
    int resImageID;
    private static Boolean flipped;
    List<String> copyRandomizedCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bigger);
    }

    public Boolean Flipped() {
        if (flipped == false) {
            // 1st card has been flipped
            flipped = true;
        } else {
            //2nd card has been flipped
            flipped = false;
        }
        return flipped;
    }

    public void setDefaultImage(final ImageButton sentImageButtonID) {

        final String[] tempCompare1 = {"null1","null2"};

        // Don't allow user to click more cards
        disableAllImageButtons();


        //if (sentImageButtonID!=ibPrevImageButtonID){


        // Initilize the list of image buttons
        sentImageButtonID.postDelayed(new Runnable() {
            @Override
            public void run() {

                ImageButton[] imageButtonsList = {ibTL, ibTR, ibBL, ibBR};
                for (int i = 0; i < imageButtonsList.length; i++) {

                    if (imageButtonsList[i]==sentImageButtonID) {
                        tempCompare1[0] =copyRandomizedCards.get(i).toString();
                    }

                    if (imageButtonsList[i]==ibPrevImageButtonID) {
                        tempCompare1[1] =copyRandomizedCards.get(i).toString();
                    }
                    imageButtonsList[i].setEnabled(true);
                }


                // Check the images of the two cards that have been clicked (ignore if the same has been clicked multiple times)
                if( (tempCompare1[1]==tempCompare1[0]) && (sentImageButtonID!=ibPrevImageButtonID)){
                    // MATCHED and leave the cards displaying
                    sentImageButtonID.setEnabled(false);
                    ibPrevImageButtonID.setEnabled(false);
                } else {
                    // NOT MATCHED and flip cards to default
                    sentImageButtonID.setBackgroundResource(R.drawable.img3);
                    ibPrevImageButtonID.setBackgroundResource(R.drawable.img3);
                    //imageButtonsList[i].setEnabled(true);
                }
            }
        }, 1000);





    }

    public void disableAllImageButtons() {
        ImageButton[] imageButtonsList = {ibTL, ibTR, ibBL,ibBR };
        for (int i = 0; i<imageButtonsList.length; i++) {
            imageButtonsList[i].setEnabled(false);
        }
    }

    public void insableAllImageButtons() {
        ImageButton[] imageButtonsList = {ibTL, ibTR, ibBL,ibBR };
        for (int i = 0; i<imageButtonsList.length; i++) {
            imageButtonsList[i].setEnabled(true);
        }
    }

    public void resetCardImages() {
        ImageButton[] imageButtonsList = {ibTL, ibTR, ibBL,ibBR };
        for (int i = 0; i<imageButtonsList.length; i++) {
            imageButtonsList[i].setBackgroundResource(R.drawable.img3);
        }
    }

    public void setImageToImageButtons(ImageButton sentImageButtonID) {
        // Initilize the list of image buttons
        ImageButton[] imageButtonsList = {ibTL, ibTR, ibBL,ibBR };

        // Set the randomized image names to each image button
        for (int i = 0; i<imageButtonsList.length; i++) {
            if (imageButtonsList[i]==sentImageButtonID) {
                //System.out.println("YOLO: "+ copyRandomizedCards.get(i));
                resImageID = getResources().getIdentifier(copyRandomizedCards.get(i), "drawable", "testing.com.cardgame");
                //imageButtonsList[i].setBackgroundResource(resImageID);
                imageButtonsList[i].setBackgroundResource(resImageID);
            }
        }
    }

    public void setRandomizedCards(List<String> listSent) {
        copyRandomizedCards = listSent;
    }

    public List<String> getRandomizedCards() {
        return copyRandomizedCards;
    }

    public void setPreviousClickedImage(ImageButton ibCurImageButtonID) {
        ibPrevImageButtonID = ibCurImageButtonID;
    }

    public ImageButton getsetPreviousClickedImage() {
        return ibPrevImageButtonID;
    }

    public List<String> RandomizingCards() {
        // list of image names
        String[] numbArray = { "img7", "img7", "img8", "img8" };
        List<String> aList = new ArrayList<String>();
        for (String s: numbArray) aList.add(s);
        Collections.shuffle(aList);
        return aList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
