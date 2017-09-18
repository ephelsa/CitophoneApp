package co.edu.udea.estructuras.landresperez.citophoneapp.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import co.edu.udea.estructuras.landresperez.citophoneapp.R;

public class Login extends FragmentActivity {

    private EditText userName, password;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toBlocks();
            }
        });
    }

    // Private Methods
    private void toBlocks () {
        Intent intent = new Intent(Login.this, Block.class);

        startActivity(intent);
    }
}
