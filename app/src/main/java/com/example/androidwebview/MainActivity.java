package com.example.androidwebview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public WebView view_main;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view_main = (WebView) findViewById(R.id.WVContent);
        /* CONFIGURANDO PARA JAVASCRIPT FUNCIONAR CORRETAMENTE NO WEBVIEW*/
        WebSettings webSettings = view_main.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setGeolocationEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        /* JAVASCRIPT INTERFACE PARA O CODIGO JAVASCRIPT INTERAGIR COM QUALQUER CODIGO JAVA QUE ADICIONAR DENTRO DA CLASSE WebAppInterface*/
        view_main.addJavascriptInterface(new WebAppInterface(this), "Android");
        webSettings.setDefaultTextEncodingName("utf-8");
        /* PARA LER ARQUIVOS LOCAIS: */
        view_main.loadUrl("file:///android_asset/index.html");
        /* PARA LER ARQUIVOS EXTERNOS, COMO SEU SITE POR EXEMPLO OU ARQUIVOS DO APP HOSPEDADOS EXTERNAMENTE:
        view_main.loadUrl("http://google.com/");
        *  */
    }
    /* DEFINA AS FUNÇÕES QUE PODERAM SER ACESSADOS PELA VARIAVEL "Android" PELO JAVASCRIPT EM SUA APLICAÇÃO HTML */
    public class WebAppInterface {
        Context mContext;
        /* Instancie a interface e defina o contexto */
        WebAppInterface(Context c) {
            mContext = c;
        }
        /* MOSTRANDO UM TOAST VINDO DO HTML */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onBackPressed() {
        /* QUANDO O BOTÃO VOLTAR É PRESSIONADO*/
        if (view_main.canGoBack()) {
            view_main.goBack();
            /* SE HOUVER HISTORICO, PAGINAS PARA VOLTAR VAI VOLTAR NO WEBVIEW */
        } else {
            /* SE NÃO HOUVER HISTORICO UMA MENSAGEM DE CONFIRMAÇÃO PARA FECHAR O APP */
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Fechar")
                    .setMessage("Tem certeza que deseja fechar o app?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            /* SE PRESSIONADO SIM O APP SERÁ FINALIZADO */
                        }

                    })
                    .setNegativeButton("Não", null)
                    .show();
        }
    }
}