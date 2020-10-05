package br.com.alura.leilao.ui.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.alura.bidding.R;
import br.com.alura.leilao.model.Bidding;

public class ListaLeilaoAdapter extends RecyclerView.Adapter<ListaLeilaoAdapter.ViewHolder> {

    private final List<Bidding> leiloes;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public ListaLeilaoAdapter(Context context, List<Bidding> leiloes) {
        this.context = context;
        this.leiloes = leiloes;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_leilao, parent, false);
        return new ViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bidding bidding = pegaLeilaoPorPosicao(position);
        holder.vincula(bidding);
    }

    @Override
    public int getItemCount() {
        return leiloes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView descricao;
        private final TextView valor;
        private Bidding bidding;

        ViewHolder(View itemView) {
            super(itemView);
            descricao = itemView.findViewById(R.id.item_leilao_descricao);
            valor = itemView.findViewById(R.id.item_leilao_maior_lance);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(bidding);
                }
            });
        }

        void vincula(Bidding bidding) {
            this.bidding = bidding;
            descricao.setText(bidding.getDescription());
            valor.setText(String.valueOf(bidding.getHighestBid()));
        }

    }

    public Bidding pegaLeilaoPorPosicao(int posicao) {
        return this.leiloes.get(posicao);
    }

    public interface OnItemClickListener {
        void onItemClick(Bidding bidding);
    }

}
