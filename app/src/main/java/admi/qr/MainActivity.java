package admi.qr;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler, View.OnClickListener {

    private ZXingScannerView mScannerView;
    Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
//        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_done:
                mDialog.dismiss();
                break;
        }
    }

    @Override
    public void handleResult(Result result) {
        if(result!=null){
            mScannerView.stopCamera();
            final TextView resultTv;
            Button done;
            mDialog = new Dialog(this, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mDialog.setContentView(R.layout.custome_result);
            resultTv = (TextView) mDialog.findViewById(R.id.result_tv);
            done = (Button) mDialog.findViewById(R.id.btn_done);
            resultTv.setText(result.getText().toString());
//        mScannerView.resumeCameraPreview(MainActivity.this);
            mDialog.show();
            done.setOnClickListener(this);
        }
    }
}
