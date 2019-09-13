package com.abaqustest.mygeotrackingapp.utils;

import android.content.Context;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.regex.Pattern;

/**
 * Utils class. It is used for Utility methods.
 *
 * @author Puneet Ahuja
 */
public class Utils {

    /**
     * Open price notify me dialog.
     *
     * @param mContext the m context
     * @param TAG      the tag
     * @param dialog   the dialog
     */
    public static void openFragmentDialog(Context mContext, String TAG, DialogFragment dialog) {
        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
        dialog.show(ft, TAG);
    }

    /**
     * For digits validation
     *
     * @param s the s
     * @return boolean boolean
     */
    public static boolean isStringContainsDigit(String s) {
        return Pattern.compile("[0-9]").matcher(s).find();
    }

    /**
     * For validation of special character
     *
     * @param s the s
     * @return boolean boolean
     */
    public static boolean isStringContainsSpecialCharacter(String s) {
        return Pattern.compile("[._/,:<>!~@#$%^&()+=?()\"|!\\[#$-]").matcher(s).find();
    }
}
