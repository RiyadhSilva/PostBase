package com.example.riyad.postbase;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by riyad on 29/08/2016.
 */
public class SimpleAdapter extends BaseAdapter{
    private Context context;
    //Instancia um timer
    public Timer timer = null;
    //Um boolean para saber se a tarefa esta em execucao ou nao
    public boolean play = false;
    private Animation a;
    private CardView c;
    private  TextView t;
    private TextView p;
    private TextView l;
    private TextView d;
    private int count;
    private ImageButton bt_deletar;
    private ImageButton bt_timer;
    private SimpleDateFormat format;
    private TimerTask tarefa;
    private long inicio;
    private String fim;
    private Boolean first;

    public SimpleAdapter(Context context){
        super();
        this.context = context; // O contexto é necessário para criar a view
    }

    public List<Atividade> atividades() {
        AtividadeDB atividadeDB = new AtividadeDB(this.context);
        List<Atividade> atividades = atividadeDB.findAll();
        return atividades;

    }

    public String nomeAtividade(int position){
        String nome;
        nome = atividades().get(atividades().size() - position - 1).nome;
        return nome;
    }

    public String prioridadeAtividade(int position){
        String prioridade;
        prioridade = atividades().get(atividades().size() - position - 1).prioridade;
        return prioridade;
    }

