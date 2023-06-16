package com.example.bjtuview.ui.talk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bjtuview.R;

import java.util.List;

public class answer_adapter extends RecyclerView.Adapter<answer_adapter.answerViewHolder>
{
    Context context;
    private List<answer> answer_list;

    public answer_adapter(Context context, List<answer> answer_list) {
        this.context = context;
        this.answer_list = answer_list;
    }

    @NonNull
    @Override
    public answer_adapter.answerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.answer_recycler_item,parent,false);
        return new answer_adapter.answerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull answer_adapter.answerViewHolder holder, int position) {
        holder.username.setText(answer_list.get(position).getUsername());
        holder.content.setText(answer_list.get(position).getContent());
        holder.time.setText(answer_list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return this.answer_list.size();
    }

    public class answerViewHolder extends RecyclerView.ViewHolder
    {
        TextView content;
        TextView time;
        TextView username;

        public answerViewHolder(@NonNull View itemView) {
            super(itemView);

            content = itemView.findViewById(R.id.content);
            time = itemView.findViewById(R.id.time);
            username = itemView.findViewById(R.id.username);
        }
    }

}
