package ac.id.atmaluhur.uts_amub_ti7a_1711500011_marcelinoariftjoanoto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterOneActivity extends AppCompatActivity {
    ImageView wut;
    Button next,back;
    EditText username, password, email;

    DatabaseReference reference;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_one);
        username = findViewById(R.id.usernameregisterone);
        password = findViewById(R.id.passwordregisterone);
        email = findViewById(R.id.emailregisterone);
        next = findViewById(R.id.nextregisterone);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences =getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(username_key,username.getText().toString());
                editor.apply();

                reference = FirebaseDatabase.getInstance().getReference().child("User/MarcelinoArif");
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("username").setValue(username.getText().toString());
                        dataSnapshot.getRef().child("password").setValue(password.getText().toString());
                        dataSnapshot.getRef().child("email").setValue(email.getText().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent gotonextregister = new Intent(RegisterOneActivity.this, RegisterTwoActivity.class);
                startActivity(gotonextregister);
            }
        });

    }
}