package com.edgespatial.zunguka.util;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.edgespatial.zunguka.R;
import com.edgespatial.zunguka.api.viewmodels.RecyclerViewModel;
import com.mg.surblime.util.Tools;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by moses on 6/20/18.
 */

public class DataBindingAdapters {
    @BindingAdapter("android:src")
    public static void setImageUri(final ImageView view, final String imageUri) {
        if (!imageUri.startsWith("http")) {
            setCircularTile(view, imageUri.toUpperCase());
        } else {
            Picasso picasso = Tools.getPicasso(view.getContext());
            RequestCreator requestCreator = picasso.load(imageUri)
                    .placeholder(com.mg.surblime.R.color.colorDivider);

            requestCreator.fit().centerCrop();
            requestCreator.into(view);
        }
    }

    @BindingAdapter("android:src")
    public static void setImageUri(ImageView view, Uri imageUri) {
        view.setImageURI(imageUri);
    }

    @BindingAdapter("android:src")
    public static void setImageDrawable(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageDrawable(imageView.getContext().getResources().getDrawable(resource));
    }

    @BindingAdapter("backgroundFromAttribute")
    public static void backgroundFromAttribute(View view, final int backgroundColorAttr) {
        final Context context = view.getContext();
        final int color = Tools.resolveColor(context, backgroundColorAttr, Color.BLACK);
        view.setBackgroundColor(color);
    }

    @BindingAdapter("filter")
    public static void setFilter(EditText editText, final RecyclerViewModel recyclerViewModel) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                recyclerViewModel.filterFrom(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @BindingAdapter("circularTile")
    public static void setCircularTile(ImageView imageView, String text) {
        TextDrawable textDrawable = TextDrawable.builder().beginConfig()
                .toUpperCase().endConfig()
                .buildRect(Tools.getFirstChar(text).toUpperCase(), ColorGenerator.MATERIAL.getRandomColor());

        imageView.setImageDrawable(textDrawable);
    }
}
