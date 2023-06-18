package com.example.chatting;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.chatting.Models.Users;
import com.example.chatting.databinding.ActivitySettingsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class SettingsActivity extends AppCompatActivity {


    ActivitySettingsBinding binding;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        storage = FirebaseStorage.getInstance();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!binding.etStatus.getText().toString().isEmpty() && !binding.txtUsername.getText().toString().isEmpty()) {
                    String status = binding.etStatus.getText().toString();
                    String userName = binding.txtUsername.getText().toString();


                    HashMap<String, Object> obj = new HashMap<>();
                    obj.put("userName", userName);
                    obj.put("status", status);

                    database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                            .updateChildren(obj);

                    Toast.makeText(SettingsActivity.this, "Profile update", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingsActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                }
            }
        });


        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                Users users = dataSnapshot.getValue(Users.class);
                Picasso.get().load(users.getProfilePic()).placeholder(R.drawable.avatar3).into(binding.profileImage);

              if (users.getStatus() != null) {
                  binding.etStatus.setText(users.getStatus());
                  binding.txtUsername.setText(users.getUserName());
              }else {
                  binding.etStatus.setText("Hey there, I am using Chatting App");
                  binding.txtUsername.setText(users.getUserName());
              }
            }
        });


        binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent , 25);
            }
        });

        binding.textview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(SettingsActivity.this, "Privacy is update", Toast.LENGTH_SHORT).show();
            }
        });

        // call
        binding.textview6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // intent to call
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0676737549"));
                startActivity(intent);
            }
        });

        // about as
        binding.textview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // intent to about as open browser
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.whatsapp.com/about/"));
                startActivity(intent);
            }
        });

        binding.textview4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // intent to share app
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody = "Download this app";
                intent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
                startActivity(Intent.createChooser(intent, "Share Using"));

            }
        });

        binding.textview5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // intent for notification
                Toast.makeText(SettingsActivity.this, "Notification", Toast.LENGTH_SHORT).show();


            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data.getData() != null){
            Uri sFile = data.getData();
            binding.profileImage.setImageURI(sFile);

            final StorageReference reference = storage.getReference().child("profile_pic")
                    .child(FirebaseAuth.getInstance().getUid());


            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                    .child("profilePic").setValue(uri.toString());

                        }
                    });
                }
            });
        }

    }
}