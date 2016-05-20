package com.example.sh.zxingdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button mBtStart;
    private TextView mTextResult;
    private Button mBtnCreate;
    private CheckBox mCheckBox;
    private EditText mEdittContent;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mBtStart = (Button) findViewById(R.id.start);
        mBtStart.setOnClickListener(this);
        mTextResult = (TextView) findViewById(R.id.result);
        mBtnCreate = (Button) findViewById(R.id.btn_create);
        mBtnCreate.setOnClickListener(this);
        mCheckBox = (CheckBox) findViewById(R.id.check);
        mEdittContent = (EditText) findViewById(R.id.edit_content);
        mImage = (ImageView) findViewById(R.id.image_ma);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                startActivityForResult(new Intent(this, CaptureActivity.class), 0);
                break;
            case R.id.btn_create :
                String content = mEdittContent.getText().toString();
                Bitmap qrCode = EncodingUtils.createQRCode(content, 500, 500, mCheckBox.isChecked() ? BitmapFactory.decodeResource(getResources(),R.mipmap.logo) : null);
                mImage.setImageBitmap(qrCode);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getExtras().getString("result");
            mTextResult.setText(result);
        }
    }
}
