package com.example.riyad.postbase;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
    public SimpleAdapter(Context context){
        super();
        this.context = context; // O contexto é necessário para criar a view
    }

    public List<Atividade> posts() {
        AtividadeDB atividadeDB = new AtividadeDB(this.context);
        List<Atividade> atividades = atividadeDB.findAll();
        return atividades;

    }

    @Override
    public int getCount(){

        return this.posts().size();//Retorna a quantidade de items no adapter
    }
    @Override
    public Object getItem(int position){
        return (this.posts().get(position).nome);//Retorna o objeto para essa posicao
    }
    @Override
    public long getItemId(int position){
        return position; // Retorna o id do objeto para esta posicao
    }
    @Override
    //Retorna a view para essa posição
    public View getView(final int position, View convertView, ViewGroup parent){
        TextView t = new TextView(context);
        final CardView c = new CardView(context);

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
        TextView p = new TextView(context);
        p.setGravity(Gravity.CENTER_VERTICAL + Gravity.CENTER_HORIZONTAL);
        p.setText(atividades.get(position).desc);
        p.setTextColor(Color.parseColor("#FAE3C6"));
        p.setTextSize(20f);

        //Parte do TextView (Custo)
        final TextView l = new TextView(context);
        l.setText(atividades.get(position).custo + " R$");
        l.setTextColor(Color.parseColor("#FAE3C6"));
        l.setTextSize(20f);
        l.setGravity(Gravity.BOTTOM + Gravity.RIGHT);
        l.setX(l.getX() - 10);

        //Parte do TextView (Data)
        final TextView d = new TextView(context);
        d.setTextColor(Color.parseColor("#FAE3C6"));
        d.setTextSize(10f);
        d.setGravity(Gravity.BOTTOM + Gravity.LEFT);
        d.setText(atividades.get(position).data);

        //Parte do Button (Deletar)
        final ImageButton bt_deletar = new ImageButton(context);
        bt_deletar.setX(0f);
        bt_deletar.setY(0f);
        bt_deletar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                .WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        bt_deletar.setImageResource(R.drawable.ic_delete);
        bt_deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Animation a = show ? encolher : aumentar;
                a.setDuration(1000);
                a.setFillAfter(true);
                //Deleta o elemento na posicao
                atividadeDB.delete(atividades.get(position));
                //
                toast("Atividade excluída!");
                c.startAnimation(a);
            }
        });

        //Parte do Button (Timer)
        final ImageButton bt_timer = new ImageButton(context);
        //Display para pegar o tamanho da tela
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        int largura = display.getWidth() - 105;
        bt_timer.setX(largura);
        bt_timer.setY(0f);
        bt_timer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                .WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        bt_timer.setImageResource(R.drawable.ic_action_play);
        bt_timer.setOnClickListener(new View.OnClickListener() {
            Atividade atividade = new Atividade();
            int  valor_atual;
            @Override
            public void onClick(View v) {
                final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                Timer ini;
                if(timer == null){
                    play = true;
                    bt_timer.setImageResource(R.drawable.ic_action_stop);
                    timer = new Timer();
                    ini = new Timer();
                    TimerTask tarefa = new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                System.out.println("Hora: " + format.format(new Date().getTime()));
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    };

                    timer.scheduleAtFixedRate(tarefa, 0, 1000);
                    toast("Tarefa iniciada!");
                    //Notificacao
                    notificacao("Atividade iniciada!", "A atividade " + posts().get(posts().size() - position - 1).nome + " foi iniciada!");

                }else if(play == true){
                    play = false;
                    bt_timer.setImageResource(R.drawable.ic_action_play);
                    toast("Tarefa encerrada!");
                    timer.cancel();
                    //Notificacao
                    notificacao("Atividade finalizada!", "A atividade " + posts().get(posts().size() - position - 1).nome + " foi finalizada!");
                }else if(!play){
                    play = true;
                    bt_timer.setImageResource(R.drawable.ic_action_stop);
                    timer = new Timer();
                    TimerTask tarefa = new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                System.out.println("Hora: " + format.format(new Date().getTime()));
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    };

                    timer.scheduleAtFixedRate(tarefa, 0, 1000);
                    //Notificacao
                    notificacao("Atividade re-iniciada!", "A atividade " + posts().get(posts().size() - position - 1).nome + " foi re-iniciada!");
                }

            }
        });

        //Parte do CardView
        int count = 0;
        if(posts().get(posts().size() - position - 1).prioridade.equals("baixa")){
            c.setCardBackgroundColor(Color.parseColor("#EFEFEF"));
            t.setTextColor(Color.parseColor("#463239"));
            p.setTextColor(Color.parseColor("#463239"));
            l.setTextColor(Color.parseColor("#463239"));
            d.setTextColor(Color.parseColor("#463239"));
        }else if (posts().get(posts().size() - position - 1).prioridade.equals("normal")){
            c.setCardBackgroundColor(Color.parseColor("#CAEBF2"));
            t.setTextColor(Color.parseColor("#254D32"));
            p.setTextColor(Color.parseColor("#254D32"));
            l.setTextColor(Color.parseColor("#254D32"));
            d.setTextColor(Color.parseColor("#254D32"));
        } else if (posts().get(posts().size() - position - 1).prioridade.equals("alta")){
            c.setCardBackgroundColor(Color.parseColor("#FF3B3F"));
            t.setTextColor(Color.parseColor("#D0DB97"));
            p.setTextColor(Color.parseColor("#D0DB97"));
            l.setTextColor(Color.parseColor("#D0DB97"));
            d.setTextColor(Color.parseColor("#D0DB97"));
        }

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
        count++;

        return c;
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
