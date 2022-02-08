package org.uts.powercoil.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;

import org.uts.powercoil.AlertActivity;
import org.uts.powercoil.DetailActivity;
import org.uts.powercoil.ListrikActivity;
import org.uts.powercoil.NotifikasiActivity;
import org.uts.powercoil.R;
import org.uts.powercoil.konsultan.PengkajianActivity;
import org.uts.powercoil.user.UserActivity;

public class NotificationFragment extends Fragment {

    LottieAnimationView lottieAnimationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        CardView notif = (CardView) view.findViewById(R.id.cvNotif);
        lottieAnimationView = view.findViewById(R.id.lottie);

        lottieAnimationView.animate();



        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), AlertActivity.class);
                startActivity(intent);
            }
        });

        CardView listrik = (CardView) view.findViewById(R.id.cvListrik);
        listrik.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Intent intent1 = new Intent(getActivity(), ListrikActivity.class);
                startActivity(intent1);
            }
        });


        CardView user = (CardView) view.findViewById(R.id.cvUser);
        user.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Intent intent2 = new Intent(getActivity(), UserActivity.class);
                startActivity(intent2);
            }
        });

        CardView pengkajian = (CardView) view.findViewById(R.id.cvPengkajian);
        pengkajian.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Intent intent3 = new Intent(getActivity(), PengkajianActivity.class);
                startActivity(intent3);
            }
        });



        return view;

    }



}