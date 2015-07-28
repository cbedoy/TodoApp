package self.cbedoy.todoapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Carlos Bedoy on 7/28/15.
 * <p/>
 * Mobile App Developer - Todo App
 * <p/>
 */

public class MainActivity extends ActionBarActivity {


    private ListView mListView;
    private Button mActionButton;
    private EditText mInputView;

    private List<String> mDataModel;
    private ArrayAdapter<String> mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readItems();

        mListView = (ListView) findViewById(R.id.todoListView);
        mActionButton = (Button) findViewById(R.id.actionButton);
        mInputView = (EditText) findViewById(R.id.inputView);

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mDataModel);
        mListView.setAdapter(mAdapter);

        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = mInputView.getText().toString();

                mDataModel.add(value);

                mInputView.setText("");

                mAdapter.notifyDataSetChanged();

                writeItems();
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long l) {

                mDataModel.remove(index);

                mAdapter.notifyDataSetChanged();

                writeItems();

                return true;

            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {

                Intent intent = new Intent(MainActivity.this, EditItemActivity.class);

                intent.putExtra("index", index);
                intent.putExtra("value", mDataModel.get(index));

                startActivityForResult(intent, EditItemActivity.REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == EditItemActivity.REQUEST_CODE && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();

            String value = extras.getString("value", "");
            int index = extras.getInt("index", -1);

            if(!(value.equals("") || index == -1))
            {
                mDataModel.set(index, value);

                mAdapter.notifyDataSetChanged();

                writeItems();
            }

        }
    }

    private void readItems(){
        File dir = getFilesDir();
        File fileToOpen = new File(dir, "todo.txt");
        try
        {
            mDataModel = new ArrayList<>(FileUtils.readLines(fileToOpen));
        }catch (Exception e){
            mDataModel = new ArrayList<>();
        }
    }

    private void writeItems(){
        File dir = getFilesDir();
        File fileToWrite = new File(dir, "todo.txt");
        try{
            FileUtils.writeLines(fileToWrite, mDataModel);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings)
        {

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
