package br.com.alura.leilao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.bidding.R;
import br.com.alura.leilao.model.Bid;
import br.com.alura.leilao.model.Bidding;
import br.com.alura.leilao.model.User;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

public class ListaLeilaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_leilao);
        ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(this, leiloesDeExemplo());
        RecyclerView recyclerView = findViewById(R.id.lista_leilao_recyclerview);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ListaLeilaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Bidding bidding) {
                Intent vaiParaLancesLeilao = new Intent(ListaLeilaoActivity.this, LancesLeilaoActivity.class);
                vaiParaLancesLeilao.putExtra("leilao", bidding);
                startActivity(vaiParaLancesLeilao);
            }
        });
    }

    private List<Bidding> leiloesDeExemplo() {
        Bidding console = new Bidding("Console");
        Bid bid = new Bid(new User("Arthur"),1600);
        Bid bid2 = new Bid(new User("Mariane"),1800);
        Bid bid3 = new Bid(new User("Roger"),1200);
        console.propoe(bid);
        console.propoe(bid2);
        console.propoe(bid3);

        Bidding computador = new Bidding("Computador");
        Bid computadorlance = new Bid(new User("Arthur"),5000);
        Bid computadorlance2 = new Bid(new User("Mariane"),2500);
        Bid computadorlance3 = new Bid(new User("Roger"),1500);
        computador.propoe(computadorlance);
        computador.propoe(computadorlance2);
        computador.propoe(computadorlance3);

        Bidding carro = new Bidding("Carro");
        Bid carrolance = new Bid(new User("Arthur"),22000);
        Bid carrolance2 = new Bid(new User("Mariane"),20000);
        Bid carrolance3 = new Bid(new User("Roger"),15000);
        carro.propoe(carrolance);
        carro.propoe(carrolance2);
        carro.propoe(carrolance3);

        return new ArrayList<>(Arrays.asList(
                console,
                computador,
                carro
        ));
    }

}
