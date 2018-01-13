package org.sam.fortuneteller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import org.sam.fortuneteller.activity.NewResultActivity;
import org.sam.fortuneteller.model.Consts;
import org.sam.fortuneteller.model.Results;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newFortuneActivity(View view)
    {
        final EditText nameText = new EditText(this);
        AlertDialog namedDialog = new AlertDialog.Builder(this).setTitle(R.string.dlg_input)
                .setIcon(android.R.drawable.ic_dialog_info).setView(nameText)
                .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (nameText.getText().length() <= 0)
                        {
                            return;
                        }
                        dialogInterface.dismiss();
                    }
                }).setNeutralButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        createNewResults(nameText.getText().toString());
                    }
                }).show();
    }

    private void createNewResults(String name)
    {
        Intent newIntent = new Intent(this, NewResultActivity.class);
        newIntent.putExtra(Consts.ARG_RESULTS_NAME, name);
        startActivity(newIntent);
    }

    public void loadFortuneActivity(View view)
    {

    }

    public void exitFortuneActivity(View view)
    {

    }
}
