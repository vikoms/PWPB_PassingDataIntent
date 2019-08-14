package com.example.passingdataintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainParcelable extends AppCompatActivity implements View.OnClickListener {


    public Button btnMoveActivity;
    public Button btnMoveWithDataActivity;
    public Button btnMoveWithObject;
    Button btnDialNumber;
    Button btnMoveResult;
    TextView tvResult;

    private int REQUEST_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parcelable);

        btnMoveActivity = (Button) findViewById(R.id.btn_move_activity);
        btnMoveActivity.setOnClickListener(this);

        btnMoveWithDataActivity = (Button) findViewById(R.id.btn_move_with_data_activity);
        btnMoveWithDataActivity.setOnClickListener(this);

        btnMoveWithObject = (Button) findViewById(R.id.btn_move_activity_object);
        btnMoveWithObject.setOnClickListener(this);

        btnDialNumber = findViewById(R.id.btn_dial_number);
        btnDialNumber.setOnClickListener(this);

        btnMoveResult = findViewById(R.id.btn_move_for_result);
        btnMoveResult.setOnClickListener(this);

        tvResult = findViewById(R.id.tv_result);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_move_activity:
                Intent moveIntent = new Intent(MainParcelable.this, ActivityMove.class);
                startActivity(moveIntent);
                break;
            case R.id.btn_move_with_data_activity:
                Intent moveWithDataIntent = new Intent(MainParcelable.this, ActivityMoveWithData.class);
                moveWithDataIntent.putExtra(ActivityMoveWithData.EXTRA_NAME,"Viko Muhammad Saputra" );
                moveWithDataIntent.putExtra(ActivityMoveWithData.EXTRA_AGE, 17);
                startActivity(moveWithDataIntent);
            break;
            case R.id.btn_move_activity_object:
                Person mPerson = new Person();
                mPerson.setName("Viko Muhammad Saputra");
                mPerson.setAge(17);

                mPerson.setEmail("vikoms95@gmail.com");

                mPerson.setCity("Bandung");
                Intent moveWithObjectIntent = new Intent(MainParcelable.this,ActivityMoveWithObject.class);

                moveWithObjectIntent.putExtra(ActivityMoveWithObject.EXTRA_PERSON, mPerson);
                startActivity(moveWithObjectIntent);
                break;

            case R.id.btn_dial_number:
                String phoneNumber = "081223823849";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + phoneNumber));
                startActivity(dialPhoneIntent);

                break;

            case R.id.btn_move_for_result:
                Intent moveForResultIntent = new Intent(MainParcelable.this,
                        ActivityMoveResult.class);
                startActivityForResult(moveForResultIntent, REQUEST_CODE);
                break;
        }

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE) {
            if(resultCode == ActivityMoveResult.RESULT_CODE) {
                int selectedValue = data.getIntExtra(ActivityMoveResult.EXTRA_SELECTED_VALUE, 0);
                tvResult.setText(String.format("Hasil : %s",selectedValue));

            }
        }
    }
}

