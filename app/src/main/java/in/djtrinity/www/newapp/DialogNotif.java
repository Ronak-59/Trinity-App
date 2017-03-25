package in.djtrinity.www.newapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

public class DialogNotif extends DialogFragment {

    String eventName;
    boolean check;
    SharedPreferences preferences;


    public void getData(String name, boolean check) {
        eventName = name;
        this.check = check;
    }
    EventDetailsActivity obj=new EventDetailsActivity();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        preferences = getActivity().getSharedPreferences("NotifPref", 0);

        if (!check) {
            builder.setMessage("Notify for " + eventName + "?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   obj.addEvent(getActivity());
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        } else {
            builder.setMessage("Cancel notification for " + eventName + "?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putBoolean(eventName, false);
                    editor.commit();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        }
        return builder.create();
    }
}
