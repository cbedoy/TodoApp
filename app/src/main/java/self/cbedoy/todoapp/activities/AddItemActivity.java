package self.cbedoy.todoapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.melnykov.fab.FloatingActionButton;

import self.cbedoy.todoapp.R;

/**
 * Created by Carlos Bedoy on 7/29/15.
 * <p/>
 * Mobile App Developer - Todo App
 * <p/>
 * Pademobile
 */
public class AddItemActivity extends ActionBarActivity
{
    public static int REQUEST_CODE = 31312;


    private EditText mTitleView;
    private EditText mMessageView;

    private Button mAcceptView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_item);

        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            Explode explode = new Explode();
            explode.setDuration(2000);
            getWindow().setEnterTransition(explode);
        }

        mTitleView = (EditText) findViewById(R.id.addTitleView);

        mMessageView = (EditText) findViewById(R.id.addDescriptionView);

        mAcceptView = (Button) findViewById(R.id.confirmView);

        mAcceptView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = mTitleView.getText().toString();
                String description = mMessageView.getText().toString();

                Intent intent = getIntent();

                intent.putExtra("title", title);
                intent.putExtra("description", description);

                setResult(RESULT_OK, intent);

                finish();
            }
        });




    }
}
