package com.hackathon.interviewme;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private ArrayList<Chat> mArrayList;
    private Context mContext;

    ChatAdapter(Context context, ArrayList<Chat> mArrayList) {
        this.mContext = context;
        this.mArrayList = mArrayList;
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chats, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Chat news = mArrayList.get(position);

        holder.text.setText(Html.fromHtml(news.getText()));
        if (news.getButtonFlag()) {
            //RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)holder.text.getLayoutParams();
            //params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            //holder.text.setLayoutParams(params);
            holder.button.setVisibility(View.VISIBLE);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, QuestionActivity.class));
                }
            });
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        ImageButton button;

        ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            button = itemView.findViewById(R.id.record);
        }
    }
}