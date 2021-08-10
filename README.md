# android-webview (default)
Este é um projeto-modelo para o Android Studio feito em Java que permite criar um aplicativo webview para Android extremamente rapido. 
Você pode usá-lo para criar um aplicativo simples para seu site ou como ponto de partida para seu aplicativo Android em HTML.

# Como usar
Baixe ou clone este repositório e importe-o para o Android Studio.
<br>
Se deseja usar uma fonte local basta colocar os seus arquivos dentro de \app\src\main e em MainActivity.java deixar a linha 35 como:<br>
view_main.loadUrl("file:///android_asset/index.html");
<br><br>
Se deseja usar uma fonte externa no loadUrl basta colocar qualqer link que desejar ler, como no exeplo da linha 37:<br>
view_main.loadUrl("http://google.com/");<br>
# Extra
Este exemplo contem "Javascript Interface" que permite que você defina qualqur função java dentro da classe "WebAppInterface" (denotro do MainActivity.java mesmo) que pode ser acessado pelo javascript chamando a variavel "Android".
