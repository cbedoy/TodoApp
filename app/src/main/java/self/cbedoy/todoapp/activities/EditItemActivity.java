package self.cbedoy.todoapp.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import self.cbedoy.todoapp.R;


public class EditItemActivity extends ActionBarActivity {

    public static int REQUEST_CODE = 23213;

    private EditText mTitleEditView;
    private EditText mDescriptionEditView;
    private TextView mDateEditView;
    private Button mActionConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Bundle extras = getIntent().getExtras();


        String title = extras.getString("title");
        String description = extras.getString("description");
        int date = extras.getInt("date");
        final int position = extras.getInt("position");


        mTitleEditView = (EditText) findViewById(R.id.titleEditView);
        mDescriptionEditView = (EditText) findViewById(R.id.descriptionEditView);
        mDateEditView = (TextView) findViewById(R.id.dateEditView);
        mActionConfirm = (Button) findViewById(R.id.confirmEditView);

        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String dateFormated = df.format(new Date(date));

        mTitleEditView.setText(title);
        mDescriptionEditView.setText(description);
        mDateEditView.setText(dateFormated);

        mActionConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titleData = mTitleEditView.getText().toString();
                String descriptionData = mDescriptionEditView.getText().toString();

                Intent intent = getIntent();

                intent.putExtra("position", position);
                intent.putExtra("value", titleData);
                intent.putExtra("description", descriptionData);

                setResult(RESULT_OK, intent);

                finish();

            }
        });

        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            Explode explode = new Explode();
            explode.setDuration(2000);
            getWindow().setEnterTransition(explode);
        }
    }

}
