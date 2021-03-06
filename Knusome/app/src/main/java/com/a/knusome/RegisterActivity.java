package com.a.knusome;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.a.knusome.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private static final int PICK_FROM_ALBUM = 10;
    private EditText idText;
    private EditText passwordText;
    private EditText nameText;
    private EditText ageText;
    private EditText deptText;
    private EditText sexText;
    private Button registerButton;
    private ImageView profileImage;
    private Uri imageUri;
    private Button email;
    private int vernum = 158760;
    private EditText verText;
    private Button correctButton;
    private int flag =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() .permitDiskReads() .permitDiskWrites() .permitNetwork().build());

        idText = (EditText) findViewById(R.id.idText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        nameText = (EditText) findViewById(R.id.nameText);
        ageText = (EditText) findViewById(R.id.ageText);
        deptText = (EditText) findViewById(R.id.deptText);
        sexText = (EditText) findViewById(R.id.sexText);
        registerButton = (Button) findViewById(R.id.registerButton);
        email = (Button) findViewById(R.id.verifybtn);
        verText = (EditText) findViewById(R.id.verifyText);
        correctButton = (Button) findViewById(R.id.correctbtn);

        //profile Image ?????????
        profileImage = (ImageView)findViewById(R.id.profileImage);
        profileImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent,PICK_FROM_ALBUM);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString() == null)
                    Toast.makeText(getApplicationContext(),"???????????? ???????????????",Toast.LENGTH_SHORT).show();

                else{
                    SendMail mailServer = new SendMail();
                    mailServer.sendSecurityCode(getApplicationContext(), idText.getText().toString() + "@knu.ac.kr");
                }
            }
        });

        correctButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v){
                if(verText.getText().toString().equals("158760")) {
                    Toast.makeText(getApplicationContext(), "?????????????????????", Toast.LENGTH_SHORT).show();

                    flag = 0;
                }

                else {
                    Toast.makeText(getApplicationContext(),"??????????????? ???????????????.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //registerButton??????
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //?????? ???????????? ??????
                if (idText.getText().toString() == null || passwordText.getText().toString() == null || nameText.getText().toString() == null || verText.getText().toString() == null || ageText.getText().toString() == null || deptText.getText().toString() == null || imageUri == null) {
                    Toast.makeText(getApplicationContext(),"?????? ????????? ???????????????",Toast.LENGTH_SHORT).show();
                    return;
                }

                //?????? ??????
                if (flag == 0) {
                    FirebaseAuth.getInstance()
                            .createUserWithEmailAndPassword(idText.getText().toString()+"@knu.ac.kr", passwordText.getText().toString())
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                //????????? ??????????????????
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //uid?????????
                                    final String uid = task.getResult().getUser().getUid();
                                    //image????????? ??????
                                    FirebaseStorage.getInstance().getReference().child("userImages").child(uid).putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                            //getDownLoadUrl()??????
                                            String imageUrl = task.getResult().getUploadSessionUri().toString();

                                            //model??? database??? ?????? ?????????
                                            UserModel userModel = new UserModel();
                                            userModel.uid = uid;
                                            userModel.userName = nameText.getText().toString();
                                            userModel.userAge = ageText.getText().toString();
                                            userModel.dept = deptText.getText().toString();
                                            userModel.profileImageUrl = imageUrl;
                                            userModel.sex = sexText.getText().toString();
                                            userModel.blockid = new ArrayList<>();

                                            FirebaseDatabase.getInstance().getReference().child("users").child(uid).setValue(userModel);

                                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                            finish();

                                        }
                                    });

                                }
                            });
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_FROM_ALBUM && resultCode==RESULT_OK){
            profileImage.setImageURI(data.getData());//????????? ?????? ??????
            imageUri=data.getData();//????????? ?????? ??????
        }
    }
}