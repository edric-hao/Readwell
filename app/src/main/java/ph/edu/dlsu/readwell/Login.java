package ph.edu.dlsu.readwell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {
    private EditText loginUsername;
    private EditText loginPassword;
    private ProgressDialog loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPass);
        Button loginButton = findViewById(R.id.btnLogin);
        TextView signUpHere = findViewById(R.id.signUpHere);
        loading = new ProgressDialog(this);

        signUpHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    private void login() {
        String user = loginUsername.getText().toString();
        String pass = loginPassword.getText().toString();

        if (TextUtils.isEmpty(user)) {
            Toast.makeText(this, "You forgot to add your username", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "You forgot to add your password", Toast.LENGTH_SHORT).show();
        }
        else {
            loading.setTitle("Log In");
            loading.setMessage("Please wait while we are checking your account.");
            loading.setCanceledOnTouchOutside(false);
            loading.show();
            checkCredentials(user, pass);
        }
    }

    public void checkCredentials(final String username, final String password) {
        final DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Users").child(username).exists()) {
                    User tao = dataSnapshot.child("Users").child(username).getValue(User.class);
                    if (tao.getUsername().equals(username)) {
                        if (tao.getPassword().equals(password)) {
                            Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                            Intent intent = new Intent(Login.this, MainWindow.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(Login.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                        }
                    }
                }
                else {
                    loading.dismiss();
                    Toast.makeText(Login.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}