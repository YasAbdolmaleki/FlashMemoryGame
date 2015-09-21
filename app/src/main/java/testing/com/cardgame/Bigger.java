package testing.com.cardgame;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Bigger extends ActionBarActivity {

    ImageButton ib1,
            ib2,
            ib3,
            ib4,
            ib5,
            ib6,
            ib7,
            ib8,
            ib9,
            ib10,
            ib11,
            ib12,
            ib13,
            ib14,
            ib15,
            ib16;

    ImageButton[] imageButtonsList;

    ImageButton ibPrevImageButtonID;
    Button bnRestart;
    int resImageID;
    private static Boolean flipped;
    List<String> copyRandomizedCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bigger);

        ib1 = (ImageButton) findViewById(R.id.ib1);
        ib2 = (ImageButton) findViewById(R.id.ib2);
        ib3 = (ImageButton) findViewById(R.id.ib3);
        ib4 = (ImageButton) findViewById(R.id.ib4);
        ib5 = (ImageButton) findViewById(R.id.ib5);
        ib6 = (ImageButton) findViewById(R.id.ib6);
        ib7 = (ImageButton) findViewById(R.id.ib7);
        ib8 = (ImageButton) findViewById(R.id.ib8);
        ib9 = (ImageButton) findViewById(R.id.ib9);
        ib10 = (ImageButton) findViewById(R.id.ib10);
        ib11 = (ImageButton) findViewById(R.id.ib11);
        ib12 = (ImageButton) findViewById(R.id.ib12);
        ib13 = (ImageButton) findViewById(R.id.ib13);
        ib14 = (ImageButton) findViewById(R.id.ib14);
        ib15 = (ImageButton) findViewById(R.id.ib15);
        ib16 = (ImageButton) findViewById(R.id.ib16);

        imageButtonsList = new ImageButton[]{ib1, ib2, ib3, ib4, ib5, ib6, ib7, ib8, ib9, ib10, ib11, ib12, ib13, ib14, ib15, ib16};
        bnRestart = (Button) findViewById(R.id.bnRestart);
        flipped=false;

        // initilize first time
        setRandomizedCards(RandomizingCards());

        // Randomize the cards
        bnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Copy to avoid continues method calls
                resetCardImages();
                insableAllImageButtons();
                resetTag();
                setRandomizedCards(RandomizingCards());
            }
        });
    }

    public void onClick(View v) {

        ImageButton currentImageButtonID = (ImageButton) findViewById(v.getId());
        setImageToImageButtons(currentImageButtonID);

        if (Flipped() == false) {
            // 2nd card is clicked
            //wait few seconds to flipp back the cards
            setDefaultImage(currentImageButtonID);
        } else {
            // 1st card is clicked, display image
            // Save this card for comparison with the 2nd card
            setPreviousClickedImage(currentImageButtonID);
        }
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

        // Initilize the list of image buttons
        sentImageButtonID.postDelayed(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < imageButtonsList.length; i++) {
                    if (imageButtonsList[i] == sentImageButtonID) {
                        tempCompare1[0] = copyRandomizedCards.get(i).toString();
                    }
                    if (imageButtonsList[i] == ibPrevImageButtonID) {
                        tempCompare1[1] = copyRandomizedCards.get(i).toString();
                    }
                    imageButtonsList[i].setEnabled(true);
                }

                // Check the images of the two cards that have been clicked (ignore if the same has been clicked multiple times)
                if ((tempCompare1[1] == tempCompare1[0]) && (sentImageButtonID != ibPrevImageButtonID)) {
                    // MATCHED and leave the cards displaying
                    sentImageButtonID.setEnabled(false);
                    ibPrevImageButtonID.setEnabled(false);
                    sentImageButtonID.setTag("MATCHED");
                    ibPrevImageButtonID.setTag("MATCHED");
                } else {
                    // NOT MATCHED and flip cards to default
                    if (ibPrevImageButtonID.getTag() != "MATCHED") {
                        ibPrevImageButtonID.setBackgroundResource(R.drawable.im10);
                    }
                    if (sentImageButtonID.getTag() != "MATCHED") {
                        sentImageButtonID.setBackgroundResource(R.drawable.im10);
                    }
                }

            }
        }, 1000);

    }

    public void disableAllImageButtons() {
        for (int i = 0; i<imageButtonsList.length; i++) {
            imageButtonsList[i].setEnabled(false);
        }
    }

    public void insableAllImageButtons() {
        for (int i = 0; i<imageButtonsList.length; i++) {
            imageButtonsList[i].setEnabled(true);
        }
    }

    public void resetTag() {
        for (int i = 0; i<imageButtonsList.length; i++) {
            imageButtonsList[i].setTag("NotMatched");
        }
    }

    public void resetCardImages() {
        for (int i = 0; i<imageButtonsList.length; i++) {
            imageButtonsList[i].setBackgroundResource(R.drawable.im10);
        }
    }

    public void setImageToImageButtons(final ImageButton sentImageButtonID) {
        // Set the randomized image names to each image button
        for (int i = 0; i<imageButtonsList.length; i++) {
            if (imageButtonsList[i]== sentImageButtonID) {
                resImageID = getResources().getIdentifier(copyRandomizedCards.get(i), "drawable", "testing.com.cardgame");
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
        String[] numbArray = { "im1", "im1", "im2", "im2" , "im3", "im3", "im4", "im4" , "im5", "im5", "im6", "im6", "im7", "im7", "im8", "im8" };
        List<String> aList = new ArrayList<String>();
        for (String s: numbArray) aList.add(s);
        Collections.shuffle(aList);
        return aList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bigger, menu);
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
