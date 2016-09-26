package com.example.riyad.postbase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {
    private TextView valorTotal;
    private TextView numAtividades;
    private GraphView graph;
    private BarGraphSeries<DataPoint> series;
    private List<Atividade> atividades = new ArrayList<>();
    private DataPoint[] dados;
    private int totalAtividades = 0;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        valorTotal = (TextView) findViewById(R.id.graph_tv_1);
        numAtividades = (TextView) findViewById(R.id.graph_tv_2);

        //Inicia conexao com o banco
        AtividadeDB atDB = new AtividadeDB(this);
        atividades = atDB.findAll();

        //Contabiliza os custos totais
        BigDecimal totalGastos = new BigDecimal("0");
        for (Atividade a:
             atividades) {
            BigDecimal atual = new BigDecimal(a.custo);
            totalGastos = totalGastos.add(atual);
            totalAtividades++;
        }

        //Seta os textViews com os valores obetidos
        valorTotal.setText(totalGastos.toString() + " R$");
        numAtividades.setText(String.valueOf(totalAtividades));

        //Incializa o grafico
        graph = (GraphView) findViewById(R.id.graph);
        dados = new DataPoint[totalAtividades + 1];
        //Faz uma busca nos dados
        Integer intCustos = 0;
        Integer numAtividade = 1;
        Integer intTotal = totalGastos.intValue();

        for (Atividade a:
             atividades) {
            intCustos = new BigDecimal(a.custo).intValue() + intCustos;
            DataPoint atual = new DataPoint(intCustos, numAtividade);
            System.out.println("Atividade " + numAtividade + ": " + intCustos);
            this.adiciona(atual);
            numAtividade++;
            intTotal = intTotal - intCustos;
        }

        DataPoint ultimo = new DataPoint(intCustos*2, numAtividade+1);
        this.adiciona(ultimo);

        series = new BarGraphSeries<>(dados);
        graph.addSeries(series);
    }

    public void adiciona(DataPoint atual){
        this.dados[this.position] = atual;
        position++;
    }
}
