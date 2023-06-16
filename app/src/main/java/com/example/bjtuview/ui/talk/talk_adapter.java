package com.example.bjtuview.ui.talk;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bjtuview.R;
import com.example.bjtuview.ui.show_talk_page;
import com.example.bjtuview.ui.query.MessageAdapter;
import com.example.bjtuview.ui.show_talk_page;

import java.util.List;

public class talk_adapter extends RecyclerView.Adapter<talk_adapter.talkViewHolder> {
    Context context;
    private List<talk_profile> talklist;

    public talk_adapter(Context context, List<talk_profile> talklist) {
        this.context = context;
        this.talklist = talklist;
    }

    @NonNull
    @Override
    public talk_adapter.talkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.talk_recycler_item,parent,false);
        return new talk_adapter.talkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull talk_adapter.talkViewHolder holder, int position) {
        holder.username.setText(talklist.get(position).getUser());
        holder.title.setText(talklist.get(position).getTitle());
        holder.time.setText(talklist.get(position).getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent = new Intent(context, show_talk_page.class);
               context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.talklist.size();
    }

    public class talkViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        TextView time;
        TextView username;

        public talkViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            time = itemView.findViewById(R.id.time);
            username = itemView.findViewById(R.id.username);
        }
    }

}
