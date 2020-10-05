package ph.edu.dlsu.readwell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    private Button logoutBtn;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        logoutBtn = findViewById(R.id.logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);
            }
        });
//        save = findViewById(R.id.savebook);
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SaveToFireBase fire = new SaveToFireBase("LBYCPEI", "Madelein", "5", "A LBYCPEI BOOK", "https://lbycpei.com");
//            }
//        });
    }
}