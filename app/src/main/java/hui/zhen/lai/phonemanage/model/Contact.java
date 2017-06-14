package hui.zhen.lai.phonemanage.model;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by Profe on 2017/5/18 0018.
 */

public class Contact implements Serializable {

    private Integer id;
    private String name;
    private String num1;
    private String num2;
    private String email;
    private String remark;
    private Bitmap headPic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Bitmap getHeadPic() {
        return headPic;
    }

    public void setHeadPic(Bitmap headPic) {
        this.headPic = headPic;
    }

    public Integer getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contact() {
        super();
    }

    public Contact(String name, String num1) {
        this.name = name;
        this.num1 = num1;
    }
}
