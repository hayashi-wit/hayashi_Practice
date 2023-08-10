package com.example.keyence_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

//必要なクラスをインポート
import com.keyence.autoid.sdk.scan.DecodeResult;
import com.keyence.autoid.sdk.scan.ScanManager;
//イベントリスナーを追加
public class MainActivity extends AppCompatActivity implements ScanManager.DataListener {
    private ScanManager mScanManager;
    //読み取りイベントの作成
    @Override
    public void onDataReceived(DecodeResult decodeResult) {
        // 読み取り結果の取得
        DecodeResult.Result result = decodeResult.getResult();
        // 読み取ったコード種の取得
        String codeType = decodeResult.getCodeType();
        // 読み取ったデータの取得
        String data = decodeResult.getData();

        TextView textView = findViewById(R.id.getNumber);
        textView.setText(data);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ScanManager クラスのインスタンスを作成
        mScanManager = ScanManager.createScanManager(this);
        // 読み取りイベント受け取るリスナーを作成
        mScanManager.addDataListener(this);
        // 読み取りを開始
        mScanManager.startRead();
        // 読み取り状態を取得
        if (mScanManager.isReading() == true) {
            // 読み取りを終了
//            mScanManager.stopRead();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // ScanManager クラスのインスタンスを破棄
        mScanManager.removeDataListener(this);
        // ScanManager クラスのインスタンスを破棄し、リソースを開放
        mScanManager.releaseScanManager();
    }
}
