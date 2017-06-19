package in.droidoselabs.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by clicklabs on 6/19/17.
 */

public class SliderItemModel implements Parcelable {

    private String name;
    private int icon;
    private int bgColor;
    private boolean selected;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.icon);
        dest.writeInt(this.bgColor);
        dest.writeByte(this.selected ? (byte) 1 : (byte) 0);
    }

    public SliderItemModel() {
    }

    protected SliderItemModel(Parcel in) {
        this.name = in.readString();
        this.icon = in.readInt();
        this.bgColor = in.readInt();
        this.selected = in.readByte() != 0;
    }

    public static final Creator<SliderItemModel> CREATOR = new Creator<SliderItemModel>() {
        @Override
        public SliderItemModel createFromParcel(Parcel source) {
            return new SliderItemModel(source);
        }

        @Override
        public SliderItemModel[] newArray(int size) {
            return new SliderItemModel[size];
        }
    };
}

