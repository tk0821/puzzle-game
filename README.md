# puzzle-game

5x5のマス目にピースをはめて、同じ色が5つ以上くっつくと消えるパズルゲームです。

ピースをはめる場所がなくなるとゲームオーバーです。

高いスコアを目指しましょう。

![puzzle](https://github.com/tk0821/puzzle-game/assets/141387547/bfb1748c-0c01-4892-baff-9546e867f4ba)

## 開発環境

OS： Windows 11

IDR：Eclipse

使用言語：　Java 17

使用フレームワーク： libGDX


Javaを学習中のため、Javaを使用しています。

簡単にゲーム開発ができて、クロスプラットフォームに対応したくなったときにすぐできそうななlibGDXを使うことにしました。

## 将来実装、変更したい機能

+ ピースの種類を増やす。
+ スコアを保存し、ローカルのランキングを表示させる。
+ ピースを消したときの演出を追加する。
+ androidで動かせるようにする。
+ BGM, SEの再生させる。
+ ShapeRendererで表示させている部分をassetで表示させる。
+ フォントをデフォルトから変更する。

## 実行

desktop
```
./gradlew.bat desktop:run
```
*windowsでのみ動作確認
