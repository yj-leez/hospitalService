package hospital.hospitalserivce.service;


import hospital.hospitalserivce.domain.Booking;
import hospital.hospitalserivce.domain.Doctor;
import hospital.hospitalserivce.domain.Member;
import hospital.hospitalserivce.repository.BookingRepository;
import hospital.hospitalserivce.repository.DoctorRepository;
import hospital.hospitalserivce.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookingService {

    private final MemberRepository memberRepository;
    private final DoctorRepository doctorRepository;
    private final BookingRepository bookingRepository;

    /**
     * 예약
     */
    @Transactional
    public Long booking(Long memberId, Long doctorId){
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Doctor doctor = doctorRepository.findOne(doctorId);

        //예약 생성
        Booking booking = Booking.createBooking(member, doctor);

        //예약 저장
        bookingRepository.save(booking);
        return booking.getId();
    }

    @Transactional
    public void completedTreatment(Long bookingId){
        //예약 엔티티 조회
        Booking booking = bookingRepository.findOne(bookingId);

        //진료 정보로 저장
        booking.completed();
    }
}
