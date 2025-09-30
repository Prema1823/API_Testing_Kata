package base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;


public class bookingRequestFields {

    private int roomid;
    private String fname;
    private String lname;
    private boolean depositpaid;
    private BookingDates bookingDates;
    private String email;
    private String phone;
    private int bookingid;



    public int getRoomid() {
        return roomid;
    }
    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getFirstname(){
        return fname;
    }
    public void setFirstname(String fname){
        this.fname = fname;
    }

    public String getLastname(){
        return lname;
    }
    public void setLastname(String lname){
        this.lname = lname;
    }

    public Boolean getdepositpaid(){
        return depositpaid;
    }
    public void setdepositpaid(Boolean depositpaid){
        this.depositpaid = depositpaid;
    }

    public String getemail(){
        return email;
    }
    public void setemail(String email){
        this.email = email;
    }

    public String getphone(){
        return phone;
    }
    public void setphone(String phone){
        this.phone= phone;
    }


    public int getbookingid(){
        return bookingid;
    }
    public void setbookingid(int bookingId){
        this.bookingid= bookingid;}

    public BookingDates getBookingdates() {
        return bookingDates;
    }
    public void setBookingdates(BookingDates bookingdates) {
        this.bookingDates = bookingdates;
    }


        public static class BookingDates {
        private String checkin;
        private String checkout;

            public String getCheckin(){
                return checkin;
            }

            public void setCheckin(String checkin){
                this.checkin = checkin;
            }

            public String getCheckout(){
                return checkout;
            }

            public void setCheckout(String checkout){
                this.checkout = checkout;
            }


    }
}
