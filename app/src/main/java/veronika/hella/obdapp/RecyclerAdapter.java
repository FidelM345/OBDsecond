package veronika.hella.obdapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    List<RecyclerModel> fullitemlist;
    Context context;


    public RecyclerAdapter(List<RecyclerModel> itemlist) {

            fullitemlist = itemlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item,parent,false);
        context=parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        String id=fullitemlist.get(i).getId();
        String title=fullitemlist.get(i).getTitle();

        viewHolder.setTextViewContents(id,title);

    }


    @Override
    public int getItemCount() {

        return fullitemlist.size();

    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView id,title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

          mView=itemView;

          id=itemView.findViewById(R.id.text1);
          title=itemView.findViewById(R.id.text2);

        }


        void setTextViewContents(String text1,String text2){

            id.setText(text1);
            title.setText(text2);

        }
    }
}
