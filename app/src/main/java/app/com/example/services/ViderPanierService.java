package app.com.example.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;

import app.com.example.billelguerfa.projone.R;
import app.com.example.billelguerfa.projone.modele.Produit;

/**
 * Created by Dell on 27/06/2016.
 */
public class ViderPanierService extends IntentService {

    public ViderPanierService() {
        super("name");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
//        if (! PanierService.panier.getListPanier().isEmpty())
 //       {

            List<Produit> produits = new ArrayList<>();
            PanierService.panier.setListPanier(produits);
            sendNotification("votre panier a été vidé",this);

        //}


    }

    private void sendNotification(String message,Context ctx) {

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(ctx)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notification")
                .setContentText(message)
                .setAutoCancel(false)
                .setSound(defaultSoundUri);

        NotificationManager notificationManager =
                (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 , notificationBuilder.build());
    }
}