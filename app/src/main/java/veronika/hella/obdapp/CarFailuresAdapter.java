package veronika.hella.obdapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CarFailuresAdapter extends RecyclerView.Adapter<CarFailuresAdapter.ViewHolder> {



    List<CarFailuresModel> fullitemlist;
    Context context;


    public CarFailuresAdapter(List<CarFailuresModel> itemlist) {

        fullitemlist = itemlist;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.failure_display,parent,false);
        context=parent.getContext();

        return new CarFailuresAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String errorcode=fullitemlist.get(i).getErrorcode();
        String errordesc=fullitemlist.get(i).getErrordesc();

        viewHolder.setTextViewContents(errorcode,errordesc);

    }

    @Override
    public int getItemCount() {
        return fullitemlist.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView errorcode1,errordesc1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView=itemView;

            errorcode1=itemView.findViewById(R.id.text_title);
            errordesc1=itemView.findViewById(R.id.text_content);

        }


        void setTextViewContents(String text1,String text2){

            errorcode1.setText(text1);
            errordesc1.setText(text2);

        }
    }
}
