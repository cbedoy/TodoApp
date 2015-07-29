package self.cbedoy.todoapp.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import self.cbedoy.todoapp.R;


public class EditItemActivity extends ActionBarActivity {

    public static int REQUEST_CODE = 23213;

    private EditText mInputView;
    private Button mActionConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Bundle extras = getIntent().getExtras();

        final String value = extras.getString("value");
        final int position = extras.getInt("position");

        mInputView = (EditText) findViewById(R.id.inputEdit);
        mActionConfirm = (Button) findViewById(R.id.confirmAction);

        mInputView.setText(value);

        mActionConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newValue = mInputView.getText().toString();

                Intent intent = getIntent();

                intent.putExtra("index", position);
                intent.putExtra("value", newValue);

                setResult(RESULT_OK, intent);

                finish();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            AlertDialog dialog
                    = new AlertDialog.Builder(this)
                    .setTitle("Code Path Prework")
                    .setMessage("Develop by Carlos Bedoy\n\nGithub:\nhttps://github.com/cbedoy\n\n :)")
                    .create();
            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
