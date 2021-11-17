package com.example.greenpayremastered;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import database.UserData;

public class RegisterActivityScreen extends AppCompatActivity {


    private EditText txtEmailAddress;
    private EditText txtPassword;
    private EditText txtUsername;
    private Button picProfileImg;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;
    private ProgressBar regprogressBar;

    //GUI
    private Button loginButton;
    private Button registerButton;
    private Button profileImgButton;
    private Button uploadImageBtn;
    private ImageView profileImageView;
    private StorageReference mStorageRef;
    //ActivityResult mGetContent;
    ActivityResultLauncher<String> mGetContent;
    private Uri imagePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        txtEmailAddress = (EditText) findViewById(R.id.editTextViewEmail);
        txtPassword = (EditText) findViewById(R.id.editTextTextPassword1);
        txtUsername = (EditText) findViewById(R.id.usernameInput);  //usernameInput

        registerButton = (Button) findViewById(R.id.registerButton);
        loginButton = (Button) findViewById(R.id.insideLoginBtn);
        regprogressBar = (ProgressBar) findViewById(R.id.regprogressBar);
        profileImageView = (ImageView) findViewById(R.id.profilePic);
        profileImgButton = (Button) findViewById(R.id.chooseProfileBtn);
        uploadImageBtn =(Button) findViewById(R.id.uploadBtn);


        regprogressBar.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();



        //myRef = mFirebaseDatabase.getReference().child("UserData");

        mStorageRef = FirebaseStorage.getInstance().getReference();


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validateEmailAdresse(txtEmailAddress,txtPassword);
                createUser(); // Creates a user with custom fields username and email. Using the Create
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntentRegLogin = new Intent(RegisterActivityScreen.this, LoginActivity.class);
                startActivity(newIntentRegLogin);
            }
        });

        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                profileImageView.setImageURI(result);
                imagePath = result;
            }
        });
        profileImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("image/*");
                //getImageInImageView();
            }
        });
        uploadImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //uploadImageToDb();
                uploadImageBtn.setText("LASTET OPP");
            }
        });

    }//end of onCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==200&resultCode==RESULT_OK){
            imagePath= data.getData();
        }
    }

    private void getImageInImageView() {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);// KANASKJE LAGE EN LOKAL VARIABLE?
        } catch (IOException e) {
            e.printStackTrace();
        }
        profileImageView.setImageBitmap(bitmap);
    }



    private void uploadImageToDb() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Laster opp....");
        progressDialog.show();

        FirebaseStorage.getInstance().getReference("images/"+ UUID.randomUUID().toString())
                .putFile(imagePath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task)
            {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivityScreen.this,"Bildet lastet opp" , Toast.LENGTH_LONG).show();
                } else{
                  Toast.makeText(RegisterActivityScreen.this,"Noe gikk galt..." , Toast.LENGTH_LONG).show();
                }
                 progressDialog.dismiss();
            }//onComeplete end
        });
    }//Upload end

    private void createUser() {

        final String username = txtUsername.getText().toString();
        final String email = txtEmailAddress.getText().toString();//ALTERNATIVE CHECK IF matches(emailPattern)
        String passwordCheck = txtPassword.getText().toString(); //String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z.]+";

        //final String profilePath = profileImage;

        if (TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmailAddress.setError("Ugyldig epost");
            txtEmailAddress.requestFocus();
        } else if (TextUtils.isEmpty(passwordCheck)) {
            txtPassword.setError("Passord feltet må være fult");
            txtPassword.requestFocus();
        } else if (TextUtils.isEmpty(username)) {
            txtUsername.getText().toString();
            txtUsername.setError("Brukernavnet må være fult");
            txtUsername.requestFocus();
        } else {
            regprogressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, passwordCheck).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseDatabase.getInstance().getReference("user/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(new UserData(email,username, "")).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                regprogressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivityScreen.this, "Brukeren laget " + txtEmailAddress.getText().toString(), Toast.LENGTH_LONG).show();
                                    Intent loggedInside = new Intent(RegisterActivityScreen.this, LoginActivityScreen.class);
                                    startActivity(loggedInside);
                                } else {
                                    Toast.makeText(RegisterActivityScreen.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                    } else {
                        Toast.makeText(RegisterActivityScreen.this, "Ugyldig epost,passord eller brukernavn", Toast.LENGTH_LONG).show();
                    }

                }// end of task
            });
        }
    }//
}//END OF REG

