package com.example.roomdatabseexample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewholder> {

    Context context;
    List<User> users;

    public MyAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.recycle1,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {

        holder.text.setText(String.valueOf(users.get(position).getUid()));
        holder.text1.setText(users.get(position).getFirstName());
        holder.text2.setText(users.get(position).getLastName());

        holder.delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //create db
                AppDatabase db = Room.databaseBuilder(context,
                        AppDatabase.class, "room_db").allowMainThreadQueries().build();

                //conectivity
                UserDao userDao = db.userDao();

                //this is delet record to room data base
                userDao.deletbyid(users.get(position).getUid());

                // this is delet recrd to recyclerview
                users.remove(position);

                notifyDataSetChanged();
            }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context,UpdateActivity.class);
                intent.putExtra("uid",String.valueOf(users.get(position).getUid()));
                intent.putExtra("ufname",users.get(position).getFirstName());
                intent.putExtra("ulname",users.get(position).getLastName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class viewholder extends RecyclerView.ViewHolder {

        TextView text,text1,text2;
        ImageView delet,update;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            delet=itemView.findViewById(R.id.delet);
            text=itemView.findViewById(R.id.text);
            text1=itemView.findViewById(R.id.text1);
            text2=itemView.findViewById(R.id.text2);
            update=itemView.findViewById(R.id.update);
        }
    }


}
