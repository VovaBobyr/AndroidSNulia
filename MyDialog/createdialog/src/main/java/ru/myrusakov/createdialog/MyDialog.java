package ru.myrusakov.createdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class MyDialog extends DialogFragment {

    private MainActivity context;

    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (MainActivity) context;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String text = getArguments().getString("text");
        return builder
                .setTitle("Удаление")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Вы уверены, что хотите удалить \"" + text + "\"?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        context.remove(text);
                    }
                })
                .setNegativeButton("Нет", null)
                .create();
    }
}
