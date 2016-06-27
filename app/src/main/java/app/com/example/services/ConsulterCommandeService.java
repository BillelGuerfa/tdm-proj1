package app.com.example.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import app.com.example.billelguerfa.projone.R;

/**
 * Created by yacine on 27/06/2016.
 */
public class ConsulterCommandeService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ConsulterCommandeService() {
        super("name");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        /*verifier d'abord si c'est la toute premier commande crée pour ce user */
        /* CODE CONSULTATION DE TOUTES LES COMMANDES LIEe AU BON USER*/



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