
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

package quanticheart.com.baseproject.Adapters.RecyclerView;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import java.util.List;

import quanticheart.com.baseproject.Adapters.RecyclerView.Model.UserData;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //==============================================================================================
    //
    // Vars
    //
    //==============================================================================================

    //First
    private Activity activity;
    private List<UserData> dataBase;
    private LayoutInflater layoutInflater;

    //Type Magazine for Load
    private int typeViewForLoad;

    //==============================================================================================
    //
    // TypeView
    //
    //==============================================================================================

    //fragments
    public static int MuralView = -1;
    public static int FriendsSolicitationView = 1;
    public static int NortificationView = 2;
    public static int PetListView = 3;
    public static int FriendsView = 4;
    public static int PetImagesView = 5;

    //==============================================================================================
    //
    // Constructor
    //
    //==============================================================================================

    /**
     * init Constructor with Magazines's List
     *
     * @param mActivity activity
     * @param typeView  typeView Load
     * @param data
     */

    public RecyclerAdapter(Activity mActivity, int typeView, List<UserData> data) {
        dataBase = data;
        activity = mActivity;
        typeViewForLoad = typeView;
        layoutInflater = LayoutInflater.from(activity);
    }

    /**
     * @param mActivity activity
     * @param data      list DatabaBase
     */
    public RecyclerAdapter(Activity mActivity, List<UserData> data) {
        dataBase = data;
        activity = mActivity;
        typeViewForLoad = -1;
        layoutInflater = LayoutInflater.from(activity);
    }

    //==============================================================================================
    //
    //
    //
    //==============================================================================================

    /**
     * @param position position adapter
     * @return ViewType for load Resorce
     */

    @Override
    public int getItemViewType(int position) {
        switch (typeViewForLoad) {
            case -1:
                return MuralView;
            case 1:
                return FriendsSolicitationView;
            case 2:
                return NortificationView;
            case 3:
                return PetListView;
            case 4:
                return FriendsView;
            case 5:
                return PetImagesView;
            default:
                return -99;
        }
    }

    private int getTypeViewInModel(int position) {
        UserData user = dataBase.get(position);
        int type = user.getTypeView();
        switch (type) {
            case 100: // text
                return 100;
            case 101: // image
                return 101;
            case 102: // Videos
                return 102;
            default:
                return -99;
        }
    }

    /**
     * @return HMAux's List size
     */
    @Override
    public int getItemCount() {
        return dataBase.size();
    }


    public static String getMagazineKey() {
        return "magazinedata";
    }


    //==============================================================================================
    //
    // Load ViewHolders
    //
    //==============================================================================================

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        if (viewType == MuralView) {
//            View view = layoutInflater.inflate(R.layout.cell_zoobook_mural, parent, false);
//            return new MuralHolder(activity, view);
//        } else if (viewType == FriendsSolicitationView) {
//            View view = layoutInflater.inflate(R.layout.cell_zoobook_friend_solicitation, parent, false);
//            return new FriendsSolicitationHolder(activity, view);
//        } else if (viewType == NortificationView) {
//            View view = layoutInflater.inflate(R.layout.cell_zoobook_nortification, parent, false);
//            return new NortificationHolder(activity, view);
//        } else if (viewType == PetListView) {
//            View view = layoutInflater.inflate(R.layout.cell_zoobook_my_pet, parent, false);
//            return new PetListHolder(activity, view);
//        } else if (viewType == FriendsView) {
//            View view = layoutInflater.inflate(R.layout.cell_zoobook_friend, parent, false);
//            return new FriendsHolder(activity, view);
//        } else if (viewType == PetImagesView) {
//            View view = layoutInflater.inflate(R.layout.cell_zoobook_pet_image_square, parent, false);
//            return new PetImagesHolder(activity, view);
//        } else {
            return null;
//        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserData data = dataBase.get(position);

//        if (holder instanceof MuralHolder) {
//            MuralHolder viewHolder = (MuralHolder) holder;
//            viewHolder.view.setOnTouchListener(touchListener);
//            viewHolder.view.setTag(position);
//            viewHolder.bind(activity, data);
//        }
//
//        if (holder instanceof FriendsSolicitationHolder) {
//            FriendsSolicitationHolder viewHolder = (FriendsSolicitationHolder) holder;
//            viewHolder.view.setOnTouchListener(touchListener);
//            viewHolder.view.setTag(position);
//            viewHolder.bind(activity, data);
//        }
//
//        if (holder instanceof NortificationHolder) {
//            NortificationHolder viewHolder = (NortificationHolder) holder;
//            viewHolder.view.setOnTouchListener(touchListener);
//            viewHolder.view.setTag(position);
//            viewHolder.bind(activity, data);
//        }
//
//        if (holder instanceof PetListHolder) {
//            PetListHolder viewHolder = (PetListHolder) holder;
//            viewHolder.view.setOnTouchListener(touchListener);
//            viewHolder.view.setTag(position);
//            viewHolder.bind(activity, data);
//        }
//
//        if (holder instanceof FriendsHolder) {
//            FriendsHolder viewHolder = (FriendsHolder) holder;
//            viewHolder.view.setOnTouchListener(touchListener);
//            viewHolder.view.setTag(position);
//            viewHolder.bind(activity, data);
//        }
//
//        if (holder instanceof PetImagesHolder) {
//            PetImagesHolder viewHolder = (PetImagesHolder) holder;
//            viewHolder.view.setOnTouchListener(touchListener);
//            viewHolder.view.setTag(position);
//            viewHolder.bind(activity, data);
//        }


    }