    @Override
    public int getCount(){

        return this.atividades().size();//Retorna a quantidade de items no adapter
    }
    @Override
    public Object getItem(int position){
        return (this.atividades().get(position).nome);//Retorna o objeto para essa posicao
    }
    @Override
    public long getItemId(int position){
        return position; // Retorna o id do objeto para esta posicao
    }
    @Override
    //Retorna a view para essa posição
    public View getView(final int position, View convertView, ViewGroup parent){
         t = new TextView(context);
         c = new CardView(context);

        //Inicia o banco
        final AtividadeDB atividadeDB = new AtividadeDB(this.context);
        final List<Atividade> atividades = atividadeDB.findAll();
        Collections.reverse(atividades);

        //Parte do TextView (Nome do autor do post)
        t.setText(atividades.get(position).nome);
        t.setGravity(Gravity.CENTER_HORIZONTAL);
        t.setTextColor(Color.parseColor("#FAA4BD"));
        t.setTextSize(15f);

        //Parte do TextView (Atividade)
        p = new TextView(context);
        p.setGravity(Gravity.CENTER_VERTICAL + Gravity.CENTER_HORIZONTAL);
        p.setText(atividades.get(position).desc);
        p.setTextColor(Color.parseColor("#FAE3C6"));
        p.setTextSize(20f);

        //Parte do TextView (Custo)
        l = new TextView(context);
        l.setText(atividades.get(position).custo + " R$");
        l.setTextColor(Color.parseColor("#FAE3C6"));
        l.setTextSize(20f);
        l.setGravity(Gravity.BOTTOM + Gravity.RIGHT);
        l.setX(l.getX() - 10);

        //Parte do TextView (Data)
        d = new TextView(context);
        d.setTextColor(Color.parseColor("#FAE3C6"));
        d.setTextSize(10f);
        d.setGravity(Gravity.BOTTOM + Gravity.LEFT);
        d.setText(atividades.get(position).data);

        //Parte do Button (Deletar)
        bt_deletar = new ImageButton(context);
        //Define o layout
        loadBtDeletarLayout();
        //Define a acao a ser tomada quando o botao for clicado
        bt_deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Carrega a animacao
                loadAnimation();
                //Deleta o elemento na posicao
                atividadeDB.delete(atividades.get(position));
                //FeedBack
                toast("Atividade excluída!");
                //Inicia a animacao
                c.startAnimation(a);
            }
        });

        //Parte do Button (Timer)
        bt_timer = new ImageButton(context);
        //Define o layout do botao
        loadBtTimerLayout();
        //Define acao para quando o botao for clicado
        bt_timer.setOnClickListener(new View.OnClickListener() {
            Atividade atividade = new Atividade();
            @Override
            public void onClick(View v) {
                loadTimer(position);

            }
        });

        //Parte do CardView
        count = 0;
        if(prioridadeAtividade(position).equals("baixa")){
            cardViewPrioridadeBaixa();
        }else if (prioridadeAtividade(position).equals("normal")){
            cardViewPrioridadeNormal();
        } else if (prioridadeAtividade(position).equals("alta")){
            cardViewPrioridadeAlta();
        }
        //Carrega o cardView
        loadCardView();
        return c;
    }

    private void loadBtDeletarLayout() {
        bt_deletar.setX(0f);
        bt_deletar.setY(0f);
        bt_deletar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                .WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        bt_deletar.setImageResource(R.drawable.ic_delete);
    }

    private void loadBtTimerLayout() {
        WindowManager windowManager = (WindowManager) context.getSystemService
                (Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        int largura = display.getWidth() - 105;
        bt_timer.setX(largura);
        bt_timer.setY(0f);
        bt_timer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                .WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        bt_timer.setImageResource(R.drawable.ic_action_play);
    }

    private void loadTimer(int position) {
        format = new SimpleDateFormat("HH:mm:ss");
        first = true;
        if(timer == null || !play){
            play = true;
            timer = new Timer();
            tarefa = new TimerTask() {
                @Override
                public void run() {
                    try {
                        if(first){
                            inicio = System.currentTimeMillis();
                            System.out.println("Inicio!\n"+"Hora: " + format.format(new Date().getTime()));
                            first = false;
                        }else{
                            System.out.println("Hora: " + format.format(new Date().getTime()));
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };

            timer.scheduleAtFixedRate(tarefa, 0, 1000);
            toast("A Tarefa " + nomeAtividade(position) + " foi iniciada!");

        }else if(play == true){
            format = new SimpleDateFormat("mm:ss");
            fim = format.format(System.currentTimeMillis() - inicio);
            System.out.println("Terminou com: " + fim);
            play = false;
            bt_timer.setImageResource(R.drawable.ic_action_play);
            toast("Tarefa encerrada! Com: " + fim);
            timer.cancel();
            //Envia um e-mail para o administrador
            Intent emailIntent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:?subject=" + "Organize - Atividade finalizada!" + "&body="
                    + "A atividade " + nomeAtividade(position) + " foi finalizada! \nCom " + fim
                    + " (Minutos:Segundos)"+ "&to=" +
                    "riyadhlevi@gmail.com");
            emailIntent.setData(data);
            context.startActivity(emailIntent);
            //Notificacao
            notificacao("Atividade finalizada!", "A atividade " + nomeAtividade(position) + " foi " +
                    "finalizada!");
        }
    }

    private void loadCardView() {
        c.setRadius(5f);
        c.setUseCompatPadding(true);
        c.setMinimumHeight(100);
        c.setMinimumHeight(280);
        c.addView(t, count);
        c.addView(p, count);
        c.addView(bt_deletar, count);
        c.addView(bt_timer, count);
        c.addView(d, count);
        c.addView(l, count);
        c.setClickable(true);
        c.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                toast("Você clicou em " + t.getText().toString());
            }
        });
        count++;
    }

    private void cardViewPrioridadeAlta() {
        c.setCardBackgroundColor(Color.parseColor("#FF3B3F"));
        t.setTextColor(Color.parseColor("#D0DB97"));
        p.setTextColor(Color.parseColor("#D0DB97"));
        l.setTextColor(Color.parseColor("#D0DB97"));
        d.setTextColor(Color.parseColor("#D0DB97"));
    }

    private void cardViewPrioridadeNormal() {
        c.setCardBackgroundColor(Color.parseColor("#CAEBF2"));
        t.setTextColor(Color.parseColor("#254D32"));
        p.setTextColor(Color.parseColor("#254D32"));
        l.setTextColor(Color.parseColor("#254D32"));
        d.setTextColor(Color.parseColor("#254D32"));
    }

    private void cardViewPrioridadeBaixa() {
        c.setCardBackgroundColor(Color.parseColor("#EFEFEF"));
        t.setTextColor(Color.parseColor("#463239"));
        p.setTextColor(Color.parseColor("#463239"));
        l.setTextColor(Color.parseColor("#463239"));
        d.setTextColor(Color.parseColor("#463239"));
    }

    private void loadAnimation() {
        boolean show = true; //Mostra ou não a view
        int angulo = 180;
        ScaleAnimation encolher = new ScaleAnimation(
                1.0f, 0.0f,
                1.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        ScaleAnimation aumentar = new ScaleAnimation(
                0.0f, 1.0f,
                0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        a = show ? encolher : aumentar;
        a.setDuration(1000);
        a.setFillAfter(true);
    }

    private void toast(String msg){

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void notificacao(String cTitle, String cText ){
        int id = 1;
        String contentTitle = cTitle;
        String contentText  = cText;
        Intent intent = new Intent(context, MainActivity.class);
        NotificationUtil.create(context, intent, contentTitle, contentText, id);
    }

}
