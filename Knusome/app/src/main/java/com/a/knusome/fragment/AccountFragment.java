package com.a.knusome.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.a.knusome.LoginActivity;
import com.a.knusome.MainActivity;
import com.a.knusome.R;
import com.a.knusome.model.ChatModel;
import com.a.knusome.model.UserModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class AccountFragment extends Fragment {
    static ImageView imageView;
    TextView textView_name;
    TextView textView_age;
    TextView textView_dept;
    TextView textView_sex;
    String uName;
    String uAge;
    String udept;
    String usex;
    Button deletebutton;
    List<String> chatModels = new ArrayList<>();
    final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_account,container,false);
        contain();
        imageView = (ImageView) view.findViewById(R.id.account_image);
        textView_name = (TextView) view.findViewById(R.id.account_name);
        textView_age = (TextView) view.findViewById(R.id.account_age);
        textView_dept = (TextView) view.findViewById(R.id.account_dept);
        textView_sex = (TextView) view.findViewById(R.id.account_sex);
        deletebutton = (Button) view.findViewById(R.id.delete);

        FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    UserModel userModel = snapshot.getValue(UserModel.class);
                    if(userModel.uid.equals(uid)){
                        uName= userModel.userName;
                        uAge = userModel.userAge;
                        udept = userModel.dept;
                        usex = userModel.sex;
                        textView_name.setText(uName);
                        textView_age.setText(uAge);
                        textView_dept.setText(udept);
                        textView_sex.setText(usex);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseStorage.getInstance().getReference().child("userImages").child(uid).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>(){
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful())

                    Glide.with
                            (AccountFragment.imageView.getContext())
                            .load(task.getResult())
                            .apply(new RequestOptions().circleCrop())
                            .into(AccountFragment.imageView);

            }
        });

        deletebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                contain();
                deleteId();
            }
        });




        return view;
    }

    void contain(){
        //대화방 담기
        FirebaseDatabase.getInstance().getReference().child("chatrooms").orderByChild("users/" + uid).equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chatModels.clear();//초기화
                //데이터 담기
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    chatModels.add(item.getKey());
                    Log.i(this.getClass().getName(),item.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    void deleteId() {
        //대화방 삭제
        if(chatModels.size()==0){
            Log.i(this.getClass().getName(), "ㅁ" );
            FirebaseStorage.getInstance().getReference().child("userImages").child(uid).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    //storage 삭제 완료

                    FirebaseDatabase.getInstance().getReference().child("users").child(uid).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            //database 삭제완료
                            FirebaseAuth.getInstance().getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        //user 삭제성공
                                        //로그아웃
                                        FirebaseAuth.getInstance().signOut();
                                        //화면전환
                                        ((MainActivity) getActivity()).changefragment();
                                    }
                                }
                            });
                        }
                    });
                }
            });
        }
        else{
        for (String chat : chatModels) {
            Log.i(this.getClass().getName(), "ㅁ" + chat);
            FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chat).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    //대화방 삭제성공
                    Log.i(this.getClass().getName(), "대화방 삭제 성공");
                    //해당 storage삭제
                    FirebaseStorage.getInstance().getReference().child("userImages").child(uid).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            //storage 삭제 완료

                            FirebaseDatabase.getInstance().getReference().child("users").child(uid).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    //database 삭제완료
                                    FirebaseAuth.getInstance().getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                //user 삭제성공
                                                //로그아웃
                                                FirebaseAuth.getInstance().signOut();
                                                //화면전환
                                                ((MainActivity) getActivity()).changefragment();
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    });


                }
            });


        }


        }
    }
}