//    public class FriendsSolicitationHolder extends RecyclerView.ViewHolder {
//
//        public View view;
//        // view
//        ImageView imgUser;
//        TextView nameUser;
//        TextView timeUser;
//        TextView distanceUser;
//        TextView avaliationUser;
//
//        public FriendsSolicitationHolder(Activity activity, View itemView) {
//            super(itemView);
//            view = itemView;
////      int viewPosition = (int) view.getTag();
////        ButterKnife.bind(this, itemView);
//
//            imgUser = itemView.findViewById(R.id.img_user);
//            nameUser = itemView.findViewById(R.id.nameUser);
//            timeUser = itemView.findViewById(R.id.timeUser);
//            distanceUser = itemView.findViewById(R.id.distanceUser);
//            avaliationUser = itemView.findViewById(R.id.avaliationUser);
//
//
//            //TODO
//            View.OnClickListener magazineViewOnClick = new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //TODO
//                }
//            };
//            itemView.setOnClickListener(magazineViewOnClick);
//            //TODO
//            View.OnLongClickListener magazineViewOnLongClick = new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    //TODO
//                    return true;
//                }
//            };
//            itemView.setOnLongClickListener(magazineViewOnLongClick);
//        }
//
//        public void bind(UserData data) {
//
//            GlideUtil.initGlide(activity , data.getImgUser() , imgUser);
//            nameUser.setText(data.getNameUser());
//            timeUser.setText(data.getTimeUser());
//            distanceUser.setText(data.getLikeNumber());
//            avaliationUser.setText(data.getComentsNumber());
//
//        }
//
//    }

    //==============================================================================================
    //
    // Util's
    //
    //==============================================================================================

    /**
     * @param list Magazine's list
     */
    public void addList(List<UserData> list) {

//        for (int i = 0; i < list.size(); i++) {
//            dataBase.add(list.get(i));
//            notifyItemInserted(dataBase.size() + 1);
//        }
        dataBase.addAll(list);
        notifyItemInserted(dataBase.size() - 1);
//        notifyDataSetChanged();
    }

    public void setMagazinesList(List<UserData> userData) {
        dataBase.addAll(userData);
        notifyDataSetChanged();
    }

    /**
     * @param data magazine data
     */
    public void addItem(UserData data) {
        dataBase.add(data);
        notifyItemInserted(dataBase.size() - 1);
    }

    /**
     * remove all items at RecyclerView
     */
    public void clear() {
        final int size = dataBase.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                dataBase.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

//    public void addEndView() {
//        Revista end = new Revista();
//        end.setTitle(endMagazine);
//        dataBase.add(end);
//        notifyItemInserted(dataBase.size() + 1);
//    }

    //==============================================================================================
    //
    // TouchEvent
    //
    //==============================================================================================

    @SuppressLint("ClickableViewAccessibility")
    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ObjectAnimator animatorUP = ObjectAnimator.ofFloat(v, "translationZ", 20);
                    animatorUP.setDuration(200);
                    animatorUP.setInterpolator(new DecelerateInterpolator());
                    animatorUP.start();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    ObjectAnimator animatorDOWN = ObjectAnimator.ofFloat(v, "translationZ", 0);
                    animatorDOWN.setDuration(200);
                    animatorDOWN.setInterpolator(new AccelerateInterpolator());
                    animatorDOWN.start();
                    break;
            }
            return false;
        }
    };


}
