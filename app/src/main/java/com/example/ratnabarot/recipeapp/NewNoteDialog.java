package com.example.ratnabarot.recipeapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public class NewNoteDialog extends DialogFragment implements View.OnClickListener{

    private static final String TAG = "NewNoteDialog";

    //widgets
    private EditText mContent;
    private TextView mCreate, mCancel;

    //vars
    private IMainActivity mIMainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int style = DialogFragment.STYLE_NORMAL;
        int theme = android.R.style.Theme_Holo_Light_Dialog;
        setStyle(style, theme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_new_note, container, false);
        mContent = view.findViewById(R.id.note_content);
        mCreate = view.findViewById(R.id.create);
        mCancel = view.findViewById(R.id.cancel);

        mCancel.setOnClickListener(this);
        mCreate.setOnClickListener(this);

        getDialog().setTitle("New Comment");

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.create:{

                // insert the new note

                String content = mContent.getText().toString();

                if(!content.equals("")){
                    mIMainActivity.createNewComment(content);
                    getDialog().dismiss();
                }
                else{
                    Toast.makeText(getActivity(), "Enter a title", Toast.LENGTH_SHORT).show();
                }
                break;
            }

            case R.id.cancel:{
                getDialog().dismiss();
                break;
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity)getActivity();
    }
}





















