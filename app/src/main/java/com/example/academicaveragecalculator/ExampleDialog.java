package com.example.academicaveragecalculator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import static com.example.academicaveragecalculator.MainActivity.average;
import static com.example.academicaveragecalculator.MainActivity.counter;
import static com.example.academicaveragecalculator.MainActivity.allCredits;


public class ExampleDialog extends AppCompatDialogFragment {


        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder b = new AlertDialog.Builder(getActivity());

            b.setTitle("Information").setMessage("Your Average: "+ average/allCredits + "\n\nNumber Of Courses: " + counter + "\n\nNumber Of Credits: " + allCredits).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            return b.create();
        }
    }
