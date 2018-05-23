package michaelk.a1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView coinImage;
    ProgressBar coinFlippingBar;
    Button flipButton;
    Button settingsButton;
    LinearLayout headtailtext;
    Button clearTextButton;
    EditText headsText;
    EditText tailsText;
    TextView resultText;
    Integer[] coin = {R.drawable.heads, R.drawable.tails};
    Random r;
    final String PREFERENCE_NAME = "value";
    SharedPreferences.Editor preferencesEditor;
    SharedPreferences preferences;
    int userinputtruefalse;
    int coinstatustruefalse;
    int flippinganimation;
    int headsortails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coinImage = (ImageView) findViewById(R.id.coinImage);
        coinFlippingBar = (ProgressBar) findViewById((R.id.coinFlippingBar));
        flipButton = (Button) findViewById(R.id.flipButton);
        settingsButton = (Button) findViewById(R.id.settingsButton);
        headtailtext = (LinearLayout) findViewById(R.id.headtailtext);
        clearTextButton = (Button) findViewById(R.id.cleartextbutton);
        headsText = (EditText) findViewById(R.id.headsText);
        tailsText = (EditText) findViewById(R.id.tailsText);
        resultText = (TextView) findViewById(R.id.resulttext);

        preferencesEditor = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        preferencesEditor.putInt("userinputtruefalse", 1).apply();
        preferencesEditor.putInt("coinstatustruefalse", 1).apply();
        preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);


        if(userinputtruefalse == 1) {
            headtailtext.setVisibility(View.VISIBLE);
        } else {
            headtailtext.setVisibility(View.INVISIBLE);
        }
        r = new Random();
        flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultText.setVisibility(View.INVISIBLE);
                coinImage.setImageBitmap(null);
                int progress = 0;
                coinFlippingBar.setProgress(progress);
                ValueAnimator animator = ValueAnimator.ofInt(0, coinFlippingBar.getMax());
                animator.setDuration(3000);
                flippinganimation = 0;
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        coinFlippingBar.setProgress((Integer)animation.getAnimatedValue());
                        if (coinstatustruefalse == 1) {
                            if (flippinganimation == 0) {
                                coinImage.setImageResource(coin[flippinganimation]);
                                flippinganimation = 1;
                            } else if (flippinganimation == 1) {
                                coinImage.setImageResource(coin[flippinganimation]);
                                flippinganimation = 0;
                            }
                        }

                    }
                });
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        headsortails = r.nextInt(coin.length);
                        if(coinstatustruefalse == 1) {
                            coinImage.setImageResource(coin[headsortails]);
                        } else {
                            resultText.setVisibility(View.VISIBLE);
                            if(headsortails == 0) {
                                resultText.setText("HEADS");
                            } else {
                                resultText.setText("TAILS");
                            }
                        }
                    }
                });
                animator.start();
            }
        });
    }

    //@Override
    protected void onResume() {
        super.onResume();
        userinputtruefalse = preferences.getInt("userinputtruefalse", 1);
        coinstatustruefalse = preferences.getInt("coinstatustruefalse", 1);
        if(coinstatustruefalse == 0) {
            coinImage.setImageBitmap(null);
        }
        if(userinputtruefalse == 1) {
            headtailtext.setVisibility(View.VISIBLE);
            clearTextButton.setVisibility(View.VISIBLE);
        } else {
            headtailtext.setVisibility(View.INVISIBLE);
            clearTextButton.setVisibility(View.INVISIBLE);
        }
    }

    public void settings(View view) {
        Intent openSettings = new Intent(this, Settings.class);
        startActivity(openSettings);
    }

    public void clearText(View view) {
        headsText.setText("");
        tailsText.setText("");
    }
}
