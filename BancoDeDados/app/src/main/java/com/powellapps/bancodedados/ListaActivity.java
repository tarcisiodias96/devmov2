package com.powellapps.bancodedados;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.powellapps.bancodedados.adapter.BandaAdapter;
import com.powellapps.bancodedados.bancodedados.BancoDeDados;
import com.powellapps.bancodedados.model.Banda;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    //Criando variável
    private ListView listViewBandas;
    private BandaAdapter bandaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        //Toolbar é a barra que fica no alto da tela dos aplicativos. Neste momento não será utilizada para nada!
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bandas");

        //Botão flutuante que aparece na maioria dos aplicativos novos.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //Implementação do click no botão flutuante
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Como o nome da variável diz, aqui é declarado uma intenção de sair da tela ListaActivity
                // e ir para tela de NovoActivity
                Intent intentParaNovoActivity = new Intent(ListaActivity.this, NovoActivity.class);
                //Esse é o método que realiza essa intenção
                startActivity(intentParaNovoActivity);
            }
        });

        //Referenciando com xml content_list
        listViewBandas = (ListView) findViewById(R.id.list_itens);
        //Inicia o adapter com o contexto e a lista de itens


        //Simulacao da lista
        final ArrayList<Banda> bandas = new ArrayList<>();
        Banda banda1 = new Banda();
        banda1.setNome("Calipso");
        banda1.setGenero("Tecnomelody");

        Banda banda2 = new Banda();
        banda2.setNome("Beatles");
        banda2.setGenero("Pop");
        bandas.add(banda2);
        bandas.add(banda1);
        bandaAdapter = new BandaAdapter(ListaActivity.this, bandas);
        //Atribui o adapter a lista dessa tela
        listViewBandas.setAdapter(bandaAdapter);

        //Liberar o click do adapter
        listViewBandas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Recupera a banda clicada da lista
                Banda minhaBandaPreferida = bandas.get(position);
                //Mostra uma mensagem na tela
                Toast.makeText(getApplicationContext(), "Minha banda preferida é: " +
                        minhaBandaPreferida.getNome(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        bandaAdapter.atualiza(new BancoDeDados(ListaActivity.this).getBandas());
    }
}
