package ph.edu.dlsu.readwell;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SaveToFireBase {
    public SaveToFireBase(final String title, final String author, final String rating,
                     final String description, final String pictureURL) {
        final DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.child("Books").child(title).exists()) {
                    HashMap<String, Object> book = new HashMap<>();
                    book.put("title", title);
                    book.put("author", author);
                    book.put("rating", rating);
                    book.put("description", description);
                    book.put("picture", pictureURL);
                    reference.child("Books").child(title).updateChildren(book);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
