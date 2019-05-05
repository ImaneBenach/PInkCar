package imaneben.fr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverLoginRegisterActivity extends AppCompatActivity {

    private ImageView driver ;
    private Button DriverLoginButton, DriverRegisterButton ;
    private TextView DriverRegisterLink, DriverStatus ;
    private EditText EmailDriver, PasswordDriver ;
    private ProgressDialog loadingBar ;

    private FirebaseAuth mAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login_register);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_driver_login_register);

        mAuth = FirebaseAuth.getInstance();

        driver  = (ImageView) findViewById(R.id.driver);
        DriverLoginButton = (Button) findViewById(R.id.driver_login_btn);
        DriverRegisterButton = (Button) findViewById(R.id.driver_register_btn);
        DriverRegisterLink = (TextView) findViewById(R.id.driver_register_link);
        DriverStatus = (TextView) findViewById(R.id.driver_status);
        EmailDriver = (EditText) findViewById(R.id.email_driver);
        PasswordDriver = (EditText) findViewById(R.id.password_driver);
        loadingBar = new ProgressDialog(this);

        // Changer la visibilité des attributs selon l'action : Pour le bouton créer un compte
        DriverRegisterButton.setVisibility(View.INVISIBLE);
        DriverRegisterButton.setEnabled(false);

        DriverRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DriverLoginButton.setVisibility(View.INVISIBLE);
                DriverRegisterLink.setVisibility(View.INVISIBLE);
                DriverStatus.setText("Inscription de la cliente");

                DriverRegisterButton.setVisibility(View.VISIBLE);
                DriverRegisterButton.setEnabled(true);
            }
        });

        // Récupérer les informations : email,password : Pour le bouton créer un compte
        DriverRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EmailDriver.getText().toString();
                String password = PasswordDriver.getText().toString();

                RegisterDriver(email,password); // La méthode spéciale que je crée
            }
        });

        // Récupérer les informations : email,password : Pour le bouton se connecter

        DriverLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EmailDriver.getText().toString();
                String password = PasswordDriver.getText().toString();

                SignInDriver(email,password); // La méthode spéciale que je crée
            }
        });
    }

    // On vérifie que les champs email et mdp ne sont pas vides puis on traite la connexion

    private void SignInDriver(String email, String password) {
        if(TextUtils.isEmpty(email)){

            Toast.makeText(DriverLoginRegisterActivity.this,"Écriver votre email...", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(password)){

            Toast.makeText(DriverLoginRegisterActivity.this,"Écriver votre mdp...", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Connexion de la conductrice");
            loadingBar.setMessage("Nous vérifions vos informations...");
            loadingBar.show();

            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(DriverLoginRegisterActivity.this ,"Connexion réussite !!",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent driverintent = new Intent(DriverLoginRegisterActivity.this,DriversMapActivity.class);
                                startActivity(driverintent);
                            }
                            else {
                                Toast.makeText(DriverLoginRegisterActivity.this,"Échec de la connexion, veuillez réessayer...",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }

    }

    // On vérifie que les champs email et mdp ne sont pas vides puis on crée l'utilisateur

    private void RegisterDriver(String email, String password) {

        if(TextUtils.isEmpty(email)){

            Toast.makeText(DriverLoginRegisterActivity.this,"Écriver votre email...", Toast.LENGTH_SHORT).show();

        }

        if(TextUtils.isEmpty(password)){

            Toast.makeText(DriverLoginRegisterActivity.this,"Écriver votre mdp...", Toast.LENGTH_SHORT).show();

        }
        else {
            loadingBar.setTitle("Inscription de la conductrice");
            loadingBar.setMessage("Nous enregistrons vos informations...");
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(DriverLoginRegisterActivity.this ,"Inscription réussite !!",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent driverintent = new Intent(DriverLoginRegisterActivity.this,DriversMapActivity.class);
                                startActivity(driverintent);
                            }
                            else {
                                Toast.makeText(DriverLoginRegisterActivity.this,"Échec de l'inscription, veuillez réessayer...",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }
}
