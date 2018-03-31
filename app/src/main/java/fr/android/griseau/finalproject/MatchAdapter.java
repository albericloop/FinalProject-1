package fr.android.griseau.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MatchAdapter extends ArrayAdapter<Match> {
    public MatchAdapter(Context context, List<Match> matchs)
    {
        super(context, 0, matchs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.content_match, parent, false);
        }

        MatchViewHolder viewHolder = (MatchViewHolder) convertView.getTag();
        if (viewHolder == null){
            viewHolder = new MatchViewHolder();
            viewHolder.player1 = (TextView) convertView.findViewById(R.id.tvPlayer1);
            viewHolder.player2 = (TextView) convertView.findViewById(R.id.tvPlayer2);
            viewHolder.winner = (TextView) convertView.findViewById(R.id.tvWinner);
        }

        Match match = getItem(position);

        viewHolder.player1.setText(match.getPlayer1());
        viewHolder.player2.setText(match.getPlayer2());
        if (match.getWinner() == 1)
            viewHolder.winner.setText(match.getPlayer1());
        else
            viewHolder.winner.setText(match.getPlayer2());
        return convertView;
    }
    private class MatchViewHolder{
        public TextView player1;
        public TextView player2;
        public TextView winner;
    }
}
