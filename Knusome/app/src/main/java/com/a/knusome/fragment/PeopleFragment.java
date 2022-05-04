package com.a.knusome.fragment;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.solver.widgets.Snapshot;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a.knusome.LoginActivity;
import com.a.knusome.MainActivity;
import com.a.knusome.R;
import com.a.knusome.chat.MessageActivity;
import com.a.knusome.model.ChatModel;
import com.a.knusome.model.UserModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PeopleFragment extends Fragment {

    View fview;
    private EditText searchText;
    private Button searchButton;
    private Button allButton;
    String myUid;
    RecyclerView rv;
    String searchdept;
    String mys;
    List<String> blockId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people,container,false);


        searchText = (EditText) view.findViewById(R.id.searchText);
        searchButton = (Button) view.findViewById(R.id.searchButton);
        allButton = (Button) view.findViewById(R.id.allButton);
        rv = (RecyclerView) view.findViewById(R.id.peoplefragment_recyclerview);
        myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference().child("users").child(myUid).child("blockid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                blockId = (List<String>)dataSnapshot.getValue();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    UserModel userModel = snapshot.getValue(UserModel.class);
                    if(userModel.uid.equals(myUid)){
                        mys= userModel.sex;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //입력 안했을때 처리
                if(searchText.getText().toString()==null){
                    return;
                }
                searchdept = searchText.getText().toString();
                listRefresh();
            }
        });
        allButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //입력 안했을때 처리
                searchdept = null;
                listRefresh();
            }
        });

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.peoplefragment_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        recyclerView.setAdapter(new PeopleFragmentRecyclerViewAdapter());
        return view;
    }

    //recyclerView
    class PeopleFragmentRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        //list 에 data(people) 저장
        List<UserModel> userModels;
        public PeopleFragmentRecyclerViewAdapter(){
            userModels = new ArrayList<>();
            FirebaseDatabase.getInstance().getReference().child("users").addValueEventListener(new ValueEventListener() {
                //data 받아오기
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    userModels.clear();
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        UserModel userModel = snapshot.getValue(UserModel.class);
                        //내 아이디일 경우 continue
                        if(userModel.uid.equals(myUid)){
                            continue;
                        }
                        //성별이 같을때
                        if(userModel.sex.equals(mys)){
                            continue;
                        }
                        //차단한거 일때
                        int check =0;
                        if(blockId!=null) {
                            for (String bi : blockId) {
                                if (userModel.uid.equals(bi)) {
                                    check = 1;
                                    break;
                                }
                            }
                        }
                        if(check==1)continue;
                        //찾지 않을때
                        if(searchdept ==null) {
                            userModels.add(userModel);
                        }
                        //찾을때
                        else{
                            if(userModel.dept.equals(searchdept)){
                                userModels.add(userModel);
                            }
                            else{
                                continue;
                            }
                        }
                    }
                    //새로고침
                    notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend,parent,false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,final int position) {

            final RecyclerView.ViewHolder Holder = holder;

            //이미지 넣어주기
            String uid = userModels.get(position).uid;
            FirebaseStorage.getInstance().getReference().child("userImages").child(uid).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>(){
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful())

                        Glide.with
                                (Holder.itemView.getContext())
                                .load(task.getResult())
                                .apply(new RequestOptions().circleCrop())
                                .into(((CustomViewHolder)Holder).imageView);

                }
            });
            //글씨넣기
            ((CustomViewHolder)holder).textView.setText(userModels.get(position).userName);

            //클릭시 이벤트
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fview = view;
                    //대화창으로 넘어가기
                    /*Intent intent = new Intent(view.getContext(), MessageActivity.class);
                    intent.putExtra("destinationUid",userModels.get(position).uid);
                    ActivityOptions activityOptions = ActivityOptions.makeCustomAnimation(view.getContext(),R.anim.fromright,R.anim.toleft);
                    startActivity(intent,activityOptions.toBundle());*/

                    //팝업창 띄우기
                    showDialog(view.getContext(),position);
                }
            });

        }

        @Override
        public int getItemCount() {
            return userModels.size();
        }

        //view만들기
        private class CustomViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public TextView textView;
            public CustomViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.frienditem_imageview);
                textView = (TextView) view.findViewById(R.id.frienditem_textview);
            }
        }

        void showDialog(Context context,final int position) {
            String name=userModels.get(position).userName;
            String age=userModels.get(position).userAge;
            String dept=userModels.get(position).dept;

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("                상대의 정보");
            builder.setMessage(name+"\n\n"+age+"\n\n"+dept);


            //LayoutInflater layoutInflater = getActivity().getLayoutInflater();
            //View view = layoutInflater.inflate(R.layout.metching_chat, null);


            builder.setPositiveButton("대화", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(fview.getContext(), MessageActivity.class);
                    intent.putExtra("destinationUid",userModels.get(position).uid);
                    ActivityOptions activityOptions = ActivityOptions.makeCustomAnimation(fview.getContext(),R.anim.fromright,R.anim.toleft);
                    startActivity(intent,activityOptions.toBundle());

                }
            }).setNegativeButton("차단", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    if(blockId==null)blockId = new ArrayList<>();
                    blockId.add(userModels.get(position).uid);
                    Log.i(this.getClass().getName(), "data 추가 성공");


                    FirebaseDatabase.getInstance().getReference().child("users").child(myUid).child("blockid").setValue(blockId).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.i(this.getClass().getName(), "새로고침");
                            listRefresh();
                        }
                    });







                }
            });
            builder.show();
        }
    }


    public void listRefresh() {

        /*방법1 - 레이아웃 매니저를 새로 붙여준다 */
/* rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
rv.setHasFixedSize(false);*/


        /*방법2 - 뷰 레이아웃을 모두 지워주고 아덥터를 다시 붙인다 */
        rv.removeAllViewsInLayout();
        rv.setAdapter(new PeopleFragmentRecyclerViewAdapter());
    }

}
