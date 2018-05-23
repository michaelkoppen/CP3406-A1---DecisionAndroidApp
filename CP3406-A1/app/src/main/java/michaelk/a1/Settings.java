package michaelk.a1;

import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class Settings extends AppCompatActivity {

    final String PREFERENCE_NAME = "value";
    SharedPreferences.Editor preferencesEditor;
    SharedPreferences preferences;
    CheckBox user_input;
    CheckBox coin_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        user_input = (CheckBox) findViewById(R.id.userInputButton);
        coin_image = (CheckBox) findViewById(R.id.coinImageButton);
        //preferencesEditor = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        //preferencesEditor.putInt("headstailtextstatus", 0).apply();
        //preferencesEditor.putInt("coinstatus", 1).apply();
        preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);


    }

    protected void onStart() {
        super.onStart();
        if(preferences.getInt("userinputtruefalse", 0) == 1) {
            user_input.setChecked(true);
        } else {
            user_input.setChecked(false);
        }
        if(preferences.getInt("coinstatustruefalse", 0) == 1) {
            coin_image.setChecked(true);
        } else {
            coin_image.setChecked(false);
        }
    }

    public void userInputToggle(View view) {
        if(user_input.isChecked()) {
            //user_input.setChecked(false);
            preferences.edit().putInt("userinputtruefalse", 1).apply();
        } else {
            //user_input.setChecked(true);
            preferences.edit().putInt("userinputtruefalse", 0).apply();
        }
    }

    public void coinImageToggle(View view) {
        if(coin_image.isChecked()) {
            //coin_image.setChecked(false);
            preferences.edit().putInt("coinstatustruefalse", 1).apply();
        } else {
            //coin_image.setChecked(true);
            preferences.edit().putInt("coinstatustruefalse", 0).apply();
        }
    }
}
