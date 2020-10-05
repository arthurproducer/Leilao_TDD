package br.com.alura.leilao.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.alura.bidding.R;
import br.com.alura.leilao.model.Bid;
import br.com.alura.leilao.model.Bidding;

public class LancesLeilaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lances_leilao);
        Intent dadosRecebidos = getIntent();
        if(dadosRecebidos.hasExtra("leilao")){
            Bidding bidding = (Bidding) dadosRecebidos.getSerializableExtra("leilao");
            TextView descricao = findViewById(R.id.lances_leilao_descricao);
            TextView maiorLance = findViewById(R.id.lances_leilao_maior_lance);
            TextView menorLance = findViewById(R.id.lances_leilao_menor_lance);
            descricao.setText(bidding.getDescription());
            maiorLance.setText(String.valueOf(bidding.getHighestBid()));
            menorLance.setText(String.valueOf(bidding.getLowestBid()));
            listLance(bidding);
        }
    }

    public void listLance(Bidding bidding){
        TextView maioresLances = findViewById(R.id.lances_leilao_maiores_lances);

        StringBuilder sb = new StringBuilder();
        for(Bid bid : bidding.tresMaioresLances()){
            sb.append(bid.getValue() + "\n");
        }

        String maioresLanceEmTexto = sb.toString();

        maioresLances.setText(maioresLanceEmTexto);
    }
}
