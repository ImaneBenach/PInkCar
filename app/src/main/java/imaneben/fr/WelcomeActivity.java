package imaneben.fr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class WelcomeActivity extends AppCompatActivity {

    private ImageView pinkcar ;
    private Button WelcomeDriverButton, WelcomeCustomerButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_welcome);

        pinkcar = (ImageView) findViewById(R.id.pinkcar);
        WelcomeCustomerButton = (Button) findViewById(R.id.welcome_customer_btn);
        WelcomeDriverButton = (Button) findViewById(R.id.welcome_driver_btn);

        WelcomeCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginRegisterCustomerIntent = new Intent(WelcomeActivity.this, CustomerLoginRegisterActivity.class);
                startActivity(LoginRegisterCustomerIntent);
            }
        });

        WelcomeDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginRegisterDriverIntent = new Intent(WelcomeActivity.this, DriverLoginRegisterActivity.class);
                startActivity(LoginRegisterDriverIntent);
            }
        });

    }
}
