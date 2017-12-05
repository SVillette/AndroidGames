package net.samael.villette.myapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import net.samael.villette.myapplication.R;
import net.samael.villette.myapplication.classes.MyConstants;

public class MainActivity extends AppCompatActivity
{
    private Context context = MainActivity.this;

    private EditText editLogin, editPassword;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = context.getSharedPreferences(MyConstants.KEY_PREFERENCES, MODE_PRIVATE);

        if (sp.getString(MyConstants.KEY_LOGIN, "").equals("")
                || sp.getString(MyConstants.KEY_PASSWORD, "").equals(""))
        {
            preferences();
        }

        editLogin = (EditText) findViewById(R.id.edit_login);
        editPassword = (EditText) findViewById(R.id.edit_password);

        editLogin.setText(sp.getString(MyConstants.KEY_LOGIN, ""));
        editPassword.setText(sp.getString(MyConstants.KEY_PASSWORD, ""));
        goCheck((View) editLogin.getParent());
    }

    public void preferences()
    {
        SharedPreferences.Editor spe = sp.edit();
        spe.putString(MyConstants.KEY_LOGIN, "login");
        spe.putString(MyConstants.KEY_PASSWORD, "admin");
        spe.apply();
    }

    public void goCheck(View v)
    {
        String login = editLogin.getText().toString();
        String password = editPassword.getText().toString();

        if (login.equals(sp.getString(MyConstants.KEY_LOGIN, ""))
                && password.equals(sp.getString(MyConstants.KEY_PASSWORD, "")))
        {
            Intent toMenuActivity = new Intent(context, MenuActivity.class);
            startActivity(toMenuActivity);
        } else {
            Toast.makeText(this, R.string.non_authenticated, Toast.LENGTH_SHORT).show();
        }
    }
}
