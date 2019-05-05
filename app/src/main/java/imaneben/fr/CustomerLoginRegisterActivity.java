package imaneben.fr;

import android.app.ProgressDialog;
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

public class CustomerLoginRegisterActivity extends AppCompatActivity {

    private ImageView customer ;
    private Button CustomerLoginButton, CustomerRegisterButton ;
    private TextView CustomerRegisterLink, CustomerStatus ;
    private EditText EmailCustomer, PasswordCustomer ;

    private ProgressDialog loadingBar ;
    private FirebaseAuth mAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_register);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_customer_login_register);


        mAuth = FirebaseAuth.getInstance();

        customer  = (ImageView) findViewById(R.id.customer);
        CustomerLoginButton = (Button) findViewById(R.id.customer_login_btn);
        CustomerRegisterButton = (Button) findViewById(R.id.customer_register_btn);
        CustomerRegisterLink = (TextView) findViewById(R.id.register_customer_link);
        CustomerStatus = (TextView) findViewById(R.id.customer_status);
        EmailCustomer = (EditText) findViewById(R.id.email_customer);
        PasswordCustomer = (EditText) findViewById(R.id.password_customer);
        loadingBar = new ProgressDialog(this);


        // Changer la visibilité des attributs selon l'action : Pour le bouton créer un compte

        CustomerRegisterButton.setVisibility(View.INVISIBLE);
        CustomerRegisterButton.setEnabled(false);

        CustomerRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerLoginButton.setVisibility(View.INVISIBLE);
                CustomerRegisterLink.setVisibility(View.INVISIBLE);
                CustomerStatus.setText("Inscription de la cliente");

                CustomerRegisterButton.setVisibility(View.VISIBLE);
                CustomerRegisterButton.setEnabled(true);
            }
        });

        // Récupérer les informations : email,password : Pour le bouton créer un compte

        CustomerRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EmailCustomer.getText().toString();
                String password = PasswordCustomer.getText().toString();

                RegisterCustomer(email,password);
            }
        });

        CustomerLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EmailCustomer.getText().toString();
                String password = PasswordCustomer.getText().toString();

                SignInCustomer(email,password); // La méthode spéciale que je crée
            }
        });
    }

    // On vérifie que les champs email et mdp ne sont pas vides puis on traite la connexion

    private void SignInCustomer(String email, String password) {
        if(TextUtils.isEmpty(email)){

            Toast.makeText(CustomerLoginRegisterActivity.this,"Écriver votre email...", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(password)){

            Toast.makeText(CustomerLoginRegisterActivity.this,"Écriver votre mdp...", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Connexion de la cliente");
            loadingBar.setMessage("Nous vérifions vos informations...");
            loadingBar.show();

            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(CustomerLoginRegisterActivity.this ,"Connexion réussite !!",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else {
                                Toast.makeText(CustomerLoginRegisterActivity.this,"Échec de la connexion, veuillez réessayer...",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }

    }

    // On vérifie que les champs email et mdp ne sont pas vides puis on crée un compte

    private void RegisterCustomer(String email, String password) {

        if(TextUtils.isEmpty(email)){

            Toast.makeText(CustomerLoginRegisterActivity.this,"Écriver votre email...", Toast.LENGTH_SHORT).show();

        }

        if(TextUtils.isEmpty(password)){

            Toast.makeText(CustomerLoginRegisterActivity.this,"Écriver votre mdp...", Toast.LENGTH_SHORT).show();

        }
        else {
            loadingBar.setTitle("Inscription de la cliente");
            loadingBar.setMessage("Nous enregistrons vos informations...");
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(CustomerLoginRegisterActivity.this ,"Inscription réussite !!",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else {
                                Toast.makeText(CustomerLoginRegisterActivity.this,"Échec de l'inscription, veuillez réessayer...",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }

    }
}
