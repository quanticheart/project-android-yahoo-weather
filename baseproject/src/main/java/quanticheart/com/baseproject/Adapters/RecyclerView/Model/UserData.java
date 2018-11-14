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
 *  * Copyright(c) Developed by John Alves at 2018/11/6 at 1:11:10 for quantic heart studios
 *
 */

package quanticheart.com.baseproject.Adapters.RecyclerView.Model;

import java.io.Serializable;

public class UserData implements Serializable {

    private int typeView;
    private String imgUser;
    private String nameUser;
    private String timeUser;
    //
    private String likeNumber;
    private String comentsNumber;
    //
    private String imgPostLink;
    private String videoPostLink;
    private String textPostLink;

    //==============================================================================================
    //
    //
    //
    //==============================================================================================

    public String getImgPostLink() {
        return imgPostLink;
    }

    public void setImgPostLink(String imgPostLink) {
        this.imgPostLink = imgPostLink;
    }

    public String getVideoPostLink() {
        return videoPostLink;
    }

    public void setVideoPostLink(String videoPostLink) {
        this.videoPostLink = videoPostLink;
    }

    public String getTextPostLink() {
        return textPostLink;
    }

    public void setTextPostLink(String textPostLink) {
        this.textPostLink = textPostLink;
    }

    public String getImgUser() {
        return imgUser;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getTimeUser() {
        return timeUser;
    }

    public void setTimeUser(String timeUser) {
        this.timeUser = timeUser;
    }

    public String getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(String likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getComentsNumber() {
        return comentsNumber;
    }

    public void setComentsNumber(String comentsNumber) {
        this.comentsNumber = comentsNumber;
    }

    public int getTypeView() {
        return typeView;
    }

    public void setTypeView(int typeView) {
        this.typeView = typeView;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "typeView=" + typeView +
                ", imgUser='" + imgUser + '\'' +
                ", nameUser='" + nameUser + '\'' +
                ", timeUser='" + timeUser + '\'' +
                ", likeNumber='" + likeNumber + '\'' +
                ", comentsNumber='" + comentsNumber + '\'' +
                ", imgPostLink='" + imgPostLink + '\'' +
                ", videoPostLink='" + videoPostLink + '\'' +
                ", textPostLink='" + textPostLink + '\'' +
                '}';
    }
}
