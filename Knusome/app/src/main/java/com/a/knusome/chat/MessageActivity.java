package com.a.knusome.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a.knusome.R;
import com.a.knusome.fragment.ChatFragement;
import com.a.knusome.fragment.PeopleFragment;
import com.a.knusome.model.ChatModel;
import com.a.knusome.model.UserModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MessageActivity extends AppCompatActivity {

    private  String destinationUid;
    private Button button;
    private EditText edittext;
    private RecyclerView recyclerView;
    private String uid;
    private String chatRoomUid;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        destinationUid = getIntent().getStringExtra("destinationUid");
        button = (Button)findViewById(R.id.messageButton);
        edittext = (EditText) findViewById(R.id.messageEditText);
        recyclerView = (RecyclerView)findViewById(R.id.messageActivity_recyclerview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatModel chatModel = new ChatModel();
                chatModel.users.put(uid,true);
                chatModel.users.put(destinationUid,true);

                //????????? ?????? database??? ??????
                if(chatRoomUid == null){
                    button.setEnabled(false);
                    FirebaseDatabase.getInstance().getReference().child("chatrooms").push().setValue(chatModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            checkChatRoom();
                        }
                    });
                }else{
                    //?????? ???????????? ????????????
                    ChatModel.Comment comment = new ChatModel.Comment();
                    comment.uid = uid;
                    comment.message = edittext.getText().toString();
                    comment.timestamp = ServerValue.TIMESTAMP;
                    //push() : ????????? ?????? ????????????
                    FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid).child("comments").push().setValue(comment).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            //message ?????? ??? ?????????
                            edittext.setText("");
                        }
                    });
                }
                }
        });
        checkChatRoom();
    }

    //???????????? ??????????????? ??????
    void checkChatRoom(){
        //chatRoomUid??? ????????? uid??? ????????? ??????
        FirebaseDatabase.getInstance().getReference().child("chatrooms").orderByChild("users/"+uid).equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot item: dataSnapshot.getChildren()){
                    ChatModel chatModel = item.getValue(ChatModel.class);
                    if(chatModel.users.containsKey(destinationUid)){
                        chatRoomUid = item.getKey();
                        button.setEnabled(true);
                        //recyclerview?????????
                        recyclerView.setLayoutManager(new LinearLayoutManager(MessageActivity.this));
                        recyclerView.setAdapter(new RecyclerViewAdapter());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //recyclerview??? message?????????
    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        List<ChatModel.Comment> comments;
        UserModel userModel;

        public RecyclerViewAdapter(){
            //comments?????? database?????? ????????? list??? ?????????
                comments = new ArrayList<>();
            FirebaseDatabase.getInstance().getReference().child("users").child(destinationUid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //???????????? ??????
                    userModel = dataSnapshot.getValue(UserModel.class);
                    //message ????????????
                    getMessageList();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

        //comments??? ????????????
        void getMessageList(){
            FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid).child("comments").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    comments.clear();
                    //list??? item??????
                    for(DataSnapshot item : dataSnapshot.getChildren()){
                        comments.add(item.getValue(ChatModel.Comment.class));
                    }
                    notifyDataSetChanged();//??????
                    recyclerView.scrollToPosition(comments.size()-1);//???????????? ??????
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        //recyclerview ??????
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,parent,false);
            return new MessageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            //final ????????????
            final MessageViewHolder messageViewHolder = ((MessageViewHolder)holder);

            //??? id??? message??? ??????
            if(comments.get(position).uid.equals(uid)){
                //view??? comment??? ??????
                messageViewHolder.textView_message.setText(comments.get(position).message);
                messageViewHolder.textView_message.setBackgroundResource(R.drawable.rightbubble);
                messageViewHolder.linearLayout_destination.setVisibility(View.INVISIBLE);
                messageViewHolder.linearLayout_main.setGravity(Gravity.RIGHT);

            }else{
                //????????? profile ????????????

                //????????? ????????????
                final RecyclerView.ViewHolder Holder = holder;
                String uid = userModel.uid;
                FirebaseStorage.getInstance().getReference().child("userImages").child(uid).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>(){
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful())

                            Glide.with
                                    (Holder.itemView.getContext())
                                    .load(task.getResult())
                                    .apply(new RequestOptions().circleCrop())
                                    .into(messageViewHolder.imageView_profile);

                    }
                });

                //message ????????????
                messageViewHolder.textView_name.setText(userModel.userName);
                messageViewHolder.linearLayout_destination.setVisibility(View.VISIBLE);
                messageViewHolder.textView_message.setBackgroundResource(R.drawable.leftbubble);
                messageViewHolder.textView_message.setText(comments.get(position).message);
                //messageViewHolder.textView_message.setTextSize(25);
                messageViewHolder.linearLayout_main.setGravity(Gravity.LEFT);
            }

            //?????? ??????
            long unixTime = (long) comments.get(position).timestamp;
            Date date = new Date(unixTime);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            String time = simpleDateFormat.format(date);
            messageViewHolder.textView_timestamp.setText(time);

            }

        @Override
        public int getItemCount() {
            return comments.size();
        }

        private class MessageViewHolder extends RecyclerView.ViewHolder {
            public TextView textView_message;
            public TextView textView_name;
            public ImageView imageView_profile;
            public LinearLayout linearLayout_destination;
            public LinearLayout linearLayout_main;
            public TextView textView_timestamp;

            public MessageViewHolder(View view) {
                super(view);
                textView_message = (TextView) view.findViewById(R.id.messageItemTextview);
                textView_name = (TextView) view.findViewById(R.id.messageItem_textview_name);
                imageView_profile = (ImageView)view.findViewById(R.id.messageItem_imageview_profile);
                linearLayout_destination = (LinearLayout)view.findViewById(R.id.messageItem_linearlayout_destination);
                linearLayout_main = (LinearLayout)view.findViewById(R.id.messageItem_linearlayout_main);
                textView_timestamp = (TextView)view.findViewById(R.id.messageItem_Textvew_timestamp);
            }
        }
    }


}
