package gui;

import numberCount.NumberCountThread;
import save.SaveManager;

public class SaveDeleteWindow extends YesNoWindow{
    private NumberCountThread numberCountThread;

    public SaveDeleteWindow(NumberCountThread numberCountThread) {
        super("<html>本当にセーブデータを消去しますか？<br>「はい」を選ぶとセーブデータを消去して一度ゲームを終了します。<html>");
        if (numberCountThread == null) {
            System.out.println("セーブ消去の準備に失敗しました。");
            dispose();
        }
        this.numberCountThread = numberCountThread;
    }
    @Override
    public void yesAction() {
        numberCountThread.deleteSaveData();
        dispose();
    }

    @Override
    public void noAction() {
        dispose();
    }
}
