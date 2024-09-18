# Clicker

Clickerはクリッカーゲームを遊べるプログラムです。  
このクリッカーゲームは、入力でポイントを増やし、そのポイントでポイントを自動で増やす装置や、ポイントが増えやすくなる装置を購入して効率よくポイントを増やしていくゲームです。  

# 作成理由

このジャンルのゲームが好きだったため作成しました。  
マルチスレッドでの処理を使った場所があり、その点が特に勉強になりました。  

# 機能概要

1.「Number」を増やしていくことが目的のゲームです。  
2.コマンドラインに「add」と入力すると、「Number」が1増えます。  
![2](https://github.com/user-attachments/assets/7321330d-9f21-44ce-8a4e-2ffbb53879e8)

3.コマンドラインに「buy」と入力すると、パワーアップアイテムの一覧が表示されます。  
![3](https://github.com/user-attachments/assets/0e8e0154-47aa-46a4-beb1-9b6a96573d96)

4.「Number」を消費することでパワーアップアイテムが購入できます。  
5.パワーアップアイテム一覧表示後にコマンドラインに買いたいアイテムの番号を入力することで、アイテムを購入できます。  
6.「MANUALAdder」の「POWERUP」を購入すると「add」と入力したときの「Number」の増加量が増えます。  
7.「AUTOAdder」は入力せずとも自動で「Number」を増やしてくれるアイテムです。  
8.「AUTOAdder」には「Tier」があり、「Tier」が高いほど強力です。  
9.「AUTOAdder」の「OWN」を購入すると「AUTOAdder」の数が１つ増えます。  
10.「AUTOAdder」の「POWERUP」を購入すると、「AUTOAdder」１つ当たりの「Number」増加量が増えます。  
11.パワーアップアイテムは購入するほど、高くなっていきます。  
12.「現在のManual Add Power」には、「add」を入力したときの「Number」増加量が表示されます。  
![12](https://github.com/user-attachments/assets/938f9836-2fa2-4ddf-99df-2fa6f4328223)

13.「現在のAuto Add Power」には、毎秒自動で増加していく「Number」の量が表示されます。  
14.「Number」などを表示しているウインドウを閉じるとゲームは終了します。  

# 起動方法

①バージョン21.0.2以降のJavaをインストールする。  
②このページからプログラムをダウンロードして、解凍する。  
③コマンドプロンプトを開く。  
④ダウンロードしたプログラムの「Clicker.jar」ファイルがあるディレクトリ(\out\artifacts\Clicker_jar)に移動する。  
⑤コマンド「java -jar Clicker.jar」を実行する。  

# 利用ライブラリ

・Java 21.0.2
