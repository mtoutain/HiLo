/**
 *  Purpose/Description of your app
 *  Design, build, test and deploy a guess a number Android app that allows the use to guess a number
 *  between 1 and 1000
 *  @author Michel Toutain (tout0004@algonquinlive.com)
 */

package ca.edumedia.tout0004.hilo;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String ABOUT_DIALOG_TAG = "About dialog";
    private EditText userGuess;
    Random randomNum = new Random();
    private int numberOfGuesses;
    int theNumber;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create random number between 1 and 1000
        theNumber = randomNum.nextInt(1000) + 1;

        userGuess = (EditText) findViewById(R.id.guessInput);
        final Button guessBtn = (Button) findViewById(R.id.guessBtn);
        final Button resetBtn = (Button) findViewById(R.id.resetBtn);

        guessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emptyUserInput = userGuess.getText().toString();

                //String userPatern = "^([1-9][0-9]{0,2}|1000)$";

                if (emptyUserInput.matches("")) {
                    userGuess.setError("Please Guess a Number");
                    userGuess.requestFocus();
                    return;
                }
                int userInput = Integer.parseInt(userGuess.getText().toString());

                if (userInput < 1 || userInput > 1000) {
                    userGuess.setError("Number Not Within Range");
                    userGuess.requestFocus();
                    return;
                }

                numberOfGuesses++;

                if (userInput == theNumber && numberOfGuesses <= 5) {

                    Toast.makeText(getApplicationContext(), getText(R.string.superior), Toast.LENGTH_LONG).show();
                }
                if(userInput == theNumber && numberOfGuesses >= 6 && numberOfGuesses <= 10){

                    Toast.makeText(getApplicationContext(), getText(R.string.excellent),Toast.LENGTH_LONG).show();
                }
                if(numberOfGuesses == 10 && userInput != theNumber){
                    Toast.makeText(getApplicationContext(), getText(R.string.lose),Toast.LENGTH_LONG).show();
                }
                if(userInput > theNumber && numberOfGuesses < 9){
                    Toast.makeText(getApplicationContext(), getText(R.string.tooHigh),Toast.LENGTH_SHORT).show();
                }
                if(userInput < theNumber && numberOfGuesses < 9){
                    Toast.makeText(getApplicationContext(), getText(R.string.tooLow),Toast.LENGTH_SHORT).show();
                }
                if(userInput > theNumber && numberOfGuesses == 9){
                    Toast.makeText(getApplicationContext(), getText(R.string.tooHighLast),Toast.LENGTH_SHORT).show();
                }
                if(userInput < theNumber && numberOfGuesses == 9){
                    Toast.makeText(getApplicationContext(), getText(R.string.tooLowLast),Toast.LENGTH_SHORT).show();
                }

                //guessBtn.setText(numberOfGuesses);
            }

        });


        resetBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                numberOfGuesses = 0;
                theNumber = randomNum.nextInt(1000) + 1;

            }

        });

        resetBtn.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view){
                Toast.makeText(getApplicationContext(), String.valueOf(theNumber),Toast.LENGTH_SHORT).show();
                return false;
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //handle action bar item clicks here
        int id = item.getItemId();

        if(id == R.id.action_about){
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
