package hospital.hospitalserivce.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.print.Doc;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKING_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOCTOR_ID")
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private LocalDateTime dateTime;

    //==연관관계 메서드==//
    public void setMember(Member member){
        this.member = member;
        member.getBookings().add(this); //멤버와 양방향관계
    }

    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
        doctor.getBookings().add(this);
    }

    //==생성 메서드==//
    public static Booking createBooking(Member member, Doctor doctor) {
        Booking booking = new Booking();
        booking.setMember(member);
        booking.setDoctor(doctor);
        booking.setStatus(BookingStatus.READY);
        booking.setDateTime(LocalDateTime.now());
        return booking;
    }

    //==비즈니스 로직==//
    public void completed(){
        this.setStatus(BookingStatus.COMP);
        this.setDateTime(LocalDateTime.now()); //완료한 시간으로 변경
    }

}
