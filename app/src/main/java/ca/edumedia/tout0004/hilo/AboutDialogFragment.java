package ca.edumedia.tout0004.hilo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Mitch on 2017-09-28.
 */

public class AboutDialogFragment extends DialogFragment {
    String[] message = {
            "Guess a Number Between 1 and 1000",
            "You Will Have 10 Tries To Guess The Number",
            "Have Fun!"
    };
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Decorate our About dialog
        builder.setTitle(R.string.action_about)
                //TODO replace with your name + userID
                .setMessage(getString(R.string.author)+"\n\n"+ getString(R.string.ruleOne)+"\n"+
                        getString(R.string.ruleTwo)+"\n\n"+ getString(R.string.ruleThree))
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
