package com.academy.shoplist.interfac;

import android.view.View;

public interface ItemClickListener {

    void onItemClick(int position);

    void onItemDelete(int position);

    void onItemEdit(int position);

}
