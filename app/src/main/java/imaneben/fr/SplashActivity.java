package imaneben.fr;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SplashActivity extends AppCompatActivity {

    private ImageView pinkcar ;
    private TextView tv_pinkcar, tv_imane ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_splash);

        pinkcar = (ImageView) findViewById(R.id.pinkcar);
        tv_imane = (TextView) findViewById(R.id.tv_imane);
        tv_pinkcar = (TextView) findViewById(R.id.tv_pinkcar);

        final Thread thread = new Thread() {
            @Override
            public void run() {
                try{
                    sleep(3000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent welcomeIntent = new Intent(SplashActivity.this, WelcomeActivity.class);
                    startActivity(welcomeIntent);
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
