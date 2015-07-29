package self.cbedoy.todoapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
public class AddTODOActivity extends Activity
{
    public static int REQUEST_CODE = 31312;


    private EditText mTitleView;
    private EditText mMessageView;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_item);

        mTitleView = (EditText) findViewById(R.id.addTitleView);

        mMessageView = (EditText) findViewById(R.id.addDescriptionView);

        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
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
