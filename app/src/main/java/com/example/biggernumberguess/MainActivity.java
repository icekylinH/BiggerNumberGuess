package com.example.biggernumberguess;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;


public class MainActivity  extends AppCompatActivity{
    int randomNum;
    String guess;
    int count = 0;//counter for try

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randomNum = randomNumber(100);//generate the random number
        EditText userInput = (EditText)findViewById(R.id.userInput);
        userInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode()
                        && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //ensure both don and send actions can be received
                    EditText userInput = (EditText)findViewById(R.id.userInput);
                    guess = userInput.getText().toString();
                    compareNumber(guess);
                    return false;
                }
                return false;
            }
        });
    }
    public boolean validInput(String input){
        String pattern = "^[1-9]$|^[0-9][0-9]$|100|q|Q";//only allow user enter number 1-100 and q or Q
        if (Pattern.matches(pattern,input)) {
            return true;
        }
        return false;
    }
    public void compareNumber(String guess){
        if (validInput(guess)==false){
            setHint("Only the number between 1-100 is allowed.");
            return;
        }
        if (guess.equals("q") || guess.equals("Q")){
            setHint("You are quitting this app");
            randomNum = randomNumber(100);
            count = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                this.finishAffinity();
            }
            else{
                quitApp();
            }
            return;
        }

        if (Integer.parseInt(guess) > randomNum){
            setHint("Your guess is bigger than the number\nplease try again");
            count++;
            return;
        }
        if (Integer.parseInt(guess) < randomNum) {
            setHint("Your guess is less than the number\nplease try again");
            count++;
            return;
        }
        if (Integer.parseInt(guess) == randomNum){
            setHint("Congratulation!!\nYou got it!\nyou tired "+count+" times,please enter q to quit or play again");
            randomNum = randomNumber(100);
            count = 0;
            return;
        }

    }
    public void quitApp(){
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
    private int randomNumber(int bound){
        Random random;
        random = new Random();
        return random.nextInt(bound)+1;
    }
    private void setHint(String text){
        TextView hint = (TextView)findViewById(R.id.hint);
        hint.setText(text);
    }
}
