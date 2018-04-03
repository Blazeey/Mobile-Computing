package com.example.vsundar17697.attendance_manager;

/**
 * Created by vsundar17697 on 23/01/18.
 */

public class student {

    String _name , _gender , _dept , _ph_no;

    public student(){
    }

    public student(String name , String id , String ph_no  , String dept){
        this._gender = id;
        this._name = name;
        this._dept = dept;
        this._ph_no = ph_no;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String name) {
        this._name = name;
    }

    public String get_id() {
        return _gender;
    }

    public void set_id(String id) {
        this._gender = id;
    }

    public String get_dept() {
        return _dept;
    }

    public void set_dept(String dept) {
        this._dept = dept;
    }

    public String get_ph_no() {
        return _ph_no;
    }

    public void set_ph_no(String ph_no) {
        this._ph_no = ph_no;
    }

}
