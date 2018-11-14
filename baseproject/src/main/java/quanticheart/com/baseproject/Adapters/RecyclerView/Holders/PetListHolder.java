/*
 *
 *  *                                     /@
 *  *                      __        __   /\/
 *  *                     /==\      /  \_/\/
 *  *                   /======\    \/\__ \__
 *  *                 /==/\  /\==\    /\_|__ \
 *  *              /==/    ||    \=\ / / / /_/
 *  *            /=/    /\ || /\   \=\/ /
 *  *         /===/   /   \||/   \   \===\
 *  *       /===/   /_________________ \===\
 *  *    /====/   / |                /  \====\
 *  *  /====/   /   |  _________    /      \===\
 *  *  /==/   /     | /   /  \ / / /         /===/
 *  * |===| /       |/   /____/ / /         /===/
 *  *  \==\             /\   / / /          /===/
 *  *  \===\__    \    /  \ / / /   /      /===/   ____                    __  _         __  __                __
 *  *    \==\ \    \\ /____/   /_\ //     /===/   / __ \__  ______  ____ _/ /_(_)____   / / / /__  ____ ______/ /_
 *  *    \===\ \   \\\\\\\/   ///////     /===/  / / / / / / / __ \/ __ `/ __/ / ___/  / /_/ / _ \/ __ `/ ___/ __/
 *  *      \==\/     \\\\/ / //////       /==/  / /_/ / /_/ / / / / /_/ / /_/ / /__   / __  /  __/ /_/ / /  / /_
 *  *      \==\     _ \\/ / /////        |==/   \___\_\__,_/_/ /_/\__,_/\__/_/\___/  /_/ /_/\___/\__,_/_/   \__/
 *  *        \==\  / \ / / ///          /===/
 *  *        \==\ /   / / /________/    /==/
 *  *          \==\  /               | /==/
 *  *          \=\  /________________|/=/
 *  *            \==\     _____     /==/
 *  *           / \===\   \   /   /===/
 *  *          / / /\===\  \_/  /===/
 *  *         / / /   \====\ /====/
 *  *        / / /      \===|===/
 *  *        |/_/         \===/
 *  *                       =
 *  *
 *  * Copyright(c) Developed by John Alves at 2018/11/6 at 1:7:49 for quantic heart studios
 *
 */

package quanticheart.com.baseproject.Adapters.RecyclerView.Holders;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import quanticheart.com.baseproject.Adapters.RecyclerView.Model.UserData;


public class PetListHolder extends RecyclerView.ViewHolder {

    public View view;
    // view
    ImageView petImage;
    TextView petName;
    Activity mActivity;
    UserData userData;

    public PetListHolder(Activity activity, View itemView) {
        super(itemView);
        view = itemView;
        mActivity = activity;
//        int viewPosition = (int) view.getTag();
//        ButterKnife.bind(this, itemView);
//
//        petImage = itemView.findViewById(R.id.petImage);
//
//        petName = itemView.findViewById(R.id.petName);
    }

    public void bind(final Activity activity, final UserData data) {
//        userData = data;
//        GlideUtil.initGlide(activity, data.getImgUser(), petImage);
//        petName.setText(data.getNameUser());
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ProjectUtils.goPetDetails(activity, data);
//            }
//        });
//        itemView.setOnLongClickListener(magazineViewOnLongClick);
    }

//    View.OnClickListener magazineViewOnClick = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(mActivity, PetPosts.class);
//            intent.putExtra(IntentKeys.keyPetData, userData);
//            ActivityUtil.callActivity(mActivity, intent, false);
//        }
//    };
//
//    View.OnLongClickListener magazineViewOnLongClick = new View.OnLongClickListener() {
//        @Override
//        public boolean onLongClick(View v) {
//            //TODO
//            return true;
//        }
//    };

}